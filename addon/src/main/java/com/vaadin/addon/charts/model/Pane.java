/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import com.vaadin.server.SizeWithUnit;
import com.vaadin.server.Sizeable.Unit;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vaadin.addon.charts.model.serializers.SizeSerializer;
/**
 * Applies only to polar charts and angular gauges. This configuration object
 * holds general options for the combined X and Y axes set. Each xAxis or yAxis
 * can reference the pane by index.
 */
@Generated(value = "This class is generated and shouldn't be modified", comments = "Incorrect and missing API should be reported to https://github.com/vaadin/charts/issues/new")
public class Pane extends AbstractConfigurationObject {

	private Integer paneIndex;
	private ArrayList<Background> background;
	private String[] center;
	private Number endAngle;
	@JsonSerialize(using = SizeSerializer.class)
	private String size;
	private Number startAngle;

	public Pane() {
	}

	/**
	 * @see #setPaneIndex(Integer)
	 */
	Integer getPaneIndex() {
		return paneIndex;
	}

	void setPaneIndex(Integer paneIndex) {
		this.paneIndex = paneIndex;
	}

	/**
	 * @see #setBackground(Background...)
	 */
	public Background[] getBackground() {
		if (background == null) {
			return new Background[]{};
		}
		Background[] arr = new Background[background.size()];
		background.toArray(arr);
		return arr;
	}

	/**
	 * An object, or array of objects, for backgrounds. Sub options include
	 * <code>backgroundColor</code> (can be solid or gradient),
	 * <code>shape</code> ("solid" or "arc"), <code>innerWidth</code>,
	 * <code>outerWidth</code>, <code>borderWidth</code>,
	 * <code>borderColor</code>.
	 */
	public void setBackground(Background... background) {
		this.background = new ArrayList<Background>(Arrays.asList(background));
	}

	/**
	 * Adds background to the background array
	 * 
	 * @param background
	 *            to add
	 * @see #setBackground(Background...)
	 */
	public void addBackground(Background background) {
		if (this.background == null) {
			this.background = new ArrayList<Background>();
		}
		this.background.add(background);
	}

	/**
	 * Removes first occurrence of background in background array
	 * 
	 * @param background
	 *            to remove
	 * @see #setBackground(Background...)
	 */
	public void removeBackground(Background background) {
		this.background.remove(background);
	}

	/**
	 * The center of a polar chart or angular gauge, given as an array of [x, y]
	 * positions. Positions can be given as integers that transform to pixels,
	 * or as percentages of the plot area size.
	 * <p>
	 * Defaults to: ["50%", "50%"]
	 */
	public void setCenter(String[] center) {
		this.center = center;
	}

	/**
	 * @see #setEndAngle(Number)
	 */
	public Number getEndAngle() {
		return endAngle;
	}

	/**
	 * The end angle of the polar X axis or gauge value axis, given in degrees
	 * where 0 is north. Defaults to <a href="#pane.startAngle">startAngle</a> +
	 * 360.
	 */
	public void setEndAngle(Number endAngle) {
		this.endAngle = endAngle;
	}

	/**
	 * @see #setSize(String)
	 */
	public float getSize() {
		String tmp = size;
		if (size == null) {
			return -1.0f;
		}
		if (this.size.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	/**
	 * Sets the size using String presentation. String presentation is similar
	 * to what is used in Cascading Style Sheets. Size can be pixels or
	 * percentage, otherwise IllegalArgumentException is thrown. The empty
	 * string ("") or null will unset the height and set the units to pixels.
	 * 
	 * @param size
	 *            CSS style string representation
	 */
	public void setSize(String size) {
		SizeWithUnit sizeWithUnit = SizeWithUnit.parseStringSize(size);
		if (sizeWithUnit != null) {
			Unit unit = sizeWithUnit.getUnit();
			if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
				throw new IllegalArgumentException(
						unit.toString()
								+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
			}
			setSize(sizeWithUnit.getSize(), sizeWithUnit.getUnit());
		} else {
			setSize(-1, Unit.PIXELS);
		}
	}

	/**
	 * @see #setSize(float,Unit)
	 */
	public Unit getSizeUnit() {
		if (this.size == null) {
			return Unit.PIXELS;
		}
		if (this.size.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	/**
	 * Sets the size using Vaadin Unit. Only Unit.PIXELS and Unit.PERCENTAGE are
	 * supported. In all other cases, IllegalArgumentException is thrown.
	 * 
	 * @param size
	 * @param unit
	 *            the unit used for the size
	 */
	public void setSize(float size, Unit unit) {
		if (!(unit.equals(Unit.PERCENTAGE) || unit.equals(Unit.PIXELS))) {
			throw new IllegalArgumentException(
					unit.toString()
							+ "is not a valid unit for sizing. Only percentage and pixels are allowed.");
		}
		String value = Float.toString(size);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (size == -1) {
			value = null;
		}
		this.size = value;
	}

	/**
	 * @see #setStartAngle(Number)
	 */
	public Number getStartAngle() {
		return startAngle;
	}

	/**
	 * The start angle of the polar X axis or gauge axis, given in degrees where
	 * 0 is north. Defaults to 0.
	 */
	public void setStartAngle(Number startAngle) {
		this.startAngle = startAngle;
	}

	public Pane(Number startAngle, Number endAngle) {
		this.startAngle = startAngle;
		this.endAngle = endAngle;
	}

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}

	public String[] getCenter() {
		return this.center;
	}
}
