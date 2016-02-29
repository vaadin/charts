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

import com.vaadin.addon.charts.model.style.Color;
/**
 * The navigator is a small series below the main series, displaying a view of
 * the entire data set. It provides tools to zoom in and out on parts of the
 * data as well as panning across the dataset.
 */
public class Navigator extends AbstractConfigurationObject {

	private Boolean adaptToUpdatedData;
	private Boolean enabled;
	private Handles handles;
	private Number height;
	private Number margin;
	private Color maskFill;
	private Boolean maskInside;
	private Color outlineColor;
	private Number outlineWidth;
	private PlotOptionsSeries series;
	private XAxis[] xAxis;
	private YAxis[] yAxis;

	public Navigator() {
	}

	/**
	 * @see #setAdaptToUpdatedData(Boolean)
	 */
	public Boolean getAdaptToUpdatedData() {
		return adaptToUpdatedData;
	}

	/**
	 * Whether the navigator and scrollbar should adapt to updated data in the
	 * base X axis. This should be false when loading data asynchronously, to
	 * prevent circular loading.
	 * <p>
	 * Defaults to: true
	 */
	public void setAdaptToUpdatedData(Boolean adaptToUpdatedData) {
		this.adaptToUpdatedData = adaptToUpdatedData;
	}

	public Navigator(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enable or disable the navigator.
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setHandles(Handles)
	 */
	public Handles getHandles() {
		if (handles == null) {
			handles = new Handles();
		}
		return handles;
	}

	/**
	 * Options for the handles for dragging the zoomed area.
	 */
	public void setHandles(Handles handles) {
		this.handles = handles;
	}

	/**
	 * @see #setHeight(Number)
	 */
	public Number getHeight() {
		return height;
	}

	/**
	 * The height of the navigator.
	 * <p>
	 * Defaults to: 40
	 */
	public void setHeight(Number height) {
		this.height = height;
	}

	/**
	 * @see #setMargin(Number)
	 */
	public Number getMargin() {
		return margin;
	}

	/**
	 * The distance from the nearest element, the X axis or X axis labels.
	 * <p>
	 * Defaults to: 25
	 */
	public void setMargin(Number margin) {
		this.margin = margin;
	}

	/**
	 * @see #setMaskFill(Color)
	 */
	public Color getMaskFill() {
		return maskFill;
	}

	/**
	 * The color of the mask covering the areas of the navigator series that are
	 * currently not visible in the main series. The default color is bluish
	 * with an opacity of 0.3 to see the series below.
	 * <p>
	 * Defaults to: rgba(128,179,236,0.3)
	 */
	public void setMaskFill(Color maskFill) {
		this.maskFill = maskFill;
	}

	/**
	 * @see #setMaskInside(Boolean)
	 */
	public Boolean getMaskInside() {
		return maskInside;
	}

	/**
	 * Whether the mask should be inside the range marking the zoomed range, or
	 * outside. In Highstock 1.x it was always <code>false</code>.
	 * <p>
	 * Defaults to: true
	 */
	public void setMaskInside(Boolean maskInside) {
		this.maskInside = maskInside;
	}

	/**
	 * @see #setOutlineColor(Color)
	 */
	public Color getOutlineColor() {
		return outlineColor;
	}

	/**
	 * The color of the line marking the currently zoomed area in the navigator.
	 * <p>
	 * Defaults to: #b2b1b6
	 */
	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	/**
	 * @see #setOutlineWidth(Number)
	 */
	public Number getOutlineWidth() {
		return outlineWidth;
	}

	/**
	 * The width of the line marking the currently zoomed area in the navigator.
	 * <p>
	 * Defaults to: 2
	 */
	public void setOutlineWidth(Number outlineWidth) {
		this.outlineWidth = outlineWidth;
	}

	/**
	 * @see #setSeries(PlotOptionsSeries)
	 */
	public PlotOptionsSeries getSeries() {
		if (series == null) {
			series = new PlotOptionsSeries();
		}
		return series;
	}

	/**
	 * <p>
	 * Options for the navigator series. Available options are the same as any
	 * series, documented at <a class="internal"
	 * href="#plotOptions.series">plotOptions</a> and <a class="internal"
	 * href="#series">series</a>.
	 * </p>
	 * 
	 * <p>
	 * Unless data is explicitly defined on navigator.series, the data is
	 * borrowed from the first series in the chart.
	 * </p>
	 * 
	 * <p>
	 * Default series options for the navigator series are:
	 * </p>
	 * 
	 * <pre>
	 * series: {
	 * 		type: 'areaspline',
	 * 		color: '#4572A7',
	 * 		fillOpacity: 0.05,
	 * 		dataGrouping: {
	 * 			smoothed: true
	 * 		},
	 * 		lineWidth: 1,
	 * 		marker: {
	 * 			enabled: false
	 * 		}
	 * 	}
	 * </pre>
	 */
	public void setSeries(PlotOptionsSeries series) {
		this.series = series;
	}

	/**
	 * @see #setXAxis(XAxis[])
	 */
	public XAxis[] getXAxis() {
		return xAxis;
	}

	/**
	 * Options for the navigator X axis. Available options are the same as any X
	 * axis, documented at <a class="internal" href="#xAxis">xAxis</a>. Default
	 * series options for the navigator xAxis are:
	 * 
	 * <pre>
	 * xAxis: {
	 * 	    tickWidth: 0,
	 * 	    lineWidth: 0,
	 * 	    gridLineWidth: 1,
	 * 	    tickPixelInterval: 200,
	 * 	    labels: {
	 * 	        align: 'left',
	 * 	        style: {
	 * 	            color: '#888'
	 * 	        },
	 * 	        x: 3,
	 * 	        y: -4
	 * 	    }
	 * 	}
	 * </pre>
	 */
	public void setXAxis(XAxis[] xAxis) {
		this.xAxis = xAxis;
	}

	/**
	 * @see #setYAxis(YAxis[])
	 */
	public YAxis[] getYAxis() {
		return yAxis;
	}

	/**
	 * Options for the navigator Y axis. Available options are the same as any y
	 * axis, documented at <a class="internal" href="#yAxis">yAxis</a>. Default
	 * series options for the navigator yAxis are:
	 * 
	 * <pre>
	 * yAxis: {
	 * 		gridLineWidth: 0,
	 * 		startOnTick: false,
	 * 		endOnTick: false,
	 * 		minPadding: 0.1,
	 * 		maxPadding: 0.1,
	 * 		labels: {
	 * 			enabled: false
	 * 		},
	 * 		title: {
	 * 			text: null
	 * 		},
	 * 		tickWidth: 0
	 * 	}
	 * </pre>
	 */
	public void setYAxis(YAxis[] yAxis) {
		this.yAxis = yAxis;
	}
}