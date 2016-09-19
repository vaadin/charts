package book.examples.chart.types;


import java.util.Collection;
import java.util.Date;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.Background;
import com.vaadin.addon.charts.model.BoxPlotItem;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataLabelsFunnel;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.FlagItem;
import com.vaadin.addon.charts.model.FlagShape;
import com.vaadin.addon.charts.model.HeatSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Level;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.MarkerSymbolEnum;
import com.vaadin.addon.charts.model.MarkerSymbolUrl;
import com.vaadin.addon.charts.model.OhlcItem;
import com.vaadin.addon.charts.model.Pane;
import com.vaadin.addon.charts.model.PlotBand;
import com.vaadin.addon.charts.model.PlotOptionsBoxplot;
import com.vaadin.addon.charts.model.PlotOptionsBubble;
import com.vaadin.addon.charts.model.PlotOptionsErrorbar;
import com.vaadin.addon.charts.model.PlotOptionsFlags;
import com.vaadin.addon.charts.model.PlotOptionsFunnel;
import com.vaadin.addon.charts.model.PlotOptionsHeatmap;
import com.vaadin.addon.charts.model.PlotOptionsOhlc;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.PlotOptionsPolygon;
import com.vaadin.addon.charts.model.PlotOptionsScatter;
import com.vaadin.addon.charts.model.PlotOptionsSolidgauge;
import com.vaadin.addon.charts.model.PlotOptionsTreemap;
import com.vaadin.addon.charts.model.PlotOptionsWaterfall;
import com.vaadin.addon.charts.model.RangeSeries;
import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.TickmarkPlacement;
import com.vaadin.addon.charts.model.TreeMapLayoutAlgorithm;
import com.vaadin.addon.charts.model.TreeSeries;
import com.vaadin.addon.charts.model.TreeSeriesItem;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.WaterFallSum;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.util.IndexedContainer;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ChartTypes {
    public void chartTypesErrorbarSnippet1() {
        // Create a chart of some primary type
        Chart chart = new Chart(ChartType.SCATTER);
        chart.setWidth("600px");
        chart.setHeight("400px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Average Temperatures in Turku");
        conf.getLegend().setEnabled(false);

// The primary data series
        ListSeries averages = new ListSeries(
                -6, -6.5, -4, 3, 9, 14, 17, 16, 11, 6, 2, -2.5);

// Error bar data series with low and high values
        DataSeries errors = new DataSeries();
        errors.add(new DataSeriesItem(0,  -9, -3));
        errors.add(new DataSeriesItem(1, -10, -3));
        errors.add(new DataSeriesItem(2,  -8,  1));

// Configure the stem and whiskers in error bars
        PlotOptionsErrorbar barOptions = new PlotOptionsErrorbar();
        barOptions.setStemColor(SolidColor.GREY);
        barOptions.setStemWidth(2);
        barOptions.setStemDashStyle(DashStyle.DASH);
        barOptions.setWhiskerColor(SolidColor.BROWN);
        barOptions.setWhiskerLength(80, Sizeable.Unit.PERCENTAGE); // 80% of category width
        barOptions.setWhiskerWidth(2); // Pixels
        errors.setPlotOptions(barOptions);

// The errors should be drawn lower
        conf.addSeries(errors);
        conf.addSeries(averages);
    }

    public void chartTypesBoxplotSnippet1() {
        Chart chart = new Chart(ChartType.BOXPLOT);
        chart.setWidth("400px");
        chart.setHeight("300px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Orienteering Split Times");
        conf.getLegend().setEnabled(false);
    }

    public void chartTypesBoxplotPlotoptionsSnippet1() {
        Chart chart = new Chart(ChartType.BOXPLOT);
        Configuration conf = chart.getConfiguration();
        // Set median line color and thickness
        PlotOptionsBoxplot plotOptions = new PlotOptionsBoxplot();
        plotOptions.setMedianColor(SolidColor.BLUE);
        plotOptions.setMedianWidth(3);
        conf.setPlotOptions(plotOptions);
    }

    public void chartTypesBoxplotPlotoptionsSnippet2() {
        Chart chart = new Chart(ChartType.BOXPLOT);
        Configuration conf = chart.getConfiguration();
        // Orienteering control point times for runners
        double data[][]=new double[5][5];

        DataSeries series = new DataSeries();
        for (double cpointtimes[]: data) {
            StatAnalysis analysis = new StatAnalysis(cpointtimes);
            series.add(new BoxPlotItem(analysis.low(),
                    analysis.firstQuartile(),
                    analysis.median(),
                    analysis.thirdQuartile(),
                    analysis.high()));
        }
        conf.setSeries(series);
    }

    public void chartTypesScatterSnippet1() {
        Chart chart = new Chart(ChartType.SCATTER);
        chart.setWidth("500px");
        chart.setHeight("500px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Random Sphere");
        conf.getLegend().setEnabled(false); // Disable legend

        PlotOptionsScatter options = new PlotOptionsScatter();
// ... Give overall plot options here ...
        conf.setPlotOptions(options);

        DataSeries series = new DataSeries();
        for (int i=0; i<300; i++) {
            double lng = Math.random() * 2 * Math.PI;
            double lat = Math.random() * Math.PI - Math.PI/2;
            double x   = Math.cos(lat) * Math.sin(lng);
            double y   = Math.sin(lat);
            double z   = Math.cos(lng) * Math.cos(lat);

            DataSeriesItem point = new DataSeriesItem(x,y);
            Marker marker = new Marker();
            // Make settings as described later
            point.setMarker(marker);
            series.add(point);
        }
        conf.addSeries(series);
    }

    public void chartTypesScatterMarkersSnippet1() {
        double x=1.0;
        double y=1.2;
        DataSeriesItem point = new DataSeriesItem(x,y);
        Marker marker = new Marker();
// ... Make any settings ...
        point.setMarker(marker);
        DataSeries series = new DataSeries();
        series.add(point);
    }

    public void chartTypesScatterMarkerPropertiesSnippet1() {
        double x=1.0;
        double y=1.2;
        double z = 1.45;
        DataSeries series = new DataSeries();
        DataSeriesItem point = new DataSeriesItem(x,y);
        Marker marker = new Marker();
        // Set line width and color
        marker.setLineWidth(1); // Normally zero width
        marker.setLineColor(SolidColor.BLACK);

// Set RGB fill color
        int level = (int) Math.round((1-z)*127);
        marker.setFillColor(
                new SolidColor(255-level, 0, level));
        point.setMarker(marker);
        marker.setRadius((z+1)*5);
        series.add(point);
    }
    public void chartTypesScatterMarkerSymbolsSnippet1() {
        Marker marker = new Marker();
        marker.setSymbol(MarkerSymbolEnum.DIAMOND);
    }
    public void chartTypesScatterMarkerSymbolsSnippet2() {
        Marker marker = new Marker();
        String url = VaadinServlet.getCurrent().getServletContext()
                .getContextPath() + "/VAADIN/themes/mytheme/img/smiley.png";
        marker.setSymbol(new MarkerSymbolUrl(url));
    }
    public void chartTypesBubbleSnippet1() {
        // Create a bubble chart
        Chart chart = new Chart(ChartType.BUBBLE);
        chart.setWidth("640px"); chart.setHeight("350px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Champagne Consumption by Country");
        conf.getLegend().setEnabled(false); // Disable legend
        conf.getTooltip().setFormatter("this.point.name + ': ' + " +
                "Math.round(100*(this.point.z * this.point.z))/100.0 + " +
                "' M bottles'");

// World map as background
        String url = VaadinServlet.getCurrent().getServletContext()
                .getContextPath() + "/VAADIN/themes/mytheme/img/map.png";
        conf.getChart().setPlotBackgroundImage(url);

// Show more bubbly bubbles with spherical color gradient
        PlotOptionsBubble plotOptions = new PlotOptionsBubble();
        Marker marker = new Marker();
        GradientColor color = GradientColor.createRadial(0.4, 0.3, 0.7);
        color.addColorStop(0.0, new SolidColor(255, 255, 255, 0.5));
        color.addColorStop(1.0, new SolidColor(170, 70, 67, 0.5));
        marker.setFillColor(color);
        plotOptions.setMarker(marker);
        conf.setPlotOptions(plotOptions);

// Source: CIVC - Les expeditions de vins de Champagne en 2011
        DataSeries series = new DataSeries("Countries");
        Object data[][] = {
                {"France",         181.6},
                {"United Kingdom",  34.53},
                {"United States",   19.37}
        };
        for (Object[] country: data) {
            String name = (String) country[0];
            double amount = (Double) country[1];
            Coordinate pos = new Coordinate();

            DataSeriesItem3d item = new DataSeriesItem3d();
            item.setX(pos.longitude * Math.cos(pos.latitude/2.0 *
                    (Math.PI/160)));
            item.setY(pos.latitude * 1.2);
            item.setZ(Math.sqrt(amount));
            item.setName(name);
            series.add(item);
        }
        conf.addSeries(series);

// Set the category labels on the axis correspondingly
        XAxis xaxis = new XAxis();
        xaxis.setExtremes(-180, 180);
        conf.addxAxis(xaxis);

// Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setExtremes(-90, 90);
        conf.addyAxis(yaxis);
    }
    public void chartTypesPieSnippet1() {
        Chart chart =new Chart();
        Configuration conf = chart.getConfiguration();
        PlotOptionsPie options = new PlotOptionsPie();
        options.setInnerSize("0"); // Non-0 results in a donut
        options.setSize("75%");  // Default
        options.setCenter("50%", "50%"); // Default
        conf.setPlotOptions(options);
    }
    public void chartTypesPieDataSnippet1() {
        Chart chart =new Chart();
        Configuration conf = chart.getConfiguration();
        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Mercury", 4900));
        series.add(new DataSeriesItem("Venus", 12100));
        conf.addSeries(series);
    }
    public void chartTypesPieDataSnippet2() {
        DataSeriesItem earth = new DataSeriesItem("Earth", 12800);
        earth.setSliced(true);
        DataSeries series = new DataSeries();
        series.add(earth);
    }
    public void chartTypesPieDonutSnippet1() {
        PlotOptionsPie options = new PlotOptionsPie();
        Chart chart =new Chart();
        Configuration conf = chart.getConfiguration();
        options.setInnerSize("60%");
        conf.setPlotOptions(options);
    }
    public void chartTypesPieDonutSnippet2() {
        // The inner pie
        DataSeries innerSeries = new DataSeries();
        innerSeries.setName("Browsers");
        PlotOptionsPie innerPieOptions = new PlotOptionsPie();
        innerPieOptions.setSize("60%");
        innerSeries.setPlotOptions(innerPieOptions);

        DataSeries outerSeries = new DataSeries();
        outerSeries.setName("Versions");
        PlotOptionsPie outerSeriesOptions = new PlotOptionsPie();
        outerSeriesOptions.setInnerSize("60%");
        outerSeries.setPlotOptions(outerSeriesOptions);
    }
    public void chartTypesGaugeSnippet1() {
        Chart chart = new Chart(ChartType.GAUGE);
        chart.setWidth("400px");
        chart.setHeight("400px");
    }
    public void chartTypesGaugeConfigurationSnippet1() {
        Chart chart=new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Speedometer");
        conf.getPane().setStartAngle(-135);
        conf.getPane().setEndAngle(135);
    }

    public void chartTypesGaugeAxisSnippet1() {
        Chart chart=new Chart();
        Configuration conf = chart.getConfiguration();
        YAxis yaxis = new YAxis();
        yaxis.setTitle("km/h");

// The limits are mandatory
        yaxis.setMin(0);
        yaxis.setMax(100);

// Other configuration
        yaxis.getLabels().setStep(1);
        yaxis.setTickInterval(10);
        yaxis.setTickLength(10);
        yaxis.setTickWidth(1);
        yaxis.setMinorTickInterval("1");
        yaxis.setMinorTickLength(5);
        yaxis.setMinorTickWidth(1);
        yaxis.setPlotBands(new PlotBand[]{
                new PlotBand(0,  60,  SolidColor.GREEN),
                new PlotBand(60, 80,  SolidColor.YELLOW),
                new PlotBand(80, 100, SolidColor.RED)});
        yaxis.setGridLineWidth(0); // Disable grid

        conf.addyAxis(yaxis);
    }

    public void chartTypesGaugeDataSnippet1() {
        Chart chart=new Chart();
        Configuration conf = chart.getConfiguration();
        ListSeries series = new ListSeries("Speed", 80);
        conf.addSeries(series);
    }
    public void chartTypesGaugeDataSnippet2() {
        VerticalLayout layout = new VerticalLayout();
        final ListSeries series = new ListSeries("Speed", 80);
        final TextField tf = new TextField("Enter a new value");
        layout.addComponent(tf);

        Button update = new Button("Update", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Integer newValue = new Integer(tf.getValue());
                series.updatePoint(0, newValue);;
            }
        });
        layout.addComponent(update);
    }

    public void chartTypesSolidGaugeSnippet1() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        chart.setWidth("400px");
        chart.setHeight("400px");
    }
    public void chartTypesSolidGaugeConfSnippet1() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Solid Gauge");

        Pane pane = conf.getPane();
        pane.setSize("125%");           // For positioning tick labels
        pane.setCenter("50%", "70%"); // Move center lower
        pane.setStartAngle(-90);        // Make semi-circle
        pane.setEndAngle(90);           // Make semi-circle
    }
    public void chartTypesSolidGaugeConfSnippet2() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        Configuration conf = chart.getConfiguration();

        Pane pane = conf.getPane();
        Background bkg = new Background();
        bkg.setBackgroundColor(new SolidColor("#eeeeee")); // Gray
        bkg.setInnerRadius("60%");  // To make it an arc and not circle
        bkg.setOuterRadius("100%"); // Default - not necessary
        bkg.setShape("arc");        // solid or arc
        pane.setBackground(bkg);
    }

    public void chartTypesSolidGaugeAxisSnippet1() {
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Pressure GPa");
        yaxis.getTitle().setY(-80); // Move 70 px upwards from center

// The limits are mandatory
        yaxis.setMin(0);
        yaxis.setMax(200);

// Configure ticks and labels
        yaxis.setTickInterval(100);  // At 0, 100, and 200
        yaxis.getLabels().setY(-16); // Move 16 px upwards
        yaxis.setGridLineWidth(0); // Disable grid
    }

    public void chartTypesSolidGaugeAxisSnippet2() {
        YAxis yaxis = new YAxis();
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        Configuration conf = chart.getConfiguration();
        yaxis.setStops(new Stop(0.1f, SolidColor.GREEN),
                new Stop(0.5f, SolidColor.YELLOW),
                new Stop(0.9f, SolidColor.RED));

        conf.addyAxis(yaxis);
    }

    public void chartTypesSolidGaugePlotoptionsSnippet1() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        Configuration conf = chart.getConfiguration();
        PlotOptionsSolidgauge options = new PlotOptionsSolidgauge();

