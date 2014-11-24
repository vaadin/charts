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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.Graph;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.PlottingListener;
import com.vaadin.addon.timeline.gwt.client.VTimelineDisplay.PlotMode;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.Util;

/**
 * VTimelineBrowser
 * 
 */

public class VTimelineBrowser extends VTimelineCanvasComponent implements
        VDataListener, MouseDownHandler, MouseMoveHandler, MouseUpHandler,
        MouseWheelHandler, DoubleClickHandler, NativePreviewHandler,
        TouchStartHandler, TouchMoveHandler, TouchEndHandler {

    private static final String CLASSNAME_CANVAS = VTimelineWidget.BROWSER_CLASSNAME
            + "-canvas";
    private static final String CLASSNAME_SCROLLBAR = VTimelineWidget.BROWSER_CLASSNAME
            + "-scrollbar";
    private static final String CLASSNAME_SCROLLBAR_LEFT = CLASSNAME_SCROLLBAR
            + "-left";
    private static final String CLASSNAME_SCROLLBAR_RIGHT = CLASSNAME_SCROLLBAR
            + "-right";
    private static final String CLASSNAME_FADE = CLASSNAME_SCROLLBAR + "-fade";
    private static final String CLASSNAME_SCALE = VTimelineWidget.BROWSER_CLASSNAME
            + "-scale";
    private static final String CLASSNAME_SCALELABEL = CLASSNAME_SCALE
            + "-label";

    private static final String CLASSNAME_CURTAIN = VTimelineWidget.BROWSER_CLASSNAME
            + "-curtain";

    private final Element browserRoot;

    private final Canvas canvas;

    private final Element scrollBar;

    private final Element scrollLeft;
    private final Element scrollRight;

    private boolean mouseDown;
    private boolean mouseIsActive = false;

    private final VTimelineWidget timelineWidget;

    private boolean sizeAdjustLeft;
    private boolean sizeAdjustRight;

    private int dragStartX;

    private PlotMode currentMode;

    private final VTimelineBrowserScroller scroller = new VTimelineBrowserScroller();

    private final VCanvasPlotter plotter;

    private final Map<Integer, List<Float>> currentValues = new HashMap<Integer, List<Float>>();
    private final Map<Integer, List<Date>> currentDates = new HashMap<Integer, List<Date>>();
    private final Map<Integer, Float> currentMax = new HashMap<Integer, Float>();
    private final Map<Integer, Float> currentMin = new HashMap<Integer, Float>();
    private final Map<Long, Integer> requestGraphMap = new HashMap<Long, Integer>();

    private final Element leftFade;
    private final Element rightFade;

    private int graphDataRecievedCounter = 0;
    private float currentTotalMin = 0f;
    private float currentTotalMax = 0f;

    private Date selectedStartDate = null;
    private Date selectedEndDate = null;

    private final AbsolutePanel horizontalScalePanel;
    private final List<Label> horizontalScaleComponents = new ArrayList<Label>();

    private final HTML disabledCurtain;

    public VTimelineBrowser(VTimelineWidget tw) {
        super(tw);
        timelineWidget = tw;

        browserRoot = DOM.createDiv();
        setElement(browserRoot);

        setHeight("64px");
        setStyleName("v-timeline-widget-browser");

        canvas = new Canvas(300, 44);
        canvas.setStyleName(CLASSNAME_CANVAS);
        browserRoot.appendChild(canvas.getElement());

        plotter = new VCanvasPlotter(canvas);

        // Add the horizontal scale
        horizontalScalePanel = new AbsolutePanel();
        horizontalScalePanel.setStyleName(CLASSNAME_SCALE);
        browserRoot.appendChild(horizontalScalePanel.getElement());

        // Add fadouts
        leftFade = DOM.createDiv();
        leftFade.setClassName(CLASSNAME_FADE);
        browserRoot.appendChild(leftFade);

        rightFade = DOM.createDiv();
        rightFade.setClassName(CLASSNAME_FADE);
        browserRoot.appendChild(rightFade);

        // Add the scrollbar
        scrollBar = DOM.createDiv();
        scrollBar.setClassName(CLASSNAME_SCROLLBAR);

        scrollLeft = DOM.createDiv();
        scrollLeft.setClassName(CLASSNAME_SCROLLBAR_LEFT);

        scrollRight = DOM.createDiv();
        scrollRight.setClassName(CLASSNAME_SCROLLBAR_RIGHT);

        scrollBar.appendChild(scrollLeft);
        scrollBar.appendChild(scrollRight);

        browserRoot.appendChild(scrollBar);
        browserRoot.appendChild(scroller.getElement());

        scroller.setVisible(false);

        // Create the disabled curtain
        disabledCurtain = new HTML("");
        disabledCurtain.setVisible(false);
        disabledCurtain.setStyleName(CLASSNAME_CURTAIN);
        disabledCurtain.setWidth("100%");
        disabledCurtain.setHeight("100%");
        disabledCurtain.getElement().getStyle().setZIndex(2);
        browserRoot.appendChild(disabledCurtain.getElement());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        super.onLoad();
        // Handlers are removed by super.onUnload()
        handlers.add(addDomHandler(this, MouseDownEvent.getType()));
        handlers.add(addDomHandler(this, MouseDownEvent.getType()));
        handlers.add(addDomHandler(this, MouseUpEvent.getType()));
        handlers.add(addDomHandler(this, MouseMoveEvent.getType()));
        handlers.add(addDomHandler(this, MouseWheelEvent.getType()));
        handlers.add(addDomHandler(this, DoubleClickEvent.getType()));
        handlers.add(Event.addNativePreviewHandler(this));
        handlers.add(addDomHandler(this, TouchStartEvent.getType()));
        handlers.add(addDomHandler(this, TouchMoveEvent.getType()));
        handlers.add(addDomHandler(this, TouchEndEvent.getType()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
     */
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        canvas.setWidth((getCanvasWidth() - 1) + "px");
        browserRoot.setAttribute("width", getCanvasWidth() + "px");
        if (!timelineWidget.isInitDone()) {
            // Ensure scroller is initially full size
            scroller.setLeftPosition(0);
            scroller.setRightPosition(getCanvasWidth());
        }
    }

    /**
     * Initializes the canvas i.e. it will fetch the points for the whole
     * timeline and render it to the canvas.
     */
    public void refresh() {
        if (!isVisible() || timelineWidget.getStartDate() == null
                || timelineWidget.getEndDate() == null
                || timelineWidget.getNumGraphs() == 0) {
            return;
        }

        // Remove old data
        clear();

        // Get the points
        timelineWidget.getDateDataAll(this, timelineWidget.getStartDate(),
                timelineWidget.getEndDate(), 1);
    }

    /**
     * Removes all stored plotting data
     */
    private void clear() {
        graphDataRecievedCounter = 0;
        currentTotalMax = 0;
        currentTotalMin = 0;
        currentMax.clear();
        currentMin.clear();
        currentValues.clear();
        currentDates.clear();
    }

    /**
     * Redraws the graph without fetching the points from the server. Use this
     * to just redraw the data after a resize for instance
     */
    public void redraw() {

        // Plot the points
        plotData();

        // Set the scroller range
        setRange(selectedStartDate, selectedEndDate);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.trends.ws.client.VDataListener#dataRecieved(java.lang.Long,
     * java.util.List, java.util.List, java.util.Set)
     */
    @Override
    public void dataRecieved(Long requestID, List<Float> values,
            List<Date> dates, Set<String> markers, Set<String> events,
            int density) {

        if (!requestGraphMap.containsKey(requestID)) {
            return;
        }

        int graph = requestGraphMap.get(requestID);
        int count = graphDataRecievedCounter;

        currentValues.put(graph, values);
        currentDates.put(graph, dates);
        currentMin.put(graph, VTimelineWidget.getMinValue(values));
        currentMax.put(graph, VTimelineWidget.getMaxValue(values));

        if (currentMin.get(graph) < currentTotalMin) {
            currentTotalMin = currentMin.get(graph);
        }

        if (currentMax.get(graph) > currentTotalMax) {
            currentTotalMax = currentMax.get(graph);
        }

        int totalGraphs = timelineWidget.getNumGraphs() - 1;
        if (count == totalGraphs) {
            // All graph data is recieved, we can PLOT!
            graphDataRecievedCounter = 0;

            // Plot the recieved data
            plotData();

        } else {
            graphDataRecievedCounter = count + 1;
        }
    }

    /**
     * Plots the horizontal scale
     */
    private void plotHorizontalScale(float xUnit, long startTime, long endTime) {
        long timeDiff = endTime - startTime;

        for (Label lbl : horizontalScaleComponents) {
            horizontalScalePanel.remove(lbl);
        }
        horizontalScaleComponents.clear();

        // Selections is less then 100ms
        if (timeDiff <= 100L) {
            plotHorizontalScale(startTime, endTime, 10, xUnit, true);
        }

        // Selections is less then a half of a second
        else if (timeDiff <= 500L) {
            plotHorizontalScale(startTime, endTime, 50, xUnit, true);
        }

        // Selections is less then a second
        else if (timeDiff <= VDateFormatInfo.SECOND) {
            plotHorizontalScale(startTime, endTime, 100, xUnit, true);
        }

        // Selection is less then a half minute
        else if (timeDiff <= VDateFormatInfo.SECOND * 30L) {
            plotHorizontalScale(startTime, endTime, 5 * VDateFormatInfo.SECOND,
                    xUnit, true);
        }

        // Selection is less than a minute
        if (timeDiff <= VDateFormatInfo.MINUTE) {
            plotHorizontalScale(startTime, endTime,
                    10 * VDateFormatInfo.SECOND, xUnit, true);
        }

        // Selection is less than five minutes
        else if (timeDiff <= 5 * VDateFormatInfo.MINUTE) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.MINUTE,
                    xUnit, true);
        }

        // Selection is less than 30 minutes
        else if (timeDiff <= 30 * VDateFormatInfo.MINUTE) {
            plotHorizontalScale(startTime, endTime, 5 * VDateFormatInfo.MINUTE,
                    xUnit, true);
        }

        // Selection is less than 1 hour
        else if (timeDiff <= VDateFormatInfo.HOUR) {
            plotHorizontalScale(startTime, endTime,
                    10 * VDateFormatInfo.MINUTE, xUnit, true);
        }

        // Selection is less then 6 hours
        else if (timeDiff <= 6 * VDateFormatInfo.HOUR) {
            plotHorizontalScale(startTime, endTime,
                    30 * VDateFormatInfo.MINUTE, xUnit, true);
        }

        // Selection is less then a half day
        else if (timeDiff <= 12 * VDateFormatInfo.HOUR) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.HOUR,
                    xUnit, false);
        }

        // Selection is less than a day
        else if (timeDiff <= VDateFormatInfo.DAY) {
            plotHorizontalScale(startTime, endTime, 2 * VDateFormatInfo.HOUR,
                    xUnit, true);
        }

        // Selection is less than 3 days
        else if (timeDiff <= 3 * VDateFormatInfo.DAY) {
            plotHorizontalScale(startTime, endTime, 6 * VDateFormatInfo.HOUR,
                    xUnit, true);
        }

        // Selection is less than a week. Show dayly view
        else if (timeDiff <= VDateFormatInfo.WEEK) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.DAY, xUnit,
                    false);
        }

        // Selection is less than two weeks
        else if (timeDiff <= 2 * VDateFormatInfo.WEEK) {
            plotHorizontalScale(startTime, endTime, 3 * VDateFormatInfo.DAY,
                    xUnit, true);
        }

        // Selection is less than a month. Show weekly view
        else if (timeDiff <= 2 * VDateFormatInfo.MONTH) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.WEEK,
                    xUnit, true);
        }

        // Selection is less than a year
        else if (timeDiff <= 6 * VDateFormatInfo.MONTH) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.MONTH,
                    xUnit, false);
        }

        // Selection is less than a year
        else if (timeDiff <= VDateFormatInfo.YEAR) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.MONTH,
                    xUnit, false);
        }

        // Selection is more than two years
        else {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.YEAR,
                    xUnit, false);
        }
    }

    @SuppressWarnings("deprecation")
    private void plotHorizontalScale(long startTime, long endTime,
            long unitTime, float xUnit, boolean leftAlign) {

        float width = unitTime * xUnit;
        boolean shortDateFormat = width < 100;
        int year = timelineWidget.getStartDate().getYear();
        long time = (new Date(year, 0, 1)).getTime();
        DateTimeFormat formatter = shortDateFormat ? timelineWidget
                .getDateFormats().getShortDateFormatter(unitTime)
                : timelineWidget.getDateFormats()
                        .getLongDateFormatter(unitTime);

        canvas.setLineWidth(1);
        canvas.setStrokeStyle("rgb(200,200,200)");
        canvas.beginPath();

        long stepsUntilInRange = (startTime - time) / unitTime;
        time += stepsUntilInRange * unitTime;

        while (time <= endTime) {
            if (time >= startTime - unitTime && time <= endTime + unitTime) {
                Label lbl = new Label();
                lbl.setStyleName(CLASSNAME_SCALELABEL);
                lbl.setWidth(width + "px");
                Date date = new Date(time);
                lbl.setText(formatter.format(date));

                long timeFromStart = time - startTime;
                float x = timeFromStart * xUnit;

                canvas.moveTo(x, 0);
                canvas.lineTo(x, canvas.getHeight());

                horizontalScalePanel.add(
                        lbl,
                        (int) x + 2,
                        horizontalScalePanel.getOffsetHeight()
                                - scrollBar.getOffsetHeight() - 13);
                horizontalScaleComponents.add(lbl);
            }

            if (unitTime == VDateFormatInfo.MONTH) {
                /*
                 * Month resolution is not so easy since it changes depending on
                 * the month. We use the Date to resolve the new time
                 */
                time += DateTimeService.getNumberOfDaysInMonth(new Date(time))
                        * VDateFormatInfo.DAY;
            } else if (unitTime == VDateFormatInfo.YEAR) {
                /*
                 * Take leap years into account
                 */
                if (DateTimeService.isLeapYear(new Date(time))) {
                    time += unitTime + VDateFormatInfo.DAY;
                } else {
                    time += unitTime;
                }

            } else {
                time += unitTime;
            }
        }

        canvas.closePath();
        canvas.stroke();
    }

    /**
     * Plots the recieved data on to the canvas
     */
    private void plotData() {
        if (!isVisible()) {
            return;
        }

        // Clear old drawings
        canvas.clear();

        // Add listener
        plotter.setListener(new PlottingListener() {
            @Override
            public void plottingStarts() {
                GWT.log(VTimelineBrowser.class.getName() + ": Plotting start");

                // Plot the horizontal scale if some graphs have been plotted
                if (timelineWidget.getNumGraphs() > 0) {
                    plotHorizontalScale(getCanvasXUnit(), timelineWidget
                            .getStartDate().getTime(), timelineWidget
                            .getEndDate().getTime());
                }
            }

            @Override
            public void plottingEnds() {
                // // Nothing to do
                GWT.log(VTimelineBrowser.class.getName() + ": Plotting ends");
            }
        });

        plotter.setUseShadows(timelineWidget.isGraphShadows());

        // Plot the graphs
        super.plotData(plotter, Collections.unmodifiableMap(currentValues),
                Collections.unmodifiableMap(currentDates));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * getLineThickness(int)
     */
    @Override
    protected double getLineThickness(int graphIndex) {
        // Static width of two pixels
        return 2;
    }

    /**
     * Set the selected range
     * 
     * @param startDate
     *            The start date of the range
     * @param endDate
     *            The end date of the range
     */
    public void setRange(Date startDate, Date endDate) {
        scroller.setVisible(true);
        Date start = timelineWidget.getStartDate();
        Date end = timelineWidget.getEndDate();

        if (startDate == null || startDate.before(start)) {
            startDate = start;
        }

        if (endDate == null || endDate.after(end)) {
            endDate = end;
        }

        Long timeDiff = end.getTime() - start.getTime();
        if (timeDiff == 0) {
            // Ignore ranges with the same date
            return;
        }

        float canvasWidth = getCanvasWidth();
        Float timeUnit = canvasWidth / (float) timeDiff;
        Long startTime = startDate.getTime() - start.getTime();
        int startPixel = (int) Math.ceil(startTime * timeUnit)
                + Util.measureMarginLeft(canvas.getElement());
        if (startPixel >= getCanvasWidth()) {
            startPixel = getCanvasWidth();
        }

        Long endTime = endDate.getTime() - start.getTime();
        int endPixel = (int) Math.ceil(endTime * timeUnit);
        if (endPixel >= getCanvasWidth()) {
            endPixel = getCanvasWidth();
        }

        scroller.setLeftPosition(startPixel);
        scroller.setRightPosition(endPixel);

        selectedStartDate = calculateStartPoint();
        selectedEndDate = calculateEndPoint();

        adjustFadouts();
    }

    /**
     * Converts the selectors start pixel position to a date
     * 
     * @return The calculated date
     */
    private Date calculateStartPoint() {
        Date start = timelineWidget.getStartDate();
        Date end = timelineWidget.getEndDate();
        Float canvasWidth = (float) getCanvasWidth();
        Long timeDiff = end.getTime() - start.getTime();
        Float timeUnit = timeDiff.floatValue() / canvasWidth;

        int selectorLeftPixel = scroller.getLeftPosition();

        /*
         * Add some right buffer in the end so it is easier to view the last
         * point. Using 2 pixel proximity to right edge.
         */
        if (selectorLeftPixel <= 0) {
            selectorLeftPixel -= 5;
        }

        Float time = selectorLeftPixel * timeUnit;
        Date date = new Date(start.getTime() + time.longValue());

        return date;
    }

    /**
     * Converts the selectors end pixel position to a date
     * 
     * @return The calculated date
     */
    private Date calculateEndPoint() {
        Date start = timelineWidget.getStartDate();
        Date end = timelineWidget.getEndDate();
        Float canvasWidth = (float) getCanvasWidth();
        Long timeDiff = end.getTime() - start.getTime();
        Float timeUnit = timeDiff.floatValue() / canvasWidth;

        int selectorRightPixel = scroller.getRightPosition();

        /*
         * Add some right buffer in the end so it is easier to view the last
         * point. Using 2 pixel proximity to right edge.
         */
        if (getCanvasWidth() - selectorRightPixel < 2) {
            selectorRightPixel += 5;
        }

        Float time = selectorRightPixel * timeUnit;
        Date date = new Date(start.getTime() + time.longValue());

        return date;
    }

    /**
     * Alias for calculateStartPoint
     * 
     * @return The calculated date
     */
    public Date getSelectedStartDate() {
        return selectedStartDate;
    }

    /**
     * Alias for calculateEndPoint
     * 
     * @return The calculated date
     */
    public Date getSelectedEndDate() {
        return selectedEndDate;
    }

    /**
     * Get the canvas width
     * 
     * @return The widht in pixels
     */
    public int getCanvasWidth() {
        return getOffsetWidth()
                - VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS * 2 - 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.trends.ws.client.VDataListener#setCurrentRequestId(java.lang
     * .Long, int)
     */
    @Override
    public void setCurrentRequestId(Long requestID, Integer graphIndex) {
        requestGraphMap.put(requestID, graphIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.trends.ws.client.VDataListener#dataRecievedAll(java.util.List,
     * java.util.Map, java.util.Map, java.util.Set, java.util.Set,
     * java.util.Map, java.util.Map)
     */
    @Override
    public void dataRecievedAll(List<Long> requestID,
            Map<Integer, List<Float>> values, Map<Integer, List<Date>> dates,
            Set<String> markers, Set<String> events, Map<Integer, Float> min,
            Map<Integer, Float> max, Float currentTotalMinimum,
            Float currentTotalMaximum, int minDensity) {

        currentTotalMin = currentTotalMinimum;
        currentTotalMax = currentTotalMaximum;

        for (int g = 0; g < timelineWidget.getNumGraphs(); g++) {
            if (!timelineWidget.graphIsVisible(g)) {
                continue;
            }

            List<Float> fvalues = values.get(g);
            List<Date> dvalues = dates.get(g);
            Float minimum = min.get(g);
            Float maximum = max.get(g);

            if (fvalues != null && dvalues != null && minimum != null
                    && maximum != null) {
                currentValues.put(g, fvalues);
                currentDates.put(g, dvalues);
                currentMin.put(g, minimum);
                currentMax.put(g, maximum);
            }
        }

        plotData();

        if (timelineWidget.isInitDone()) {
            refreshSelection();
        }
    }

    /**
     * Checks if element exists in the browser
     * 
     * @param elem
     *            The element
     * @return True if the element exists, else false
     */
    public boolean hasElement(com.google.gwt.dom.client.Element elem) {
        if (elem == getElement() || elem == browserRoot || elem == leftFade
                || elem == rightFade || elem == scrollBar || elem == scrollLeft
                || elem == scrollRight || elem == canvas.getElement()
                || elem == horizontalScalePanel.getElement()
                || scroller.hasElement(elem)
                || elem == disabledCurtain.getElement()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Enables the browser
     * 
     * @param enabled
     *            Is the browser usable by the user
     */
    public void setEnabled(boolean enabled) {
        disabledCurtain.setVisible(!enabled);
        if (!scroller.isVisible()) {
            /*
             * This happens if initially the graph did not have any data so
             * setRange was never called.
             */
            scroller.setVisible(true);
            scroller.setLeftPosition(0);
            scroller.setRightPosition(getCanvasWidth());
        }
    }

    public boolean isEnabled() {
        return !disabledCurtain.isVisible();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseUpHandler#onMouseUp(com.google.gwt
     * .event.dom.client.MouseUpEvent)
     */
    @Override
    public void onMouseUp(MouseUpEvent event) {
        if (isEnabled()) {
            event.preventDefault();
            stopDrag(event.getNativeEvent());
        }
    }

    private void stopDrag(NativeEvent event) {
        /*
         * Note, event might be null from preview handler
         */
        mouseIsActive = false;

        DOM.releaseCapture(horizontalScalePanel.getElement());

        if (sizeAdjustLeft || sizeAdjustRight) {
            /*
             * Resize ended
             */
            scroller.lockSize();
        }

        selectedStartDate = calculateStartPoint();
        selectedEndDate = calculateEndPoint();
        timelineWidget.setDisplayRange(selectedStartDate, selectedEndDate);

        adjustFadouts();

        sizeAdjustLeft = false;
        sizeAdjustRight = false;
    }

    private void adjustFadouts() {
        int leftFadeWidth = scroller.getLeftPosition()
                + VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS;
        int rightFadeWidth = getOffsetWidth() - scroller.getRightPosition() - 2
                * VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS;
        int rightFadeLeft = scroller.getRightPosition()
                + VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS;
        if (leftFadeWidth > 0 && rightFadeWidth > 0) {
            leftFade.getStyle().setWidth(leftFadeWidth, Unit.PX);
            rightFade.getStyle().setLeft(rightFadeLeft, Unit.PX);
            rightFade.getStyle().setWidth(rightFadeWidth, Unit.PX);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseMoveHandler#onMouseMove(com.google
     * .gwt.event.dom.client.MouseMoveEvent)
     */
    @Override
    public void onMouseMove(MouseMoveEvent event) {
        if (isEnabled()) {
            NativeEvent mouseEvent = event.getNativeEvent();
            if (mouseDown) {
                moveDragging(mouseEvent);
            }
        }
    }

    private void moveDragging(NativeEvent event) {
        int adjustment = Util.getTouchOrMouseClientX(event) - dragStartX;
        if (sizeAdjustLeft && scroller.getLeftPosition() + adjustment > 0) {
            /*
             * Adjusting left edge
             */
            scroller.adjustLeftSideSize(adjustment);
            if (BrowserInfo.get().isTouchDevice()) {
                scroller.setLeftAdjustmentHandleVisible(false);
            }

        } else if (sizeAdjustLeft) {
            /*
             * Adjusting left edge
             */
            scroller.adjustLeftSideSize(-scroller.getLeftPosition());
            if (BrowserInfo.get().isTouchDevice()) {
                scroller.setLeftAdjustmentHandleVisible(false);
            }

        } else if (sizeAdjustRight
                && scroller.getRightPosition() + adjustment < scrollBar
                        .getOffsetWidth()
                        - 2
                        * VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS) {
            /*
             * Adjusting right edge
             */
            scroller.adjustRightSideSize(adjustment);
            if (BrowserInfo.get().isTouchDevice()) {
                scroller.setRightAdjustmentHandleVisible(false);
            }

        } else if (sizeAdjustRight) {
            /*
             * Adjusting right edge
             */
            int diff = (scrollBar.getOffsetWidth() - 2 * VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS)
                    - scroller.getRightPosition();
            scroller.adjustRightSideSize(diff - 1);
            if (BrowserInfo.get().isTouchDevice()) {
                scroller.setRightAdjustmentHandleVisible(false);
            }

        } else {
            /*
             * Dragging the whole selection box
             */
            adjustment = scroller.getMouseOffset(event) - dragStartX;
            scroll(adjustment);
            dragStartX = scroller.getMouseOffset(event);
        }

        adjustFadouts();
    }

    public void scrollLeft(boolean boost) {
        scroll(boost ? -10 : -5);
    }

    public void scrollRight(boolean boost) {
        scroll(boost ? 10 : 5);
    }

    private void scroll(int adjustment) {
        int left = scroller.getLeftPosition() + adjustment;
        int right = left + scroller.getAreaContentWidth()
                - scroller.getRightHandleWidth() / 2;
        if (right < getCanvasWidth()) {

            scroller.setLeftPosition(left
                    + VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS);

            selectedStartDate = calculateStartPoint();
            selectedEndDate = calculateEndPoint();

            timelineWidget.setDisplayRange(selectedStartDate, selectedEndDate);
            adjustFadouts();
        }
    }

    public void zoomIn(int adjustment) {
        zoom(adjustment);
    }

    public void zoomOut(int adjustment) {
        zoom(-adjustment);
    }

    private void zoom(int adjustment) {
        int availableLeftSpace = scroller.getLeftPosition();
        int availableRightSpace = getCanvasWidth()
                - scroller.getRightPosition();
        int availableWidth = scroller.getAreaContentWidth();

        if (availableWidth - adjustment * 2 < 5) {
            /*
             * Minimum reached, prevent zooming
             */
            return;
        }

        if (adjustment > 0
                || (availableLeftSpace > 0 && availableLeftSpace - adjustment >= 0)) {
            scroller.adjustLeftSideSize(adjustment);
        } else {
            scroller.setLeftPosition(0);
        }

        if (adjustment > 0
                || (availableRightSpace > 0 && availableRightSpace + adjustment >= 0)) {
            scroller.adjustRightSideSize(-adjustment * 2);
        } else if (availableLeftSpace > 0
                && availableLeftSpace - adjustment >= 0) {
            scroller.adjustRightSideSize(-adjustment + availableRightSpace);
        }

        refreshSelection();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseDownHandler#onMouseDown(com.google
     * .gwt.event.dom.client.MouseDownEvent)
     */
    @Override
    public void onMouseDown(MouseDownEvent event) {
        if (isEnabled()) {
            NativeEvent mouseEvent = event.getNativeEvent();
            mouseDown = true;
            startDrag(mouseEvent);
        }
    }

    /**
     * Called when a drag operation is started
     * 
     * @param event
     *            The event that triggered the dragging event
     */
    private void startDrag(NativeEvent event) {
        if (!isEnabled()) {
            return;
        }

        timelineWidget.focus();

        mouseIsActive = true;

        event.preventDefault();
        event.stopPropagation();

        DOM.setCapture(horizontalScalePanel.getElement());

        Element mouseOver = (Element) Element.as(event.getEventTarget());

        if (mouseOver == scrollLeft) {
            /*
             * Mouse down on move left button
             */
            sizeAdjustLeft = false;
            sizeAdjustRight = false;
            scrollLeft(false);

        } else if (mouseOver == scrollRight) {
            /*
             * Mouse down on move right button
             */
            sizeAdjustLeft = false;
            sizeAdjustRight = false;
            scrollRight(false);

        } else if (scroller.isMouseOverScrollElement(event)
                || scroller.isMouseOverScrollArea(event)) {
            /*
             * Mouse down on the scroller element
             */
            sizeAdjustLeft = false;
            sizeAdjustRight = false;
            dragStartX = scroller.getMouseOffset(event);

        } else if (scroller.isMouseOverLeftSideSizeAdjuster(event)) {

            /*
             * Mouse down on the left edge of the select
             */
            sizeAdjustRight = false;
            sizeAdjustLeft = true;
            dragStartX = Util.getTouchOrMouseClientX(event);

        } else if (scroller.isMouseOverRightSideSizeAdjuster(event)) {

            /*
             * Mouse down on the right edge of the select
             */
            sizeAdjustLeft = false;
            sizeAdjustRight = true;
            dragStartX = Util.getTouchOrMouseClientX(event);

        } else if (mouseOver == horizontalScalePanel.getElement()
                || mouseOver == leftFade || mouseOver == rightFade) {
            /*
             * Mouse down on the scales, left/right fadeout covers
             */

            sizeAdjustLeft = false;
            sizeAdjustRight = false;

            // Calculate the centering point
            int center = Util.getTouchOrMouseClientX(event) - getAbsoluteLeft();
            int area = scroller.getAreaWidth();

            if (center - area / 2 < VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS) {
                center = area / 2;
            }

            if (center + area / 2 > getOffsetWidth() - 2
                    * VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS) {
                center = getOffsetWidth() - 2
                        * VTimelineBrowserScroller.CANVAS_OFFSET_LEFT_PIXELS
                        - area / 2;
            }

            // Center scoller and set fades
            scroller.center(center);

            adjustFadouts();

            dragStartX = scroller.getMouseOffset(event);

            timelineWidget.setDisplayRange(selectedStartDate, selectedEndDate);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.MouseWheelHandler#onMouseWheel(com.google
     * .gwt.event.dom.client.MouseWheelEvent)
     */
    @Override
    public void onMouseWheel(MouseWheelEvent event) {
        if (isEnabled()) {
            NativeEvent mouseEvent = event.getNativeEvent();
            event.preventDefault();

            boolean up = mouseEvent.getMouseWheelVelocityY() > 0;

            // Only apply event to scroller
            if (!scroller.isMouseOverScrollArea(mouseEvent)) {
                return;
            }

            sizeAdjustLeft = true;
            sizeAdjustRight = true;

            if (up) {
                zoomOut(5);
            } else {
                zoomIn(5);
            }
        }
    }

    /**
     * Refreshes selection, by recalculating position
     */
    public void refreshSelection() {
        if (isVisible()) {
            selectedStartDate = calculateStartPoint();
            selectedEndDate = calculateEndPoint();
        } else {
            selectedStartDate = timelineWidget.getStartDate();
            selectedEndDate = timelineWidget.getEndDate();
        }

        timelineWidget.setDisplayRange(selectedStartDate, selectedEndDate);

        if (!mouseDown) {
            scroller.lockSize();

            adjustFadouts();

            sizeAdjustLeft = false;
            sizeAdjustRight = false;
        }
    }

    /**
     * Sets the chart plotting mode
     * 
     * @param mode
     *            The plotting mode
     */
    public void setChartMode(PlotMode mode, boolean doPlotting) {
        if (mode != null) {
            currentMode = mode;
            if (doPlotting) {
                plotData();
            }
        }
    }

    /**
     * Call this when no graphs are rendered
     * 
     * @param enabled
     */
    public void displayNoDataMessage(boolean enabled) {
        if (enabled) {
            canvas.clear();
            for (Label lbl : horizontalScaleComponents) {
                horizontalScalePanel.remove(lbl);
            }
            horizontalScaleComponents.clear();
            clear();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.user.client.Event.NativePreviewHandler#onPreviewNativeEvent
     * (com.google.gwt.user.client.Event.NativePreviewEvent)
     */
    @Override
    public void onPreviewNativeEvent(NativePreviewEvent event) {
        // Monitor mouse button state
        if (event.getTypeInt() == Event.ONMOUSEUP) {
            mouseDown = false;
            if (mouseIsActive) {
                Scheduler.get().scheduleDeferred(new ScheduledCommand() {
                    @Override
                    public void execute() {
                        stopDrag(null);
                    }
                });

            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.DoubleClickHandler#onDoubleClick(com.
     * google.gwt.event.dom.client.DoubleClickEvent)
     */
    @Override
    public void onDoubleClick(DoubleClickEvent event) {
        NativeEvent mouseEvent = event.getNativeEvent();
        if (scroller.isMouseOverScrollElement(mouseEvent)
                || scroller.isMouseOverScrollArea(mouseEvent)) {
            scroller.setLeftPosition(0);
            scroller.setRightPosition(getCanvasWidth());
            refreshSelection();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getCanvas()
     */
    @Override
    protected Canvas getCanvas() {
        return canvas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * getSelectionStartDate()
     */
    @Override
    protected Date getSelectionStartDate() {
        /*
         * Returning the timelines start date so the plotting renders the whole
         * graph
         */
        return timelineWidget.getStartDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * getSelectionEndDate()
     */
    @Override
    protected Date getSelectionEndDate() {
        /*
         * Returning the timelines start date so the plotting renders the whole
         * graph
         */
        return timelineWidget.getEndDate();
    }

    /**
     * Returns the selections start date
     * 
     * @return
     */
    public Date getSelectionStart() {
        if (selectedStartDate == null) {
            return timelineWidget.getSelectedStartDate();
        }
        return selectedStartDate;
    }

    /**
     * Returns the selection end date
     * 
     * @return
     */
    public Date getSelectionEnd() {
        if (selectedEndDate == null) {
            return timelineWidget.getSelectedEndDate();
        }
        return selectedEndDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * getVerticalScaleMinAndMax()
     */
    @Override
    protected float[] getVerticalScaleMinAndMax() {
        return new float[] { currentTotalMin, currentTotalMax };
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * calculateVerticalZero(float, float)
     */
    @Override
    protected float calculateVerticalZero(float yUnit, float canvasHeight) {
        float zero = canvasHeight;
        float valueDiff = Math.abs(currentTotalMax - currentTotalMin);
        if (currentTotalMin < 0) {
            zero -= (valueDiff / 2.0f) * yUnit;
        }
        return zero;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getPlotMode
     * ()
     */
    @Override
    protected PlotMode getPlotMode() {
        return currentMode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * setCurrentGraphs(java.util.List)
     */
    @Override
    protected void setCurrentGraphs(List<Graph> graphs) {
        // We do not save the graphs to save memory
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getLineCaps
     * (int)
     */
    @Override
    protected boolean getLineCaps(int graphIndex) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getFillColors
     * (int)
     */
    @Override
    protected String getFillColors(int graphIndex) {
        return timelineWidget.getBrowserFillColorMap().get(graphIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getColors
     * (int)
     */
    @Override
    protected String getColors(int graphIndex) {
        return timelineWidget.getBrowserColorMap().get(graphIndex);
    }

    private boolean multitouching = false;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.TouchEndHandler#onTouchEnd(com.google
     * .gwt.event.dom.client.TouchEndEvent)
     */
    @Override
    public void onTouchEnd(TouchEndEvent event) {
        event.preventDefault();
        if (multitouching) {
            /*
             * Adding a timer to prevent single multitouch end events to mess up
             * rendering
             */
            Timer t = new Timer() {
                @Override
                public void run() {
                    multitouching = false;
                }
            };
            t.schedule(500);
        } else if (!multitouching) {
            stopDrag(event.getNativeEvent());
        }

        scroller.setLeftAdjustmentHandleVisible(true);
        scroller.setRightAdjustmentHandleVisible(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.TouchMoveHandler#onTouchMove(com.google
     * .gwt.event.dom.client.TouchMoveEvent)
     */
    @Override
    public void onTouchMove(TouchMoveEvent event) {
        event.preventDefault();
        touchMove(event);
    }

    /**
     * Called when a user is dragging the graph with n-amount of fingers
     * 
     * @param event
     */
    private void touchMove(TouchEvent<?> event) {
        if (event.getTouches().length() > 1) {
            Touch t1 = event.getTouches().get(0);
            Touch t2 = event.getTouches().get(1);

            int left, right;
            if (t1.getClientX() > t2.getClientX()) {
                left = t2.getClientX() - getElement().getAbsoluteLeft();
                right = t1.getClientX() - getElement().getAbsoluteLeft();
            } else {
                left = t1.getClientX() - getElement().getAbsoluteLeft();
                right = t2.getClientX() - getElement().getAbsoluteLeft();
            }

            // Boundary checks
            if (left < 0) {
                left = 0;
            }
            if (right >= getCanvasWidth()) {
                right = getCanvasWidth();
            }

            scroller.setLeftPosition(left);
            scroller.setRightPosition(right);
            scroller.lockSize();
            refreshSelection();

        } else if (!multitouching) {
            moveDragging(event.getNativeEvent());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.TouchStartHandler#onTouchStart(com.google
     * .gwt.event.dom.client.TouchStartEvent)
     */
    @Override
    public void onTouchStart(TouchStartEvent event) {
        event.preventDefault();
        if (event.getTouches().length() > 1) {
            multitouching = true;
            scroller.setLeftAdjustmentHandleVisible(false);
            scroller.setRightAdjustmentHandleVisible(false);
            touchMove(event);
        } else {
            startDrag(event.getNativeEvent());
        }
    }
}
