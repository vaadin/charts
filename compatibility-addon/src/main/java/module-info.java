/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
module com.vaadin.v7.addon.charts {
    requires com.vaadin.addon.charts;
    requires jackson.core;
    requires jackson.databind;
    requires vaadin.compatibility.server;

    exports com.vaadin.v7.addon.charts.model;
    exports com.vaadin.v7.addon.charts.model.serializers;
}