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

package com.vaadin.addon.timeline.gwt.canvas.client.impl.ie;

/*
 * %%Ignore-License
 */

import java.util.Stack;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;
import com.vaadin.addon.timeline.gwt.canvas.client.Gradient;
import com.vaadin.addon.timeline.gwt.canvas.client.impl.CanvasImpl;
import com.vaadin.client.VConsole;

/**
 * The Internet Explorer implementation of the canvas widget.
 * 
 * @see http://msdn2.microsoft.com/en-us/library/bb250524(VS.85).aspx
 * @see http://en.wikipedia.org/wiki/Transformation_matrix
 * @see http://code.google.com/p/explorercanvas/
 */
public class CanvasImplIE extends CanvasImpl {

    // ///////////////////////////////////////////////////////////////
    // STATIC CONSTANTS AND METHODS
    // ///////////////////////////////////////////////////////////////

    public final static String SOURCE_OVER = "beforeEnd";

    public final static String DESTINATION_OVER = "afterBegin";

    public final static String BUTT = "flat";

    public static native int floor(double value) /*-{
                                                 return (value | 0);
                                                 }-*/;

    // ///////////////////////////////////////////////////////////////
    // PRIVATE/PROTECTED MEMBERS/METHODS
    // ///////////////////////////////////////////////////////////////

    private final Stack<VMLContext> contextStack = new Stack<VMLContext>();

    protected JSOStack<String> pathStack = JSOStack.create();

    protected JSOStack<String> scratchStack = JSOStack.create();

    protected VMLContext context;

    private double[] matrix;

    private double currentX = 0.0;

    private double currentY = 0.0;

    private int getCoordX(double x, double y) {
        int coordX = floor(10 * (matrix[0] * x + matrix[3] * y + matrix[6]) - 4.5);
        // TODO update min-max x-values
        return coordX;
    }

    private int getCoordY(double x, double y) {
        int coordY = floor(10 * (matrix[1] * x + matrix[4] * y + matrix[7]) - 4.5);
        // TODO update min-max y-values
        return coordY;
    }

    protected native void insert(String where, String html) /*-{
                                                            this.@com.vaadin.addon.timeline.gwt.canvas.client.impl.CanvasImpl::element.insertAdjacentHTML(where, html);
                                                            }-*/;

    // TODO investigate further
    // protected native void cancelSelections() /*-{
    // try {
    // $doc.selection.empty();
    // } catch (e) {
    // // do nothing
    // }
    // }-*/;

    @Override
    protected native void init() /*-{
                                 if (!$doc.namespaces["v"]) {
                                 $doc.namespaces.add("v", "urn:schemas-microsoft-com:vml");
                                 $doc.createStyleSheet().cssText = "v\\:*{behavior:url(#default#VML);}";
                                 }
                                 }-*/;

    // ///////////////////////////////////////////////////////////////
    // CONSTRUCTORS AND PUBLIC METHODS
    // ///////////////////////////////////////////////////////////////

    @Override
    public void init(Element element) {
        this.element = element;
        DOM.setStyleAttribute(element, "position", "relative");
        DOM.setStyleAttribute(element, "display", "inline-block");
        DOM.setStyleAttribute(element, "overflow", "hidden");
        DOM.setStyleAttribute(element, "textAlign", "left");
        DOM.setStyleAttribute(element, "cursor", "default");

        init();
        context = new VMLContext();
        matrix = context.matrix;

        VConsole.log("Using default IE canvas implementation");
    }

    @Override
    public void setWidth(String width) {
        DOM.setInnerHTML(element, "");
        DOM.setStyleAttribute(element, "width", width);
    }

    @Override
    public void setHeight(String height) {
        DOM.setInnerHTML(element, "");
        DOM.setStyleAttribute(element, "height", height);
    }

    // public void onMouseDown(Event event) {
    // cancelSelections();
    // DOM.setCapture(element);
    // }

    // public void onMouseUp() {
    // DOM.releaseCapture(element);
    // }

    // ///////////////////////////////////////////////////////////////
    // CANVAS STATE METHODS
    // ///////////////////////////////////////////////////////////////

    @Override
    public void restore() {
        if (!contextStack.isEmpty()) {
            context = contextStack.pop();
            matrix = context.matrix;
        }
    }

    @Override
    public void save() {
        contextStack.push(context);
        context = new VMLContext(context);
        matrix = context.matrix;
    }

