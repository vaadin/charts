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

import java.util.ArrayList;

import com.vaadin.addon.timeline.gwt.canvas.client.Gradient;

/**
 * The Internet Explorer implementation of the gradient style.
 */
public abstract class GradientImplIE extends Gradient {

    public ArrayList<ColorStop> colorStops = new ArrayList<ColorStop>();

    public GradientImplIE(double x0, double y0, double x1, double y1) {
    }

    public void addColorStop(double offset, String color) {
        ColorStop colorStop = new ColorStop(offset, color);
        for (int i = 0; i < colorStops.size(); ++i) {
            ColorStop cs = colorStops.get(i);
            if (offset < cs.offset) {
                colorStops.add(i, colorStop);
                return;
            }
        }
        colorStops.add(colorStop);
    }
}
