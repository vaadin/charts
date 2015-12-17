package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.util.SizeWithUnit;
/**
 * An array of data points for the series. For the <code>waterfall</code> series
 * type, points can be given in the following ways:
 * <ol>
 * <li>An array of numerical values. In this case, the numerical values will be
 * interpreted as <code>y</code> options. The <code>x</code> values will be
 * automatically calculated, either starting at 0 and incremented by 1, or from
 * <code>pointStart</code> and <code>pointInterval</code> given in the series
 * options. If the axis has categories, these will be used. Example:
 * 
 * <pre>
 * data: [0, 5, 3, 5]
 * </pre>
 * 
 * </li>
 * <li>
 * <p>
 * An array of arrays with 2 values. In this case, the values correspond to
 * <code>x,y</code>. If the first value is a string, it is applied as the name
 * of the point, and the <code>x</code> value is inferred.
 * 
 * <pre>
 * data: [
 *     [0, 7], 
 *     [1, 8], 
 *     [2, 3]
 * ]
 * </pre>
 * 
 * </li>
 * 
 * 
 * <li>
 * <p>
 * An array of objects with named values. The objects are point configuration
 * objects as seen below. If the total number of data points exceeds the series'
 * <a href='#series<waterfall>.turboThreshold'>turboThreshold</a>, this option
 * is not available.
 * </p>
 * 
 * <pre>
 * data: [{
 *     x: 1,
 *     y: 8,
 *     name: "Point2",
 *     color: "#00FF00"
 * }, {
 *     x: 1,
 *     y: 8,
 *     name: "Point1",
 *     color: "#FF00FF"
 * }]
 * </pre>
 * 
 * </li>
 * </ol>
 */
