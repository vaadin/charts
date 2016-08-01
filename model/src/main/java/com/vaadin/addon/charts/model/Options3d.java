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
 * Options to render charts in 3 dimensions. This feature requires
 * <code>highcharts-3d.js</code>, found in the download package or online at <a
 * href="http://code.highcharts.com/highcharts-3d.js">code.highcharts.com/
 * highcharts-3d.js</a>.
 */
public class Options3d extends AbstractConfigurationObject {

	private Number alpha;
	private Number beta;
	private Number depth;
	private Boolean enabled;
	private Boolean fitToPlot;
	private Frame frame;
	private Number viewDistance;

	public Options3d() {
	}

	/**
	 * @see #setAlpha(Number)
	 */
	public Number getAlpha() {
		return alpha;
	}

	/**
	 * One of the two rotation angles for the chart.
	 * <p>
	 * Defaults to: 0
	 */
	public void setAlpha(Number alpha) {
		this.alpha = alpha;
	}

	/**
	 * @see #setBeta(Number)
	 */
	public Number getBeta() {
		return beta;
	}

	/**
	 * One of the two rotation angles for the chart.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBeta(Number beta) {
		this.beta = beta;
	}

	/**
	 * @see #setDepth(Number)
	 */
	public Number getDepth() {
		return depth;
	}

	/**
	 * The total depth of the chart.
	 * <p>
	 * Defaults to: 100
	 */
	public void setDepth(Number depth) {
		this.depth = depth;
	}

	public Options3d(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Wether to render the chart using the 3D functionality.
	 * <p>
	 * Defaults to: false
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setFitToPlot(Boolean)
	 */
	public Boolean getFitToPlot() {
		return fitToPlot;
	}

	/**
	 * Whether the 3d box should automatically adjust to the chart plot area.
	 * <p>
	 * Defaults to: true
	 */
	public void setFitToPlot(Boolean fitToPlot) {
		this.fitToPlot = fitToPlot;
	}

	/**
	 * @see #setFrame(Frame)
	 */
	public Frame getFrame() {
		if (frame == null) {
			frame = new Frame();
		}
		return frame;
	}

	/**
	 * Provides the option to draw a frame around the charts by defining a
	 * bottom, front and back panel.
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	/**
	 * @see #setViewDistance(Number)
	 */
	public Number getViewDistance() {
		return viewDistance;
	}

	/**
	 * Defines the distance the viewer is standing in front of the chart, this
	 * setting is important to calculate the perspective effect in column and
	 * scatter charts. It is not used for 3D pie charts.
	 * <p>
	 * Defaults to: 100
	 */
	public void setViewDistance(Number viewDistance) {
		this.viewDistance = viewDistance;
	}
}