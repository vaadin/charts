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

import com.vaadin.addon.charts.model.style.Color;

public abstract class PyramidOptions extends AbstractPlotOptions {

    /**
     * @see #setAllowPointSelect(Boolean)
     */
    public abstract Boolean getAllowPointSelect();

    /**
     * Allow this series' points to be selected by clicking on the markers, bars
     * or pie slices.
     */
    public abstract void setAllowPointSelect(Boolean allowPointSelect);

    /**
     * @see #setBorderColor(Color)
     */
    public abstract Color getBorderColor();

    /**
     * The color of the border surrounding each slice. When <code>null</code>,
     * the border takes the same color as the slice fill. This can be used
     * together with a <code>borderWidth</code> to fill drawing gaps created by
     * antialiazing artefacts in borderless pies.
     */
    public abstract void setBorderColor(Color borderColor);

    /**
     * @see #setBorderWidth(Number)
     */
    public abstract Number getBorderWidth();

    /**
     * <p>
     * The width of the border surrounding each slice.
     * </p>
     * 
     * <p>
     * When setting the border width to 0, there may be small gaps between the
     * slices due to SVG antialiasing artefacts. To work around this, keep the
     * border width at 0.5 or 1, but set the <code>borderColor</code> to
     * <code>null</code> instead.
     * </p>
     */
    public abstract void setBorderWidth(Number borderWidth);

    /**
     * The center of the series. By default, it is centered in the middle of the
     * plot area, so it fills the plot area height.
     */
    public abstract void setCenter(String[] center);

    /**
     * @see #setColors(Color...)
     */
    public abstract Color[] getColors();

    /**
     * A series specific or series type specific color set to use instead of the
     * theme colors.
     */
    public abstract void setColors(Color... colors);

    /**
     * Adds color to the colors array
     * 
     * @param color
     *            to add
     * @see #setColors(Color...)
     */
    public abstract void addColor(Color color);

    /**
     * Removes first occurrence of color in colors array
     * 
     * @param color
     *            to remove
     * @see #setColors(Color...)
     */
    public abstract void removeColor(Color color);

    /**
     * @see #setCursor(Cursor)
     */
    public abstract Cursor getCursor();

    /**
     * You can set the cursor to "pointer" if you have click events attached to
     * the series, to signal to the user that the points and lines can be
     * clicked.
     */
    public abstract void setCursor(Cursor cursor);

    /**
     * @see #setDataLabels(DataLabelsFunnel)
     */
    public abstract DataLabelsFunnel getDataLabels();

    /**
     * Specific data labels configuration for a series type
     * 
     * @param dataLabels
     */
    public abstract void setDataLabels(DataLabelsFunnel dataLabels);

    /**
     * @see #setDepth(Number)
     */
    public abstract Number getDepth();

    /**
     * The thickness of a 3D pie.
     */
    public abstract void setDepth(Number depth);

    /**
     * @see #setEnableMouseTracking(Boolean)
     */
    public abstract Boolean getEnableMouseTracking();

    /**
     * Enable or disable the mouse tracking for a specific series. This includes
     * point tooltips and click events on graphs and points. For large datasets
     * it improves performance.
     */
    public abstract void setEnableMouseTracking(Boolean enableMouseTracking);

    /**
     * @see #setGetExtremesFromAll(Boolean)
     */
    public abstract Boolean getGetExtremesFromAll();

    /**
     * Whether to use the Y extremes of the total chart width or only the zoomed
     * area when zooming in on parts of the X axis. By default, the Y axis
     * adjusts to the min and max of the visible data. Cartesian series only.
     */
    public abstract void setGetExtremesFromAll(Boolean getExtremesFromAll);

    /**
     * @see #setHeight(String)
     */
    public abstract String getHeight();

    /**
     * Sets the height using String presentation. String presentation is similar
     * to what is used in Cascading Style Sheets. Size can be pixels or
     * percentage, otherwise IllegalArgumentException is thrown. The empty
     * string ("") or null will unset the height and set the units to pixels.
     * 
     * @param height
     *            CSS style string representation
     */
    public abstract void setHeight(String height);

    /**
     * @see #setKeys(String...)
     */
    public abstract String[] getKeys();

    /**
     * An array specifying which option maps to which key in the data point
     * array. This makes it convenient to work with unstructured data arrays
     * from different sources.
     */
    public abstract void setKeys(String... keys);

    /**
     * Adds key to the keys array
     * 
     * @param key
     *            to add
     * @see #setKeys(String...)
     */
    public abstract void addKey(String key);

    /**
     * Removes first occurrence of key in keys array
     * 
     * @param key
     *            to remove
     * @see #setKeys(String...)
     */
    public abstract void removeKey(String key);

