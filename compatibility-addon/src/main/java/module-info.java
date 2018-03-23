module com.vaadin.v7.addon.charts {
    requires com.vaadin.addon.charts;
    requires jackson.core;
    requires jackson.databind;
    requires vaadin.compatibility.server;

    exports com.vaadin.v7.addon.charts.model;
    exports com.vaadin.v7.addon.charts.model.serializers;
}