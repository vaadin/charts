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
import com.vaadin.addon.charts.util.SizeWithUnit;
/**
 * An array of objects defining plot bands on the Y axis.
 */
public class PlotBand extends AbstractConfigurationObject {

	private Color borderColor;
	private Number borderWidth;
	private Color color;
	private Number from;
	private String id;
	private String innerRadius;
	private Label label;
	private String outerRadius;
	private String thickness;
	private Number to;
	private Number zIndex;

	public PlotBand() {
	}

	/**
	 * @see #setBorderColor(Color)
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Border color for the plot band. Also requires <code>borderWidth</code> to
	 * be set.
	 * <p>
	 * Defaults to: null
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @see #setBorderWidth(Number)
	 */
	public Number getBorderWidth() {
		return borderWidth;
	}

	/**
	 * Border width for the plot band. Also requires <code>borderColor</code> to
	 * be set.
	 * <p>
	 * Defaults to: 0
	 */
	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * The color of the plot band.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setFrom(Number)
	 */
	public Number getFrom() {
		return from;
	}

	/**
	 * The start position of the plot band in axis units.
	 */
	public void setFrom(Number from) {
		this.from = from;
	}

	/**
	 * @see #setId(String)
	 */
	public String getId() {
		return id;
	}

	/**
	 * An id used for identifying the plot band in Axis.removePlotBand.
	 */
	public void setId(String id) {
		this.id = id;
	}

	public float getInnerRadius() {
		String tmp = innerRadius;
		if (innerRadius == null) {
			return -1.0f;
		}
		if (this.innerRadius.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getInnerRadiusUnit() {
		if (this.innerRadius == null) {
			return Unit.PIXELS;
		}
		if (this.innerRadius.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setInnerRadius(String innerRadius) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(innerRadius);
		if (tmp != null) {
			setInnerRadius(tmp.getSize(), tmp.getUnit());
		} else {
			setInnerRadius(-1, Unit.PIXELS);
		}
	}

	public void setInnerRadius(float innerRadius, Unit unit) {
		String value = Float.toString(innerRadius);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (innerRadius == -1) {
			value = null;
		}
		this.innerRadius = value;
	}

	/**
	 * @see #setLabel(Label)
	 */
	public Label getLabel() {
		if (label == null) {
			label = new Label();
		}
		return label;
	}

	/**
	 * Text labels for the plot bands
	 */
	public void setLabel(Label label) {
		this.label = label;
	}

	public float getOuterRadius() {
		String tmp = outerRadius;
		if (outerRadius == null) {
			return -1.0f;
		}
		if (this.outerRadius.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getOuterRadiusUnit() {
		if (this.outerRadius == null) {
			return Unit.PIXELS;
		}
		if (this.outerRadius.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setOuterRadius(String outerRadius) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(outerRadius);
		if (tmp != null) {
			setOuterRadius(tmp.getSize(), tmp.getUnit());
		} else {
			setOuterRadius(-1, Unit.PIXELS);
		}
	}

	public void setOuterRadius(float outerRadius, Unit unit) {
		String value = Float.toString(outerRadius);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (outerRadius == -1) {
			value = null;
		}
		this.outerRadius = value;
	}

	public float getThickness() {
		String tmp = thickness;
		if (thickness == null) {
			return -1.0f;
		}
		if (this.thickness.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getThicknessUnit() {
		if (this.thickness == null) {
			return Unit.PIXELS;
		}
		if (this.thickness.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setThickness(String thickness) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(thickness);
		if (tmp != null) {
			setThickness(tmp.getSize(), tmp.getUnit());
		} else {
			setThickness(-1, Unit.PIXELS);
		}
	}

	public void setThickness(float thickness, Unit unit) {
		String value = Float.toString(thickness);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (thickness == -1) {
			value = null;
		}
		this.thickness = value;
	}

	/**
	 * @see #setTo(Number)
	 */
	public Number getTo() {
		return to;
	}

	/**
	 * The end position of the plot band in axis units.
	 */
	public void setTo(Number to) {
		this.to = to;
	}

	/**
	 * @see #setZIndex(Number)
	 */
	public Number getZIndex() {
		return zIndex;
	}

	/**
	 * The z index of the plot band within the chart, relative to other
	 * elements. Using the same z index as another element may give
	 * unpredictable results, as the last rendered element will be on top.
	 * Values from 0 to 20 make sense.
	 */
	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}

	public PlotBand(Number from, Number to, Color color) {
		this.from = from;
		this.to = to;
		this.color = color;
	}
}