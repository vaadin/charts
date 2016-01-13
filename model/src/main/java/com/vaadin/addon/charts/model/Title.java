package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2016 Vaadin Ltd
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

import java.util.ArrayList;
import java.util.Arrays;
import com.vaadin.addon.charts.model.style.Style;
/**
 * The chart's main title.
 */
public class Title extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private HorizontalAlign align;
	private Boolean floating;
	private ArrayList<Number> margin;
	private Style style;
	private String text;
	private Boolean useHTML;
	private VerticalAlign verticalAlign;
	private Number x;
	private Number y;

	public Title() {
	}

	/**
	 * @see #setAlign(HorizontalAlign)
	 */
	public HorizontalAlign getAlign() {
		return align;
	}

	/**
	 * The horizontal alignment of the title. Can be one of "left", "center" and
	 * "right".
	 * <p>
	 * Defaults to: center
	 */
	public void setAlign(HorizontalAlign align) {
		this.align = align;
	}

	/**
	 * @see #setFloating(Boolean)
	 */
	public Boolean getFloating() {
		return floating;
	}

	/**
	 * When the title is floating, the plot area will not move to make space for
	 * it.
	 * <p>
	 * Defaults to: false
	 */
	public void setFloating(Boolean floating) {
		this.floating = floating;
	}

	public Number[] getMargin() {
		Number[] arr = new Number[margin.size()];
		margin.toArray(arr);
		return arr;
	}

	public void setMargin(Number... margin) {
		this.margin = new ArrayList<Number>(Arrays.asList(margin));
	}

	public void addMargin(Number margin) {
		if (this.margin == null) {
			this.margin = new ArrayList<Number>();
		}
		this.margin.add(margin);
	}

	public void removeMargin(Number margin) {
		this.margin.remove(margin);
	}

	/**
	 * @see #setStyle(Style)
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * CSS styles for the title. Use this for font styling, but use
	 * <code>align</code>, <code>x</code> and <code>y</code> for text alignment.
	 * <p>
	 * Defaults to: { "color": "#333333", "fontSize": "18px" }
	 */
	public void setStyle(Style style) {
		this.style = style;
	}

	public Title(String text) {
		this.text = text;
	}

	/**
	 * @see #setText(String)
	 */
	public String getText() {
		return text;
	}

	/**
	 * The title of the chart. To disable the title, set the <code>text</code>
	 * to <code>null</code>.
	 * <p>
	 * Defaults to: Chart title
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @see #setUseHTML(Boolean)
	 */
	public Boolean getUseHTML() {
		return useHTML;
	}

	/**
	 * Whether to <a href=
	 * "http://www.highcharts.com/docs/chart-concepts/labels-and-string-formatting#html"
	 * >use HTML</a> to render the text.
	 * <p>
	 * Defaults to: false
	 */
	public void setUseHTML(Boolean useHTML) {
		this.useHTML = useHTML;
	}

	/**
	 * @see #setVerticalAlign(VerticalAlign)
	 */
	public VerticalAlign getVerticalAlign() {
		return verticalAlign;
	}

	/**
	 * The vertical alignment of the title. Can be one of "top", "middle" and
	 * "bottom". When a value is given, the title behaves as floating.
	 * <p>
	 * Defaults to:
	 */
	public void setVerticalAlign(VerticalAlign verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	/**
	 * @see #setX(Number)
	 */
	public Number getX() {
		return x;
	}

	/**
	 * The x position of the title relative to the alignment within
	 * chart.spacingLeft and chart.spacingRight.
	 * <p>
	 * Defaults to: 0
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
	 * The y position of the title relative to the alignment within <a
	 * href="#chart.spacingTop">chart.spacingTop</a> and <a
	 * href="#chart.spacingBottom">chart.spacingBottom</a>. By default it
	 * depends on the font size.
	 */
	public void setY(Number y) {
		this.y = y;
	}
}