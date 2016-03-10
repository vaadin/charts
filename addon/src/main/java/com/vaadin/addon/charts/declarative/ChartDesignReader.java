package com.vaadin.addon.charts.declarative;

import java.beans.BeanInfo;
import java.beans.IndexedPropertyDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.ui.declarative.ChartDesignFormatter;
import com.vaadin.ui.declarative.DesignAttributeHandler;
import com.vaadin.ui.declarative.DesignException;

public class ChartDesignReader implements Serializable {

    static {
        // This is needed to add a converter from String to Number
        ChartDesignFormatter.init();
    }

    private static Logger logger = Logger.getLogger(ChartDesignReader.class
            .getName());

    private static Map<Class<?>, ConfigurationCacheEntry> cache = new ConcurrentHashMap<Class<?>, ConfigurationCacheEntry>();

    /**
     * Nodes whose text content should be set as text property value For
     * example, <chart-title>My title</chart-title> will be set as
     * title.setText("My title")
     **/
    private static final List<String> textContentNodes = Arrays.asList(
            "subtitle", "chart-title");

    /**
     * Nodes that contain an array of values as a comma-separated list For
     * example, <categories>Jan,Feb,March</categories>
     **/
    private static final List<String>
        arrayNodes = Arrays.asList("categories", "stops", "margin", "center", "units", "colors");

    private static class ConfigurationCacheEntry implements Serializable {

        private Map<String, Method> setterMethods = new ConcurrentHashMap<String, Method>();

        public void addProperty(String propertyName, Method setter) {
            setterMethods.put(propertyName, setter);
        }

        public Method getSetter(String nodeName) {
            return setterMethods.get(nodeName);
        }
    }

    public static void readConfigurationFromElements(Elements elements,
            Configuration configuration) {
        addToConfiguration(elements, configuration);
    }

    private static void addToConfiguration(Elements children,
            AbstractConfigurationObject configuration) {
        for (Element child : children) {
            if ("plotoptions".equals(toPropertyName(child.nodeName()))) {
                addToConfiguration(child.children(), configuration);
            } else {
                addToConfiguration(child, configuration);
            }
        }
    }

    private static void addToConfiguration(Element element,
            AbstractConfigurationObject parent) {
        resolvePropertySettersFor(parent.getClass());

        Object value = createValueObjectForElement(element, parent);
        readTextContentNodes(element, value);
        readAttributeValues(element, value);
        setValueToParent(parent, element, value);

        if (element.children().size() > 0
                && value instanceof AbstractConfigurationObject) {
            addToConfiguration(element.children(),
                    (AbstractConfigurationObject) value);
        }
    }

    private static void readTextContentNodes(Element element, Object value) {
        if (textContentNodes.contains(element.nodeName())
                && hasOnlyText(element)) {
            DesignAttributeHandler.assignValue(value, "text", element.text());
        }
    }

    private static void readAttributeValues(Element element, Object value) {
        for (Attribute attribute : element.attributes()) {
            String attributeName = removeDrawPrefix(attribute.getKey());
            DesignAttributeHandler.assignValue(value, attributeName,
                    attribute.getValue());
        }
    }

    private static Object createValueObjectForElement(Element element,
            AbstractConfigurationObject parent) {
        String nodeName = element.nodeName();
        if(arrayNodes.contains(nodeName)) {
            return  readArrayValue(element.text());
        } else if(isPlotOptions(element)) {
            return  createPlotOptionsFor(nodeName);
        } else if(isColor(element)) {
            return ColorFactory.createGradient(element);
        } else {
            return createConfigurationFor(nodeName, parent);
        }
    }

    private static boolean isColor(Element element) {
        return element.nodeName().contains("color");
    }

    private static boolean isPlotOptions(Element element) {
        if (element.parent() == null) {
            return false;
        }
        String parentNodeName = element.parent().nodeName();
        return "plotoptions".equals(toPropertyName(parentNodeName));
    }

    private static AbstractPlotOptions createPlotOptionsFor(String type) {
        String plotOptionsClassName = "com.vaadin.addon.charts.model.PlotOptions"
                + toClassName(type);
        try {
            Class<?> plotOptionsClass = Class.forName(plotOptionsClassName);
            Object plotOptions = plotOptionsClass.newInstance();
            return (AbstractPlotOptions) plotOptions;
        } catch (ClassNotFoundException e) {
            throw new DesignException("Cannot find plot options class: "
                    + plotOptionsClassName);
        } catch (InstantiationException e) {
            throw new DesignException("Cannot create options class: "
                    + plotOptionsClassName);
        } catch (IllegalAccessException e) {
            throw new DesignException("Cannot create options class: "
                    + plotOptionsClassName);
        }
    }

