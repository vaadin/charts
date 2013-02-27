package com.vaadin.addon.timeline;

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

import java.awt.Color;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.addon.timeline.gwt.client.TimelineConstants;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Container.ItemSetChangeNotifier;
import com.vaadin.data.Container.PropertySetChangeEvent;
import com.vaadin.data.Container.PropertySetChangeListener;
import com.vaadin.data.Container.PropertySetChangeNotifier;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Property.ValueChangeNotifier;
import com.vaadin.server.PaintException;
import com.vaadin.server.PaintTarget;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.LegacyComponent;

/**
 * Vaadin Timeline implementation.
 * 
 */
@SuppressWarnings({ "unchecked", "serial", "deprecation" })
public class Timeline extends AbstractComponent implements LegacyComponent {

    // Graph containers
    private final List<TimelineDatasourceProperties> datasources = new ArrayList<TimelineDatasourceProperties>();

    // Marker container
    protected Container.Indexed markers;

    // Event container
    protected Container.Indexed events;

    /**
     * The width of the canvas.<br/>
     * This is received from the client side when the graph has loaded and used
     * in measuring the density of the points to send to the client side.
     */
    private int canvasWidth = 0;

    // Initialization is done
    private boolean initDone = false;

    // Should the graph container sizes be sent
    private boolean sendSizes = false;

    // Should the graph colors be sent
    private boolean sendGraphColors = false;

    // Should the graph fill colors be sent
    private boolean sendGraphFillColors = false;

    private boolean sendGraphThicknesses = false;

    // Should the time limits be sent
    private boolean sendTimeLimits = false;

    // Should the graph captions be sent
    private boolean sendCaptions = false;

    // Should the captions for teh chartmode, zoom etc. be sent
    private boolean sendUICaptions = false;

    // Should the graph data be sent
    private boolean sendDataPoints = false;

    // Should a refresh command be sent
    private boolean sendRefreshRequest = false;

    // Should the date range be sent. (Maximum and Minimum dates be sent)
    private boolean sendDateRange = false;

    // Should the vertical scale limits be sent (Maximum and minimum)
    private boolean sendVerticalScaleLimits = false;

    private boolean sendVerticalGridLines = false;

    // Activate change events
    private boolean sendChangeEventAvailable = false;
    private boolean sendEventClickAvailable = false;

    // Should the graph visibilities be sent
    private boolean sendGraphVisibilities = false;

    // Should the browser colors be sent
    private boolean sendBrowserColors = false;

    // Should the browser fill colors be sent
    private boolean sendBrowserFillColors = false;

    // Should the vertical unit be sent
    private boolean sendVUnit = false;

    // Should the client cache be sent
    private boolean sendClientCache = false;

    // Should the line caps be sent
    private boolean sendLineCaps = false;

    // Should the component visibilities be sent
    private boolean sendComponentVisibilities = true;

    // Should the zoom levels be sent
    private boolean sendZoomLevels = false;

    // Should the selected chart mode be sent
    private boolean sendChartMode = false;

    // Should the graph grid color be sent
    private boolean sendGraphGrid = false;

    // Should the date format info be sent
    private boolean sendDateFormatInfo = false;

    // Should the vertical axis number format be sent in the next paint
    private boolean sendVerticalAxisNumberFormat = false;

    // Is client cache enabled
    protected boolean clientCache = true;

    // Is vertical scale fitting enabled
    protected boolean verticalScaleFitting = true;

    // Has the data source been tagged dirty by container updates
    protected boolean dirty = false;

    // Is the browser bar locked to the selection
    protected boolean selectionBarLocked = true;

    // Is bar chart uniform bar thickness enabled
    protected boolean uniformBarThickness = false;

    /*
     * The minimum vertical scale limit Used when vertical scale fitting is
     * turned off
     */
    protected Float verticalScaleMinimum = null;

    /*
     * The maximum vertical scale limit Used when vertical scale fitting is
     * turned off
     */
    protected Float verticalScaleMaximum = null;

    /**
     * The point data to send in the next refresh.<br/>
     * key = Unique request id <br/>
     * value = List of data strings
     */
    private final Map<Long, List<String>> dataToSend = new HashMap<Long, List<String>>();

    /**
     * The changed densities to send in the next refresh.<br/>
     * key = Unique requires id <br/>
     * value = the density (Integer)
     */
    private final Map<Long, String> changedDensities = new HashMap<Long, String>();

    // The markers to send in the next refresh
    private final Set<String> markersToSend = new HashSet<String>();

    // The events to send in the next refresh
    private final Set<String> eventsToSend = new HashSet<String>();

    /**
     * The zoom levels to send in the next refresh.<br/>
     * key = Caption of the zoom level<br/>
     * value = The time in milliseconds of the zoom level<br/>
     */
    private final Map<String, Long> zoomLevels = new LinkedHashMap<String, Long>();

    // The minimum date of all the graphs
    private Date minDate = null;

    // The maximum data of all the graphs
    private Date maxDate = null;

    // Selection minimum date
    private Date selectedStartDate = null;

    // Selection maximum date
    private Date selectedEndDate = null;

    /**
     * The currently displayed points <br/>
     * key = Graph index<br/>
     * value = List of items<br/>
     */
    private final Map<Integer, List<Item>> currentDisplayedPoints = new HashMap<Integer, List<Item>>();

    private float[] verticalGridLines;

    private boolean graphShadowsEnabled = true;

    /**
     * Listens to the datasource for changes
     */
    private final DataSourceListener dataSourceListener = new DataSourceListener();

    private final DirtyDataSourceListener markerEventListener = new DirtyDataSourceListener();

    // Event properties
    protected Object eventTimestampPropertyId = PropertyId.TIMESTAMP;
    protected Object eventCaptionPropertyId = PropertyId.CAPTION;

    // Marker properties
    protected Object markerTimestampPropertyId = PropertyId.TIMESTAMP;
    protected Object markerCaptionPropertyId = PropertyId.CAPTION;
    protected Object markerValuePropertyId = PropertyId.VALUE;

    // Is the browser visible
    protected boolean browserIsVisible = true;

    // Is the zoom levels visible
    protected boolean zoomIsVisible = true;

    // The caption of the zoom levels
    protected String zoomLevelCaption = "Zoom";

    // The no data source caption
    protected String noDataSourceCaption = "No data source.";

    // Is the date select visible
    protected boolean dateSelectVisible = true;

    // Is the date select enabled
    protected boolean dateSelectEnabled = true;

    // Are the chart modes visible
    protected boolean chartModesVisible = true;

    // Is the line chart mode visible
    protected boolean lineChartModeVisible = true;

    // Is the bar chart mode visible
    protected boolean barChartModeVisible = true;

    // Is the scatter chart mode visible
    protected boolean scatterChartModeVisible = true;

    // Is the legend visible
    protected boolean legendVisible = true;

    // The current chart mode
    protected ChartMode currentChartMode = ChartMode.LINE;

    protected String chartModeCaption = "Chart mode";

    // The graph grid color
    protected Color graphGridColor = Color.LIGHT_GRAY;

    // Stacking
    protected boolean graphStacking = false;

    // Date formats
    protected final DateFormatInfo dateFormatInfo = new DateFormatInfo();

    // Vertical axis number format
    protected String verticalAxisNumberFormat = "##.##";

    // Points which should be rendered offscreen to enhance browsing
    protected int offscreenPoints = 100;

    protected DuplicateHandler duplicateHandler;

    /**
     * Property id for the graph point container
     */
    public enum PropertyId {
        VALUE, TIMESTAMP, CAPTION
    };

    /**
     * Chart modes
     */
    public enum ChartMode {
        BAR, LINE, SCATTER
    };

    /**
     * Date range changed event.<br/>
     * The date range changed event represents a change in the time span.
     */
    public class DateRangeChangedEvent extends Component.Event {

        private Date startDate;

        private Date endDate;

        /**
         * Default constructor.<br/>
         * See {@link Component.Event} for more details.
         * 
         * @param source
         *            The source of the event
         */
        public DateRangeChangedEvent(Component source) {
            super(source);
        }

        /**
         * See {@link Component.Event} for more details.
         * 
         * @param source
         *            The source of the event
         * @param start
         *            The start date of the range
         * @param end
         *            The end date of the range
         */
        public DateRangeChangedEvent(Component source, Date start, Date end) {
            super(source);
            startDate = start;
            endDate = end;
        }

        /**
         * Returns the start date of the range
         * 
         * @return The start date
         */
        public Date getStartDate() {
            return startDate;
        }

        /**
         * Returns the end date of the range
         * 
         * @return The end date
         */
        public Date getEndDate() {
            return endDate;
        }
    }

    /**
     * Event button click event. This event is sent when a user clicks an event
     * button in the graph.
     * 
     */
    public class EventButtonClickEvent extends Component.Event {

        private List<Object> ids;

        /**
         * See {@link Component.Event} for details.
         * 
         * @param source
         *            The source of the event
         */
        public EventButtonClickEvent(Component source) {
            super(source);
        }

        /**
         * See {@link Component.Event} for more details.
         * 
         * @param source
         *            The source of the event
         * @param itemIds
         *            The item id:s in the event data source which are related
         *            to the event
         */
        public EventButtonClickEvent(Component source, List<Object> itemIds) {
            super(source);
            ids = itemIds;
        }

        /**
         * Gets the item id:s in the event data source which are related to the
         * event
         * 
         * @return The item id:s related to the event
         */
        public List<Object> getItemIds() {
            return Collections.unmodifiableList(ids);
        }
    }

    /**
     * Describes the date formats used by the Timeline.
     */
    public class DateFormatInfo implements Serializable {

        private String dateSelectDisplaySimpleDateFormat = "MMM d, y";
        private String dateSelectEditSimpleDateFormat = "dd/MM/yyyy";
        private String shortYearFormat = "''yy";
        private String longYearFormat = "yyyy";
        private String shortMonthFormat = "MMM yyyy";
        private String longMonthFormat = "MMMM yyyy";
        private String shortDayFormat = "MMM d, yyyy";
        private String longDayFormat = "MMMM d, yyyy";
        private String shortTimeFormat = "HH:mm";
        private String longTimeFormat = "HH:mm:ss";

        private static final char DELIM = '|';

        /**
         * Get the date format which is used to display the selected date range
         * in the top right corner.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getDateSelectDisplaySimpleDateFormat() {
            return dateSelectDisplaySimpleDateFormat;
        }

        /**
         * Set the date format used to display the selected date range in the
         * top right corner.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setDateSelectDisplaySimpleDateFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }

            this.dateSelectDisplaySimpleDateFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is used to edit the selected date range in
         * the top right corner
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getDateSelectEditSimpleDateFormat() {
            return dateSelectEditSimpleDateFormat;
        }

        /**
         * Set the date format used to display the selected date range in the
         * top right corner.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setDateSelectEditSimpleDateFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }

            this.dateSelectEditSimpleDateFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a year-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getShortYearFormat() {
            return shortYearFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale has a year-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setShortYearFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }

            this.shortYearFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }

        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a year-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getLongYearFormat() {
            return longYearFormat;
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a year-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setLongYearFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }

            this.longYearFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a month-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getShortMonthFormat() {
            return shortMonthFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale has a month-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setShortMonthFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.shortMonthFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a month-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getLongMonthFormat() {
            return longMonthFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale has a month-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setLongMonthFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.longMonthFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a day-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getShortDayFormat() {
            return shortDayFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale has a day-resolution and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setShortDayFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.shortDayFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale has a day-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getLongDayFormat() {
            return longDayFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale has a day-resolution.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setLongDayFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.longDayFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale is displaying time and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getShortTimeFormat() {
            return shortTimeFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale is displaying time and only a little amount of space is
         * available.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setShortTimeFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.shortTimeFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Get the date format which is displayed in the horizontal scale when
         * the scale is displaying time.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @return A format describing how the date should be formatted
         */
        public String getLongTimeFormat() {
            return longTimeFormat;
        }

        /**
         * Set the date format which is displayed in the horizontal scale when
         * the scale is displaying time.
         * 
         * See {@link SimpleDateFormat} for format details.
         * 
         * @param format
         *            A format describing how the date should be formatted
         */
        public void setLongTimeFormat(String format) {
            if (format == null) {
                throw new IllegalArgumentException("Date format cannot be null");
            }
            this.longTimeFormat = format;
            sendDateFormatInfo = true;
            if (initDone) {
                requestRepaint();
            }
        }

