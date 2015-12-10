package com.vaadin.addon.charts.model;
/**
 * Options for the export related buttons, print and export. In addition to the
 * default buttons listed here, custom buttons can be added. See <a
 * href="#navigation.buttonOptions">navigation.buttonOptions</a> for general
 * options.
 */
public class Buttons extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private ContextButton contextButton;

	public Buttons() {
	}

	/**
	 * @see #setContextButton(ContextButton)
	 */
	public ContextButton getContextButton() {
		return contextButton;
	}

	/**
	 * Options for the export button.
	 */
	public void setContextButton(ContextButton contextButton) {
		this.contextButton = contextButton;
	}
}