/*
 * Copyright 2008-2009 Oliver Zoran
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vaadin.addon.timeline.gwt.canvas.client;

/*
 * %%Ignore-License
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FocusWidget;
import com.vaadin.addon.timeline.gwt.canvas.client.impl.CanvasImpl;

/**
 * The <code>Canvas</code> is a widget that you use to draw arbitrary graphics.
 * <p>
 * When you want to draw a shape, you set the current path to that shape and
 * then paint the shape by stroking, filling, or both stroking and filling.
 * Stroking is the process of painting a line along the current path. Filling is
 * the process of painting the area contained within the path.
 * <p>
 * You use paths to draw both simple shapes (for example, lines, circles, or
 * rectangles) and complex shapes (such as the silhouette of a mountain range)
 * in a canvas. You can use a path to both draw the outline of a shape and fill
 * the inside of a shape. Prior to building the path, you can define properties
 * such as fillStyle or strokeStyle that can be used by drawing primitives to
 * fill or stroke the path.
 * <p>
 * When you close the path, the canvas connects the end of the last line segment
 * to the start of the first segment and terminates the current subpath. If you
 * don’t close the path by calling closePath before painting, the path is
 * implicitly closed for you by drawing primitives that fill or clip (but it is
 * not closed for stroking).
 * 
 * @see http://developer.mozilla.org/en/docs/Canvas_tutorial
 */
public class Canvas extends FocusWidget {

    // ///////////////////////////////////////////////////////////////
    // STATIC CONSTANTS
    // ///////////////////////////////////////////////////////////////

    /**
     * Use this constant as a parameter for the
     * {@link #setGlobalCompositeOperation(String)} method.
     */
    public final static String SOURCE_OVER = "source-over";

    /**
     * Use this constant as a parameter for the
     * {@link #setGlobalCompositeOperation(String)} method.
     */
    public final static String DESTINATION_OVER = "destination-over";

    /**
     * Use this constant as a parameter for the {@link #setLineCap(String)}
     * method.
     */
    public final static String BUTT = "butt";

    /**
     * Use this constant as a parameter for the {@link #setLineCap(String)}
     * method.
     */
    public final static String SQUARE = "square";

    /**
     * Use this constant either as a parameter for the
     * {@link #setLineCap(String)} and the {@link #setLineJoin(String)} method.
     */
    public final static String ROUND = "round";

    /**
     * Use this constant as a parameter for the {@link #setLineJoin(String)}
     * method.
     */
    public final static String BEVEL = "bevel";

    /**
     * Use this constant as a parameter for the {@link #setLineJoin(String)}
     * method.
     */
    public final static String MITER = "miter";

    /**
     * Use this constant as a parameter for the
     * {@link #setBackgroundColor(String)} method.
     */
    public final static String TRANSPARENT = "";

    // ///////////////////////////////////////////////////////////////
    // PRIVATE MEMBERS
    // ///////////////////////////////////////////////////////////////

    /**
     * Let GWT create our browser specific canvas implementation.
     */
    private CanvasImpl impl = (CanvasImpl) GWT.create(CanvasImpl.class);

    /**
     * The current fill gradient, if any.
     */
    private Gradient fillGradient;

    // TODO investigate further
    // private boolean preventSelection = false;

    // ///////////////////////////////////////////////////////////////
    // CONSTRUCTORS AND PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////

    /**
     * Creates a canvas widget with an initial drawing area of 300x150 pixels.
     * <p>
     * It's background color is not set by default (it is transparent). The
     * default CSS class selector is named <code>gwt-Canvas</code>. It may be
     * used to apply additional style rules to the canvas widget.
     */
    public Canvas() {
        this(300, 150);
    }

    /**
     * Creates a canvas widget with the specified drawing area size.
     * <p>
     * It's background color is not set by default (it is transparent). The
     * default CSS class selector is named <code>gwt-Canvas</code>. It may be
     * used to apply additional style rules to the canvas widget.
     * 
     * @param width
     *            the width of the canvas drawing area in pixels
     * @param height
     *            the height of the canvas drawing area in pixels
     */
    public Canvas(int width, int height) {
        this(width + "px", height + "px");
    }

