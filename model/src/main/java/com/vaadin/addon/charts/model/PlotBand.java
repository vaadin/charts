package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.util.SizeWithUnit;
public class PlotBand extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
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

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Number getFrom() {
		return from;
	}

	public void setFrom(Number from) {
		this.from = from;
	}

	public String getId() {
		return id;
	}

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

	public Label getLabel() {
		return label;
	}

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

	public Number getTo() {
		return to;
	}

	public void setTo(Number to) {
		this.to = to;
	}

	public Number getZIndex() {
		return zIndex;
	}

	public void setZIndex(Number zIndex) {
		this.zIndex = zIndex;
	}

	public PlotBand(Number from, Number to, Color color) {
		this.from = from;
		this.to = to;
		this.color = color;
	}
}