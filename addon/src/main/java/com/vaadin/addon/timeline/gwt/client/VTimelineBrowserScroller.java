package com.vaadin.addon.timeline.gwt.client;

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

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.Util;

/**
 * VTimelineBrowserScroller is the scroller widget in timeline browser.
 * 
 */
public class VTimelineBrowserScroller extends Widget {
    private static final String CLASSNAME_SCROLLAREA_CONTAINER = VTimelineWidget.BROWSER_CLASSNAME
            + "-scrollarea";

    private static final String CLASSNAME_SCROLLAREA_CONTENT = VTimelineWidget.BROWSER_CLASSNAME
            + "-scrollarea-content";

    private static final String CLASSNAME_SCROLLAREA_BAR = CLASSNAME_SCROLLAREA_CONTAINER
            + "-bar";

    private static final String CLASSNAME_SCROLLAREA_BAR_GRIP = CLASSNAME_SCROLLAREA_BAR
            + "-grip";

    private static final String CLASSNAME_SCROLLAREA_BAR_LEFT = CLASSNAME_SCROLLAREA_BAR
            + "-left";

    private static final String CLASSNAME_SCROLLAREA_BAR_RIGHT = CLASSNAME_SCROLLAREA_BAR
            + "-right";

    private static final String CLASSNAME_SIZE_ADJUSTER = "v-size-adjuster";

    private static final String CLASSNAME_SIZE_ADJUSTER_LEFT = CLASSNAME_SIZE_ADJUSTER
            + "-left";

    private static final String CLASSNAME_SIZE_ADJUSTER_RIGHT = CLASSNAME_SIZE_ADJUSTER
            + "-right";

    public static final int CANVAS_OFFSET_LEFT_PIXELS = 14;

    private final Element scrollAreaContainer;
    private final Element scrollAreaContent;

    private final Element scrollAreaBar;
    private final Element scrollAreaBarGrip;
    private final Element scrollAreaBarLeft;
    private final Element scrollAreaBarRight;

    private final Element scrollAreaLeftAdjuster;
    private final Element scrollAreaRightAdjuster;

    /**
     * Default constructor
     */
    public VTimelineBrowserScroller() {
        scrollAreaContainer = DOM.createDiv();
        scrollAreaContainer.setClassName(CLASSNAME_SCROLLAREA_CONTAINER);

        scrollAreaContent = DOM.createDiv();
        scrollAreaContent.setClassName(CLASSNAME_SCROLLAREA_CONTENT);

        scrollAreaBar = DOM.createDiv();
        scrollAreaBar.setClassName(CLASSNAME_SCROLLAREA_BAR);

        scrollAreaBarLeft = DOM.createDiv();
        scrollAreaBarLeft.setClassName(CLASSNAME_SCROLLAREA_BAR_LEFT);
        scrollAreaBar.appendChild(scrollAreaBarLeft);

        scrollAreaBarRight = DOM.createDiv();
        scrollAreaBarRight.setClassName(CLASSNAME_SCROLLAREA_BAR_RIGHT);
        scrollAreaBar.appendChild(scrollAreaBarRight);

        scrollAreaBarGrip = DOM.createDiv();
        scrollAreaBarGrip.setClassName(CLASSNAME_SCROLLAREA_BAR_GRIP);
        scrollAreaBar.appendChild(scrollAreaBarGrip);

        scrollAreaContainer.appendChild(scrollAreaContent);
        scrollAreaContainer.appendChild(scrollAreaBar);

        scrollAreaLeftAdjuster = DOM.createDiv();
        scrollAreaLeftAdjuster.setClassName(CLASSNAME_SIZE_ADJUSTER + " "
                + CLASSNAME_SIZE_ADJUSTER_LEFT);
        if (BrowserInfo.get().isTouchDevice()) {
            scrollAreaLeftAdjuster.addClassName(CLASSNAME_SIZE_ADJUSTER_LEFT
                    + "-mobile");
        }

        scrollAreaRightAdjuster = DOM.createDiv();
        scrollAreaRightAdjuster.setClassName(CLASSNAME_SIZE_ADJUSTER + " "
                + CLASSNAME_SIZE_ADJUSTER_RIGHT);
        if (BrowserInfo.get().isTouchDevice()) {
            scrollAreaRightAdjuster.addClassName(CLASSNAME_SIZE_ADJUSTER_RIGHT
                    + "-mobile");
        }

        scrollAreaContent.appendChild(scrollAreaLeftAdjuster);
        scrollAreaContent.appendChild(scrollAreaRightAdjuster);

        setElement(scrollAreaContainer);
    }