// Move the value display box at the center a bit higher
        DataLabels dataLabels = new DataLabels();
        dataLabels.setY(-20);
        options.setDataLabels(dataLabels);

        conf.setPlotOptions(options);
    }

    public void chartTypesSolidGaugeDataSnippet1() {
        Chart chart = new Chart(ChartType.SOLIDGAUGE);
        Configuration conf = chart.getConfiguration();
        ListSeries series = new ListSeries("Pressure MPa", 80);
        conf.addSeries(series);
    }

    public void chartTypesSolidGaugeDataSnippet2() {
        VerticalLayout layout = new VerticalLayout();
        final ListSeries series = new ListSeries("Pressure MPa", 80);
        final TextField tf = new TextField("Enter a new value");
        layout.addComponent(tf);

        Button update = new Button("Update", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Integer newValue = new Integer(tf.getValue());
                series.updatePoint(0, newValue);
            }
        });
        layout.addComponent(update);
    }

    public void chartTypesRangeChartsSnippet1() {
        Chart chart = new Chart(ChartType.AREARANGE);
        chart.setWidth("400px");
        chart.setHeight("300px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Extreme Temperature Range in Finland");
// Create the range series
// Source: http://ilmatieteenlaitos.fi/lampotilaennatyksia
        RangeSeries series = new RangeSeries("Temperature Extremes",
                new Double[]{-51.5,10.9},
                new Double[]{-49.0,11.8},
        new Double[]{-47.0,10.8});//
        conf.addSeries(series);
    }

    public void chartTypePolarSnippet1() {
        // Create a chart of some type
        Chart chart = new Chart(ChartType.LINE);

        // Enable the polar projection
        Configuration conf = chart.getConfiguration();
        conf.getChart().setPolar(true);
    }

    public void chartTypePolarSnippet2() {
        Chart chart = new Chart(ChartType.LINE);

        // Enable the polar projection
        Configuration conf = chart.getConfiguration();
        // Define the sector of the polar projection
        Pane pane = new Pane(0, 360); // Full circle
        conf.addPane(pane);

// Define the X axis and set its value range
        XAxis axis = new XAxis();
        axis.setMin(0);
        axis.setMax(360);
    }

    public void chartTypeSpiderWebSnippet1() {Chart chart = new Chart(ChartType.LINE);

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.getChart().setPolar(true);

// Create the range series
// Source: http://ilmatieteenlaitos.fi/lampotilaennatyksia
        ListSeries series = new ListSeries("Temperature Extremes",
                10.9, 11.8, 17.5, 25.5, 31.0, 33.8,
                37.2, 33.8, 28.8, 19.4, 14.1, 10.8);
        conf.addSeries(series);

// Set the category labels on the X axis correspondingly
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Jan", "Feb", "Mar",
                "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                "Oct", "Nov", "Dec");
        xaxis.setTickmarkPlacement(TickmarkPlacement.ON);
        xaxis.setLineWidth(0);
        conf.addxAxis(xaxis);

// Configure the Y axis
        YAxis yaxis = new YAxis();
        yaxis.setGridLineInterpolation("polygon"); // Webby look
        yaxis.setMin(0);
        yaxis.setTickInterval(10);
        yaxis.getLabels().setStep(1);
        conf.addyAxis(yaxis);
    }

    public void chartTypesFunnel() {
        Chart chart = new Chart(ChartType.FUNNEL);
        chart.setWidth("500px");
        chart.setHeight("350px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Monster Utilization");
        conf.getLegend().setEnabled(false);

// Give more room for the labels
        conf.getChart().setSpacingRight(120);

// Configure the funnel neck shape
        PlotOptionsFunnel options = new PlotOptionsFunnel();
        options.setNeckHeight(20, Sizeable.Unit.PERCENTAGE);
        options.setNeckWidth(20, Sizeable.Unit.PERCENTAGE);

// Style the data labels
        DataLabelsFunnel dataLabels = new DataLabelsFunnel();
        dataLabels.setFormat("<b>{point.name}</b> ({point.y:,.0f})");
        dataLabels.setSoftConnector(false);
        dataLabels.setColor(SolidColor.BLACK);
        options.setDataLabels(dataLabels);

        conf.setPlotOptions(options);

// Create the range series
        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Monsters Met", 340));
        series.add(new DataSeriesItem("Engaged", 235));
        series.add(new DataSeriesItem("Killed", 187));
        series.add(new DataSeriesItem("Tinned", 70));
        series.add(new DataSeriesItem("Eaten", 55));
        conf.addSeries(series);
    }
    public void chartTypesWaterfall() {
        Chart chart = new Chart(ChartType.WATERFALL);
        chart.setWidth("500px");
        chart.setHeight("350px");

// Modify the default configuration a bit
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Changes in Reindeer Population in 2011");
        conf.getLegend().setEnabled(false);

// Configure X axis
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Start", "Predators", "Slaughter",
                "Reproduction", "End");
        conf.addxAxis(xaxis);