    /**
     * Creates a canvas widget with the specified drawing area size.
     * <p>
     * It's background color is not set by default (it is transparent). The
     * default CSS class selector is named <code>gwt-Canvas</code>. It may be
     * used to apply additional style rules to the canvas widget.
     * 
     * @param width
     *            the width of the canvas drawing area in CSS units
     * @param height
     *            the height of the canvas drawing area in CSS units
     */
    public Canvas(String width, String height) {
        if (width == null || height == null) {
            throw new IllegalArgumentException();
        }
        setElement(DOM.createElement("canvas"));
        impl.init(getElement());
        setStyleName("gwt-Canvas");
        setBackgroundColor(TRANSPARENT);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Specifies the width of the drawing area.
     * <p>
     * You can specify this value as a fixed number of pixels only.
     * 
     * @param width
     *            the width of the drawing area
     */
    public void setWidth(int width) {
        setWidth(width + "px");
    }

    /**
     * Specifies the height of the drawing area.
     * <p>
     * You can specify this value as a fixed number of pixels only.
     * 
     * @param height
     *            the height of the drawing area
     */
    public void setHeight(int height) {
        setHeight(height + "px");
    }

    /**
     * Sets the object's width. This width does not include decorations such as
     * border, margin, and padding.
     * 
     * @param width
     *            the width of the drawing area, in CSS units (e.g. "100px")
     */
    @Override
    public void setWidth(String width) {
        if (width == null) {
            throw new IllegalArgumentException();
        }
        int w = 0;
        try {
            w = Integer.parseInt(width);
        } catch (NumberFormatException e) {
            impl.setWidth(width);
            return;
        }
        impl.setWidth(w + "px");
    }

    /**
     * Sets the object's height. This height does not include decorations such
     * as border, margin, and padding.
     * 
     * @param height
     *            the height of the drawing area, in CSS units (e.g. "100px")
     */
    @Override
    public void setHeight(String height) {
        if (height == null) {
            throw new IllegalArgumentException();
        }
        int h = 0;
        try {
            h = Integer.parseInt(height);
        } catch (NumberFormatException e) {
            impl.setHeight(height);
            return;
        }
        impl.setHeight(h + "px");
    }

    /**
     * Returns the width of the canvas drawing area in pixels.
     * <p>
     * Unlike {@link #getOffsetWidth()} the returned value represents the width
     * of the actual drawing area, excluding decorations such as border, margin,
     * and padding.
     * 
     * @return the width of the drawing area
     */
    public int getWidth() {
        // TODO return (width - padding - border - margin) in IE quirks mode
        // @see http://de.selfhtml.org/css/formate/box_modell.htm#top
        return getOffsetWidth();
    }

    /**
     * Returns the height of the canvas drawing area in pixels.
     * <p>
     * Unlike {@link #getOffsetHeight()} the returned value represents the
     * height of the actual drawing area, excluding decorations such as border,
     * margin, and padding.
     * 
     * @return the height of the drawing area
     */
    public int getHeight() {
        // TODO return (height - padding - border - margin) in IE quirks mode
        // @see http://de.selfhtml.org/css/formate/box_modell.htm#top
        return getOffsetHeight();
    }

    /**
     * Sets the background color of the canvas widget.
     * <p>
     * You can set it in several different ways depending on the color space you
     * intend to use. For web-safe colors, pass a web color specification string
     * of the form <code>"#RRGGBB"</code>, which represents an RGB color using
     * hexadecimal numbers. If you want to make the background transparent,
     * simply pass <code>Canvas.TRANSPARENT</code> as a parameter.
     * <p>
     * The widget's background color is <b>not</b> part of the drawing state.
     * 
     * @param color
     *            <code>"#RRGGBB"</code> to specify a color or
     *            <code>Canvas.TRANSPARENT</code> if a transparent background is
     *            needed
     */
    public void setBackgroundColor(String color) {
        if (color == null) {
            throw new IllegalArgumentException();
        }
        color = color.trim();
        // remove alpha value if present due to browser inconsistencies
        if (color.startsWith("rgba(")) {
            int end = color.indexOf(")", 12);
            if (end > -1) {
                String[] guts = color.substring(5, end).split(",");
                if (guts.length >= 3) {
                    color = "rgb(" + guts[0] + "," + guts[1] + "," + guts[2]
                            + ")";
                }
            }
        }
        impl.setBackgroundColor(color);
    }

    /**
     * Returns the current background color of the canvas.
     * <p>
     * If the canvas background is transparent, <code>Canvas.TRANSPARENT</code>
     * will be returned.
     * 
     * @return the current background color or <code>Canvas.TRANSPARENT</code>
     */
    public String getBackgroundColor() {
        return impl.getBackgroundColor();
    }

    // TODO investigate further
    // /**
    // * Specifies the browsers selection behavior on mouse events.
    // * <p>
    // * If you press the mouse button on the canvas surface and move the cursor
    // * outside the canvas while holding the button down, the browser usually
    // * selects whatever comes underneath the mouse cursor. If this behavior is
    // * not wanted, it can be suppressed by passing <code>true</code>, and be
    // * restored to it's default by passing <code>false</code> as a parameter.
    // *
    // * @param value
    // */
    // public void preventSelection(boolean value) {
    // preventSelection = value;
    // }

    // TODO investigate further
    // /**
    // * Returns <code>true</code> if selections are prevented on mouse events,
    // * or <code>false</code> if they are not prevented (the default behavior).
    // *
    // * @return
    // */
    // public boolean isSelectionPrevented() {
    // return preventSelection;
    // }

    // ///////////////////////////////////////////////////////////////
    // CANVAS STATE METHODS
    // ///////////////////////////////////////////////////////////////

    /**
     * Restores the current graphics state to the state most recently saved.
     * <p>
     * If you wish to save the settings of the current drawing environment, that
     * is, the current graphics state, you can call the {@link #save()} method.
     * When you call {@link #save()}, the canvas saves the current graphics
     * state to the top of the graphics state stack.
     * <p>
     * To restore your drawing environment to a previously saved state, you can
     * use this method. When you call {@link #restore()}, the canvas removes the
     * most recently saved graphics state from the top of the stack and uses
     * that state’s saved settings for the current graphics state.
     */
    public void restore() {
        impl.restore();
    }

    /**
     * Saves a copy of the current graphics state.
     * <p>
     * The graphics state contains data describing the current drawing
     * environment. Methods that draw to the canvas use the graphics state
     * settings to determine how to render their results.
     * <p>
     * Each canvas maintains a stack of graphics states. If you wish to save the
     * settings of the current drawing environment, that is, the current
     * graphics state, you can call the {@link #save()} method. When you call
     * {@link #save()}, the canvas object saves the current graphics state to
     * the top of the graphics state stack.
     * <p>
     * To restore your drawing environment to a previously saved state, you can
     * use the {@link #restore()} method. When you call {@link #restore()}, the
     * canvas removes the most recently saved graphics state from the top of the
     * stack and uses that state’s saved settings for the current graphics
     * state.
     * <p>
     * Note that not all aspects of the current drawing environment are elements
     * of the graphics state. For example, the current path is not considered
     * part of the graphics state and is therefore not saved when you call this
     * method.
     */
    public void save() {
        impl.save();
    }

    /**
     * Rotates the user coordinate system of the canvas.
     * <p>
     * The current transformation matrix (CTM) specifies the mapping from
     * device-independent user space coordinates to a device space. By modifying
     * the CTM, you can modify (scale, translate, rotate) the objects you draw.
     * It’s important to note the order of events necessary to transform an
     * object in a graphics context. Prior to drawing the object, you must first
     * transform the coordinate space of the context (by calling this method),
     * and then draw the object.
     * <p>
     * For example, to rotate an image, you must call this method to rotate the
     * coordinate space of the context before drawing the image. When you draw
     * the image, the canvas draws to the window using the rotated coordinate
     * system. You specify both the magnitude and direction of the rotation by
     * specifying an angle of adjustment in radians.
     * <p>
     * To restore the previous coordinate space, you must save the graphics
     * state before modifying the CTM, and restore the graphics state after
     * drawing.
     * 
     * @param angle
     *            specifies the amount of coordinate-space rotation and is
     *            measured in radians
     */
    public void rotate(double angle) {
        impl.rotate(angle);
    }

    /**
     * Changes the scale of the canvas coordinate system.
     * <p>
     * The current transformation matrix (CTM) specifies the mapping from device
     * independent user space coordinates to a device space. By modifying the
     * CTM, you can modify (scale, translate, rotate) the objects you draw. It
     * is important to note the order of events necessary to transform an object
     * in a graphics context. Prior to drawing the object, you must first
     * transform the coordinate space of the context (by calling this method),
     * and then draw the object.
     * <p>
     * Scaling operations modify the x- and y-coordinates by a given scaling
     * factor. The magnitude of the x and y factors governs whether the new
     * coordinates are larger or smaller than the original. For example,
     * specifying the value 2.0 for the <code>x</code> parameter causes
     * subsequently drawn objects to appear at twice their specified width. In
     * addition, by making the x factor negative, you can flip the coordinates
     * about the y-axis; similarly, you can flip coordinates about the x-axis by
     * making the y factor negative.
     * <p>
     * To restore the previous coordinate space, you must save the graphics
     * state before modifying the CTM, and restore the graphics state after
     * drawing.
     * 
     * @param x
     *            contains a double value with the x-axis scale factor
     * @param y
     *            contains a double value with the y-axis scale factor
     */
    public void scale(double x, double y) {
        impl.scale(x, y);
    }

    /**
     * Changes the origin of the canvas coordinate system.
     * <p>
     * The current transformation matrix (CTM) specifies the mapping from
     * device-independent user space coordinates to a device space. By modifying
     * the CTM, you can modify (scale, translate, rotate) the objects you draw.
     * It’s important to note the order of events necessary to transform an
     * object in a graphics context. Prior to drawing the object, you must first
     * transform the coordinate space of the page (by calling this method), and
     * then draw the object.
     * <p>
     * This method displaces the x- and y-axes (thus, the origin) of the
     * coordinate system by the values you specify. When you draw into this
     * adjusted coordinate space, the x- and y-coordinates of your drawing are
     * also displaced.
     * <p>
     * To restore the previous coordinate space, you must save the graphics
     * state before modifying the CTM, and restore the graphics state after
     * drawing.
     * 
     * @param x
     *            contains a double value with the x-axis translation value
     * @param y
     *            contains a double value with the y-axis translation value
     */
    public void translate(double x, double y) {
        impl.translate(x, y);
    }

    // ///////////////////////////////////////////////////////////////
    // WORKING WITH PATHS
    // ///////////////////////////////////////////////////////////////

    /**
     * Adds an arc of a circle to the current subpath.
     * <p>
     * The arc is built based on the circle whose origin and radius are
     * specified by the <code>x</code>, <code>y</code>, and <code>radius</code>
     * parameters. The <code>startAngle</code> parameter specifies the angle of
     * the starting point of the arc, measured in radians from the positive
     * x-axis. The <code>endAngle</code> parameter specifies the angle of the
     * endpoint of the arc, measured in radians from the positive x-axis.
     * Specify <code>false</code> for the <code>anticlockwise</code> parameter
     * to draw the arc in a clockwise direction; otherwise specify
     * <code>true</code>.
     * <p>
     * If the current path already contains a subpath, the canvas appends a
     * straight line segment from the current point to the starting point of the
     * arc. If the current path is empty, the canvas creates a new subpath for
     * the arc and does not add an initial line segment. After adding the arc,
     * the current point is set to the endpoint of the arc.
     * 
     * @param x
     * @param y
     * @param radius
     * @param startAngle
     * @param endAngle
     * @param anticlockwise
     */
    public void arc(double x, double y, double radius, double startAngle,
            double endAngle, boolean antiClockwise) {
        impl.arc(x, y, radius, startAngle, endAngle, antiClockwise);
    }

    /**
     * Appends a cubic Bézier curve to the current path.
     * <p>
     * A cubic curve segment has a start point, two control points, and an
     * endpoint. The start point is the current endpoint of the open path. The
     * <code>cp1x</code>, <code>cp1y</code>, <code>cp2x</code>, and
     * <code>cp2y</code> parameters specify the two control points for the path.
     * The <code>x</code> and <code>y</code> parameters specify the new endpoint
     * for the path. After adding the segment, the current point is reset from
     * the beginning of the new segment to the endpoint of that segment.
     * 
     * @param cp1x
     * @param cp1y
     * @param cp2x
     * @param cp2y
     * @param x
     * @param y
     */
    public void cubicCurveTo(double cp1x, double cp1y, double cp2x,
            double cp2y, double x, double y) {
        impl.cubicCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
    }

    /**
     * Appends a quadratic Bézier curve to the current path.
     * <p>
     * A quadratic curve segment has a start point, one control point, and an
     * endpoint. The start point is the current point of the canvas. The
     * <code>cpx</code> and <code>cpy</code> parameters specify the control
     * point. The <code>x</code> and <code>y</code> parameters specify the new
     * endpoint. After adding the segment, the current point is reset from the
     * beginning of the new segment to the endpoint of that segment.
     * 
     * @param cpx
     * @param cpy
     * @param x
     * @param y
     */
    public void quadraticCurveTo(double cpx, double cpy, double x, double y) {
        impl.quadraticCurveTo(cpx, cpy, x, y);
    }

    /**
     * Creates a new empty path in the canvas.
     * <p>
     * You use paths to draw both simple shapes (for example, lines, circles, or
     * rectangles) and complex shapes (such as the silhouette of a mountain
     * range) in a canvas. You can use a path to both draw the outline of a
     * shape and fill the inside of a shape.
     * <p>
     * Before painting a shape, you must create the shape to be painted using
     * the current path. You build a path from a set of subpaths, each of which
     * is a list of one or more segments, either straight lines or curves.
     * <p>
     * A canvas can have only a single path in use at any time. Therefore, if
     * the specified context already contains a current path when you call this
     * method, the canvas replaces the previous current path with the new path.
     * In this case, the canvas discards the old path and any data associated
     * with it.
     * <p>
     * Note: The current path is not part of the graphics state. Consequently,
     * saving and restoring the graphics state has no effect on the current
     * path.
     */
    public void beginPath() {
        impl.beginPath();
    }

    /**
     * Closes and terminates an open subpath.
     * <p>
     * When a subpath is open and you call this method, the canvas closes the
     * subpath (draws a straight line that connects the current point to the
     * starting point), and terminates the subpath (the current point is no
     * longer defined).
     * <p>
     * If no subpath is open, calling this method does nothing.
     * <p>
     * Note: You can stroke along an open subpath. When a subpath is open and
     * you fill, however, the canvas implicitly closes the subpath for you.
     */
    public void closePath() {
        impl.closePath();
    }

    /**
     * Begins a new subpath at the point you specify.
     * <p>
     * Before painting a shape in the canvas, you must create the shape to be
     * painted using the current path. You build a path from a set of subpaths,
     * each of which is a list of one or more segments, either straight lines or
     * curves.
     * <p>
     * This method begins a newsubpath starting at the point you specify with
     * the <code>x</code> and <code>y</code> parameters. This point is defined
     * to be the "current" point, and it defines the starting point of the next
     * line segment. The canvas sets the current point in one of two ways:
     * <ul>
     * <li>Explicitly, when you call this method to begin a new subpath at a
     * given point
     * <li>Implicitly, when you add a new curve or straight line segment to the
     * subpath; after adding the segment, the current point is reset from the
     * beginning of the new segment to the endpoint of that segment
     * </ul>
     * 
     * @param x
     * @param y
     */
    public void moveTo(double x, double y) {
        impl.moveTo(x, y);
    }

    /**
     * Appends a straight line segment from the current point to the point you
     * specify.
     * <p>
     * You can use straight line segments, cubic and quadratic Bézier curve
     * segments, and rectangles to specify a path. You can append a single
     * straight line segment to the current subpath using this method. After
     * adding the line segment, the current point is reset from the beginning of
     * the new line segment to the endpoint of that line segment, as specified
     * by the <code>x</code> and <code>y</code> parameters.
     * 
     * @param x
     * @param y
     */
    public void lineTo(double x, double y) {
        impl.lineTo(x, y);
    }

    /**
     * Adds a new subpath consisting of a single rectangle to the canvas.
     * <p>
     * The parameters of this method all contain double values.
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void rect(double x, double y, double w, double h) {
        impl.rect(x, y, w, h);
    }

    // ///////////////////////////////////////////////////////////////
    // STROKING AND FILLING
    // ///////////////////////////////////////////////////////////////

    /**
     * Clears the entire canvas.
     * <p>
     * When you call this method, the canvas effectively "erases" all of it's
     * contents, if any.
     */
    public void clear() {
        impl.clear();
    }

    /**
     * Paints the area within the current path.
     * <p>
     * The fill color is an attribute of the graphics state. You can set the
     * current fill color by setting a value with the
     * {@link #setFillStyle(String)} method.
     * <p>
     * When you fill the current path, the canvas fills each subpath
     * independently. Any subpath that has not been explicitly closed is closed
     * implicitly by the fill routines.
     */
    public void fill() {
        impl.fill();
    }

    /**
     * Paints a line along the current path.
     * <p>
     * To modify the behavior of this method, you can change any of the
     * following graphics state properties with these methods:
     * <ul>
     * <li>{@link #setLineWidth(double)}
     * <li>{@link #setLineJoin(String)}
     * <li>{@link #setLineCap(String)}
     * <li>{@link #setMiterLimit(double)}
     * <li>{@link #setStrokeStyle(String)}
     * <li>{@link #setGlobalAlpha(double)}
     * </ul>
     */
    public void stroke() {
        impl.stroke();
    }

    /**
     * Paints the area within the specified rectangle.
     * <p>
     * This method uses the current fill color to paint the area of the
     * specified rectangle. The parameters of this method all contain double
     * values.
     * <p>
     * As a side effect of calling this method, the canvas clears the current
     * path.
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void fillRect(double x, double y, double w, double h) {
        impl.fillRect(x, y, w, h);
    }

    /**
     * Paints an outline of a rectangle.
     * <p>
     * This method uses the current stroke color to paint the path represented
     * by the specified rectangle. The parameters of this method all contain
     * double values.
     * <p>
     * To alter the appearance of the painted outline, you can modify the
     * following attributes of the graphics state:
     * <ul>
     * <li>{@link #setLineWidth(double)}
     * <li>{@link #setLineJoin(String)}
     * <li>{@link #setLineCap(String)}
     * <li>{@link #setMiterLimit(double)}
     * <li>{@link #setStrokeStyle(String)}
     * <li>{@link #setGlobalAlpha(double)}
     * </ul>
     * 
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public void strokeRect(double x, double y, double w, double h) {
        impl.strokeRect(x, y, w, h);
    }

    // ///////////////////////////////////////////////////////////////
    // GRADIENT STYLES
    // ///////////////////////////////////////////////////////////////

    /**
     * Returns a gradient object representing a linear gradient.
     * <p>
     * Use {@link Gradient#addColorStop(double, String)} to add colors and
     * offsets to a gradient. The 0 offset in this case is the start of the
     * gradient <code>(x0, y0)</code> while the 1 offset is the end of the
     * gradient <code>(x1, y1)</code>.
     * <p>
     * The returned object can be assigned to the
     * {@link #setFillStyle(Gradient)} and {@link #setStrokeStyle(Gradient)}
     * methods.
     * 
     * @param x0
     * @param y0
     * @param x1
     * @param y1
     * @return an object that represents a linear gradient between the two input
     *         coordinates
     */
    public Gradient createLinearGradient(double x0, double y0, double x1,
            double y1) {
        return impl.createLinearGradient(x0, y0, x1, y1);
    }

    /**
     * Returns a gradient object representing a radial gradient.
     * <p>
     * Use {@link Gradient#addColorStop(double, String)} to add colors and
     * offsets to a gradient. The 0 offset in this case is the circumference of
     * the first circle <code>(x0, y0, r0)</code> while the 1 offset is the
     * circumference of the second circle <code>(x1, y1, r1)</code>.
     * <p>
     * The returned object can be assigned to the
     * {@link #setFillStyle(Gradient)} and {@link #setStrokeStyle(Gradient)}
     * methods.
     * 
     * @param x0
     * @param y0
     * @param r0
     * @param x1
     * @param y1
     * @param r1
     * @return an object that represents a radial gradient between the edges of
     *         two circles using the input coordinates and the radii
     */
    public Gradient createRadialGradient(double x0, double y0, double r0,
            double x1, double y1, double r1) {
        return impl.createRadialGradient(x0, y0, r0, x1, y1, r1);
    }

    // ///////////////////////////////////////////////////////////////
    // DRAWING IMAGES
    // ///////////////////////////////////////////////////////////////

    /**
     * Draws an image in the specified rectangle.
     * <p>
     * This method is overloaded with three variants, used to draw the contents
     * of an Image object into the canvas.
     * <p>
     * Draws the image at the <code>x</code> and <code>y</code> coordinates
     * within the canvas. The image is sized as it is in the object.
     * 
     * @param image
     * @param x
     * @param y
     */
    public void drawImage(Image image, double x, double y) {
        if (image != null && image.isLoaded()) {
            double width = image.getWidth();
            double height = image.getHeight();
            impl.drawImage(image.getElement(), 0, 0, width, height, x, y,
                    width, height);
        }
    }

    /**
     * Draws an image in the specified rectangle.
     * <p>
     * This method is overloaded with three variants, used to draw the contents
     * of an Image object into the canvas.
     * <p>
     * The <code>x</code>, <code>y</code>, <code>width</code>, and
     * <code>height</code> parameters contain values representing the bounding
     * rectangle for the image. These values are specified in the coordinate
     * system of the canvas. If the specified coordinates lie outside of the
     * canvas bounds, the image will be clipped.
     * 
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void drawImage(Image image, double x, double y, double width,
            double height) {
        if (image != null && image.isLoaded()) {
            impl.drawImage(image.getElement(), 0, 0, image.getWidth(),
                    image.getHeight(), x, y, width, height);
        }
    }

    /**
     * Draws an image in the specified rectangle.
     * <p>
     * This method is overloaded with three variants, used to draw the contents
     * of an Image object into the canvas.
     * <p>
     * Draws the portion of the image specified by the source rectangle (
     * <code>sx, sy, sWidth, sHeight</code>) onto the canvas at the specified
     * destination rectangle (<code>dx, dy, dWidth, dHeight</code>). The source
     * rectangle is specified in the image coordinate space and the destination
     * rectangle is specified in the canvas coordinate space.
     * 
     * @param image
     * @param sx
     * @param sy
     * @param sWidth
     * @param sHeight
     * @param dx
     * @param dy
     * @param dWidth
     * @param dHeight
     */
    public void drawImage(Image image, double sx, double sy, double sWidth,
            double sHeight, double dx, double dy, double dWidth, double dHeight) {
        if (image != null && image.isLoaded()) {
            impl.drawImage(image.getElement(), sx, sy, sWidth, sHeight, dx, dy,
                    dWidth, dHeight);
        }
    }

    // ///////////////////////////////////////////////////////////////
    // SETTERS AND GETTERS
    // ///////////////////////////////////////////////////////////////

    /**
     * A double value indicating the alpha channel value, which determines the
     * opacity of content drawn on the canvas. The range of values is between
     * 0.0 (fully transparent) and 1.0 (no additional transparency). By default,
     * this parameter’s value is 1.0 (no transparency).
     * <p>
     * The canvas uses the alpha value in the current graphics state to
     * determine how to composite newly painted objects.
     * 
     * @param globalAlpha
     *            the global alpha channel value (range 0.0 to 1.0)
     */
    public void setGlobalAlpha(double globalAlpha) {
        impl.setGlobalAlpha(globalAlpha);
    }

    /**
     * See setter method {@link #setGlobalAlpha(double)} for a fully detailed
     * description.
     * 
     * @return the global alpha channel value (range 0.0 to 1.0)
     */
    public double getGlobalAlpha() {
        return impl.getGlobalAlpha();
    }

    /**
     * Determines how the canvas contents are displayed relative to any existing
     * content. The string identifies the desired compositing mode. If you do
     * not set this value explicitly, the canvas uses the
     * <code>Canvas.SOURCE_OVER</code> compositing setting by default.
     * 
     * @param globalCompositeOperation
     *            <code>Canvas.SOURCE_OVER</code> or
     *            <code>Canvas.DESTINATION_OVER</code>
     */
    public void setGlobalCompositeOperation(String globalCompositeOperation) {
        if (globalCompositeOperation == null) {
            throw new IllegalArgumentException();
        }
        impl.setGlobalCompositeOperation(globalCompositeOperation);
    }

    /**
     * See setter method {@link #setGlobalCompositeOperation(String)} for a
     * fully detailed description.
     * 
     * @return <code>Canvas.SOURCE_OVER</code> or
     *         <code>Canvas.DESTINATION_OVER</code>
     */
    public String getGlobalCompositeOperation() {
        return impl.getGlobalCompositeOperation();
    }

    /**
     * The color the canvas applies when stroking paths. When you set this
     * property, the canvas sets the stroke style parameter of the graphics
     * state.
     * <p>
     * You can set it in several different ways depending on the color space you
     * intend to use. For web-safe colors, pass a web color specification string
     * of the form <code>"#RRGGBB"</code>, which represents an RGB color using
     * hexadecimal numbers.
     * <p>
     * If you want the shape stroke to have an alpha value, use the CSS
     * <code>rgba(r, g, b, alpha)</code> functional-notation style. Use integer
     * values between 0 and 255 for the <code>r</code>, <code>g</code>, and
     * <code>b</code> parameters. The <code>alpha</code> parameter contains a
     * double value, between 0.0 and 1.0, indicating the alpha channel value,
     * which determines the opacity of the color.
     * 
     * @param strokeStyle
     *            a String representing the stroke color
     */
    public void setStrokeStyle(String strokeStyle) {
        if (strokeStyle == null) {
            throw new IllegalArgumentException();
        }
        impl.setStrokeStyle(strokeStyle);
    }

    /**
     * See setter methods {@link #setStrokeStyle(String)} for a fully detailed
     * description.
     * 
     * @return a color String object representing the stroke style
     */
    public String getStrokeStyle() {
        return impl.getStrokeStyle();
    }

    /**
     * The style the canvas applies when filling paths. When you set this
     * property, the canvas sets the fill style parameter of the graphics state.
     * <p>
     * You can set the fill style to be a linear or radial gradient. Use the
     * {@link #createLinearGradient(double, double, double, double)} and
     * {@link #createRadialGradient(double, double, double, double, double, double)}
     * methods to define a style that you can apply to this method.
     * 
     * @param fillStyle
     *            a Gradient representing the fill style
     */
    public void setFillStyle(Gradient fillStyle) {
        if (fillStyle == null) {
            throw new IllegalArgumentException();
        }
        fillGradient = fillStyle;
        impl.setFillStyle(fillStyle);
    }

    /**
     * The color the canvas applies when filling paths. When you set this
     * property, the canvas sets the fill style parameter of the graphics state.
     * <p>
     * You can set it in several different ways depending on the color space you
     * intend to use. For web-safe colors, pass a web color specification string
     * of the form <code>"#RRGGBB"</code>, which represents an RGB color using
     * hexadecimal numbers.
     * <p>
     * If you want the shape fill to have an alpha value, use the CSS
     * <code>rgba(r, g, b, alpha)</code> functional-notation style. Use integer
     * values between 0 and 255 for the <code>r</code>, <code>g</code>, and
     * <code>b</code> parameters. The <code>alpha</code> parameter contains a
     * double value, between 0.0 and 1.0, indicating the alpha channel value,
     * which determines the opacity of the color.
     * 
     * @param fillStyle
     *            a String representing the fill color
     */
    public void setFillStyle(String fillStyle) {
        if (fillStyle == null) {
            throw new IllegalArgumentException();
        }
        fillGradient = null;
        impl.setFillStyle(fillStyle);
    }

    /**
     * See setter methods {@link #setFillStyle(String)} and
     * {@link #setFillStyle(Gradient)} for a fully detailed description.
     * 
     * @return a color String or Gradient object representing the fill style
     */
    public Object getFillStyle() {
        if (fillGradient != null) {
            return fillGradient;
        }
        return impl.getFillStyle();
    }

    /**
     * A double value indicating the line width for drawing operations. This
     * value must be greater than zero. You can affect the width of lines and
     * curves that the canvas draws by modifying the line width property of the
     * graphics state. The line width is the total width of the line, expressed
     * in units of the user space. The line surrounds the center of the path,
     * with half of the total width on either side.
     * 
     * @param lineWidth
     *            the line width
     */
    public void setLineWidth(double lineWidth) {
        impl.setLineWidth(lineWidth);
    }

    /**
     * See setter method {@link #setLineWidth(double)} for a fully detailed
     * description.
     * 
     * @return the line width
     */
    public double getLineWidth() {
        return impl.getLineWidth();
    }

    /**
     * A string value that determines the end style used when drawing a line. If
     * you do not set this value explicitly, the canvas uses the
     * <code>Canvas.BUTT</code> line cap style.
     * 
     * @param lineCap
     *            <code>Canvas.BUTT</code>, <code>Canvas.ROUND</code> or
     *            <code>Canvas.SQUARE</code>
     */
    public void setLineCap(String lineCap) {
        if (lineCap == null) {
            throw new IllegalArgumentException();
        }
        impl.setLineCap(lineCap);
    }

    /**
     * See setter method {@link #setLineCap(String)} for a fully detailed
     * description.
     * 
     * @return <code>Canvas.BUTT</code>, <code>Canvas.ROUND</code> or
     *         <code>Canvas.SQUARE</code>
     */
    public String getLineCap() {
        return impl.getLineCap();
    }

    /**
     * A string value that determines the join style between lines. If you do
     * not set this value explicitly, the canvas uses the
     * <code>Canvas.MITER</code> line join style.
     * 
     * @param lineJoin
     *            <code>Canvas.ROUND</code>, <code>Canvas.BEVEL</code> or
     *            <code>Canvas.MITER</code>
     */
    public void setLineJoin(String lineJoin) {
        if (lineJoin == null) {
            throw new IllegalArgumentException();
        }
        impl.setLineJoin(lineJoin);
    }

    /**
     * See setter method {@link #setLineJoin(String)} for a fully detailed
     * description.
     * 
     * @return <code>Canvas.ROUND</code>, <code>Canvas.BEVEL</code> or
     *         <code>Canvas.MITER</code>
     */
    public String getLineJoin() {
        return impl.getLineJoin();
    }

    /**
     * A double value with the new miter limit. You use this property to specify
     * how the canvas draws the juncture between connected line segments. If the
     * line join is set to <code>Canvas.MITER</code>, the canvas uses the miter
     * limit to determine whether the lines should be joined with a bevel
     * instead of a miter. The canvas divides the length of the miter by the
     * line width. If the result is greater than the miter limit, the style is
     * converted to a bevel.
     * 
     * @param miterLimit
     *            the miter limit
     */
    public void setMiterLimit(double miterLimit) {
        impl.setMiterLimit(miterLimit);
    }

    /**
     * See setter method {@link #setMiterLimit(double)} for a fully detailed
     * description.
     * 
     * @return the miter limit
     */
    public double getMiterLimit() {
        return impl.getMiterLimit();
    }

    /**
     * Sets the canvas shadow
     * 
     * @param color
     *            The color of the shadow as a CSS string
     * @param blur
     *            The blur distance
     * @param offsetX
     *            The horizontal offset of the shadow
     * @param offsetY
     *            The vertical offset of the shadow
     */
    public void setShadow(String color, int blur, int offsetX, int offsetY) {
        impl.setShadow(color, blur, offsetX, offsetY);
    }
}
