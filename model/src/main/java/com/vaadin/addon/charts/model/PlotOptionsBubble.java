package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import com.vaadin.addon.charts.util.Util;
import com.vaadin.addon.charts.util.SizeWithUnit;
public class PlotOptionsBubble extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Boolean animation;
	private Color color;
	private Number cropThreshold;
	private String cursor;
	private String dashStyle;
	private DataLabels dataLabels;
	private Boolean displayNegative;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private ArrayList<String> keys;
	private Number lineWidth;
	private String linkedTo;
	private Marker marker;
	private Color negativeColor;
	private Number pointInterval;
	private String pointIntervalUnit;
	private Number pointStart;
	private Boolean selected;
	private Object shadow;
	private Boolean showCheckbox;
	private Boolean showInLegend;
	private String sizeBy;
	private Boolean sizeByAbsoluteValue;
	private Boolean softThreshold;
	private States states;
	private Boolean stickyTracking;
	private Number threshold;
	private Tooltip tooltip;
	private Boolean visible;
	private Number zMax;
	private Number zMin;
	private Number zThreshold;
	private String zoneAxis;
	private ArrayList<Zones> zones;
	private String minSize;
	private String maxSize;

	public PlotOptionsBubble() {
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Boolean getAnimation() {
		return animation;
	}

	public void setAnimation(Boolean animation) {
		this.animation = animation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Number getCropThreshold() {
		return cropThreshold;
	}

	public void setCropThreshold(Number cropThreshold) {
		this.cropThreshold = cropThreshold;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public String getDashStyle() {
		return dashStyle;
	}

	public void setDashStyle(String dashStyle) {
		this.dashStyle = dashStyle;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public Boolean getDisplayNegative() {
		return displayNegative;
	}

	public void setDisplayNegative(Boolean displayNegative) {
		this.displayNegative = displayNegative;
	}

	public Boolean getEnableMouseTracking() {
		return enableMouseTracking;
	}

	public void setEnableMouseTracking(Boolean enableMouseTracking) {
		this.enableMouseTracking = enableMouseTracking;
	}

	public Boolean getGetExtremesFromAll() {
		return getExtremesFromAll;
	}

	public void setGetExtremesFromAll(Boolean getExtremesFromAll) {
		this.getExtremesFromAll = getExtremesFromAll;
	}

	public String[] getKeys() {
		String[] arr = new String[keys.size()];
		keys.toArray(arr);
		return arr;
	}

	public void setKeys(String... keys) {
		this.keys = new ArrayList<String>(Arrays.asList(keys));
	}

	public void addKey(String key) {
		if (this.keys == null) {
			this.keys = new ArrayList<String>();
		}
		this.keys.add(key);
	}

	public void removeKey(String key) {
		this.keys.remove(key);
	}

	public Number getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Number lineWidth) {
		this.lineWidth = lineWidth;
	}

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public Color getNegativeColor() {
		return negativeColor;
	}

	public void setNegativeColor(Color negativeColor) {
		this.negativeColor = negativeColor;
	}

	public Number getPointInterval() {
		return pointInterval;
	}

	public void setPointInterval(Number pointInterval) {
		this.pointInterval = pointInterval;
	}

	public String getPointIntervalUnit() {
		return pointIntervalUnit;
	}

	public void setPointIntervalUnit(String pointIntervalUnit) {
		this.pointIntervalUnit = pointIntervalUnit;
	}

	public Number getPointStart() {
		return pointStart;
	}

	public void setPointStart(Number pointStart) {
		this.pointStart = pointStart;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Object getShadow() {
		return shadow;
	}

	public void setShadow(Object shadow) {
		this.shadow = shadow;
	}

	public Boolean getShowCheckbox() {
		return showCheckbox;
	}

	public void setShowCheckbox(Boolean showCheckbox) {
		this.showCheckbox = showCheckbox;
	}

	public Boolean getShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public String getSizeBy() {
		return sizeBy;
	}

	public void setSizeBy(String sizeBy) {
		this.sizeBy = sizeBy;
	}

	public Boolean getSizeByAbsoluteValue() {
		return sizeByAbsoluteValue;
	}

	public void setSizeByAbsoluteValue(Boolean sizeByAbsoluteValue) {
		this.sizeByAbsoluteValue = sizeByAbsoluteValue;
	}

	public Boolean getSoftThreshold() {
		return softThreshold;
	}

	public void setSoftThreshold(Boolean softThreshold) {
		this.softThreshold = softThreshold;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	public Number getThreshold() {
		return threshold;
	}

	public void setThreshold(Number threshold) {
		this.threshold = threshold;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Number getZMax() {
		return zMax;
	}

	public void setZMax(Number zMax) {
		this.zMax = zMax;
	}

	public Number getZMin() {
		return zMin;
	}

	public void setZMin(Number zMin) {
		this.zMin = zMin;
	}

	public Number getZThreshold() {
		return zThreshold;
	}

	public void setZThreshold(Number zThreshold) {
		this.zThreshold = zThreshold;
	}

	public String getZoneAxis() {
		return zoneAxis;
	}

	public void setZoneAxis(String zoneAxis) {
		this.zoneAxis = zoneAxis;
	}

	public Zones[] getZones() {
		Zones[] arr = new Zones[zones.size()];
		zones.toArray(arr);
		return arr;
	}

	public void setZones(Zones... zones) {
		this.zones = new ArrayList<Zones>(Arrays.asList(zones));
	}

	public void addZone(Zones zone) {
		if (this.zones == null) {
			this.zones = new ArrayList<Zones>();
		}
		this.zones.add(zone);
	}

	public void removeZone(Zones zone) {
		this.zones.remove(zone);
	}

	@Override
	public ChartType getChartType() {
		return ChartType.BUBBLE;
	}

	public void setPointStart(Date date) {
		this.pointStart = Util.toHighchartsTS(date);
	}

	public float getMinSize() {
		String tmp = minSize;
		if (minSize == null) {
			return -1.0f;
		}
		if (this.minSize.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getMinSizeUnit() {
		if (this.minSize == null) {
			return Unit.PIXELS;
		}
		if (this.minSize.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setMinSize(String minSize) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(minSize);
		if (tmp != null) {
			setMinSize(tmp.getSize(), tmp.getUnit());
		} else {
			setMinSize(-1, Unit.PIXELS);
		}
	}

	public void setMinSize(float minSize, Unit unit) {
		String value = Float.toString(minSize);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (minSize == -1) {
			value = null;
		}
		this.minSize = value;
	}

	public float getMaxSize() {
		String tmp = maxSize;
		if (maxSize == null) {
			return -1.0f;
		}
		if (this.maxSize.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getMaxSizeUnit() {
		if (this.maxSize == null) {
			return Unit.PIXELS;
		}
		if (this.maxSize.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setMaxSize(String maxSize) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(maxSize);
		if (tmp != null) {
			setMaxSize(tmp.getSize(), tmp.getUnit());
		} else {
			setMaxSize(-1, Unit.PIXELS);
		}
	}

	public void setMaxSize(float maxSize, Unit unit) {
		String value = Float.toString(maxSize);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (maxSize == -1) {
			value = null;
		}
		this.maxSize = value;
	}
}