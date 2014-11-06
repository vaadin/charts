package com.vaadin.addon.charts.model;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 2.0
 * (CVALv2).
 * 
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 * 
 * You should have received a copy of the CVALv2 along with this program.
 * If not, see <http://vaadin.com/license/cval-2.0>.
 * #L%
 */

/**
 * 3d options
 * 
 */
public class Options3d extends AbstractConfigurationObject {

    private Number alpha;
    private Number beta;
    private Number depth;
    private Boolean enabled;
    private Frame frame;
    private Number viewDistance;

    /**
     * @see #setAlpha(Number)
     * @return alpha
     */
    public Number getAlpha() {
        return alpha;
    }

    /**
     * One of the two rotation angles for the chart. Defaults to 0.
     * 
     * @param alpha
     */
    public void setAlpha(Number alpha) {
        this.alpha = alpha;
    }

    /**
     * @see #setBeta(Number)
     * @return beta
     */
    public Number getBeta() {
        return beta;
    }

    /**
     * One of the two rotation angles for the chart. Defaults to 0.
     * 
     * @param beta
     */
    public void setBeta(Number beta) {
        this.beta = beta;
    }

    /**
     * @see #setDepth(Number)
     * @return depth
     */
    public Number getDepth() {
        return depth;
    }

    /**
     * The total depth of the chart. Defaults to 100.
     * 
     * @param depth
     */
    public void setDepth(Number depth) {
        this.depth = depth;
    }

    /**
     * @see #setEnabled(Boolean)
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Wether to render the chart using the 3D functionality. Defaults to false
     * 
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see #setFrame(Frame)
     * @return frame
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Provides the option to draw a frame around the charts by defining a
     * bottom, front and back panel.
     * 
     * @param frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * @see #setViewDistance(Number)
     * @return viewDistance
     */
    public Number getViewDistance() {
        return viewDistance;
    }

    /**
     * Defines the distance the viewer is standing in front of the chart, this
     * setting is important to calculate the perspective effect in column and
     * scatter charts. It is not used for 3D pie charts. Defaults to 100.
     * 
     * @param viewDistance
     */
    public void setViewDistance(Number viewDistance) {
        this.viewDistance = viewDistance;
    }

}
