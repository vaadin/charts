package com.vaadin.addon.timeline.gwt.client;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEvent;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.addon.timeline.gwt.canvas.client.Canvas;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.Graph;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.PlottingListener;
import com.vaadin.addon.timeline.gwt.client.VCanvasPlotter.Point;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.Util;
import com.vaadin.client.VConsole;

/**
 * VTimelineDisplay
 * 
 * @author John Ahlroos / Vaadin Ltd
 * 
 */
public class VTimelineDisplay extends VTimelineCanvasComponent implements
        VDataListener, MouseDownHandler, MouseMoveHandler, MouseWheelHandler,
        NativePreviewHandler, PlottingListener, TouchStartHandler,
        TouchMoveHandler {

    private static final String CLASSNAME_BOTTOMBAR = VTimelineWidget.DISPLAY_CLASSNAME
            + "-bottombar";
    private static final String CLASSNAME_CANVAS = VTimelineWidget.DISPLAY_CLASSNAME
            + "-canvas";
    private static final String CLASSNAME_CANVASDRAG = CLASSNAME_CANVAS
            + "-drag";
    private static final String CLASSNAME_MARKER = VTimelineWidget.CLASSNAME
            + "-marker";
    private static final String CLASSNAME_MARKERTOOLTIP = CLASSNAME_MARKER
            + "-tooltip";
    private static final String CLASSNAME_LOADINGCURTAIN = VTimelineWidget.DISPLAY_CLASSNAME
            + "-curtain";
    private static final String CLASSNAME_EVENT = VTimelineWidget.CLASSNAME
            + "-event";
    private static final String CLASSNAME_SCALEVALUE = VTimelineWidget.DISPLAY_CLASSNAME
            + "-vscale";
    private static final String CLASSNAME_SCALEVALUEDRAG = CLASSNAME_SCALEVALUE
            + "-drag";
    private static final String CLASSNAME_SCALEDATE = VTimelineWidget.DISPLAY_CLASSNAME
            + "-hscale";
    private static final String CLASSNAME_SCALEDATE_LEFT = CLASSNAME_SCALEDATE
            + "-left";
    private static final String CLASSNAME_DOT = VTimelineWidget.DISPLAY_CLASSNAME
            + "-dot";
    private static final String CLASSNAME_BAR = VTimelineWidget.DISPLAY_CLASSNAME
            + "-bar";

    private final List<Dot> dots = new ArrayList<Dot>();
    private final List<HTML> bars = new ArrayList<HTML>();

    private final VTimelineWidget widget;

    private final Element browserRoot;
    private final AbsolutePanel displayComponentPanel;

    private final Canvas canvas;
    private final VCanvasPlotter plotter;

    private final HTML bottomBar;

    private final Map<Integer, List<Float>> currentValues = new HashMap<Integer, List<Float>>();
    private final Map<Integer, List<Date>> currentDates = new HashMap<Integer, List<Date>>();
    private final Map<Integer, Float> currentMax = new HashMap<Integer, Float>();
    private final Map<Integer, Float> currentMin = new HashMap<Integer, Float>();

    private List<Graph> currentGraphs = new ArrayList<VCanvasPlotter.Graph>();

    private final Set<String> currentEvents = new HashSet<String>();
    private final Map<Button, List<Integer>> currentEventMap = new HashMap<Button, List<Integer>>();
    private final Map<Button, List<Date>> currentEventDates = new HashMap<Button, List<Date>>();

    private final Map<String, HTML> markerMap = new HashMap<String, HTML>();
    private final Map<String, FlexTable> markerTooltipMap = new HashMap<String, FlexTable>();

    private final List<Button> events = new ArrayList<Button>();
    private final List<Integer> eventCoordinates = new ArrayList<Integer>();

    private float currentTotalMin = 0f;
    private float currentTotalMax = 0f;

    // The selected date range
    private Date currentStartDate = null;
    private Date currentEndDate = null;

    // The date range returned from server when
    // the above was requested
    private Date currentRealStartDate = null;
    private Date currentRealEndDate = null;

    // Request mapping
    private final Map<Long, Integer> requestGraphMap = new HashMap<Long, Integer>();

    // Mouse actions
    private boolean mouseIsDown = false;
    private boolean mouseOrTouchActive = false;
    private int mouseDownX = 0;
    private int lastMouseX = 0;

    // States
    private boolean enabled = true;
    private PlotMode currentMode;
    private boolean forcePlot = false;

    // Counters
    private int graphDataRecievedCounter = 0;

    // Dragging
    private Date currentStartDragDate = null;
    private Date currentEndDragDate = null;

    // Vertical scale
    private Float verticalScaleMin = null;
    private Float verticalScaleMax = null;

    // Curtains
    private final HTML loadingCurtain;
    private final HTML disabledCurtain;

    // Scale components lists
    private final List<Label> horizontalScaleComponents = new ArrayList<Label>();
    private final List<Label> verticalScaleComponents = new ArrayList<Label>();

    // Error and loading messages
    private final HTML noDataLabel = new HTML();

    private float fittedVerticalScaleMinimum = Float.MAX_VALUE;
    private float fittedVerticalScaleMaximum = Float.MIN_VALUE;

    private float[] verticalScaleLines;

    // Graph formatting
    private String gridColor = "rgb(200,200,200)";

    // Touch related
    private int lastTouchFingerDistance = 0;
    private boolean multitouching = false;

    private Date renderStart = new Date();
    private Date lastPlotStartDate = null;
    private Date lastPlotEndDate = null;

    private NumberFormat verticalAxisNumberFormat = NumberFormat
            .getFormat("##.##");

    public enum PlotMode {
        LINE, SCATTER, BAR
    }

    public VTimelineDisplay(VTimelineWidget w) {
        super(w);
        widget = w;
        currentMode = PlotMode.LINE;

        browserRoot = DOM.createDiv();
        setElement(browserRoot);
        setStyleName(VTimelineWidget.DISPLAY_CLASSNAME);

        // Add the components
        displayComponentPanel = new AbsolutePanel();
        browserRoot.appendChild(displayComponentPanel.getElement());
        DOM.setStyleAttribute(displayComponentPanel.getElement(), "position",
                "relative");

        // Add the canvas
        canvas = new Canvas(100, 100);
        canvas.setStyleName(CLASSNAME_CANVAS);
        displayComponentPanel.add(canvas, 0, 0);

        // Add the plotter
        plotter = new VCanvasPlotter(canvas);

        // Create the loading indicator
        loadingCurtain = new HTML("");
        loadingCurtain.setStyleName("v-app-loading");
        loadingCurtain.addStyleName(CLASSNAME_LOADINGCURTAIN);
        loadingCurtain.setWidth("100%");
        loadingCurtain.setHeight("100%");
        displayComponentPanel.add(loadingCurtain);

        // Create the bottom bar
        bottomBar = new HTML();
        bottomBar.setWidth("100%");
        bottomBar.setStyleName(CLASSNAME_BOTTOMBAR);
        displayComponentPanel.add(bottomBar);

        // Add no data source label
        noDataLabel.setVisible(false);
        displayComponentPanel.add(noDataLabel, 10, 10);

        // Create the disabled curtain
        disabledCurtain = new HTML("");
        disabledCurtain.setVisible(false);
        disabledCurtain.setStyleName(CLASSNAME_LOADINGCURTAIN);
        disabledCurtain.setWidth("100%");
        disabledCurtain.setHeight("100%");
        displayComponentPanel.add(disabledCurtain);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#onLoad()
     */
    @Override
    protected void onLoad() {
        super.onLoad();
        // Handlers are removed by super.onUnload()
        handlers.add(addDomHandler(this, MouseDownEvent.getType()));
        handlers.add(addDomHandler(this, MouseMoveEvent.getType()));
        handlers.add(addDomHandler(this, MouseWheelEvent.getType()));
        handlers.add(Event.addNativePreviewHandler(this));
        handlers.add(addDomHandler(this, TouchStartEvent.getType()));
        handlers.add(addDomHandler(this, TouchMoveEvent.getType()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.UIObject#setHeight(java.lang.String)
     */
    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        canvas.setHeight(getOffsetHeight() - bottomBar.getOffsetHeight());
        displayComponentPanel.setHeight(getOffsetHeight() + "px");
        displayComponentPanel.setWidgetPosition(bottomBar, 0, getOffsetHeight()
                - bottomBar.getOffsetHeight());
        if (widget.isInitDone()) {
            plotData(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
     */
    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        canvas.setWidth(getOffsetWidth() + "px");
        displayComponentPanel.setWidth(getOffsetWidth() + "px");
        if (widget.isInitDone()) {
            plotData(true);
        }
    }

    /**
     * Resets the display cache
     */
    public void resetDisplayCache() {
        currentValues.clear();
        currentDates.clear();
        currentMin.clear();
        currentTotalMin = 0;
        currentTotalMax = 0;
        currentMax.clear();
        currentGraphs.clear();
    }

    /**
     * Add a value label
     * 
     * @param y
     *            The y-coordinate
     * @param val
     *            The value
     * @param diff
     *            The time difference
     */
    private void addValueLabel(int y, float val, int diff) {
        String str = verticalAxisNumberFormat.format(val);
        Label lbl = new Label(str);
        lbl.setStyleName(CLASSNAME_SCALEVALUE);
        displayComponentPanel.add(lbl, 0, y);
        lbl.getElement().getStyle().clearLeft();
        verticalScaleComponents.add(lbl);
    }

    /**
     * Plots the horizontal scale
     * 
     * @param startTime
     *            The epoch time when the display area starts
     * @param endTime
     *            The epoch time when the display area ends
     * @param unitTime
     *            The time vs. pixel width ratio
     * @param xUnit
     *            The x-coordinate time vs. pixel unit ratio
     * @param leftAlign
     *            Should the label be left aligned
     */
    @SuppressWarnings("deprecation")
    private void plotHorizontalScale(long startTime, long endTime,
            long unitTime, float xUnit, boolean leftAlign) {

        if (unitTime <= 0 || xUnit <= 0) {
            return;
        }

        float width = unitTime * xUnit;
        boolean shortDateFormat = width < 100;
        int year = widget.getStartDate().getYear();
        long time = (new Date(year, 0, 1)).getTime();
        DateTimeFormat formatter = shortDateFormat ? widget.getDateFormats()
                .getShortDateFormatter(unitTime) : widget.getDateFormats()
                .getLongDateFormatter(unitTime);

        if (gridColor != null) {
            canvas.setStrokeStyle(gridColor);
            canvas.setLineWidth(0.5);
            canvas.beginPath();
        }

        long stepsUntilInRange = (startTime - time) / unitTime;
        time += stepsUntilInRange * unitTime;

        while (time <= endTime) {
            if (time >= startTime - unitTime && time <= endTime + unitTime) {
                Label lbl = new Label();
                lbl.setStyleName(leftAlign ? CLASSNAME_SCALEDATE_LEFT
                        : CLASSNAME_SCALEDATE);
                lbl.setWidth(width + "px");
                Date date = new Date(time);

                lbl.setText(widget.getDateTimeService().formatDate(date,
                        formatter.getPattern()));

                long timeFromStart = time - startTime;
                float x = timeFromStart * xUnit;

                if (gridColor != null) {
                    canvas.moveTo(x, 0);
                    canvas.lineTo(x, canvas.getHeight());
                }

                displayComponentPanel.add(lbl, (int) x,
                        displayComponentPanel.getOffsetHeight() - 15);
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

        if (gridColor != null) {
            canvas.closePath();
            canvas.stroke();
        }
    }

    /**
     * Plots the horizontal scale on to the canvas
     * 
     * @param xUnit
     *            The x-coordinate time vs. pixel unit ratio
     * @param startTime
     *            The epoch time when the display area starts
     * @param endTime
     *            The epoch time when the display area ends
     */
    private void plotHorizontalScale(float xUnit, long startTime, long endTime) {

        Date start = new Date();
        long timeDiff = endTime - startTime;

        for (Label lbl : horizontalScaleComponents) {
            displayComponentPanel.remove(lbl);
        }
        horizontalScaleComponents.clear();

        canvas.setGlobalCompositeOperation(Canvas.DESTINATION_OVER);

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
        else if (timeDiff <= VDateFormatInfo.MINUTE) {
            plotHorizontalScale(startTime, endTime,
                    10 * VDateFormatInfo.SECOND, xUnit, true);
        }

        // Selections is less the 5 minutes
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

        // Selection is less than two years
        else if (timeDiff <= 2 * VDateFormatInfo.YEAR) {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.MONTH,
                    xUnit, false);
        }

        // Selection is more than two years
        else {
            plotHorizontalScale(startTime, endTime, VDateFormatInfo.YEAR,
                    xUnit, false);
        }

        canvas.setGlobalCompositeOperation(Canvas.SOURCE_OVER);

        long time = new Date().getTime() - start.getTime();
        VConsole.log("VTimelineDisplay: Render horizontal scale " + time + "ms");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * calculateVerticalZero(float, float)
     */
    @Override
    protected float calculateVerticalZero(float yUnit, float canvasHeight) {
        float[] startAndEnd = getVerticalScaleMinAndMax();
        float scaleStart = startAndEnd[0];
        float scaleEnd = startAndEnd[1];

        float zeroYCoordinate = canvasHeight + scaleStart * yUnit;
        float iNorm = 0f;
        float valueDiff = Math.abs(scaleEnd - scaleStart);
        float last = scaleStart;
        for (float i = scaleStart; i <= scaleEnd; i += valueDiff / 5f) {
            iNorm = i - scaleStart;
            // Catch the zero coordinate
            if (last < 0 && i > 0 || i == 0) {
                zeroYCoordinate = canvasHeight - (iNorm - i) * yUnit;
            }
            last = i;
        }

        return zeroYCoordinate;
    }

    /**
     * Resets the vertical fitting scales to the initial values
     */
    public void resetVerticalScaleFitting() {
        fittedVerticalScaleMinimum = Float.MAX_VALUE;
        fittedVerticalScaleMaximum = Float.MIN_VALUE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#
     * getVerticalScaleMinAndMax()
     */
    @Override
    protected float[] getVerticalScaleMinAndMax() {
        float scaleStart = 0f;
        float scaleEnd = 0f;

        if (verticalScaleMax == null || verticalScaleMin == null) {
            // Vertical fitting

            if (widget.isGraphsStacked()) {
                if (currentTotalMin == currentTotalMax) {
                    scaleStart = currentTotalMin - 5;
                    scaleEnd = currentTotalMax * widget.getNumGraphs() + 5;
                } else {
                    scaleStart = currentTotalMin;
                    scaleEnd = currentTotalMax * widget.getNumGraphs();
                }
            } else {
                if (currentTotalMin == currentTotalMax) {
                    scaleStart = currentTotalMin - 5;
                    scaleEnd = currentTotalMax + 5;
                } else {
                    scaleStart = currentTotalMin;
                    scaleEnd = currentTotalMax;
                }
            }

            // Only increase scale visiblity
            if (scaleStart < fittedVerticalScaleMinimum) {
                fittedVerticalScaleMinimum = scaleStart;
            } else {
                scaleStart = fittedVerticalScaleMinimum;
            }

            if (scaleEnd > fittedVerticalScaleMaximum) {
                fittedVerticalScaleMaximum = scaleEnd;
            } else {
                scaleEnd = fittedVerticalScaleMaximum;
            }

        } else {
            scaleStart = verticalScaleMin;
            scaleEnd = verticalScaleMax;
            fittedVerticalScaleMinimum = Float.MAX_VALUE;
            fittedVerticalScaleMaximum = Float.MIN_VALUE;
        }

        return new float[] { scaleStart, scaleEnd };
    }

    /**
     * Plots the vertical scale
     * 
     * @return Returns the y-coordinate of the zero line
     */
    private float plotVerticalScale(float yUnit, float canvasHeight) {

        Date start = new Date();

        for (Label lbl : verticalScaleComponents) {
            displayComponentPanel.remove(lbl);
        }
        verticalScaleComponents.clear();

        if (gridColor != null) {
            canvas.setStrokeStyle(gridColor);
            canvas.setLineWidth(0.5);
            canvas.beginPath();
        }

        float[] startAndEnd = getVerticalScaleMinAndMax();
        float scaleStart = startAndEnd[0];
        float scaleEnd = startAndEnd[1];

        float valueDiff = Math.abs(scaleEnd - scaleStart);
        float last = scaleStart;
        float zeroYCoordinate = canvasHeight + scaleStart * yUnit;

        float y = 0f;
        float iNorm = 0f;

        if (verticalScaleLines == null) {
            /*
             * No vertical lines defined, draw default lines
             */
            for (float i = scaleStart; i <= scaleEnd; i += valueDiff / 5f) {
                iNorm = i - scaleStart;
                y = canvasHeight - iNorm * yUnit;

                if (gridColor != null) {
                    canvas.moveTo(0, y);
                    canvas.lineTo(canvas.getWidth(), y);
                }

                addValueLabel(Math.round(y - 13), i, 0);

                // Catch the zero coordinate
                if (last < 0 && i > 0 || i == 0) {
                    zeroYCoordinate = canvasHeight - (iNorm - i) * yUnit;
                }

                last = i;
            }
        } else {
            /*
             * Use predefines scales
             */
            for (float value : verticalScaleLines) {
                iNorm = value - scaleStart;
                y = canvasHeight - iNorm * yUnit;
                if (gridColor != null) {
                    canvas.moveTo(0, y);
                    canvas.lineTo(canvas.getWidth(), y);
                }
                addValueLabel(Math.round(y - 13), value, 0);
            }
        }

        if (gridColor != null) {
            canvas.closePath();
            canvas.stroke();
        }

        // Draw the zero line
        if (zeroYCoordinate > 0) {

            canvas.setStrokeStyle("rgb(0,0,0)");
            canvas.setLineWidth(1);
            canvas.beginPath();
            canvas.moveTo(0, zeroYCoordinate);
            canvas.lineTo(canvas.getWidth(), zeroYCoordinate);
            canvas.closePath();
            canvas.stroke();

            addValueLabel(Math.round(zeroYCoordinate - 13), 0f, 0);
        }

        long time = new Date().getTime() - start.getTime();
        VConsole.log("VTimelineDisplay: Render vertical scale " + time + "ms");
        return zeroYCoordinate;
    }

    /**
     * Timer to ensure redrawing is done lazily
     */

    private Timer lazyRedrawTimer = new Timer() {
        @Override
        public void run() {
            if (widget.isInitDone()) {
                lazyPlottingImminent = false;
                plotData(forcePlot);
            }
        }
    };

    /**
     * Redraws the canvas area
     */
    private boolean lazyPlottingImminent = false;

    public void redraw() {
        lazyPlottingImminent = true;
        forcePlot = true;
        lazyRedrawTimer.schedule(500);
    }

    private void plotData(boolean force) {

        /*
         * Optimize rendering so it does not re-render if not needed
         */
        if (!force && lastPlotStartDate != null
                && lastPlotStartDate == currentRealStartDate) {
            return;
        }
        if (!force && lastPlotEndDate != null
                && lastPlotEndDate == currentRealEndDate) {
            return;
        }

        lastPlotStartDate = currentRealStartDate;
        lastPlotEndDate = currentRealEndDate;
        renderStart = new Date();

        /*
         * Using a plotter listener to listen to when the plotting of the graphs
         * has stopped and we can plot the scales
         */
        final long startT = getSelectionStartDate().getTime();
        final long endT = getSelectionEndDate().getTime();

        plotter.setListener(this);
        plotter.setUseShadows(widget.isGraphShadows());

        // Plot graphs
        super.plotData(plotter, Collections.unmodifiableMap(currentValues),
                Collections.unmodifiableMap(currentDates));

        // Add markers
        Set<String> removableMarkers = new HashSet<String>();
        for (String markStr : markerMap.keySet()) {

            String m[] = markStr.split("_");
            Long time = Long.parseLong(m[0]);

            if (time >= startT && time <= endT) {
                Long timeFromStart = time - startT;

                float x = timeFromStart * getCanvasXUnit();
                float y = canvas.getHeight();

                plotMarker(markStr, x, y);
            } else {
                removableMarkers.add(markStr);
            }
        }

        // Remove old markers
        for (String m : removableMarkers) {
            removeMarker(m);
        }

        // Remove old events
        for (Button btn : events) {
            displayComponentPanel.remove(btn);
        }

        events.clear();
        eventCoordinates.clear();
        currentEventMap.clear();
        currentEventDates.clear();

        // Add events
        List<String> eventList = new ArrayList<String>(currentEvents);
        for (int e = eventList.size() - 1; e >= 0; e--) {

            String eventStr = eventList.get(e);
            String m[] = eventStr.split("_");
            Long time = Long.parseLong(m[0]);
            String caption = m[1];
            int i = Integer.parseInt(m[2]);

            Long timeFromStart = time - startT;

            float x = timeFromStart * getCanvasXUnit();
            float y = 10;

            plotEvent(caption, new Date(time), i, x, y);
        }

        // Always reset force plot flag after plot
        forcePlot = false;
    }

    /**
     * Removes a marker from the display and memory
     * 
     * @param markString
     *            The id of the marker
     */
    private void removeMarker(String markString) {
        Label lbl = markerMap.get(markString);
        if (lbl != null) {
            displayComponentPanel.remove(lbl);
        }
        markerMap.remove(markString);

        FlexTable tooltip = markerTooltipMap.get(markString);
        if (tooltip != null) {
            displayComponentPanel.remove(tooltip);
        }
        markerTooltipMap.remove(markString);
    }

    /**
     * Removes an event from the display and memory
     * 
     * @param event
     *            The event to remove
     */
    private void removeEvent(Button event) {
        displayComponentPanel.remove(event);
        int idx = events.indexOf(event);
        events.remove(event);
        eventCoordinates.remove(idx);
        currentEventMap.remove(event);
        currentEventDates.remove(event);
        currentEvents.remove(idx);
    }

    /**
     * Removes all in memory items
     */
    public void clearCache() {

        // Remove markers
        Set<String> markers = new HashSet<String>(markerMap.keySet());
        for (String mark : markers) {
            removeMarker(mark);
        }

        // Remove events
        Set<Button> events = new HashSet<Button>(this.events);
        for (Button event : events) {
            removeEvent(event);
        }
    }

    /**
     * Plots a marker to a gived position
     * 
     * @param caption
     *            The short caption to be displayed in the graph
     * @param description
     *            The tooltip
     * @param x
     *            The x-coordinate
     * @param y
     *            The y-coordinate
     */
    private void plotMarker(String markString, float x, float y) {

        HTML lbl;
        FlexTable tooltip;

        // Check if marker exists on display
        if (markerMap.get(markString) != null) {
            lbl = markerMap.get(markString);
            tooltip = markerTooltipMap.get(markString);
        } else {
            String m[] = markString.split("_");
            Long time = Long.parseLong(m[0]);
            String caption = m[1];
            String description = m[2];

            lbl = new HTML("<span class=\"" + CLASSNAME_MARKER + "-text\">"
                    + caption + "</span>");
            lbl.setStyleName(CLASSNAME_MARKER);
            lbl.addStyleName(CLASSNAME_MARKER + "-" + caption);

            displayComponentPanel.add(lbl);
            displayComponentPanel.setWidgetPosition(lbl,
                    (int) x - lbl.getOffsetWidth() / 2,
                    (int) y - lbl.getOffsetHeight());

            markerMap.put(markString, lbl);

            DateTimeFormat formatter = DateTimeFormat.getFormat("MMM d, ''yy");

            tooltip = new FlexTable();
            tooltip.setVisible(true);
            tooltip.setStyleName(CLASSNAME_MARKERTOOLTIP);
            tooltip.setBorderWidth(0);
            tooltip.setCellSpacing(0);
            tooltip.setCellPadding(0);

            tooltip.getRowFormatter().setStyleName(0, "top");
            tooltip.getCellFormatter().setStyleName(0, 0, "topLeft");
            tooltip.getCellFormatter().setStyleName(0, 1, "topMiddle");
            tooltip.getCellFormatter().setStyleName(0, 2, "topRight");

            tooltip.getRowFormatter().setStyleName(1, "middle");
            tooltip.getCellFormatter().setStyleName(1, 0, "middleLeft");
            tooltip.getCellFormatter().setStyleName(1, 1, "content");
            tooltip.getCellFormatter().setStyleName(1, 2, "middleRight");

            tooltip.getRowFormatter().setStyleName(2, "bottom");
            tooltip.getCellFormatter().setStyleName(2, 0, "bottomLeft");
            tooltip.getCellFormatter().setStyleName(2, 1, "bottomMiddle");
            tooltip.getCellFormatter().setStyleName(2, 2, "bottomRight");

            HTML content = new HTML("<span class=date>"
                    + formatter.format(new Date(time)) + "</span> <br/>"
                    + "<span class=text>" + description + "</span>");

            tooltip.setWidget(1, 1, content);

            displayComponentPanel.add(tooltip);
            markerTooltipMap.put(markString, tooltip);
        }

        int markerX = (int) (x - Util.getRequiredWidth(lbl) / 2.0);
        int markerY = (int) (y - Util.getRequiredHeight(lbl));

        displayComponentPanel.setWidgetPosition(lbl, markerX, markerY);

        // Show tooltip for measurement
        tooltip.setVisible(true);
        displayComponentPanel.setWidgetPosition(tooltip, 0, 0);

        markerY -= Util.getRequiredHeight(tooltip);

        // Check if right border is reached, if so, the move the tooltip
        // left
        if (markerX + Util.getRequiredWidth(tooltip) > Util
                .getRequiredWidth(displayComponentPanel)) {
            markerX -= Util.getRequiredWidth(tooltip);
        }

        displayComponentPanel.setWidgetPosition(tooltip, markerX, markerY);
        tooltip.setVisible(false);
    }

    /**
     * Plots an event onto the display
     * 
     * @param caption
     *            The caption that shows on the screen
     * @param date
     *            The date the event happend
     * @param index
     *            The server index of the event
     * @param x
     *            The X-coordinate
     * @param y
     *            The Y-coordinate
     */
    private void plotEvent(String caption, Date date, int index, float x,
            float y) {
        Button event = new Button(caption);
        event.setWidth((caption.length() * 30) + "px");
        event.setStyleName(CLASSNAME_EVENT);

        // Does an event with the same coordinate exist
        boolean exists = false;
        for (Integer i : eventCoordinates) {
            if ((i >= x && i <= x + 30) || (i >= x - 30 && i <= x)) {
                event = events.get(eventCoordinates.indexOf(i));
                event.setText("*");
                event.setWidth("30px");

                if (x < i) {
                    displayComponentPanel.setWidgetPosition(event, (int) x,
                            displayComponentPanel.getWidgetTop(event));
                    eventCoordinates.set(eventCoordinates.indexOf(i), (int) x);
                }

                currentEventMap.get(event).add(index);
                currentEventDates.get(event).add(date);
                exists = true;
                break;
            }
        }

        // Else add the event to the display
        if (!exists) {
            displayComponentPanel.add(event, (int) x, (int) y);
            events.add(event);
            eventCoordinates.add((int) x);

            List<Integer> indexes = new ArrayList<Integer>();
            indexes.add(index);
            currentEventMap.put(event, indexes);

            List<Date> dates = new ArrayList<Date>();
            dates.add(date);
            currentEventDates.put(event, dates);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.trends.ws.client.VDataListener#dataRecieved(java.lang.Long,
     * java.util.List, java.util.List, java.util.Set)
     */
    private int currentMinDensity = Integer.MAX_VALUE;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VDataListener#dataRecieved(java.
     * lang.Long, java.util.List, java.util.List, java.util.Set, java.util.Set,
     * int)
     */
    @Override
    public void dataRecieved(Long requestId, List<Float> values,
            List<Date> dates, Set<String> markers, Set<String> events,
            int density) {

        if (!requestGraphMap.containsKey(requestId)) {
            return;
        }

        if (density < currentMinDensity) {
            currentMinDensity = density;
        }

        Integer g = requestGraphMap.get(requestId);
        requestGraphMap.remove(requestId);

        // Check if we have new markers
        if (markers != null) {
            for (String marker : markers) {
                if (!markerMap.containsKey(marker)) {
                    markerMap.put(marker, null);
                    markerTooltipMap.put(marker, null);
                }
            }
        }

        // Check if we ahve events
        currentEvents.clear();
        if (events != null) {
            currentEvents.addAll(events);
        }

        currentValues.put(g, values);
        currentDates.put(g, dates);
        currentMin.put(g, VTimelineWidget.getMinValue(values));
        currentMax.put(g, VTimelineWidget.getMaxValue(values));

        if (dates.size() > 0) {
            if (currentRealStartDate == null
                    || currentRealStartDate.after(dates.get(0))) {
                currentRealStartDate = dates.get(0);
            }

            if (currentRealEndDate == null
                    || currentRealEndDate.before(dates.get(dates.size() - 1))) {
                currentRealEndDate = dates.get(dates.size() - 1);
            }
        }

        if (currentMin.get(g) < currentTotalMin) {
            currentTotalMin = currentMin.get(g);
        }

        if (currentMax.get(g) > currentTotalMax) {
            currentTotalMax = currentMax.get(g);
        }

        // If all graphs data has been recieved then plot the graph so the
        // graphs
        // does not appear one by one
        if (graphDataRecievedCounter == widget.getNumGraphs() - 1) {
            graphDataRecievedCounter = 0;
            requestGraphMap.clear();

            plotData(forcePlot);

            // Hide curtain
            setLoadingIndicatorVisible(false);

        }

        graphDataRecievedCounter++;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VDataListener#dataRecievedAll(java
     * .util.List, java.util.Map, java.util.Map, java.util.Set, java.util.Set,
     * java.util.Map, java.util.Map, java.lang.Float, java.lang.Float, int)
     */
    @Override
    public void dataRecievedAll(List<Long> requestID,
            Map<Integer, List<Float>> values, Map<Integer, List<Date>> dates,
            Set<String> markers, Set<String> events, Map<Integer, Float> min,
            Map<Integer, Float> max, Float totalMinimum, Float totalMaximum,
            int minDensity) {

        currentMinDensity = minDensity;

        for (Long req : requestID) {
            requestGraphMap.remove(req);
        }

        // Check if we have new markers
        if (markers != null) {
            for (String marker : markers) {
                if (!markerMap.containsKey(marker)) {
                    markerMap.put(marker, null);
                    markerTooltipMap.put(marker, null);
                }
            }
        }

        // Check if we ahve events
        if (events != null) {
            currentEvents.addAll(events);
        }

        // Clear previous data
        resetDisplayCache();

        // Get total minimum / maximum
        currentTotalMin = totalMinimum;
        currentTotalMax = totalMaximum;

        for (Entry<Integer, List<Float>> entry : values.entrySet()) {

            Integer graph = entry.getKey();
            List<Float> fvalues = entry.getValue();
            List<Date> dvalues = dates.get(graph);
            Float minval = min.get(graph);
            Float maxval = max.get(graph);

            // Sanity check 1
            if (fvalues == null || dvalues == null) {
                continue;
            }

            // Sanity check 2
            if (dvalues.size() != fvalues.size()) {
                continue;
            }

            // Sanity check 3
            if (minval == null || maxval == null) {
                continue;
            }

            currentValues.put(graph, fvalues);
            currentDates.put(graph, dvalues);

            currentMin.put(graph, minval);
            currentMax.put(graph, maxval);

            currentRealStartDate = dvalues.get(0);
            currentRealEndDate = dvalues.get(dvalues.size() - 1);
        }

        plotData(forcePlot);

        setLoadingIndicatorVisible(false);

        graphDataRecievedCounter = 0;
    }

    /**
     * Should the spinning loading indicator be visible?
     * 
     * @param visible
     *            Is it visible
     */
    private void setLoadingIndicatorVisible(boolean visible) {
        loadingCurtain.setVisible(visible);
    }

    /**
     * Returns TRUE if the given x- and y- coordinate is over a marker
     * 
     * @param x
     *            The x coordinate relative to the display component
     * @param y
     *            The y coordinate relative to the display component
     * @return Returns the marker it is over, else null
     */
    private String coordinateIsOverMarker(int x, int y) {
        int left, top, width, height;
        for (String markerId : markerMap.keySet()) {
            Label marker = markerMap.get(markerId);

            left = displayComponentPanel.getWidgetLeft(marker);
            top = displayComponentPanel.getWidgetTop(marker);
            width = marker.getOffsetWidth();
            height = marker.getOffsetHeight();

            if (x >= left && x <= left + width && y >= top && y <= top + height) {
                return markerId;
            }
        }

        return null;
    }

    /**
     * Hides all marker tooltips
     */
    private void hideAllMarkerTooltips() {
        for (FlexTable tooltip : markerTooltipMap.values()) {
            tooltip.setVisible(false);
        }
    }

    /**
     * Highlights a bar chart
     * 
     * @param graph
     *            The graph of the bar
     * @param point
     *            The point that represents the bar
     */
    private void setSelectedBarGraphBarVisible(Graph graph, Point point) {

        int barY = point.y;
        int barWidth = point.width;
        int barX = point.x - barWidth; // Using left measuring (see
        // VCanvasPlotter)
        float barHeight = getCurrentZeroCoordinate() - point.y;

        HTML bar = bars.get(currentGraphs.indexOf(graph));
        bar.setWidth(barWidth + "px");
        bar.setHeight(barHeight + "px");
        bar.setVisible(true);

        displayComponentPanel.setWidgetPosition(bar, barX, barY);
    }

    private Date setRangeTimerStart, setRangeTimerEnd;

    /**
     * Set the currently displayed time range
     * 
     * @param start
     *            The start date
     * @param end
     *            The end date
     * @return Returns true if the range was set, false otherwise
     */

    public boolean setRange(Date start, Date end) {
        if (widget.isInitDone()) {
            setRangeTimerStart = start;
            setRangeTimerEnd = end;
            Scheduler.get().scheduleIncremental(
                    new Scheduler.RepeatingCommand() {
                        @Override
                        public boolean execute() {
                            if (setRangeTimerStart != null
                                    && setRangeTimerEnd != null) {
                                if (mouseOrTouchActive) {
                                    return false;
                                }

                                setRange(setRangeTimerStart, setRangeTimerEnd,
                                        false, false);
                                setRangeTimerStart = null;
                                setRangeTimerEnd = null;

                                return true;
                            }
                            return false;
                        }
                    });
        } else {
            setRange(start, end, true, false);
        }

        return true;
    }

    /**
     * Set the range to display
     * 
     * @param start
     *            The start date
     * @param end
     *            The end date
     * @return
     */
    private boolean setRange(Date start, Date end, boolean useCurtain,
            boolean forceServer) {

        if (!isVisible()) {
            return false;
        }

        if (start == null || end == null) {
            return false;
        }

        /*
         * Use previous density as refernece
         */
        int density = 1;
        if (currentEndDate != null && currentStartDate != null) {
            long currentDiff = currentEndDate.getTime()
                    - currentStartDate.getTime();
            long diff = end.getTime() - start.getTime();
            if (diff > currentDiff - VDateFormatInfo.DAY
                    && diff < currentDiff + VDateFormatInfo.DAY) {
                density = currentMinDensity == Integer.MAX_VALUE ? 1
                        : currentMinDensity;
            }
        }

        currentMinDensity = Integer.MAX_VALUE;
        currentStartDate = start;
        currentEndDate = end;
        currentRealStartDate = null;
        currentRealEndDate = null;
        graphDataRecievedCounter = 0;

        resetDisplayCache();

        // Get the date data
        boolean cached = false;
        if (forceServer) {

            // Some data not found in cache, get it from the server
            for (int g = 0; g < widget.getNumGraphs(); g++) {
                widget.getDateData(this, g, start, end, density);
            }

            // Issue request
            widget.getFromServer();

        } else {
            cached = widget.getDateDataAll(this, start, end, density);
        }

        setLoadingIndicatorVisible(useCurtain && !cached);

        if (!isMouseActive()) {
            widget.fireDateRangeChangedEvent();
        }

        return true;
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
                redraw();
            }
        }
    }

    /**
     * Returns the canvas width
     * 
     * @return The width in pixels
     */
    public int getCanvasWidth() {
        return canvas.getWidth();
    }

    /**
     * Get the current starting date
     * 
     * @return The date
     */
    @Override
    public Date getSelectionStartDate() {
        if (currentStartDate == null) {
            return widget.getSelectedStartDate();
        }
        return currentStartDate;
    }

    /**
     * Get the current ending date
     * 
     * @return The date
     */
    @Override
    public Date getSelectionEndDate() {
        if (currentEndDate == null) {
            return widget.getSelectedEndDate();
        }
        return currentEndDate;
    }

    /**
     * Get the total minimal value of the graphs
     * 
     * @return The minimum
     */
    public Float getMin() {
        return currentTotalMin;
    }

    /**
     * Get the total maximal value of the graphs
     * 
     * @return The maximum
     */
    public Float getMax() {
        return currentTotalMax;
    }

    /**
     * Set the state of the vertical fitting. If vertical fitting is enabled
     * then the vertical scale is made depending on the maximal and minimal
     * values in the graphs. If min and max is given then the scale is made
     * using them.
     * 
     * @param auto
     *            Enable automatic scale fitting
     * @param min
     *            The minimal value of the scale
     * @param max
     *            The maximal value of the scale
     */
    public void setVerticalFitting(boolean auto, float min, float max) {
        if (auto) {
            verticalScaleMax = null;
            verticalScaleMin = null;
        } else {
            verticalScaleMin = min;
            verticalScaleMax = max;
        }
        redraw();
    }

    /**
     * Removes a graph from the cache so it will not be rendered when plotData
     * is called
     * 
     * @param graph
     *            The index of the graph to be removed
     */
    public void removeGraph(int graph) {
        currentValues.put(graph, new ArrayList<Float>());
        currentDates.put(graph, new ArrayList<Date>());
        redraw();
    }

    /**
     * Triggered when an event button is pressed
     * 
     * @param eventButton
     */
    private void eventClick(Button eventButton) {
        List<Integer> indexes = currentEventMap.get(eventButton);
        widget.fireEventButtonClickEvent(indexes);

        if (indexes.size() > 1) {

            // Resolve first and last date
            List<Date> dates = currentEventDates.get(eventButton);
            Date start = dates.get(0);
            Date end = dates.get(0);
            for (Date d : dates) {
                if (start.after(d)) {
                    start = d;
                }
                if (end.before(d)) {
                    end = d;
                }
            }

            Long timeDiff = end.getTime() - start.getTime();

            Date leftEdge = new Date(start.getTime() - timeDiff * 2L);
            Date rightEdge = new Date(end.getTime() + timeDiff * 2L);

            widget.setBrowserRange(leftEdge, rightEdge);
            setRange(leftEdge, rightEdge, true, false);
        }
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

    /**
     * Does the component have an specific element
     * 
     * @param elem
     *            The element
     * @return True if the component has the element
     */
    public boolean hasElement(com.google.gwt.dom.client.Element elem) {

        for (HTML dot : dots) {
            if (dot.getElement() == elem) {
                return true;
            }
        }

        for (HTML bar : bars) {
            if (bar.getElement() == elem) {
                return true;
            }
        }

        for (Label lbl : horizontalScaleComponents) {
            if (lbl.getElement() == elem) {
                return true;
            }
        }

        for (Label lbl : verticalScaleComponents) {
            if (lbl.getElement() == elem) {
                return true;
            }
        }

        for (String marker : markerMap.keySet()) {
            if (markerMap.get(marker) != null
                    && markerMap.get(marker).getElement() == elem) {
                return true;
            }
        }

        for (Button eventBtn : events) {
            if (eventBtn.getElement() == elem) {
                return true;
            }
        }

        if (elem == displayComponentPanel.getElement() || elem == getElement()
                || elem == canvas.getElement()
                || elem == loadingCurtain.getElement() || elem == browserRoot
                || elem == bottomBar.getElement()
                || elem == disabledCurtain.getElement()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Displays the no data found message
     * 
     * @param enabled
     *            Is the message displayed
     */
    public void displayNoDataMessage(boolean enabled) {
        noDataLabel.setVisible(enabled);
        setLoadingIndicatorVisible(false);
        if (enabled) {

            // Clear canvas
            canvas.setWidth(canvas.getWidth());

            // Clear markers
            Set<String> removeMarkers = new HashSet<String>(markerMap.keySet());
            for (String m : removeMarkers) {
                removeMarker(m);
            }

            // Clear events
            Set<Button> removeEvents = new HashSet<Button>(events);
            for (Button event : removeEvents) {
                removeEvent(event);
            }

            // Clear vertical scale
            for (Label lbl : verticalScaleComponents) {
                displayComponentPanel.remove(lbl);
            }
            verticalScaleComponents.clear();

            // Clear horizontal scale
            for (Label lbl : horizontalScaleComponents) {
                displayComponentPanel.remove(lbl);
            }
            horizontalScaleComponents.clear();

            // Remove old dots
            for (HTML dot : dots) {
                displayComponentPanel.remove(dot);
            }
            dots.clear();

            // Remove old bars
            for (HTML bar : bars) {
                displayComponentPanel.remove(bar);
            }
            bars.clear();
        }
    }

    /**
     * Is the display enabled
     * 
     * @param enabled
     *            True if enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        setLoadingIndicatorVisible(false);
    }

    /**
     * Force plotting the the data even if it is the same time range. Forced
     * plotting is always turned off after a forced plot so you will have to
     * turn on forced plotting again before the next plot.
     * 
     * @param enabled
     *            The plotting is forced
     */
    public void setForcedPlotting(boolean enabled) {
        forcePlot = enabled;
    }

    /**
     * Sets the scale grid color, set to null to not draw the grid
     * 
     * @param color
     *            The color
     */
    public void setGridColor(String color) {
        gridColor = color;
        redraw();
    }

    int dragX = 0;

    // TODO investigate it this is needed, not used
    // /**
    // * A timer for scrolling the canvas left or right
    // */
    // private Timer scrollTimer = new Timer() {
    // @Override
    // public void run() {
    // lastTouchFingerDistance = 0;
    // refresh();
    // widget.setBrowserRange(getSelectionStartDate(),
    // getSelectionEndDate());
    // }
    // };

    /**
     * Called when a user is dragging the canvas with the mouse
     * 
     * @param event
     *            The mouse event
     */
    // TODO investigate it this is needed, not used
    @SuppressWarnings("unused")
    private boolean lastDragWasToTheRight = false;

    private void moveDragging(NativeEvent event) {

        if (currentEndDragDate == null || currentStartDragDate == null) {
            return;
        }

        Long timeDiff = currentEndDragDate.getTime()
                - currentStartDragDate.getTime();
        float xdiff = (lastMouseX - mouseDownX) / 2f;
        lastDragWasToTheRight = xdiff > 0;
        float canvasWidth = canvas.getWidth();
        float widthUnit = xdiff / canvasWidth;
        Float time = timeDiff * widthUnit;
        Long start = currentStartDate.getTime() - time.longValue();
        Long end = currentEndDate.getTime() - time.longValue();

        Date startDate = new Date(start);
        Date endDate = new Date(end);

        if (startDate.before(widget.getStartDate())) {
            startDate = widget.getStartDate();
        }

        if (endDate.after(widget.getEndDate())) {
            endDate = widget.getEndDate();
        }

        if (startDate.equals(getSelectionStartDate())
                || endDate.equals(getSelectionEndDate())) {
            return;
        }

        setRange(startDate, endDate, false, false);
        widget.setBrowserRange(startDate, endDate);
        dragX = lastMouseX;
        mouseDownX = dragX;
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
        NativeEvent mouseEvent = event.getNativeEvent();

        // Dragging action occurring..
        if (mouseIsDown && enabled && currentEndDragDate != null
                && currentStartDragDate != null) {
            lastMouseX = event.getClientX();
            moveDragging(mouseEvent);

            // Mouse is hovering over the display area
        } else if (enabled && !loadingCurtain.isVisible()
                && !mouseOrTouchActive) {

            int x = mouseEvent.getClientX() - getAbsoluteLeft();
            int y = mouseEvent.getClientY() - getAbsoluteTop();

            // Handle graph selectors
            handleGraphSelector(x, y);
        }
    }

    /**
     * Searches for the closest selector on the canvas and hightlights it
     * 
     * @param x
     *            The x-coordinate
     * @param y
     *            The y-coordinate
     */
    private void handleGraphSelector(int x, int y) {

        if (currentGraphs.size() == 0 || lazyPlottingImminent
                || isCurrentlyRendering()) {
            // If no graphs or we are currently plotting then ignore
            return;
        }

        hideAllMarkerTooltips();

        // Over a marker
        String markerId = coordinateIsOverMarker(x, y);
        if (markerId != null) {
            FlexTable markerTooltip = markerTooltipMap.get(markerId);
            markerTooltip.setVisible(true);
            return;
        }

        for (int g = 0; g < currentGraphs.size(); g++) {
            Graph graph = currentGraphs.get(g);

            // Ensure we have some points to search in
            if (!widget.graphIsVisible(g) || graph.getPoints().size() == 0) {
                continue;
            }

            // Iterate through all points and find the closes one
            float distance = Float.MAX_VALUE;
            for (Point p : graph.getPoints()) {

                // If point is not visible on canvas, ignore it
                if (p.x < 0 || p.x > canvas.getWidth()) {
                    continue;
                }

                // Get the diff between the mouse pointer and the point on the
                // canvas
                float diff = Math.abs(x - p.x);
                if (currentMode == PlotMode.BAR) {
                    // Barchart renders to the left of the point so we have to
                    // adjust
                    diff = Math.abs(x - (p.x - p.width / 2f));
                }

                if (diff < distance) {
                    distance = diff;

                }

            }

        }

        for (int g = 0; g < currentGraphs.size(); g++) {
            Graph graph = currentGraphs.get(g);

            if (!widget.graphIsVisible(g) || graph.getPoints().size() == 0) {
                continue;
            }

            List<Point> points = graph.getPoints();

            // Search for index of the closes point
            float minDiff = 10000000f;
            int pointIndex = 0;
            Point p;
            for (int i = 0; i < points.size(); i++) {
                p = points.get(i);
                if (p.x < 0 || p.x > canvas.getWidth()) {
                    continue;
                }

                float diff = Math.abs(x - p.x);
                if (currentMode == PlotMode.BAR) {
                    // Barchart renders to the left of the point so we have to
                    // adjust
                    diff = Math.abs(x - (p.x - p.width / 2f));
                }

                if (diff < minDiff) {
                    minDiff = diff;
                    pointIndex = i;
                }
            }

            // Retrieve the selected point
            p = points.get(pointIndex);
            if (dots.size() > g) {
                HTML dot = dots.get(g);
                dot.setVisible(true);
                if (widget.isGraphsStacked()) {
                    float pSum = 0;
                    for (int j = 0; j < g; j++) {
                        Graph graph2 = currentGraphs.get(j);
                        Point q = graph2.getPoints().get(pointIndex);
                        if (q != null) {
                            pSum += q.y - getCurrentZeroCoordinate();
                        }
                    }

                    if (currentMode == PlotMode.BAR) {
                        setSelectedBarGraphBarVisible(graph, p);
                        displayComponentPanel.setWidgetPosition(
                                dot,
                                p.x - dot.getOffsetWidth() / 2,
                                Math.round(p.y + pSum - dot.getOffsetHeight()
                                        / 2));
                    } else {
                        displayComponentPanel.setWidgetPosition(
                                dot,
                                p.x - dot.getOffsetWidth() / 2,
                                Math.round(p.y + pSum - dot.getOffsetHeight()
                                        / 2));
                    }
                } else {
                    if (currentMode == PlotMode.BAR) {
                        setSelectedBarGraphBarVisible(graph, p);
                        displayComponentPanel.setWidgetPosition(dot,
                                p.x - dot.getOffsetWidth() / 2,
                                p.y - dot.getOffsetHeight() / 2);
                    } else {
                        displayComponentPanel.setWidgetPosition(dot,
                                p.x - dot.getOffsetWidth() / 2,
                                p.y - dot.getOffsetHeight() / 2);
                    }
                }
            }

            List<Float> values = currentValues.get(g);
            widget.setLegendValue(g, values.get(pointIndex));
        }
    }

    /**
     * Called when the user stops dragging either by releasing the mouse button
     * on removes the finger from the touch device
     * 
     * @param event
     *            The touch or mouse event
     */
    private void stopDragging(NativeEvent event) {
        for (Label lbl : verticalScaleComponents) {
            lbl.setStyleName(CLASSNAME_SCALEVALUE);
        }
        canvas.setStyleName(CLASSNAME_CANVAS);

        mouseOrTouchActive = false;
        multitouching = false;
        DOM.releaseCapture(canvas.getElement());
        VConsole.log("VTimelineDisplay: Dragging stopped");
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
        NativeEvent mouseEvent = event.getNativeEvent();
        event.preventDefault();
        event.stopPropagation();

        // Check if element is a event button
        for (Button btn : events) {
            if (Element.as(mouseEvent.getEventTarget()) == btn.getElement()) {
                eventClick(btn);
                return;
            }
        }

        startDrag(mouseEvent);
    }

    private void startDrag(NativeEvent event) {
        if (enabled) {
            mouseOrTouchActive = true;
            widget.focus();

            mouseDownX = Util.getTouchOrMouseClientX(event);

            canvas.setStyleName(CLASSNAME_CANVASDRAG);

            for (Label lbl : verticalScaleComponents) {
                lbl.setStyleName(CLASSNAME_SCALEVALUEDRAG);
            }

            DOM.setCapture(canvas.getElement());

            currentStartDragDate = new Date(currentStartDate.getTime());
            currentEndDragDate = new Date(currentEndDate.getTime());

            VConsole.log("VTimelineWidget: Dragging started");
        }
    }

    /**
     * Zooms in around a center x-coordinate
     * 
     * @param centerX
     *            The x-coordinate of the center point which to zoom in on
     * @param timespanRatio
     *            The amount to zoom in
     */
    public void zoomIn(float centerX, float timespanRatio) {
        float canvasWidth = canvas.getWidth();
        float ratio = centerX / canvasWidth;

        // Calculate the minimum timeunit for the whole range
        long diff = widget.getEndDate().getTime()
                - widget.getStartDate().getTime();
        float minTimeSpan = (diff / canvasWidth) * timespanRatio;

        Float startRatio = ratio * minTimeSpan;
        Float endRatio = (1f - ratio) * minTimeSpan;

        Date start = new Date(currentStartDate.getTime()
                - startRatio.longValue());

        if (start.before(widget.getStartDate())) {
            /*
             * Ensure we are in bounds
             */
            start = widget.getStartDate();
        }

        Date end = new Date(currentEndDate.getTime() + endRatio.longValue());

        if (end.after(widget.getEndDate())) {
            /*
             * Ensure we are in bounds
             */
            end = widget.getEndDate();
        }

        if (end.getTime() - start.getTime() > minTimeSpan) {
            currentStartDate = start;
            currentEndDate = end;
            lastTouchFingerDistance = 0;
            refresh();
            widget.setBrowserRange(getSelectionStartDate(),
                    getSelectionEndDate());
        }
    }

    /**
     * Zooms out around a center x-coordinate
     * 
     * @param x
     *            The x-coordinate of the center point which to zoom out on
     * @param timespanRatio
     *            The amount to zoom out
     */
    public void zoomOut(float x, float timespanRatio) {
        float canvasWidth = canvas.getWidth();
        float ratio = x / canvasWidth;

        // Calculate the minimum timeunit for the whole range
        long diff = widget.getEndDate().getTime()
                - widget.getStartDate().getTime();
        float minTimeSpan = (diff / canvasWidth) * timespanRatio;

        Float startRatio = ratio * minTimeSpan;
        Float endRatio = (1f - ratio) * minTimeSpan;
        Date start = new Date(currentStartDate.getTime()
                + startRatio.longValue());

        if (start.before(widget.getStartDate())) {
            /*
             * Ensure we are in bounds
             */
            start = widget.getStartDate();
        }

        Date end = new Date(currentEndDate.getTime() - endRatio.longValue());

        if (end.after(widget.getEndDate())) {
            /*
             * Ensure we are in bounds
             */
            end = widget.getEndDate();
        }

        if (end.getTime() - start.getTime() > minTimeSpan) {
            currentStartDate = start;
            currentEndDate = end;
            lastTouchFingerDistance = 0;
            refresh();
            widget.setBrowserRange(getSelectionStartDate(),
                    getSelectionEndDate());
        }
    }

    public void scrollLeft(boolean boost) {
        scroll(boost ? -10 : -5);
    }

    public void scrollRight(boolean boost) {
        scroll(boost ? 10 : 5);
    }

    private void scroll(int adjustment) {
        // TODO
        VConsole.error("Using method VTimelineDisplay.scroll() which is not implemented!");
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
        NativeEvent mouseEvent = event.getNativeEvent();
        if (hasElement(Element.as(mouseEvent.getEventTarget()))) {
            event.preventDefault();
            boolean up = mouseEvent.getMouseWheelVelocityY() > 0;
            if (up) {
                zoomIn(mouseEvent.getClientX(), 6f);
            } else {
                zoomOut(mouseEvent.getClientX(), 6f);
            }
        } else {
            VConsole.error("Mouse wheel on unknowm element");
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
        if (event.getTypeInt() == Event.ONMOUSEUP
                && event.getNativeEvent().getButton() == NativeEvent.BUTTON_LEFT) {
            mouseIsDown = false;
            if (mouseOrTouchActive) {
                stopDragging(null);
            }
        } else if (event.getTypeInt() == Event.ONMOUSEDOWN
                && event.getNativeEvent().getButton() == NativeEvent.BUTTON_LEFT
                && hasElement((Element) event.getNativeEvent().getEventTarget()
                        .cast())) {
            mouseIsDown = true;
        } else if (event.getTypeInt() == Event.ONTOUCHEND) {
            if (mouseOrTouchActive) {
                stopDragging(null);
            }
        }
    }

    /**
     * Initializes the canvas i.e. it will fetch the points for the whole
     * timeline and render it to the canvas.
     */
    public void refresh() {
        if (!isVisible()) {
            return;
        }
        setRange(getSelectionStartDate(), getSelectionEndDate(), false, false);
    }

    /**
     * Is there currently a mouse event occurring
     * 
     * @return
     */
    public boolean isMouseActive() {
        return mouseOrTouchActive;
    }

    /**
     * Called when the plotting starts. Override to use.
     */
    @Override
    public void plottingStarts() {

        // Hide dots
        for (HTML dot : dots) {
            dot.setVisible(false);
        }

        // Hide bars if in barchar mode
        if (currentMode == PlotMode.BAR) {
            for (HTML bar : bars) {
                bar.setVisible(false);
            }
        }

        /*
         * Optimization. Instead of using canvas.clear() which will do a
         * canvas.drawRect() of the whole area we simple reset the width of the
         * canvas which automatically will reset the canvas context
         */
        canvas.setWidth(canvas.getWidth());

        // Plot vertical scale if not stacked
        if (!widget.isGraphsStacked()) {
            plotVerticalScale(getCanvasYUnit(), getCanvas().getHeight());
        }
    }

    /**
     * A dot displayed over the graph
     */
    private class Dot extends HTML {

        // TODO check where if this value is needed somewhere!? Commented out as
        // not used.
        // private double value;

        public Dot(List<Integer> colors) {
            super(" ");
            setStyleName(CLASSNAME_DOT);
            getElement().getStyle().setBackgroundColor(
                    "rgb(" + colors.get(0) + "," + colors.get(1) + ","
                            + colors.get(2) + ")");
        }

        // public double getValue() {
        // return value;
        // }
        //
        // public void setValue(double value) {
        // this.value = value;
        // }
    }

    /**
     * Updates the highlight dots
     */
    private void createDots() {
        if (dots.size() != widget.getNumGraphs()) {
            // Remove old dots
            for (HTML dot : dots) {
                displayComponentPanel.remove(dot);
            }
            dots.clear();

            // Create the dots
            for (int dg = 0; dg < widget.getNumGraphs(); dg++) {
                Dot dot = new Dot(widget.getColorMap().get(dg));
                dots.add(dot);
                displayComponentPanel.add(dot);
            }
        }
    }

    /**
     * Updates the highlight bars
     */
    private void createBars() {
        if (bars.size() != widget.getNumGraphs() && currentMode == PlotMode.BAR) {
            // Remove old bars
            for (HTML bar : bars) {
                displayComponentPanel.remove(bar);
            }
            bars.clear();

            // Create the bars
            for (int dg = 0; dg < widget.getNumGraphs(); dg++) {
                HTML bar = new HTML("");
                bar.setStyleName(CLASSNAME_BAR);

                bars.add(bar);
                displayComponentPanel.add(bar);
            }
        }
    }

    /**
     * Called when the plotting ends. Override to use.
     */
    @Override
    public void plottingEnds() {

        // Plot the horizontal scale
        plotHorizontalScale(getCanvasXUnit(),
                getSelectionStartDate().getTime(), getSelectionEndDate()
                        .getTime());

        // Plot vertical scale (if stacked)
        if (widget.isGraphsStacked()) {
            canvas.setGlobalCompositeOperation(Canvas.DESTINATION_OVER);
            plotVerticalScale(getCanvasYUnit(), getCanvas().getHeight());
            canvas.setGlobalCompositeOperation(Canvas.SOURCE_OVER);
        }

        // Create the highlight dots if needed
        createDots();

        // Create the highlight bars if needed
        createBars();

        // Set the text fields with the correct date
        widget.setFromDateTextField(currentStartDate);
        widget.setToDateTextField(currentEndDate);

        plotter.setListener(null);

        Long renderTime = new Date().getTime() - renderStart.getTime();
        VConsole.log("VTimelineDisplay: Rendering time " + renderTime + "ms");
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
        // Called by super.plotData
        currentGraphs = graphs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getFillColors
     * (int)
     */
    @Override
    protected List<Integer> getFillColors(int graphIndex) {
        return widget.getFillColorMap().get(graphIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.addon.timeline.gwt.client.VTimelineCanvasComponent#getColors
     * (int)
     */
    @Override
    protected List<Integer> getColors(int graphIndex) {
        return widget.getColorMap().get(graphIndex);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.TouchEndHandler#onTouchEnd(com.google
     * .gwt.event.dom.client.TouchEndEvent)
     */
    public void onTouchEnd(TouchEndEvent event) {
        if (lastTouchFingerDistance > 0) {
            touchMove(event);
            lastTouchFingerDistance = 0;
            /*
             * Adding a timer to prevent single multitouch end events to mess up
             * rendering
             */
            Timer t = new Timer() {
                @Override
                public void run() {
                    mouseOrTouchActive = false;
                    multitouching = false;
                    VConsole.log("VTimelineDisplay: Touch dragging ended (with a delay)");
                }
            };
            t.schedule(500);
        } else if (!multitouching) {
            stopDragging(event.getNativeEvent());
        }
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
        touchMove(event);
    }

    /**
     * Triggered whenever a touch event occurs
     * 
     * @param event
     *            The touch event
     */
    private void touchMove(TouchEvent<?> event) {
        event.preventDefault();
        if (event.getTouches().length() > 1) {
            Touch t1 = event.getTouches().get(0);
            Touch t2 = event.getTouches().get(1);
            int fingerDistance = Math.abs(t1.getClientX() - t2.getClientX());

            if (t1.getClientX() < t2.getClientX()) {
                /*
                 * t1 is left finger, t2 is right finger
                 */
                lastMouseX = t1.getClientX() + fingerDistance / 2;
            } else {
                /*
                 * t2 is left finger, t1 is right finger
                 */
                lastMouseX = t2.getClientX() + fingerDistance / 2;
            }

            if (lastTouchFingerDistance > 0) {
                if (fingerDistance > lastTouchFingerDistance) {
                    zoomOut(lastMouseX,
                            Math.abs(lastTouchFingerDistance - fingerDistance) / 10);
                } else {
                    zoomIn(lastMouseX,
                            Math.abs(lastTouchFingerDistance - fingerDistance) / 10);
                }
            } else {
                lastTouchFingerDistance = fingerDistance;
            }
        } else if (!multitouching) {
            lastMouseX = Util.getTouchOrMouseClientX(event.getNativeEvent());
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
            mouseOrTouchActive = true;
            lastTouchFingerDistance = 0;
            multitouching = true;
            touchMove(event);
        } else {
            startDrag(event.getNativeEvent());
        }
    }

    /**
     * Sets the vertical scale line values
     * 
     * @param lines
     *            The values of the vertical scale
     */
    public void setVerticalScaleLines(float... lines) {
        verticalScaleLines = lines;
        redraw();
    }

    /**
     * Returns the vertical scale lines or null if automatic lines are being
     * used.
     * 
     * @return
     */
    public float[] getVerticalScaleLines() {
        return verticalScaleLines;
    }

    /**
     * Sets the text that is displayed when no data source is present
     * 
     * @param caption
     *            The text that should be displayed
     */
    public void setNoDataSourceCaption(String caption) {
        noDataLabel.setHTML(caption);
    }

    /**
     * Returns the number format used by the vertical axis to display the values
     * 
     * @return
     */
    public NumberFormat getVerticalAxisNumberFormat() {
        return verticalAxisNumberFormat;
    }

    /**
     * Set the format to format the vertical axis value to a string. See
     * http://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html
     * form more information about the different formats that can be used.
     * 
     * @param verticalAxisNumberFormat
     *            The format of the number to display
     */
    public void setVerticalAxisNumberFormat(
            NumberFormat verticalAxisNumberFormat) {
        this.verticalAxisNumberFormat = verticalAxisNumberFormat;
        redraw();
    }
}