    @Override
    public void rotate(double angle) {
        double s = Math.sin(angle);
        double c = Math.cos(angle);
        double a = matrix[0];
        double b = matrix[3];
        matrix[0] = a * c + b * s;
        matrix[3] = -a * s + b * c;
        a = matrix[1];
        b = matrix[4];
        matrix[1] = a * c + b * s;
        matrix[4] = -a * s + b * c;
    }

    @Override
    public void scale(double x, double y) {
        context.arcScaleX *= x;
        context.arcScaleY *= y;
        matrix[0] *= x;
        matrix[1] *= x;
        matrix[3] *= y;
        matrix[4] *= y;
    }

    @Override
    public void translate(double x, double y) {
        matrix[6] += matrix[0] * x + matrix[3] * y;
        matrix[7] += matrix[1] * x + matrix[4] * y;
    }

    public void transform(double m11, double m12, double m21, double m22,
            double dx, double dy) {
        double a = matrix[0];
        double b = matrix[3];
        matrix[0] = a * m11 + b * m12;
        matrix[3] = a * m21 + b * m22;
        matrix[6] += a * dx + b * dy;
        a = matrix[1];
        b = matrix[4];
        matrix[1] = a * m11 + b * m12;
        matrix[4] = a * m21 + b * m22;
        matrix[7] += a * dx + b * dy;
    }

    public void setTransform(double m11, double m12, double m21, double m22,
            double dx, double dy) {
        matrix[0] = m11;
        matrix[1] = m12;
        matrix[3] = m21;
        matrix[4] = m22;
        matrix[6] = dx;
        matrix[7] = dy;
    }

    // ///////////////////////////////////////////////////////////////
    // WORKING WITH PATHS
    // ///////////////////////////////////////////////////////////////

    @Override
    public void arc(double x, double y, double radius, double startAngle,
            double endAngle, boolean antiClockwise) {
        double ar = radius * 10;
        double startX = x + Math.cos(startAngle) * ar - 5;
        double startY = y + Math.sin(startAngle) * ar - 5;
        double endX = x + Math.cos(endAngle) * ar - 5;
        double endY = y + Math.sin(endAngle) * ar - 5;
        if (Math.abs(startX - endX) < .0000001 && !antiClockwise) {
            startX += 0.125;
        }
        int cx = getCoordX(x, y);
        int cy = getCoordY(x, y);
        double arcX = context.arcScaleX * ar;
        double arcY = context.arcScaleY * ar;
        if (antiClockwise) {
            pathStack.push(" at " + floor(cx - arcX + 0.5));
        } else {
            pathStack.push(" wa " + floor(cx - arcX + 0.5));
        }
        pathStack.push("," + floor(cy - arcY + 0.5));
        pathStack.push(" " + floor(cx + arcX + 0.5));
        pathStack.push("," + floor(cy + arcY + 0.5));
        pathStack.push(" " + getCoordX(startX, startY));
        pathStack.push("," + getCoordY(startX, startY));
        pathStack.push(" " + getCoordX(endX, endY));
        pathStack.push("," + getCoordY(endX, endY));
    }

    // another version, works but has issues, see demo
    // public void arc(double x, double y, double radius, double startAngle,
    // double endAngle, boolean antiClockwise) {
    // if (!antiClockwise) {
    // double temp = startAngle;
    // startAngle = endAngle;
    // endAngle = temp;
    // }
    // double ar = radius * 10;
    // double startX = x + Math.cos(startAngle) * ar - 5;
    // double startY = y + Math.sin(startAngle) * ar - 5;
    // double endX = x + Math.cos(endAngle) * ar - 5;
    // double endY = y + Math.sin(endAngle) * ar - 5;
    // if (startX == endX && !antiClockwise) {
    // startX += 0.125;
    // }
    // double cx = getCoordX(x, y);
    // double cy = getCoordY(x, y);
    // double arcX = context.arcScaleX * ar;
    // double arcY = context.arcScaleY * ar;
    // pathStack.push(" ar " + floor(cx - arcX + 0.5));
    // pathStack.push("," + floor(cy + arcY + 0.5));
    // pathStack.push(" " + floor(cx + arcX + 0.5));
    // pathStack.push("," + floor(cy - arcY + 0.5));
    // pathStack.push(" " + getCoordX(startX, startY));
    // pathStack.push("," + getCoordY(startX, startY));
    // pathStack.push(" " + getCoordX(endX, endY));
    // pathStack.push("," + getCoordY(endX, endY));
    // }