public class Data extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Color color;
	private Object dataLabels;
	private Drilldown drilldown;
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

	/**
	 * @see #setColor(Color)
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Individual color for the point. By default the color is pulled from the
	 * global <code>colors</code> array.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @see #setDataLabels(Object)
	 */
	public Object getDataLabels() {
		return dataLabels;
	}

	/**
	 * Individual data label for each point. The options are the same as the
	 * ones for <a class="internal"
	 * href="#plotOptions.series.dataLabels">plotOptions.series.dataLabels</a>
	 */
	public void setDataLabels(Object dataLabels) {
		this.dataLabels = dataLabels;
	}

	/**
	 * @see #setDrilldown(Drilldown)
	 */
	public Drilldown getDrilldown() {
		return drilldown;
	}

	/**
	 * The <code>id</code> of a series in the <a
	 * href="#drilldown.series">drilldown.series</a> array to use for a
	 * drilldown for this point.
	 * <p>
	 * Defaults to:
	 */
	public void setDrilldown(Drilldown drilldown) {
		this.drilldown = drilldown;
	}

	/**
	 * @see #setId(String)
	 */
	public String getId() {
		return id;
	}

	/**
	 * An id for the point. This can be used after render time to get a pointer
	 * to the point object through <code>chart.get()</code>.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @see #setIsIntermediateSum(Boolean)
	 */
	public Boolean getIsIntermediateSum() {
		return isIntermediateSum;
	}

	/**
	 * When this property is true, the points acts as a summary column for the
	 * values added or substracted since the last intermediate sum, or since the
	 * start of the series. The <code>y</code> value is ignored.
	 * <p>
	 * Defaults to: false
	 */
	public void setIsIntermediateSum(Boolean isIntermediateSum) {
		this.isIntermediateSum = isIntermediateSum;
	}

	/**
	 * @see #setIsSum(Boolean)
	 */
	public Boolean getIsSum() {
		return isSum;
	}

	/**
	 * When this property is true, the point display the total sum across the
	 * entire series. The <code>y</code> value is ignored.
	 * <p>
	 * Defaults to: false
	 */
	public void setIsSum(Boolean isSum) {
		this.isSum = isSum;
	}

	/**
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * The name of the point as shown in the legend, tooltip, dataLabel etc.
	 * </p>
	 * 
	 * <p>
	 * If the <a href="#xAxis.type">xAxis.type</a> is set to
	 * <code>category</code>, and no <a href="#xAxis.categories">categories</a>
	 * option exists, the category will be pulled from the
	 * <code>point.name</code> of the last series defined. For multiple series,
	 * best practice however is to define <code>xAxis.categories</code>.
	 * </p>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see #setSelected(Boolean)
	 */
	public Boolean getSelected() {
		return selected;
	}

	/**
	 * Whether the data point is selected initially.
	 * <p>
	 * Defaults to: false
	 */
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * @see #setX(Number)
	 */
	public Number getX() {
		return x;
	}

	/**
	 * The x value of the point. For datetime axes, the X value is the timestamp
	 * in milliseconds since 1970.
	 */
	public void setX(Number x) {
		this.x = x;
	}

	/**
	 * @see #setY(Number)
	 */
	public Number getY() {
		return y;
	}

	/**
	 * The y value of the point.
	 */
	public void setY(Number y) {
		this.y = y;
	}

	/**
	 * @see #setColorValue(Number)
	 */
	public Number getColorValue() {
		return colorValue;
	}

	/**
	 * Serves a purpose only if a colorAxis object is defined in the chart
	 * options. This value will decide which color the point gets from the scale
	 * of the colorAxis.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setColorValue(Number colorValue) {
		this.colorValue = colorValue;
	}

	/**
	 * @see #setParent(String)
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * Only for treemap. Use this option to build a tree structure. The value
	 * should be the id of the point which is the parent. If no points has a
	 * matching id, or this option is undefined, then the parent will be set to
	 * the root.
	 * <p>
	 * Defaults to: undefined
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * @see #setValue(Number)
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * The value of the point, resulting in a relative area of the point in the
	 * treemap.
	 */
	public void setValue(Number value) {
		this.value = value;
	}

	/**
	 * @see #setMarker(Marker)
	 */
	public Marker getMarker() {
		return marker;
	}

	/**
	 * 
	 */
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

	/**
	 * @see #setLegendIndex(Number)
	 */
	public Number getLegendIndex() {
		return legendIndex;
	}

	/**
	 * The sequential index of the data point in the legend.
	 */
	public void setLegendIndex(Number legendIndex) {
		this.legendIndex = legendIndex;
	}

	/**
	 * @see #setSliced(Boolean)
	 */
	public Boolean getSliced() {
		return sliced;
	}

	/**
	 * Whether to display a slice offset from the center.
	 * <p>
	 * Defaults to: false
	 */
	public void setSliced(Boolean sliced) {
		this.sliced = sliced;
	}

	/**
	 * @see #setHigh(Number)
	 */
	public Number getHigh() {
		return high;
	}

	/**
	 * The high or maximum value for each data point.
	 */
	public void setHigh(Number high) {
		this.high = high;
	}

	/**
	 * @see #setLow(Number)
	 */
	public Number getLow() {
		return low;
	}

	/**
	 * The low or minimum value for each data point.
	 */
	public void setLow(Number low) {
		this.low = low;
	}

	/**
	 * @see #setZ(Number)
	 */
	public Number getZ() {
		return z;
	}

	/**
	 * The size value for each bubble. The bubbles' diameters are computed based
	 * on the <code>z</code>, and controlled by series options like
	 * <code>minSize</code>, <code>maxSize</code>, <code>sizeBy</code>,
	 * <code>zMin</code> and <code>zMax</code>.
	 */
	public void setZ(Number z) {
		this.z = z;
	}

	/**
	 * @see #setMedian(Number)
	 */
	public Number getMedian() {
		return median;
	}

	/**
	 * The median for each data point. This is drawn as a line through the
	 * middle area of the box.
	 */
	public void setMedian(Number median) {
		this.median = median;
	}

	/**
	 * @see #setQ1(Number)
	 */
	public Number getQ1() {
		return q1;
	}

	/**
	 * The lower quartile for each data point. This is the bottom of the box.
	 */
	public void setQ1(Number q1) {
		this.q1 = q1;
	}

	/**
	 * @see #setQ3(Number)
	 */
	public Number getQ3() {
		return q3;
	}

	/**
	 * The higher quartile for each data point. This is the top of the box.
	 */
	public void setQ3(Number q3) {
		this.q3 = q3;
	}

	/**
	 * @see #setColumns(Object[])
	 */
	public Object[] getColumns() {
		return columns;
	}

	/**
	 * A two-dimensional array representing the input data on tabular form. This
	 * input can be used when the data is already parsed, for example from a
	 * grid view component. Each cell can be a string or number. If not
	 * switchRowsAndColumns is set, the columns are interpreted as series.
	 * <p>
	 * Defaults to:
	 */
	public void setColumns(Object[] columns) {
		this.columns = columns;
	}

	/**
	 * @see #setComplete(Object)
	 */
	public Object getComplete() {
		return complete;
	}

	/**
	 * The callback that is evaluated when the data is finished loading,
	 * optionally from an external source, and parsed. The first argument passed
	 * is a finished chart options object, containing the series. These options
	 * can be extended with additional options and passed directly to the chart
	 * constructor.
	 * <p>
	 * Defaults to:
	 */
	public void setComplete(Object complete) {
		this.complete = complete;
	}

	/**
	 * @see #setCsv(String)
	 */
	public String getCsv() {
		return csv;
	}

	/**
	 * A comma delimited string to be parsed. Related options are <a
	 * href="#data.startRow">startRow</a>, <a href="#data.endRow">endRow</a>, <a
	 * href="#data.startColumn">startColumn</a> and <a
	 * href="#data.endColumn">endColumn</a> to delimit what part of the table is
	 * used. The <a href="#data.lineDelimiter">lineDelimiter</a> and <a
	 * href="#data.itemDelimiter">itemDelimiter</a> options define the CSV
	 * delimiter formats.
	 * <p>
	 * Defaults to:
	 */
	public void setCsv(String csv) {
		this.csv = csv;
	}

	/**
	 * @see #setDateFormat(String)
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * <p>
	 * Which of the predefined date formats in Date.prototype.dateFormats to use
	 * to parse date values. Defaults to a best guess based on what format gives
	 * valid and ordered dates.
	 * </p>
	 * 
	 * <p>
	 * Valid options include:
	 * <ul>
	 * <li><code>YYYY-mm-dd</code></li>
	 * <li><code>dd/mm/YYYY</code></li>
	 * <li><code>mm/dd/YYYY</code></li>
	 * <li><code>dd/mm/YY</code></li>
	 * <li><code>mm/dd/YY</code></li>
	 * </ul>
	 * </p>
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * @see #setDecimalPoint(String)
	 */
	public String getDecimalPoint() {
		return decimalPoint;
	}

	/**
	 * The decimal point used for parsing numbers in the CSV.
	 * <p>
	 * Defaults to: .
	 */
	public void setDecimalPoint(String decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	/**
	 * @see #setEndColumn(Number)
	 */
	public Number getEndColumn() {
		return endColumn;
	}

	/**
	 * In tabular input data, the last column (indexed by 0) to use. Defaults to
	 * the last column containing data.
	 * <p>
	 * Defaults to:
	 */
	public void setEndColumn(Number endColumn) {
		this.endColumn = endColumn;
	}

	/**
	 * @see #setEndRow(Number)
	 */
	public Number getEndRow() {
		return endRow;
	}

	/**
	 * In tabular input data, the last row (indexed by 0) to use. Defaults to
	 * the last row containing data.
	 * <p>
	 * Defaults to:
	 */
	public void setEndRow(Number endRow) {
		this.endRow = endRow;
	}

	/**
	 * @see #setFirstRowAsNames(Boolean)
	 */
	public Boolean getFirstRowAsNames() {
		return firstRowAsNames;
	}

	/**
	 * Whether to use the first row in the data set as series names.
	 * <p>
	 * Defaults to: true
	 */
	public void setFirstRowAsNames(Boolean firstRowAsNames) {
		this.firstRowAsNames = firstRowAsNames;
	}

	/**
	 * @see #setGoogleSpreadsheetKey(String)
	 */
	public String getGoogleSpreadsheetKey() {
		return googleSpreadsheetKey;
	}

	/**
	 * The key for a Google Spreadsheet to load. See <a
	 * href="https://developers.google.com/gdata/samples/spreadsheet_sample"
	 * >general information on GS</a>.
	 * <p>
	 * Defaults to:
	 */
	public void setGoogleSpreadsheetKey(String googleSpreadsheetKey) {
		this.googleSpreadsheetKey = googleSpreadsheetKey;
	}

	/**
	 * @see #setGoogleSpreadsheetWorksheet(String)
	 */
	public String getGoogleSpreadsheetWorksheet() {
		return googleSpreadsheetWorksheet;
	}

	/**
	 * The Google Spreadsheet worksheet to use in combination with <a
	 * href="#data.googleSpreadsheetKey">googleSpreadsheetKey</a>. The available
	 * id's from your sheet can be read from
	 * <code>https://spreadsheets.google.com/feeds/worksheets/{key}/public/basic</code>
	 */
	public void setGoogleSpreadsheetWorksheet(String googleSpreadsheetWorksheet) {
		this.googleSpreadsheetWorksheet = googleSpreadsheetWorksheet;
	}

	/**
	 * @see #setItemDelimiter(String)
	 */
	public String getItemDelimiter() {
		return itemDelimiter;
	}

	/**
	 * Item or cell delimiter for parsing CSV. Defaults to the tab character
	 * <code>\t</code> if a tab character is found in the CSV string, if not it
	 * defaults to <code>,</code>.
	 * <p>
	 * Defaults to:
	 */
	public void setItemDelimiter(String itemDelimiter) {
		this.itemDelimiter = itemDelimiter;
	}

	/**
	 * @see #setLineDelimiter(String)
	 */
	public String getLineDelimiter() {
		return lineDelimiter;
	}

	/**
	 * Line delimiter for parsing CSV.
	 * <p>
	 * Defaults to: \n
	 */
	public void setLineDelimiter(String lineDelimiter) {
		this.lineDelimiter = lineDelimiter;
	}

	/**
	 * @see #setParseDate(Object)
	 */
	public Object getParseDate() {
		return parseDate;
	}

	/**
	 * A callback function to parse string representations of dates into
	 * JavaScript timestamps. Should return an integer timestamp on success.
	 * <p>
	 * Defaults to:
	 */
	public void setParseDate(Object parseDate) {
		this.parseDate = parseDate;
	}

	/**
	 * @see #setParsed(Object)
	 */
	public Object getParsed() {
		return parsed;
	}

	/**
	 * A callback function to access the parsed columns, the two-dimentional
	 * input data array directly, before they are interpreted into series data
	 * and categories. Return <code>false</code> to stop completion, or call
	 * <code>this.complete()</code> to continue async.
	 * <p>
	 * Defaults to:
	 */
	public void setParsed(Object parsed) {
		this.parsed = parsed;
	}

	/**
	 * @see #setRows(Object[])
	 */
	public Object[] getRows() {
		return rows;
	}

	/**
	 * The same as the columns input option, but defining rows intead of
	 * columns.
	 * <p>
	 * Defaults to:
	 */
	public void setRows(Object[] rows) {
		this.rows = rows;
	}

	/**
	 * @see #setSeriesMapping(Object)
	 */
	public Object getSeriesMapping() {
		return seriesMapping;
	}

	/**
	 * An array containing object with Point property names along with what
	 * column id the property should be taken from.
	 */
	public void setSeriesMapping(Object seriesMapping) {
		this.seriesMapping = seriesMapping;
	}

	/**
	 * @see #setStartColumn(Number)
	 */
	public Number getStartColumn() {
		return startColumn;
	}

	/**
	 * In tabular input data, the first column (indexed by 0) to use.
	 * <p>
	 * Defaults to: 0
	 */
	public void setStartColumn(Number startColumn) {
		this.startColumn = startColumn;
	}

	/**
	 * @see #setStartRow(Number)
	 */
	public Number getStartRow() {
		return startRow;
	}

	/**
	 * In tabular input data, the first row (indexed by 0) to use.
	 * <p>
	 * Defaults to: 0
	 */
	public void setStartRow(Number startRow) {
		this.startRow = startRow;
	}

	/**
	 * @see #setSwitchRowsAndColumns(Boolean)
	 */
	public Boolean getSwitchRowsAndColumns() {
		return switchRowsAndColumns;
	}

	/**
	 * Switch rows and columns of the input data, so that
	 * <code>this.columns</code> effectively becomes the rows of the data set,
	 * and the rows are interpreted as series.
	 * <p>
	 * Defaults to: false
	 */
	public void setSwitchRowsAndColumns(Boolean switchRowsAndColumns) {
		this.switchRowsAndColumns = switchRowsAndColumns;
	}

	/**
	 * @see #setTable(Object)
	 */
	public Object getTable() {
		return table;
	}

	/**
	 * A HTML table or the id of such to be parsed as input data. Related
	 * options are <code>startRow</code>, <code>endRow</code>,
	 * <code>startColumn</code> and <code>endColumn</code> to delimit what part
	 * of the table is used.
	 * <p>
	 * Defaults to:
	 */
	public void setTable(Object table) {
		this.table = table;
	}
}