// Configure Y axis
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Population (thousands)");
        conf.addyAxis(yaxis);
    }

    public void chartTypesWaterfallPlotoptions() {
        // Define the colors
        Chart chart=new Chart();
        Configuration conf = chart.getConfiguration();
        final Color balanceColor = SolidColor.BLACK;
        final Color positiveColor = SolidColor.BLUE;
        final Color negativeColor = SolidColor.RED;

// Configure the colors
        PlotOptionsWaterfall options = new PlotOptionsWaterfall();
        options.setUpColor(positiveColor);
        options.setColor(negativeColor);

// Configure the labels
        DataLabels labels = new DataLabels(true);
        labels.setVerticalAlign(VerticalAlign.TOP);
        labels.setY(-20);
        labels.setFormatter("Math.floor(this.y/1000) + 'k'");
        Style style = new Style();
        style.setColor(SolidColor.BLACK);
        style.setFontWeight(FontWeight.BOLD);
        labels.setStyle(style);
        options.setDataLabels(labels);
        options.setPointPadding(0);
        conf.setPlotOptions(options);
    }

    public void chartTypesWaterfallDataModel() {
        // Define the colors
        Chart chart=new Chart();
        Configuration conf = chart.getConfiguration();
        // The data
        DataSeries series = new DataSeries();

// The beginning balance
        DataSeriesItem start = new DataSeriesItem("Start", 306503);
        SolidColor balanceColor=SolidColor.KHAKI;
        start.setColor(balanceColor);
        series.add(start);

// Deltas
        series.add(new DataSeriesItem("Predators", -3330));
        series.add(new DataSeriesItem("Slaughter", -103332));
        series.add(new DataSeriesItem("Reproduction", +104052));

        WaterFallSum end = new WaterFallSum("End");
        end.setColor(balanceColor);
        end.setIntermediate(false); // Not intermediate (default)
        series.add(end);

        conf.addSeries(series);
    }

    public void chartTypesHeatMap() {
        Chart chart = new Chart(ChartType.HEATMAP);
        chart.setWidth("600px");
        chart.setHeight("300px");

        Configuration conf = chart.getConfiguration();
        conf.setTitle("Heat Data");

// Set colors for the extremes
        conf.getColorAxis().setMinColor(SolidColor.AQUA);
        conf.getColorAxis().setMaxColor(SolidColor.RED);

// Set up border and data labels
        PlotOptionsHeatmap plotOptions = new PlotOptionsHeatmap();
        plotOptions.setBorderColor(SolidColor.WHITE);
        plotOptions.setBorderWidth(2);
        plotOptions.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plotOptions);