    @Override
    public void cubicCurveTo(double cp1x, double cp1y, double cp2x,
            double cp2y, double x, double y) {
        pathStack.push(" c " + getCoordX(cp1x, cp1y));
        pathStack.push("," + getCoordY(cp1x, cp1y));
        pathStack.push("," + getCoordX(cp2x, cp2y));
        pathStack.push("," + getCoordY(cp2x, cp2y));
        pathStack.push("," + getCoordX(x, y));
        pathStack.push("," + getCoordY(x, y));
        currentX = x;
        currentY = y;
    }

    @Override
    public void quadraticCurveTo(double cpx, double cpy, double x, double y) {
        double cp1x = currentX + 2.0 / 3.0 * (cpx - currentX);
        double cp1y = currentY + 2.0 / 3.0 * (cpy - currentY);
        double cp2x = cp1x + (x - currentX) / 3.0;
        double cp2y = cp1y + (y - currentY) / 3.0;
        cubicCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
    }

    @Override
    public void beginPath() {
        pathStack.clear();
    }

    @Override
    public void closePath() {
        pathStack.push(" x");
    }

    @Override
    public void moveTo(double x, double y) {
        pathStack.push(" m " + getCoordX(x, y) + "," + getCoordY(x, y));
        currentX = x;
        currentY = y;
    }

    @Override
    public void lineTo(double x, double y) {
        pathStack.push(" l " + getCoordX(x, y) + "," + getCoordY(x, y));
        currentX = x;
        currentY = y;
    }

    @Override
    public void rect(double x, double y, double w, double h) {
        w += x;
        h += y;
        pathStack.push(" m " + getCoordX(x, y));
        pathStack.push("," + getCoordY(x, y));
        pathStack.push(" l " + getCoordX(w, y));
        pathStack.push("," + getCoordY(x, y));
        pathStack.push(" l " + getCoordX(w, y));
        pathStack.push("," + getCoordY(x, h));
        pathStack.push(" l " + getCoordX(x, y));
        pathStack.push("," + getCoordY(x, h));
        pathStack.push(" x");
        currentX = x;
        currentY = h;
    }

    // ///////////////////////////////////////////////////////////////
    // STROKING AND FILLING
    // ///////////////////////////////////////////////////////////////

    @Override
    public void clear() {
        // TODO reset min-max values
        pathStack.clear();
        DOM.setInnerHTML(element, "");
    }

    @Override
    public void fill() {
        if (pathStack.isEmpty()) {
            return;
        }
        scratchStack.clear();
        scratchStack
                .push("<v:shape style=\"position:absolute;width:10;height:10;\" coordsize=\"100,100\" fillcolor=\"");
        scratchStack.push(context.fillStyle);
        scratchStack.push("\" stroked=\"f\" path=\"");
        scratchStack.push(pathStack.join());
        scratchStack.push(" e\"><v:fill opacity=\"" + context.globalAlpha
                * context.fillAlpha);
        // TODO add gradient code here
        scratchStack.push("\"></v:fill>");
        if (context.shadowColor != null) {
            scratchStack.push("<v:shadow on=\"True\" ");
            scratchStack.push("color=\"" + context.shadowColor + "\" ");
            scratchStack.push("offset=\"" + context.shadowOffsetX + "px,"
                    + context.shadowOffsetY + "px\" ");
            scratchStack.push("opacity=\"" + context.shadowBlur + "\"");
            scratchStack.push("/>");
        }
        scratchStack.push("</v:shape>");
        insert(context.globalCompositeOperation, scratchStack.join());
    }

