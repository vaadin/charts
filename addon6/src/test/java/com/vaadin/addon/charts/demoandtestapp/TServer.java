package com.vaadin.addon.charts.demoandtestapp;

import java.io.File;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import com.vaadin.terminal.gwt.server.ApplicationServlet;

public class TServer {

    private static final int PORT = 9998;

    /**
     * 
     * Test server for the addon.
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        startServer(PORT);
    }

    public static Server startServer(Integer port) throws Exception {
        Server server = new Server();

        final Connector connector = new SelectChannelConnector();

        if (port != null) {
            connector.setPort(port);
        }
        server.setConnectors(new Connector[] { connector });

        WebAppContext context = new WebAppContext();
        ApplicationServlet vaadinServlet = new ApplicationServlet();
        ServletHolder servletHolder = new ServletHolder(vaadinServlet);
        servletHolder.setInitParameter("widgetset",
                "com.vaadin.addon.charts.ChartsWithTimelineWidgetset");
        servletHolder.setInitParameter("application", TestApp.class.getName());

        File file = new File("target/testwebapp");
        context.setWar(file.getPath());
        context.setContextPath("/");

        context.addServlet(servletHolder, "/*");
        server.setHandler(context);
        server.start();
        return server;
    }
}
