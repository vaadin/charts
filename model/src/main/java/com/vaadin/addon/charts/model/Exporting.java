package com.vaadin.addon.charts.model;
public class Exporting extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Buttons buttons;
	private Object chartOptions;
	private Boolean enabled;
	private String filename;
	private Object formAttributes;
	private Number scale;
	private Number sourceHeight;
	private Number sourceWidth;
	private String type;
	private String url;
	private Number width;

	public Exporting() {
	}

	public Buttons getButtons() {
		return buttons;
	}

	public void setButtons(Buttons buttons) {
		this.buttons = buttons;
	}

	public Object getChartOptions() {
		return chartOptions;
	}

	public void setChartOptions(Object chartOptions) {
		this.chartOptions = chartOptions;
	}

	public Exporting(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Object getFormAttributes() {
		return formAttributes;
	}

	public void setFormAttributes(Object formAttributes) {
		this.formAttributes = formAttributes;
	}

	public Number getScale() {
		return scale;
	}

	public void setScale(Number scale) {
		this.scale = scale;
	}

	public Number getSourceHeight() {
		return sourceHeight;
	}

	public void setSourceHeight(Number sourceHeight) {
		this.sourceHeight = sourceHeight;
	}

	public Number getSourceWidth() {
		return sourceWidth;
	}

	public void setSourceWidth(Number sourceWidth) {
		this.sourceWidth = sourceWidth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}
}