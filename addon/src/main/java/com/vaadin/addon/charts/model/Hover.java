package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2018 Vaadin Ltd
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

import javax.annotation.Generated;
import com.vaadin.addon.charts.model.style.Color;
/**
 * Options for the hovered series
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/charts/issues/new")
public class Hover extends AbstractConfigurationObject {

	private Boolean enabled;
	private Halo halo;
	private Number lineWidth;
	private Number lineWidthPlus;
	private Color fillColor;
	private Color lineColor;
	private Number radius;
	private Number radiusPlus;
	private Marker marker;

	public Hover() {
	}

	public Hover(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setEnabled(Boolean)
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Enable separate styles for the hovered series to visualize that the user
	 * hovers either the series itself or the legend. .
	 * <p>
	 * Defaults to: true
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @see #setHalo(Halo)
	 */
	public Halo getHalo() {
		if (halo == null) {
			halo = new Halo();
		}
		return halo;
	}

	/**
	 * Options for the halo appearing around the hovered point in line-type
	 * series as well as outside the hovered slice in pie charts. By default the
	 * halo is filled by the current point or series color with an opacity of
	 * 0.25. The halo can be disabled by setting the <code>halo</code> option to
	 * <code>false</code>.
	 */
	public void setHalo(Halo halo) {
		this.halo = halo;
	}

	/**
	 * @see #setLineWidth(Number)
	 */
	public Number getLineWidth() {
		return lineWidth;
	}

	/**
	 * Pixel with of the graph line.
	 * <p>
	 * Defaults to: 2
	 */
	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * @see #setLineWidthPlus(Number)
	 */
	public Number getLineWidthPlus() {
		return lineWidthPlus;
	}

	/**
	 * The additional line width for the graph of a hovered series.
	 * <p>
	 * Defaults to: 1
	 */
	public void setLineWidthPlus(Number lineWidthPlus) {
		this.lineWidthPlus = lineWidthPlus;
	}

	/**
	 * @see #setFillColor(Color)
	 */
	public Color getFillColor() {
		return fillColor;
	}

	/**
	 * The fill color of the marker in hover state.
	 */
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * @see #setLineColor(Color)
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * The color of the point marker's outline. When <code>null</code>, the
	 * series' or point's color is used.
	 * <p>
	 * Defaults to: #FFFFFF
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * @see #setRadius(Number)
	 */
	public Number getRadius() {
		return radius;
	}

	/**
	 * The radius of the point marker. In hover state, it defaults to the normal
	 * state's radius + 2 as per the <a
	 * href="#plotOptions.series.marker.states.hover.radiusPlus">radiusPlus</a>
	 * option.
	 */
	public void setRadius(Number radius) {
		this.radius = radius;
	}

	/**
	 * @see #setRadiusPlus(Number)
	 */
	public Number getRadiusPlus() {
		return radiusPlus;
	}

	/**
	 * The number of pixels to increase the radius of the hovered point.
	 * <p>
	 * Defaults to: 2
	 */
	public void setRadiusPlus(Number radiusPlus) {
		this.radiusPlus = radiusPlus;
	}

	/**
	 * @see #setMarker(Marker)
	 */
	public Marker getMarker() {
		if (marker == null) {
			marker = new Marker();
		}
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}
}