/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package book.examples.basic.use;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.Back;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem3d;
import com.vaadin.addon.charts.model.Frame;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.Marker;
import com.vaadin.addon.charts.model.Options3d;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.themes.SkiesTheme;

public class BasicUse {
    public void basicUseSnippet1() {
        Chart chart = new Chart(ChartType.COLUMN);
        chart.setWidth("400px");  // 100% by default
        chart.setHeight("300px"); // 400px by default
    }

    public void basicUseConfigurationSnippet1() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Reindeer Kills by Predators");
        conf.setSubTitle("Kills Grouped by Counties");
    }

    public void basicUsePlotOptionsSnippet1() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setMarker(new Marker(false));
        conf.setPlotOptions(plotOptions);
    }

    public void basicUseDataSnippet1() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        ListSeries series = new ListSeries("Diameter");
        series.setData(4900,  12100,  12800,
                6800,  143000, 125000,
                51100, 49500);
        conf.addSeries(series);
    }
    public void basicUseAxisSnippet1() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        XAxis xaxis = new XAxis();
        xaxis.setCategories("Mercury", "Venus",   "Earth",
                "Mars",    "Jupiter", "Saturn",
                "Uranus",  "Neptune");
        xaxis.setTitle("Planet");
        conf.addxAxis(xaxis);
    }

    public void basicUseAxisSnippet2() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        // Set the Y axis title
        YAxis yaxis = new YAxis();
        yaxis.setTitle("Diameter");
        yaxis.getLabels().setFormatter(
                "function() {return Math.floor(this.value/1000) + \'Mm\';}");
        yaxis.getLabels().setStep(2);
        conf.addyAxis(yaxis);
    }

    public void basicUseTwoDimensional() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        String predators[] = {"Bear", "Wolf", "Wolverine", "Lynx"};
        int kills[][] = {        // Location:
                {8,   0,  7, 0}, // Muddusjarvi
                {30,  1, 30, 2}, // Ivalo
                {37,  0, 22, 2}, // Oraniemi
                {13, 23,  4, 1}, // Salla
                {3,  10,  9, 0}, // Alakitka
        };

        // Create a data series for each numeric column in the table
        for (int predator = 0; predator < 4; predator++) {
            ListSeries series = new ListSeries();
            series.setName(predators[predator]);

            // The rows of the table
            for (int location = 0; location < kills.length; location++)
                series.addData(kills[location][predator]);
            conf.addSeries(series);
        }

    }

    public void basicUseMixed() {
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
// A data series as column graph
        DataSeries series1 = new DataSeries();
        PlotOptionsColumn options1 = new PlotOptionsColumn();
        options1.setColor(SolidColor.BLUE);
        series1.setPlotOptions(options1);
        series1.setData(4900,  12100,  12800,
                6800,  143000, 125000, 51100, 49500);
        conf.addSeries(series1);

// A data series as line graph
        ListSeries series2 = new ListSeries("Diameter");
        PlotOptionsLine options2 = new PlotOptionsLine();
        options2.setColor(SolidColor.RED);
        series2.setPlotOptions(options2);
        series2.setData(4900,  12100,  12800,
                6800,  143000, 125000, 51100, 49500);
        conf.addSeries(series2);
    }

    public void basicUse3dSnippet1() {
        Chart chart = new Chart(ChartType.SCATTER);
        Configuration conf = chart.getConfiguration();
// In 3D!
        Options3d options3d = new Options3d();
        options3d.setEnabled(true);
        options3d.setAlpha(10);
        options3d.setBeta(30);
        options3d.setDepth(135); // Default is 100
        options3d.setViewDistance(100); // Default
        conf.getChart().setOptions3d(options3d);
    }

    public void basicUse3dSnippet2() {
        Options3d options3d = new Options3d();
        Frame frame = new Frame();
        Back back=new Back();
        back.setColor(SolidColor.BEIGE);
        back.setSize(1);
        frame.setBack(back);
        options3d.setFrame(frame);
    }

    public void basicUse3dPlotoptionsSnippet1() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        // Set some plot options
        PlotOptionsPie options = new PlotOptionsPie();

        options.setDepth(45); // Our pie is quite thick

        conf.setPlotOptions(options);
    }

    public void basicUse3dDataSnippet1() {
        Chart chart = new Chart(ChartType.PIE);
        Configuration conf = chart.getConfiguration();
        Options3d options3d = new Options3d();
        double[][] points = { {0.0, 0.0, 0.0}, // x, y, z
                {1.0, 0.0, 0.0},
                {0.0, 1.0, 0.0},
                {0.0, 0.0, 1.0},
                {-1.0, 0.0, 0.0},
                {0.0, -1.0, 0.0},
                {0.0, 0.0, -1.0}};

        DataSeries series = new DataSeries();
        for (int i=0; i<points.length; i++) {
            double x = points[i][0];
            double y = points[i][1];
            double z = points[i][2];

            // Scale the depth coordinate, as the depth axis is
            // not scaled automatically
            DataSeriesItem3d item = new DataSeriesItem3d(x, y,
                    z * options3d.getDepth().doubleValue());
            series.add(item);
        }
        conf.addSeries(series);
    }

    public double basicUse3dDistanceSnippet1(double[] point, double alpha,
        double beta, double viewDist) {
            final double theta = alpha * Math.PI / 180;
            final double phi   = beta * Math.PI / 180;
            double x = viewDist * Math.sin(theta) * Math.cos(phi);
            double y = viewDist * Math.sin(theta) * Math.sin(phi);
            double z = - viewDist * Math.cos(theta);
            return Math.sqrt(Math.pow(x - point[0], 2) +
                    Math.pow(y - point[1], 2) +
                    Math.pow(z - point[2], 2));
        }

    public void basicUse3dDistanceSnippet2() {
        double x=3.2;
        double z=3.3;
        double y=2.3;
        Options3d options3d = new Options3d();
        DataSeriesItem3d item = new DataSeriesItem3d(x, y,
                z * options3d.getDepth().doubleValue());

        double distance = 43.2;

        int gr = (int) (distance*75); // Grayness
        Marker marker = new Marker(true);
        marker.setRadius(1 + 10 / distance);
        marker.setFillColor(new SolidColor(gr, gr, gr));
        item.setMarker(marker);

        DataSeries series = new DataSeries();
        series.add(item);
    }

    public void basicUse3dFadeSnippet1() {
        ChartOptions.get().setTheme(new SkiesTheme());
    }
}
