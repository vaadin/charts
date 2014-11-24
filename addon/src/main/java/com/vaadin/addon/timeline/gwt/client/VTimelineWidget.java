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
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.vaadin.addon.timeline.gwt.client.VTimelineDisplay.PlotMode;
import com.vaadin.client.ApplicationConnection;
import com.vaadin.client.DateTimeService;
import com.vaadin.client.Focusable;
import com.vaadin.client.LocaleNotLoadedException;
import com.vaadin.client.Paintable;
import com.vaadin.client.UIDL;
import com.vaadin.client.Util;
import com.vaadin.client.VConsole;
import com.vaadin.client.ValueMap;

/**
 * VTimelineWidget
 * 
 */
@SuppressWarnings("deprecation")
public class VTimelineWidget extends FocusPanel implements Paintable,
        KeyDownHandler, KeyUpHandler, KeyPressHandler, Focusable {

    // Style names
    public static final String TAGNAME = "timeline-widget";
    public static final String CLASSNAME = "v-" + TAGNAME;

    public static final String DISPLAY_CLASSNAME = CLASSNAME + "-display";
    public static final String BROWSER_CLASSNAME = CLASSNAME + "-browser";
    public static final String CAPTION_CLASSNAME = CLASSNAME + "-caption";

    private static final String CLASSNAME_TOPBAR = CLASSNAME + "-topbar";
    private static final String CLASSNAME_ZOOMBAR = CLASSNAME + "-zoombar";
    private static final String CLASSNAME_ZOOMBARLABEL = CLASSNAME_ZOOMBAR
            + "-label";
    private static final String CLASSNAME_DATEFIELD = CLASSNAME + "-datefield";
    private static final String CLASSNAME_DATEFIELDEDIT = CLASSNAME_DATEFIELD
            + "-edit";
    private static final String CLASSNAME_DATERANGE = CLASSNAME + "-daterange";
    private static final String CLASSNAME_LEGEND = CLASSNAME + "-legend";
    private static final String CLASSNAME_LEGENDLABEL = CLASSNAME_LEGEND
            + "-label";
    private static final String CLASSNAME_LEGENDLABELVALUE = CLASSNAME_LEGEND
            + "-value";
    private static final String CLASSNAME_CHARTMODE = CLASSNAME + "-chartmode";
    private static final String CLASSNAME_CHARTMODELINE = CLASSNAME_CHARTMODE
            + "-line";
    private static final String CLASSNAME_CHARTMODESCATTER = CLASSNAME_CHARTMODE
            + "-scatter";
    private static final String CLASSNAME_CHARTMODEBAR = CLASSNAME_CHARTMODE
            + "-bar";
    private static final String CLASSNAME_MODELEGEND_ROW = CLASSNAME
            + "-modelegend";

    private final ClickHandler modeClickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            if (event.getSource() == lineModeBtn) {
                setChartMode(PlotMode.LINE, true);
            } else if (event.getSource() == barModeBtn) {
                setChartMode(PlotMode.BAR, true);
            } else if (event.getSource() == scatterModeBtn) {
                setChartMode(PlotMode.SCATTER, true);
            }
        }
    };
    private final ClickHandler zoomClickHandler = new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
            zoomLevelClicked(event);
        }
    };

    // Panels
    private final HorizontalPanel topBar;
    private final HorizontalPanel zoomBar;
    private final HorizontalPanel dateRangeBar;
    private final HorizontalPanel chartModeBar;
    private final HorizontalPanel modeLegendBar;

    // Legend
    private final HorizontalPanel legend;
    private final List<Label> legendCaptions = new ArrayList<Label>();
    private final List<HTML> legendColors = new ArrayList<HTML>();
    private final List<Label> legendValues = new ArrayList<Label>();

    // Date selection
    private final TextBox dateFrom;
    private final TextBox dateTo;
    private Date intervalStartDate, intervalEndDate;

    // Default UIDL stuff
    private ApplicationConnection client;
    private String uidlId;

    // Initialization
    private boolean initStage1Done = false;
    private boolean initStage2Done = false;
    private boolean initDone = false;
    private boolean noDataAvailable = false;
    private Date selectedStartDate, selectedEndDate;

    // Components
    private VDateFormatInfo dateFormatInfo = new VDateFormatInfo();
    private VTimelineDisplay display;
    private VTimelineBrowser browser;
    private final Map<Integer, VClientCache> multiLevelCache = new HashMap<Integer, VClientCache>();

    // Base
    private final FlowPanel root = new FlowPanel();
    private final Label caption;
    private boolean isIdle = true;
    private boolean clientCacheEnabled = true;
    private PlotMode initPlotMode = PlotMode.LINE;
    private String initGridColor = "rgb(200,200,200)";
    private boolean selectionLock = true;
    private boolean graphStacking = false;
    private boolean uniformBarThickness = false;
    private String currentLocale;
    private DateTimeService dts;

    private boolean dateRangeChangeEventsEnabled = false;
    private boolean eventClickListenersEnabled = false;

    // Data handling
    private long requestCounter = 0L;
    private final Map<Long, VDataListener> waitingForData = new HashMap<Long, VDataListener>();
    private final Map<Long, Integer> requestGraphMap = new HashMap<Long, Integer>();
    private final Map<Integer, Boolean> graphVisibilites = new HashMap<Integer, Boolean>();
    private final Map<Long, Integer> densityMap = new HashMap<Long, Integer>();
    private final List<Integer> containerSizes = new ArrayList<Integer>();
    private int offscreenPoints = 100;

    // Graph specific properties
    private Date startDate = null;
    private Date endDate = null;
    private int numGraphs = 0;

    // Graph colors
    private final Map<Integer, String> colors = new HashMap<Integer, String>();
    private final Map<Integer, String> fillcolors = new HashMap<Integer, String>();
    private final Map<Integer, Double> thicknesses = new HashMap<Integer, Double>();
    private final Map<Integer, Boolean> caps = new HashMap<Integer, Boolean>();

    // Browser colors
    private final Map<Integer, String> browserColors = new HashMap<Integer, String>();
    private final Map<Integer, String> browserFillColors = new HashMap<Integer, String>();

    // Legend values
    private final Map<Integer, String> captions = new HashMap<Integer, String>();
    private final Map<Integer, String> units = new HashMap<Integer, String>();

    // Zoom levels
    private final Map<HTML, Long> zoomLevels = new HashMap<HTML, Long>();

    // Chart mode
    private final Button lineModeBtn;
    private final Button scatterModeBtn;
    private final Button barModeBtn;

    // Component visibilities
    private boolean browserIsVisible = true;
    private boolean zoomIsVisible = true;
    private boolean dateSelectVisible = true;
    private boolean dateSelectEnabled = true;
    private boolean chartModeVisible = true;
    private boolean lineChartModeVisible = true;
    private boolean barChartModeVisible = true;
    private boolean scatterChartModeVisible = true;
    private boolean legendVisible = true;
    private boolean graphShadows = true;

    private List<HandlerRegistration> handlers = new LinkedList<HandlerRegistration>();

    public VTimelineWidget() {
        setWidget(root);
        getElement().setClassName("v-timeline");

        dts = new DateTimeService();

        root.setStyleName(CLASSNAME);
        root.setSize("100%", "100%");

        caption = new Label("");
        caption.setStyleName(CAPTION_CLASSNAME);
        caption.setVisible(false);
        root.add(caption);

        endDate = new Date();
        startDate = new Date(endDate.getTime() - VDateFormatInfo.MONTH);

        topBar = new HorizontalPanel();
        topBar.setStyleName(CLASSNAME_TOPBAR);
        topBar.setVisible(zoomIsVisible || dateSelectVisible);
        root.add(topBar);

        zoomBar = new HorizontalPanel();
        zoomBar.setStyleName(CLASSNAME_ZOOMBAR);
        zoomBar.setVisible(zoomIsVisible);

        Label zoomLbl = new Label("Zoom:");
        zoomLbl.setStyleName(CLASSNAME_ZOOMBARLABEL);
        zoomBar.add(zoomLbl);
        topBar.add(zoomBar);

        dateRangeBar = new HorizontalPanel();
        dateRangeBar.setStyleName(CLASSNAME_DATERANGE);
        dateRangeBar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
        dateRangeBar.setVisible(dateSelectVisible);

        dateFrom = new TextBox();
        dateFrom.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                try {
                    Date newDate = dts.parseDate(event.getValue(),
                            dateFormatInfo.getEditFormat().getPattern(), true);
                    if ((newDate.equals(startDate) || newDate.after(startDate))
                            && (newDate.equals(endDate) || newDate
                                    .before(endDate))) {
                        intervalStartDate = newDate;
                        setBrowserRange(intervalStartDate, intervalEndDate);
                        setDisplayRange(intervalStartDate, intervalEndDate);
                        dateFrom.setFocus(false);
                    } else {
                        dateFrom.setText(dts.formatDate(intervalStartDate,
                                dateFormatInfo.getEditFormat().getPattern()));
                    }
                } catch (IllegalArgumentException iae) {
                    dateFrom.setText(dts.formatDate(intervalStartDate,
                            dateFormatInfo.getEditFormat().getPattern()));
                }
            }
        });
        dateFrom.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                dateFrom.setStyleName(CLASSNAME_DATEFIELDEDIT);
                dateFrom.setText(dts.formatDate(intervalStartDate,
                        dateFormatInfo.getEditFormat().getPattern()));
                dateFrom.selectAll();
            }
        });
        dateFrom.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                dateFrom.setStyleName(CLASSNAME_DATEFIELD);
                dateFrom.setText(dts.formatDate(intervalStartDate,
                        dateFormatInfo.getDisplayFormat().getPattern()));
            }
        });
        dateFrom.setReadOnly(!dateSelectEnabled);
        dateFrom.setStyleName(CLASSNAME_DATEFIELD);
        dateRangeBar.add(dateFrom);

        Label dash = new Label();
        dash.setText("-");
        dateRangeBar.add(dash);

        dateTo = new TextBox();
        dateTo.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                try {
                    Date newDate = dts.parseDate(event.getValue(),
                            dateFormatInfo.getEditFormat().getPattern(), true);
                    if ((newDate.equals(startDate) || newDate.after(startDate))
                            && (newDate.equals(endDate) || newDate
                                    .before(endDate))) {
                        intervalEndDate = newDate;
                        setBrowserRange(intervalStartDate, intervalEndDate);
                        setDisplayRange(intervalStartDate, intervalEndDate);
                        dateTo.setFocus(false);
                    } else {
                        dateTo.setText(dts.formatDate(intervalEndDate,
                                dateFormatInfo.getEditFormat().getPattern()));
                    }
                } catch (IllegalArgumentException iae) {
                    dateTo.setText(dts.formatDate(intervalEndDate,
                            dateFormatInfo.getEditFormat().getPattern()));
                }
            }
        });
        dateTo.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                dateTo.setStyleName(CLASSNAME_DATEFIELDEDIT);
                dateTo.setText(dts.formatDate(intervalEndDate, dateFormatInfo
                        .getEditFormat().getPattern()));
                dateTo.selectAll();
            }
        });
        dateTo.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                dateTo.setStyleName(CLASSNAME_DATEFIELD);
                dateTo.setText(dts.formatDate(intervalEndDate, dateFormatInfo
                        .getDisplayFormat().getPattern()));
            }
        });
        dateTo.setReadOnly(!dateSelectEnabled);
        dateTo.setStyleName(CLASSNAME_DATEFIELD);
        dateRangeBar.add(dateTo);

        topBar.add(dateRangeBar);
        topBar.setCellHorizontalAlignment(dateRangeBar,
                HorizontalPanel.ALIGN_RIGHT);

        legend = new HorizontalPanel();
        legend.setVisible(legendVisible);
        legend.setHeight("30px");
        legend.setStyleName(CLASSNAME_LEGEND);

        chartModeBar = new HorizontalPanel();
        chartModeBar.setVisible(chartModeVisible);
        chartModeBar.setStyleName(CLASSNAME_CHARTMODE);

        Label modelbl = new Label("Chart mode:");
        chartModeBar.add(modelbl);

        lineModeBtn = new Button("", modeClickHandler);
        lineModeBtn.setTabIndex(-1);
        lineModeBtn.setVisible(lineChartModeVisible);
        lineModeBtn.setStyleName(CLASSNAME_CHARTMODELINE);
        lineModeBtn.setTitle("Line Graph");
        chartModeBar.add(lineModeBtn);

        barModeBtn = new Button("", modeClickHandler);
        barModeBtn.setTabIndex(-1);
        barModeBtn.setVisible(barChartModeVisible);
        barModeBtn.setStyleName(CLASSNAME_CHARTMODEBAR);
        barModeBtn.setTitle("Bar Graph");
        chartModeBar.add(barModeBtn);

        scatterModeBtn = new Button("", modeClickHandler);
        scatterModeBtn.setTabIndex(-1);
        scatterModeBtn.setVisible(scatterChartModeVisible);
        scatterModeBtn.setStyleName(CLASSNAME_CHARTMODESCATTER);
        scatterModeBtn.setTitle("Scatter Graph");
        chartModeBar.add(scatterModeBtn);

        modeLegendBar = new HorizontalPanel();
        modeLegendBar.setVisible(chartModeVisible || legendVisible);
        modeLegendBar.setWidth("100%");
        modeLegendBar.setHeight("31px");
        modeLegendBar.setStyleName(CLASSNAME_MODELEGEND_ROW);
        modeLegendBar.add(chartModeBar);
        modeLegendBar.add(legend);
        modeLegendBar.setCellHorizontalAlignment(legend,
                HorizontalPanel.ALIGN_RIGHT);

        root.add(modeLegendBar);

        // Add the display
        display = new VTimelineDisplay(this);
        root.add(display);

        // Add the browser
        browser = new VTimelineBrowser(this);
        browser.setVisible(browserIsVisible);
        root.add(browser);
    }

    /**
     * Returns the {@link DateTimeService} used by timezone calculations
     * 
     * @return
     */
    public DateTimeService getDateTimeService() {
        return dts;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.Widget#onLoad()
     */
    @Override
    protected void onLoad() {
        super.onLoad();

        // Keyboard listeners
        handlers.add(addDomHandler(this, KeyPressEvent.getType()));
        handlers.add(addDomHandler(this, KeyDownEvent.getType()));
        handlers.add(addDomHandler(this, KeyUpEvent.getType()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.Widget#onUnload()
     */
    @Override
    protected void onUnload() {
        super.onUnload();
        for (HandlerRegistration handler : handlers) {
            handler.removeHandler();
        }
        handlers.clear();
    }

    void recalculateHeights(int height) {
        int captionHeight = getCaptionHeight();
        int topBarHeight = topBar.isVisible() ? Util.getRequiredHeight(topBar)
                : 0;
        int modeLegendBarHeight = modeLegendBar.isVisible() ? Util
                .getRequiredHeight(modeLegendBar) : 0;
        int browserHeight = browser.isVisible() ? Util
                .getRequiredHeight(browser) : 0;
        int displayHeight = height - captionHeight - topBarHeight
                - modeLegendBarHeight - browserHeight;
        display.setHeight(displayHeight + "px");
        if (isInitDone()) {
            display.redraw();
            browser.redraw();
        }
    }

    void recalculateWidths(int width) {
        caption.setWidth((width - 5) + "px");
        topBar.setWidth(width + "px");
        modeLegendBar.setWidth(width + "px");
        browser.setWidth(width + "px");
        display.setWidth(width + "px");
        if (isInitDone()) {
            display.redraw();
            browser.redraw();
        }
    }

    /**
     * Adds a zoom level to the zoom bar
     * 
     * @param caption
     *            The caption of the zoom level
     * @param time
     *            The time in milliseconds of the zoom level
     */
    private void addZoomLevel(String caption, Long time) {
        HTML level = new HTML(caption);
        level.setStyleName("zoom-level");
        level.addClickHandler(zoomClickHandler);
        zoomLevels.put(level, time);
        zoomBar.add(level);
    }

    /**
     * Initializes the widget
     */
    private boolean init1(UIDL uidl) {

        clearCache();
        setCache(uidl);

        // Set initial data
        setStartDate(uidl);
        setEndDate(uidl);
        setNumGraphs(uidl);
        setCaption(uidl);
        setZoomCaption(uidl);
        setChartModeCaption(uidl);
        setNoDataSourceCaption(uidl);
        setSelectionLock(uidl);
        setDateFormatInfo(uidl);
        setLocale(uidl);

        setVerticalFitting(uidl);
        setVerticalGraphLines(uidl);

        // Component UI
        setZoomVisibility(uidl);
        setChartModeVisibility(uidl);
        setDateSelectEnabled(uidl);
        setDateSelectVisibility(uidl);
        setBarChartModeVisible(uidl);
        setLineChartModeVisible(uidl);
        setScatterChartModeVisible(uidl);
        setBrowserVisibility(uidl);
        setLegendVisibility(uidl);

        handleDateRangeChangeEventEnabled(uidl);
        handleOffscreeRendering(uidl);
        setSelectionRange(uidl);
        handleVerticalAxisNumberFormat(uidl);

        return true;
    }

    private boolean init2(UIDL uidl) {
        init1(uidl);

        handleEventClickListenersEnabled(uidl);
        handleGraphShadows(uidl);

        setGraphOutlineColors(uidl);
        setGraphFillColors(uidl);
        setBrowserFillColors(uidl);
        setBrowserOutlineColors(uidl);
        setLineCaps(uidl);
        setLineGraphThickness(uidl);
        setGridColor(uidl);

        setZoomLevels(uidl);

        setZoomCaption(uidl);
        setChartModeCaption(uidl);
        setNoDataSourceCaption(uidl);

        setGraphCaptions(uidl);
        setGraphUnits(uidl);
        setGraphVisibilities(uidl);

        handleOnePointGraph();

        setChartMode(uidl);

        // Refresh the browser part
        if (browserIsVisible) {
            browser.refresh();
        }

        // If no data is present, then show "No data" label
        setNoData(uidl);

        return true;
    }

    private void setLineGraphThickness(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_LINE_GRAPH_THICKNESS)) {
            String[] ts = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_LINE_GRAPH_THICKNESS);
            thicknesses.clear();
            for (int t = 0; t < ts.length; t++) {
                double thickness = Double.parseDouble(ts[t]);
                thicknesses.put(t, thickness);
            }
            if (isInitDone()) {
                display.redraw();
            }
        }
    }

    private void setStartDate(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_START_DATE)) {
            startDate = new Date(
                    uidl.getLongAttribute(TimelineConstants.ATTRIBUTE_START_DATE));
        }
    }

    private void setEndDate(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_END_DATE)) {
            endDate = new Date(
                    uidl.getLongAttribute(TimelineConstants.ATTRIBUTE_END_DATE));
        }
    }

    private void setNumGraphs(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_NUMBER_OF_GRAPHS)) {
            numGraphs = uidl
                    .getIntAttribute(TimelineConstants.ATTRIBUTE_NUMBER_OF_GRAPHS);
        }
    }

    private void setCaption(UIDL uidl) {
        if (uidl.hasAttribute("caption")) {
            String captionText = uidl.getStringAttribute("caption");
            setCaption(captionText);
        }
    }

    void setCaption(String captionText) {
        boolean captionWasVisible = caption.isVisible();

        if (captionText == null || captionText.equals("")) {
            caption.setText("");
            caption.setVisible(false);
        } else {
            caption.setText(captionText);
            caption.setVisible(true);
        }

        if (captionWasVisible != caption.isVisible()) {
            if (isInitDone()) {
                recalculateHeights(getOffsetHeight());
                display.redraw();
                browser.redraw();
            }
        }
    }

    private void setSelectionLock(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_LOCK)) {
            selectionLock = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_LOCK);
        } else {
            selectionLock = false;
        }
    }

    private void setZoomLevels(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVELS)) {
            ValueMap levelMap = uidl
                    .getMapAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVELS);

            if (zoomBar != null) {
                for (HTML lvl : zoomLevels.keySet()) {
                    zoomBar.remove(lvl);
                }
            }
            zoomLevels.clear();

            for (String caption : levelMap.getKeySet()) {
                Long time = Long.parseLong(levelMap.getString(caption));
                addZoomLevel(caption, time);
            }
        }
    }

    private void setZoomVisibility(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_ZOOMBAR_VISIBILITY)) {
            zoomIsVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_ZOOMBAR_VISIBILITY);

            if (zoomBar != null) {
                zoomBar.setVisible(zoomIsVisible);
                setTopBarVisibility(zoomIsVisible || dateSelectVisible);
            }
        }
    }

    private void setZoomCaption(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVEL_CAPTION)) {
            String caption = uidl
                    .getStringAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVEL_CAPTION);
            Label lbl = (Label) zoomBar.getWidget(0);
            lbl.setText(caption);
        }
    }

    private void setNoDataSourceCaption(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_NO_DS_CAPTION)) {
            String caption = uidl
                    .getStringAttribute(TimelineConstants.ATTRIBUTE_NO_DS_CAPTION);
            display.setNoDataSourceCaption(caption);
        }
    }

    private void setModeLegendBarVisibility(boolean visibility) {
        boolean isVisible = modeLegendBar.isVisible();
        if (isVisible != visibility) {
            modeLegendBar.setVisible(visibility);
            if (isInitDone()) {
                display.redraw();
                browser.redraw();
            }
        }
    }

    private void setTopBarVisibility(boolean visibility) {
        if (topBar != null) {
            boolean isVisible = topBar.isVisible();
            if (isVisible != visibility) {
                topBar.setVisible(visibility);
                if (isInitDone()) {
                    display.redraw();
                    browser.redraw();
                }
            }
        }
    }

    private Timer refreshTimer = new Timer() {
        @Override
        public void run() {
            // Clear the cache from old data
            clearCache();

            // Render the browser canvas (refreshes the selection after loading
            // the data)
            browser.refresh();

            /*
             * Refresh display
             */
            display.resetVerticalScaleFitting();
            display.resetDisplayCache();
            display.refresh();
        }
    };

    public boolean isSelectionLocked() {
        return selectionLock;
    }

    private void setDirty(UIDL uidl) {
        if (uidl.getBooleanAttribute(TimelineConstants.ATTRIBUTE_DIRTY)
                && isInitDone()) {
            refreshTimer.cancel();
            refreshTimer.schedule(500);
        }
    }

    private void clearCache() {
        multiLevelCache.clear();
        multiLevelCache.put(1, new VClientCache(VTimelineWidget.this));
        display.clearCache();
    }

    private void setGraphStacking(UIDL uidl) {
        graphStacking = uidl
                .getBooleanAttribute(TimelineConstants.ATTRIBUTE_STACKED);
    }

    private void setUniformBarThicknessEnabled(UIDL uidl) {
        uniformBarThickness = uidl
                .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UNIFORM_BARCHART);
    }

    private void setContainerSizes(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_CONTAINER_SIZES)) {
            int[] sizes = uidl
                    .getIntArrayVariable(TimelineConstants.VARIABLE_CONTAINER_SIZES);
            containerSizes.clear();
            int maxSize = 0;
            for (int size : sizes) {
                containerSizes.add(size);
                maxSize = size > maxSize ? size : maxSize;
            }

            // Cache is not up to date so empty it
            clearCache();
        }
    }

    private void setCache(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_CACHE)) {
            clientCacheEnabled = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_CACHE);

            // Clear current cache
            clearCache();
        }
    }

    private void setVerticalFitting(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING)) {
            boolean auto = uidl
                    .getBooleanVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING);
            float min = 0f;
            float max = 0f;

            if (uidl.hasVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING_MIN)
                    && uidl.hasVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING_MAX)) {
                min = uidl
                        .getFloatVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING_MIN);
                max = uidl
                        .getFloatVariable(TimelineConstants.VARIABLE_VERTICAL_FITTING_MAX);
            }

            display.setVerticalFitting(auto, min, max);
        }
    }

    private void setVerticalGraphLines(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_VERTICAL_GRID_LINES)) {
            String[] linesStr = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_VERTICAL_GRID_LINES);
            float[] values = new float[linesStr.length];
            for (int i = 0; i < linesStr.length; i++) {
                values[i] = Float.parseFloat(linesStr[i]);
            }
            display.setVerticalScaleLines(values);
        } else if (uidl
                .hasVariable(TimelineConstants.VARIABLE_NO_VERTICAL_GRID)
                && uidl.getBooleanVariable(TimelineConstants.VARIABLE_NO_VERTICAL_GRID)) {
            display.setVerticalScaleLines(null);
        }
    }

    private void setGraphCaptions(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_CAPTIONS)) {
            String[] caps = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_CAPTIONS);

            captions.clear();
            for (int g = 0; g < caps.length; g++) {
                captions.put(g, caps[g]);
            }

            // Create the legend labels
            if (!captions.isEmpty()) {
                legend.clear();
                legendColors.clear();
                legendCaptions.clear();
                legendValues.clear();
                for (Integer graph : captions.keySet()) {
                    String caption = captions.get(graph);

                    if (colors.get(graph) != null) {
                        HTML color = new HTML("<B>—</B>");
                        color.setHeight("15px");
                        String cc = colors.get(graph);
                        DOM.setStyleAttribute(color.getElement(), "color", cc);
                        legend.add(color);
                        legendColors.add(color);
                    }

                    HTML html = new HTML();
                    legend.add(html);

                    Label lbl = new Label(caption);
                    lbl.setHeight("15px");
                    lbl.setStyleName(CLASSNAME_LEGENDLABEL);
                    html.getElement().appendChild(lbl.getElement());
                    legendCaptions.add(lbl);

                    Label val = new Label();
                    val.setHeight("15px");
                    val.setStyleName(CLASSNAME_LEGENDLABELVALUE);
                    html.getElement().appendChild(val.getElement());
                    legendValues.add(val);
                }
            }
        }
    }

    private void setSelectionRange(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_SELECTION_START)
                && uidl.hasVariable(TimelineConstants.VARIABLE_SELECTION_END)) {
            selectedStartDate = new Date(
                    uidl.getLongVariable(TimelineConstants.VARIABLE_SELECTION_START));
            selectedEndDate = new Date(
                    uidl.getLongVariable(TimelineConstants.VARIABLE_SELECTION_END));

            if (selectedStartDate.before(startDate)) {
                selectedStartDate = startDate;
            }

            if (selectedEndDate.after(endDate)) {
                selectedEndDate = endDate;
            }

            if (browser.isVisible()) {
                browser.setRange(selectedStartDate, selectedEndDate);
                selectedStartDate = browser.getSelectionStart();
                selectedEndDate = browser.getSelectionEnd();
            }

            display.setRange(selectedStartDate, selectedEndDate);
            if (!isInitDone()) {
                selectedStartDate = display.getSelectionStartDate();
                selectedEndDate = display.getSelectionEndDate();
            }
        }
    }

    private void setGraphVisibilities(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_VISIBILITIES)) {
            String[] v = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_VISIBILITIES);
            for (int i = 0; i < v.length; i++) {
                setLegendCaptionVisibility(i, Boolean.parseBoolean(v[i]));
            }
        }
    }

    private void setGraphUnits(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_VERTICAL_UNIT)) {
            String[] gUnits = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_VERTICAL_UNIT);
            for (int g = 0; g < gUnits.length; g++) {
                units.put(g, gUnits[g]);
            }
        }
    }

    private void setGridColor(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_GRID_COLOR)) {
            String color = uidl
                    .getStringAttribute(TimelineConstants.ATTRIBUTE_GRID_COLOR);
            if (color == null || color.equals("")) {
                initGridColor = null;
            } else {
                initGridColor = color;
            }

            display.setGridColor(initGridColor);
        }
    }

    private void setBarChartModeVisible(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_BARCHART_VISIBILITY)) {
            barChartModeVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_BARCHART_VISIBILITY);
            if (barModeBtn != null) {
                barModeBtn.setVisible(barChartModeVisible);
                chartModeBar
                        .setVisible(chartModeVisible
                                && (lineChartModeVisible || barChartModeVisible || scatterChartModeVisible));
            }
        }
    }

    private void setScatterChartModeVisible(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_SCATTERCHART_VISIBILITY)) {
            scatterChartModeVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_SCATTERCHART_VISIBILITY);
            if (scatterModeBtn != null) {
                scatterModeBtn.setVisible(scatterChartModeVisible);
                chartModeBar
                        .setVisible(chartModeVisible
                                && (lineChartModeVisible || barChartModeVisible || scatterChartModeVisible));
            }
        }
    }

    private void setChartModeVisibility(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_VISIBILITY)) {
            chartModeVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_VISIBILITY);
            if (chartModeBar != null) {
                chartModeBar.setVisible(chartModeVisible);
            }
        }
    }

    private void setChartMode(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE)) {
            setChartMode(
                    PlotMode.valueOf(uidl
                            .getStringAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE)),
                    initStage2Done);
        }
    }

    void setChartMode(PlotMode mode, boolean plot) {
        initPlotMode = mode;
        display.setChartMode(initPlotMode, plot);
        browser.setChartMode(initPlotMode, plot);
    }

    private void setChartModeCaption(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE_CAPTION)) {
            String caption = uidl
                    .getStringAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE_CAPTION);
            Label lbl = (Label) chartModeBar.getWidget(0);
            lbl.setText(caption);
        }
    }

    private void setDateSelectEnabled(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_DATESELECT_ENABLED)) {
            dateSelectEnabled = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_DATESELECT_ENABLED);
            dateFrom.setReadOnly(!dateSelectEnabled);
            dateTo.setReadOnly(!dateSelectEnabled);
        }
    }

    private void setDateSelectVisibility(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_DATESELECT_VISIBILITY)) {
            dateSelectVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_DATESELECT_VISIBILITY);

            if (dateRangeBar != null) {
                dateRangeBar.setVisible(dateSelectVisible);
                setTopBarVisibility(zoomIsVisible || dateSelectVisible);
            }
        }
    }

    private void setLegendVisibility(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIUBTE_UI_LEGEND_VISIBILITY)) {
            legendVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIUBTE_UI_LEGEND_VISIBILITY);
            if (legend != null) {
                legend.setVisible(legendVisible);
                setModeLegendBarVisibility(legendVisible
                        || chartModeBar.isVisible());
            }
        }
    }

    private void setBrowserVisibility(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_BROWSER_VISIBLITY)) {
            boolean browserWasVisible = browserIsVisible;
            browserIsVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_BROWSER_VISIBLITY);
            if (browser != null && browserWasVisible != browserIsVisible) {
                browser.setVisible(browserIsVisible);
                if (isInitDone()) {
                    display.redraw();
                    browser.redraw();
                }
            }
        }
    }

    private void setBrowserOutlineColors(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_BROWSER_COLORS)) {
            browserColors.clear();
            String[] colorStrings = uidl
                    .getStringArrayAttribute(TimelineConstants.ATTRIBUTE_BROWSER_COLORS);
            for (int s = 0; s < colorStrings.length; s++) {
                browserColors.put(s, colorStrings[s]);
            }
            if (isInitDone()) {
                browser.redraw();
            }
        }
    }

    private void setBrowserFillColors(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_BROWSER_FILLCOLORS)) {
            browserFillColors.clear();
            String[] colorStrings = uidl
                    .getStringArrayAttribute(TimelineConstants.ATTRIBUTE_BROWSER_FILLCOLORS);
            for (int s = 0; s < colorStrings.length; s++) {
                browserFillColors.put(s, colorStrings[s]);
            }
            if (isInitDone()) {
                browser.redraw();
            }
        }
    }

    private void setGraphOutlineColors(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_COLORS)) {
            colors.clear();
            String[] colorStrings = uidl
                    .getStringArrayAttribute(TimelineConstants.ATTRIBUTE_COLORS);
            for (int s = 0; s < colorStrings.length; s++) {
                colors.put(s, colorStrings[s]);
            }
            if (isInitDone()) {
                display.redraw();
            }
        }
    }

    private void setGraphFillColors(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_FILLCOLORS)) {
            fillcolors.clear();
            String[] colorStrings = uidl
                    .getStringArrayAttribute(TimelineConstants.ATTRIBUTE_FILLCOLORS);
            for (int s = 0; s < colorStrings.length; s++) {
                fillcolors.put(s, colorStrings[s]);
            }
            if (isInitDone()) {
                display.redraw();
            }
        }
    }

    private void setLineCaps(UIDL uidl) {
        if (uidl.hasVariable(TimelineConstants.VARIABLE_CAPS)
                && display != null) {
            caps.clear();
            String[] cs = uidl
                    .getStringArrayVariable(TimelineConstants.VARIABLE_CAPS);
            for (int c = 0; c < cs.length; c++) {
                caps.put(c, Integer.parseInt(cs[c]) == 1 ? true : false);
            }
            if (isInitDone()) {
                display.redraw();
            }
        }
    }

    private void setLineChartModeVisible(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_LINECHART_VISIBILITY)) {
            lineChartModeVisible = uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_UI_CHARTMODE_LINECHART_VISIBILITY);
            if (lineModeBtn != null) {
                lineModeBtn.setVisible(lineChartModeVisible);
                chartModeBar
                        .setVisible(chartModeVisible
                                && (lineChartModeVisible || barChartModeVisible || scatterChartModeVisible));
            }
        }
    }

    private void setLocale(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_LOCALE)) {
            final String locale = uidl
                    .getStringAttribute(TimelineConstants.ATTRIBUTE_LOCALE);
            try {
                dts.setLocale(locale);
                currentLocale = locale;
            } catch (final LocaleNotLoadedException e) {
                currentLocale = dts.getLocale();
                VConsole.error("Tried to use an unloaded locale \"" + locale
                        + "\". Using default locale (" + currentLocale + ").");
                VConsole.error(e);
            }
        }
    }

    private void handleOnePointGraph() {
        if (startDate.equals(endDate)) {
            long halfDay = VDateFormatInfo.DAY / 2L;
            startDate = new Date(startDate.getTime() - halfDay);
            endDate = new Date(endDate.getTime() + halfDay);
            selectedStartDate = startDate;
            selectedEndDate = endDate;
            setBrowserRange(selectedStartDate, selectedEndDate);
            setDisplayRange(selectedStartDate, selectedEndDate);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.terminal.gwt.client.Paintable#updateFromUIDL(com.vaadin.terminal
     * .gwt.client.UIDL, com.vaadin.terminal.gwt.client.ApplicationConnection)
     */
    @Override
    public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {

        // Check parent components
        if (client.updateComponent(this, uidl, true)) {
            return;
        }

        // Store reference variables for later usage
        this.client = client;
        uidlId = uidl.getId();

        // Initialization stage 1
        if (!initStage1Done) {
            initStage1Done = init1(uidl);

            // Send init flag back to server along with the canvas width
            client.updateVariable(uidlId, TimelineConstants.VARIABLE_INIT,
                    true, false);
            client.updateVariable(uidlId,
                    TimelineConstants.VARIABLE_CANVAS_WIDTH, getOffsetWidth(),
                    true);
            return;

            // Initialization done
        } else if (!initStage2Done) {
            initStage2Done = init2(uidl);
            return;

        } else if (!isInitDone()) {
            initDone = true;
        }

        handleOffscreeRendering(uidl);
        handleDateRangeChangeEventEnabled(uidl);
        handleEventClickListenersEnabled(uidl);
        handleGraphShadows(uidl);
        handleVerticalAxisNumberFormat(uidl);

        // Basic
        setCaption(uidl);
        setLocale(uidl);
        setNoData(uidl);
        setSelectionLock(uidl);
        setGraphStacking(uidl);
        setUniformBarThicknessEnabled(uidl);
        setContainerSizes(uidl);
        setCache(uidl);

        // Dates
        setStartDate(uidl);
        setEndDate(uidl);
        handleOnePointGraph();

        // Graphs
        setNumGraphs(uidl);
        setGraphOutlineColors(uidl);
        setBrowserOutlineColors(uidl);
        setBrowserFillColors(uidl);
        setGraphFillColors(uidl);
        setGraphCaptions(uidl);
        setGraphVisibilities(uidl);
        setSelectionRange(uidl);
        setVerticalFitting(uidl);
        setVerticalGraphLines(uidl);
        setGraphUnits(uidl);
        setLineCaps(uidl);
        setLineGraphThickness(uidl);

        // Browser UI
        setBrowserVisibility(uidl);
        setZoomVisibility(uidl);
        setDateSelectVisibility(uidl);
        setDateSelectEnabled(uidl);
        setChartModeVisibility(uidl);
        setLineChartModeVisible(uidl);
        setBarChartModeVisible(uidl);
        setScatterChartModeVisible(uidl);
        setLegendVisibility(uidl);
        setZoomLevels(uidl);

        // Chrat modes and colors
        setChartMode(uidl);
        setGridColor(uidl);
        setZoomCaption(uidl);
        setChartModeCaption(uidl);
        setNoDataSourceCaption(uidl);
        setDateFormatInfo(uidl);

        // Data received
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_DATA)) {
            ValueMap map = uidl
                    .getMapAttribute(TimelineConstants.ATTRIBUTE_DATA);
            ValueMap changedDensitiesMap = uidl
                    .getMapAttribute(TimelineConstants.ATTRIBUTE_DENSITIES);

            List<Long> removableRequests = new LinkedList<Long>();
            Set<Long> currentlyWaitingData = new HashSet<Long>(
                    waitingForData.keySet());

            // Check waiting data list
            for (Long req : currentlyWaitingData) {
                for (String req2 : map.getKeySet()) {
                    if (req2.equals(req.toString())) {

                        // Process the request
                        processRequest(req, map, changedDensitiesMap, uidl);

                        // Remove the request
                        removableRequests.add(req);
                    }
                }
            }

            // Remove recieved requests
            for (Long requestToRemove : removableRequests) {
                waitingForData.remove(requestToRemove);
                requestGraphMap.remove(requestToRemove);
                densityMap.remove(requestToRemove);
            }

            isIdle = waitingForData.isEmpty();
        }

        // Handle cache cleaning
        setDirty(uidl);
    }

    private void handleGraphShadows(UIDL uidl) {
        boolean hadShadows = graphShadows;
        graphShadows = uidl
                .getBooleanAttribute(TimelineConstants.ATTRIBUTE_GRAPH_SHADOWS);
        if (hadShadows != graphShadows) {
            browser.redraw();
        }
    }

    private void handleDateRangeChangeEventEnabled(UIDL uidl) {
        dateRangeChangeEventsEnabled = uidl
                .getBooleanAttribute(TimelineConstants.ATTRIBUTE_CHANGE_EVENT_ENABLED);
    }

    private void handleOffscreeRendering(UIDL uidl) {
        offscreenPoints = uidl
                .getIntAttribute(TimelineConstants.ATTRIBUTE_OFFSCREEN_RENDERING);
    }

    private void handleEventClickListenersEnabled(UIDL uidl) {
        eventClickListenersEnabled = uidl
                .getBooleanAttribute(TimelineConstants.ATTRIBUTE_EVENT_CLICK_LISTENER_ENABLED);
    }

    private void setNoData(UIDL uidl) {
        // Disable component since no data source has been defined on the server
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_NODATA)) {
            setWidgetNoData(uidl
                    .getBooleanAttribute(TimelineConstants.ATTRIBUTE_NODATA));
        } else {
            setWidgetNoData(false);
        }
    }

    private void handleVerticalAxisNumberFormat(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_VERTICAL_NUMBER_FORMAT)) {
            NumberFormat format = NumberFormat
                    .getFormat(uidl
                            .getStringAttribute(TimelineConstants.ATTRIBUTE_VERTICAL_NUMBER_FORMAT));
            display.setVerticalAxisNumberFormat(format);
        }
    }

    private void processRequest(Long req, ValueMap map,
            ValueMap changedDensitiesMap, UIDL uidl) {

        VDataListener comp = waitingForData.get(req);

        // Get the values.
        List<String> values = new ArrayList<String>();
        String valuesString = map.getString(String.valueOf(req));
        if (valuesString != null && !valuesString.equals("")) {
            for (String val : map.getString(String.valueOf(req)).split(";")) {
                values.add(val);
            }
        }

        List<Float> fvalues = new ArrayList<Float>();
        List<Date> dvalues = new ArrayList<Date>();
        Integer density = densityMap.get(req);

        if (changedDensitiesMap != null) {
            String d = changedDensitiesMap.getString(String.valueOf(req));
            if (d != null) {
                density = Integer.valueOf(d);
            }
        }

        Long lastTime = null;
        Long lastIncrement = null;
        for (String val : values) {

            // Values and timestamps are separated with a '_'
            // character
            String[] v = val.split("_");

            // Get the value
            Float value = Float.parseFloat(v[0]);

            // Get the time/increment
            Long time = v.length < 2 ? lastIncrement : Long.parseLong(v[1]);

            // If last time is null then we have the first point
            // with a complete timestamp
            if (lastTime == null) {
                dvalues.add(new Date(time));
                lastTime = time;

                // Else the time is the difference between the
                // last points time and this points time.
            } else {
                dvalues.add(new Date(lastTime + time));
                lastTime += time;
                lastIncrement = time;
            }

            // Add the value to the value list
            fvalues.add(value);
        }

        // Get markers
        Set<String> markers = null;
        if (uidl.hasVariable(TimelineConstants.VARIABLE_MARKERS)) {
            markers = uidl
                    .getStringArrayVariableAsSet(TimelineConstants.VARIABLE_MARKERS);
        }

        // Get events
        Set<String> events = null;
        if (uidl.hasVariable(TimelineConstants.VARIABLE_EVENTS)) {
            events = uidl
                    .getStringArrayVariableAsSet(TimelineConstants.VARIABLE_EVENTS);
        }

        // Put data in cache
        int graph = requestGraphMap.get(req);
        if (dvalues.size() > 0 && clientCacheEnabled) {

            // Only add visible graphs to cache
            if (graphIsVisible(graph)) {
                VClientCache densityCache = multiLevelCache.get(density);

                // Create new cache if it does not exist
                if (densityCache == null) {
                    densityCache = new VClientCache(this);
                    multiLevelCache.put(density, densityCache);
                }

                // Add points to cache
                densityCache.addToCache(graph, dvalues.get(0),
                        dvalues.get(dvalues.size() - 1), fvalues, dvalues,
                        markers, events);
            }
        }

        // Notify component of the data
        comp.dataRecieved(req, fvalues, dvalues, markers, events, density);
    }

    /**
     * Set the not data state
     * 
     * @param enabled
     */
    private void setWidgetNoData(boolean enabled) {
        noDataAvailable = enabled;

        display.displayNoDataMessage(enabled);
        browser.displayNoDataMessage(enabled);

        display.setEnabled(!enabled);
        browser.setEnabled(!enabled);

        barModeBtn.setEnabled(!enabled);
        lineModeBtn.setEnabled(!enabled);
        scatterModeBtn.setEnabled(!enabled);

        dateFrom.setEnabled(!enabled);
        dateTo.setEnabled(!enabled);

        if (enabled) {
            legend.clear();
            legendColors.clear();
            legendCaptions.clear();
            legendValues.clear();
            captions.clear();
        }
    }

    /**
     * Get data points from server
     * 
     * @param component
     *            The component which need the points
     * @param graphIndex
     *            From which graph should the data points be fetched
     * @param startDate
     *            The start date of the data points
     * @param endDate
     *            The end date of the data points
     * @return Was the data fetched from the cache
     */
    public boolean getDateData(VDataListener component, int graphIndex,
            Date startDate, Date endDate, int density) {

        if (component.isVisible()) {
            isIdle = false;
            requestCounter++;
            component.setCurrentRequestId(Long.valueOf(requestCounter),
                    graphIndex);

            waitingForData.put(requestCounter, component);
            requestGraphMap.put(requestCounter, graphIndex);
            densityMap.put(requestCounter, density);

            client.updateVariable(
                    uidlId,
                    TimelineConstants.VARIABLE_DATA_PREFIX + requestCounter,
                    new String[] { String.valueOf(requestCounter),
                            String.valueOf(graphIndex),
                            String.valueOf(startDate.getTime()),
                            String.valueOf(endDate.getTime()),
                            String.valueOf(density) }, false);
        }

        return false;
    }

    /**
     * Tries to get the data points from the cache, if that fails then gets the
     * points from the server
     * 
     * @param component
     *            The component which need the points
     * @param startDate
     *            The start date
     * @param endDate
     *            The end date
     * @param density
     *            The density of the points
     * @return Was the data fetched from the cache
     * 
     */
    @SuppressWarnings("unchecked")
    public boolean getDateDataAll(VDataListener component, Date startDate,
            Date endDate, int density) {
        if (clientCacheEnabled) {
            boolean allRetrievedFromCache = true;

            List<Long> requests = new ArrayList<Long>();
            Map<Integer, List<Float>> values = new HashMap<Integer, List<Float>>();
            Map<Integer, List<Date>> dates = new HashMap<Integer, List<Date>>();
            Set<String> markers = new HashSet<String>();
            Set<String> events = new HashSet<String>();
            Map<Integer, Float> min = new HashMap<Integer, Float>();
            Map<Integer, Float> max = new HashMap<Integer, Float>();

            Float totalMinimum = Float.MAX_VALUE;
            Float totalMaximum = Float.MIN_VALUE;

            int numGraphs = getNumGraphs();
            for (int g = 0; g < numGraphs; g++) {

                // Abort if graph is not visible
                if (!graphIsVisible(g)) {
                    continue;
                }

                requestCounter++;

                component.setCurrentRequestId(Long.valueOf(requestCounter), g);
                requests.add(Long.valueOf(requestCounter));

                VClientCache densityCache = multiLevelCache.get(density);
                int cacheDensity = density;

                /*
                 * We didn't find the correct density cache, try to find a lower
                 * level to use
                 */
                if (densityCache == null) {
                    cacheDensity = (int) Math.floor(density / 2f);
                    while (cacheDensity >= 1) {
                        densityCache = multiLevelCache.get(cacheDensity);
                        if (densityCache != null) {
                            break;
                        }
                        cacheDensity = (int) Math.floor(cacheDensity / 2f);
                    }
                }

                Object[] obj = null;
                if (densityCache != null) {
                    obj = densityCache.getFromCache(g, startDate, endDate);
                }

                if (obj == null) {
                    allRetrievedFromCache = false;
                    break;
                } else {
                    List<Date> dvalues = (List<Date>) obj[1];
                    List<Float> fvalues = (List<Float>) obj[0];
                    Float minvalue = (Float) obj[4];
                    Float maxvalue = (Float) obj[5];

                    /*
                     * Ensures we got enough points from cache to cover the
                     * display area
                     */
                    Date realStartDate = dvalues.get(0);
                    Date realEndDate = dvalues.get(dvalues.size() - 1);
                    if (realStartDate.after(startDate)
                            || realEndDate.before(endDate)) {
                        allRetrievedFromCache = false;
                        break;
                    }

                    /*
                     * If we are using a higher density cache then we need to
                     * remove some points
                     */
                    if (cacheDensity < density) {
                        int cachediff = density - cacheDensity;

                        List<Float> newValues = new ArrayList<Float>();
                        List<Date> newDates = new ArrayList<Date>();
                        float newMin = fvalues.get(0);
                        float newMax = fvalues.get(0);

                        for (int i = 0; i < fvalues.size(); i += cachediff + 1) {
                            float value = fvalues.get(i);
                            newValues.add(value);
                            newMin = value < newMin ? value : newMin;
                            newMax = value > newMax ? value : newMax;

                            newDates.add(dvalues.get(i));
                        }

                        dvalues = newDates;
                        fvalues = newValues;
                        minvalue = newMin;
                        maxvalue = newMax;
                    }

                    values.put(g, fvalues);
                    dates.put(g, dvalues);
                    min.put(g, minvalue);
                    max.put(g, maxvalue);

                    if (minvalue < totalMinimum) {
                        totalMinimum = minvalue;
                    }

                    if (maxvalue > totalMaximum) {
                        totalMaximum = maxvalue;
                    }

                    if (obj[2] != null) {
                        markers.addAll((Set<String>) obj[2]);
                    }

                    if (obj[3] != null) {
                        events.addAll((Set<String>) obj[3]);
                    }
                }
            }

            // Everything was fetched from the cache
            if (allRetrievedFromCache && getNumGraphs() > 0) {
                component.dataRecievedAll(requests, values, dates, markers,
                        events, min, max, totalMinimum, totalMaximum, density);
                return true;
            }
        }

        // Some data not found in cache, get it from the server
        for (int g = 0; g < getNumGraphs(); g++) {
            getDateData(component, g, startDate, endDate, density);
        }

        // Issue request
        getFromServer();

        return false;
    }

    /**
     * @param graphIndex
     * 
     * @return Returns how many points a graph has
     */
    public int getContainerSize(int graphIndex) {
        if (graphIndex >= containerSizes.size()) {
            return 0;
        }
        return containerSizes.get(graphIndex);
    }

    /**
     * Set the displayed date range
     * 
     * @param start
     *            The start date
     * @param end
     *            The end date
     */
    public void setDisplayRange(Date start, Date end) {
        display.setRange(start, end);
    }

    /**
     * Set the selected date range
     * 
     * @param start
     *            The start date
     * @param end
     *            The end date
     */
    public void setBrowserRange(Date start, Date end) {
        if (browser.isVisible()) {
            browser.setRange(start, end);
        }
    }

    /**
     * Calculates the maximum value of a list of values
     * 
     * @param values
     *            The list of values
     * @return The maximum value
     */
    public static float getMaxValue(List<Float> values) {
        if (values == null || values.size() == 0) {
            return 0;
        }
        float max = values.get(0);
        for (Float value : values) {
            max = max < value.floatValue() ? value.floatValue() : max;
        }
        return max;
    }

    /**
     * Calculates the minimum value of a list of values
     * 
     * @param values
     *            The list of values
     * @return The minimum value
     */
    public static float getMinValue(List<Float> values) {

        if (values == null || values.size() == 0) {
            return 0;
        }

        float min = values.get(0);
        for (Float value : values) {
            min = min > value.floatValue() ? value.floatValue() : min;
        }

        return min;
    }

    /**
     * Normalizes a list of values using the minimum value as reference
     * 
     * @param values
     *            A list of values
     * @param minimum
     *            The reference value
     * @return A normilized list
     */
    public static List<Float> normalizeValues(List<Float> values, float minimum) {

        if (minimum == 0f) {
            return values;
        }

        List<Float> normValues = new ArrayList<Float>();

        if (values != null) {
            for (Float val : values) {
                normValues.add(new Float(val - minimum));
            }
        }

        return normValues;
    }

    /**
     * Get the ISO week date week number
     */
    public static int getWeek(Date date) {
        Date onejan = new Date(date.getYear(), 0, 1);
        Long t1 = onejan.getTime();
        Long t2 = date.getTime();
        Long diff = t2 - t1;
        Float d = diff.floatValue() / 86400000f;
        Float d2 = (onejan.getDay() + 1f);

        return (int) Math.floor((d + d2) / 7f);
    }

    /**
     * Returns the number of days of a given month
     * 
     * @param month
     *            The month 0-11
     * @param year
     *            The year
     * @return Days in the month
     */
    public static int getDaysInMonth(int month, int year) {
        return 32 - new Date(year, month, 32).getDate();
    }

    /**
     * Returns the start date
     * 
     * @return The start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date
     * 
     * @return The end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Returns the amount of graphs
     * 
     * @return The amount of grafs
     */
    public int getNumGraphs() {
        return numGraphs;
    }

    /**
     * Issues a get request to fetch the requested data points
     */
    public void getFromServer() {
        client.updateVariable(uidlId, TimelineConstants.VARIABLE_SEND, true,
                true);
    }

    /**
     * Returns the colormap where the graph colors are stored
     * 
     * @return A map of colors
     */
    Map<Integer, String> getColorMap() {
        return colors;
    }

    /**
     * Returns the thicknesses for the graphs
     * 
     * @return
     */
    Map<Integer, Double> getThicknessMap() {
        return thicknesses;
    }

    /**
     * Returns the caps for the graphs
     * 
     * @return
     */
    Map<Integer, Boolean> getCapsMap() {
        return caps;
    }

    /**
     * Get the colormap used in the browser
     * 
     * @return
     */
    public Map<Integer, String> getBrowserColorMap() {
        return browserColors;
    }

    /**
     * Returns the colormap where the graph fill colors are stored
     * 
     * @return A map of colors
     */
    public Map<Integer, String> getFillColorMap() {
        return fillcolors;
    }

    public Map<Integer, String> getBrowserFillColorMap() {
        return browserFillColors;
    }

    /**
     * Set the starting date text field
     * 
     * @param date
     *            The date
     */
    public void setFromDateTextField(Date date) {
        intervalStartDate = date;
        if (dateSelectVisible) {
            if (date != null) {
                dateFrom.setText(dts.formatDate(date, dateFormatInfo
                        .getDisplayFormat().getPattern()));
            } else {
                dateFrom.setText("");
            }
        }
    }

    /**
     * Set the ending date text field
     * 
     * @param date
     *            The date
     */
    public void setToDateTextField(Date date) {
        intervalEndDate = date;
        if (dateSelectVisible) {
            if (date != null) {
                dateTo.setText(dts.formatDate(date, dateFormatInfo
                        .getDisplayFormat().getPattern()));
            } else {
                dateTo.setText("");
            }
        }
    }

    /**
     * The top bar height
     * 
     * @return Height in pixels
     */
    public int getTopBarHeight() {
        if (topBar.isVisible()) {
            return topBar.getOffsetHeight();
        } else {
            return 0;
        }
    }

    /**
     * The caption height
     * 
     * @return Height in pixels
     */
    public int getCaptionHeight() {
        if (caption.isVisible()) {
            return caption.getOffsetHeight();
        } else {
            return 0;
        }
    }

    /**
     * The legend height
     * 
     * @return height in pixels
     */
    public int getLegendHeight() {
        if (modeLegendBar.isVisible()) {
            return modeLegendBar.getOffsetHeight();
        } else {
            return 0;
        }
    }

    /**
     * A zoom level was clicked
     * 
     * @param evt
     *            The click event
     */
    private void zoomLevelClicked(ClickEvent evt) {
        evt.preventDefault();

        // If we have no data do nothing
        if (noDataAvailable) {
            return;
        }

        // Was a zoom level clicked
        Long time = zoomLevels.get(evt.getSource());
        Long totalTime = getEndDate().getTime() - getStartDate().getTime();

        if (totalTime >= time) {

            // Calculate the center
            Date center;
            if (browserIsVisible) {
                Long selectedTime = browser.getSelectedEndDate().getTime()
                        - browser.getSelectedStartDate().getTime();
                center = new Date(browser.getSelectedStartDate().getTime()
                        + selectedTime / 2L);
            } else {
                center = new Date(display.getSelectionStartDate().getTime()
                        + time / 2L);
            }

            // Calculate start date
            Date start = new Date(center.getTime() - time / 2L);
            if (start.before(getStartDate())) {
                start = getStartDate();
            }

            // Calculate end date
            Date end = new Date(start.getTime() + time);
            if (end.after(getEndDate())) {
                end = getEndDate();
            }

            // Set the browser
            if (browserIsVisible) {
                setBrowserRange(start, end);
            }

            // Set the display
            setDisplayRange(start, end);

        } else {
            if (browserIsVisible) {
                setBrowserRange(getStartDate(), getEndDate());
            }

            setDisplayRange(getStartDate(), getEndDate());
        }
    }

    /**
     * Sets the legend and graph visibilites
     * 
     * @param graph
     *            The graph index
     * @param visibility
     *            The visibility
     */
    private void setLegendCaptionVisibility(int graph, boolean visibility) {

        // Store the visiblities
        graphVisibilites.put(graph, visibility);

        // Remove cached points for hidden graphs
        if (!visibility) {
            display.removeGraph(graph);
        }

        // Remove legend entries
        if (legendCaptions.size() > graph) {
            Label caption = legendCaptions.get(graph);
            HTML color = legendColors.get(graph);
            Label value = legendValues.get(graph);

            caption.setVisible(visibility);
            color.setVisible(visibility);
            value.setVisible(visibility);
        }
    }

    /**
     * Timer to prevent flogging the server with date range changed events
     */
    private Timer dateRangeChangeEventTimer = new Timer() {
        @Override
        public void run() {
            String[] values = new String[] {
                    String.valueOf(selectedStartDate.getTime()),
                    String.valueOf(selectedEndDate.getTime()) };
            client.updateVariable(uidlId,
                    TimelineConstants.VARIABLE_DATE_RANGE_CHANGED_EVENT,
                    values, true);
        }
    };

    /**
     * Fire a date range change event
     */
    public void fireDateRangeChangedEvent() {
        if (dateRangeChangeEventsEnabled
                && display.getSelectionStartDate() != null
                && display.getSelectionEndDate() != null) {
            selectedStartDate = display.getSelectionStartDate();
            selectedEndDate = display.getSelectionEndDate();
            dateRangeChangeEventTimer.cancel();
            dateRangeChangeEventTimer.schedule(100);
        }
    }

    /**
     * Fire a event button click event
     * 
     * @param indexes
     */
    public void fireEventButtonClickEvent(List<Integer> indexes) {
        if (eventClickListenersEnabled) {
            String[] ixs = new String[indexes.size()];
            for (int i = 0; i < indexes.size(); i++) {
                ixs[i] = String.valueOf(indexes.get(i));
            }
            client.updateVariable(uidlId,
                    TimelineConstants.VARIABLE_EVENT_BUTTON_CLICK, ixs, true);
        }
    }

    /**
     * Returns the height of the widget in pixels
     * 
     * @return A pixel height
     */
    public int getWidgetHeight() {
        try {
            int height = Integer.parseInt(DOM.getAttribute(getElement(),
                    "height").replaceAll("px", ""));
            return height;
        } catch (Exception e) {
            try {
                int height = Integer.parseInt(DOM.getStyleAttribute(
                        getElement(), "height").replaceAll("px", ""));
                return height;
            } catch (Exception f) {
                return getOffsetHeight();
            }
        }
    }

    public int getWidgetWidth() {
        try {
            int width = Integer.parseInt(DOM
                    .getAttribute(getElement(), "width").replaceAll("px", ""));
            return width;
        } catch (Exception e) {
            try {
                int width = Integer.parseInt(DOM.getStyleAttribute(
                        getElement(), "width").replaceAll("px", ""));
                return width;
            } catch (Exception f) {
                return getOffsetWidth();
            }
        }
    }

    /**
     * Get the browser height
     * 
     * @return The height in pixels
     */
    public int getBrowserHeight() {
        if (browserIsVisible) {
            return browser.getOffsetHeight();
        } else {
            return 0;
        }
    }

    /**
     * Returns true if a graph is visible
     * 
     * @param graph
     *            The graph index
     * @return True if graph is visible, false if not
     */
    public boolean graphIsVisible(int graph) {
        if (graphVisibilites.get(graph) == null) {
            return true;
        }

        return graphVisibilites.get(graph);
    }

    /**
     * The idle state of the widget
     * 
     * @return If the widget is idle true is returned else false
     */
    public boolean isIdle() {
        return isIdle;
    }

    /**
     * Sets the legend value
     * 
     * @param graph
     *            The graph the value is from
     * @param value
     *            The value
     */
    public void setLegendValue(int graph, float value) {
        if (legendValues.size() > graph) {
            Label lbl = legendValues.get(graph);
            if (lbl != null) {
                NumberFormat format = display.getVerticalAxisNumberFormat();
                lbl.setText(format.format(value) + " " + units.get(graph));
            }
        }
    }

    /**
     * Get the unit of the vertical scale
     * 
     * @param graph
     *            The graph
     * @return
     */
    public String getVerticalUnit(int graph) {
        return units.get(graph);
    }

    /**
     * Has the widget been properly initialized
     * 
     * @return
     */
    public boolean isInitDone() {
        return initDone;
    }

    /**
     * Are the graphs stacked
     * 
     * @return
     */
    public boolean isGraphsStacked() {
        return graphStacking;
    }

    /**
     * Are the bars in the bar chart plotted uniformly
     * 
     * @return
     */
    public boolean isBarChartUniform() {
        return uniformBarThickness;
    }

    /**
     * Get the selections start date
     * 
     * @return
     */
    public Date getSelectedStartDate() {
        return selectedStartDate;
    }

    /**
     * Get the selects end date
     */
    public Date getSelectedEndDate() {
        return selectedEndDate;
    }

    /**
     * Sets the date formats used by the timeline. Recieved from the server.
     * 
     * @param uidl
     *            The UIDL which contains the date format info
     */
    private void setDateFormatInfo(UIDL uidl) {
        if (uidl.hasAttribute(TimelineConstants.ATTRIBUTE_DATE_FORMATS)) {

            dateFormatInfo.setDateFormatInfo(uidl);

            // Update textfields with the new format
            setToDateTextField(intervalEndDate);
            setFromDateTextField(intervalStartDate);

            // Update scales by redrawing
            if (isInitDone()) {
                display.redraw();
            }
        }
    }

    /**
     * 
     * Returns the date formats used by the timeline Get date formats
     */
    public VDateFormatInfo getDateFormats() {
        return dateFormatInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.KeyPressHandler#onKeyPress(com.google
     * .gwt.event.dom.client.KeyPressEvent)
     */
    @Override
    public void onKeyPress(KeyPressEvent event) {
        if (!inTextBoxes(event)) {
            event.preventDefault();
            handleKeyboardEvent(event);
        }
    }

    private boolean inTextBoxes(KeyEvent<?> event) {
        Element eventTarget = event.getNativeEvent().getEventTarget().cast();
        if (eventTarget == dateFrom.getElement()
                || eventTarget == dateTo.getElement()) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.KeyUpHandler#onKeyUp(com.google.gwt.event
     * .dom.client.KeyUpEvent)
     */
    @Override
    public void onKeyUp(KeyUpEvent event) {
        if (!inTextBoxes(event)) {
            event.preventDefault();
            handleKeyboardEvent(event);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.event.dom.client.KeyDownHandler#onKeyDown(com.google.gwt
     * .event.dom.client.KeyDownEvent)
     */
    @Override
    public void onKeyDown(KeyDownEvent event) {
        if (!inTextBoxes(event)) {
            event.preventDefault();
            handleKeyboardEvent(event);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.terminal.gwt.client.Focusable#focus()
     */
    @Override
    public void focus() {
        setFocus(true);
    }

    /**
     * Handles keyboard events recieved from the user
     * 
     * @param event
     *            The event recieved
     */
    protected void handleKeyboardEvent(KeyEvent<? extends EventHandler> event) {
        int keyCode = event.getNativeEvent().getKeyCode();
        if (browser.isVisible()) {
            /*
             * Using browser to scroll with
             */
            if (event.getAssociatedType() == KeyUpEvent.getType()) {
                browser.refreshSelection();
            } else if (keyCode == KeyCodes.KEY_LEFT) {
                browser.scrollLeft(event.isShiftKeyDown());
            } else if (keyCode == KeyCodes.KEY_RIGHT) {
                browser.scrollRight(event.isShiftKeyDown());
            } else if (keyCode == KeyCodes.KEY_UP) {
                browser.zoomIn(5);
            } else if (keyCode == KeyCodes.KEY_DOWN) {
                browser.zoomOut(5);
            }
        } else {
            /*
             * Using display to scroll if browser is not available
             */
            if (keyCode == KeyCodes.KEY_LEFT) {
                display.scrollLeft(event.isShiftKeyDown());
            } else if (keyCode == KeyCodes.KEY_RIGHT) {
                display.scrollRight(event.isShiftKeyDown());
            } else if (keyCode == KeyCodes.KEY_UP) {
                // TODO
                VConsole.error("Zooming when no browser bar present not implemented");
            } else if (keyCode == KeyCodes.KEY_DOWN) {
                // TODO
                VConsole.error("Zooming when no browser bar present not implemented");
            }
        }
    }

    /**
     * Are graph shadows enabled
     */
    public boolean isGraphShadows() {
        return graphShadows;
    }

    /**
     * Returns the amount of offscreen points which should be rendered
     * 
     * @return
     */
    int getOffscreenRenderingPoints() {
        return offscreenPoints;
    }
}
