package com.vaadin.addon.charts.model.junittests;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.UI;

@RunWith(MockitoJUnitRunner.class)
public class StopJSONSerializationTest {

    @Mock
    private UI ui;

    private ChartOptions options;

    @Before
    public void setup() {
        options = ChartOptions.get(ui);
    }

    @Test
    public void toJSON_itemWithRadialGradientColor_RadialGradientSerialized() {
        final Chart chart = new Chart();
        final Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SOLIDGAUGE);
        YAxis yaxis = configuration.getyAxis();

        Stop stop1 = new Stop(0.1f, SolidColor.GREEN);
        Stop stop2 = new Stop(0.5f, SolidColor.YELLOW);
        Stop stop3 = new Stop(0.9f, SolidColor.RED);
        yaxis.setStops(new Stop[] { stop1, stop2, stop3 });

        GradientColor color = GradientColor.createRadial(0.5, 0.3, 0.7);
        color.addColorStop(0, new SolidColor(255, 128, 0));
        color.addColorStop(1, new SolidColor(128, 64, 0));

        DataSeriesItem item = new DataSeriesItem("Foobar", 45.0);
        item.setColor(color);

        DataSeries series = new DataSeries();

        series.add(item);

        // stops array should look like this
        // stops: [
        // [0.1, '#55BF3B'], // green
        // [0.5, '#DDDF0D'], // yellow
        // [0.9, '#DF5353'] // red
        // ]

        String expected = "{\"axisIndex\":0,"
                + "\"stops\":[[0.1,\"#008000\"],[0.5,\"#FFFF00\"],[0.9,\"#FF0000\"]]}";

        assertEquals(expected, toJSON(yaxis));
    }

}
