package book.examples.migration;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Crosshair;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsArea;
import com.vaadin.addon.charts.model.PlotOptionsAreaspline;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPyramid;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.PointOptions;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.server.Sizeable;

public class Migration {


    private void setCommonProperties(PointOptions plotOptions){
        plotOptions.setLineWidth(5);
        plotOptions.setColor(SolidColor.RED);
        plotOptions.setAnimation(false);
    }
    public void abstractclassPlotoptions(PointOptions options) {
        DataSeries series=null;
        DataSeries series2=null;
        PlotOptionsSpline plotOptions=new PlotOptionsSpline();
        PlotOptionsLine plotOptions2=new PlotOptionsLine();
        setCommonProperties(plotOptions);
        setCommonProperties(plotOptions);

        series.setPlotOptions(plotOptions);
        series2.setPlotOptions(plotOptions2);
    }
    public void classnamedifColor() {
        PlotOptionsArea plotOptions=new PlotOptionsArea();
        plotOptions.setFillColor(new SolidColor("#ff0000"));
    }

    public void methodnamedifColor() {
        PlotOptionsPyramid options = new PlotOptionsPyramid();
        options.setWidth(70, Sizeable.Unit.PERCENTAGE);
        options.setWidth("70%");
    }

    public Chart exampleResult() {
        Chart chart = new Chart();

        Configuration config = chart.getConfiguration();
        config.setTitle("Charts migration");
        config.getTitle().setAlign(HorizontalAlign.LEFT);

        Crosshair xCrossHair = new Crosshair();
        xCrossHair.setColor(SolidColor.BLACK);
        xCrossHair.setDashStyle(DashStyle.SOLID);
        xCrossHair.setWidth(10);
        xCrossHair.setZIndex(0);
        config.getxAxis().setCrosshair(xCrossHair);

        Crosshair yCrossHair = new Crosshair();
        yCrossHair.setColor(new SolidColor("#880000"));
        yCrossHair.setDashStyle(DashStyle.DOT);
        yCrossHair.setWidth(5);
        yCrossHair.setZIndex(1);
        config.getyAxis().setCrosshair(yCrossHair);

        config.getLegend().setEnabled(false);
        config.getTooltip().setEnabled(false);

        ListSeries ls = new ListSeries();
        ls.setName("Data");
        ls.setData(29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4,
                194.1, 95.6, 54.4);
        PlotOptionsAreaspline plotOptions = new PlotOptionsAreaspline();
        plotOptions.setColor(SolidColor.BURLYWOOD);
        plotOptions.setDataLabels(new DataLabels(false));
        ls.setPlotOptions(plotOptions);
        config.setSeries(ls);

        return chart;
    }
}
