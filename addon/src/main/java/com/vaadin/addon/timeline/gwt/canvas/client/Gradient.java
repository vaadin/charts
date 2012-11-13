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

/**
 * Canvas gradient which may be used as a fill or stroke style.
 */
public abstract class Gradient {

	/**
	 * Adds a color at an offset point to the gradient.
	 * 
	 * @param offset the offset at which to apply the color stop
	 * @param color the actual color value of the color stop
	 */
	public abstract void addColorStop(double offset, String color);
}
