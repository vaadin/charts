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
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable.Unit;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vaadin.addon.charts.model.serializers.SizeSerializer;
import java.util.Date;
import java.time.Instant;
import com.vaadin.addon.charts.util.Util;
/**
 * An array of objects defining plot bands on the Y axis.
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/charts/issues/new")
public class PlotBand extends AbstractConfigurationObject {

	private Color borderColor;
	private Number borderWidth;
	private String className;
	private Color color;
	private Number from;
	private String id;
	@JsonSerialize(using = SizeSerializer.class)
	private String innerRadius;
	private Label label;
	@JsonSerialize(using = SizeSerializer.class)
	private String outerRadius;
	@JsonSerialize(using = SizeSerializer.class)
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
	 * @see #setClassName(String)
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * A custom class name, in addition to the default
	 * <code>highcharts-plot-band</code>, to apply to each individual band.
	 */
	public void setClassName(String className) {
		this.className = className;
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

	/**
	 * @see #setInnerRadius(String)
	 */
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

	/**
	 * Sets the innerRadius using String presentation. String presentation is
	 * similar to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param innerRadius
	 *            CSS style string representation
	 */
	public void setInnerRadius(String innerRadius) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(innerRadius);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setInnerRadius(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setInnerRadius(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setInnerRadius(float,Unit)
	 */
	public Unit getInnerRadiusUnit() {
		if (this.innerRadius == null) {
			return Unit.PIXELS;
		}
		if (this.innerRadius.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the innerRadius using Vaadin Unit. Only Unit.PIXELS and
	 * Unit.PERCENTAGE are supported. In all other cases,
	 * IllegalArgumentException is thrown.
	 * 
	 * @param innerRadius
	 * @param unit
	 *            the unit used for the innerRadius
	 */
	public void setInnerRadius(float innerRadius, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
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

	/**
	 * @see #setOuterRadius(String)
	 */
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

	/**
	 * Sets the outerRadius using String presentation. String presentation is
	 * similar to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param outerRadius
	 *            CSS style string representation
	 */
	public void setOuterRadius(String outerRadius) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(outerRadius);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setOuterRadius(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setOuterRadius(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setOuterRadius(float,Unit)
	 */
	public Unit getOuterRadiusUnit() {
		if (this.outerRadius == null) {
			return Unit.PIXELS;
		}
		if (this.outerRadius.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the outerRadius using Vaadin Unit. Only Unit.PIXELS and
	 * Unit.PERCENTAGE are supported. In all other cases,
	 * IllegalArgumentException is thrown.
	 * 
	 * @param outerRadius
	 * @param unit
	 *            the unit used for the outerRadius
	 */
	public void setOuterRadius(float outerRadius, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
		String value = Float.toString(outerRadius);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (outerRadius == -1) {
			value = null;
		}
		this.outerRadius = value;
	}

	/**
	 * @see #setThickness(String)
	 */
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

	/**
	 * Sets the thickness using String presentation. String presentation is
	 * similar to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param thickness
	 *            CSS style string representation
	 */
	public void setThickness(String thickness) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(thickness);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setThickness(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setThickness(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setThickness(float,Unit)
	 */
	public Unit getThicknessUnit() {
		if (this.thickness == null) {
			return Unit.PIXELS;
		}
		if (this.thickness.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the thickness using Vaadin Unit. Only Unit.PIXELS and
	 * Unit.PERCENTAGE are supported. In all other cases,
	 * IllegalArgumentException is thrown.
	 * 
	 * @param thickness
	 * @param unit
	 *            the unit used for the thickness
	 */
	public void setThickness(float thickness, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
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

	/**
	 * @deprecated as of 4.0. Use {@link #setPointStart(Instant)}
	 */
	@Deprecated
	public void setFrom(Date date) {
		this.from = Util.toHighchartsTS(date);
	}

	/**
	 * @see #setFrom(Number)
	 */
	public void setFrom(Instant instant) {
		this.from = Util.toHighchartsTS(instant);
	}

	/**
	 * @deprecated as of 4.0. Use {@link #setPointStart(Instant)}
	 */
	@Deprecated
	public void setTo(Date date) {
		this.to = Util.toHighchartsTS(date);
	}

	/**
	 * @see #setTo(Number)
	 */
	public void setTo(Instant instant) {
		this.to = Util.toHighchartsTS(instant);
	}

	public PlotBand(Number from, Number to, Color color) {
		this.from = from;
		this.to = to;
		this.color = color;
	}
}