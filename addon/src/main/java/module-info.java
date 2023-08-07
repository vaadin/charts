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
module com.vaadin.addon.charts {
    requires java.annotation;
    requires java.desktop;
    requires java.logging;
    requires gentyref;
    requires gwt.user;
    requires jackson.core;
    requires jackson.databind;
    requires jackson.annotations;
    requires jsoup;
    requires vaadin.client;
    requires vaadin.server;
    requires vaadin.shared;

    exports com.vaadin.addon.charts;
    exports com.vaadin.addon.charts.client;
    exports com.vaadin.addon.charts.client.ui;
    exports com.vaadin.addon.charts.declarative;
    exports com.vaadin.addon.charts.events;
    exports com.vaadin.addon.charts.model;
    exports com.vaadin.addon.charts.model.serializers;
    exports com.vaadin.addon.charts.model.style;
    exports com.vaadin.addon.charts.shared;
    exports com.vaadin.addon.charts.themes;
    exports com.vaadin.addon.charts.util;
}
