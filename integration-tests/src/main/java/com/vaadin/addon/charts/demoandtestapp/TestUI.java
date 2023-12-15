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

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

public class TestUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        String name = request.getPathInfo();
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        if (!"".equals(name) && !name.contains(".ico")
                && name.matches("[A-Za-z/].*")) {
            try {

                String className;
                if (name.startsWith("com.")) {
                    className = name;
                } else {
                    className = "com.vaadin.addon.charts.examples."
                            + name.replace("/", ".");
                }
                @SuppressWarnings("unchecked")
                Class<? extends ComponentContainer> forName = (Class<? extends ComponentContainer>) Class
                        .forName(className);
                if (forName != null) {
                    setContent(forName.newInstance());
                }
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
