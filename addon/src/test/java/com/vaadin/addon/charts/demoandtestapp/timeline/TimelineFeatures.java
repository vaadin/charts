package com.vaadin.addon.charts.demoandtestapp.timeline;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vaadin.addon.charts.demoandtestapp.AbstractVaadinChartExample;
import com.vaadin.addon.charts.demoandtestapp.SkipFromDemo;
import com.vaadin.addon.timeline.Timeline;
import com.vaadin.addon.timeline.Timeline.EventButtonClickEvent;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Indexed;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SkipFromDemo
@SuppressWarnings("serial")
public class TimelineFeatures extends AbstractVaadinChartExample {

    private VerticalLayout layout;

    private Timeline timeline;

    private MenuBar menu;

    private final List<Indexed> datasources = new ArrayList<Indexed>();

    public TimelineFeatures() {
        layout = new VerticalLayout();
        layout.setSizeFull();
        menu = createMenuBar();
        menu.setWidth("100%");
        layout.addComponent(menu);

        timeline = new Timeline();
        timeline.setSizeFull();
        timeline.setImmediate(true);
        timeline.setVerticalAxisRange(0f, 100f);
        timeline.addZoomLevel("1day", 86400000L);

        timeline.addListener(new Timeline.EventClickListener() {
            @Override
            public void eventClick(EventButtonClickEvent event) {
                Notification.show("Event click!");
            }
        });

        addNewDatasource();
        layout.addComponent(timeline);
        layout.setExpandRatio(timeline, 1);
    }

