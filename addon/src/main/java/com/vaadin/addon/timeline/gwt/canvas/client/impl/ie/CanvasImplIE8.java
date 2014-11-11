package com.vaadin.addon.timeline.gwt.canvas.client.impl.ie;

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

import com.google.gwt.user.client.Element;
import com.vaadin.client.VConsole;

/**
 * Internet Explorer 8 fix for VML.
 * 
 */
public class CanvasImplIE8 extends CanvasImplIE {

    @Override
    protected native void init()
    /*-{
               if (!$doc.namespaces["v"]) {
                       $doc.namespaces.add('v', 'urn:schemas-microsoft-com:vml', "#default#VML");
                       $doc.createStyleSheet().cssText = "v\\:*{behavior:url(#default#VML);}";
                }
    }-*/;

    @Override
    public void init(Element element) {
        super.init(element);
        VConsole.log("Using IE 8 canvas implementation");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.canvas.client.impl.ie.CanvasImplIE#stroke()
     */
    @Override
    public void stroke() {
        if (pathStack.isEmpty()) {
            return;
        }

        scratchStack.clear();
        scratchStack
                .push("<v:shape style=\"position:absolute;width:10px;height:10px;\" coordsize=\"100,100\" filled=\"f\" strokecolor=\"");
        scratchStack.push(context.strokeStyle);
        scratchStack.push("\" strokeweight=\"" + context.lineWidth);
        scratchStack.push("px\" path=\"");
        scratchStack.push(pathStack.join());
        scratchStack
                .push(" e\"><v:stroke style=\"position:absolute;width:10px;height:10px;\" opacity=\""
                        + context.globalAlpha * context.strokeAlpha);
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.canvas.client.impl.ie.CanvasImplIE#fill()
     */
    @Override
    public void fill() {
        if (pathStack.isEmpty()) {
            return;
        }
        scratchStack.clear();
        scratchStack
                .push("<v:shape style=\"position:absolute;width:10px;height:10px;\" coordsize=\"100,100\" fillcolor=\"");
        scratchStack.push(context.fillStyle);
        scratchStack.push("\" stroked=\"f\" path=\"");
        scratchStack.push(pathStack.join());
        scratchStack
                .push(" e\"><v:fill style=\"position:absolute;width:10px;height:10px;\" opacity=\""
                        + context.globalAlpha * context.fillAlpha);
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
}
