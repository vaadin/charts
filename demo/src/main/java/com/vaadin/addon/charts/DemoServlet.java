package com.vaadin.addon.charts;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@VaadinServletConfiguration(productionMode = false, ui = ChartsDemoUI.class, widgetset = "com.vaadin.addon.charts.AppWidgetSet")
@WebServlet("/*")
public class DemoServlet extends VaadinServlet {
}
