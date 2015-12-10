package com.vaadin.addon.charts.model;
/**
 * A wrapper object for all the series options in specific states.
 */
public class States extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Hover hover;
	private Select select;

	public States() {
	}

	/**
	 * @see #setHover(Hover)
	 */
	public Hover getHover() {
		return hover;
	}

	/**
	 * Options for the hovered series
	 */
	public void setHover(Hover hover) {
		this.hover = hover;
	}

	/**
	 * @see #setSelect(Select)
	 */
	public Select getSelect() {
		return select;
	}

	/**
	 * The appearance of the point marker when selected. In order to allow a
	 * point to be selected, set the <code>series.allowPointSelect</code> option
	 * to true.
	 */
	public void setSelect(Select select) {
		this.select = select;
	}
}