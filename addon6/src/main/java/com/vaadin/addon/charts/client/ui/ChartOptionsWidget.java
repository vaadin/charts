package com.vaadin.addon.charts.client.ui;

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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.charts.client.HighchartsScriptLoader;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class ChartOptionsWidget extends Widget implements Paintable {

    public ChartOptionsWidget() {
        HighchartsScriptLoader.ensureInjected();
        setElement(Document.get().createDivElement());
        Style style = getElement().getStyle();
        style.setWidth(0, Unit.PX);
        style.setHeight(0, Unit.PX);
        style.setOverflow(Overflow.HIDDEN);
    }

    @Override
    public void updateFromUIDL(UIDL mainUidl, ApplicationConnection client) {
        if (!client.updateComponent(this, mainUidl, true)) {
            JavaScriptObject options = JSONParser.parseLenient(mainUidl.getStringAttribute("json"))
                    .isObject().getJavaScriptObject();
            applyOptions(options);
        }
    }

    private native void applyOptions(JavaScriptObject obj)
    /*-{
        $wnd.Highcharts.setOptions(obj);
    }-*/;

    @Override
    public void setWidth(String width) {
    }

    @Override
    public void setHeight(String height) {
    }

}
