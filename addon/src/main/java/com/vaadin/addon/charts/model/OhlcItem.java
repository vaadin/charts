/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model;

import java.util.Date;

/**
 * A DataSeriesItem implementation suitable for <a
 * https://en.wikipedia.org/wiki/Open-high-low-close_chart">OHLC charts</a>.
 * OHLC charts visualize well financial data.
 * 
 * @see PlotOptionsOhlc
 * @see PlotOptionsCandlestick
 * 
 */
public class OhlcItem extends DataSeriesItem {
    // high/low already defined in DataSeriesItem

    private Number open;
    private Number close;

    /**
     * Constructs an empty ohlc data item
     */
    public OhlcItem() {
    }

    /**
     * Constructs an ohlc data item for give open, high, low and close values
     *
     * @param x
     * @param open
     * @param high
     * @param low
     * @param close
     */
    public OhlcItem(Number x, Number open, Number high, Number low, Number close) {
        this();
        setX(x);
        setOpen(open);
        setLow(low);
        setHigh(high);
        setClose(close);
    }

    /**
     * Constructs an ohlc data item for give open, high, low and close values
     *
     * @param date
     * @param open
     * @param high
     * @param low
     * @param close
     */
    public OhlcItem(Date date, Number open, Number high, Number low,
            Number close) {
        this();
        setX(date);
        setOpen(open);
        setLow(low);
        setHigh(high);
        setClose(close);
    }

    /**
     * @see #setOpen(Number)
     */
    public Number getOpen() {
        return open;
    }

    /**
     * Sets the open value of the OHLC item
     *
     * @param open
     */
    public void setOpen(Number open) {
        this.open = open;
    }

    /**
     * @see #setClose(Number)
     */
    public Number getClose() {
        return close;
    }

    /**
     * Sets the close value of the OHLC item
     *
     * @param close
     */
    public void setClose(Number close) {
        this.close = close;
    }
}
