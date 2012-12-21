package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * Symbol that is fetched from the url, implementing ChartEnum to provide
 * correct serialization
 */
public class MarkerSymbolUrl extends AbstractConfigurationObject implements MarkerSymbol, ChartEnum {

    private String url;

    /**
     * Construct MarkerSymbol from given url
     * 
     * @param url
     */
    public MarkerSymbolUrl(String url) {
        this.setUrl(url);
    }

    /**
     * Set the url of the marker symbol
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @see #setUrl(String)
     * @return
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }

}
