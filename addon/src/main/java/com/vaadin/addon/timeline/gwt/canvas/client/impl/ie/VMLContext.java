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

import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;

/**
 * The VML context abstraction for the Internet Explorer implementation.
 */
public class VMLContext {

    public double[] matrix = new double[9];

    public double arcScaleX;

    public double arcScaleY;

    public double globalAlpha;

    public double strokeAlpha;

    public double fillAlpha;

    public double miterLimit;

    public double lineWidth;

    public String lineCap;

    public String lineJoin;

    public String strokeStyle;

    public String strokeStyle_;

    public String fillStyle;

    public String fillStyle_;

    public String globalCompositeOperation;

    public GradientImplIE fillGradient;

    public String shadowColor;

    public double shadowBlur;

    public int shadowOffsetX;

    public int shadowOffsetY;

    public VMLContext() {

        // load identity matrix
        matrix[0] = 1.0;
        matrix[1] = 0.0;
        matrix[2] = 0.0;
        matrix[3] = 0.0;
        matrix[4] = 1.0;
        matrix[5] = 0.0;
        matrix[6] = 0.0;
        matrix[7] = 0.0;
        matrix[8] = 1.0;

        // init other stuff
        arcScaleX = 1.0;
        arcScaleY = 1.0;
        globalAlpha = 1.0;
        strokeAlpha = 1.0;
        fillAlpha = 1.0;
        miterLimit = 10.0;
        lineWidth = 1.0;
        lineCap = CanvasImplIE.BUTT;
        lineJoin = Canvas.MITER;
        strokeStyle = "#000";
        fillStyle = "#000";
        globalCompositeOperation = CanvasImplIE.SOURCE_OVER;
    }

    public VMLContext(VMLContext ctx) {

        // copy the matrix
        matrix[0] = ctx.matrix[0];
        matrix[1] = ctx.matrix[1];
        matrix[3] = ctx.matrix[3];
        matrix[4] = ctx.matrix[4];
        matrix[6] = ctx.matrix[6];
        matrix[7] = ctx.matrix[7];

        // copy other stuff
        arcScaleX = ctx.arcScaleX;
        arcScaleY = ctx.arcScaleY;
        globalAlpha = ctx.globalAlpha;
        strokeAlpha = ctx.strokeAlpha;
        fillAlpha = ctx.fillAlpha;
        miterLimit = ctx.miterLimit;
        lineWidth = ctx.lineWidth;
        lineCap = ctx.lineCap;
        lineJoin = ctx.lineJoin;
        strokeStyle = ctx.strokeStyle;
        strokeStyle_ = ctx.strokeStyle_;
        fillStyle = ctx.fillStyle;
        fillStyle_ = ctx.fillStyle_;
        globalCompositeOperation = ctx.globalCompositeOperation;
        fillGradient = ctx.fillGradient;
        shadowBlur = ctx.shadowBlur;
        shadowColor = ctx.shadowColor;
        shadowOffsetX = ctx.shadowOffsetX;
        shadowOffsetY = ctx.shadowOffsetY;
    }
}
