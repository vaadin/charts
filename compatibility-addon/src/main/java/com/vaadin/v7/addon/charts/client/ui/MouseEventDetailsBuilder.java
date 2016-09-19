package com.vaadin.v7.addon.charts.client.ui;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
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

import com.google.gwt.user.client.ui.UIObject;
import com.vaadin.v7.addon.charts.shared.MouseEventDetails;
import com.vaadin.v7.addon.charts.shared.MouseEventDetails.MouseButton;

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
     *            {@link MouseEventDetails#getxValue()} and
     *            {@link MouseEventDetails#getyValue()}
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
     *            {@link MouseEventDetails#getxValue()} and
     *            {@link MouseEventDetails#getyValue()}
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
