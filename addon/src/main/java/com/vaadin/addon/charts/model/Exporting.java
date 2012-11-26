package com.vaadin.addon.charts.model;

import javax.activation.MimeType;

/**
 * Vaadin Chart's exporting module
 */
public class Exporting extends AbstractConfigurationObject {

    private Boolean enableImages;
    private Boolean enabled = false;
    private String filename;
    private MimeType type;
    private String url;
    private Number width;

    /**
     * Construct Exporting with given state
     * 
     * @param enabled
     */
    public Exporting(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setEnableImages(Boolean)
     * @return
     */
    public Boolean isEnableImages() {
        return enableImages;
    }

    /**
     * Whether to enable images in the export. Including image point markers,
     * background images etc. Defaults to false.
     * 
     * @param enableImages
     */
    public void setEnableImages(Boolean enableImages) {
        this.enableImages = enableImages;
    }

    /**
     * @see #setEnabled(Boolean)
     * @return
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Whether to enable the exporting module. Defaults to false.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setFilename(String)
     * @return
     */
    public String getFilename() {
        return filename;
    }

    /**
     * The filename, without extension, to use for the exported chart. Defaults
     * to "chart".
     * 
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @see #setType(MimeType)
     * @return
     */
    public MimeType getType() {
        return type;
    }

    /**
     * Default MIME type for exporting if chart.exportChart() is called without
     * specifying a type option. Possible values are image/png, image/jpeg,
     * application/pdf and image/svg+xml. Defaults to "image/png".
     * 
     * @param type
     */
    public void setType(MimeType type) {
        this.type = type;
    }

    /**
     * @see #setUrl(String)
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * The URL for the server module converting the SVG string to an image
     * format. By default this points to Highslide Software's free web service.
     * Defaults to http://export.highcharts.com.
     * 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @see #setWidth(Number)
     * @return
     */
    public Number getWidth() {
        return width;
    }

    /**
     * The pixel width of charts exported to PNG or JPG. Defaults to 800.
     * 
     * @param width
     */
    public void setWidth(Number width) {
        this.width = width;
    }

}
