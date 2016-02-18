package com.vaadin.addon.charts.declarative;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.AbstractPlotOptions;
import com.vaadin.addon.charts.model.Axis;
import com.vaadin.addon.charts.model.AxisList;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.ui.declarative.DesignAttributeHandler;
import com.vaadin.ui.declarative.DesignException;

import org.jsoup.nodes.Element;

public class ChartDesignWriter implements Serializable {

    /**
     * These properties are ignored when reading values from configuration object
     *      For example, serialVersionUid is not wanted to be added to declarative html
     **/
    private static final List<String> ignoredConfigurationObjectProperties =
        Arrays.asList(
            "serialversionuid", "series", "changelisteners", "axisindex",
            "configuration");
    private static final HashMap<Class<? extends AbstractConfigurationObject>, AbstractConfigurationObject>
        cache =
        new HashMap<Class<? extends AbstractConfigurationObject>, AbstractConfigurationObject>();
    private static Logger logger =
        Logger.getLogger(ChartDesignReader.class.getName());

    public static void writeConfigurationToElement(
        AbstractConfigurationObject configuration, Element parent) {
        List<Field> declaredFields = resolveFields(configuration.getClass());
        for (Field field : declaredFields) {
            if (ignoredConfigurationObjectProperties
                .contains(field.getName().toLowerCase())) {
                continue;
            }
            Object value = readFieldValue(configuration, field);

            if (value != null) {
                if (isPlotOptions(field)) {
                    writePlotOptions(
                        parent, (Map<String, AbstractPlotOptions>) value);
                } else if (isAxisList(field)) {
                    writeAxisList(parent, (AxisList) value);
                } else if (isConfigurationObject(field.getType())) {
                    createElementForConfigurationObject(
                        (AbstractConfigurationObject) value, parent,
                        field.getName());
                } else if (isCollection(field)) {
                    writeCollection(
                        configuration, parent, field, (Collection) value);
                } else if (isAttribute(field.getType())) {
                    writeAttribute(configuration, parent, field);
                } else {
                    logger.log(
                        Level.INFO,
                        "Field '" + field.getName() + "' with type '" + field.getType().getName() +
                            "' in class " + configuration.getClass().getName() +
                            " was not added to declarative output");
                }
            }
        }

    }

    private static boolean isAttribute(Class<?> type) {
        return DesignAttributeHandler.getFormatter().canConvert(type);
    }

