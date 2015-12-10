package com.vaadin.addon.charts.model;
/**
 * Options for the drill up button that appears when drilling down on a series.
 * The text for the button is defined in <a
 * href="#lang.drillUpText">lang.drillUpText</a>.
 */
public class DrillUpButton extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Position position;
	private String relativeTo;
	private Object theme;

	public DrillUpButton() {
	}

	/**
	 * @see #setPosition(Position)
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Positioning options for the button within the <code>relativeTo</code>
	 * box. Available properties are <code>x</code>, <code>y</code>,
	 * <code>align</code> and <code>verticalAlign</code>.
	 * <p>
	 * Defaults to:
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * @see #setRelativeTo(String)
	 */
	public String getRelativeTo() {
		return relativeTo;
	}

	/**
	 * What box to align the button to. Can be either "plotBox" or "spacingBox".
	 * <p>
	 * Defaults to: plotBox
	 */
	public void setRelativeTo(String relativeTo) {
		this.relativeTo = relativeTo;
	}

	/**
	 * @see #setTheme(Object)
	 */
	public Object getTheme() {
		return theme;
	}

	/**
	 * A collection of attributes for the button. The object takes SVG
	 * attributes like <code>fill</code>, <code>stroke</code>,
	 * <code>stroke-width</code> or <code>r</code>, the border radius. The theme
	 * also supports <code>style</code>, a collection of CSS properties for the
	 * text. Equivalent attributes for the hover state are given in
	 * <code>theme.states.hover</code>.
	 * <p>
	 * Defaults to:
	 */
	public void setTheme(Object theme) {
		this.theme = theme;
	}
}