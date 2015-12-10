package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import java.util.ArrayList;
import java.util.Arrays;
import com.vaadin.addon.charts.util.SizeWithUnit;
import java.util.Date;
import com.vaadin.addon.charts.util.Util;
public class PlotOptionsErrorbar extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Color color;
	private Boolean colorByPoint;
	private Object colors;
	private String cursor;
	private Number depth;
	private Color edgeColor;
	private Number edgeWidth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private Number groupZPadding;
	private ArrayList<String> keys;
	private Number lineWidth;
	private String linkedTo;
	private Number maxPointWidth;
	private Color negativeColor;
	private Number pointInterval;
	private String pointIntervalUnit;
	private Number pointPadding;
	private Object pointPlacement;
	private Number pointRange;
	private Number pointStart;
	private Number pointWidth;
	private Boolean selected;
	private States states;
	private Color stemColor;
	private String stemDashStyle;
	private Number stemWidth;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Number turboThreshold;
	private Boolean visible;
	private Color whiskerColor;
	private String whiskerLength;
	private Number whiskerWidth;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsErrorbar() {
	}

	@Override
	public ChartType getChartType() {
		return ChartType.ERRORBAR;
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean getColorByPoint() {
		return colorByPoint;
	}

	public void setColorByPoint(Boolean colorByPoint) {
		this.colorByPoint = colorByPoint;
	}

	public Object getColors() {
		return colors;
	}

	public void setColors(Object colors) {
		this.colors = colors;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Number getDepth() {
		return depth;
	}

	public void setDepth(Number depth) {
		this.depth = depth;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	public void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public Number getEdgeWidth() {
		return edgeWidth;
	}

	public void setEdgeWidth(Number edgeWidth) {
		this.edgeWidth = edgeWidth;
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

	public Number getGroupZPadding() {
		return groupZPadding;
	}

	public void setGroupZPadding(Number groupZPadding) {
		this.groupZPadding = groupZPadding;
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

	public Number getMaxPointWidth() {
		return maxPointWidth;
	}

	public void setMaxPointWidth(Number maxPointWidth) {
		this.maxPointWidth = maxPointWidth;
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

	public Number getPointPadding() {
		return pointPadding;
	}

	public void setPointPadding(Number pointPadding) {
		this.pointPadding = pointPadding;
	}

	public Object getPointPlacement() {
		return pointPlacement;
	}

	public void setPointPlacement(Object pointPlacement) {
		this.pointPlacement = pointPlacement;
	}

	public Number getPointRange() {
		return pointRange;
	}

	public void setPointRange(Number pointRange) {
		this.pointRange = pointRange;
	}

	public Number getPointStart() {
		return pointStart;
	}

	public void setPointStart(Number pointStart) {
		this.pointStart = pointStart;
	}

	public Number getPointWidth() {
		return pointWidth;
	}

	public void setPointWidth(Number pointWidth) {
		this.pointWidth = pointWidth;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public States getStates() {
		return states;
	}

	public void setStates(States states) {
		this.states = states;
	}

	public Color getStemColor() {
		return stemColor;
	}

	public void setStemColor(Color stemColor) {
		this.stemColor = stemColor;
	}

	public String getStemDashStyle() {
		return stemDashStyle;
	}

	public void setStemDashStyle(String stemDashStyle) {
		this.stemDashStyle = stemDashStyle;
	}

	public Number getStemWidth() {
		return stemWidth;
	}

	public void setStemWidth(Number stemWidth) {
		this.stemWidth = stemWidth;
	}

	public Boolean getStickyTracking() {
		return stickyTracking;
	}

	public void setStickyTracking(Boolean stickyTracking) {
		this.stickyTracking = stickyTracking;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Number getTurboThreshold() {
		return turboThreshold;
	}

	public void setTurboThreshold(Number turboThreshold) {
		this.turboThreshold = turboThreshold;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Color getWhiskerColor() {
		return whiskerColor;
	}

	public void setWhiskerColor(Color whiskerColor) {
		this.whiskerColor = whiskerColor;
	}

	public float getWhiskerLength() {
		String tmp = whiskerLength;
		if (whiskerLength == null) {
			return -1.0f;
		}
		if (this.whiskerLength.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getWhiskerLengthUnit() {
		if (this.whiskerLength == null) {
			return Unit.PIXELS;
		}
		if (this.whiskerLength.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setWhiskerLength(String whiskerLength) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(whiskerLength);
		if (tmp != null) {
			setWhiskerLength(tmp.getSize(), tmp.getUnit());
		} else {
			setWhiskerLength(-1, Unit.PIXELS);
		}
	}

	public void setWhiskerLength(float whiskerLength, Unit unit) {
		String value = Float.toString(whiskerLength);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (whiskerLength == -1) {
			value = null;
		}
		this.whiskerLength = value;
	}

	public Number getWhiskerWidth() {
		return whiskerWidth;
	}

	public void setWhiskerWidth(Number whiskerWidth) {
		this.whiskerWidth = whiskerWidth;
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

	public void setPointStart(Date date) {
		this.pointStart = Util.toHighchartsTS(date);
	}
}