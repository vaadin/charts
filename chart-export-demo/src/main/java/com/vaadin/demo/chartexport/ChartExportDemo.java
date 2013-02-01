package com.vaadin.demo.chartexport;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Labels;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.util.SVGGenerator;
import com.vaadin.demo.chartexport.util.PdfExportDemo;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Demo class to show how Vaadin Charts can be exported to image/PDF in the
 * server side. Demonstrates also how to render charts completely in the server.
 * Basically you can just get the json formatted chart options from the Chart
 * component and run Highcharts library in the server with these options. This
 * demo uses PhantomJs to run javascript in server.</br></br> This also
 * demonstrates how to embed chart SVG image to PDF. iText 2.1.7 <i>(notice that
 * this is old version, but it's open source licensed)</i> is used to generate
 * PDF. Batik is used to render SVG.
 * 
 */
public class ChartExportDemo extends VerticalLayout {

    private Button exportButton2;
    private Button exportButton3;

    private final HorizontalLayout layout;
    private final Chart chart;

    public ChartExportDemo() {
        setMargin(true);

        layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        chart = createChart();
        layout.addComponent(chart);

        createRightSideLayout();

        addComponent(new Label(
                "<h1>Demo of Vaadin Charts server side exporting<h1>",
                ContentMode.HTML));
        addComponent(layout);
    }

    private StreamSource createSVGStreamSource() {
        return new StreamSource() {
            @Override
            public InputStream getStream() {
                String svg = SVGGenerator.getInstance().generate(
                        chart.getConfiguration());
                if (svg != null) {
                    return new ByteArrayInputStream(svg.getBytes());
                }
                return null;
            }
        };
    }

    private StreamSource createPdfStreamSource() {
        return new StreamSource() {
            @Override
            public InputStream getStream() {
                File result = doExportPDF();
                if (result != null) {
                    try {
                        return new FileInputStream(result);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
    }

    private void createRightSideLayout() {
        VerticalLayout l = new VerticalLayout();

        exportButton2 = createExportButton("Download as SVG", "chart.svg",
                createSVGStreamSource());
        exportButton3 = createExportButton("Download PDF containing the chart",
                "chart.pdf", createPdfStreamSource());

        try {
            String html = IOUtils.toString(ChartExportDemo.class
                    .getResourceAsStream("/introduction.html"));
            l.addComponent(new Label(html, ContentMode.HTML));
        } catch (IOException e) {
        }

        l.addComponent(exportButton2);
        l.addComponent(exportButton3);

        layout.addComponent(l);
    }

    private Button createExportButton(String caption, String filename,
            StreamSource ss) {
        Button b = new Button(caption);
        FileDownloader downloader = new FileDownloader(new StreamResource(ss,
                filename));
        downloader.extend(b);
        return b;
    }

    /**
     * Export chart's options into a SVG String and then embed it into a PDF
     * document.
     * 
     * @return PDF file
     */
    private File doExportPDF() {
        String svg = SVGGenerator.getInstance().generate(
                chart.getConfiguration());
        return new PdfExportDemo().writePdf("chart", svg);
    }

    public static Chart createChart() {
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

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

}
