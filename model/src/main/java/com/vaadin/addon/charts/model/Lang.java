package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.AbstractConfigurationObject;
public class Lang extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private String contextButtonTitle;
	private String decimalPoint;
	private String downloadJPEG;
	private String downloadPDF;
	private String downloadPNG;
	private String downloadSVG;
	private String drillUpText;
	private Loading loading;
	private String[] months;
	private NoData noData;
	private String[] numericSymbols;
	private String printChart;
	private String resetZoom;
	private String resetZoomTitle;
	private String[] shortMonths;
	private String thousandsSep;
	private String[] weekdays;

	public Lang() {
	}

	public String getContextButtonTitle() {
		return contextButtonTitle;
	}

	public void setContextButtonTitle(String contextButtonTitle) {
		this.contextButtonTitle = contextButtonTitle;
	}

	public String getDecimalPoint() {
		return decimalPoint;
	}

	public void setDecimalPoint(String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	public String getDownloadJPEG() {
		return downloadJPEG;
	}

	public void setDownloadJPEG(String downloadJPEG) {
		this.downloadJPEG = downloadJPEG;
	}

	public String getDownloadPDF() {
		return downloadPDF;
	}

	public void setDownloadPDF(String downloadPDF) {
		this.downloadPDF = downloadPDF;
	}

	public String getDownloadPNG() {
		return downloadPNG;
	}

	public void setDownloadPNG(String downloadPNG) {
		this.downloadPNG = downloadPNG;
	}

	public String getDownloadSVG() {
		return downloadSVG;
	}

	public void setDownloadSVG(String downloadSVG) {
		this.downloadSVG = downloadSVG;
	}

	public String getDrillUpText() {
		return drillUpText;
	}

	public void setDrillUpText(String drillUpText) {
		this.drillUpText = drillUpText;
	}

	public Loading getLoading() {
		return loading;
	}

	public void setLoading(Loading loading) {
		this.loading = loading;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public NoData getNoData() {
		return noData;
	}

	public void setNoData(NoData noData) {
		this.noData = noData;
	}

	public String[] getNumericSymbols() {
		return numericSymbols;
	}

	public void setNumericSymbols(String[] numericSymbols) {
		this.numericSymbols = numericSymbols;
	}

	public String getPrintChart() {
		return printChart;
	}

	public void setPrintChart(String printChart) {
		this.printChart = printChart;
	}

	public String getResetZoom() {
		return resetZoom;
	}

	public void setResetZoom(String resetZoom) {
		this.resetZoom = resetZoom;
	}

	public String getResetZoomTitle() {
		return resetZoomTitle;
	}

	public void setResetZoomTitle(String resetZoomTitle) {
		this.resetZoomTitle = resetZoomTitle;
	}

	public String[] getShortMonths() {
		return shortMonths;
	}

	public void setShortMonths(String[] shortMonths) {
		this.shortMonths = shortMonths;
	}

	public String getThousandsSep() {
		return thousandsSep;
	}

	public void setThousandsSep(String thousandsSep) {
		this.thousandsSep = thousandsSep;
	}

	public String[] getWeekdays() {
		return weekdays;
	}

	public void setWeekdays(String[] weekdays) {
		this.weekdays = weekdays;
	}
}