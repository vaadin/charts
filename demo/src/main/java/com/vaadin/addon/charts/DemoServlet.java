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
package com.vaadin.addon.charts;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@VaadinServletConfiguration(productionMode = false, ui = ChartsDemoUI.class, widgetset = "com.vaadin.addon.charts.AppWidgetSet")
@WebServlet("/*")
public class DemoServlet extends VaadinServlet {
}