        /**
         * Serializes the date formats into a string which can be sent to the
         * client.
         * 
         * @return
         */
        public String serialize() {
            return dateSelectDisplaySimpleDateFormat + DELIM
                    + dateSelectEditSimpleDateFormat + DELIM + shortYearFormat
                    + DELIM + longYearFormat + DELIM + shortMonthFormat + DELIM
                    + longMonthFormat + DELIM + shortDayFormat + DELIM
                    + longDayFormat + DELIM + shortTimeFormat + DELIM
                    + longTimeFormat;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return serialize();
        }
    }

    /**
     * The date range listener interface
     */
    public interface DateRangeListener {
        public void dateRangeChanged(DateRangeChangedEvent event);
    }

    /**
     * The event click listener interface
     */
    public interface EventClickListener {
        public void eventClick(EventButtonClickEvent event);
    }

    /**
     * A duplicate handler is called for dates with multiple points for the same
     * date
     * 
     */
    public interface DuplicateHandler {

        /**
         * Called by the timeline when duplicates are observed in the Timeline
         * to resolve the values
         * 
         * @param date
         *            The date-time where there is multiple points
         * @param values
         *            The duplicate values
         * @return Return the number that should be used for that date
         */
        Number resolveDuplicates(Date date, List<Number> values);
    }

    /**
     * The datasource listener listens to changes in the attached graph
     * containers and updates the container accordingly
     */
    private class DataSourceListener extends DirtyDataSourceListener implements
            ItemSetChangeListener, PropertySetChangeListener,
            ValueChangeListener {

        @Override
        public void valueChange(ValueChangeEvent event) {
            super.valueChange(event);
            updateFlags();

            /*
             * Minimum and maximum dates might have changed, recalculate
             */
            minDate = null;
            maxDate = null;
            for (TimelineDatasourceProperties ds : datasources) {
                if (ds.getDatasource().size() > 0) {
                    Indexed cont = ds.getDatasource();
                    Object timeProp = getGraphTimestampPropertyId(cont);
                    Date d1 = (Date) cont.getItem(cont.getIdByIndex(0))
                            .getItemProperty(timeProp).getValue();
                    Date d2 = (Date) cont
                            .getItem(cont.getIdByIndex(cont.size() - 1))
                            .getItemProperty(timeProp).getValue();
                    if (minDate == null || minDate.after(d1)) {
                        minDate = d1;
                    }
                    if (maxDate == null || maxDate.before(d2)) {
                        maxDate = d2;
                    }
                }
            }

            requestRepaint();
        }

        @Override
        public void containerPropertySetChange(PropertySetChangeEvent event) {
            super.containerPropertySetChange(event);
            updateFlags();
            requestRepaint();
        }

        @Override
        public void containerItemSetChange(ItemSetChangeEvent event) {
            super.containerItemSetChange(event);
            updateFlags();
            requestRepaint();
        }

        private void updateFlags() {
            sendTimeLimits = true;
            sendRefreshRequest = true;
            sendSizes = true;
            sendVerticalScaleLimits = true;
            sendDataPoints = true;
        }
    }

    /**
     * The datasource listener is attached to event and marker containers to be
     * able to listen to changes in the container
     */
    private class DirtyDataSourceListener implements ItemSetChangeListener,
            PropertySetChangeListener, ValueChangeListener {

        @Override
        public void valueChange(ValueChangeEvent event) {
            dirty = true;
            requestRepaint();
        }

        @Override
        public void containerPropertySetChange(PropertySetChangeEvent event) {
            dirty = true;
            requestRepaint();
        }

        @Override
        public void containerItemSetChange(ItemSetChangeEvent event) {
            dirty = true;
            requestRepaint();
        }
    }

    /*
     * Event methods
     */
    private static final Method DATERANGE_CHANGED_METHOD;
    private static final Method EVENT_CLICK_METHOD;

    static {
        try {
            DATERANGE_CHANGED_METHOD = DateRangeListener.class
                    .getDeclaredMethod("dateRangeChanged",
                            new Class[] { DateRangeChangedEvent.class });

            EVENT_CLICK_METHOD = EventClickListener.class.getDeclaredMethod(
                    "eventClick", new Class[] { EventButtonClickEvent.class });

        } catch (final java.lang.NoSuchMethodException e) {
            // This should never happen but if it does log the error and throw
            // an runtime exception
            getLogger().log(Level.SEVERE, "Could not find listener method", e);
            throw new java.lang.RuntimeException(
                    "Internal error finding methods in Timeline");
        }
    }

    /**
     * Get the logger for the Timeline
     * 
     * @return
     */
    private static final Logger getLogger() {
        return Logger.getLogger(Timeline.class.getName());
    }

    /**
     * Default constructor
     */
    public Timeline() {

        // Default size
        setWidth("400px");
        setHeight("300px");
    }

    /**
     * Constructor
     * 
     * @param caption
     *            The caption of the graph
     */
    public Timeline(String caption) {
        this();
        setCaption(caption);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.vaadin.ui.AbstractComponent#changeVariables(java.lang.Object,
     * java.util.Map)
     */
    @Override
    public void changeVariables(Object source, @SuppressWarnings("rawtypes")
    Map variables) {

        if (dirty) {
            /*
             * If the graphs are marked dirty then ignore client request since
             * it will return the wrong data anyhow #7876
             */
            return;
        }

        // The width of the canvas area. Used in measuring density.
        if (variables.containsKey(TimelineConstants.VARIABLE_CANVAS_WIDTH)) {
            canvasWidth = Integer.parseInt(variables.get(
                    TimelineConstants.VARIABLE_CANVAS_WIDTH).toString());
            getLogger().finest("Canvas width is " + canvasWidth);
        }

        // The currently selected chart mode
        if (variables.containsKey(TimelineConstants.VARIABLE_CHART_MODE)) {
            currentChartMode = ChartMode.valueOf(variables.get(
                    TimelineConstants.VARIABLE_CHART_MODE).toString());
            getLogger().finest("Current chart mode is " + currentChartMode);
        }

        // Initialization data requested from the client side (refresh occurred)
        if (variables.containsKey(TimelineConstants.VARIABLE_INIT)) {
            initDataFlags();
            getLogger().fine("Initializing the timeline...");
            requestRepaint();
        }

        // Iterate through the variables and check for data to send
        for (Object entryObj : variables.entrySet()) {
            Entry<String, Object[]> entry = (Entry<String, Object[]>) entryObj;

            // The client need some data to display
            if (entry.getKey().startsWith(
                    TimelineConstants.VARIABLE_DATA_PREFIX)) {
                Object[] indexes = entry.getValue();
                Long requestID = Long.parseLong(indexes[0].toString());
                Integer graph = Integer.parseInt(indexes[1].toString());
                Date start = new Date(Long.parseLong(indexes[2].toString()));
                Date end = new Date(Long.parseLong(indexes[3].toString()));
                Integer density = Integer.parseInt(indexes[4].toString());

                // Too big index
                if (graph >= datasources.size()) {
                    List<String> values = new ArrayList<String>();
                    dataToSend.put(requestID, values);
                    getLogger().finest(
                            "Skipping graph with index " + graph
                                    + ". Index too big.");
                    continue;
                }

                // Get the graph container
                TimelineDatasourceProperties ds = datasources.get(graph);
                Container.Indexed cont = ds.getDatasource();

                // Check visibility or empty container
                if (!ds.isVisible()) {
                    List<String> values = new ArrayList<String>();
                    dataToSend.put(requestID, values);
                    getLogger()
                            .finest("Graph with index "
                                    + graph
                                    + " is not visible. Sending empty point array.");
                    continue;
                }

                List<String> values = new ArrayList<String>();
                List<Item> items = new ArrayList<Item>();
                if (cont.size() > 0) {
                    int startIndex = getContainerIndexForDate(start, cont,
                            false);
                    int endIndex = getContainerIndexForDate(end, cont, true);

                    // Adjust density if points will be more than the canvas
                    // width
                    int amountOfPoints = endIndex - startIndex;
                    if (amountOfPoints > canvasWidth) {
                        int newDensity = (int) Math
                                .ceil((double) amountOfPoints
                                        / (double) canvasWidth * 2);

                        if (getChartMode() == ChartMode.BAR) {
                            // Minimum size of bars is 3px so we can decrese
                            // density
                            // even further
                            newDensity = (int) Math
                                    .ceil((double) amountOfPoints
                                            / (double) canvasWidth * 4);
                        }

                        if (newDensity > density) {
                            density = newDensity;
                            changedDensities.put(requestID, density.toString());
                        }
                    }

                    // Get the points, stored in values and items lists
                    serializeDataPointsTostrings(start, end, startIndex,
                            endIndex, density, cont, values, items);
                }

                // Add data to send queue
                dataToSend.put(requestID, values);
                currentDisplayedPoints.put(graph, items);

                // Check if any markers exist
                List<String> marks = serializeMarkersToStrings(start, end);
                if (marks.size() > 0) {
                    markersToSend.addAll(marks);
                }

                // Check if any clickable events exists
                List<String> evnts = serializeEventsToStrings(start, end);
                if (evnts.size() > 0) {
                    eventsToSend.addAll(evnts);
                }
            }
        }

        // Send the data to the client
        if (variables.containsKey(TimelineConstants.VARIABLE_SEND)) {
            sendDataPoints = true;
            requestRepaint();
        }

        // Date range changed event
        if (variables
                .containsKey(TimelineConstants.VARIABLE_DATE_RANGE_CHANGED_EVENT)) {
            Object[] values = (Object[]) variables
                    .get(TimelineConstants.VARIABLE_DATE_RANGE_CHANGED_EVENT);
            selectedStartDate = new Date(Long.parseLong(values[0].toString()));
            selectedEndDate = new Date(Long.parseLong(values[1].toString()));
            fireDateRangeChangedEvent(selectedStartDate, selectedEndDate);
        }

        // Event button click event received
        if (variables
                .containsKey(TimelineConstants.VARIABLE_EVENT_BUTTON_CLICK)) {
            Object[] indexes = (Object[]) variables
                    .get(TimelineConstants.VARIABLE_EVENT_BUTTON_CLICK);
            List<Object> ids = new ArrayList<Object>();
            for (Object i : indexes) {
                int idx = Integer.parseInt(i.toString());
                Object id = events.getIdByIndex(idx);
                if (id != null) {
                    ids.add(id);
                }
            }

            fireEventButtonClickEvent(ids);
        }
    }

