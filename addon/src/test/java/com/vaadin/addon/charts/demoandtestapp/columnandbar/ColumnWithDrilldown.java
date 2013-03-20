package com.vaadin.addon.charts.demoandtestapp.columnandbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.FontWeight;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.VaadinTheme;
import com.vaadin.ui.Component;

@SuppressWarnings("serial")
public class ColumnWithDrilldown extends AbstractVaadinChartExample {

    @Override
    public String getDescription() {
        return "Basic column";
    }

    @Override
    protected Component getChart() {
        final Chart chart = new Chart(ChartType.COLUMN);

        Color[] colors = new VaadinTheme().getColors();
        final Configuration conf = chart.getConfiguration();

        conf.setTitle("Browser market share, April, 2011");
        conf.setSubTitle("Click the columns to view versions. Click again to view brands.");

        XAxis x = new XAxis();
        x.setCategories("MSIE", "Firefox", "Chrome", "Safari", "Opera");
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setTitle("Total percent market share");
        conf.addyAxis(y);

        PlotOptionsColumn column = new PlotOptionsColumn();
        column.setCursor(Cursor.POINTER);
        column.setDataLabels(new Labels(true));
        column.getDataLabels().setColor(colors[0]);
        column.getDataLabels().getStyle().setFontWeight(FontWeight.BOLD);
        column.getDataLabels().setFormatter("this.y +'%'");

        conf.setPlotOptions(column);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter("function() {"
                + " var point = this.point, s = this.x +':<b>'+ this.y +'% "
                + "market share</b><br/>'; " + "if (!point.drilldown) { "
                + "s += 'Click to view '+ point.category +' versions'; "
                + "} else { " + "s += 'Click to return to browser brands'; "
                + "} " //
                + "return s; }");
        conf.setTooltip(tooltip);

        final DrilldownSeries series = new DrilldownSeries(conf, 55.11, 21.63,
                11.94, 7.15, 2.14);
        series.setName("Browser brands");
        PlotOptionsColumn plotOptionsColumn = new PlotOptionsColumn();
        plotOptionsColumn.setColor(new SolidColor("#ffffff"));
        series.setPlotOptions(plotOptionsColumn);

        Drilldown drill = new Drilldown("MSIE versions");
        drill.setData(10.85, 7.35, 33.06, 2.81);
        drill.setCategories("MSIE 6.0", "MSIE 7.0", "MSIE 8.0", "MSIE 9.0");
        drill.setColor(colors[0]);
        series.addDrilldown("MSIE", drill);

        drill = new Drilldown("Firefox versions");
        drill.setData(0.20, 0.83, 1.58, 13.12, 5.43);
        drill.setCategories("Firefox 2.0", "Firefox 3.0", "Firefox 3.5",
                "Firefox 3.6", "Firefox 4.0");
        drill.setColor(colors[1]);
        series.addDrilldown("Firefox", drill);

        drill = new Drilldown("Chrome versions");
        drill.setData(0.12, 0.19, 0.12, 0.36, 0.32, 9.91, 0.50, 0.22);
        drill.setCategories("Chrome 5.0", "Chrome 6.0", "Chrome 7.0",
                "Chrome 8.0", "Chrome 9.0", "Chrome 10.0", "Chrome 11.0",
                "Chrome 12.0");
        drill.setColor(colors[2]);
        series.addDrilldown("Chrome", drill);

        drill = new Drilldown("Safari versions");
        drill.setData(4.55, 1.42, 0.23, 0.21, 0.20, 0.19, 0.14);
        drill.setCategories("Safari 5.0", "Safari 4.0", "Safari Win 5.0",
                "Safari 4.1", "Safari/Maxthon", "Safari 3.1", "Safari 4.1");
        drill.setColor(colors[3]);
        series.addDrilldown("Safari", drill);

        drill = new Drilldown("Opera versions");
        drill.setData(0.12, 0.37, 1.65);
        drill.setCategories("Opera 9.x", "Opera 10.x", "Opera 11.x");
        drill.setColor(colors[4]);
        series.addDrilldown("Opera", drill);

        conf.setSeries(series);
        conf.setExporting(false);

        chart.addPointClickListener(new PointClickListener() {
            @Override
            public void onClick(PointClickEvent event) {
                if (!series.isDrilldown()) {
                    series.drillDown(event.getCategory());
                } else {
                    series.backToUp();
                }
                chart.drawChart(conf);
            }
        });

        chart.drawChart(conf);
        return chart;
    }
    
