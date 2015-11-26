package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.util.SizeWithUnit;
public class Data extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Object color;
	private DataLabels dataLabels;
	private String drilldown;
	private String id;
	private Boolean isIntermediateSum;
	private Boolean isSum;
	private String name;
	private Boolean selected;
	private Number x;
	private Number y;
	private Number colorValue;
	private String parent;
	private Number value;
	private Marker marker;
	private String innerRadius;
	private String radius;
	private Number legendIndex;
	private Boolean sliced;
	private Number high;
	private Number low;
	private Number z;
	private Number median;
	private Number q1;
	private Number q3;
	private Object[] columns;
	private Object complete;
	private String csv;
	private String dateFormat;
	private String decimalPoint;
	private Number endColumn;
	private Number endRow;
	private Boolean firstRowAsNames;
	private String googleSpreadsheetKey;
	private String googleSpreadsheetWorksheet;
	private String itemDelimiter;
	private String lineDelimiter;
	private Object parseDate;
	private Object parsed;
	private Object[] rows;
	private Object seriesMapping;
	private Number startColumn;
	private Number startRow;
	private Boolean switchRowsAndColumns;
	private Object table;

	public Data() {
	}

	public Object getColor() {
		return color;
	}

	public void setColor(Object color) {
		this.color = color;
	}

	public DataLabels getDataLabels() {
		return dataLabels;
	}

	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}

	public String getDrilldown() {
		return drilldown;
	}

	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsIntermediateSum() {
		return isIntermediateSum;
	}

	public void setIsIntermediateSum(Boolean isIntermediateSum) {
		this.isIntermediateSum = isIntermediateSum;
	}

	public Boolean getIsSum() {
		return isSum;
	}

	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Number getX() {
		return x;
	}

	public void setX(Number x) {
		this.x = x;
	}

	public Number getY() {
		return y;
	}

	public void setY(Number y) {
		this.y = y;
	}

	public Number getColorValue() {
		return colorValue;
	}

	public void setColorValue(Number colorValue) {
		this.colorValue = colorValue;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
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

	public float getRadius() {
		String tmp = radius;
		if (radius == null) {
			return -1.0f;
		}
		if (this.radius.contains("%")) {
			tmp = tmp.replace("%", "");
		}
		return Float.valueOf(tmp).floatValue();
	}

	public Unit getRadiusUnit() {
		if (this.radius == null) {
			return Unit.PIXELS;
		}
		if (this.radius.contains("%")) {
			return Unit.PERCENTAGE;
		}
		return Unit.PIXELS;
	}

	public void setRadius(String radius) {
		SizeWithUnit tmp = SizeWithUnit.parseStringSize(radius);
		if (tmp != null) {
			setRadius(tmp.getSize(), tmp.getUnit());
		} else {
			setRadius(-1, Unit.PIXELS);
		}
	}

	public void setRadius(float radius, Unit unit) {
		String value = Float.toString(radius);
		if (unit.equals(Unit.PERCENTAGE)) {
			value += "%";
		}
		if (radius == -1) {
			value = null;
		}
		this.radius = value;
	}

	public Number getLegendIndex() {
		return legendIndex;
	}

	public void setLegendIndex(Number legendIndex) {
		this.legendIndex = legendIndex;
	}

	public Boolean getSliced() {
		return sliced;
	}

	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}

	public Number getHigh() {
		return high;
	}

	public void setHigh(Number high) {
		this.high = high;
	}

	public Number getLow() {
		return low;
	}

	public void setLow(Number low) {
		this.low = low;
	}

	public Number getZ() {
		return z;
	}

	public void setZ(Number z) {
		this.z = z;
	}

	public Number getMedian() {
		return median;
	}

	public void setMedian(Number median) {
		this.median = median;
	}

	public Number getQ1() {
		return q1;
	}

	public void setQ1(Number q1) {
		this.q1 = q1;
	}

	public Number getQ3() {
		return q3;
	}

	public void setQ3(Number q3) {
		this.q3 = q3;
	}

	public Object[] getColumns() {
		return columns;
	}

	public void setColumns(Object[] columns) {
		this.columns = columns;
	}

	public Object getComplete() {
		return complete;
	}

	public void setComplete(Object complete) {
		this.complete = complete;
	}

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDecimalPoint() {
		return decimalPoint;
	}

	public void setDecimalPoint(String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	public Number getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(Number endColumn) {
		this.endColumn = endColumn;
	}

	public Number getEndRow() {
		return endRow;
	}

	public void setEndRow(Number endRow) {
		this.endRow = endRow;
	}

	public Boolean getFirstRowAsNames() {
		return firstRowAsNames;
	}

	public void setFirstRowAsNames(Boolean firstRowAsNames) {
		this.firstRowAsNames = firstRowAsNames;
	}

	public String getGoogleSpreadsheetKey() {
		return googleSpreadsheetKey;
	}

	public void setGoogleSpreadsheetKey(String googleSpreadsheetKey) {
		this.googleSpreadsheetKey = googleSpreadsheetKey;
	}

	public String getGoogleSpreadsheetWorksheet() {
		return googleSpreadsheetWorksheet;
	}

	public void setGoogleSpreadsheetWorksheet(String googleSpreadsheetWorksheet) {
		this.googleSpreadsheetWorksheet = googleSpreadsheetWorksheet;
	}

	public String getItemDelimiter() {
		return itemDelimiter;
	}

	public void setItemDelimiter(String itemDelimiter) {
		this.itemDelimiter = itemDelimiter;
	}

	public String getLineDelimiter() {
		return lineDelimiter;
	}

	public void setLineDelimiter(String lineDelimiter) {
		this.lineDelimiter = lineDelimiter;
	}

	public Object getParseDate() {
		return parseDate;
	}

	public void setParseDate(Object parseDate) {
		this.parseDate = parseDate;
	}

	public Object getParsed() {
		return parsed;
	}

	public void setParsed(Object parsed) {
		this.parsed = parsed;
	}

	public Object[] getRows() {
		return rows;
	}

	public void setRows(Object[] rows) {
		this.rows = rows;
	}

	public Object getSeriesMapping() {
		return seriesMapping;
	}

	public void setSeriesMapping(Object seriesMapping) {
		this.seriesMapping = seriesMapping;
	}

	public Number getStartColumn() {
		return startColumn;
	}

	public void setStartColumn(Number startColumn) {
		this.startColumn = startColumn;
	}

	public Number getStartRow() {
		return startRow;
	}

	public void setStartRow(Number startRow) {
		this.startRow = startRow;
	}

	public Boolean getSwitchRowsAndColumns() {
		return switchRowsAndColumns;
	}

	public void setSwitchRowsAndColumns(Boolean switchRowsAndColumns) {
		this.switchRowsAndColumns = switchRowsAndColumns;
	}

	public Object getTable() {
		return table;
	}

	public void setTable(Object table) {
		this.table = table;
	}
}