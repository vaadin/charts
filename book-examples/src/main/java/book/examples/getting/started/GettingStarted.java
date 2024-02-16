/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2024 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package book.examples.getting.started;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataProviderSeries;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.VerticalLayout;

public class GettingStarted {

    public void creatingYourFirstChartSnippet1() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        Chart chart = new Chart();
        layout.addComponent(chart);
    }

    public void creatingYourFirstChartSnippet2() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Hello Charts!");
    }

    public void addingSomeDataSnippet1() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.getChart().setType(ChartType.LINE);
    }

    public void addingSomeDataSnippet2() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        ChartsData data = new ChartsData();
        DataSeries girls = new DataSeries("Girls");
        for (ShoeSizeInfo shoeSizeInfo : data.getGirlsData()) {
            // Shoe size on the X-axis, age on the Y-axis
            girls.add(new DataSeriesItem(shoeSizeInfo.getSize(),
                    shoeSizeInfo.getAgeMonths() / 12.0f));
        }
        conf.addSeries(girls);
    }

    public void addingSomeDataSnippet3() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.getxAxis().setTitle("Shoe size (EU)");
        conf.getyAxis().setTitle("Age (Years)");
    }

    public void bindAnotherDataSnippet1() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        DataSeries boys = new DataSeries("Boys");
        ChartsData data = new ChartsData();
        for (ShoeSizeInfo shoeSizeInfo : data.getBoysData()) {
            // Shoe size on the X-axis, age on the Y-axis
            boys.add(new DataSeriesItem(shoeSizeInfo.getSize(),
                    shoeSizeInfo.getAgeMonths() / 12.0f));
        }
        conf.addSeries(boys);
    }

    public void configuringTheColors() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        DataSeries girls = new DataSeries("Girls");
        DataSeries boys = new DataSeries("Boys");

        PlotOptionsLine girlsOpts = new PlotOptionsLine();
        girlsOpts.setColor(SolidColor.HOTPINK);
        girls.setPlotOptions(girlsOpts);

        PlotOptionsLine boysOpts = new PlotOptionsLine();
        boysOpts.setColor(SolidColor.BLUE);
        boys.setPlotOptions(boysOpts);
    }

    public void combinationChartPreparationsSnippet1(VaadinRequest request) {
        Chart chart = new Chart();
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        ChartsData data = new ChartsData();
        layout.addComponent(chart);
    }

    public void combinationChartPreparationsSnippet2(VaadinRequest request) {
        Chart chart = new Chart();
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(chart);
    }

    public Chart combinationChartPreparationsSnippet3(ChartsData data) {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Turku, Finland 2013");

        conf.getChart().setType(ChartType.LINE);
        ListDataProvider<WeatherInfo> dataProvider = new ListDataProvider<>(
                data.getWeatherData());
        DataProviderSeries<WeatherInfo> temp = new DataProviderSeries<>(
                dataProvider);

        temp.setName("Temperature");
        temp.setX(WeatherInfo::getInstant);
        temp.setY(WeatherInfo::getMaxTemp);
        conf.addSeries(temp);

        conf.getxAxis().setTitle("Date");
        conf.getxAxis().setType(AxisType.DATETIME);
        conf.getyAxis().setTitle("Temperature (°C)");
        return chart;
    }

    public void addColumnsSnippet1(List<WeatherInfo> weatherInfo) {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();

        ListDataProvider<WeatherInfo> dataProvider = new ListDataProvider<>(
                weatherInfo);
        DataProviderSeries<WeatherInfo> humidity = new DataProviderSeries<>(
                dataProvider);

        humidity.setName("Humidity");
        humidity.setX(WeatherInfo::getInstant);
        humidity.setY(WeatherInfo::getMeanHumidity);
        humidity.setPlotOptions(new PlotOptionsColumn());
        conf.addSeries(humidity);
    }

    public void addColumnsSnippet2(List<WeatherInfo> weatherInfo,
            DataProviderSeries<WeatherInfo> humidity, Configuration conf) {
        YAxis humidityYAxis = new YAxis();
        humidityYAxis.setTitle("Humidity (%)");
        humidityYAxis.setMin(0);
        humidityYAxis.setOpposite(true);
        conf.addyAxis(humidityYAxis);
        humidity.setyAxis(humidityYAxis);
    }

    public Chart addColumnsSnippet3(ChartsData data) {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Turku, Finland 2013");

        conf.getChart().setType(ChartType.LINE);

        ListDataProvider<WeatherInfo> dataProvider = new ListDataProvider<>(
                data.getWeatherData());

        DataProviderSeries<WeatherInfo> temp = new DataProviderSeries<>(
                dataProvider);

        temp.setName("Temperature");
        temp.setX(WeatherInfo::getInstant);
        temp.setY(WeatherInfo::getMaxTemp);

        conf.getxAxis().setTitle("Date");
        conf.getxAxis().setType(AxisType.DATETIME);
        conf.getyAxis().setTitle("Temperature (°C)");

        DataProviderSeries<WeatherInfo> humidity = new DataProviderSeries<>(
                dataProvider);

        humidity.setName("Humidity");
        humidity.setX(WeatherInfo::getInstant);
        humidity.setY(WeatherInfo::getMeanHumidity);
        humidity.setPlotOptions(new PlotOptionsColumn());

        conf.addSeries(humidity);
        conf.addSeries(temp);

        YAxis humidityYAxis = new YAxis();
        humidityYAxis.setTitle("Humidity (%)");
        humidityYAxis.setMin(0);
        humidityYAxis.setOpposite(true);
        conf.addyAxis(humidityYAxis);
        humidity.setyAxis(humidityYAxis);

        return chart;
    }

    public void addColumnsSnippet4(ChartsData data,
            ListDataProvider<WeatherInfo> dataProvider) {
        dataProvider.addFilter(point -> {
            LocalDateTime date = LocalDateTime.ofInstant(point.getInstant(),
                    ZoneId.of("Europe/Helsinki"));
                    return date.getDayOfWeek() == DayOfWeek.SUNDAY;
        });
    }

    public class ChartsData {
        public Collection<ShoeSizeInfo> getGirlsData() {
            return null;
        }

        public Collection<ShoeSizeInfo> getBoysData() {
            return null;
        }

        public Collection<WeatherInfo> getWeatherData() {
            return null;
        }
    }

    public class ShoeSizeInfo {
        public int getSize() {
            return 0;
        }

        public int getAgeMonths() {
            return 0;
        }
    }

    public class WeatherInfo {
        private Instant instant;
        private int meanHumidity;
        private int maxTemp;

        public int getMeanHumidity() {
            return meanHumidity;
        }

        public int getMaxTemp() {
            return maxTemp;
        }

        public void setMeanHumidity(int meanHumidity) {
            this.meanHumidity = meanHumidity;
        }

        public Instant getInstant() {
            return instant;
        }

        public void setDate(Instant instant) {
            this.instant = instant;
        }
    }
}
