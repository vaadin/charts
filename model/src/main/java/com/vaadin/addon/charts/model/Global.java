package com.vaadin.addon.charts.model;
public class Global extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object Date;
	private String VMLRadialGradientURL;
	private String canvasToolsURL;
	private Object getTimezoneOffset;
	private Number timezoneOffset;
	private Boolean useUTC;

	public Global() {
	}

	public Object getDate() {
		return Date;
	}

	public void setDate(Object Date) {
		this.Date = Date;
	}

	public String getVMLRadialGradientURL() {
		return VMLRadialGradientURL;
	}

	public void setVMLRadialGradientURL(String VMLRadialGradientURL) {
		this.VMLRadialGradientURL = VMLRadialGradientURL;
	}

	public String getCanvasToolsURL() {
		return canvasToolsURL;
	}

	public void setCanvasToolsURL(String canvasToolsURL) {
		this.canvasToolsURL = canvasToolsURL;
	}

	public Object getGetTimezoneOffset() {
		return getTimezoneOffset;
	}

	public void setGetTimezoneOffset(Object getTimezoneOffset) {
		this.getTimezoneOffset = getTimezoneOffset;
	}

	public Number getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(Number timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public Boolean getUseUTC() {
		return useUTC;
	}

	public void setUseUTC(Boolean useUTC) {
		this.useUTC = useUTC;
	}
}