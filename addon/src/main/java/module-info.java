/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
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
