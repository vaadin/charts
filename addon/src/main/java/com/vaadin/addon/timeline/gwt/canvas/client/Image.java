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

import com.google.gwt.dom.client.ImageElement;

/**
 * Canvas image which may be used by the <code>Canvas.drawImage(...)</code>
 * methods.
 */
public class Image {

    /**
     * An optional loading listener callback method.
     */
    private ImageListener loadingListener;

    /**
     * The actual image element.
     */
    private ImageElement imageElement;

    /**
     * Flag that indicates the loading status.
     */
    private boolean loaded;

    /**
     * Creates an image object that will not give any notifications on its own
     * when it's actual data has been loaded by the browser.
     * 
     * @param url
     *            to an image file (GIF, JPEG, PNG)
     */
    public Image(String url) {
        this(url, null);
    }

    /**
     * Creates an image object that will notify the given callback listener when
     * it's actual data has been loaded by the browser.
     * 
     * @param url
     *            to an image file (GIF, JPEG, PNG)
     * @param listener
     *            a user defined callback method
     */
    public Image(String url, ImageListener listener) {
        if (url == null) {
            throw new IllegalArgumentException();
        }
        loadingListener = listener;
        imageElement = getNativeHandle();
        imageElement.setSrc(url);
    }

    /**
     * Creates an image handle that will notify the {@link Image} instance, if
     * it has loaded it's actual data successfully.
     * 
     * @return an ImageElement object
     */
    private native ImageElement getNativeHandle() /*-{
                                                  var self = this;
                                                  var image = new Image();
                                                  image.onload = function() {
                                                  self.@com.vaadin.addon.timeline.gwt.canvas.client.Image::invokeCallback()();
                                                  image.onload = null;
                                                  }
                                                  return image;
                                                  }-*/;

    /**
     * Invokes the {@link Image.ImageListener} instance, if any.
     */
    private void invokeCallback() {
        loaded = true;
        if (loadingListener != null) {
            loadingListener.onLoadingComplete(this);
            loadingListener = null;
        }
    }

    /**
     * Use this method to determine if the actual image has already been loaded
     * by the browser.
     * 
     * @return true when loaded, false otherwise
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Returns the actual image DOM element.
     * 
     * @return the image element
     */
    public ImageElement getElement() {
        return imageElement;
    }

    /**
     * Returns the image's width or -1 if it has not yet been loaded
     * 
     * @return the width or -1 if unknown
     */
    public int getWidth() {
        if (imageElement != null) {
            return imageElement.getWidth();
        }
        return -1;
    }

    /**
     * Returns the image's height or -1 if it has not yet been loaded
     * 
     * @return the height or -1 if unknown
     */
    public int getHeight() {
        if (imageElement != null) {
            return imageElement.getHeight();
        }
        return -1;
    }
}
