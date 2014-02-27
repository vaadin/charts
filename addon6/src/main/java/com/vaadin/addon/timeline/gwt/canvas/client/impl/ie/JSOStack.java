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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * JavaScriptObject overlay for an array, treated as a stack.
 */
public class JSOStack<T> extends JavaScriptObject {

    public static native <M> JSOStack<M> create() /*-{
                                                  return [];
                                                  }-*/;

    protected JSOStack() {
    }

    public final native void clear() /*-{
                                     this.length = 0;
                                     }-*/;

    public final native boolean isEmpty() /*-{
                                          return (this.length == 0);
                                          }-*/;

    public final native String join() /*-{
                                      return this.join("");
                                      }-*/;

    public final native T pop() /*-{
                                return this.pop();
                                }-*/;

    public final native void push(T pathStr) /*-{
                                             this[this.length] = pathStr;
                                             }-*/;

    public final native int size() /*-{
                                   return this.length;
                                   }-*/;
}
