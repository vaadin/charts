/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.demoandtestapp;

import java.util.Set;

import org.reflections.Reflections;

import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.v7.event.FieldEvents.TextChangeEvent;
import com.vaadin.v7.event.FieldEvents.TextChangeListener;
import com.vaadin.v7.ui.Table;
import com.vaadin.v7.ui.TextField;


/**
 *
 */
public class TListUi extends UI {
    private IndexedContainer testClassess;

    @Override
    protected void init(VaadinRequest request) {
        loadTestClasses(TListUi.this);
    }

    private void loadTestClasses(TListUi aThis) {
        if (testClassess != null) {
            return;
        }
        testClassess = new IndexedContainer();
        testClassess.addContainerProperty("name", String.class, "");
        testClassess.addContainerProperty("description", String.class, "");
        testClassess.addContainerProperty("package", String.class, "");

        listTestClasses(testClassess, "area");
        listTestClasses(testClassess, "columnandbar");
        listTestClasses(testClassess, "combinations");
        listTestClasses(testClassess, "container");
        listTestClasses(testClassess, "dynamic");
        listTestClasses(testClassess, "lineandscatter");
        listTestClasses(testClassess, "other");
        listTestClasses(testClassess, "pie");
        listTestClasses(testClassess, "themes");
        listTestClasses(testClassess, "librarydata");
        listTestClasses(testClassess, "timeline");
        listTestClasses(testClassess, "threed");
        listTestClasses(testClassess, "declarative");
        listTestClasses(testClassess, "dataprovider");

        Table table = new Table("Test cases", testClassess);
        table.addGeneratedColumn("name", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String name = (String) source.getItem(itemId)
                        .getItemProperty(columnId).getValue();
                String pack = (String) source.getItem(itemId)
                        .getItemProperty("package").getValue();
                Link link = new Link();
                link.setResource(new ExternalResource("/"
                        + (pack != null ? pack + "/" : "") + name));
                link.setCaption(name);
                link.setTargetName("_new");
                return link;
            }
        });
        table.addGeneratedColumn("description", new Table.ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String description = (String) source.getItem(itemId)
                        .getItemProperty(columnId).getValue();
                return new Label(description);
            }
        });
        table.setSizeFull();
        table.setColumnExpandRatio("description", 1);

        VerticalLayout verticalLayout = new VerticalLayout();
        TextField filter = new TextField();
        filter.addTextChangeListener(new TextChangeListener() {
            @Override
            public void textChange(TextChangeEvent event) {
                String text = event.getText();
                testClassess.removeAllContainerFilters();
                testClassess.addContainerFilter("name", text, true, false);
            }
        });
        verticalLayout.addComponent(filter);
        filter.focus();
        verticalLayout.addComponent(table);
        setContent(verticalLayout);
    }

    private void listTestClasses(Container indexedContainer, String subpackage) {
        String packageName = "com.vaadin.addon.charts.examples";

        if(subpackage != null) {
            packageName += "." + subpackage;
        }

        Reflections reflections = new Reflections(packageName);

        Set<Class<? extends AbstractVaadinChartExample>> subTypes = reflections
                .getSubTypesOf(AbstractVaadinChartExample.class);

        for(Class<? extends AbstractVaadinChartExample> subType: subTypes) {
            try {
                addTest(indexedContainer, subType.getSimpleName(), subType, subpackage);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void addTest(Container indexedContainer, String simpleName,
            Class<?> forName, String subpackage) throws InstantiationException,
            IllegalAccessException {
        if (AbstractVaadinChartExample.class.isAssignableFrom(forName)) {
            AbstractVaadinChartExample newInstance = (AbstractVaadinChartExample) forName
                    .newInstance();
            Object id = indexedContainer.addItem();
            Item item = indexedContainer.getItem(id);
            item.getItemProperty("name").setValue(simpleName);
            item.getItemProperty("description").setValue(
                    newInstance.getDescription());
            item.getItemProperty("package").setValue(subpackage);
        }
    }

}