    private static String toClassName(String nodeName) {
        nodeName = removeChartsPrefix(nodeName);
        String[] words = nodeName.split("-");
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            builder.append(Character.toUpperCase(word.charAt(0)));
            builder.append(word.substring(1).toLowerCase());
        }
        return builder.toString();
    }

    private static String[] readArrayValue(String text) {
        if (text == null) {
            return null;
        }
        String[] values = text.split(",");
        for (int i = 0; i < values.length; ++i) {
            values[i] = values[i].trim();
        }
        return values;
    }

    private static boolean hasOnlyText(Element element) {
        return element.hasText() && element.children().size() == 0;
    }

    private static void setValueToParent(AbstractConfigurationObject parent,
            Element element, Object value) {
        String propertyName = resolvePropertyName(element);
        Method setter = cache.get(parent.getClass()).getSetter(propertyName);
        if (setter == null) {
            throw new DesignException("Could not find setter for "
                    + propertyName + " in class " + parent.getClass());
        }

        try {
            setter.invoke(parent, value);
        } catch (Exception e) {
            throw new DesignException("Could not set value type "
                    + value.getClass() + " to class " + parent.getClass(), e);
        }
    }

    private static String resolvePropertyName(Element element) {
        if (isPlotOptions(element)) {
            return "plotoptions";
        }
        return toPropertyName(element.nodeName());
    }

    private static void resolvePropertySettersFor(
            Class<? extends AbstractConfigurationObject> parentClass) {
        if (cache.containsKey(parentClass)) {
            return;
        }

        BeanInfo beanInfo = getBeanInfo(parentClass);

        ConfigurationCacheEntry entry = new ConfigurationCacheEntry();
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            if(!(isChartModelType(descriptor) || isArrayNodeProperty(descriptor)
                  || isColorProperty(descriptor))) {
                    continue;
            }
            Method setter = resolveWriteMethod(descriptor, beanInfo);
            if (setter != null) {
                entry.addProperty(descriptor.getName().toLowerCase(), setter);
            } else {
                logger.log(
                        Level.FINE,
                        "Setter was not found for property "
                                + descriptor.getName() + " in class "
                                + parentClass.getName());
            }
        }
        cache.put(parentClass, entry);
    }

    private static Method resolveWriteMethod(PropertyDescriptor descriptor,
            BeanInfo beanInfo) {
        if (useAddMethodFor(descriptor)) {
            for (MethodDescriptor method : beanInfo.getMethodDescriptors()) {
                if (isAddPropertyMethod(descriptor, method)) {
                    return method.getMethod();
                }
            }
            return null;
        }
        return descriptor.getWriteMethod();
    }

    private static boolean useAddMethodFor(PropertyDescriptor descriptor) {
        // null, when instance of IndexedPropertyDescriptor
        return descriptor.getPropertyType() == null
                || descriptor.getWriteMethod() == null
                || descriptor.getPropertyType().isArray()
                && isChartModelType(descriptor);
    }

    private static boolean isAddPropertyMethod(PropertyDescriptor descriptor,
            MethodDescriptor method) {
        return method.getName().toLowerCase()
                .equals(expectedAddMethod(descriptor).toLowerCase())
                && method.getMethod().getParameterTypes().length == 1
                && method.getMethod().getParameterTypes()[0]
                        .equals(resolvePropertyType(descriptor));
    }

    private static String expectedAddMethod(PropertyDescriptor descriptor) {
        return "add" + singularForm(descriptor.getName());
    }

    private static String singularForm(String name) {
        if ("plotOptions".equals(name)) {
            return "plotOptions";
        }
        if (name.endsWith("Axis")) {
            return name;
        }
        if (name.endsWith("ies")) {
            return name.substring(0, name.length() - 3) + "y";
        }
        if (name.endsWith("s")) {
            return name.substring(0, name.length() - 1);
        }
        return name;
    }

    private static BeanInfo getBeanInfo(
            Class<? extends AbstractConfigurationObject> parentClass) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(parentClass);
        } catch (IntrospectionException e) {
            throw new DesignException(
                    "Could not get supported attributes for class "
                            + parentClass.getName());
        }
        return beanInfo;
    }

    private static AbstractConfigurationObject createConfigurationFor(
            String nodeName, AbstractConfigurationObject parent) {
        BeanInfo beanInfo = getBeanInfo(parent.getClass());
        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            if (isChartModelType(descriptor)
                    && descriptor.getName().toLowerCase()
                            .equals(toPropertyName(nodeName))) {
                try {
                    return (AbstractConfigurationObject) resolvePropertyType(
                            descriptor).newInstance();
                } catch (InstantiationException e) {
                    throw new DesignException("Unknown tag: " + nodeName, e);
                } catch (IllegalAccessException e) {
                    throw new DesignException("Unknown tag: " + nodeName, e);
                }
            }
        }
        throw new DesignException("Unknown tag: " + nodeName);
    }

    private static boolean isChartModelType(PropertyDescriptor descriptor) {
        return AbstractConfigurationObject.class
                .isAssignableFrom(resolvePropertyType(descriptor));
    }

    private static boolean isArrayNodeProperty(PropertyDescriptor descriptor) {
        return arrayNodes.contains(descriptor.getName());
    }

    private static boolean isColorProperty(PropertyDescriptor descriptor) {
        return Color.class.isAssignableFrom(resolvePropertyType(descriptor));
    }

    private static Class<?> resolvePropertyType(PropertyDescriptor descriptor) {
        if ("plotOptions".equals(descriptor.getName())) {
            return AbstractPlotOptions.class;
        }
        if (descriptor instanceof IndexedPropertyDescriptor) {
            return ((IndexedPropertyDescriptor) descriptor)
                    .getIndexedPropertyType();
        }
        Class<?> propertyType = descriptor.getPropertyType();
        if (propertyType.isArray()) {
            return propertyType.getComponentType();
        }

        return propertyType;
    }

    private static String toPropertyName(String nodeName) {
        if (nodeName == null) {
            return null;
        }
        nodeName = removeChartsPrefix(nodeName);
        return nodeName.toLowerCase().replace("-", "");
    }

    private static String removeChartsPrefix(String nodeName) {
        if (nodeName.toLowerCase().startsWith(ChartDesignCommon.CHART_PREFIX)) {
            nodeName = nodeName.substring(ChartDesignCommon.CHART_PREFIX
                    .length());
        }
        return nodeName;
    }

    private static String removeDrawPrefix(String nodeName) {
        if (nodeName.toLowerCase().startsWith(ChartDesignCommon.RESERVED_PROPERTY_PREFIX)) {
            nodeName = nodeName.substring(ChartDesignCommon.RESERVED_PROPERTY_PREFIX
                    .length());
        }
        return nodeName;
    }

}
