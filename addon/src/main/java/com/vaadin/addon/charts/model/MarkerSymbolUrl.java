package com.vaadin.addon.charts.model;

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