    /**
     * Get the left position of the selection area relative to the canvas
     * 
     * @return
     */
    public int getLeftPosition() {
        return getElement().getOffsetLeft() - CANVAS_OFFSET_LEFT_PIXELS;
    }

    /**
     * Get the right position of the selection are relative to the canvas
     * 
     * @return
     */
    public int getRightPosition() {
        return getLeftPosition() + getAreaWidth();
    }

    /**
     * Set the left position of the scrolling area relative to the canvas. Does
     * not affect the width of the area
     * 
     * @param pos
     *            The position in pixels relative to the canvas
     */
    public void setLeftPosition(float pos) {
        if (pos < CANVAS_OFFSET_LEFT_PIXELS) {
            pos = CANVAS_OFFSET_LEFT_PIXELS;
        }
        scrollAreaContainer.getStyle().setLeft(pos, Unit.PX);
    }

    /**
     * Set the right position of the scrolling area relative to the left
     * position. Affects the width of the scrolling area
     * 
     * @param pos
     *            The position relative to the left position in pixels
     */
    public void setRightPosition(int pos) {
        int leftPos = getLeftPosition();
        int xDiff = pos - leftPos;
        setWidth(xDiff);
    }

    /**
     * Center the area on a pixel position relative to the canvas.
     * 
     * @param pos
     *            The position relative to the canvas in pixels to center on
     */
    public void center(float pos) {
        int width = getAreaWidth();
        assert (width > 0);
        setLeftPosition(pos - (int) Math.floor(width / 2.0));
        setWidth(width);
    }

    /**
     * Get the width of the selected area in pixels
     * 
     * @return
     */
    public int getAreaWidth() {
        int width = Util.getRequiredWidth(scrollAreaContainer);
        assert (width > 0);
        return width;
    }

    public int getAreaContentWidth() {
        return Util.getRequiredWidth(scrollAreaContent);
    }

    public int getRightHandleWidth() {
        return scrollAreaRightAdjuster.getOffsetWidth();
    }

    /**
     * Moves scroller given amount of pixels. If amount is positive, scroller is
     * moved to right, if negative scroller is moved to left.
     * 
     * @param amount
     */

    public void move(int amount) {
        int scrollerPos = scrollAreaContainer.getOffsetLeft();
        scrollerPos += amount;
        setLeftPosition(scrollerPos);
    }

    /**
     * Adjust the left edge of the selection with an amount of pixels
     * 
     * @param amount
     *            The amount of pixels to adjust with. Negative pixels move the
     *            edge left while positive pixels move the edge right
     */
    public void adjustLeftSideSize(int amount) {
        scrollAreaContent.getStyle().setLeft(amount, Unit.PX);
        adjustRightSideSize(-amount);
    }

    /**
     * Adjust the right edge of the selection with an amount of pixels
     * 
     * @param amount
     *            The amount of pixels to adjust with. Negative pixels move the
     *            edge left while position pixels move the edge right
     */
    public void adjustRightSideSize(int amount) {
        int newWidth = getAreaWidth() + amount;
        if (newWidth > 0) {
            scrollAreaContent.getStyle().setWidth(newWidth, Unit.PX);
        }
    }

