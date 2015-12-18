package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
/**
 * An array of objects representing plot lines on the X axis
 */
public class PlotLine extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color color;
	private DashStyle dashStyle;
	private String id;
	private Label label;
	private Number value;
	private Number width;
	private Number zIndex;

	public PlotLine() {
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The color of the line.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setDashStyle(DashStyle)
	 */
	public DashStyle getDashStyle() {
		return dashStyle;
	}

	/**
	 * The dashing or dot style for the plot line. For possible values see <a
	 * href=
	 * "http://jsfiddle.net/gh/get/jquery/1.7.1/highslide-software/highcharts.com/tree/master/samples/highcharts/plotoptions/series-dashstyle-all/"
	 * >this overview</a>.
	 * <p>
	 * Defaults to: Solid
	 */
	public void setDashStyle(DashStyle dashStyle) {
		this.dashStyle = dashStyle;
	}

	/**
	 * @see #setId(String)
	 */
	public String getId() {
		return id;
	}

	/**
	 * An id used for identifying the plot line in Axis.removePlotLine.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see #setLabel(Label)
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Text labels for the plot bands
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	/**
	 * @see #setValue(Number)
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * The position of the line in axis units.
	 */
	public void setValue(Number value) {
		this.value = value;
	}

	/**
	 * @see #setWidth(Number)
	 */
	public Number getWidth() {
		return width;
	}

	/**
	 * The width or thickness of the plot line.
	 */
	public void setWidth(Number width) {
		this.width = width;
	}

	/**
	 * @see #setZIndex(Number)
	 */
	public Number getZIndex() {
		return zIndex;
	}

	/**
	 * The z index of the plot line within the chart.
	 */
	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}

	public PlotLine(Number value, Number width, Color color) {
		this.value = value;
		this.width = width;
		this.color = color;
	}
}