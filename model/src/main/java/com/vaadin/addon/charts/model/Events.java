package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Events extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object click;
	private Object mouseOut;
	private Object mouseOver;
	private Object remove;
	private Select select;
	private Object unselect;
	private Object update;

	public Events() {
	}

	public Object getClick() {
		return click;
	}

	public void setClick(Object click) {
		this.click = click;
	}

	public Object getMouseOut() {
		return mouseOut;
	}

	public void setMouseOut(Object mouseOut) {
		this.mouseOut = mouseOut;
	}

	public Object getMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(Object mouseOver) {
		this.mouseOver = mouseOver;
	}

	public Object getRemove() {
		return remove;
	}

	public void setRemove(Object remove) {
		this.remove = remove;
	}

	public Select getSelect() {
		return select;
	}

	public void setSelect(Select select) {
		this.select = select;
	}

	public Object getUnselect() {
		return unselect;
	}

	public void setUnselect(Object unselect) {
		this.unselect = unselect;
	}

	public Object getUpdate() {
		return update;
	}

	public void setUpdate(Object update) {
		this.update = update;
	}
}