    /**
     * Works like normal DataSeries but stores also the drilldown data and contains
     * functionality for dill down and back to up
     */
    public static class DrilldownSeries extends DataSeries {
        private transient final Map<String, Drilldown> drilldowns = new HashMap<String, Drilldown>();
        private transient final Configuration configuration;
        private transient Number[] mainData = null;
        private transient String[] mainCategories;
        private transient String mainName;
        private transient Color mainColor;
        private transient boolean isDrilldown;

        /**
         * Constructs the DrilldownSeries with Chart's configuration
         * 
         * @param configuration
         */
        public DrilldownSeries(Configuration configuration) {
            super();
            this.configuration = configuration;
        }

        /**
         * Constructs the DrilldownSeries with Chart's configuration and numeric
         * data for main categories (that are fetched from the chart configuration)
         * 
         * @param configuration
         */
        public DrilldownSeries(Configuration configuration, Number... data) {
            super(configuration.getxAxis().getCategories(), data);
            mainCategories = configuration.getxAxis().getCategories();
            this.configuration = configuration;
        }

        /**
         * Adds a new Drilldown to be drilldown serie of the given categoryName
         * 
         * @param categoryName
         * @param numbers
         */
        public void addDrilldown(String categoryName, Drilldown drilldown) {
            drilldowns.put(categoryName, drilldown);
            extractColors();
        }

        /**
         * Extract series colors for series when all series are given
         */
        private void extractColors() {
            if (mainCategories.length == drilldowns.size()) {
                for (DataSeriesItem item : getData()) {
                    Drilldown dd = drilldowns.get(item.getName());
                    ((DataSeriesItem) item).setColor(dd.getColor());
                }
            }
        }

        /**
         * Drills down into given category
         * 
         * @param category
         */
        public void drillDown(String category) {
            if (mainData == null) {
                
                mainData = new Number[size()];
                
                List<DataSeriesItem> data = getData();
                for (int i = 0; i < data.size(); i++) {
                    mainData[i] = data.get(i).getY();
                }
                mainCategories = configuration.getxAxis().getCategories().clone();
                mainName = getName();
                if (getPlotOptions() != null) {
                    mainColor = getPlotOptions().getColor();
                }
            }

            Drilldown dd = drilldowns.get(category);
            configuration.getxAxis().setCategories(dd.getCategories());
            setName(dd.getName());
            setData(dd.getData());
            setDrillDownColors(category);
            isDrilldown = true;
        }

        /**
         * Set colors of the current drilldown series
         * 
         * @param category
         */
        private void setDrillDownColors(String category) {
            Drilldown dd = drilldowns.get(category);
            for (DataSeriesItem item : getData()) {
                ((DataSeriesItem) item).setColor(dd.getColor());
            }
        }

        /**
         * Goes back to up
         */
        public void backToUp() {
            configuration.getxAxis().setCategories(mainCategories);
            setName(mainName);
            setData(mainCategories, mainData);
            configuration.getLegend().getStyle().setColor(mainColor);
            extractColors();

            isDrilldown = false;
        }

        /**
         * Are we drilled down now
         * 
         * @return
         */
        public boolean isDrilldown() {
            return isDrilldown;
        }
    }

    public static class Drilldown {
        private String name;
        private String[] categories;
        private Number[] data;
        private Color color;

        public Drilldown() {

        }

        public Drilldown(String name) {
            this.name = name;
        }

        public Drilldown(String name, String[] categories, Number[] data,
                Color color) {
            super();
            this.name = name;
            this.categories = categories;
            this.data = data;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getCategories() {
            return categories;
        }

        public void setCategories(String... categories) {
            this.categories = categories;
        }

        public Number[] getData() {
            return data;
        }

        public void setData(Number... data) {
            this.data = data;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}
