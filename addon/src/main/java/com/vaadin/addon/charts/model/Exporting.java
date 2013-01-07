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

import javax.activation.MimeType;

/**
 * Module for exporting Vaadin Charts
 */
public class Exporting extends AbstractConfigurationObject {

    private Boolean enableImages;
    private Boolean enabled = false;
    private String filename;
    private MimeType type;
    private String url;
    private Number width;

    /**
     * Constructs an Exporting instance that is either enabled or disabled.
     * 
     * @param enabled
     *            true to enable, false to disable.
     */
    public Exporting(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setEnableImages(Boolean)
     */
    public Boolean isEnableImages() {
        return enableImages;
    }

    /**
     * Sets whether to enable images in the export. Including image point
     * markers, background images etc. Defaults to false.
     * 
     * @param enableImages
     */
    public void setEnableImages(Boolean enableImages) {
        this.enableImages = enableImages;
    }

    /**
     * @see #setEnabled(Boolean)
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether to enable the exporting module. Defaults to false.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setFilename(String)
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename, without extension, to use for the exported chart.
     * Defaults to "chart".
     * 
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @see #setType(MimeType)
     */
    public MimeType getType() {
        return type;
    }

    /**
     * Sets the default MIME type for exporting if chart.exportChart() is called
     * without specifying a type option. Possible values are "image/png",
     * "image/jpeg", "application/pdf" and "image/svg+xml". Defaults to
     * "image/png".
     * 
     * @param type
     */
    public void setType(MimeType type) {
        this.type = type;
    }

    /**
     * @see #setUrl(String)
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the server module converting the SVG string to an image
     * format. Defaults to the free http://export.highcharts.com web service.
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @see #setWidth(Number)
     */
    public Number getWidth() {
        return width;
    }

    /**
     * Sets the pixel width of charts exported to PNG or JPG. Defaults to 800.
     * 
     * @param width
     */
    public void setWidth(Number width) {
        this.width = width;
    }

}
