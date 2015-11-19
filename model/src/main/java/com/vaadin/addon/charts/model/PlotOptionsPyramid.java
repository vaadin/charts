package com.vaadin.addon.charts.model;

import java.util.ArrayList;
import java.util.Arrays;
public class PlotOptionsPyramid extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Object borderColor;
	private Number borderWidth;
	private Object[] center;
	private Object colors;
	private String cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private Object height;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number minSize;
	private Point point;
	private Boolean reversed;
	private Boolean selected;
	private Object shadow;
	private Boolean showInLegend;
	private Number slicedOffset;
	private States states;
	private Boolean stickyTracking;
	private Tooltip tooltip;
	private Boolean visible;
	private Object width;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsPyramid() {
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}

	public Object getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Object borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	public Object[] getCenter() {
		return center;
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

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public Number getDepth() {
		return depth;
	}

	public void setDepth(Number depth) {
		this.depth = depth;
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

	public Object getHeight() {
		return height;
	}

	public void setHeight(Object height) {
		this.height = height;
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

	public String getLinkedTo() {
		return linkedTo;
	}

	public void setLinkedTo(String linkedTo) {
		this.linkedTo = linkedTo;
	}

	public Number getMinSize() {
		return minSize;
	}

	public void setMinSize(Number minSize) {
		this.minSize = minSize;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
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

	public Boolean getShowInLegend() {
		return showInLegend;
	}

	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}

	public Number getSlicedOffset() {
		return slicedOffset;
	}

	public void setSlicedOffset(Number slicedOffset) {
		this.slicedOffset = slicedOffset;
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

	public Object getWidth() {
		return width;
	}

	public void setWidth(Object width) {
		this.width = width;
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
		return ChartType.PYRAMID;
	}

	public void setCenter(Number x, Number y) {
		this.center = new Number[]{x, y};
	}

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}
}