    /**
     * Converts a AWT Color to a rgb/rgba separated string
     * 
     * @param c
     *            The color to convert
     * @param separator
     *            The separator
     * @param includeAlpha
     *            Should the alpha color be appended
     * @return A string representation of the color
     */
    private static String colorToString(Color c, String separator,
            boolean includeAlpha) {
        String red = String.valueOf(c.getRed());
        String green = String.valueOf(c.getGreen());
        String blue = String.valueOf(c.getBlue());
        String alpha = String.valueOf(c.getAlpha());

        StringBuilder color = new StringBuilder();
        color.append(red);
        color.append(separator);
        color.append(green);
        color.append(separator);
        color.append(blue);

        if (includeAlpha) {
            color.append(separator);
            color.append(alpha);
        }

        return color.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.vaadin.ui.AbstractComponent#paintContent(com.vaadin.terminal.PaintTarget
     * )
     */
    @Override
    public void paintContent(PaintTarget target) throws PaintException {

        // Send dirty flag if graph data sources have been altered
        target.addAttribute(TimelineConstants.ATTRIBUTE_DIRTY, dirty);
        if (dirty) {
            sendSizes = true;
            sendGraphColors = true;
            sendGraphFillColors = true;
            sendCaptions = true;
            sendClientCache = true;
            sendBrowserColors = true;
            sendBrowserFillColors = true;
            sendDataPoints = true;
            sendGraphVisibilities = true;
            sendLineCaps = true;
            sendVerticalScaleLimits = true;
            sendVerticalGridLines = true;
            sendSizes = true;
            sendTimeLimits = true;
        }
        dirty = false;

        // Send the selection lock
        target.addAttribute(TimelineConstants.ATTRIBUTE_LOCK,
                selectionBarLocked);

        // Send graph stacking
        target.addAttribute(TimelineConstants.ATTRIBUTE_STACKED, graphStacking);

        // Send uniform bar chart
        target.addAttribute(TimelineConstants.ATTRIBUTE_UNIFORM_BARCHART,
                uniformBarThickness);

        // Always send change event available flag
        target.addAttribute(TimelineConstants.ATTRIBUTE_CHANGE_EVENT_ENABLED,
                sendChangeEventAvailable);
        target.addAttribute(
                TimelineConstants.ATTRIBUTE_EVENT_CLICK_LISTENER_ENABLED,
                sendEventClickAvailable);

        // Always send shadows
        target.addAttribute(TimelineConstants.ATTRIBUTE_GRAPH_SHADOWS,
                graphShadowsEnabled);

        // Always send offscreen rendering
        target.addAttribute(TimelineConstants.ATTRIBUTE_OFFSCREEN_RENDERING,
                offscreenPoints);

        // Always sending locale
        final Locale l = getLocale();
        if (l != null) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_LOCALE,
                    l.toString());
        }

        // Send disable flag if no points are present
        boolean hasData = false;
        for (TimelineDatasourceProperties ds : datasources) {
            if (ds.getDatasource().size() > 0) {
                hasData = true;
                break;
            }
        }

        // Send the no data flag if there is no data added to the graph
        if (datasources.size() == 0 || !hasData) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_NODATA, true);
        }

        // Send the state of the client cache
        if (sendClientCache) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_CACHE, clientCache);
            sendClientCache = false;
        }

        // Send graph visibilities
        if (sendGraphVisibilities) {
            List<String> vis = new ArrayList<String>();
            for (TimelineDatasourceProperties ds : datasources) {
                vis.add(String.valueOf(ds.isVisible()));
            }

            target.addVariable(this, TimelineConstants.VARIABLE_VISIBILITIES,
                    vis.toArray(new String[datasources.size()]));
            sendGraphVisibilities = false;
        }

        // Send point data
        if (dataToSend.keySet().size() > 0 && sendDataPoints) {
            getLogger().finest(
                    "Sending " + dataToSend.keySet().size()
                            + " data points to client");

            // Concatenate the graph data into a semicolon separated string and
            // put
            // them in a map
            Map<Long, String> mmap = new HashMap<Long, String>();
            for (Long r : dataToSend.keySet()) {
                StringBuilder values = new StringBuilder();

                for (String value : dataToSend.get(r)) {
                    values.append(value);
                    values.append(";");
                }

                mmap.put(r, values.toString());
            }

            // If we have any graph data to send then add them as a map
            // attribute
            if (mmap.size() > 0) {
                target.addAttribute(TimelineConstants.ATTRIBUTE_DATA, mmap);
            }

            dataToSend.clear();

            // Add the markers
            if (markersToSend.size() > 0) {
                getLogger().finest(
                        "Sending " + markersToSend.size()
                                + " markers to client");
                target.addVariable(this, TimelineConstants.VARIABLE_MARKERS,
                        markersToSend.toArray(new String[markersToSend.size()]));
            }

            markersToSend.clear();

            // Add the events
            if (eventsToSend.size() > 0) {
                getLogger().finest(
                        "Sending " + eventsToSend.size() + " events to client");
                target.addVariable(this, TimelineConstants.VARIABLE_EVENTS,
                        eventsToSend.toArray(new String[eventsToSend.size()]));
            }

            eventsToSend.clear();

            // Notify client of changed densities
            if (changedDensities.size() > 0) {
                getLogger().finest(
                        "Notifying client of new density "
                                + changedDensities.size() + "");
                target.addAttribute(TimelineConstants.ATTRIBUTE_DENSITIES,
                        changedDensities);
            }

            changedDensities.clear();
        }

        // Send sizes of the graph containers
        if (sendSizes) {
            String[] sizes = new String[datasources.size()];
            for (int i = 0; i < datasources.size(); i++) {
                TimelineDatasourceProperties ds = datasources.get(i);
                Container.Indexed cont = ds.getDatasource();
                sizes[i] = String.valueOf(cont.size());
                getLogger().finest(
                        "Datasource " + i + " has a size of " + sizes[i]
                                + " points");
            }
            target.addVariable(this,
                    TimelineConstants.VARIABLE_CONTAINER_SIZES, sizes);
            sendSizes = false;
        }

        // Send time limits of the graph containers
        if (sendTimeLimits && minDate != null && maxDate != null) {
            getLogger().finest("Notifying client of minimum date " + minDate);
            target.addAttribute(TimelineConstants.ATTRIBUTE_START_DATE,
                    minDate.getTime());
            getLogger().finest("Notifying client of maximum date " + maxDate);
            target.addAttribute(TimelineConstants.ATTRIBUTE_END_DATE,
                    maxDate.getTime());
            getLogger().finest(
                    "Notifying client of number of graphs "
                            + datasources.size());
            target.addAttribute(TimelineConstants.ATTRIBUTE_NUMBER_OF_GRAPHS,
                    datasources.size());
            sendTimeLimits = false;
        }

        // Send the stroke colors
        if (sendGraphColors) {
            Object[] cols = new Object[datasources.size()];
            for (int i = 0; i < cols.length; i++) {
                TimelineDatasourceProperties ds = datasources.get(i);
                Color c = ds.getColor();
                cols[i] = colorToString(c, ";", true);
                getLogger().finest("Graph " + i + " stroke color is " + c);
            }
            target.addAttribute(TimelineConstants.ATTRIBUTE_COLORS, cols);
            sendGraphColors = false;
        }

        // Send the browser stroke colors
        if (sendBrowserColors) {
            Object[] cols = new Object[datasources.size()];
            for (int i = 0; i < cols.length; i++) {
                TimelineDatasourceProperties ds = datasources.get(i);
                Color c = ds.getBrowserColor();
                cols[i] = colorToString(c, ";", true);
                getLogger().finest(
                        "Browser Graph " + i + " stroke color is " + c);
            }
            target.addAttribute(TimelineConstants.ATTRIBUTE_BROWSER_COLORS,
                    cols);
            sendBrowserColors = false;

        }

        // Send the fill colors
        if (sendGraphFillColors) {
            Object[] cols = new Object[datasources.size()];
            for (int i = 0; i < cols.length; i++) {
                TimelineDatasourceProperties ds = datasources.get(i);
                Color c = ds.getFillColor();
                cols[i] = colorToString(c, ";", true);
                getLogger().finest("Graph " + i + " fill color is " + c);
            }
            target.addAttribute(TimelineConstants.ATTRIBUTE_FILLCOLORS, cols);
            sendGraphFillColors = false;
        }

        // Send the browser fill colors
        if (sendBrowserFillColors) {
            Object[] cols = new Object[datasources.size()];
            for (int i = 0; i < cols.length; i++) {
                TimelineDatasourceProperties ds = datasources.get(i);
                Color c = ds.getBrowserFillColor();
                cols[i] = colorToString(c, ";", true);
                getLogger().finest(
                        "Browser Graph " + i + " stroke color is " + c);
            }
            target.addAttribute(TimelineConstants.ATTRIBUTE_BROWSER_FILLCOLORS,
                    cols);
            sendBrowserFillColors = false;
        }

        // Send the graph captions
        if (sendCaptions) {
            List<String> captionsToSend = new ArrayList<String>();
            for (TimelineDatasourceProperties ds : datasources) {
                captionsToSend.add(ds.getCaption());
            }
            getLogger().finest(
                    "Sending graph legend captions " + captionsToSend
                            + " to client");
            target.addVariable(this, TimelineConstants.VARIABLE_CAPTIONS,
                    captionsToSend.toArray(new String[captionsToSend.size()]));
            sendCaptions = false;
        }

        // Send the graph units
        if (sendVUnit) {
            List<String> unitsToSend = new ArrayList<String>();
            for (TimelineDatasourceProperties ds : datasources) {
                unitsToSend.add(ds.getLegendUnit());
            }
            getLogger().finest(
                    "Sending graph vertical units " + unitsToSend
                            + " to client");
            target.addVariable(this, TimelineConstants.VARIABLE_VERTICAL_UNIT,
                    unitsToSend.toArray(new String[unitsToSend.size()]));
            sendVUnit = false;
        }

        // Send refresh request
        if (sendRefreshRequest) {
            getLogger().finest(
                    "Server side implementation request a client refresh");
            target.addAttribute(TimelineConstants.ATTRIBUTE_REFRESH, true);
            sendRefreshRequest = false;
        }

        // Send new selected date range
        if (sendDateRange && selectedStartDate != null
                && selectedEndDate != null && initDone) {
            getLogger().finest(
                    "Sending date selection (" + selectedStartDate + " - "
                            + selectedEndDate + ") to client");
            target.addVariable(this,
                    TimelineConstants.VARIABLE_SELECTION_START,
                    selectedStartDate.getTime());
            target.addVariable(this, TimelineConstants.VARIABLE_SELECTION_END,
                    selectedEndDate.getTime());

            sendDateRange = false;
        }

        // Send vertical scale settings
        if (sendVerticalScaleLimits) {
            target.addVariable(this,
                    TimelineConstants.VARIABLE_VERTICAL_FITTING,
                    verticalScaleFitting);
            getLogger().finest(
                    "Timeline is using vertical scale fitting "
                            + verticalScaleFitting);

            if (verticalScaleMinimum != null && verticalScaleMaximum != null) {
                target.addVariable(this,
                        TimelineConstants.VARIABLE_VERTICAL_FITTING_MIN,
                        verticalScaleMinimum);
                getLogger().finest(
                        "Sending vertical scale limits, min: "
                                + verticalScaleMinimum + ", max: "
                                + verticalScaleMaximum);
                target.addVariable(this,
                        TimelineConstants.VARIABLE_VERTICAL_FITTING_MAX,
                        verticalScaleMaximum);
            }

            sendVerticalScaleLimits = false;
        }

        // Send grid lines
        if (sendVerticalGridLines) {
            if (verticalGridLines == null) {
                target.addVariable(this,
                        TimelineConstants.VARIABLE_NO_VERTICAL_GRID, true);
                getLogger().finest("Notifying client to turn of vertical grid");
            } else {
                List<String> gridValues = new ArrayList<String>();
                for (Float line : verticalGridLines) {
                    gridValues.add(String.valueOf(line));
                }
                target.addVariable(this,
                        TimelineConstants.VARIABLE_VERTICAL_GRID_LINES,
                        gridValues.toArray(new String[gridValues.size()]));
                getLogger().finest(
                        "Sending grid lines " + gridValues + " to client");
                target.addVariable(this,
                        TimelineConstants.VARIABLE_NO_VERTICAL_GRID, false);
            }
            sendVerticalGridLines = false;
        }

        // Send the line caps state
        if (sendLineCaps) {
            List<String> caps = new ArrayList<String>();
            for (TimelineDatasourceProperties ds : datasources) {
                caps.add(String.valueOf(ds.isCapsVisible() ? 1 : 0));
            }
            getLogger().finest("Sending line caps " + caps + " to client");
            target.addVariable(this, TimelineConstants.VARIABLE_CAPS,
                    caps.toArray(new String[caps.size()]));
            sendLineCaps = false;
        }

        // Send the graph thickness
        if (sendGraphThicknesses) {
            List<String> thicknesses = new ArrayList<String>();
            for (TimelineDatasourceProperties ds : datasources) {
                thicknesses.add(String.valueOf(ds.getThickness()));
            }
            getLogger().finest(
                    "Sending graph line thicknesses " + thicknesses
                            + " to client");
            target.addVariable(this,
                    TimelineConstants.VARIABLE_LINE_GRAPH_THICKNESS,
                    thicknesses.toArray(new String[thicknesses.size()]));

            sendGraphThicknesses = false;
        }

        // Send the component visibilities
        if (sendComponentVisibilities) {
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_BROWSER_VISIBLITY,
                    browserIsVisible);
            getLogger().finest("Browser area is visible " + browserIsVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_ZOOMBAR_VISIBILITY,
                    zoomIsVisible);
            getLogger().finest("Zoombar is visible " + zoomIsVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_DATESELECT_VISIBILITY,
                    dateSelectVisible);
            getLogger().finest("Date select is visible " + dateSelectVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_DATESELECT_ENABLED,
                    dateSelectEnabled);
            getLogger().finest("Date select is enabled " + dateSelectEnabled);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_CHARTMODE_VISIBILITY,
                    chartModesVisible);
            getLogger().finest("Chart modes are visible " + chartModesVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_CHARTMODE_LINECHART_VISIBILITY,
                    lineChartModeVisible);
            getLogger().finest(
                    "Linechart mode is visible " + lineChartModeVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_CHARTMODE_BARCHART_VISIBILITY,
                    barChartModeVisible);
            getLogger().finest(
                    "Barchart mode is visible " + barChartModeVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_UI_CHARTMODE_SCATTERCHART_VISIBILITY,
                    scatterChartModeVisible);
            getLogger().finest(
                    "Scatterchart mode is visible " + scatterChartModeVisible);
            target.addAttribute(
                    TimelineConstants.ATTRIUBTE_UI_LEGEND_VISIBILITY,
                    legendVisible);
            getLogger().finest("Legend is visible " + legendVisible);
            sendComponentVisibilities = false;
        }

        // Send zoom levels
        if (sendZoomLevels) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVELS,
                    zoomLevels);
            getLogger().finest(
                    "Sending zoom levels " + zoomLevels + " to client");
            sendZoomLevels = false;
        }

        // Send the chart mode if it has changed
        if (sendChartMode) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE,
                    currentChartMode.toString());
            getLogger().finest("Current chart mode is " + currentChartMode);
            sendChartMode = false;
        }

        // Send the graph grid color if it has changed
        if (sendGraphGrid) {
            getLogger().finest("Grid color is " + graphGridColor);
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_GRID_COLOR,
                    graphGridColor == null ? "" : colorToString(graphGridColor,
                            ";", true));
            sendGraphGrid = false;
        }

        // Send the UI captions if they have changed
        if (sendUICaptions) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_CHART_MODE_CAPTION,
                    chartModeCaption);
            getLogger().finest("Chart mode caption is " + chartModeCaption);
            target.addAttribute(TimelineConstants.ATTRIBUTE_ZOOM_LEVEL_CAPTION,
                    zoomLevelCaption == null ? "" : zoomLevelCaption);
            getLogger()
                    .finest("Zoom level mode caption is " + zoomLevelCaption);
            target.addAttribute(TimelineConstants.ATTRIBUTE_NO_DS_CAPTION,
                    noDataSourceCaption == null ? "" : noDataSourceCaption);
            sendUICaptions = false;
        }

        // Send date formats if set
        if (sendDateFormatInfo) {
            target.addAttribute(TimelineConstants.ATTRIBUTE_DATE_FORMATS,
                    dateFormatInfo.serialize());
            sendDateFormatInfo = false;
        }

        // Send vertical axis number format if needed
        if (sendVerticalAxisNumberFormat) {
            target.addAttribute(
                    TimelineConstants.ATTRIBUTE_VERTICAL_NUMBER_FORMAT,
                    verticalAxisNumberFormat);
            sendVerticalAxisNumberFormat = false;
        }
    }

    /**
     * <p>
     * Adds a graph data source.
     * </p>
     * <p>
     * The graph data source should use the default property id:s
     * PropertyId.TIMESTAMP and PropertyId.VALUE or the property id:s should
     * <b>immediately</b> be set with setGraphTimestampPropertyId() and
     * setGraphValuePropertyId().
     * </p>
     * <p>
     * <B>Note:</B> The graph has to be sorted by the timestamp value for the
     * timeline to work properly!
     * </p>
     * 
     * @param dataSource
     *            The data source container
     * @deprecated Use {@link #addGraphDataSource(Indexed, Object, Object)}
     *             instead
     */
    @Deprecated
    public void addGraphDataSource(Container.Indexed dataSource) {
        /*
         * NOTE Uses its own implementation instead of
         * addGraphDatasource(source,property,property) since property ids might
         * be set after call to this method.
         */
        if (dataSource == null) {
            getLogger().severe("Cannot add a datasource with NULL value");
            throw new IllegalArgumentException("The data source cannot be NULL");
        }

        // Ignore already added data source
        for (TimelineDatasourceProperties ds : datasources) {
            if (ds.getDatasource() == dataSource) {
                getLogger().warning("Datasource already added to Timeline");
                return;
            }
        }

        // Create a new datasource
        TimelineDatasourceProperties ds = new TimelineDatasourceProperties(
                dataSource);
        datasources.add(ds);
        getLogger().finest("A new datasource was added to the Timeline");

        initNewGraphDataSource(ds);
    }

    /**
     * Initiates new default values for a datasource
     * 
     * @param dataSource
     *            The new datasource
     */
    private void initNewGraphDataSource(TimelineDatasourceProperties ds) {

        ds.setCaption("Graph " + datasources.indexOf(ds));

        sendSizes = true;
        sendTimeLimits = true;
        sendDataPoints = true;
        sendVUnit = true;
        sendGraphVisibilities = true;
        sendGraphColors = true;
        sendGraphFillColors = true;
        sendCaptions = true;
        sendBrowserColors = true;
        sendBrowserFillColors = true;

        // Set the minimum and maximum date
        minDate = getFirstDateInGraphs();
        maxDate = getLastDateInGraphs();

        // Check for selection
        if (selectedStartDate == null) {
            selectedStartDate = minDate;
            sendDateRange = true;
        }
        if (selectedEndDate == null) {
            selectedEndDate = maxDate;
            sendDateRange = true;
        }

        if (initDone) {
            dirty = true;
            requestRepaint();
        }

        // Listen to data source changes
        listenToDatasourceChanges();
    }

    /**
     * <p>
     * Adds a new graph data source.
     * </p>
     * 
     * <p>
     * <B>Note:</B> The graph has to be sorted by the timestamp value for the
     * timeline to work properly!
     * </p>
     * 
     * @param dataSource
     *            The data source
     * @param timestampPropertyId
     *            The timestamp property id
     * @param valuePropertyId
     *            The value property id
     * 
     */
    public void addGraphDataSource(Container.Indexed dataSource,
            Object timestampPropertyId, Object valuePropertyId) {
        if (dataSource == null) {
            throw new IllegalArgumentException("The data source cannot be NULL");
        }
        if (timestampPropertyId == null || valuePropertyId == null) {
            throw new IllegalArgumentException("Property cannot be NULL");
        }
        if (!dataSource.getContainerPropertyIds().contains(timestampPropertyId)) {
            throw new IllegalArgumentException(
                    "Datasource does not contain timestamp property");
        }
        if (!dataSource.getContainerPropertyIds().contains(valuePropertyId)) {
            throw new IllegalArgumentException(
                    "Datasource does not contain value property");
        }

        // Ignore already added data source
        for (TimelineDatasourceProperties ds : datasources) {
            if (ds.getDatasource() == dataSource) {
                getLogger().warning("Datasource already added to Timeline");
                return;
            }
        }

        // Create new datasource
        TimelineDatasourceProperties ds = new TimelineDatasourceProperties(
                dataSource);
        datasources.add(ds);
        getLogger().finest("Added new datasource to Timeline");

        try {
            setGraphTimestampPropertyId(dataSource, timestampPropertyId);
            setGraphValuePropertyId(dataSource, valuePropertyId);
        } catch (IllegalArgumentException e) {
            getLogger()
                    .warning(
                            "Failed to set properties for graph, rolling back datasource addition");
            datasources.remove(ds);
            throw e;
        }

        initNewGraphDataSource(ds);
    }

    /**
     * Remove graph data source.<br/>
     * This will remove the graph data source and all properties related to it
     * from the Timeline.
     * 
     * @param graphIndex
     *            The index of the graph data source
     */
    protected void removeGraphDataSource(int graphIndex) {
        if (graphIndex < 0 || graphIndex >= datasources.size()) {
            return;
        }

        TimelineDatasourceProperties ds = datasources.get(graphIndex);

        // Remove listeners
        if (ds.getDatasource() instanceof ItemSetChangeNotifier) {
            ((ItemSetChangeNotifier) ds.getDatasource())
                    .removeListener(dataSourceListener);

        }
        if (ds.getDatasource() instanceof PropertySetChangeNotifier) {
            ((PropertySetChangeNotifier) ds.getDatasource())
                    .removeListener(dataSourceListener);
        }
        if (ds.getDatasource() instanceof ValueChangeNotifier) {
            ((ValueChangeNotifier) ds.getDatasource())
                    .removeListener(dataSourceListener);
        }

        datasources.remove(ds);

        sendSizes = true;
        sendTimeLimits = true;
        sendDataPoints = true;
        sendVUnit = true;
        sendGraphVisibilities = true;
        sendGraphColors = true;
        sendGraphFillColors = true;
        sendCaptions = true;
        sendBrowserColors = true;
        sendBrowserFillColors = true;

        // Set the minimum and maximum date f
        minDate = getFirstDateInGraphs();
        maxDate = getLastDateInGraphs();

        // Ensure selection is between the values, else reset them
        if (selectedStartDate != null && minDate != null && maxDate != null) {
            if (selectedStartDate.before(minDate)
                    || selectedStartDate.after(maxDate)) {
                selectedStartDate = minDate;
            }
        }

        if (selectedEndDate != null && minDate != null && maxDate != null) {
            if (selectedEndDate.before(minDate)
                    || selectedEndDate.after(maxDate)) {
                selectedEndDate = maxDate;
            }
        }

        if (initDone) {
            dirty = true;
            requestRepaint();
        }
    }

    /**
     * Gets the absolute first date in the graph, there exists no points before
     * this date.
     * 
     * @return The starting date of the Timeline
     */
    private Date getFirstDateInGraphs() {
        Date minDate = null;
        for (TimelineDatasourceProperties ds : datasources) {
            Indexed cont = ds.getDatasource();
            Object timestampPropertyId = getGraphTimestampPropertyId(cont);
            if (timestampPropertyId != null && cont.size() > 0) {
                Item item = cont.getItem(cont.firstItemId());
                if (item != null
                        && item.getItemPropertyIds().contains(
                                timestampPropertyId)) {
                    Property<?> property = item
                            .getItemProperty(timestampPropertyId);
                    Date d1 = (Date) property.getValue();
                    if (minDate == null || minDate.after(d1)) {
                        minDate = d1;
                    }
                } else {
                    getLogger()
                            .warning(
                                    "Item not found or timestamp property was NULL "
                                            + "when searching for first date in graph. Check that "
                                            + "the container is sorted and that the timestamp property "
                                            + "is correctly given.");
                }
            }
        }
        return minDate;
    }

    /**
     * Gets the absolute last date in the Timeline, there exists no points after
     * this date.
     * 
     * @return The ending date of the Timeline
     */
    private Date getLastDateInGraphs() {
        Date maxDate = null;
        for (TimelineDatasourceProperties ds : datasources) {
            Indexed cont = ds.getDatasource();
            Object timestampPropertyId = getGraphTimestampPropertyId(cont);
            if (timestampPropertyId != null & cont.size() > 0) {
                Item item = cont.getItem(cont.lastItemId());
                if (item != null
                        && item.getItemPropertyIds().contains(
                                timestampPropertyId)) {
                    Property<?> property = cont.getItem(cont.lastItemId())
                            .getItemProperty(timestampPropertyId);
                    Date d2 = (Date) property.getValue();
                    if (maxDate == null || maxDate.before(d2)) {
                        maxDate = d2;
                    }
                } else {
                    getLogger()
                            .warning(
                                    "Item not found or timestamp property was NULL "
                                            + "when searching for last date in graph. Check that "
                                            + "the container is sorted and that the timestamp property "
                                            + "is correctly given.");
                }
            }
        }
        return maxDate;
    }

    /**
     * Remove graph data source. <br/>
     * This will remove the graph data source and all properties related to it
     * from the Timeline.
     * 
     * @param dataSource
     *            The graphs data source
     */
    public void removeGraphDataSource(Container.Indexed dataSource) {
        if (dataSource == null) {
            getLogger().warning("Could not remove a NULL datasource");
            return;
        }

        for (int i = 0; i < datasources.size(); i++) {
            if (datasources.get(i).getDatasource() == dataSource) {
                removeGraphDataSource(i);
            }
        }
    }

    /**
     * Set the marker event data source.<br/>
     * The marker event data source needs to have three specific properties; a
     * timestamp property, a caption property and a value property. These will
     * be used to draw the markers on to the timeline. This method will use the
     * default property id:s to search for these properties.
     * 
     * @param markers
     *            The markers data source
     * @deprecated Use
     *             {@link #setMarkerDataSource(Indexed, Object, Object, Object)}
     *             instead
     */
    @Deprecated
    public void setMarkerDataSource(Container.Indexed markers) {
        setMarkerDataSource(markers, markerTimestampPropertyId,
                markerCaptionPropertyId, markerValuePropertyId);
    }

    /**
     * Set the marker event data source<br/>
     * 
     * @param markers
     *            A container with the markers
     * @param timestampPropertyId
     *            The timestamp property id
     * @param captionPropertyId
     *            The caption property id
     * @param valuePropertyId
     *            The value property id
     */
    public void setMarkerDataSource(Container.Indexed markers,
            Object timestampPropertyId, Object captionPropertyId,
            Object valuePropertyId) {

        this.markers = markers;

        markerTimestampPropertyId = timestampPropertyId;
        markerCaptionPropertyId = captionPropertyId;
        markerValuePropertyId = valuePropertyId;

        // Sort if possible
        if (markers instanceof Container.Sortable) {
            getLogger().finest("Sorting marker datasource");
            ((Container.Sortable) markers).sort(
                    new Object[] { markerTimestampPropertyId },
                    new boolean[] { true });
        }

        if (initDone) {
            dirty = true;
            requestRepaint();
        }

        listenToDatasourceChanges();
    }

    /**
     * Get the marker event data source
     */
    public Container.Indexed getMarkerDataSource() {
        return markers;
    }

    /**
     * Sets the timestamp property id of a graph event
     * 
     * @param id
     *            The timestamp property id
     */
    public void setEventTimestampPropertyId(Object id) {
        eventTimestampPropertyId = id;
    }

    /**
     * Gets the timestamp property id of a graph event
     * 
     * @return The timestamp property id
     */
    public Object getEventTimestampPropertyId() {
        return eventTimestampPropertyId;
    }

    /**
     * Sets the caption property id of a graph event
     * 
     * @param id
     *            The caption property id
     */
    public void setEventCaptionPropertyId(Object id) {
        eventCaptionPropertyId = id;
    }

    /**
     * Gets the caption property id of a graph event
     * 
     * @return The caption property id
     */
    public Object getEventCaptionPropertyId() {
        return eventCaptionPropertyId;
    }

    /**
     * Set the clickable events data source. id.
     * 
     * @param events
     *            The events container
     * @deprecated Use {@link #setEventDataSource(Indexed, Object, Object)}
     *             instead
     */
    @Deprecated
    public void setEventDataSource(Container.Indexed events) {
        setEventDataSource(events, eventTimestampPropertyId,
                eventCaptionPropertyId);
    }

    /**
     * Set the clickable events Note: The container should be sorted by date
     * 
     * @param events
     *            A container with the dates
     * @param timestampPropertyId
     *            The timestamp property id
     * @param captionPropertyId
     *            The caption property id
     */
    public void setEventDataSource(Container.Indexed events,
            Object timestampPropertyId, Object captionPropertyId) {
        this.events = events;

        eventTimestampPropertyId = timestampPropertyId;
        eventCaptionPropertyId = captionPropertyId;

        // Sort if possible
        if (events instanceof Container.Sortable) {
            getLogger().finest("Sorting event datasource");
            ((Container.Sortable) events).sort(
                    new Object[] { eventTimestampPropertyId },
                    new boolean[] { true });
        }

        if (initDone) {
            dirty = true;
            requestRepaint();
        }

        listenToDatasourceChanges();
    }

    /**
     * Get the event data source
     */
    public Container.Indexed getEventDataSource() {
        return events;
    }

    /**
     * The the properties object for a graph datasource. The properties object
     * contains all the graph specific settings like color, fillcolor etc.
     * 
     * @param dataSource
     *            The datasource to get the properties for
     * @return
     */
    protected TimelineDatasourceProperties getDatasourceProperties(
            Container.Indexed dataSource) {
        for (TimelineDatasourceProperties ds : datasources) {
            if (ds.getDatasource() == dataSource) {
                return ds;
            }
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set the outline color of a graph.
     * 
     * @param dataSource
     *            The data source of the graph
     * @param color
     *            The color of the graph outline
     */
    public void setGraphOutlineColor(Container.Indexed dataSource, Color color) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            if (color == null) {
                color = Color.BLACK;
            }
            ds.setColor(color);
            sendGraphColors = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the color.");
        }
    }

    /**
     * Get a graph color
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public Color getGraphOutlineColor(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getColor();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set a specific graphs fill color
     * 
     * @param dataSource
     *            The data source of the graph
     * @param color
     *            The color to be used as a fill color
     */
    public void setGraphFillColor(Container.Indexed dataSource, Color color) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            if (color == null) {
                color = new Color(0, 0, 0, 0);
            }
            ds.setFillColor(color);
            sendGraphFillColors = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the color");
        }
    }

    /**
     * Get graph fill color
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public Color getGraphFillColor(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getFillColor();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set the browser stroke color
     * 
     * @param dataSource
     *            The data source of the graph
     * @param color
     *            The outline color of the graph
     */
    public void setBrowserOutlineColor(Container.Indexed dataSource, Color color) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            if (color == null) {
                color = new Color(0x00, 0x67, 0xDD);
            }
            ds.setBrowserColor(color);
            sendBrowserColors = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the color.");
        }
    }

    /**
     * Get the browser stroke color
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public Color getBrowserOutlineColor(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getBrowserColor();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set the browser graph fill color
     * 
     * @param dataSource
     *            The data source of the graph
     * @param color
     *            The fill color of the graph
     */
    public void setBrowserFillColor(Container.Indexed dataSource, Color color) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            if (color == null) {
                color = new Color(0xED, 0xF7, 0xFF);
            }
            ds.setBrowserFillColor(color);
            sendBrowserFillColors = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the color.");
        }
    }

    /**
     * Get the browser fill color
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public Color getBrowserFillColor(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getBrowserFillColor();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * @deprecated use {@link #setGraphCaption(Indexed, String)} instead
     */
    @Deprecated
    public void setGraphLegend(Container.Indexed dataSource, String caption) {
        setGraphCaption(dataSource, caption);
    }

    /**
     * Set a specific graphs caption in the legend
     * 
     * @param dataSource
     *            The data source of the graph
     * @param caption
     *            The caption to be shown in the legend
     */
    public void setGraphCaption(Container.Indexed dataSource, String caption) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            int index = datasources.indexOf(ds);
            if (caption == null) {
                // Reset caption to default
                caption = "Graph " + index;
            }
            ds.setCaption(caption);
            sendCaptions = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the caption");
        }

    }

    /**
     * Get a graphs legend caption
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public String getGraphCaption(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getCaption();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set the visibility of a graph. <br/>
     * 
     * 
     * 
     * @param dataSource
     *            The data source of the graph
     * @param visibility
     *            The visibility of the graph
     */
    public void setGraphVisibility(Container.Indexed dataSource,
            boolean visibility) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            ds.setVisible(visibility);
            sendGraphVisibilities = true;
            if (initDone) {
                dirty = true;
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the visibility.");
        }
    }

    /**
     * Get a graphs visibility
     * 
     * @param dataSource
     *            The data source of the graph
     */
    public boolean getGraphVisibility(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.isVisible();
        }
        getLogger().warning("Could not find datasource properties");
        return false;
    }

    /**
     * Sets the displayed time range.<br/>
     * The displayed time is the time range selected in the browser and
     * displayed in the main area of the component. The displayed time range
     * cannot be set until some data source has been added to the component.
     * 
     * @param start
     *            The start date
     * @param end
     *            The end data
     */
    public void setVisibleDateRange(Date start, Date end) {

        if (minDate == null || maxDate == null) {
            throw new IllegalArgumentException(
                    "Add graph data source before selecting date range.");
        }

        if (start == null || start.compareTo(minDate) <= 0) {
            start = minDate;
        }

        if (end == null || end.compareTo(maxDate) >= 0) {
            end = maxDate;
        }

        // Do consistency check
        if (start.equals(end) || end.after(start)) {
            selectedStartDate = start;
            selectedEndDate = end;
            sendDateRange = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "End date must come after the start date.");
        }
    }

    /**
     * Makes the whole graph visible and selected in the browser.
     */
    public void selectFullRange() {
        setVisibleDateRange(minDate, maxDate);
    }

    /**
     * Adds a date range listener.<br/>
     * This is triggered when the date range is changed.
     * 
     * @param listener
     *            The listener to be added
     */
    public void addListener(DateRangeListener listener) {
        addListener(DateRangeChangedEvent.class, listener,
                DATERANGE_CHANGED_METHOD);
        sendChangeEventAvailable = true;
        requestRepaint();
    }

    /**
     * Add a button click listener
     * 
     * @param listener
     *            The listener to be added
     */
    public void addListener(EventClickListener listener) {
        addListener(EventButtonClickEvent.class, listener, EVENT_CLICK_METHOD);
        sendEventClickAvailable = true;
        requestRepaint();
    }

    /**
     * Remove a date range listener
     * 
     * @param listener
     *            The listener to be removed
     */
    public void removeListener(DateRangeListener listener) {
        removeListener(DateRangeChangedEvent.class, listener);
        if (getListeners(DateRangeChangedEvent.class).isEmpty()) {
            sendChangeEventAvailable = false;
        }
        requestRepaint();
    }

    /**
     * Remove a event button click listener
     * 
     * @param listener
     *            The listener to be removed
     */
    public void removeListener(EventClickListener listener) {
        removeListener(EventButtonClickEvent.class, listener);
        if (getListeners(EventButtonClickEvent.class).isEmpty()) {
            sendEventClickAvailable = false;
        }
        requestRepaint();
    }

    /**
     * Fires a date range changed event
     * 
     * @param start
     *            The start date of the range
     * @param end
     *            The end date of the range
     */
    protected void fireDateRangeChangedEvent(Date start, Date end) {
        getLogger().finest(
                "Firing a date range changed event with dates " + start + " - "
                        + end);
        fireEvent(new Timeline.DateRangeChangedEvent(this, start, end));
    }

    /**
     * Fires a event button click event which occurs when the user presses an
     * event button in the graph
     * 
     * @param itemIds
     *            The item id(s) in the Event container which represents the
     *            button(s)
     */
    protected void fireEventButtonClickEvent(List<Object> itemIds) {
        getLogger().finest(
                "Firing a event button click event with ids " + itemIds);
        fireEvent(new Timeline.EventButtonClickEvent(this, itemIds));
    }

    /**
     * Freezes the drawing scale to the given maximum and minimum. Setting
     * minimum and and maximum to null turns automatic vertical scaling on.
     * 
     * @param min
     *            Lower bound
     * @param max
     *            Upper bound
     */
    public void setVerticalAxisRange(Float min, Float max) {

        // Automatic vertical scaling?
        if (min == null && max == null) {
            verticalScaleFitting = true;
            verticalScaleMinimum = null;
            verticalScaleMaximum = null;
            sendVerticalScaleLimits = true;

            if (initDone) {
                requestRepaint();
            }

            // Manual vertical scaling?
        } else if (min != null && max != null) {
            if (min >= max) {
                throw new IllegalArgumentException(
                        "Minimum must be less than maximum");
            }

            verticalScaleFitting = false;
            verticalScaleMinimum = min;
            verticalScaleMaximum = max;
            sendVerticalScaleLimits = true;

            if (initDone) {
                requestRepaint();
            }

            // Both have to be null or a value
        } else {
            throw new IllegalArgumentException(
                    "Cannot set vertical axis range with only one limit.");
        }
    }

    /**
     * Sets the unit of the vertical scale.<br/>
     * The vertical unit is displayed after the value in the legend.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @param unit
     *            The unit
     */
    public void setVerticalAxisLegendUnit(Container.Indexed dataSource,
            String unit) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            if (unit == null) {
                ds.setLegendUnit("");
            } else {
                ds.setLegendUnit(unit);
            }
            sendVUnit = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the vertical unit.");
        }
    }

    /**
     * Get the vertical unit for the graph.<br/>
     * The vertical unit is displayed after the value in the legend.
     * 
     * @param The
     *            data source of the graph
     */
    public String getVerticalAxisLegendUnit(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getLegendUnit();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Set the timestamp property id.<br/>
     * The timestamp property is used to map a specific value to a point in
     * time. The timestamp property should be of type java.util.Date.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @param id
     *            The timestamp property id
     */
    public void setGraphTimestampPropertyId(Container.Indexed dataSource,
            Object id) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            Class<?> clazz = dataSource.getType(id);
            if (java.util.Date.class.isAssignableFrom(clazz)) {
                ds.setTimestampProperty(id);
            } else {
                throw new IllegalArgumentException(
                        "The timestamp must be of type java.util.Date");
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the timestamp property.");
        }
    }

    /**
     * Get the timestamp property id for a graph.<br/>
     * The timestamp property is used to map a specific value to a point in
     * time. The timestamp property should be of type java.util.Date.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @return The timestamp property id
     */
    public Object getGraphTimestampPropertyId(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getTimestampProperty();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Sets the value property id for the markers.<br/>
     * The value property is used to display the text in the popup dialog
     * displayed to the user when the mouse cursor is hovered over the marker
     * icon. The value property id should be a string.
     * 
     * @param id
     *            The value property id
     */
    public void setMarkerValuePropertyId(Object id) {
        markerValuePropertyId = id;
    }

    /**
     * Gets the value property id for the markers.<br/>
     * The value property is used to display the text in the popup dialog
     * displayed to the user when the mouse cursor is hovered over the marker
     * icon. The value property id should be a string.
     * 
     * @return The value property id
     */
    public Object getMarkerValuePropertyId() {
        return markerValuePropertyId;
    }

    /**
     * Sets the caption property id for the markers.<br/>
     * Captions are displayed on top of the marker icon.
     * 
     * @param id
     *            The caption property id
     */
    public void setMarkerCaptionPropertyId(Object id) {
        markerCaptionPropertyId = id;
    }

    /**
     * Gets the caption property id for the markers.<br/>
     * Captions are displayed on top of the marker icon.
     * 
     * @return The caption property id
     */
    public Object getMarkerCaptionPropertyId() {
        return markerCaptionPropertyId;
    }

    /**
     * Sets the timestamp property id of the markers.<br/>
     * The timestamp property id is used to determine where on the timeline the
     * marker will be displayed.
     * 
     * @param id
     *            The timestamp property id
     */
    public void setMarkerTimestampPropertyId(Object id) {
        markerTimestampPropertyId = id;
    }

    /**
     * Gets the timestamp property id of the markers.<br/>
     * The timestamp propert id is used to determine where on the timeline the
     * marker will be displayed.
     * 
     * @return The timestamp property id
     */
    public Object getMarkerTimestampPropertyId() {
        return markerTimestampPropertyId;
    }

    /**
     * Set the value property id of a graph data source.<br/>
     * The value property id is used to get the the graph data points value. The
     * value property can be any numeric value and cannot be null.
     * 
     * Deprecated, use {@link #setGraphValuePropertyId(Indexed, Object)}
     * instead.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @param id
     *            The value property id
     */
    @Deprecated
    public void setGraphValueProperyId(Container.Indexed dataSource, Object id) {
        setGraphValuePropertyId(dataSource, id);
    }

    /**
     * Set the value property id of a graph data source.<br/>
     * The value property id is used to get the the graph data points value. The
     * value property can be any numeric value and cannot be null.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @param id
     *            The value property id
     */
    public void setGraphValuePropertyId(Container.Indexed dataSource, Object id) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            Class<?> clazz = dataSource.getType(id);
            if (Number.class.isAssignableFrom(clazz)) {
                ds.setValueProperty(id);
            } else {
                throw new IllegalArgumentException(
                        "The value property must be numeric.");
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the value property.");
        }
    }

    /**
     * Get the value property id of a graph data source.<br/>
     * The value property id is used to get the the graph data points value. The
     * value property can be any numeric value and cannot be null.
     * 
     * @param dataSource
     *            The data source of the graph
     * 
     * @return the id
     */
    public Object getGraphValueProperyId(Container.Indexed dataSource) {
        TimelineDatasourceProperties ds = getDatasourceProperties(dataSource);
        if (ds != null) {
            return ds.getValueProperty();
        }
        getLogger().warning("Could not find datasource properties");
        return null;
    }

    /**
     * Sets the state of the client cache.<br/>
     * Client cache is used to store the data on the client side so less data is
     * sent over the network. If the client cache is disabled then all data is
     * always read from the data source and transferred over the network to the
     * client. The preferred option is to leave this on.
     * 
     * @param enabled
     *            If true then turns client cache on
     */
    public void setClientCacheEnabled(boolean enabled) {
        if (clientCache != enabled) {
            clientCache = enabled;
            sendClientCache = true;
            if (initDone) {
                requestRepaint();
            }
        }
    }

    /**
     * Is the client cache enabled<br/>
     * Client cache is used to store the data on the client side so less data is
     * sent over the network. If the client cache is disabled then all data is
     * always read from the data source and transferred over the network to the
     * client. The preferred option is to leave this on.
     */
    public boolean isClientCacheEnabled() {
        return clientCache;
    }

    /**
     * Removes all graph data sources from the Timeline.<br/>
     * NOTE: This however will not clear the marker and event data sources!
     */
    public void removeAllGraphDataSources() {
        datasources.clear();

        minDate = null;
        maxDate = null;
        selectedStartDate = null;
        selectedEndDate = null;

        sendSizes = true;
        sendTimeLimits = true;
        sendDataPoints = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Turns the line caps on for all graphs. <br/>
     * Line caps can be showed at the intersections in the line graph to mark
     * where the actual data point is.
     * 
     * Note that since version 1.3 the graph data sources need to be added
     * before calling this method
     * 
     * @param visible
     *            If true then line caps is drawn, else not
     * @deprecated Use {@link #setGraphCapsVisible(Indexed, boolean)} instead
     */
    @Deprecated
    public void setGraphCapsVisible(boolean visible) {
        if (datasources.isEmpty()) {
            throw new IllegalStateException(
                    "Please add the graph datasources before setting the caps visiblity");
        }
        for (TimelineDatasourceProperties ds : datasources) {
            setGraphCapsVisible(ds.getDatasource(), visible);
        }
    }

    /**
     * Turns the line caps on for the line graph. <br/>
     * Line caps can be showed at the intersections in the line graph to mark
     * where the actual data point is.
     * 
     * @param graph
     *            The graph the the caps should be turned on/off for
     * @param visible
     *            Should the caps be visible
     */
    public void setGraphCapsVisible(Container.Indexed graph, boolean visible) {
        TimelineDatasourceProperties ds = getDatasourceProperties(graph);
        if (ds != null) {
            ds.setCapsVisible(visible);
            sendLineCaps = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the thickness.");
        }
    }

    /**
     * Gets the line caps state
     * 
     * @return The line caps state
     * @deprecated Use {@link #isGraphCapsVisible(Indexed)} instead
     */
    @Deprecated
    public boolean isGraphCapsVisible() {
        if (!datasources.isEmpty()) {
            return datasources.get(0).isCapsVisible();
        }
        return false;
    }

    /**
     * Gets the line caps state
     * 
     * @return The line caps state
     */
    public boolean isGraphCapsVisible(Container.Indexed graph) {
        if (graph == null) {
            throw new IllegalArgumentException(
                    "Graph datasource cannot be null");
        }
        TimelineDatasourceProperties ds = getDatasourceProperties(graph);
        if (ds != null) {
            return ds.isCapsVisible();
        }
        return false;
    }

    /**
     * Sets the outline graph thickness.
     * 
     * Since version 1.3 the graph data sources need to be added before calling
     * this method
     * 
     * @param thickness
     *            The thickness in pixels
     * 
     * @deprecated Use {@link #setGraphOutlineThickness(Indexed, double)}
     *             instead
     */
    @Deprecated
    public void setGraphOutlineThickness(double thickness) {
        if (thickness <= 0) {
            throw new IllegalArgumentException("Thickness must be positive");
        } else if (!datasources.isEmpty()) {
            for (TimelineDatasourceProperties ds : datasources) {
                setGraphOutlineThickness(ds.getDatasource(), thickness);
            }
        } else {
            throw new IllegalStateException(
                    "No graph datasources has been added to the Timeline");
        }
    }

    /**
     * Sets the outline graph thickness.
     * 
     * @param graph
     *            The graph to set the thickness to
     * @param thickness
     *            The thickness of the graph in pixels (must be a positive
     *            value)
     */
    public void setGraphOutlineThickness(Container.Indexed graph,
            double thickness) {
        if (thickness <= 0) {
            throw new IllegalArgumentException("Thickness must be positive");
        }

        TimelineDatasourceProperties ds = getDatasourceProperties(graph);
        if (ds != null) {
            ds.setThickness(thickness);
            sendGraphThicknesses = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the thickness.");
        }
    }

    /**
     * Returns the outline graph thickness
     * 
     * Note: since version 1.3 the graph data sources need to be added before
     * calling this method
     * 
     * @return The thickness in pixels
     * @deprecated Use {@link #getGraphOutlineThickness(Indexed)} instead.
     */
    @Deprecated
    public double getGraphLineThickness() {
        if (!datasources.isEmpty()) {
            return datasources.get(0).getThickness();
        }
        return 2;
    }

    /**
     * Returns the outline graph thickness
     * 
     * @param graph
     *            The graph to get the thicknesses for
     * @return
     */
    public double getGraphOutlineThickness(Container.Indexed graph) {
        TimelineDatasourceProperties ds = getDatasourceProperties(graph);
        if (ds != null) {
            return ds.getThickness();
        } else {
            getLogger().warning("Could not find datasource properties");
            throw new IllegalArgumentException(
                    "The graph container must be added before setting the thickness.");
        }
    }

    /**
     * Shows or hides the browser.<br/>
     * The browser is the timeline browser in the bottom of the component. With
     * the browser you can move or zoom in time.
     * 
     * @param visible
     *            If true then the browser is visible
     */
    public void setBrowserVisible(boolean visible) {
        browserIsVisible = visible;
        sendComponentVisibilities = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Is the browser visible
     */
    public boolean isBrowserVisible() {
        return browserIsVisible;
    }

    /**
     * Show the zoom levels.<br/>
     * Zoom levels are predefined time ranges which are displayed in the top
     * left corner of the Timeline component.
     * 
     * @param visible
     *            If true then the zoom levels are visible
     */
    public void setZoomLevelsVisible(boolean visible) {
        zoomIsVisible = visible;
        sendComponentVisibilities = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Are the zoom levels visible.<br/>
     * Zoom levels are predefined time ranges which are displayed in the top
     * left corner of the Timeline component.
     */
    public boolean isZoomLevelsVisible() {
        return zoomIsVisible;
    }

    /**
     * Sets the caption of the zoom levels
     * 
     * @param caption
     * 
     */
    public void setZoomLevelsCaption(String caption) {
        this.zoomLevelCaption = caption;
        sendUICaptions = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Get the caption of the zoom levels
     * 
     * @return
     */
    public String getZoomLevelsCaption() {
        return this.zoomLevelCaption;
    }

    /**
     * Sets the text that is displayed in the Timeline if no data source is
     * present
     * 
     * @param caption
     *            The caption to set
     */
    public void setNoDataSourceCaption(String caption) {
        this.noDataSourceCaption = caption;
        sendUICaptions = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Returns the caption that is displayed when no captions are present
     * 
     * @return
     */
    public String getNoDataSourceCaption() {
        return noDataSourceCaption;
    }

    /**
     * Show the date select.<br/>
     * The date select is the text fields in the top right corner of the
     * component which shows the currently selected date range.
     * 
     * @param visible
     *            Should the data select be visible
     */
    public void setDateSelectVisible(boolean visible) {
        dateSelectVisible = visible;
        sendComponentVisibilities = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Is the date select visible.<br/>
     * The date select is the text fields in the top right corner of the
     * component which shows the currently selected date range.
     */
    public boolean isDateSelectVisible() {
        return dateSelectVisible;
    }

    /**
     * Enable manual editing of selected date range.<br/>
     * The date select is the text fields in the top right corner of the
     * component which shows the currently selected date range.<br/>
     * The date range can be used to either just display the currently selected
     * date range or one can allow the used to manually edit the selected date
     * range by clicking on the dates by setting enabled to true.
     * 
     * @param enabled
     *            Is the date selected manually editable
     */
    public void setDateSelectEnabled(boolean enabled) {
        dateSelectEnabled = enabled;
        sendComponentVisibilities = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Is the date select enabled.<br/>
     * The date select is the text fields in the top right corner of the
     * component which shows the currently selected date range.<br/>
     * The date range can be used to either just display the currently selected
     * date range or one can allow the used to manually edit the selected date
     * range by clicking on the dates by setting enabled to true.
     */
    public boolean isDateSelectEnabled() {
        return dateSelectEnabled;
    }

    /**
     * Set chart modes visible. <br/>
     * This setting determines whether the user can switch between different
     * chart modes.
     * 
     * @param visible
     *            Are the chart modes visible
     */
    public void setChartModesVisible(boolean visible) {
        chartModesVisible = visible;
        sendComponentVisibilities = true;

        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Are chart modes visible.<br/>
     * This setting determines whether the user can switch between different
     * chart modes.
     */
    public boolean isChartModesVisible() {
        return chartModesVisible;
    }

    /**
     * Set visibility of the chart modes.<br/>
     * Several chart modes are available in the Timeline component. The chart
     * mode determines how the data points are rendered on to the graph.
     * 
     * @param mode
     *            The mode to set visibility of
     * @param visibility
     *            The visibility
     */
    public void setChartModeVisible(ChartMode mode, boolean visibility) {
        if (mode == ChartMode.BAR) {
            barChartModeVisible = visibility;
        } else if (mode == ChartMode.LINE) {
            lineChartModeVisible = visibility;
        } else if (mode == ChartMode.SCATTER) {
            scatterChartModeVisible = visibility;
        } else {
            throw new IllegalArgumentException("Chart mode +" + mode
                    + "is not supported");
        }

        sendComponentVisibilities = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Is a chart mode visible.<br/>
     * Several chart modes are available in the Timeline component. The chart
     * mode determines how the data points are rendered on to the graph.
     * 
     * @param mode
     *            The chart mode
     */
    public boolean isChartModeVisible(ChartMode mode) {
        if (mode == ChartMode.BAR) {
            return barChartModeVisible;
        } else if (mode == ChartMode.LINE) {
            return lineChartModeVisible;
        } else if (mode == ChartMode.SCATTER) {
            return scatterChartModeVisible;
        }

        return false;
    }

    /**
     * Sets the visible chart mode.
     * 
     * @param mode
     *            The chart mode to set visible
     */
    public void setChartMode(ChartMode mode) {
        if (mode != null) {
            currentChartMode = mode;
            sendChartMode = true;
            if (initDone) {
                requestRepaint();
            }
        } else {
            throw new IllegalArgumentException("Chart mode cannot be null");
        }
    }

    /**
     * Returns the current chart mode.
     * 
     * @return The currently used chart mode
     */
    public ChartMode getChartMode() {
        return currentChartMode;
    }

    /**
     * Sets the caption of the chart mode panel
     * 
     * @param caption
     *            The caption to set
     */
    public void setChartModesCaption(String caption) {
        if (caption == null) {
            caption = "";
        }

        chartModeCaption = caption;
        sendUICaptions = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * The caption of the chart mode panel
     * 
     * @return
     */
    public String getChartModesCaption() {
        return chartModeCaption;
    }

    /**
     * Set legend visibility.<br/>
     * The legend displays labels for each graph
     * 
     * @param visible
     *            The legend visibility
     */
    public void setGraphLegendVisible(boolean visible) {
        legendVisible = visible;
        sendComponentVisibilities = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Is the legend visible
     */
    public boolean isGraphLegendVisible() {
        return legendVisible;
    }

    /**
     * Add a zoom level.<br/>
     * Adds a custom zoom level. Zoom levels are defined as milliseconds and are
     * added to the top left of the Timeline component.
     * 
     * @param caption
     *            The title of the zoom level
     * @param time
     *            The timespan of the zoom level
     */
    public void addZoomLevel(String caption, Long time) {
        zoomLevels.put(caption, time);
        sendZoomLevels = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Remove a zoom level.<br/>
     * Zoom levels are defined as milliseconds and are added to the top left of
     * the Timeline component.
     * 
     * @param caption
     *            The title of the zoom level
     */
    public void removeZoomLevel(String caption) {
        zoomLevels.remove(caption);
        sendZoomLevels = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Sets the color of the graph grid.<br/>
     * Setting the color to NULL will remove the grid.
     * 
     * @param color
     *            The color of the grid or Null to remove the grid
     */
    public void setGraphGridColor(Color color) {
        graphGridColor = color;
        sendGraphGrid = true;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Gets the grid color used to draw the vertical and horizontal scale grids.<br/>
     * Returns null if no grid is drawn in the graph
     * 
     * @return The color of the graph
     */
    public Color getGraphGridColor() {
        return graphGridColor;
    }

    /**
     * When using dynamically updating graphs the updates may cause the
     * selection bar to move when new items are added to the data source and the
     * graph changes. To lock the browser bar so it stays still and instead the
     * selection changes when new items are added set this to false. By default
     * the selection bar is locked to the selection. <br/>
     * Please note that when the selection lock is unlocked then the selection
     * range will change with each update.
     * 
     * @param locked
     *            Should the selection range be locked to the selected range or
     *            should the selection change when new items are added to the
     *            data source
     */
    public void setBrowserSelectionLock(boolean locked) {
        selectionBarLocked = locked;
        if (initDone) {
            requestRepaint();
        }
    }

    /**
     * Gets a container index for a date in the container. If the date is not
     * found in the container the closest index to that date is returned.
     * 
     * @param date
     *            The Date to search for, cannot be NULL
     * @param container
     *            The Container to search in, cannot be NULL
     * @param approximateAbove
     *            If the approximation does not find an equal point, then should
     *            a point above be returned or below? If true then the closest
     *            point above the given point is returned
     * 
     * @return The index where the date can be found
     */
    private int getContainerIndexForDate(Date date, Indexed container,
            boolean approximateAbove) {

        // Ensure that min and max dates are set
        if (minDate == null) {
            minDate = getFirstDateInGraphs();
        }
        if (maxDate == null) {
            maxDate = getLastDateInGraphs();
        }

        if (minDate == null || maxDate == null) {
            // Min or max date could not be calculated, this is because the
            // containers are empty or no containers exist
            throw new IllegalStateException(
                    "Either no datasources exist or they are empty");
        }

        // Search for start and end index by approximating the location
        Long timespan = maxDate.getTime() - minDate.getTime();

        // Timespan is 0, only one point exists
        if (timespan == 0) {
            return 0;
        } else if (timespan < 0) {
            throw new IllegalStateException(
                    "The container is not sorted by date");
        }

        /*
         * Do an approximation on the index. Here we assume dates are equally
         * spaced (does not have to be the case, since we are doing an
         * approximation).
         */
        Float timeSizeRatio = container.size() / timespan.floatValue();
        int approximatedIndex = (int) ((date.getTime() - minDate.getTime()) * timeSizeRatio);

        // Boundary checks
        if (approximatedIndex < 0) {
            approximatedIndex = 0;
        } else if (approximatedIndex >= container.size()) {
            approximatedIndex = container.size() - 1;
        }

        // Now resolve the real index from the approximated one
        Date approximatedDate = (Date) container
                .getItem(container.getIdByIndex(approximatedIndex))
                .getItemProperty(getGraphTimestampPropertyId(container))
                .getValue();

        // Check if the approximation was correct, if so we can return the
        // approximation
        if (approximatedDate.getTime() == date.getTime()) {
            return approximatedIndex;
        }

        /*
         * Our approximation was offset, we need to iterate to the right date to
         * find the index
         */
        int index = approximatedIndex;
        if (approximatedDate.before(date)) {

            /*
             * Our approximation is before the real point, iterate forward
             */
            for (int i = approximatedIndex; i < container.size(); i++) {
                approximatedDate = (Date) container
                        .getItem(container.getIdByIndex(i))
                        .getItemProperty(getGraphTimestampPropertyId(container))
                        .getValue();
                if (approximatedDate != null && approximatedDate.after(date)) {
                    index = approximateAbove ? i : i - 1;
                    break;
                }
            }

        } else if (approximatedDate.after(date)) {
            /*
             * Our approximation is after the real point, iterate backward
             */
            for (int i = approximatedIndex; i >= 0; i--) {
                approximatedDate = (Date) container
                        .getItem(container.getIdByIndex(i))
                        .getItemProperty(getGraphTimestampPropertyId(container))
                        .getValue();

                if (approximatedDate != null && approximatedDate.before(date)) {
                    index = approximateAbove ? i + 1 : i;
                    break;
                }
            }
        }

        return index;
    }

    private List<String> serializeMarkersToStrings(Date start, Date end) {
        List<String> marks = new ArrayList<String>();
        if (markers != null) {
            for (int i = 0; i < markers.size(); i++) {
                Object id = markers.getIdByIndex(i);
                Item item = markers.getItem(id);
                Date date = (Date) item.getItemProperty(
                        markerTimestampPropertyId).getValue();

                if ((date.after(start) || date.equals(start))
                        && (date.before(end) || date.equals(end))) {
                    Object caption = item.getItemProperty(
                            markerCaptionPropertyId).getValue();
                    Object description = item.getItemProperty(
                            markerValuePropertyId).getValue();

                    marks.add(String.valueOf(date.getTime()) + "_"
                            + String.valueOf(caption) + "_"
                            + String.valueOf(description));
                }
            }
        }
        return marks;
    }

    private List<String> serializeEventsToStrings(Date start, Date end) {
        List<String> evnts = new ArrayList<String>();
        if (events != null) {
            for (int i = 0; i < events.size(); i++) {
                Object id = events.getIdByIndex(i);
                Item item = events.getItem(id);
                Date date = (Date) item.getItemProperty(
                        eventTimestampPropertyId).getValue();

                if (date != null && (date.after(start) || date.equals(start))
                        && (date.before(end) || date.equals(end))) {
                    Object caption = item.getItemProperty(
                            eventCaptionPropertyId).getValue();

                    evnts.add(String.valueOf(date.getTime()) + "_"
                            + String.valueOf(caption) + "_" + i);
                }
            }
        }
        return evnts;
    }

    /**
     * Serializes the data points into provided values and items lists.
     * 
     * @param start
     *            The start date when the date range starts
     * @param end
     *            The end date when the date range ends
     * @param startIndex
     *            The index of the first date in the container
     * @param endIndex
     *            The index of the last date int the container
     * @param density
     *            The density of the points
     * @param cont
     *            The container where the search should be made
     * @param values
     *            The values list where the serialized values should be put
     * @param items
     *            The items list where found items should be put
     */
    private void serializeDataPointsTostrings(Date start, Date end,
            int startIndex, int endIndex, int density, Indexed cont,
            List<String> values, List<Item> items) {
        Date lastDate = null;
        // Date firstDate = null;
        Long lastIncrement = null;
        StringBuilder valueString = null;

        startIndex -= offscreenPoints;
        if (startIndex < 0) {
            startIndex = 0;
        }

        int containerSize = cont.size();
        endIndex += offscreenPoints;
        if (endIndex > containerSize) {
            endIndex = containerSize - 1;
        }

        // Get the points
        for (int i = startIndex; i <= endIndex; i++) {
            Object id = cont.getIdByIndex(i);
            Item item = cont.getItem(id);
            Date date = (Date) item.getItemProperty(
                    getGraphTimestampPropertyId(cont)).getValue();

            if (density <= 1 || i % density == 0 || i == startIndex
                    || i == endIndex) {

                // Add point
                String value = item
                        .getItemProperty(getGraphValueProperyId(cont))
                        .getValue().toString();

                valueString = new StringBuilder(value);

                if (lastDate == null) {
                    valueString.append("_");
                    valueString.append(String.valueOf(date.getTime()));
                } else {
                    // Use incremental dates. Add increment time if
                    // it changes
                    Long time = date.getTime() - lastDate.getTime();

                    if (time == 0L && duplicateHandler != null) {
                        /*
                         * Duplicate processing
                         */

                        // Remove previous value
                        values.remove(values.size() - 1);
                        items.remove(items.size() - 1);

                        // Scan forward for more duplicates
                        List<Number> duplicates = new ArrayList<Number>();
                        int j = i - 1;
                        while (j < cont.size()) {
                            Item itm = cont.getItem(cont.getIdByIndex(j));
                            Date d = (Date) itm.getItemProperty(
                                    getGraphTimestampPropertyId(cont))
                                    .getValue();
                            Long diff = d.getTime() - lastDate.getTime();
                            if (diff == 0) {
                                duplicates.add((Number) itm.getItemProperty(
                                        getGraphValueProperyId(cont))
                                        .getValue());
                                j++;
                            } else {
                                break;
                            }
                        }
                        i = j;

                        // Ask duplicate handler what to do
                        Number resolvedValue = duplicateHandler
                                .resolveDuplicates(date, duplicates);
                        if (resolvedValue != null) {
                            valueString = new StringBuilder(
                                    resolvedValue.toString());
                        } else {
                            // Null ignores the date altogether
                            continue;
                        }

                    } else if (time == 0L) {
                        getLogger()
                                .warning(
                                        "Duplicate points were found in the graph but no duplicateHandler set. Graph will not be accurate.");
                    }

                    if (!time.equals(lastIncrement)) {
                        valueString.append("_");
                        valueString.append(String.valueOf(time));
                        lastIncrement = time;
                    }
                }

                values.add(valueString.toString());
                lastDate = date;
                items.add(item);
            }
        }
    }

    private void initDataFlags() {
        initDone = true;
        sendSizes = true;
        sendGraphColors = true;
        sendGraphFillColors = true;
        sendTimeLimits = true;
        sendCaptions = true;
        sendUICaptions = true;
        sendDataPoints = true;
        sendGraphVisibilities = true;
        sendBrowserColors = true;
        sendBrowserFillColors = true;
        sendVUnit = true;
        sendClientCache = true;
        sendLineCaps = true;
        sendGraphThicknesses = true;
        sendComponentVisibilities = true;
        sendChartMode = true;
        sendGraphGrid = true;
        sendVerticalScaleLimits = true;
        sendZoomLevels = true;
        sendDateRange = true;
        sendVerticalAxisNumberFormat = true;

        // Start listening on data source changes
        listenToDatasourceChanges();
    }

    private void listenToDatasourceChanges() {
        for (TimelineDatasourceProperties ds : datasources) {
            Indexed dataSource = ds.getDatasource();

            // Add data source listeners if necessery
            // Listen to datasource changes if possible
            if (dataSource instanceof ItemSetChangeNotifier) {
                getLogger().finest(
                        "Attaching ItemSetChangeListener to graph datasource");
                ItemSetChangeNotifier isn = (ItemSetChangeNotifier) dataSource;

                // Ensure we not already are listening to the events
                isn.removeListener(dataSourceListener);

                // Add listener
                isn.addListener(dataSourceListener);
            }
            if (dataSource instanceof PropertySetChangeNotifier) {
                getLogger()
                        .finest("Attaching PropertySetChangeListener to graph datasource");
                PropertySetChangeNotifier pscn = (PropertySetChangeNotifier) dataSource;

                // Ensure we not already are listening to the events
                pscn.removeListener(dataSourceListener);

                // Add listener
                pscn.addListener(dataSourceListener);
            }
            if (dataSource instanceof ValueChangeNotifier) {
                getLogger().finest(
                        "Attaching ValueChangeListener to graph datasource");
                ValueChangeNotifier vcn = (ValueChangeNotifier) dataSource;

                // Ensure we not already are listening to the events
                vcn.removeListener(dataSourceListener);

                // Add listener
                vcn.addListener(dataSourceListener);
            }
        }

        if (markers instanceof ItemSetChangeNotifier) {
            getLogger().finest(
                    "Attaching ItemSetChangeListener to marker datasource");
            ItemSetChangeNotifier isn = (ItemSetChangeNotifier) markers;

            // Ensure we not already are listening to the events
            isn.removeListener(markerEventListener);

            // Add listener
            isn.addListener(markerEventListener);
        }
        if (markers instanceof PropertySetChangeNotifier) {
            getLogger().finest(
                    "Attaching PropertySetChangeListener to marker datasource");
            PropertySetChangeNotifier pscn = (PropertySetChangeNotifier) markers;

            // Ensure we not already are listening to the events
            pscn.removeListener(markerEventListener);

            // Add listener
            pscn.addListener(markerEventListener);
        }
        if (markers instanceof ValueChangeNotifier) {
            getLogger().finest(
                    "Attaching ValueChangeListener to marker datasource");
            ValueChangeNotifier vcn = (ValueChangeNotifier) markers;

            // Ensure we not already are listening to the events
            vcn.removeListener(markerEventListener);

            // Add listener
            vcn.addListener(markerEventListener);
        }

        if (events instanceof ItemSetChangeNotifier) {
            getLogger().finest(
                    "Attaching ValueChangeListener to marker datasource");
            ItemSetChangeNotifier isn = (ItemSetChangeNotifier) events;

            // Ensure we not already are listening to the events
            isn.removeListener(markerEventListener);

            // Add listener
            isn.addListener(markerEventListener);
        }
        if (events instanceof PropertySetChangeNotifier) {
            getLogger().finest(
                    "Attaching ValueChangeListener to marker datasource");
            PropertySetChangeNotifier pscn = (PropertySetChangeNotifier) events;

            // Ensure we not already are listening to the events
            pscn.removeListener(markerEventListener);

            // Add listener
            pscn.addListener(markerEventListener);
        }
        if (events instanceof ValueChangeNotifier) {
            getLogger().finest(
                    "Attaching ValueChangeListener to marker datasource");
            ValueChangeNotifier vcn = (ValueChangeNotifier) events;

            // Ensure we not already are listening to the events
            vcn.removeListener(markerEventListener);

            // Add listener
            vcn.addListener(markerEventListener);
        }
    }

    /**
     * Should the graphs be stacked on top of each other or painted over.
     * 
     * @param stacked
     *            Should the graphs be stacked
     */
    public void setGraphStacking(boolean stacked) {
        this.graphStacking = stacked;
        if (initDone) {
            this.dirty = true;
            this.sendRefreshRequest = true;
            this.sendVerticalScaleLimits = true;
            requestRepaint();
        }
    }

    /**
     * Are graphs stacked on top of each other
     * 
     * @return
     */
    public boolean isGraphStacked() {
        return graphStacking;
    }

    /**
     * Gets an object describing the date formats.
     * 
     * To use your own date formats retrieve the formats using this method and
     * use the setters to customize the date formats.
     * 
     * @return
     */
    public DateFormatInfo getDateFormats() {
        return this.dateFormatInfo;
    }

    /**
     * Returns the upper limit of the vertical scale if it has been set. If it
     * is not set then NULL is returned.
     * 
     * @return
     */
    public Float getVerticalAxisMaximum() {
        return verticalScaleMaximum;
    }

    /**
     * Returns the lower limit of the vertical scale if it has been set. If it
     * is not set then NULL is returned.
     * 
     * @return
     */
    public Float getVerticalAxisMinimum() {
        return verticalScaleMinimum;
    }

    /**
     * Assigns the number format used in the vertical axis.
     * 
     * For more information about what formats are supported see <a href=
     * "http://google-web-toolkit.googlecode.com/svn/javadoc/latest/com/google/gwt/i18n/client/NumberFormat.html"
     * >GWT NumberFormat documentation</a>.
     */
    public void setVerticalAxisNumberFormat(String format) {
        verticalAxisNumberFormat = format;
        if (initDone) {
            sendVerticalAxisNumberFormat = true;
            requestRepaint();
        }
    }

    /**
     * Get the currently used number format in the vertical axis.
     */
    public String getVerticalAxisNumberFormat() {
        return verticalAxisNumberFormat;
    }

    /**
     * Sets at what distances the vertical grid lines should be drawn.
     * 
     * Set empty list to remove grid lines or NULL to automatically calculate
     * the grid lines using the formula max/5*(1 to 4).
     * 
     * @param values
     *            The values where the grid lines should be drawn
     */
    public void setVerticalGridLines(float... values) {
        verticalGridLines = values;
        if (initDone) {
            sendVerticalGridLines = true;
            requestRepaint();
        }
    }

    /**
     * Gets the values for the drawn grid lines. Null if automitic grid lines
     * are being used (default).
     * 
     * @return
     */
    public float[] getVerticalGridLines() {
        return verticalGridLines;
    }

    /**
     * Should the graphs have drop shadows
     * 
     * @param enabled
     *            Turn drop shadows on by setting this to true, turn them of
     *            using false
     */
    public void setGraphShadowsEnabled(boolean enabled) {
        graphShadowsEnabled = enabled;
        if (initDone) {
            this.dirty = true;
            requestRepaint();
        }
    }

    /**
     * Are drop shadows for the graphs enabled
     * 
     * @return
     */
    public boolean isGraphShadowsEnabled() {
        return graphShadowsEnabled;
    }

    /**
     * Returns the first date which is visible in the graph
     * 
     * @return
     */
    public Date getVisibleSelectionStart() {
        return selectedStartDate;
    }

    /**
     * Returns the last date which is visible in the graph
     * 
     * @return
     */
    public Date getVisibleSelectionEnd() {
        return selectedEndDate;
    }

    /**
     * To gain smoother transitions when browsing the graph and less HTTP
     * requests some points are rendered off-screen so they are available when
     * the graph time range is changed.
     * 
     * @param amountOfPoints
     *            The amount of off-screen points to add before and after the
     *            actual rendering. By default 100 points are used.
     */
    public void setGraphBufferSize(int amountOfPoints) {
        offscreenPoints = amountOfPoints;
    }

    /**
     * Returns the graph data sources added to the Timeline
     * 
     * @return
     */
    public List<Indexed> getGraphDatasources() {
        List<Indexed> graphs = new ArrayList<Container.Indexed>();
        for (TimelineDatasourceProperties tdp : datasources) {
            graphs.add(tdp.getDatasource());
        }
        return Collections.unmodifiableList(graphs);
    }

    /**
     * When displaying non-uniform data as a bar chart the bar widths will vary
     * depending on the data distribution. To make the bars equal size set this
     * to true. By default uniform bar thickness is disabled.
     * 
     * @param enabled
     *            When turned on the bars in the bar chart all have the same
     *            thickness
     */
    public void setUniformBarThicknessEnabled(boolean enabled) {
        if (uniformBarThickness != enabled) {
            uniformBarThickness = enabled;
            if (initDone) {
                this.dirty = true;
                requestRepaint();
            }
        }
    }

    /**
     * When displaying non-uniform data as a bar chart the bar widths will vary
     * depending on the data distribution. To make the bars equal size set this
     * to true. By default uniform bar thickness is disabled.
     * 
     * @return
     */
    public boolean isUniformBarThicknessEnabled() {
        return uniformBarThickness;
    }

    /**
     * When displaying data where duplicates points are defined for the same
     * date-time then a duplicate handler is called to resolve the value for
     * that date. The duplicate handler should return the value which should be
     * displayed in the graph. Returning null will remove the point altogether
     * from the graph. By default no duplicate handler is present.
     * 
     * @param handler
     *            THe handler to handle duplicate points
     */
    public void setDuplicateHandler(DuplicateHandler handler) {
        this.duplicateHandler = handler;
    }

    /**
     * When displaying data where duplicates points are defined for the same
     * date-time then a duplicate handler is called to resolve the value for
     * that date. The duplicate handler should return the value which should be
     * displayed in the graph. Returning null will remove the point altogether
     * from the graph. By default no duplicate handler is present.
     * 
     */
    public DuplicateHandler getDuplicateHandler() {
        return duplicateHandler;
    }
}
