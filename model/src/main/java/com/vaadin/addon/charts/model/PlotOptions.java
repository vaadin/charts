package com.vaadin.addon.charts.model;
/**
 * <p>
 * The plotOptions is a wrapper object for config objects for each series type.
 * The config objects for each series can also be overridden for each series
 * item as given in the series array.
 * </p>
 * <p>
 * Configuration options for the series are given in three levels. Options for
 * all series in a chart are given in the <a class="internal"
 * href="#plotOptions.series">plotOptions.series</a> object. Then options for
 * all series of a specific type are given in the plotOptions of that type, for
 * example plotOptions.line. Next, options for one single series are given in <a
 * class="internal" href="#series">the series array</a>.
 * </p>
 */
public class PlotOptions extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private PlotOptionsArea area;
	private PlotOptionsAreaRange arearange;
	private PlotOptionsAreaSpline areaspline;
	private PlotOptionsAreaSplineRange areasplinerange;
	private PlotOptionsBar bar;
	private PlotOptionsBoxplot boxplot;
	private PlotOptionsBubble bubble;
	private PlotOptionsColumn column;
	private PlotOptionsColumnRange columnrange;
	private PlotOptionsErrorbar errorbar;
	private PlotOptionsFunnel funnel;
	private PlotOptionsGauge gauge;
	private PlotOptionsHeatMap heatmap;
	private PlotOptionsLine line;
	private PlotOptionsPie pie;
	private PlotOptionsPolygon polygon;
	private PlotOptionsPyramid pyramid;
	private PlotOptionsScatter scatter;
	private PlotOptionsSeries series;
	private PlotOptionsSolidGauge solidgauge;
	private PlotOptionsSpline spline;
	private PlotOptionsTreeMap treemap;
	private PlotOptionsWaterfall waterfall;

	public PlotOptions() {
	}

	/**
	 * @see #setArea(PlotOptionsArea)
	 */
	public PlotOptionsArea getArea() {
		return area;
	}

	/**
	 * 
	 */
	public void setArea(PlotOptionsArea area) {
		this.area = area;
	}

	/**
	 * @see #setArearange(PlotOptionsAreaRange)
	 */
	public PlotOptionsAreaRange getArearange() {
		return arearange;
	}

	/**
	 * The area range is a cartesian series type with higher and lower Y values
	 * along an X axis. Requires <code>highcharts-more.js</code>.
	 */
	public void setArearange(PlotOptionsAreaRange arearange) {
		this.arearange = arearange;
	}

	/**
	 * @see #setAreaspline(PlotOptionsAreaSpline)
	 */
	public PlotOptionsAreaSpline getAreaspline() {
		return areaspline;
	}

	/**
	 * 
	 */
	public void setAreaspline(PlotOptionsAreaSpline areaspline) {
		this.areaspline = areaspline;
	}

	/**
	 * @see #setAreasplinerange(PlotOptionsAreaSplineRange)
	 */
	public PlotOptionsAreaSplineRange getAreasplinerange() {
		return areasplinerange;
	}

	/**
	 * The area spline range is a cartesian series type with higher and lower Y
	 * values along an X axis. Requires <code>highcharts-more.js</code>.
	 * <p>
	 * Defaults to:
	 */
	public void setAreasplinerange(PlotOptionsAreaSplineRange areasplinerange) {
		this.areasplinerange = areasplinerange;
	}

	/**
	 * @see #setBar(PlotOptionsBar)
	 */
	public PlotOptionsBar getBar() {
		return bar;
	}

	/**
	 * 
	 */
	public void setBar(PlotOptionsBar bar) {
		this.bar = bar;
	}

	/**
	 * @see #setBoxplot(PlotOptionsBoxplot)
	 */
	public PlotOptionsBoxplot getBoxplot() {
		return boxplot;
	}

	/**
	 * A box plot is a convenient way of depicting groups of data through their
	 * five-number summaries: the smallest observation (sample minimum), lower
	 * quartile (Q1), median (Q2), upper quartile (Q3), and largest observation
	 * (sample maximum).
	 */
	public void setBoxplot(PlotOptionsBoxplot boxplot) {
		this.boxplot = boxplot;
	}

	/**
	 * @see #setBubble(PlotOptionsBubble)
	 */
	public PlotOptionsBubble getBubble() {
		return bubble;
	}

	/**
	 * A bubble series is a three dimensional series type where each point
	 * renders an X, Y and Z value. Each points is drawn as a bubble where the
	 * position along the X and Y axes mark the X and Y values, and the size of
	 * the bubble relates to the Z value.
	 * <p>
	 * Defaults to:
	 */
	public void setBubble(PlotOptionsBubble bubble) {
		this.bubble = bubble;
	}

	/**
	 * @see #setColumn(PlotOptionsColumn)
	 */
	public PlotOptionsColumn getColumn() {
		return column;
	}

	/**
	 * <p>
	 * Defaults to:
	 */
	public void setColumn(PlotOptionsColumn column) {
		this.column = column;
	}

	/**
	 * @see #setColumnrange(PlotOptionsColumnRange)
	 */
	public PlotOptionsColumnRange getColumnrange() {
		return columnrange;
	}

	/**
	 * The column range is a cartesian series type with higher and lower Y
	 * values along an X axis. Requires <code>highcharts-more.js</code>. To
	 * display horizontal bars, set <a href="#chart.inverted">chart.inverted</a>
	 * to <code>true</code>.
	 */
	public void setColumnrange(PlotOptionsColumnRange columnrange) {
		this.columnrange = columnrange;
	}

	/**
	 * @see #setErrorbar(PlotOptionsErrorbar)
	 */
	public PlotOptionsErrorbar getErrorbar() {
		return errorbar;
	}

	/**
	 * Error bars are a graphical representation of the variability of data and
	 * are used on graphs to indicate the error, or uncertainty in a reported
	 * measurement.
	 * <p>
	 * Defaults to:
	 */
	public void setErrorbar(PlotOptionsErrorbar errorbar) {
		this.errorbar = errorbar;
	}

	/**
	 * @see #setFunnel(PlotOptionsFunnel)
	 */
	public PlotOptionsFunnel getFunnel() {
		return funnel;
	}

	/**
	 * Funnel charts are a type of chart often used to visualize stages in a
	 * sales project, where the top are the initial stages with the most
	 * clients. It requires that the <code>modules/funnel.js</code> file is
	 * loaded.
	 */
	public void setFunnel(PlotOptionsFunnel funnel) {
		this.funnel = funnel;
	}

	/**
	 * @see #setGauge(PlotOptionsGauge)
	 */
	public PlotOptionsGauge getGauge() {
		return gauge;
	}

	/**
	 * General plotting options for the gauge series type. Requires
	 * <code>highcharts-more.js</code>
	 */
	public void setGauge(PlotOptionsGauge gauge) {
		this.gauge = gauge;
	}

	/**
	 * @see #setHeatmap(PlotOptionsHeatMap)
	 */
	public PlotOptionsHeatMap getHeatmap() {
		return heatmap;
	}

	/**
	 * <p>
	 * The heatmap series type. This series type is available both in Highcharts
	 * and Highmaps.
	 * </p>
	 * 
	 * <p>
	 * The colors of each heat map point is usually determined by its value and
	 * controlled by settings on the <a href="#colorAxis">colorAxis</a>.
	 * </p>
	 */
	public void setHeatmap(PlotOptionsHeatMap heatmap) {
		this.heatmap = heatmap;
	}

	/**
	 * @see #setLine(PlotOptionsLine)
	 */
	public PlotOptionsLine getLine() {
		return line;
	}

	/**
	 * 
	 */
	public void setLine(PlotOptionsLine line) {
		this.line = line;
	}

	/**
	 * @see #setPie(PlotOptionsPie)
	 */
	public PlotOptionsPie getPie() {
		return pie;
	}

	/**
	 * A pie chart is a circular chart divided into sectors, illustrating
	 * numerical proportion.
	 */
	public void setPie(PlotOptionsPie pie) {
		this.pie = pie;
	}

	/**
	 * @see #setPolygon(PlotOptionsPolygon)
	 */
	public PlotOptionsPolygon getPolygon() {
		return polygon;
	}

	/**
	 * A polygon series can be used to draw any freeform shape in the cartesian
	 * coordinate system. A fill is applied with the <code>color</code> option,
	 * and stroke is applied through <code>lineWidth</code> and
	 * <code>lineColor</code> options. Requires the
	 * <code>highcharts-more.js</code> file.
	 */
	public void setPolygon(PlotOptionsPolygon polygon) {
		this.polygon = polygon;
	}

	/**
	 * @see #setPyramid(PlotOptionsPyramid)
	 */
	public PlotOptionsPyramid getPyramid() {
		return pyramid;
	}

	/**
	 * A pyramid chart consists of a single pyramid with item heights
	 * corresponding to each point value. Technically it is the same as a
	 * reversed funnel chart without a neck.
	 */
	public void setPyramid(PlotOptionsPyramid pyramid) {
		this.pyramid = pyramid;
	}

	/**
	 * @see #setScatter(PlotOptionsScatter)
	 */
	public PlotOptionsScatter getScatter() {
		return scatter;
	}

	/**
	 * <p>
	 * Defaults to:
	 */
	public void setScatter(PlotOptionsScatter scatter) {
		this.scatter = scatter;
	}

	/**
	 * @see #setSeries(PlotOptionsSeries)
	 */
	public PlotOptionsSeries getSeries() {
		return series;
	}

	/**
	 * <p>
	 * General options for all series types.
	 * </p>
	 */
	public void setSeries(PlotOptionsSeries series) {
		this.series = series;
	}

	/**
	 * @see #setSolidgauge(PlotOptionsSolidGauge)
	 */
	public PlotOptionsSolidGauge getSolidgauge() {
		return solidgauge;
	}

	/**
	 * A gauge showing values using a filled arc with colors indicating the
	 * value. The solid gauge plots values against the <code>yAxis</code>, which
	 * is extended with some color options, <a
	 * href="#yAxis.minColor">minColor</a>, <a
	 * href="#yAxis.maxColor">maxColor</a> and <a href="#yAxis.stops">stops</a>,
	 * to control the color of the gauge itself.
	 */
	public void setSolidgauge(PlotOptionsSolidGauge solidgauge) {
		this.solidgauge = solidgauge;
	}

	/**
	 * @see #setSpline(PlotOptionsSpline)
	 */
	public PlotOptionsSpline getSpline() {
		return spline;
	}

	/**
	 * 
	 */
	public void setSpline(PlotOptionsSpline spline) {
		this.spline = spline;
	}

	/**
	 * @see #setTreemap(PlotOptionsTreeMap)
	 */
	public PlotOptionsTreeMap getTreemap() {
		return treemap;
	}

	/**
	 * The size of the point shape is determined by its value relative to its
	 * siblings values. Requires the module <code>heatmap.js</code> as well, if
	 * functionality such as the <a
	 * href="http://api.highcharts.com/highmaps#colorAxis">colorAxis</a> is to
	 * be used.
	 */
	public void setTreemap(PlotOptionsTreeMap treemap) {
		this.treemap = treemap;
	}

	/**
	 * @see #setWaterfall(PlotOptionsWaterfall)
	 */
	public PlotOptionsWaterfall getWaterfall() {
		return waterfall;
	}

	/**
	 * Options for the waterfall series type.
	 * <p>
	 * Defaults to:
	 */
	public void setWaterfall(PlotOptionsWaterfall waterfall) {
		this.waterfall = waterfall;
	}
}