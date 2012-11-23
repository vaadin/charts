package com.vaadin.addon.charts.model.style;

import java.util.ArrayList;
import java.util.List;

/**
 * Class providing gradient colors
 */
public class GradientColor implements Color {

    public static class RadialGradient {
        final Number cx;
        final Number cy;
        final Number r;

        public RadialGradient(double centerX, double centerY, double radius) {
            cx = centerX;
            cy = centerY;
            r = radius;
        }
    }

    public static class LinearGradient {
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
     * Return the linearGradient
     * 
     * @return
     */
    public LinearGradient getLinearGradient() {
        return linearGradient;
    }

    /**
     * Return the radialGradient
     * 
     * @return
     */
    public RadialGradient getRadialGradient() {
        return radialGradient;
    }

    /**
     * Currently for internal use only, make stops typed if opening api is
     * required.
     */
    List<List<Object>> getStops() {
        return stops;
    }

    /**
     * Adds a color stop to gradient
     * 
     * @param d
     *            relative point of the color stop, between 0 and 1
     * @param c
     *            the color for color stop
     */
    public void addColorStop(double d, SolidColor c) {
        if (stops == null) {
            stops = new ArrayList<List<Object>>();
        }
        ArrayList<Object> colorStop = new ArrayList<Object>(2);
        colorStop.add(d);
        colorStop.add(c);
        stops.add(colorStop);
    }

    /**
     * Creates a new linear gradient between given points.
     * 
     * @param startX
     *            relative start point in x axis, 0-1
     * @param startY
     *            relative start point in y axis, 0-1
     * @param endX
     *            relative end point in x axis, 0-1
     * @param endY
     *            relative end point in y axis, 0-1
     * @return New linear gradient color
     */
    public static GradientColor createLinear(double startX, double startY,
            double endX, double endY) {
        GradientColor ret = new GradientColor();
        ret.linearGradient = new LinearGradient(startX, startY, endX, endY);
        return ret;
    }

    /**
     * Create radial gradient color
     * 
     * @param centerX
     *            Center X coordinate
     * @param centerY
     *            Center Y coordinate
     * @param radius
     *            Radius
     * @return Radial gradient color
     */
    public static GradientColor createRadial(double centerX, double centerY,
            double radius) {
        GradientColor ret = new GradientColor();
        ret.radialGradient = new RadialGradient(centerX, centerY, radius);
        return ret;
    }
}
