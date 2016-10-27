package com.vaadin.addon.charts.examples.dynamic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.CheckboxClickEvent;
import com.vaadin.addon.charts.CheckboxClickListener;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.LegendItemClickListener;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
import com.vaadin.addon.charts.PointSelectEvent;
import com.vaadin.addon.charts.PointSelectListener;
import com.vaadin.addon.charts.PointUnselectEvent;
import com.vaadin.addon.charts.PointUnselectListener;
import com.vaadin.addon.charts.SeriesHideEvent;
import com.vaadin.addon.charts.SeriesHideListener;
import com.vaadin.addon.charts.SeriesShowEvent;
import com.vaadin.addon.charts.SeriesShowListener;
import com.vaadin.addon.charts.XAxesExtremesChangeEvent;
import com.vaadin.addon.charts.XAxesExtremesChangeListener;
import com.vaadin.addon.charts.YAxesExtremesChangeEvent;
import com.vaadin.addon.charts.YAxesExtremesChangeListener;
import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.examples.SkipFromDemo;
import com.vaadin.addon.charts.model.AbstractSeries;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotLine;
import com.vaadin.addon.charts.model.PlotOptionsSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.OptionGroup;

@SkipFromDemo
public class ServerSideEvents extends AbstractVaadinChartExample {

    private Chart chart;
    private Label lastEvent = new Label();
    private Label eventDetails = new Label();
    private int id = 0;
    private VerticalLayout historyLayout = new VerticalLayout();
    private int eventNumber = 0;
    private DataSeriesItem firstDataPoint;
    private VerticalLayout eventListeners;
    private CheckBox visibilityToggling;

    @Override
    public String getDescription() {
        return "Test server side events";
    }

    @Override
    protected Component getChart() {
        eventDetails.setId("eventDetails");
        lastEvent.setId("lastEvent");
        historyLayout.setId("history");
        chart = new Chart();
        chart.setId("chart");
        chart.setWidth("500px");

        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SCATTER);
        configuration.getTitle().setText("Test server side events.");
        configuration.getSubTitle().setText(
                "When an event occurs, the details are shown below the chart");
        configuration.setExporting(true);
        configuration.getChart().setAnimation(false);
        configuration.getChart().setZoomType(ZoomType.XY);

        XAxis xAxis = configuration.getxAxis();
        xAxis.setMinPadding(0.2);
        xAxis.setMaxPadding(0.2);

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Value"));
        PlotLine plotline = new PlotLine();
        plotline.setValue(0);
        plotline.setWidth(1);
        plotline.setColor(new SolidColor("#808080"));
        yAxis.setPlotLines(new PlotLine[] { plotline });
        yAxis.setMinPadding(0.2);
        yAxis.setMaxPadding(0.2);

        YAxis yAxis1 = new YAxis();
        yAxis1.setTitle("Another axis");
        yAxis1.setOpposite(true);
        configuration.addyAxis(yAxis1);

        PlotOptionsSeries opt = new PlotOptionsSeries();
        opt.setLineWidth(1);
        opt.setShowCheckbox(true);
        opt.setAllowPointSelect(true);

        configuration.setPlotOptions(opt);
        configuration.setTooltip(new Tooltip(false));
        final DataSeries series1 = createDataSeries(0);
        final DataSeries series2 = createDataSeries(20);
        DataSeries series3 = createDataSeries(100);
        series3.get(0).setY(105);
        series3.get(3).setY(95);
        series3.setName("Another axis");
        series3.setyAxis(1);
        firstDataPoint = series1.get(0);
        firstDataPoint.setSelected(true);
        configuration.setSeries(series1, series2, series3);

        chart.drawChart(configuration);

        final Layout toggles = createControls();
        Layout eventListeners = addEventListeners();

        chart.setSeriesVisibilityTogglingDisabled(false);
        visibilityToggling.setValue(false);

