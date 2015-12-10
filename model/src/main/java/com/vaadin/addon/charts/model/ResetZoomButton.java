package com.vaadin.addon.charts.model;
/**
 * The button that appears after a selection zoom, allowing the user to reset
 * zoom.
 */
public class ResetZoomButton extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Position position;
	private String relativeTo;
	private Object theme;

	public ResetZoomButton() {
	}

	/**
	 * @see #setPosition(Position)
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * The position of the button. This is an object that can hold the
	 * properties <code>align</code>, <code>verticalAlign</code>, <code>x</code>
	 * and <code>y</code>.
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
	 * What frame the button should be placed related to. Can be either "plot"
	 * or "chart".
	 * <p>
	 * Defaults to: plot
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
	 */
	public void setTheme(Object theme) {
		this.theme = theme;
	}
}