    /**
     * Get the amount of pixels from the left edge of the canvas
     * 
     * @param event
     *            The event which should be measured
     * @return
     */
    public int getMouseOffset(NativeEvent event) {
        return Util.getTouchOrMouseClientX(event)
                - getElement().getOffsetLeft();
    }

    /**
     * Is the mouse event over the scroller?
     * 
     * @param mouseEvent
     *            The event to check
     * @return
     */
    public boolean isMouseOverScrollElement(NativeEvent mouseEvent) {
        Element mouseOver = (Element) Element.as(mouseEvent.getEventTarget());
        return mouseOver == scrollAreaBar || mouseOver == scrollAreaBarGrip
                || mouseOver == scrollAreaBarLeft
                || mouseOver == scrollAreaBarRight;
    }

    /**
     * Is the mouse event over the selection area?
     * 
     * @param mouseEvent
     *            The event the check
     * @return
     */
    public boolean isMouseOverScrollArea(NativeEvent mouseEvent) {
        Element mouseOver = (Element) Element.as(mouseEvent.getEventTarget());
        return mouseOver == scrollAreaContainer
                || mouseOver == scrollAreaContent;

    }

    /**
     * Is the mouse event over the left edge adjustment handle?
     * 
     * @param mouseEvent
     *            The event to check
     * @return
     */
    public boolean isMouseOverLeftSideSizeAdjuster(NativeEvent mouseEvent) {
        Element mouseOver = (Element) Element.as(mouseEvent.getEventTarget());
        return mouseOver == scrollAreaLeftAdjuster;
    }

    /**
     * Is the mouse event over the right edge adjustment handle?
     * 
     * @param mouseEvent
     *            The event to check
     * @return
     */
    public boolean isMouseOverRightSideSizeAdjuster(NativeEvent mouseEvent) {
        Element mouseOver = (Element) Element.as(mouseEvent.getEventTarget());
        return mouseOver == scrollAreaRightAdjuster;
    }

    /**
     * Set the width of the scroller
     * 
     * @param width
     *            The width in pixels
     */
    public void setWidth(int width) {
        if (width > 0) {
            scrollAreaContent.getStyle().setWidth(width, Unit.PX);
            scrollAreaBar.getStyle().setWidth(width, Unit.PX);
            scrollAreaContainer.getStyle().setWidth(width, Unit.PX);
        }
    }

    /**
     * Sets the width of the selection area by measuring the scroll area
     * elements
     */
    public void lockSize() {
        int width = scrollAreaContent.getClientWidth();
        if (width > 0) {
            setWidth(width);
            int left = scrollAreaContainer.getOffsetLeft()
                    + scrollAreaContent.getOffsetLeft();
            setLeftPosition(left);
            scrollAreaContent.getStyle().setLeft(0, Unit.PX);
        }
    }

    /**
     * Does the scroller DOM contain an element
     * 
     * @param elem
     *            The element to check
     * @return
     */
    public boolean hasElement(com.google.gwt.dom.client.Element elem) {
        if (elem == getElement() || elem == scrollAreaBar
                || elem == scrollAreaBarGrip || elem == scrollAreaContainer
                || elem == scrollAreaContent || elem == scrollAreaLeftAdjuster
                || elem == scrollAreaRightAdjuster || elem == scrollAreaBarLeft
                || elem == scrollAreaBarRight) {
            return true;
        } else {
            return false;
        }
    }

    public void setLeftAdjustmentHandleVisible(boolean visible) {
        if (visible) {
            scrollAreaLeftAdjuster.getStyle().clearOpacity();
        } else {
            scrollAreaLeftAdjuster.getStyle().setOpacity(0);
        }
    }

    public void setRightAdjustmentHandleVisible(boolean visible) {
        if (visible) {
            scrollAreaRightAdjuster.getStyle().clearOpacity();
        } else {
            scrollAreaRightAdjuster.getStyle().setOpacity(0);
        }
    }
}
