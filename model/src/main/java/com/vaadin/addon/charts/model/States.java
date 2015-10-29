package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class States extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Hover hover;
	private Select select;

	public States() {
	}

	public Hover getHover() {
		return hover;
	}

	public void setHover(Hover hover) {
		this.hover = hover;
	}

	public Select getSelect() {
		return select;
	}

	public void setSelect(Select select) {
		this.select = select;
	}
}