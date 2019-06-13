package com.vaadin.addon.charts.client.ui;

/*-
 * #%L
 * Vaadin Charts Addon
 * %%
 * Copyright (C) 2012 - 2019 Vaadin Ltd
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.charts.shared.MouseEventDetails;
import com.vaadin.addon.charts.shared.MouseEventDetails.MouseButton;
import com.vaadin.client.VConsole;
import com.vaadin.client.WidgetUtil;

/**
 * Helper class for constructing a MouseEventDetails object from different types
 * of {@link AbstractClickEvent}.
 */
public class MouseEventDetailsBuilder {

    /**
     * Construct a {@link MouseEventDetails} object from the given
     * {@link ChartClickEvent}
     *
     * @param event
     *            The event to use as a source for the details
     * @param relativeToObject
     *            The element used to calculate
     *                         {@link MouseEventDetails#getxValue()} and
     *                         {@link MouseEventDetails#getyValue()}
     * @return mouseEventDetails containing information from the event
     */
    public static MouseEventDetails buildMouseEventDetails(
            ChartClickEvent event, UIObject relativeToObject) {
        MouseEventDetails mouseEventDetails = new MouseEventDetails();
        initCommonValues(mouseEventDetails, event);

        ValueAxisPair xPair = event.getXPairs().get(0);
        ValueAxisPair yPair = event.getYPairs().get(0);
        double x = xPair.getValue();
        double y = yPair.getValue();
        int absoluteX = relativeToObject.getAbsoluteLeft() + event.getChartX();
        int absoluteY = relativeToObject.getAbsoluteTop() + event.getChartY();

        mouseEventDetails.setAbsoluteX(absoluteX);
        mouseEventDetails.setAbsoluteY(absoluteY);
        mouseEventDetails.setxValue(x);
        mouseEventDetails.setyValue(y);

        return mouseEventDetails;

    }

    /**
     * Construct a {@link MouseEventDetails} object from the given
     * {@link PointClickEvent}
     *
     * @param event
     *            The event to use as a source for the details
     * @param relativeToObject
     *            The element used to calculate
     *                         {@link MouseEventDetails#getxValue()} and
     *                         {@link MouseEventDetails#getyValue()}
     * @return mouseEventDetails containing information from the event
     */
    public static MouseEventDetails buildMouseEventDetails(
            PointClickEvent event, UIObject relativeToObject) {
        MouseEventDetails mouseEventDetails = new MouseEventDetails();
        initCommonValues(mouseEventDetails, event);

        int absoluteX = relativeToObject.getAbsoluteLeft() + event.getChartX();
        int absoluteY = relativeToObject.getAbsoluteTop() + event.getChartY();
        mouseEventDetails.setAbsoluteX(absoluteX);
        mouseEventDetails.setAbsoluteY(absoluteY);
        mouseEventDetails.setxValue(event.getX());
        mouseEventDetails.setyValue(event.getY());

        return mouseEventDetails;
    }


    /**
     * Construct a {@link MouseEventDetails} object from the given event.
     *
     * @param evt               The event to use as a source for the details
     * @param relativeToObject The element whose position
     *                          {@link MouseEventDetails#getxValue()} ()} and
     *                          {@link MouseEventDetails#getyValue()} ()} are relative to.
     * @return a MouseEventDetails containing information from the event
     */
    public static MouseEventDetails buildMouseEventDetails(NativeEvent evt, UIObject relativeToObject) {
        MouseEventDetails mouseEventDetails = new MouseEventDetails();

        mouseEventDetails.setAbsoluteX(evt.getClientX());
        mouseEventDetails.setAbsoluteY(evt.getClientY());

        int relativeX = evt.getClientX() - relativeToObject.getAbsoluteLeft();
        int relativeY = evt.getClientY() - relativeToObject.getAbsoluteTop();

        mouseEventDetails.setxValue(relativeX);
        mouseEventDetails.setyValue(relativeY);

        VConsole.error("Abs y val: " + evt.getClientY() + " Relative y val: " + relativeY + " Widget util Y: "+ WidgetUtil.getTouchOrMouseClientY(evt));


        if (evt.getButton() == NativeEvent.BUTTON_LEFT) {
            mouseEventDetails.setButton(MouseButton.LEFT);
        } else if (evt.getButton() == NativeEvent.BUTTON_RIGHT) {
            mouseEventDetails.setButton(MouseButton.RIGHT);
        } else if (evt.getButton() == NativeEvent.BUTTON_MIDDLE) {
            mouseEventDetails.setButton(MouseButton.MIDDLE);
        } else {
            // No button reported? Assume left.
            mouseEventDetails.setButton(MouseButton.LEFT);
        }
        mouseEventDetails.setAltKey(evt.getAltKey());
        mouseEventDetails.setCtrlKey(evt.getCtrlKey());
        mouseEventDetails.setMetaKey(evt.getMetaKey());
        mouseEventDetails.setShiftKey(evt.getShiftKey());
        return mouseEventDetails;

    }


    /**
     * Init {@link MouseEventDetails} with {@link AbstractClickEvent} info
     *
     * @param details
     *            object to be initialized
     * @param event
     *            The event to use as a source for the details
     */
    private static void initCommonValues(MouseEventDetails details,
                                         AbstractClickEvent event) {
        if (event.getButton() == 0) {
            details.setButton(MouseButton.LEFT);
        } else if (event.getButton() == 2) {
            details.setButton(MouseButton.RIGHT);
        } else if (event.getButton() == 1) {
            details.setButton(MouseButton.MIDDLE);
        } else {
            // IE8 does not always report a button. Assume left.
            details.setButton(MouseButton.LEFT);
        }
        details.setAltKey(event.isAltKey());
        details.setCtrlKey(event.isCtrlKey());
        details.setMetaKey(event.isMetaKey());
        details.setShiftKey(event.isShiftKey());
    }

}