// Create some data
        HeatSeries series = new HeatSeries();
        series.addHeatPoint( 0, 0,  10.9); // Jan High
        series.addHeatPoint( 0, 1, -51.5); // Jan Low
        series.addHeatPoint( 1, 0,  11.8); // Feb High
        series.addHeatPoint(11, 1, -47.0); // Dec Low
        conf.addSeries(series);

// Set the category labels on the X axis
        XAxis xaxis = new XAxis();
        xaxis.setTitle("Month");
        xaxis.setCategories("Jan", "Feb", "Mar",
                "Apr", "May", "Jun", "Jul", "Aug", "Sep",
                "Oct", "Nov", "Dec");
        conf.addxAxis(xaxis);

// Set the category labels on the Y axis
        YAxis yaxis = new YAxis();
        yaxis.setTitle("");
        yaxis.setCategories("High C", "Low C");
        conf.addyAxis(yaxis);
    }

    public void chartTypesTreemap() {
        Chart chart = new Chart();

        PlotOptionsTreemap plotOptions = new PlotOptionsTreemap();
        plotOptions.setLayoutAlgorithm(TreeMapLayoutAlgorithm.STRIPES);
        plotOptions.setAlternateStartingDirection(true);

        Level level = new Level();
        level.setLevel(1);
        level.setLayoutAlgorithm(TreeMapLayoutAlgorithm.SLICEANDDICE);

        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(true);
        dataLabels.setAlign(HorizontalAlign.LEFT);
        dataLabels.setVerticalAlign(VerticalAlign.TOP);

        Style style = new Style();
        style.setFontSize("15px");
        style.setFontWeight(FontWeight.BOLD);

        dataLabels.setStyle(style);
        level.setDataLabels(dataLabels);
        plotOptions.setLevels(level);

        TreeSeries series = new TreeSeries();

        TreeSeriesItem apples = new TreeSeriesItem("A", "Apples");
        apples.setColor(new SolidColor("#EC2500"));

        TreeSeriesItem bananas = new TreeSeriesItem("B", "Bananas");
        bananas.setColor(new SolidColor("#ECE100"));

        TreeSeriesItem oranges = new TreeSeriesItem("O", "Oranges");
        oranges.setColor(new SolidColor("#EC9800"));

        TreeSeriesItem anneA = new TreeSeriesItem("Anne", apples, 5);
        TreeSeriesItem rickA = new TreeSeriesItem("Rick", apples, 3);
        TreeSeriesItem paulA = new TreeSeriesItem("Paul", apples, 4);

        TreeSeriesItem anneB = new TreeSeriesItem("Anne", bananas, 4);
        TreeSeriesItem rickB = new TreeSeriesItem("Rick", bananas, 10);
        TreeSeriesItem paulB = new TreeSeriesItem("Paul", bananas, 1);

        TreeSeriesItem anneO = new TreeSeriesItem("Anne", oranges, 1);
        TreeSeriesItem rickO = new TreeSeriesItem("Rick", oranges, 3);
        TreeSeriesItem paulO = new TreeSeriesItem("Paul", oranges, 3);

        TreeSeriesItem susanne = new TreeSeriesItem("Susanne", 2);
        susanne.setParent("Kiwi");
        susanne.setColor(new SolidColor("#9EDE00"));

        series.addAll(apples, bananas, oranges, anneA, rickA, paulA,
                anneB, rickB, paulB, anneO, rickO, paulO, susanne);

        series.setPlotOptions(plotOptions);

        chart.getConfiguration().addSeries(series);

        chart.getConfiguration().setTitle("Fruit consumption");
    }

    public void chartTypesPolygon() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Height vs Weight");

        XAxis xAxis = conf.getxAxis();
        xAxis.setStartOnTick(true);
        xAxis.setEndOnTick(true);
        xAxis.setShowLastLabel(true);
        xAxis.setTitle("Height (cm)");

        YAxis yAxis = conf.getyAxis();
        yAxis.setTitle("Weight (kg)");

        PlotOptionsScatter optionsScatter = new PlotOptionsScatter();
        DataSeries scatter = new DataSeries();
        scatter.setPlotOptions(optionsScatter);
        scatter.setName("Observations");

        scatter.add(new DataSeriesItem(160, 67));
        scatter.add(new DataSeriesItem(180, 75));
        conf.addSeries(scatter);

        DataSeries polygon = new DataSeries();
        PlotOptionsPolygon optionsPolygon = new PlotOptionsPolygon();
        optionsPolygon.setEnableMouseTracking(false);
        polygon.setPlotOptions(optionsPolygon);
        polygon.setName("Target");

        polygon.add(new DataSeriesItem(153, 42));
        polygon.add(new DataSeriesItem(149, 46));
        polygon.add(new DataSeriesItem(173, 52));
        polygon.add(new DataSeriesItem(166, 45));
        conf.addSeries(polygon);
    }

    public void chartTypesFlagsExample() {
        Chart chart = new Chart(ChartType.SPLINE);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("USD to EUR exchange rate");
        configuration.getxAxis().setType(AxisType.DATETIME);

// A data series to annotate with flags
        DataSeries dataSeries = new DataSeries();
        dataSeries.setId("dataseries");
        dataSeries.addData(new Number[][] { { 1434499200000l, 0.8821 },
                { 1434585600000l, 0.8802 }, { 1434672000000l, 0.8808 },
                { 1434844800000l, 0.8794 }, { 1434931200000l, 0.8818 },
                { 1435017600000l, 0.8952 }, { 1435104000000l, 0.8924 },
                { 1435190400000l, 0.8925 }, { 1435276800000l, 0.8955 } });

// Flags on the data series
        DataSeries flagsOnSeries = new DataSeries();
        flagsOnSeries.setName("Flags on series");
        PlotOptionsFlags plotOptionsFlags = new PlotOptionsFlags();
        plotOptionsFlags.setShape(FlagShape.SQUAREPIN);
        plotOptionsFlags.setOnSeries("dataseries");
        flagsOnSeries.setPlotOptions(plotOptionsFlags);
        flagsOnSeries.add(new FlagItem(1434585600000l, "First Series Flag",
                "First Series Flag Tooltip Text"));
        flagsOnSeries.add(new FlagItem(1435017600000l, "Second Series Flag"));

// Flags on the X axis
        DataSeries flagsOnAxis = new DataSeries();
        flagsOnAxis.setPlotOptions(new PlotOptionsFlags());
        flagsOnAxis.setName("Flags on axis");
        flagsOnAxis.add(new FlagItem(1434844800000l, "First Axis Flag",
                "First Axis Flag Tooltip Text"));
        flagsOnAxis.add(new FlagItem(1435190400000l, "Second Axis Flag"));

        configuration.setSeries(dataSeries, flagsOnSeries, flagsOnAxis);
    }

    public void chartTypesOhlcSnippet1() {
        Chart chart = new Chart(ChartType.OHLC);
        chart.setTimeline(true);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("AAPL Stock Price");
        DataSeries dataSeries = new DataSeries();
        Collection<OhlcData> dataBank= null;
        for (OhlcData data : dataBank) {
            OhlcItem item = new OhlcItem();
            item.setX(data.getDate());
            item.setLow(data.getLow());
            item.setHigh(data.getHigh());
            item.setClose(data.getClose());
            item.setOpen(data.getOpen());
            dataSeries.add(item);
        }
        configuration.setSeries(dataSeries);
        chart.drawChart(configuration);
    }

    public void chartTypesOhlcSnippet2() {
        IndexedContainer container= null;
        Collection<OhlcData> dataBank= null;
        int i=0;
        for (OhlcData data : dataBank) {
            Item ie = container.addItem(i);
            ie.getItemProperty("x").setValue(data.getDate());
            ie.getItemProperty("low").setValue(data.getLow());
            ie.getItemProperty("high").setValue(data.getHigh());
            ie.getItemProperty("open").setValue(data.getOpen());
            ie.getItemProperty("close").setValue(data.getClose());
            i++;
        }

        Chart chart = new Chart(ChartType.OHLC);
        Configuration configuration = chart.getConfiguration();
// Wrap the container in a data series
        ContainerDataSeries dataSeries = new ContainerDataSeries(container);
        configuration.setSeries(dataSeries);
        dataSeries.setHighPropertyId("high");
        dataSeries.setLowPropertyId("low");
        dataSeries.setXPropertyId("x");
        dataSeries.setOpenPropertyId("open");
        dataSeries.setClosePropertyId("open");

        PlotOptionsOhlc plotOptionsOhlc = new PlotOptionsOhlc();
        plotOptionsOhlc.setTurboThreshold(0);
        dataSeries.setPlotOptions(plotOptionsOhlc);
    }
    private class OhlcData {
        public Date getDate() {
            return null;
        }


        public Number getLow() {
            return 0;
        }

        public Number getHigh() {
            return 0;
        }

        public Number getClose() {
            return 1;
        }

        public Number getOpen() {
            return 1;
        }
    }



    private class StatAnalysis {
        public StatAnalysis(double[] foo) {

        }
        public double low() {
            return 1.0;
        }
        public double high() {
            return 1.0;
        }

        public double firstQuartile() {
            return 1.0;
        }
        public double median() {
            return 1.0;
        }
        public double thirdQuartile() {
            return 1.0;
        }
    }

    private class Coordinate {
        public double latitude=12.2;
        public double longitude=32.4;
    }
}