    @Override
    public void stroke() {
        if (pathStack.isEmpty()) {
            return;
        }
        scratchStack.clear();
        scratchStack
                .push("<v:shape style=\"position:absolute;width:10;height:10;\" coordsize=\"100,100\" filled=\"f\" strokecolor=\"");
        scratchStack.push(context.strokeStyle);
        scratchStack.push("\" strokeweight=\"" + context.lineWidth);
        scratchStack.push("px\" path=\"");
        scratchStack.push(pathStack.join());
        scratchStack.push(" e\"><v:stroke opacity=\"" + context.globalAlpha
                * context.strokeAlpha);
        scratchStack.push("\" miterlimit=\"" + context.miterLimit);
        scratchStack.push("\" joinstyle=\"");
        scratchStack.push(context.lineJoin);
        scratchStack.push("\" endcap=\"");
        scratchStack.push(context.lineCap);
        scratchStack.push("\"></v:stroke>");
        if (context.shadowColor != null) {
            scratchStack.push("<v:shadow on=\"True\" ");
            scratchStack.push("color=\"" + context.shadowColor + "\" ");
            scratchStack.push("offset=\"" + context.shadowOffsetX + "px,"
                    + context.shadowOffsetY + "px\" ");
            scratchStack.push("opacity=\"" + context.shadowBlur + "\"");
            scratchStack.push("/>");
        }
        scratchStack.push("</v:shape>");
        insert(context.globalCompositeOperation, scratchStack.join());
    }

    @Override
    public void setShadow(String color, int blur, int offsetX, int offsetY) {
        context.shadowBlur = 1 - blur / 10.0;
        context.shadowColor = color;
        context.shadowOffsetX = offsetX;
        context.shadowOffsetY = offsetY;
    }

    @Override
    public void fillRect(double x, double y, double w, double h) {
        beginPath();
        rect(x, y, w, h);
        fill();
        pathStack.clear();
    }

    @Override
    public void strokeRect(double x, double y, double w, double h) {
        beginPath();
        rect(x, y, w, h);
        stroke();
        pathStack.clear();
    }

    // ///////////////////////////////////////////////////////////////
    // GRADIENT STYLES
    // ///////////////////////////////////////////////////////////////

    @Override
    public Gradient createLinearGradient(double x0, double y0, double x1,
            double y1) {
        return new LinearGradientImplIE(x0, y0, x1, y1);
    }

    @Override
    public Gradient createRadialGradient(double x0, double y0, double r0,
            double x1, double y1, double r1) {
        return new RadialGradientImplIE(x0, y0, r0, x1, y1, r1);
    }

    // ///////////////////////////////////////////////////////////////
    // DRAWING IMAGES
    // ///////////////////////////////////////////////////////////////

    @Override
    public void drawImage(ImageElement image, double sx, double sy,
            double sWidth, double sHeight, double dx, double dy, double dWidth,
            double dHeight) {

        int iWidth = image.getWidth();
        int iHeight = image.getHeight();
        int dX = (int) (getCoordX(dx, dy) / 10.0);
        int dY = (int) (getCoordY(dx, dy) / 10.0);
        scratchStack.clear();
        scratchStack
                .push("<v:group style=\"position:absolute;width:10;height:10;");

        // If the transformation matrix has a rotation/scale we apply a filter
        // Create a padding bounding box to prevent clipping
        if (context.matrix[0] != 1 || context.matrix[3] != 0) {
            scratchStack.push("padding-right:");
            // TODO verify px suffix
            scratchStack.push(DOM.getStyleAttribute(element, "width"));
            scratchStack.push(";padding-bottom:");
            // TODO verify px suffix
            scratchStack.push(DOM.getStyleAttribute(element, "height"));
            scratchStack
                    .push(";filter:progid:DXImageTransform.Microsoft.Matrix(M11='");
            scratchStack.push(matrix[0] + "',M12='");
            scratchStack.push(matrix[3] + "',M21='");
            scratchStack.push(matrix[1] + "',M22='");
            scratchStack.push(matrix[4] + "',Dx='");
            scratchStack.push(floor(dX) + "',Dy='");
            scratchStack.push(floor(dY) + "', SizingMethod='clip');");
        } else {
            scratchStack.push("left:");
            scratchStack.push(dX + "px;");
            scratchStack.push("top:");
            scratchStack.push(dY + "px");
        }
        scratchStack
                .push("\" coordsize=\"100,100\" coordorigin=\"0,0\"><v:image src=\"");
        scratchStack.push(image.getSrc());
        scratchStack.push("\" style=\"width:" + (int) (dWidth * 10));
        scratchStack.push(";height:" + (int) (dHeight * 10));
        scratchStack.push(";\" cropleft=\"" + (sx / iWidth));
        scratchStack.push("\" croptop=\"" + (sy / iHeight));
        scratchStack
                .push("\" cropright=\"" + ((iWidth - sx - sWidth) / iWidth));
        scratchStack.push("\" cropbottom=\""
                + ((iHeight - sy - sHeight) / iHeight));
        scratchStack.push("\"/></v:group>");
        insert("BeforeEnd", scratchStack.join());
    }