    /**
     * @see #setLinkedTo(String)
     */
    public abstract String getLinkedTo();

    /**
     * The ID of another series to link to. Additionally, the value can be
     * ":previous" to link to the previous series. When two series are linked,
     * only the first one appears in the legend. Toggling the visibility of this
     * also toggles the linked series.
     */
    public abstract void setLinkedTo(String linkedTo);

    /**
     * @see #setMinSize(Number)
     */
    public abstract Number getMinSize();

    /**
     * The minimum size for a pie in response to auto margins. The pie will try
     * to shrink to make room for data labels in side the plot area, but only to
     * this size.
     */
    public abstract void setMinSize(Number minSize);

    /**
     * @see #setReversed(Boolean)
     */
    public abstract Boolean getReversed();

    /**
     * The pyramid is reversed by default, as opposed to the funnel, which
     * shares the layout engine, and is not reversed.
     */
    public abstract void setReversed(Boolean reversed);

    /**
     * @see #setSelected(Boolean)
     */
    public abstract Boolean getSelected();

    /**
     * Whether to select the series initially. If <code>showCheckbox</code> is
     * true, the checkbox next to the series name will be checked for a selected
     * series.
     */
    public abstract void setSelected(Boolean selected);

    /**
     * @see #setShadow(Boolean)
     */
    public abstract Boolean getShadow();

    /**
     * Whether to apply a drop shadow to the graph line.
     */
    public abstract void setShadow(Boolean shadow);

    /**
     * @see #setShowInLegend(Boolean)
     */
    public abstract Boolean getShowInLegend();

    /**
     * Whether to display this particular series or series type in the legend.
     */
    public abstract void setShowInLegend(Boolean showInLegend);

    /**
     * @see #setSlicedOffset(Number)
     */
    public abstract Number getSlicedOffset();

    /**
     * If a point is sliced, moved out from the center, how many pixels should
     * it be moved?.
     */
    public abstract void setSlicedOffset(Number slicedOffset);

    /**
     * @see #setStates(States)
     */
    public abstract States getStates();

    /**
     * A wrapper object for all the series options in specific states.
     */
    public abstract void setStates(States states);

    /**
     * @see #setStickyTracking(Boolean)
     */
    public abstract Boolean getStickyTracking();

    /**
     * Sticky tracking of mouse events. When true, the <code>mouseOut</code>
     * event on a series isn't triggered until the mouse moves over another
     * series, or out of the plot area. When false, the <code>mouseOut</code>
     * event on a series is triggered when the mouse leaves the area around the
     * series' graph or markers. This also implies the tooltip. When
     * <code>stickyTracking</code> is false and <code>tooltip.shared</code> is
     * false, the tooltip will be hidden when moving the mouse between series.
     */
    public abstract void setStickyTracking(Boolean stickyTracking);

    /**
     * @see #setTooltip(SeriesTooltip)
     */
    public abstract SeriesTooltip getTooltip();

    /**
     * A configuration object for the tooltip rendering of each single series.
     */
    public abstract void setTooltip(SeriesTooltip tooltip);

    /**
     * @see #setVisible(Boolean)
     */
    public abstract Boolean getVisible();

    /**
     * Set the initial visibility of the series.
     */
    public abstract void setVisible(Boolean visible);

    /**
     * @see #setWidth(String)
     */
    public abstract String getWidth();

    /**
     * Sets the width using String presentation. String presentation is similar
     * to what is used in Cascading Style Sheets. Size can be pixels or
     * percentage, otherwise IllegalArgumentException is thrown. The empty
     * string ("") or null will unset the height and set the units to pixels.
     * 
     * @param width
     *            CSS style string representation
     */
    public abstract void setWidth(String width);

    /**
     * @see #setZoneAxis(String)
     */
    public abstract String getZoneAxis();

    /**
     * Defines the Axis on which the zones are applied.
     */
    public abstract void setZoneAxis(String zoneAxis);

    /**
     * @see #setZones(Zones...)
     */
    public abstract Zones[] getZones();

    /**
     * An array defining zones within a series. Zones can be applied to the X
     * axis, Y axis or Z axis for bubbles, according to the
     * <code>zoneAxis</code> option.
     */
    public abstract void setZones(Zones... zones);

    /**
     * Adds zone to the zones array
     * 
     * @param zone
     *            to add
     * @see #setZones(Zones...)
     */
    public abstract void addZone(Zones zone);

    /**
     * Removes first occurrence of zone in zones array
     * 
     * @param zone
     *            to remove
     * @see #setZones(Zones...)
     */
    public abstract void removeZone(Zones zone);

    public abstract void setCenter(String x, String y);

    public abstract String[] getCenter();
}