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

public class Frame extends AbstractConfigurationObject {

    private FramePanel back;
    private FramePanel bottom;
    private FramePanel side;

    /**
     * @see #setBack(FramePanel)
     * @return back
     */
    public FramePanel getBack() {
        return back;
    }

    /**
     * The back panel of the frame around a 3D chart
     * 
     * @param back
     */
    public void setBack(FramePanel back) {
        this.back = back;
    }

    /**
     * @see #setBottom(FramePanel)
     * @return bottom
     */
    public FramePanel getBottom() {
        return bottom;
    }

    /**
     * The bottom panel of the frame around a 3D chart
     * 
     * @param bottom
     */
    public void setBottom(FramePanel bottom) {
        this.bottom = bottom;
    }

    /**
     * @see #setSide(FramePanel)
     * @return side
     */
    public FramePanel getSide() {
        return side;
    }

    /**
     * The side panel of the frame around a 3D chart
     * 
     * @param side
     */
    public void setSide(FramePanel side) {
        this.side = side;
    }

}
