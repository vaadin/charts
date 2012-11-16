package com.vaadin.addon.charts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.reflections.Reflections;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.annotations.JavaScript;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@JavaScript("prettify.js")
public class ChartsDemoUI extends UI {

    private static Map<String, List<Class<? extends AbstractVaadinChartExample>>> tests;
    
    private static final String[] GROUP_ORDER = {"Column and Bar", "Pie", "Area", "Line and Scatter", "Dynamic", "Combinations", "Other", "Container", "Themes"};

    static {
        Reflections reflections = new Reflections(
                "com.vaadin.addon.charts.demoandtestapp");

        Set<Class<? extends AbstractVaadinChartExample>> subTypes = reflections
                .getSubTypesOf(AbstractVaadinChartExample.class);

        Map<String, List<Class<? extends AbstractVaadinChartExample>>> grouped = new HashMap<String, List<Class<? extends AbstractVaadinChartExample>>>();

        for (Class<? extends AbstractVaadinChartExample> class1 : subTypes) {
            Package package1 = class1.getPackage();
            String name = package1.getName();
            name = name.substring(name.lastIndexOf(".") + 1);

            List<Class<? extends AbstractVaadinChartExample>> list = grouped
                    .get(name);
            if (list == null) {
                list = new ArrayList<Class<? extends AbstractVaadinChartExample>>();
                grouped.put(name, list);
            }
            list.add(class1);
        }
        tests = grouped;
    }

    private Tree tree;

    @Override
    protected void init(VaadinRequest request) {
        
        final TabSheet tabSheet = new TabSheet();
        tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(SelectedTabChangeEvent event) {
                com.vaadin.ui.JavaScript.eval(
                        "setTimeout(function(){prettyPrint();},300);");
            }
        });
        tabSheet.setSizeFull();

        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        horizontalSplitPanel.setSecondComponent(tabSheet);
        horizontalSplitPanel.setSplitPosition(20);
        setContent(horizontalSplitPanel);

        tree = new Tree();
        tree.setImmediate(true);
        tree.setContainerDataSource(getContainer());
        tree.setItemCaptionPropertyId("displayName");
        Panel panel = new Panel("Vaadin Charts - Test Explorer");
        panel.setContent(tree);
        panel.setSizeFull();
        horizontalSplitPanel.setFirstComponent(panel);

        tree.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                Object value2 = event.getProperty().getValue();
                if (value2 instanceof Class) {
                    try {
                        Class value = (Class) value2;
                        AbstractVaadinChartExample newInstance = (AbstractVaadinChartExample) value
                                .newInstance();
                        tabSheet.removeAllComponents();
                        tabSheet.addTab(newInstance, "Graph");
                        
                        String r = "/"+value.getName().replace(".", "/") + ".java";
                        r = value.getSimpleName() + ".java";
                        InputStream resourceAsStream = newInstance.getClass()
                                .getResourceAsStream(r);
                        String code = IOUtils.toString(resourceAsStream);
                        Label c = new Label("<pre class='prettyprint'>"+code + "</pre>");
                        c.setContentMode(ContentMode.HTML);
                        tabSheet.addTab(c, "Source");
                        
                        Page.getCurrent().setUriFragment(value.getSimpleName(), false);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        // e.printStackTrace();
                    }
                } else {
                    tree.expandItemsRecursively(value2);
                }
            }
        });
        
        selectItem();
        
        Page.getCurrent().addUriFragmentChangedListener(new UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(UriFragmentChangedEvent event) {
                selectItem();
            }
        });
        
    }

    private void selectItem() {
        String uriFragment = Page.getCurrent().getUriFragment();
        if(uriFragment != null) {
            Collection<?> itemIds = tree.getItemIds();
            for (Object object : itemIds) {
                if (object instanceof Class) {
                    @SuppressWarnings("unchecked")
                    Class<? extends AbstractVaadinChartExample> t = (Class<? extends AbstractVaadinChartExample>) object;
                    if(t.getSimpleName().equals(uriFragment)) {
                        Object parent2 = tree.getParent(t);
                        tree.expandItem(parent2);
                        tree.setValue(t);
                        return;
                    }
                }
            }
        } else {
            Iterator<?> iterator = tree.getItemIds().iterator();
            tree.expandItem(iterator.next());
            tree.select(iterator.next());
        }
        tree.focus();
        
    }

    private HierarchicalContainer getContainer() {

        HierarchicalContainer hierarchicalContainer = new HierarchicalContainer();
        hierarchicalContainer.addContainerProperty("displayName", String.class,
                "");
        
        for (String g : GROUP_ORDER) {
            String group = g.replaceAll(" ", "").toLowerCase();
            Item groupItem = hierarchicalContainer.addItem(group);
            groupItem.getItemProperty("displayName").setValue(
                    g);
            List<Class<? extends AbstractVaadinChartExample>> list = tests
                    .get(group);
            for (Class<? extends AbstractVaadinChartExample> class1 : list) {
                Item testItem = hierarchicalContainer.addItem(class1);
                testItem.getItemProperty("displayName").setValue(
                        class1.getSimpleName());
                hierarchicalContainer.setParent(class1, group);
                hierarchicalContainer.setChildrenAllowed(class1, false);
            }
            
        }

        return hierarchicalContainer;
    }

}
