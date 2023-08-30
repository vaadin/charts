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
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(value = "/*", asyncSupported = true, initParams = {
        @WebInitParam(name = "heartbeatInterval", value = "10"),
        @WebInitParam(name = "widgetset", value = "com.vaadin.addon.charts.AppWidgetSet"),
        @WebInitParam(name = "UIProvider", value = "com.vaadin.addon.charts.demoandtestapp.AbstractUIProviderImpl")})
public class TestServlet extends VaadinServlet {
}
