package com.vaadin.addon.charts.model;
public class Tooltip extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object dateTimeLabelFormats;
	private Boolean followPointer;
	private Boolean followTouchMove;
	private String footerFormat;
	private String headerFormat;
	private Number hideDelay;
	private String pointFormat;
	private Object pointFormatter;
	private String shape;
	private Number valueDecimals;
	private String valuePrefix;
	private String valueSuffix;
	private String xDateFormat;

	public Tooltip() {
	}

	public Object getDateTimeLabelFormats() {
		return dateTimeLabelFormats;
	}

	public void setDateTimeLabelFormats(Object dateTimeLabelFormats) {
		this.dateTimeLabelFormats = dateTimeLabelFormats;
	}

	public Boolean getFollowPointer() {
		return followPointer;
	}

	public void setFollowPointer(Boolean followPointer) {
		this.followPointer = followPointer;
	}

	public Boolean getFollowTouchMove() {
		return followTouchMove;
	}

	public void setFollowTouchMove(Boolean followTouchMove) {
		this.followTouchMove = followTouchMove;
	}

	public String getFooterFormat() {
		return footerFormat;
	}

	public void setFooterFormat(String footerFormat) {
		this.footerFormat = footerFormat;
	}

	public String getHeaderFormat() {
		return headerFormat;
	}

	public void setHeaderFormat(String headerFormat) {
		this.headerFormat = headerFormat;
	}

	public Number getHideDelay() {
		return hideDelay;
	}

	public void setHideDelay(Number hideDelay) {
		this.hideDelay = hideDelay;
	}

	public String getPointFormat() {
		return pointFormat;
	}

	public void setPointFormat(String pointFormat) {
		this.pointFormat = pointFormat;
	}

	public Object getPointFormatter() {
		return pointFormatter;
	}

	public void setPointFormatter(Object pointFormatter) {
		this.pointFormatter = pointFormatter;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public Number getValueDecimals() {
		return valueDecimals;
	}

	public void setValueDecimals(Number valueDecimals) {
		this.valueDecimals = valueDecimals;
	}

	public String getValuePrefix() {
		return valuePrefix;
	}

	public void setValuePrefix(String valuePrefix) {
		this.valuePrefix = valuePrefix;
	}

	public String getValueSuffix() {
		return valueSuffix;
	}

	public void setValueSuffix(String valueSuffix) {
		this.valueSuffix = valueSuffix;
	}

	public String getXDateFormat() {
		return xDateFormat;
	}

	public void setXDateFormat(String xDateFormat) {
		this.xDateFormat = xDateFormat;
	}
}