    private static boolean isCollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }

    private static boolean isConfigurationObject(Class<?> type) {
        return AbstractConfigurationObject.class.isAssignableFrom(
            type);
    }

    private static boolean isAxisList(Field field) {
        return AxisList.class.isAssignableFrom(field.getType());
    }

    private static boolean isPlotOptions(Field field) {
        return "plotoptions".equals(field.getName().toLowerCase());
    }

    private static void writeAttribute(
        AbstractConfigurationObject configuration, Element parent,
        Field field) {
        AbstractConfigurationObject defaultConfiguration =
            resolveDefaultConfiguration(configuration.getClass());
        String attributeName = toNodeName(
            field.getName().replace("_fn_", ""));
        DesignAttributeHandler.writeAttribute(
            configuration, attributeName, parent.attributes(),
            defaultConfiguration);
    }

    private static void writeCollection(
        AbstractConfigurationObject configuration, Element parent, Field field,
        Collection collection) {
        if (!collection.isEmpty()) {
            if (containsConfigurationObjects(collection)) {
                for (Object o : collection) {
                    String tagName = o.getClass().getSimpleName() + "s";
                    createElementForConfigurationObject(
                        (AbstractConfigurationObject) o, parent, tagName);
                }
            } else {
                writeCollectionElement(
                    configuration, parent, field, collection);
            }
        }
    }

    private static void writeAxisList(Element parent, AxisList value) {
        AxisList axisList = value;
        for (int i = 0; i < axisList.getNumberOfAxes(); ++i) {
            Axis axis = axisList.getAxis(i);
            String tagName = axis.getClass().getSimpleName();
            createElementForConfigurationObject(
                axis, parent, tagName);
        }
    }

    private static void writePlotOptions(Element parent,
        Map<String, AbstractPlotOptions> value) {
        Map<String, AbstractPlotOptions> plotOptions = value;
        for (Map.Entry<String, AbstractPlotOptions> entry : plotOptions
            .entrySet()) {
            Element plotOptionsElement = parent.appendElement("plot-options");
            createElementForConfigurationObject(entry.getValue(), plotOptionsElement, entry.getKey());
        }
    }

    private static Object readFieldValue(
        AbstractConfigurationObject configuration, Field field) {
        Object value = null;
        try {
            field.setAccessible(true);
            value = field.get(configuration);
        } catch (IllegalAccessException e) {
            logger.log(
                Level.WARNING,
                "Could not get value from field " + field.getName() +
                    " in " + configuration.getClass().getName());
        }
        return value;
    }

    private static boolean containsConfigurationObjects(Collection collection) {
        return isConfigurationObject(collection.iterator().next().getClass());
    }

    private static void writeCollectionElement(
        AbstractConfigurationObject configuration, Element parent, Field field,
        Collection collection) {
        Element element = parent.appendElement(toNodeName(field.getName()));
        StringBuilder collectionValue = new StringBuilder();
        for (Object object : collection) {
            if (collectionValue.length() > 0) {
                collectionValue.append(", ");
            }
            if (!isAttribute(object.getClass())) {
                logger.log(
                    Level.WARNING,
                    "Cannot convert class " + object.getClass().getName() +
                        " in field " + field.getName() + " in a class " +
                        configuration.getClass().getName());
                continue;
            }
            String formatted = DesignAttributeHandler.getFormatter().format(
                object);
            collectionValue.append(formatted);
        }
        if ("".equals(collectionValue)) {
            element.remove();
        } else {
            element.appendText(collectionValue.toString());
        }
    }

    private static List<Field> resolveFields(
        Class<? extends AbstractConfigurationObject> clazz) {
        List<Field> allFields = new ArrayList<Field>();
        for (Field field : clazz.getDeclaredFields()) {
            allFields.add(field);
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && isConfigurationObject(superclass)) {
            allFields.addAll(
                resolveFields(
                    (Class<? extends AbstractConfigurationObject>) superclass));
        }

        return allFields;
    }

    private static void createElementForConfigurationObject(
        AbstractConfigurationObject newConfiguration, Element parent,
        String tagName) {
        Element element = parent.appendElement(toNodeName(tagName));
        writeConfigurationToElement(newConfiguration, element);
        if (isEmpty(element)) {
            element.remove();
        }
    }

    private static boolean isEmpty(Element element) {
        return element.attributes().size() == 0 &&
            element.children().size() == 0;
    }

    private static AbstractConfigurationObject resolveDefaultConfiguration(
        Class<? extends AbstractConfigurationObject> clazz) {
        if (cache.containsKey(clazz)) {
            return cache.get(clazz);
        }

        AbstractConfigurationObject defaultObject =
            readDefaultFromChartConfigurationObject(clazz);
        if (defaultObject == null) {
            try {
                defaultObject = clazz.newInstance();
            } catch (Exception e) {
                logger.log(
                    Level.SEVERE,
                    "Was not able to create default instance for" +
                        clazz.getName());
            }
        }
        cache.put(clazz, defaultObject);

        return defaultObject;
    }

    private static AbstractConfigurationObject readDefaultFromChartConfigurationObject(
        Class<? extends AbstractConfigurationObject> clazz) {
        if (Configuration.class.equals(clazz)) {
            return null;
        }

        Configuration defaultConfiguration =
            (Configuration) resolveDefaultConfiguration(Configuration.class);

        for (Method method : Configuration.class.getMethods()) {
            if (method.getName().startsWith("get") &&
                method.getParameterTypes().length == 0 &&
                method.getReturnType().equals(clazz)) {
                try {
                    return (AbstractConfigurationObject) method
                        .invoke(defaultConfiguration);
                } catch (Exception e) {
                    throw new DesignException(
                        "Was not able to create default " + clazz.getName() +
                            " from default Configuration object", e);
                }

            }
        }
        return null;
    }

    /**
     * Reads strings in camelcase format and returns node names where words are
     * all lowercase and splitted with character '-'
     *      For example, YAxis -> y-axis and dataLabels -> data-labels
     *
     * @param camelcase
     * @return node name
     */
    private static String toNodeName(String camelcase) {
        return camelcase
            .replaceAll("(?<=[^A-Z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])", "-")
            .toLowerCase();
    }
}
