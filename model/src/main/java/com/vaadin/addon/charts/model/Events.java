package com.vaadin.addon.charts.model;
public class Events extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object click;
	private Object mouseOut;
	private Object mouseOver;
	private Object remove;
	private Select select;
	private Object unselect;
	private Object update;
	private Object afterAnimate;
	private Object checkboxClick;
	private Object hide;
	private Object legendItemClick;
	private Object show;
	private Object addSeries;
	private Object afterPrint;
	private Object beforePrint;
	private Object drilldown;
	private Object drillup;
	private Object load;
	private Object redraw;
	private Object selection;

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

	public Object getAfterAnimate() {
		return afterAnimate;
	}

	public void setAfterAnimate(Object afterAnimate) {
		this.afterAnimate = afterAnimate;
	}

	public Object getCheckboxClick() {
		return checkboxClick;
	}

	public void setCheckboxClick(Object checkboxClick) {
		this.checkboxClick = checkboxClick;
	}

	public Object getHide() {
		return hide;
	}

	public void setHide(Object hide) {
		this.hide = hide;
	}

	public Object getLegendItemClick() {
		return legendItemClick;
	}

	public void setLegendItemClick(Object legendItemClick) {
		this.legendItemClick = legendItemClick;
	}

	public Object getShow() {
		return show;
	}

	public void setShow(Object show) {
		this.show = show;
	}

	public Object getAddSeries() {
		return addSeries;
	}

	public void setAddSeries(Object addSeries) {
		this.addSeries = addSeries;
	}

	public Object getAfterPrint() {
		return afterPrint;
	}

	public void setAfterPrint(Object afterPrint) {
		this.afterPrint = afterPrint;
	}

	public Object getBeforePrint() {
		return beforePrint;
	}

	public void setBeforePrint(Object beforePrint) {
		this.beforePrint = beforePrint;
	}

	public Object getDrilldown() {
		return drilldown;
	}

	public void setDrilldown(Object drilldown) {
		this.drilldown = drilldown;
	}

	public Object getDrillup() {
		return drillup;
	}

	public void setDrillup(Object drillup) {
		this.drillup = drillup;
	}

	public Object getLoad() {
		return load;
	}

	public void setLoad(Object load) {
		this.load = load;
	}

	public Object getRedraw() {
		return redraw;
	}

	public void setRedraw(Object redraw) {
		this.redraw = redraw;
	}

	public Object getSelection() {
		return selection;
	}

	public void setSelection(Object selection) {
		this.selection = selection;
	}
}