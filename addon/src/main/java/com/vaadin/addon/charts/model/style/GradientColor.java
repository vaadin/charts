package com.vaadin.addon.charts.model.style;

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class providing gradient colors
 */
public class GradientColor implements Color {

    public static class RadialGradient implements Serializable {
        final Number cx;
        final Number cy;
        final Number r;

        public RadialGradient(double centerX, double centerY, double radius) {
            cx = centerX;
            cy = centerY;
            r = radius;
        }
    }

    public static class LinearGradient implements Serializable {
        final Number x1;
        final Number y1;
        final Number x2;
        final Number y2;

        public LinearGradient(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private List<List<Object>> stops;

    private RadialGradient radialGradient;

    private LinearGradient linearGradient;

    private GradientColor() {

    }

    /**
     * @return The linear gradient
     */
    public LinearGradient getLinearGradient() {
        return linearGradient;
    }

    /**
     * @return The radial gradient
     */
    public RadialGradient getRadialGradient() {
        return radialGradient;
    }

    /**
     * Currently for internal use only, make stops typed if opening API is
     * required.
     */
    List<List<Object>> getStops() {
        return stops;
    }

    /**
     * Adds a color stop to the gradient
     * 
     * @param d
     *            The relative point of the color stop, between 0 and 1
     * @param color
     *            The color at the point defined by d
     */
    public void addColorStop(double d, SolidColor color) {
        if (stops == null) {
            stops = new ArrayList<List<Object>>();
        }
        ArrayList<Object> colorStop = new ArrayList<Object>(2);
        colorStop.add(d);
        colorStop.add(color);
        stops.add(colorStop);
    }

    /**
     * Creates a new linear gradient between two given points. Use
     * {@link #addColorStop(double, SolidColor)} to define the colors.
     * 
     * @param startX
     *            The relative start point on the X-axis, 0..1
     * @param startY
     *            The relative start point on the Y-axis, 0..1
     * @param endX
     *            The relative end point on the X-axis, 0..1
     * @param endY
     *            The relative end point on the Y-axis, 0..1
     * @return A new linear gradient color
     */
    public static GradientColor createLinear(double startX, double startY,
            double endX, double endY) {
        GradientColor ret = new GradientColor();
        ret.linearGradient = new LinearGradient(startX, startY, endX, endY);
        return ret;
    }

    /**
     * Creates a radial gradient color at a specified point with the given
     * radius. Use {@link #addColorStop(double, SolidColor)} to define the
     * colors.
     * 
     * @param centerX
     *            The X coordinate of the center
     * @param centerY
     *            The Y coordinate of the center
     * @param radius
     *            The radius
     * @return A new radial gradient
     */
    public static GradientColor createRadial(double centerX, double centerY,
            double radius) {
        GradientColor ret = new GradientColor();
        ret.radialGradient = new RadialGradient(centerX, centerY, radius);
        return ret;
    }
}