    private MenuBar createMenuBar() {
        MenuBar m = new MenuBar();
        m.setDebugId("menu");

        // Add datasource features
        MenuItem ds = m.addItem("Datasource", null);
        ds.addItem("No graphs", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                removeAllDatasources();
            }
        });
        for (int i = 1; i <= 3; i++) {
            final int graphs = i;
            ds.addItem(i + " graphs", new MenuBar.Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
                    removeAllDatasources();
                    for (int j = 0; j < graphs; j++) {
                        addNewDatasource();
                    }
                }
            });
        }
        ds.addItem("Set CSV datasource", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                removeAllDatasources();
                addCSVDatasource();
                timeline.setVerticalAxisRange(0f, 2500f);
            }
        });
        ds.addItem("3 graphs, second empty", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                removeAllDatasources();
                addNewDatasource();

                // Empty
                Indexed c = TestContainers.createEmptyDefaultContainer();
                datasources.add(c);
                timeline.addGraphDataSource(c);

                addNewDatasource();
            }
        });
        ds.addItem("Toggle Markers", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                if (timeline.getMarkerDataSource() == null) {
                    timeline.setMarkerDataSource(TestContainers
                            .createMarkerContainer());
                } else {
                    timeline.setMarkerDataSource(null);
                }
            }
        });
        ds.addItem("Toggle Events", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                if (timeline.getEventDataSource() == null) {
                    timeline.setEventDataSource(TestContainers
                            .createEventContainer());

                } else {
                    timeline.setEventDataSource(null);
                }
            }
        });
        ds.addItem("Set pre-epoch graph", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                removeAllDatasources();
                addPreEpochDatasource();
            }
        });

        // Graph rendering features
        MenuItem graph = m.addItem("Graph", null);

        MenuItem graph_thickness = graph.addItem("Thickness", null);
        for (double i = 1; i < 5; i += 0.5) {
            final double thickness = i;
            graph_thickness.addItem(i + "px", new MenuBar.Command() {
                @Override
                public void menuSelected(MenuItem selectedItem) {
                    timeline.setGraphOutlineThickness(thickness);
                }
            });
        }

        MenuItem graph_color = graph.addItem("Color", null);
        addGraphColorToMenu("None", new Color(0, 0, 0, 255), graph_color);
        addGraphColorToMenu("Red", Color.RED, graph_color);
        addGraphColorToMenu("Green", Color.GREEN, graph_color);
        addGraphColorToMenu("Blue", Color.BLUE, graph_color);
        addGraphColorToMenu("Orange", Color.ORANGE, graph_color);
        addGraphColorToMenu("Black", Color.BLACK, graph_color);

        MenuItem graph_fillcolor = graph.addItem("Fill Color", null);
        addGraphFillColorToMenu("None", new Color(0, 0, 0, 255),
                graph_fillcolor);
        addGraphFillColorToMenu("Red", Color.RED, graph_fillcolor);
        addGraphFillColorToMenu("Green", Color.GREEN, graph_fillcolor);
        addGraphFillColorToMenu("Blue", Color.BLUE, graph_fillcolor);
        addGraphFillColorToMenu("Orange", Color.ORANGE, graph_fillcolor);
        addGraphFillColorToMenu("Black", Color.BLACK, graph_fillcolor);

        graph.addItem("Toggle stacked", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setGraphStacking(!timeline.isGraphStacked());
            }
        });

        graph.addItem("Toggle caps", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setGraphCapsVisible(!timeline.isGraphCapsVisible());
            }
        });

        graph.addItem("Toggle custom grid", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                if (timeline.getVerticalGridLines() == null) {
                    timeline.setVerticalGridLines(0, 10, 20, 30, 40, 50);
                } else {
                    timeline.setVerticalGridLines(null);
                }
            }
        });

        graph.addItem("Toggle shadows", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setGraphShadowsEnabled(!timeline
                        .isGraphShadowsEnabled());
            }
        });

        graph.addItem("Toggle uniform bars", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setUniformBarThicknessEnabled(!timeline
                        .isUniformBarThicknessEnabled());
            }
        });

        MenuItem vAxisNumFormat = graph.addItem("V-Axis number format", null);
        vAxisNumFormat.addItem("##", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setVerticalAxisNumberFormat(selectedItem.getText());
            }
        });
        vAxisNumFormat.addItem("##.##", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setVerticalAxisNumberFormat(selectedItem.getText());
            }
        });
        vAxisNumFormat.addItem("##.## GB", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setVerticalAxisNumberFormat(selectedItem.getText());
            }
        });
        vAxisNumFormat.addItem("00000.00", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setVerticalAxisNumberFormat(selectedItem.getText());
            }
        });
        vAxisNumFormat.addItem("###,###", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setVerticalAxisNumberFormat(selectedItem.getText());
            }
        });

        // Browser rendering
        MenuItem browser = m.addItem("Browser", null);

        MenuItem browser_color = browser.addItem("Color", null);
        addBrowserColorToMenu("None", new Color(0, 0, 0, 255), browser_color);
        addBrowserColorToMenu("Red", Color.RED, browser_color);
        addBrowserColorToMenu("Green", Color.GREEN, browser_color);
        addBrowserColorToMenu("Blue", Color.BLUE, browser_color);
        addBrowserColorToMenu("Orange", Color.ORANGE, browser_color);
        addBrowserColorToMenu("Black", Color.BLACK, browser_color);

        MenuItem browser_fillcolor = browser.addItem("Fill Color", null);
        addBrowserFillColorToMenu("None", new Color(0, 0, 0, 255),
                browser_fillcolor);
        addBrowserFillColorToMenu("Red", Color.RED, browser_fillcolor);
        addBrowserFillColorToMenu("Green", Color.GREEN, browser_fillcolor);
        addBrowserFillColorToMenu("Blue", Color.BLUE, browser_fillcolor);
        addBrowserFillColorToMenu("Orange", Color.ORANGE, browser_fillcolor);
        addBrowserFillColorToMenu("Black", Color.BLACK, browser_fillcolor);

        MenuItem browser_range = browser.addItem("Show range", null);
        browser_range.addItem("First week", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Container.Indexed ds = datasources.get(0);
                Item firstItem = ds.getItem(ds.getIdByIndex(0));
                Date startDate = (Date) firstItem.getItemProperty(
                        Timeline.PropertyId.TIMESTAMP).getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DAY_OF_WEEK, 7);
                Date endDate = cal.getTime();
                timeline.setVisibleDateRange(startDate, endDate);
            }
        });
        browser_range.addItem("Second week", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Container.Indexed ds = datasources.get(0);
                Item firstItem = ds.getItem(ds.getIdByIndex(0));
                Date startDate = (Date) firstItem.getItemProperty(
                        Timeline.PropertyId.TIMESTAMP).getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(startDate);
                cal.add(Calendar.DAY_OF_WEEK, 7);
                startDate = cal.getTime();
                cal.add(Calendar.DAY_OF_WEEK, 7);
                Date endDate = cal.getTime();
                timeline.setVisibleDateRange(startDate, endDate);
            }
        });
        browser_range.addItem("Last week", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Container.Indexed ds = datasources.get(0);
                Item lastItem = ds.getItem(ds.getIdByIndex(ds.size() - 1));
                Date endDate = (Date) lastItem.getItemProperty(
                        Timeline.PropertyId.TIMESTAMP).getValue();
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                cal.add(Calendar.DAY_OF_WEEK, -7);
                Date startDate = cal.getTime();
                timeline.setVisibleDateRange(startDate, endDate);
            }
        });

        // UI related features
        MenuItem ui = m.addItem("UI", null);

        ui.addItem("Toggle Caption", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                if (timeline.getCaption() == null) {
                    timeline.setCaption("Timeline example caption");
                } else {
                    timeline.setCaption(null);
                }
            }
        });

        ui.addItem("Toggle browser bar", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setBrowserVisible(!timeline.isBrowserVisible());
            }
        });

        ui.addItem("Toggle zoom controls", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setZoomLevelsVisible(!timeline.isZoomLevelsVisible());
            }
        });

        ui.addItem("Toggle zoom caption", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                if (timeline.getZoomLevelsCaption() == null) {
                    timeline.setZoomLevelsCaption("Zoom:");
                } else {
                    timeline.setZoomLevelsCaption(null);
                }
            }
        });

        ui.addItem("Toggle date range", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                timeline.setDateSelectVisible(!timeline.isDateSelectVisible());
            }
        });

        return m;
    }

    private void addNewDatasource() {
        Indexed c = TestContainers
                .createSmoothContainer(datasources.size() * 1223L);
        datasources.add(c);
        timeline.addGraphDataSource(c);
    }

    private void addPreEpochDatasource() {
        Indexed c = TestContainers.createPreEpochDatasource(1454352334);
        datasources.add(c);
        timeline.addGraphDataSource(c);
    }

    private void addCSVDatasource() {
        Indexed c = TestContainers.createCSVContainer(this.getClass()
                .getResource("test.csv").getFile());
        datasources.add(c);
        timeline.addGraphDataSource(c);
    }

    private void removeAllDatasources() {
        datasources.clear();
        timeline.removeAllGraphDataSources();
    }

    private void addGraphColorToMenu(String caption, final Color c,
            MenuItem root) {
        root.addItem(caption, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                for (Indexed ds : datasources) {
                    timeline.setGraphOutlineColor(ds, c);
                }
            }
        });
    }

    private void addGraphFillColorToMenu(String caption, final Color c,
            MenuItem root) {
        root.addItem(caption, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                for (Indexed ds : datasources) {
                    timeline.setGraphFillColor(ds, c);
                }
            }
        });
    }

    private void addBrowserColorToMenu(String caption, final Color c,
            MenuItem root) {
        root.addItem(caption, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                for (Indexed ds : datasources) {
                    timeline.setBrowserOutlineColor(ds, c);
                }
            }
        });
    }

    private void addBrowserFillColorToMenu(String caption, final Color c,
            MenuItem root) {
        root.addItem(caption, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                for (Indexed ds : datasources) {
                    timeline.setBrowserFillColor(ds, c);
                }
            }
        });
    }

    @Override
    protected Component getChart() {
        return layout;
    }
}
