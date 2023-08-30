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
package com.vaadin.demo.chartplugin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface HighchartPluginResources extends ClientBundle {

    public static final HighchartPluginResources INSTANCE = GWT
            .create(HighchartPluginResources.class);

    @Source("world-map-shapes.js")
    TextResource worldMapShapes();

    @Source("map.src.js")
    TextResource mapSrcJs();


}
