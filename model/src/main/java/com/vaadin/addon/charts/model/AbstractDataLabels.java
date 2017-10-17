package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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
import com.vaadin.addon.charts.model.style.Style;

public abstract class AbstractDataLabels extends AbstractConfigurationObject {

    /**
     * @see #setBackgroundColor(Color)
     */
    abstract public Color getBackgroundColor();

    /**
     * The background color or gradient for the data label.
     */
    abstract public void setBackgroundColor(Color backgroundColor);

    /**
     * @see #setBorderColor(Color)
     */
    abstract public Color getBorderColor();

    /**
     * The border color for the data label.
     */
    abstract public void setBorderColor(Color borderColor);

    /**
     * @see #setBorderRadius(Number)
     */
    abstract public Number getBorderRadius();

    /**
     * The border radius in pixels for the data label.
     */
    abstract public void setBorderRadius(Number borderRadius);

    /**
     * @see #setBorderWidth(Number)
     */
    abstract public Number getBorderWidth();

    /**
     * The border width in pixels for the data label.
     */
    abstract public void setBorderWidth(Number borderWidth);

    /**
     * @see #setColor(Color)
     */
    abstract public Color getColor();

    /**
     * The text color for the data labels.
     */
    abstract public void setColor(Color color);

    /**
     * @see #setCrop(Boolean)
     */
    abstract public Boolean getCrop();

    /**
     * Whether to hide data labels that are outside the plot area. By default,
     * the data label is moved inside the plot area according to the overflow option.
     */
    abstract public void setCrop(Boolean crop);

    /**
     * @see #setDefer(Boolean)
     */
    abstract public Boolean getDefer();
    /**
     * Whether to defer displaying the data labels until the initial series
     * animation has finished.
     */
    abstract public void setDefer(Boolean defer);

    /**
     * @see #setEnabled(Boolean)
     */
    abstract public Boolean getEnabled();

    /**
     * Enable or disable the data labels.
     */
    abstract public void setEnabled(Boolean enabled);

    /**
     * @see #setFormat(String)
     */
    abstract public String getFormat();

    /**
     * A format string for the data label. Available variables are the same
     * as for <code>formatter</code>.
     */
    abstract public void setFormat(String format);

    /**
     * @see #setFormatter(String)
     */
    abstract public String getFormatter();

    /**
     * Callback JavaScript function to format the data label.
     * Note that if a <code>format</code> is defined, the format
     * takes precedence and the formatter is ignored.
     */
    abstract public void setFormatter(String _fn_formatter);

    /**
     * @see #setInside(Boolean)
     */
    abstract public Boolean getInside();

    /**
     * For points with an extent, like columns, whether to align the data label
     * inside the box or to the actual value point.
     */
    abstract public void setInside(Boolean inside);

    /**
     * @see #setOverflow(String)
     */
    abstract public String getOverflow();

    /**
     * How to handle data labels that flow outside the plot area. The default is
     * <code>justify</code>, which aligns them inside the plot area. For columns
     * and bars, this means it will be moved inside the bar. To display data
     * labels outside the plot area, set <code>crop</code> to <code>false</code>
     * and <code>overflow</code> to <code>"none"</code>.
     */
    abstract public void setOverflow(String overflow);

    /**
     * @see #setPadding(Number)
     */
    abstract public Number getPadding();

    /**
     * When either the <code>borderWidth</code> or the
     * <code>backgroundColor</code> is set, this is the padding within the box.
     */
    abstract public void setPadding(Number padding);

    /**
     * @see #setRotation(Number)
     */
    abstract public Number getRotation();

    /**
     * Text rotation in degrees. Note that due to a more complex structure,
     * backgrounds, borders and padding will be lost on a rotated data label.
     */
    abstract public void setRotation(Number rotation);

    /**
     * @see #setShadow(Boolean)
     */
    abstract public Boolean getShadow();

    /**
     * The shadow of the box.
     */
    abstract public void setShadow(Boolean shadow);

    /**
     * @see #setShape(Shape)
     */
    abstract public Shape getShape();

    /**
     * The name of a symbol to use for the border around the label. Symbols are
     * predefined functions on the Renderer object.
     */
    abstract public void setShape(Shape shape);

    /**
     * @see #setStyle(Style)
     */
    abstract public Style getStyle();

    /**
     * Styles for the label.
     */
    abstract public void setStyle(Style style);

    /**
     * @see #setUseHTML(Boolean)
     */
    abstract public Boolean getUseHTML();

    /**
     * Whether to use HTML to render the labels.
     */
    abstract public void setUseHTML(Boolean useHTML);

    /**
     * @see #setVerticalAlign(VerticalAlign)
     */
    abstract public VerticalAlign getVerticalAlign();

    /**
     * The vertical alignment of a data label.
     */
    abstract public void setVerticalAlign(VerticalAlign verticalAlign);
}


