package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
/**
 * Set options on specific levels. Takes precedence over series options, but not
 * point options.
 */
public class Levels extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color borderColor;
	private String borderDashStyle;
	private Number borderWidth;
	private Color color;
	private DataLabels dataLabels;
	private String layoutAlgorithm;
	private String layoutStartingDirection;
	private Number level;

	public Levels() {
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Can set borderColor on all points which lies on the same level.
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @see #setBorderDashStyle(String)
	 */
	public String getBorderDashStyle() {
		return borderDashStyle;
	}

	/**
	 * Set the dash style of the border of all the point which lies on the
	 * level. See <a
	 * href"#plotOptions.scatter.dashStyle">plotOptions.scatter.dashStyle</a>
	 * for possible options.
	 */
	public void setBorderDashStyle(String borderDashStyle) {
		this.borderDashStyle = borderDashStyle;
	}

	/**
	 * @see #setBorderWidth(Number)
	 */
	public Number getBorderWidth() {
		return borderWidth;
	}

	/**
	 * Can set the borderWidth on all points which lies on the same level.
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Can set a color on all points which lies on the same level.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setDataLabels(DataLabels)
	 */
	public DataLabels getDataLabels() {
		return dataLabels;
	}

	/**
	 * Can set the options of dataLabels on each point which lies on the level.
	 * <a
	 * href="#plotOptions.treemap.dataLabels">plotOptions.treemap.dataLabels</a>
	 * for possible values.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	/**
	 * @see #setLayoutAlgorithm(String)
	 */
	public String getLayoutAlgorithm() {
		return layoutAlgorithm;
	}

	/**
	 * Can set the layoutAlgorithm option on a specific level.
	 */
	public void setLayoutAlgorithm(String layoutAlgorithm) {
		this.layoutAlgorithm = layoutAlgorithm;
	}

	/**
	 * @see #setLayoutStartingDirection(String)
	 */
	public String getLayoutStartingDirection() {
		return layoutStartingDirection;
	}

	/**
	 * Can set the layoutStartingDirection option on a specific level.
	 */
	public void setLayoutStartingDirection(String layoutStartingDirection) {
		this.layoutStartingDirection = layoutStartingDirection;
	}

	/**
	 * @see #setLevel(Number)
	 */
	public Number getLevel() {
		return level;
	}

	/**
	 * Decides which level takes effect from the options set in the levels
	 * object.
	 */
	public void setLevel(Number level) {
		this.level = level;
	}
}