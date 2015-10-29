package com.vaadin.addon.charts.examples.columnandbar;

import java.util.Collection;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Title;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.OptionGroup;

@SuppressWarnings("serial")
public class ToggledSeriesVisibility extends AbstractVaadinChartExample {

    private final ListSeries berlin = new ListSeries("Berlin", 42.4, 33.2,
            34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1);
    private final ListSeries london = new ListSeries("London", 48.9, 38.8,
            39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2);
    private final ListSeries newYork = new ListSeries("New York", 83.6, 78.8,
            98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3);
    private final ListSeries tokyo = new ListSeries("Tokyo", 49.9, 71.5, 106.4,
            129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4);
    private Chart chart;
    private OptionGroup optionGroup;

    @Override
    public String getDescription() {
        return "Column chart with Vaadin component to control displayed series. Note, that visibility can also be toggled by clicking on legend.";
    }

    @Override
    protected Component getChart() {
        chart = new Chart(ChartType.COLUMN);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Total fruit consumption, grouped by gender");
        conf.setSubTitle("Source: WorldClimate.com");

        XAxis x = new XAxis();
        x.setCategories(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" });
        conf.addxAxis(x);

        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle(new Title("Rainfall (mm)"));
        conf.addyAxis(y);

        Legend legend = new Legend();
        // FIXME remove toString() once enums are used in model (CHARTS-159)
        legend.setLayout(LayoutDirection.VERTICAL.toString());
        legend.setBackgroundColor("#FFFFFF");
        legend.setAlign(HorizontalAlign.LEFT.toString());
        legend.setVerticalAlign(VerticalAlign.TOP.toString());
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);

        Tooltip tooltip = new Tooltip();
        // tooltip.setFormatter("this.x +': '+ this.y +' mm'");
        conf.setTooltip(tooltip);

        PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);

        conf.addSeries(tokyo);
        conf.addSeries(newYork);
        conf.addSeries(berlin);
        conf.addSeries(london);

        chart.addLegendItemClickListener(new LegendItemClickListener() {

            @Override
            public void onClick(LegendItemClickEvent event) {
                /*
                 * Visibility of the series is also toggled from legend clicks
                 * by default. Still developers might wish to override this
                 * behavior if the visibility is also controlled by other
                 * components like here or if e.g. multiple charts are bound
                 * together (hiding series in one chart should hide related data
                 * in other chart as well).
                 */

                ListSeries series = (ListSeries) event.getSeries();
                /*
                 * Toggle checked in option group. As a side effect (via value
                 * change listener, see setup method) the visibility will change
                 * in the chart as well.
                 */
                if (series.isVisible()) {
                    optionGroup.unselect(series);
                } else {
                    optionGroup.select(series);
                }
            }
        });

        chart.drawChart(conf);
        return chart;
    }

    @Override
    protected void setup() {
        super.setup();
        optionGroup = new OptionGroup();
        optionGroup.setId("vaadin-optiongroup");
        optionGroup.setImmediate(true);
        optionGroup.setMultiSelect(true);

        final List<Series> series = chart.getConfiguration().getSeries();
        for (Series series2 : series) {
            optionGroup.addItem(series2);
            optionGroup.setItemCaption(series2, series2.getName());
        }
        optionGroup.setValue(optionGroup.getItemIds());
        addComponentAsFirst(optionGroup);
        optionGroup.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                @SuppressWarnings("unchecked")
                Collection<ListSeries> value = (Collection<ListSeries>) event
                        .getProperty().getValue();
                for (Series s : series) {
                    ((ListSeries) s).setVisible(value.contains(s));
                }
            }
        });
    }
}
