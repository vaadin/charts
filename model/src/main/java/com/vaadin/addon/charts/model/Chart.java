package com.vaadin.addon.charts.model;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.Style;
public class Chart extends AbstractConfigurationObject {

	private static final long serialVersionUID = 1L;
	private Boolean alignTicks;
	private Object animation;
	private Color backgroundColor;
	private Color borderColor;
	private Number borderRadius;
	private Number borderWidth;
	private String className;
	private Number height;
	private Boolean ignoreHiddenSeries;
	private Boolean inverted;
	private Object margin;
	private Number marginBottom;
	private Number marginLeft;
	private Number marginRight;
	private Number marginTop;
	private Options3d options3d;
	private String panKey;
	private Boolean panning;
	private String pinchType;
	private Color plotBackgroundColor;
	private String plotBackgroundImage;
	private Color plotBorderColor;
	private Number plotBorderWidth;
	private Object plotShadow;
	private Boolean polar;
	private Boolean reflow;
	private Object renderTo;
	private ResetZoomButton resetZoomButton;
	private Color selectionMarkerFill;
	private Object shadow;
	private Boolean showAxes;
	private Number[] spacing;
	private Number spacingBottom;
	private Number spacingLeft;
	private Number spacingRight;
	private Number spacingTop;
	private Style style;
	private String type;
	private Number width;
	private String zoomType;

	public Chart() {
	}

	public Boolean getAlignTicks() {
		return alignTicks;
	}

	public void setAlignTicks(Boolean alignTicks) {
		this.alignTicks = alignTicks;
	}

	public Object getAnimation() {
		return animation;
	}

	public void setAnimation(Object animation) {
		this.animation = animation;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Number getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Number borderRadius) {
		this.borderRadius = borderRadius;
	}

	public Number getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Number borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) {
		this.height = height;
	}

	public Boolean getIgnoreHiddenSeries() {
		return ignoreHiddenSeries;
	}

	public void setIgnoreHiddenSeries(Boolean ignoreHiddenSeries) {
		this.ignoreHiddenSeries = ignoreHiddenSeries;
	}

	public Boolean getInverted() {
		return inverted;
	}

	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}

	public Object getMargin() {
		return margin;
	}

	public void setMargin(Object margin) {
		this.margin = margin;
	}

	public Number getMarginBottom() {
		return marginBottom;
	}

	public void setMarginBottom(Number marginBottom) {
		this.marginBottom = marginBottom;
	}

	public Number getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(Number marginLeft) {
		this.marginLeft = marginLeft;
	}

	public Number getMarginRight() {
		return marginRight;
	}

	public void setMarginRight(Number marginRight) {
		this.marginRight = marginRight;
	}

	public Number getMarginTop() {
		return marginTop;
	}

	public void setMarginTop(Number marginTop) {
		this.marginTop = marginTop;
	}

	public Options3d getOptions3d() {
		return options3d;
	}

	public void setOptions3d(Options3d options3d) {
		this.options3d = options3d;
	}

	public String getPanKey() {
		return panKey;
	}

	public void setPanKey(String panKey) {
		this.panKey = panKey;
	}

	public Boolean getPanning() {
		return panning;
	}

	public void setPanning(Boolean panning) {
		this.panning = panning;
	}

	public String getPinchType() {
		return pinchType;
	}

	public void setPinchType(String pinchType) {
		this.pinchType = pinchType;
	}

	public Color getPlotBackgroundColor() {
		return plotBackgroundColor;
	}

	public void setPlotBackgroundColor(Color plotBackgroundColor) {
		this.plotBackgroundColor = plotBackgroundColor;
	}

	public String getPlotBackgroundImage() {
		return plotBackgroundImage;
	}

	public void setPlotBackgroundImage(String plotBackgroundImage) {
		this.plotBackgroundImage = plotBackgroundImage;
	}

	public Color getPlotBorderColor() {
		return plotBorderColor;
	}

	public void setPlotBorderColor(Color plotBorderColor) {
		this.plotBorderColor = plotBorderColor;
	}

	public Number getPlotBorderWidth() {
		return plotBorderWidth;
	}

	public void setPlotBorderWidth(Number plotBorderWidth) {
		this.plotBorderWidth = plotBorderWidth;
	}

	public Object getPlotShadow() {
		return plotShadow;
	}

	public void setPlotShadow(Object plotShadow) {
		this.plotShadow = plotShadow;
	}

	public Boolean getPolar() {
		return polar;
	}

	public void setPolar(Boolean polar) {
		this.polar = polar;
	}

	public Boolean getReflow() {
		return reflow;
	}

	public void setReflow(Boolean reflow) {
		this.reflow = reflow;
	}

	public Object getRenderTo() {
		return renderTo;
	}

	public void setRenderTo(Object renderTo) {
		this.renderTo = renderTo;
	}

	public ResetZoomButton getResetZoomButton() {
		return resetZoomButton;
	}

	public void setResetZoomButton(ResetZoomButton resetZoomButton) {
		this.resetZoomButton = resetZoomButton;
	}

	public Color getSelectionMarkerFill() {
		return selectionMarkerFill;
	}

	public void setSelectionMarkerFill(Color selectionMarkerFill) {
		this.selectionMarkerFill = selectionMarkerFill;
	}

	public Object getShadow() {
		return shadow;
	}

	public void setShadow(Object shadow) {
		this.shadow = shadow;
	}

	public Boolean getShowAxes() {
		return showAxes;
	}

	public void setShowAxes(Boolean showAxes) {
		this.showAxes = showAxes;
	}

	public Number[] getSpacing() {
		return spacing;
	}

	public void setSpacing(Number[] spacing) {
		this.spacing = spacing;
	}

	public Number getSpacingBottom() {
		return spacingBottom;
	}

	public void setSpacingBottom(Number spacingBottom) {
		this.spacingBottom = spacingBottom;
	}

	public Number getSpacingLeft() {
		return spacingLeft;
	}

	public void setSpacingLeft(Number spacingLeft) {
		this.spacingLeft = spacingLeft;
	}

	public Number getSpacingRight() {
		return spacingRight;
	}

	public void setSpacingRight(Number spacingRight) {
		this.spacingRight = spacingRight;
	}

	public Number getSpacingTop() {
		return spacingTop;
	}

	public void setSpacingTop(Number spacingTop) {
		this.spacingTop = spacingTop;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}

	public String getZoomType() {
		return zoomType;
	}

	public void setZoomType(String zoomType) {
		this.zoomType = zoomType;
	}
}