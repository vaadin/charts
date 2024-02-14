/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

public class States extends AbstractConfigurationObject {

	private Hover hover;
	private Select select;

	public States() {
	}

	/**
	 * @see #setHover(Hover)
	 */
	public Hover getHover() {
		if (hover == null) {
			hover = new Hover();
		}
		return hover;
	}

	public void setHover(Hover hover) {
		this.hover = hover;
	}

	/**
	 * @see #setSelect(Select)
	 */
	public Select getSelect() {
		if (select == null) {
			select = new Select();
		}
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
