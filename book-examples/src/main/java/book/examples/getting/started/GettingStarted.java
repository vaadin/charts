package book.examples.getting.started;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.ContainerDataSeries;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.VerticalLayout;

import java.util.Collection;
import java.util.Date;

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
            girls.add(new DataSeriesItem(
                    shoeSizeInfo.getSize(),
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
            boys.add(new DataSeriesItem(
                    shoeSizeInfo.getSize(),
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

    public Chart combinationChartPreparationsSnippet3() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Turku, Finland 2013");

        conf.getChart().setType(ChartType.LINE);
        Collection<WeatherInfo> weatherInfo = null;
        BeanItemContainer<WeatherInfo> weatherContainer =
                new BeanItemContainer<WeatherInfo>(
                        WeatherInfo.class, weatherInfo);

        ContainerDataSeries temp =
                new ContainerDataSeries(weatherContainer);
        temp.setName("Temperature");
        temp.setXPropertyId("date");
        temp.setYPropertyId("maxTemp");

        conf.addSeries(temp);
        conf.getxAxis().setTitle("Date");
        conf.getxAxis().setType(AxisType.DATETIME);
        conf.getyAxis().setTitle("Temperature (°C)");
        return chart;
    }

    public void addColumnsSnippet1() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        Collection<WeatherInfo> weatherInfo = null;
        BeanItemContainer<WeatherInfo> weatherContainer =
                new BeanItemContainer<WeatherInfo>(
                        WeatherInfo.class, weatherInfo);
        ContainerDataSeries humidity =
                new ContainerDataSeries(weatherContainer);
        humidity.setName("Humidity");
        humidity.setXPropertyId("date");
        humidity.setYPropertyId("meanHumidity");

        humidity.setPlotOptions(new PlotOptionsColumn());
        conf.addSeries(humidity);
    }

    public void addColumnsSnippet2() {
        Chart chart = new Chart();
        Configuration conf = chart.getConfiguration();
        Collection<WeatherInfo> weatherInfo = null;
        BeanItemContainer<WeatherInfo> weatherContainer =
                new BeanItemContainer<WeatherInfo>(
                        WeatherInfo.class, weatherInfo);
        ContainerDataSeries humidity =
                new ContainerDataSeries(weatherContainer);
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

        BeanItemContainer<WeatherInfo> weatherContainer =
                new BeanItemContainer<WeatherInfo>(
                        WeatherInfo.class, data.getWeatherData());

        ContainerDataSeries temp =
                new ContainerDataSeries(weatherContainer);
        temp.setName("Temperature");
        temp.setXPropertyId("date");
        temp.setYPropertyId("maxTemp");

        conf.getxAxis().setTitle("Date");
        conf.getxAxis().setType(AxisType.DATETIME);
        conf.getyAxis().setTitle("Temperature (°C)");

        ContainerDataSeries humidity =
                new ContainerDataSeries(weatherContainer);
        humidity.setName("Humidity");
        humidity.setXPropertyId("date");
        humidity.setYPropertyId("meanHumidity");
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

    public void addColumnsSnippet4(ChartsData data) {
        BeanItemContainer<WeatherInfo> weatherContainer =
                new BeanItemContainer<WeatherInfo>(
                        WeatherInfo.class, data.getWeatherData());
        weatherContainer.addContainerFilter(new Container.Filter() {
            @Override
            public boolean passesFilter(Object o, Item item)
                    throws UnsupportedOperationException {
                Date date = (Date)item.getItemProperty("date")
                        .getValue();
                return date.getDay() == 0;
            }

            @Override
            public boolean appliesToProperty(Object o) {
                return "date".equals(o);
            }
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
        private Date date;
        private int meanHumidity;

        public int getMeanHumidity() {
            return meanHumidity;
        }

        public void setMeanHumidity(int meanHumidity) {
            this.meanHumidity = meanHumidity;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