        lastEvent.setCaption("Last event");
        eventDetails.setCaption("Details");
        historyLayout.setCaption("History");
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.addComponent(toggles);
        HorizontalLayout chartAndListeners = new HorizontalLayout(chart,
                eventListeners);
        chartAndListeners.setSizeUndefined();
        chartAndListeners.setSpacing(true);
        layout.addComponent(chartAndListeners);
        layout.addComponent(lastEvent);
        layout.addComponent(eventDetails);
        layout.addComponent(historyLayout);
        return layout;
    }

    private Layout addEventListeners() {
        eventListeners = new VerticalLayout();
        eventListeners.setCaption("Event listeners:");

        final ChartClickListener listener = new ChartClickListener() {
            @Override
            public void onClick(ChartClickEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Chart click", "ChartClick", new ListenerToggle() {
            @Override
            public void add() {
                chart.addChartClickListener(listener);
            }

            @Override
            public void remove() {
                chart.removeChartClickListener(listener);
            }
        });

        final PointClickListener pcListener = new PointClickListener() {

            @Override
            public void onClick(PointClickEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Point click", "PointClick", new ListenerToggle() {
            @Override
            public void add() {
                chart.addPointClickListener(pcListener);
            }

            @Override
            public void remove() {
                chart.removePointClickListener(pcListener);

            }
        });

        final CheckboxClickListener cbListener = new CheckboxClickListener() {
            @Override
            public void onClick(CheckboxClickEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Checkbox click", "CheckboxClick",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addCheckBoxClickListener(cbListener);
                    }

                    @Override
                    public void remove() {
                        chart.removeCheckBoxClickListener(cbListener);
                    }
                });

        final LegendItemClickListener legendItemListener = new LegendItemClickListener() {
            @Override
            public void onClick(LegendItemClickEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Legend item click", "LegendItemClick",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addLegendItemClickListener(legendItemListener);
                        visibilityToggling.setValue(true);
                    }

                    @Override
                    public void remove() {
                        chart.removeLegendItemClickListener(legendItemListener);
                    }
                });

        final SeriesHideListener hideListener = new SeriesHideListener() {
            @Override
            public void onHide(SeriesHideEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Series hide", "SeriesHide", new ListenerToggle() {
            @Override
            public void add() {
                chart.addSeriesHideListener(hideListener);
            }

            @Override
            public void remove() {
                chart.removeSeriesHideListener(hideListener);
            }
        });

        final SeriesShowListener show = new SeriesShowListener() {
            @Override
            public void onShow(SeriesShowEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Series show", "SeriesShow", new ListenerToggle() {
            @Override
            public void add() {
                chart.addSeriesShowListener(show);
            }

            @Override
            public void remove() {
                chart.removeSeriesShowListener(show);
            }
        });

        final XAxesExtremesChangeListener xaxesListener = new XAxesExtremesChangeListener() {
            @Override
            public void onXAxesExtremesChange(XAxesExtremesChangeEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("XAxes extremes change", "XAxesExtremes",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addXAxesExtremesChangeListener(xaxesListener);
                    }

                    @Override
                    public void remove() {
                        chart.removeXAxesExtremesChangeListener(xaxesListener);
                    }
                });

        final YAxesExtremesChangeListener yaxesListener = new YAxesExtremesChangeListener() {
            @Override
            public void onYAxesExtremesChange(YAxesExtremesChangeEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Y axes extremes change", "YAxesExtremes",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addYAxesExtremesChangeListener(yaxesListener);
                    }

                    @Override
                    public void remove() {
                        chart.removeYAxesExtremesChangeListener(yaxesListener);
                    }
                });

        final PointSelectListener selectListener = new PointSelectListener() {
            @Override
            public void onSelect(PointSelectEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Point select", "PointSelect",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addPointSelectListener(selectListener);
                    }

                    @Override
                    public void remove() {
                        chart.removePointSelectListener(selectListener);
                    }
                });

        final PointUnselectListener unselectListener = new PointUnselectListener() {
            @Override
            public void onUnselect(PointUnselectEvent event) {
                logEvent(event);
            }
        };
        addToggleForListener("Point unselect", "PointUnselect",
                new ListenerToggle() {
                    @Override
                    public void add() {
                        chart.addPointUnselectListener(unselectListener);
                    }

                    @Override
                    public void remove() {
                        chart.removePointUnselectListener(unselectListener);
                    }
                });
        return eventListeners;
    }

    private void addToggleForListener(String caption, String id,
            final ListenerToggle listenerToggle) {
        final CheckBox checkBox = new CheckBox(caption);
        checkBox.setId(id);
        checkBox.addValueChangeListener(e -> {
            if (checkBox.getValue()) {
                listenerToggle.add();
            } else {
                listenerToggle.remove();
            }
        });
        checkBox.setValue(true);
        eventListeners.addComponent(checkBox);
    }

    private interface ListenerToggle {
        void add();

        void remove();
    }

    private Layout createControls() {
        visibilityToggling = new CheckBox("Disable series visibility toggling");
        visibilityToggling.addValueChangeListener(e -> {
            chart.setSeriesVisibilityTogglingDisabled(visibilityToggling
                    .getValue());
        });

        final Button firstSeriesVisible = new Button("Hide first series");
        firstSeriesVisible.setId("hideFirstSeries");
        firstSeriesVisible.addClickListener(new Button.ClickListener() {
            boolean hideSeries = true;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Series firstSeries = chart.getConfiguration().getSeries()
                        .get(0);
                ((AbstractSeries) firstSeries).setVisible(!hideSeries);
                hideSeries = !hideSeries;
                String caption = hideSeries ? "Hide first series"
                        : "Show first series";
                firstSeriesVisible.setCaption(caption);
            }
        });

        Button setExtremes = new Button("Toggle extremes");
        setExtremes.setId("setExtremes");
        setExtremes.addClickListener(new Button.ClickListener() {
            public boolean extremesSet;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (extremesSet) {
                    chart.getConfiguration().getxAxis().setExtremes(10, 90);
                } else {
                    chart.getConfiguration().getxAxis().setExtremes(20, 80);
                }
                extremesSet = !extremesSet;
            }
        });

        final OptionGroup zoomLevels = new OptionGroup("Zoom Type");
        zoomLevels.addItem(ZoomType.XY);
        zoomLevels.addItem(ZoomType.X);
        zoomLevels.addItem(ZoomType.Y);
        zoomLevels.setValue(ZoomType.XY);
        zoomLevels.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                ZoomType zoom = (ZoomType) zoomLevels.getValue();
                chart.getConfiguration().getChart().setZoomType(zoom);
                chart.drawChart();
            }
        });

        Button resetHistory = new Button("Reset history");
        resetHistory.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                lastEvent.setValue(null);
                eventDetails.setValue(null);
                historyLayout.removeAllComponents();
            }
        });

        HorizontalLayout controls = new HorizontalLayout();
        controls.setId("controls");
        controls.setSpacing(true);
        controls.addComponent(visibilityToggling);
        controls.addComponent(firstSeriesVisible);
        controls.addComponent(setExtremes);
        controls.addComponent(zoomLevels);
        controls.addComponent(resetHistory);
        return controls;
    }

    private DataSeries createDataSeries(Number yvalue) {
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem(20, yvalue));
        series.add(new DataSeriesItem(40, yvalue.intValue() + 10));
        series.add(new DataSeriesItem(60, yvalue.intValue() - 15));
        series.add(new DataSeriesItem(80, yvalue));
        series.setId("" + id);
        series.setName("Test Series " + id);
        ++id;
        return series;
    }

    private void logEvent(Event event) {
        String name = event.getClass().getSimpleName();
        String details = createEventString(event);
        lastEvent.setValue(name);
        eventDetails.setValue(details);
        Label history = new Label(name + ": " + details + "\n");
        history.setId("event" + eventNumber);
        historyLayout.addComponentAsFirst(history);
        ++eventNumber;
    }

    private String createEventString(Event event) {
        ObjectMapper mapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setVisibility(PropertyAccessor.ALL,
                        JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD,
                        JsonAutoDetect.Visibility.ANY);

        try {
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
