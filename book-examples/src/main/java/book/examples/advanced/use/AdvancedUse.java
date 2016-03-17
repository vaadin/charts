package book.examples.advanced.use;


import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Exporting;
import com.vaadin.addon.charts.util.SVGGenerator;

public class AdvancedUse {

    public void chartsAdvancedExportSnippet1() {
        Chart chart =new Chart();
        chart.getConfiguration().setExporting(true);
    }

    public void chartsAdvancedExportSnippet2() {
        Chart chart =new Chart();
        Exporting exporting = new Exporting(true);
        exporting.setFilename("mychartfile.pdf");
        chart.getConfiguration().setExporting(exporting);
    }

    public void chartsAdvancedExportSnippet3() {
        Exporting exporting = new Exporting(true);
        exporting.setUrl("http://my.own.server.com");
    }

    public void chartsAdvancedExportSVGGenerator() {
        Chart chart =new Chart();
        String svg = SVGGenerator.getInstance()
                .generate(chart.getConfiguration());
    }
}
