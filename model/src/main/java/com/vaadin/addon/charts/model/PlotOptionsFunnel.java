package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.util.SizeWithUnit;
import java.util.ArrayList;
import java.util.Arrays;
public class PlotOptionsFunnel extends AbstractPlotOptions {

	private static final long serialVersionUID = 1L;
	private Boolean allowPointSelect;
	private Color borderColor;
	private Number borderWidth;
	private Object[] center;
	private Object colors;
	private String cursor;
	private DataLabels dataLabels;
	private Number depth;
	private Boolean enableMouseTracking;
	private Boolean getExtremesFromAll;
	private String height;
	private ArrayList<String> keys;
	private String linkedTo;
	private Number minSize;
	private String neckHeight;
	private String neckWidth;
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
	private String width;
	private String zoneAxis;
	private ArrayList<Zones> zones;

	public PlotOptionsFunnel() {
	}

	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}

	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
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

	public float getHeight() {
		String tmp = height;
		if (height == null) {
			return -1.0f;
		}
		if (this.height.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getHeightUnit() {
		if (this.height == null) {
			return Unit.PIXELS;
		}
		if (this.height.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setHeight(String height) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(height);
		if (tmp != null) {
			setHeight(tmp.getSize(), tmp.getUnit());
		} else {
			setHeight(-1, Unit.PIXELS);
		}
	}

	public void setHeight(float height, Unit unit) {
		String value = Float.toString(height);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (height == -1) {
			value = null;
		}
		this.height = value;
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

	public float getNeckHeight() {
		String tmp = neckHeight;
		if (neckHeight == null) {
			return -1.0f;
		}
		if (this.neckHeight.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getNeckHeightUnit() {
		if (this.neckHeight == null) {
			return Unit.PIXELS;
		}
		if (this.neckHeight.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setNeckHeight(String neckHeight) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(neckHeight);
		if (tmp != null) {
			setNeckHeight(tmp.getSize(), tmp.getUnit());
		} else {
			setNeckHeight(-1, Unit.PIXELS);
		}
	}

	public void setNeckHeight(float neckHeight, Unit unit) {
		String value = Float.toString(neckHeight);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (neckHeight == -1) {
			value = null;
		}
		this.neckHeight = value;
	}

	public float getNeckWidth() {
		String tmp = neckWidth;
		if (neckWidth == null) {
			return -1.0f;
		}
		if (this.neckWidth.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getNeckWidthUnit() {
		if (this.neckWidth == null) {
			return Unit.PIXELS;
		}
		if (this.neckWidth.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setNeckWidth(String neckWidth) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(neckWidth);
		if (tmp != null) {
			setNeckWidth(tmp.getSize(), tmp.getUnit());
		} else {
			setNeckWidth(-1, Unit.PIXELS);
		}
	}

	public void setNeckWidth(float neckWidth, Unit unit) {
		String value = Float.toString(neckWidth);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (neckWidth == -1) {
			value = null;
		}
		this.neckWidth = value;
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

	public float getWidth() {
		String tmp = width;
		if (width == null) {
			return -1.0f;
		}
		if (this.width.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getWidthUnit() {
		if (this.width == null) {
			return Unit.PIXELS;
		}
		if (this.width.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setWidth(String width) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(width);
		if (tmp != null) {
			setWidth(tmp.getSize(), tmp.getUnit());
		} else {
			setWidth(-1, Unit.PIXELS);
		}
	}

	public void setWidth(float width, Unit unit) {
		String value = Float.toString(width);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (width == -1) {
			value = null;
		}
		this.width = value;
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
		return ChartType.FUNNEL;
	}

	public void setCenter(String x, String y) {
		this.center = new String[]{x, y};
	}

	public void setCenter(Number x, Number y) {
		this.center = new Number[]{x, y};
	}
}