package com.vaadin.demo.chartexport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AbstractConfigurationObject;
import com.vaadin.addon.charts.model.AxisList;
import com.vaadin.addon.charts.model.ChartEnum;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Exporting;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PaneList;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.gsonhelpers.AbstractSeriesTypeAdapterFactory;
import com.vaadin.addon.charts.model.gsonhelpers.AxisListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ChartEnumSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.ContainerDataSeriesSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.DataSeriesItemTypeAdapterFactory;
import com.vaadin.addon.charts.model.gsonhelpers.PaneListSerializer;
import com.vaadin.addon.charts.model.gsonhelpers.SolidColorSerializer;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.demo.chartexport.util.SVGCreatorDemo;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ChartExportDemo extends HorizontalLayout {

    private static String exportingServiceUrl = "http://localhost:8080/chart-export-demo/exporting-service";

    private Button exportButton1;
    private Button exportButton2;

    private final Chart chart;

    public ChartExportDemo() {
        setSizeFull();
        setSpacing(true);
        setMargin(true);

        chart = createChart();
        addComponent(chart);

        createRightSide();
    }

    private StreamSource createStreamSource(final boolean writeToFile) {
        return new StreamSource() {
            @Override
            public InputStream getStream() {
                String result = doExport(writeToFile);
                if (result != null) {
                    return new ByteArrayInputStream(result.getBytes());
                }
                return null;
            }
        };
    }

    private void createRightSide() {
        VerticalLayout l = new VerticalLayout();

        exportButton1 = createExportButton("Export chart - cache to file",
                createStreamSource(true));
        exportButton2 = createExportButton("Export chart",
                createStreamSource(false));

        l.addComponent(new Label(
                String.format(
                        "<p>Click the button over the top-right corner of the chart to export chart to a image/pdf. Chart's SVG image will be sent to the server, and the server transforms the SVG to the wanted format and returns it back to the browser.</p><ul><li><b>ExportServlet</b> is mapped to %s and it handles the posted SVG</li><li>To export to PDF, %s is required</li></ul>",
                        exportingServiceUrl,
                        getLink("Batik SVG Toolkit",
                                "http://xmlgraphics.apache.org/batik/")),
                ContentMode.HTML));
        l.addComponent(new Label("<hr></hr>", ContentMode.HTML));
        l.addComponent(new Label(
                String.format(
                        "<p>Click the button below to export the chart to a SVG file <u>completely in the server side</u>. Chart will be rendered in the server by using %s which is a headless WebKit. %s is used as a PhantomJs script.</p><p>Result SVG gets downloaded.</p>",
                        getLink("Phantomjs", "http://phantomjs.org/"),
                        getLink("highcharts-convert.js",
                                "https://github.com/highslide-software/highcharts.com/tree/master/exporting-server/phantomjs")),
                ContentMode.HTML));
        l.addComponent(exportButton1);
        l.addComponent(new Label("<hr></hr>", ContentMode.HTML));
        l.addComponent(new Label(
                "<p>Click the button below to achieve the same result than with the button above but without any needs to write the SVG in a file.</p><p>This uses a slightly modified highcharts-convert.js.",
                ContentMode.HTML));
        l.addComponent(exportButton2);
        addComponent(l);
    }

    private String getLink(String caption, String url) {
        return "<a target=\"blank\" href=\"" + url + "\">" + caption + "</a>";
    }

    private Button createExportButton(String caption, StreamSource ss) {
        Button b = new Button(caption);
        FileDownloader downloader = new FileDownloader(new StreamResource(ss,
                "chart.svg"));
        downloader.extend(b);
        return b;
    }

    /**
     * Export chart's options into a SVG String.
     * 
     * @param writeToFile
     *            If true, write the result SVG to a file.
     * @return SVG String
     */
    private String doExport(boolean writeToFile) {
        try {
            String location = getUI().getSession().getService()
                    .getBaseDirectory().getPath()
                    + File.separator + "WEB-INF";

            // Chart.getConfiguration().toString() returns json formatted
            // options.
            return SVGCreatorDemo.getInstance().createSVG(location,
                    chart.getConfiguration().toString(), writeToFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Chart createChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        // This is needed to disable pretty printing (optional)
        AbstractConfigurationObject.setGsonInstance(createGsonBuilder());

        Exporting exporting = new Exporting(true);
        // Let's override the default exporting service with out own one
        exporting.setUrl(exportingServiceUrl);
        conf.setExporting(exporting);

        conf.setTitle("Browser market shares at a specific website, 2010");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        Labels dataLabels = new Labels();
        dataLabels.setEnabled(true);
        dataLabels.setColor(new SolidColor(0, 0, 0));
        dataLabels.setConnectorColor(new SolidColor(0, 0, 0));
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Firefox", 45.0));
        series.add(new DataSeriesItem("IE", 26.8));
        DataSeriesItem chrome = new DataSeriesItem("Chrome", 12.8);
        chrome.setSliced(true);
        chrome.setSelected(true);
        series.add(chrome);
        series.add(new DataSeriesItem("Safari", 8.5));
        series.add(new DataSeriesItem("Opera", 6.2));
        series.add(new DataSeriesItem("Others", 0.7));
        conf.setSeries(series);

        chart.drawChart(conf);

        return chart;
    }

    private static Gson createGsonBuilder() {
        // This is copied from the
        // AbstractConfigurationObject.createGsonBuilder.
        // Pretty printing is enabled by default with Vaadin Charts beta. This
        // just disables the pretty printing.
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeHierarchyAdapter(ChartEnum.class,
                new ChartEnumSerializer());
        builder.registerTypeHierarchyAdapter(SolidColor.class,
                new SolidColorSerializer());
        builder.registerTypeHierarchyAdapter(AxisList.class,
                new AxisListSerializer());
        builder.registerTypeHierarchyAdapter(PaneList.class,
                new PaneListSerializer());
        builder.registerTypeAdapter(ContainerDataSeries.class,
                new ContainerDataSeriesSerializer());
        builder.registerTypeAdapterFactory(new DataSeriesItemTypeAdapterFactory());
        builder.registerTypeAdapterFactory(new AbstractSeriesTypeAdapterFactory());
        return builder.create();
    }
}
