package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2014 Vaadin Ltd
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

import java.util.Date;

/**
 * DataSeriesItem that can hold also Z value. Used in e.g. bubble charts.
 */
public class FlagItem extends DataSeriesItem {

    private String title;

    /**
     * Constructs an item with X and Title values
     *
     * @param x
     * @param title
     */
    public FlagItem(Number x, String title) {
        setX(x);
        setTitle(title);
    }

    /**
     * Constructs an item with X and Title values
     *
     * @param date
     * @param title
     */
    public FlagItem(Date date, String title) {
        setX(date);
        setTitle(title);
    }

    /**
     * Sets the title of the flag
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
        makeCustomized();
    }

    /**
     * @return the title of the flag
     */
    public String getTitle() {
        return title;
    }

}
