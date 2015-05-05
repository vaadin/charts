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

import java.util.ArrayList;
import java.util.List;

import com.vaadin.addon.charts.model.style.Style;

/**
 * Options for drill down, the concept of inspecting increasingly high
 * resolution data through clicking on chart items like columns or pie slices.
 */
public class Drilldown extends AbstractConfigurationObject {

    private Style activeAxisLabelStyle;
    private Style activeDataLabelStyle;
    private Object animation;
    private DrillUpButton drillUpButton;
    private List<Series> series = new ArrayList<Series>();
    private transient Configuration configuration;

    /**
     * Adds a series configurations for the drilldown. These drilldown series
     * are hidden by default. The drilldown series is linked to the parent
     * series' point by its {@link Series#getId()}
     * 
     * @param series
     */
    void addSeries(Series series) {
        this.series.add(series);
    }

    /**
     * Sets the configuration linked to the drilldown series.
     * 
     * @param configuration
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * @see #setConfiguration(Configuration)
     * @return the {@link Configuration} that this series is linked to.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * @see #setActiveAxisLabelStyle(Style)
     * @return
     */
    public Style getActiveAxisLabelStyle() {
        return activeAxisLabelStyle;
    }

    /**
     * Additional styles to apply to the X axis label for a point that has
     * drilldown data.
     * 
     * @param activeAxisLabelStyle
     */
    public void setActiveAxisLabelStyle(Style activeAxisLabelStyle) {
        this.activeAxisLabelStyle = activeAxisLabelStyle;
    }

    /**
     * @see #setActiveDataLabelStyle(Style)
     * @return
     */
    public Style getActiveDataLabelStyle() {
        return activeDataLabelStyle;
    }

    /**
     * Additional styles to apply to the data label of a point that has
     * drilldown data.
     * 
     * @param activeDataLabelStyle
     */
    public void setActiveDataLabelStyle(Style activeDataLabelStyle) {
        this.activeDataLabelStyle = activeDataLabelStyle;
    }

    /**
     * Checks if animation is set as a Boolean and if so, returns that setting.
     * Otherwise returns <code>null</code>.
     * 
     * @see #setAnimation(Boolean)
     * @return <code>null</code> when the animation is not set as Boolean,
     *         otherwise a corresponding Boolean.
     */
    public Boolean isAnimation() {
        return animation instanceof Boolean ? (Boolean) animation : null;
    }

    /**
     * Returns current animation settings. Can be a boolean (
     * {@link #isAnimation()}), or an {@link Animation} instance.
     * 
     * @see #setAnimation(Boolean)
     * @see #setAnimation(Animation)
     * @return Current animation setting.
     */
    public Object getAnimation() {
        return animation;
    }

    /**
     * Set the animation for all drilldown animations. Animation of a drilldown
     * occurs when drilling between a column point and a column series, or a pie
     * slice and a full pie series. Drilldown can still be used between series
     * and points of different types, but animation will not occur.
     * 
     * @param animation
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * Set the animation for all drilldown animations. Animation of a drilldown
     * occurs when drilling between a column point and a column series, or a pie
     * slice and a full pie series. Drilldown can still be used between series
     * and points of different types, but animation will not occur.
     * 
     * @param animation
     */
    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    /**
     * @see Drilldown#setDrillUpButton(DrillUpButton)
     * @return
     */
    public DrillUpButton getDrillUpButton() {
        if (drillUpButton == null) {
            drillUpButton = new DrillUpButton();
        }
        return drillUpButton;
    }

    /**
     * Options for the drill up button that appears when drilling down on a
     * series. The text for the button is defined in
     * {@link Lang#setDrillUpText(String)}.
     * 
     * @param drillUpButton
     */
    public void setDrillUpButton(DrillUpButton drillUpButton) {
        this.drillUpButton = drillUpButton;
    }
}
