package com.vaadin.addon.charts.model;

/**
 * Hovered series state
 */
public class HoverState extends AbstractConfigurationObject {
    private Boolean enabled;
    private Number radius;
    private Number lineWidth;
    private MarkerSymbol symbol;

    /**
     * Default constructor
     */
    public HoverState() {

    }

    /**
     * Constructor with given enabled parameter
     * 
     * @param enabled
     */
    public HoverState(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setEnabled(Boolean)
     * @return
     */
    public boolean isEnabled() {
    	return enabled == null ? true : enabled;
    }

    /**
     * Enable separate styles for the hovered series to visualize that the user
     * hovers either the series itself or the legend. Defaults to true.
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * The radius of the point marker. Defaults to 0.
     * 
     * @param radius
     */
    public void setRadius(Number radius) {
        this.radius = radius;
    }

    /**
     * @see #setRadius(Number)
     * @return
     */
    public Number getRadius() {
        return radius;
    }

    /**
     * The width of the point marker's outline. Defaults to 0.
     * 
     * @param lineWidth
     */
    public void setLineWidth(Number lineWidth) {
        this.lineWidth = lineWidth;
    }

    /**
     * @see #setLineWidth(Number)
     * @return
     */
    public Number getLineWidth() {
        return lineWidth;
    }

    /**
     * A predefined shape or symbol for the marker. When null, the symbol is
     * pulled from options.symbols. Other possible values are "circle",
     * "square", "diamond", "triangle" and "triangle-down". Additionally, the
     * URL to a graphic can be given on this form: "url(graphic.png)". Defaults
     * to null.
     * 
     * @param symbol
     */
    public void setSymbol(MarkerSymbol symbol) {
        this.symbol = symbol;
    }

    /**
     * @see #setSymbol(MarkerSymbol)
     * @return
     */
    public MarkerSymbol getSymbol() {
        return symbol;
    }
}
