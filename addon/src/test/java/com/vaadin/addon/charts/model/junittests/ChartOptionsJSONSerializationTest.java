/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2023 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.model.junittests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.addon.charts.util.ChartSerialization;
import com.vaadin.ui.UI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static com.vaadin.addon.charts.util.ChartSerialization.toJSON;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ChartOptionsJSONSerializationTest {

    private static final String EmptyThemeJson = "{\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"bar\":{},\"boxplot\":{},\"bubble\":{},\"column\":{},\"columnrange\":{},\"errorbar\":{},\"funnel\":{},\"gauge\":{},\"heatmap\":{},\"line\":{},\"pie\":{},\"polygon\":{},\"pyramid\":{},\"scatter\":{},\"series\":{},\"solidgauge\":{},\"spline\":{},\"treemap\":{},\"waterfall\":{},\"candlestick\":{},\"flags\":{},\"ohlc\":{}},\"credits\":{}}";
    private static final String ThemeWithLinearGradientFormat = "{\"colors\":[{\"linearGradient\":[1.0,2.0,3.0,4.0]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"bar\":{},\"boxplot\":{},\"bubble\":{},\"column\":{},\"columnrange\":{},\"errorbar\":{},\"funnel\":{},\"gauge\":{},\"heatmap\":{},\"line\":{},\"pie\":{},\"polygon\":{},\"pyramid\":{},\"scatter\":{},\"series\":{},\"solidgauge\":{},\"spline\":{},\"treemap\":{},\"waterfall\":{},\"candlestick\":{},\"flags\":{},\"ohlc\":{}},\"credits\":{}}";
    private static final String ThemeWithLinearGradientAndStopsFormat = "{\"colors\":[{\"linearGradient\":[1.0,2.0,3.0,4.0],\"stops\":[[5.0,\"#0000FF\"]]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"bar\":{},\"boxplot\":{},\"bubble\":{},\"column\":{},\"columnrange\":{},\"errorbar\":{},\"funnel\":{},\"gauge\":{},\"heatmap\":{},\"line\":{},\"pie\":{},\"polygon\":{},\"pyramid\":{},\"scatter\":{},\"series\":{},\"solidgauge\":{},\"spline\":{},\"treemap\":{},\"waterfall\":{},\"candlestick\":{},\"flags\":{},\"ohlc\":{}},\"credits\":{}}";
    private static final String ThemeWithRadialGradientFormat = "{\"colors\":[{\"radialGradient\":[1.0,2.0,3.0]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"bar\":{},\"boxplot\":{},\"bubble\":{},\"column\":{},\"columnrange\":{},\"errorbar\":{},\"funnel\":{},\"gauge\":{},\"heatmap\":{},\"line\":{},\"pie\":{},\"polygon\":{},\"pyramid\":{},\"scatter\":{},\"series\":{},\"solidgauge\":{},\"spline\":{},\"treemap\":{},\"waterfall\":{},\"candlestick\":{},\"flags\":{},\"ohlc\":{}},\"credits\":{}}";
    public static final String LangWithFinnishLocale = "{\"colors\":[{\"radialGradient\":[1.0,2.0,3.0]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"bar\":{},\"boxplot\":{},\"bubble\":{},\"column\":{},\"columnrange\":{},\"errorbar\":{},\"funnel\":{},\"gauge\":{},\"heatmap\":{},\"line\":{},\"pie\":{},\"polygon\":{},\"pyramid\":{},\"scatter\":{},\"series\":{},\"solidgauge\":{},\"spline\":{},\"treemap\":{},\"waterfall\":{},\"candlestick\":{},\"flags\":{},\"ohlc\":{}},\"credits\":{}}";

    @Mock
    private UI ui;

    private ChartOptions options;

    @Before
    public void setup() {
        options = ChartOptions.get(ui);
    }

    @Test
    public void toJSON_NoThemeOrLang_EmptyJsonSerialized() {
        assertEquals("{}", toJSON(options));
    }

    @Test
    public void toJSON_ThemeSet_ThemeSerialized() {
        Theme theme = new Theme();
        options.setTheme(theme);

        assertEquals(EmptyThemeJson, toJSON(options));
    }

    @Test
    public void toJSON_ThemeSetWithLinearGradient_ThemeSerializedWithLinearGradient() {
        Theme theme = new Theme();
        theme.setColors(GradientColor.createLinear(1, 2, 3, 4));
        options.setTheme(theme);

        String expected = String.format(ThemeWithLinearGradientFormat, 1, 2, 3,
                4);
        assertEquals(expected, toJSON(options));
    }

    @Test
    public void toJSON_ThemeSetWithRadialGradient_ThemeSerializedWithRadialGradient() {
        Theme theme = new Theme();
        theme.setColors(GradientColor.createRadial(1, 2, 3));
        options.setTheme(theme);

        String expected = String.format(ThemeWithRadialGradientFormat, 1, 2, 3);
        assertEquals(expected, toJSON(options));
    }

    @Test
    public void toJSON_ThemeSetWithColorStops_StopsSerialized() {
        Theme theme = new Theme();
        GradientColor linear = GradientColor.createLinear(1, 2, 3, 4);
        linear.addColorStop(5, SolidColor.BLUE);
        theme.setColors(linear);

        options.setTheme(theme);

        String expected = String.format(ThemeWithLinearGradientAndStopsFormat,
                1, 2, 3, 4, 5, SolidColor.BLUE.toString());
        assertEquals(expected, toJSON(options));
    }

    @Test
    public void toJSON_LangWithFinnishLocale_LocaleSerialized_Months() throws IOException {
        final String[] fiMonths=new String[] {"Tammikuu", "Helmikuu", "Maaliskuu",
                "Huhtikuu", "Toukokuu", "Kesäkuu", "Heinäkuu", "Elokuu",
                "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu"};
        final Lang fi = new Lang();
        fi.setMonths(fiMonths);

        options.setLang(fi);
        String json = toJSON(options);
        ObjectMapper om = ChartSerialization.createObjectMapper();
        ChartOptions chartOptions = om.readValue(json, ChartOptions.class);

        Assert.assertArrayEquals(fiMonths, chartOptions.getLang().getMonths());
    }

    @Test
    public void toJSON_LangWithFinnishLocale_LocaleSerialized_ShortMonths() throws IOException {
        final String[] fiShortMonths=new String[]{"Tammi", "Helmi", "Maalis", "Huhti",
                "Touko", "Kesä", "Heinä", "Elo", "Syys", "Loka", "Marras",
                "Joulu"};
        final Lang fi = new Lang();
        fi.setShortMonths(fiShortMonths);
        options.setLang(fi);
        String json = toJSON(options);
        ObjectMapper om = ChartSerialization.createObjectMapper();
        ChartOptions fromJson = om.readValue(json, ChartOptions.class);

        Assert.assertArrayEquals(fiShortMonths, fromJson.getLang().getShortMonths());
    }

    @Test
    public void toJSON_LangWithFinnishLocale_LocaleSerialized_Days() throws IOException {
        final String[] fiDays=new String[]{"Ma", "Ti", "Ke", "To", "Pe", "La", "Su"};
        final Lang fi = new Lang();
        fi.setWeekdays(fiDays);

        options.setLang(fi);
        String json = toJSON(options);
        ObjectMapper om = ChartSerialization.createObjectMapper();
        ChartOptions chartOptions = om.readValue(json, ChartOptions.class);

        Assert.assertArrayEquals(fiDays,  chartOptions.getLang().getWeekdays());
    }

    @Test
    public void toJSON_itemWithRadialGradientColor_RadialGradientSerialized() {
        GradientColor color = GradientColor.createRadial(0.5, 0.3, 0.7);
        color.addColorStop(0, new SolidColor(255, 128, 0));
        color.addColorStop(1, new SolidColor(128, 64, 0));

        DataSeriesItem item = new DataSeriesItem("Foobar", 45.0);
        item.setColor(color);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[{\"name\":\"Foobar\",\"y\":45.0,\"color\":{\"stops\":[[0.0,\"#FF8000\"],[1.0,\"#804000\"]],\"radialGradient\":{\"cx\":0.5,\"cy\":0.3,\"r\":0.7}}}]}";
        assertEquals(expected, toJSON(series));
    }

}