    // ///////////////////////////////////////////////////////////////
    // SETTERS AND GETTERS
    // ///////////////////////////////////////////////////////////////

    @Override
    public void setGlobalAlpha(double globalAlpha) {
        context.globalAlpha = globalAlpha;
    }

    @Override
    public double getGlobalAlpha() {
        return context.globalAlpha;
    }

    @Override
    public void setGlobalCompositeOperation(String gcop) {
        gcop = gcop.trim();
        if (gcop.equalsIgnoreCase(Canvas.SOURCE_OVER)) {
            context.globalCompositeOperation = CanvasImplIE.SOURCE_OVER;
        } else if (gcop.equalsIgnoreCase(Canvas.DESTINATION_OVER)) {
            context.globalCompositeOperation = CanvasImplIE.DESTINATION_OVER;
        }
    }

    @Override
    public String getGlobalCompositeOperation() {
        if (context.globalCompositeOperation == CanvasImplIE.DESTINATION_OVER) {
            return Canvas.DESTINATION_OVER;
        } else {
            return Canvas.SOURCE_OVER;
        }
    }

    @Override
    public void setStrokeStyle(String strokeStyle) {
        strokeStyle = strokeStyle.trim();
        if (strokeStyle.startsWith("rgba(")) {
            int end = strokeStyle.indexOf(")", 12);
            if (end > -1) {
                String[] guts = strokeStyle.substring(5, end).split(",");
                if (guts.length == 4) {
                    context.strokeAlpha = Double.parseDouble(guts[3]);
                    context.strokeStyle = "rgb(" + guts[0] + "," + guts[1]
                            + "," + guts[2] + ")";
                    context.strokeStyle_ = strokeStyle;
                }
            }
        } else {
            context.strokeAlpha = 1.0;
            context.strokeStyle = strokeStyle;
            context.strokeStyle_ = null;
        }
    }

    @Override
    public String getStrokeStyle() {
        if (context.strokeStyle_ != null) {
            return context.strokeStyle_;
        }
        return context.strokeStyle;
    }

    @Override
    public void setFillStyle(Gradient fillStyle) {
        context.fillGradient = (GradientImplIE) fillStyle;
    }

    @Override
    public void setFillStyle(String fillStyle) {
        fillStyle = fillStyle.trim();
        if (fillStyle.startsWith("rgba(")) {
            int end = fillStyle.indexOf(")", 12);
            if (end > -1) {
                String[] guts = fillStyle.substring(5, end).split(",");
                if (guts.length == 4) {
                    context.fillAlpha = Double.parseDouble(guts[3]);
                    context.fillStyle = "rgb(" + guts[0] + "," + guts[1] + ","
                            + guts[2] + ")";
                    context.fillStyle_ = fillStyle;
                }
            }
        } else {
            context.fillAlpha = 1.0;
            context.fillStyle = fillStyle;
            context.fillStyle_ = null;
        }
        context.fillGradient = null;
    }

    @Override
    public String getFillStyle() {
        if (context.fillStyle_ != null) {
            return context.fillStyle_;
        }
        return context.fillStyle;
    }

    @Override
    public void setLineWidth(double lineWidth) {
        context.lineWidth = lineWidth;
    }

    @Override
    public double getLineWidth() {
        return context.lineWidth;
    }

    @Override
    public void setLineCap(String lineCap) {
        if (lineCap.trim().equalsIgnoreCase(Canvas.BUTT)) {
            context.lineCap = CanvasImplIE.BUTT;
        } else {
            context.lineCap = lineCap;
        }
    }

    @Override
    public String getLineCap() {
        if (context.lineCap == CanvasImplIE.BUTT) {
            return Canvas.BUTT;
        }
        return context.lineCap;
    }

    @Override
    public void setLineJoin(String lineJoin) {
        context.lineJoin = lineJoin;
    }

    @Override
    public String getLineJoin() {
        return context.lineJoin;
    }

    @Override
    public void setMiterLimit(double miterLimit) {
        context.miterLimit = miterLimit;
    }

    @Override
    public double getMiterLimit() {
        return context.miterLimit;
    }
}
