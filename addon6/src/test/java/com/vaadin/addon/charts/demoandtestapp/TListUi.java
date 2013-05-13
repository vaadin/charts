/*
 * Copyright 2012 Vaadin Community.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vaadin.addon.charts.demoandtestapp;

import java.io.File;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 */
public class TListUi extends Window {
    private IndexedContainer testClassess;
    
    public TListUi() {
    	loadTestClasses(this);
	}


    private void loadTestClasses(TListUi aThis) {
        if (testClassess != null) {
            return;
        }
        testClassess = new IndexedContainer();
        testClassess.addContainerProperty("name", String.class, "");
        testClassess.addContainerProperty("description", String.class, "");
        testClassess.addContainerProperty("package", String.class, "");

        listTestClasses(testClassess, null);
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

        Table table = new Table("Test cases", testClassess);
        table.addGeneratedColumn("name", new Table.ColumnGenerator() {
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                String name = (String) source.getItem(itemId)
                        .getItemProperty(columnId).getValue();
                Link link = new Link();
                link.setResource(new ExternalResource("/" + name));
                link.setCaption(name);
                link.setTargetName("_new");
                return link;
            }
        });
        table.addGeneratedColumn("description", new Table.ColumnGenerator() {
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
        filter.addListener(new TextChangeListener() {
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
        String path = "src/test/java/"
                + getClass().getPackage().getName().replace('.', '/');
        if (subpackage != null) {
            path = path + "/" + subpackage;
        }
        File file = new File(path);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (File f : listFiles) {
                try {
                    String name = f.getName();
                    String simpleName = name
                            .substring(0, name.indexOf(".java"));
                    String fullname = getClass().getPackage().getName() + "."
                            + (subpackage != null ? subpackage + "." : "")
                            + simpleName;
                    Class<?> forName = Class.forName(fullname);
                    addTest(indexedContainer, fullname, forName, subpackage);
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void addTest(Container indexedContainer, String simpleName,
            Class<?> forName, String subpackage) throws InstantiationException,
            IllegalAccessException {
        if (AbstractVaadinChartExample.class.isAssignableFrom(forName)) {
        	try {
        		AbstractVaadinChartExample newInstance = (AbstractVaadinChartExample) forName
        				.newInstance();
        		Object id = indexedContainer.addItem();
        		Item item = indexedContainer.getItem(id);
        		item.getItemProperty("name").setValue(simpleName);
        		item.getItemProperty("description").setValue(
        				newInstance.getDescription());
        		item.getItemProperty("package").setValue(subpackage);
        	} catch (Throwable e) {
//        		e.printStackTrace();
        	}
        }
    }

}
