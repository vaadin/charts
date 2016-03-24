package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2016 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */
/**
 * Options for the export related buttons, print and export. In addition to the
 * default buttons listed here, custom buttons can be added. See <a
 * href="#navigation.buttonOptions">navigation.buttonOptions</a> for general
 * options.
 */
public class Buttons extends AbstractConfigurationObject {

	private ContextButton contextButton;

	public Buttons() {
	}

	/**
	 * @see #setContextButton(ContextButton)
	 */
	public ContextButton getContextButton() {
		if (contextButton == null) {
			contextButton = new ContextButton();
		}
		return contextButton;
	}

	/**
	 * Options for the export button.
	 */
	public void setContextButton(ContextButton contextButton) {
		this.contextButton = contextButton;
	}
}