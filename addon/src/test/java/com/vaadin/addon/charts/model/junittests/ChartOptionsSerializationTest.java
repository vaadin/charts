package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.Lang;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Theme;
import com.vaadin.ui.UI;

@RunWith(MockitoJUnitRunner.class)
public class ChartOptionsSerializationTest {

    private static final String EmptyThemeJson = "{\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"bar\":{},\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"pie\":{},\"line\":{},\"column\":{},\"spline\":{},\"series\":{},\"pyramid\":{},\"waterfall\":{},\"treemap\":{},\"polygon\":{}},\"credits\":{}}";
    private static final String ThemeWithLinearGradientFormat = "{\"colors\":[{\"linearGradient\":[%d.0,%d.0,%d.0,%d.0]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"bar\":{},\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"pie\":{},\"line\":{},\"column\":{},\"spline\":{},\"series\":{},\"pyramid\":{},\"waterfall\":{},\"treemap\":{},\"polygon\":{}},\"credits\":{}}";
    private static final String ThemeWithLinearGradientAndStopsFormat = "{\"colors\":[{\"linearGradient\":[%d.0,%d.0,%d.0,%d.0],\"stops\":[[%d.0,\"%s\"]]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"bar\":{},\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"pie\":{},\"line\":{},\"column\":{},\"spline\":{},\"series\":{},\"pyramid\":{},\"waterfall\":{},\"treemap\":{},\"polygon\":{}},\"credits\":{}}";
    private static final String ThemeWithRadialGradientFormat = "{\"colors\":[{\"radialGradient\":[%d.0,%d.0,%d.0]}],\"chart\":{},\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"xAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"yAxis\":{\"title\":{\"style\":{}},\"subtitle\":{\"style\":{}},\"labels\":{\"style\":{}}},\"labels\":{\"style\":{}},\"legend\":{},\"tooltip\":{\"followPointer\":false,\"style\":{}},\"plotOptions\":{\"bar\":{},\"area\":{},\"arearange\":{},\"areaspline\":{},\"areasplinerange\":{},\"pie\":{},\"line\":{},\"column\":{},\"spline\":{},\"series\":{},\"pyramid\":{},\"waterfall\":{},\"treemap\":{},\"polygon\":{}},\"credits\":{}}";
    public static final String LangWithFinnishLocale = "{\"lang\":{\"decimalPoint\":\",\",\"months\":[\"Tammikuu\",\"Helmikuu\",\"Maaliskuu\",\"Huhtikuu\",\"Toukokuu\",\"Kesäkuu\",\"Heinäkuu\",\"Elokuu\",\"Syyskuu\",\"Lokakuu\",\"Marraskuu\",\"Joulukuu\"],\"shortMonths\":[\"Tammi\",\"Helmi\",\"Maalis\",\"Huhti\",\"Touko\",\"Kesä\",\"Heinä\",\"Elo\",\"Syys\",\"Loka\",\"Marras\",\"Joulu\"],\"weekdays\":[\"Ma\",\"Ti\",\"Ke\",\"To\",\"Pe\",\"La\",\"Su\"]}}";

    @Mock
    private UI ui;

    private ChartOptions options;

    @Before
    public void setup() {
        options = ChartOptions.get(ui);
    }

    @Test
    public void toString_NoThemeOrLang_EmptyJsonSerialized() {
        assertEquals("{}", options.toString());
    }

    @Test
    public void toString_ThemeSet_ThemeSerialized() {
        Theme theme = new Theme();
        options.setTheme(theme);

        assertEquals(EmptyThemeJson, options.toString());
    }

    @Test
    public void toString_ThemeSetWithLinearGradient_ThemeSerializedWithLinearGradient() {
        Theme theme = new Theme();
        theme.setColors(GradientColor.createLinear(1, 2, 3, 4));
        options.setTheme(theme);

        String expected = String.format(ThemeWithLinearGradientFormat, 1, 2, 3,
                4);
        assertEquals(expected, options.toString());
    }

    @Test
    public void toString_ThemeSetWithRadialGradient_ThemeSerializedWithRadialGradient() {
        Theme theme = new Theme();
        theme.setColors(GradientColor.createRadial(1, 2, 3));
        options.setTheme(theme);

        String expected = String.format(ThemeWithRadialGradientFormat, 1, 2, 3);
        assertEquals(expected, options.toString());
    }

    @Test
    public void toString_ThemeSetWithColorStops_StopsSerialized() {
        Theme theme = new Theme();
        GradientColor linear = GradientColor.createLinear(1, 2, 3, 4);
        linear.addColorStop(5, SolidColor.BLUE);
        theme.setColors(linear);

        options.setTheme(theme);

        String expected = String.format(ThemeWithLinearGradientAndStopsFormat,
                1, 2, 3, 4, 5, SolidColor.BLUE.toString());
        assertEquals(expected, options.toString());
    }

    @Test
    public void toString_LangWithFinnishLocale_LocaleSerialized() {
        final Lang fi = new Lang();
        fi.setDecimalPoint(",");
        fi.setShortMonths(new String[] { "Tammi", "Helmi", "Maalis", "Huhti",
                "Touko", "Kesä", "Heinä", "Elo", "Syys", "Loka", "Marras",
                "Joulu" });
        fi.setMonths(new String[] { "Tammikuu", "Helmikuu", "Maaliskuu",
                "Huhtikuu", "Toukokuu", "Kesäkuu", "Heinäkuu", "Elokuu",
                "Syyskuu", "Lokakuu", "Marraskuu", "Joulukuu" });
        fi.setWeekdays(new String[] { "Ma", "Ti", "Ke", "To", "Pe", "La", "Su" });

        options.setLang(fi);

        assertEquals(LangWithFinnishLocale, options.toString());
    }

    @Test
    public void toString_itemWithRadialGradientColor_RadialGradientSerialized() {
        GradientColor color = GradientColor.createRadial(0.5, 0.3, 0.7);
        color.addColorStop(0, new SolidColor(255, 128, 0));
        color.addColorStop(1, new SolidColor(128, 64, 0));

        DataSeriesItem item = new DataSeriesItem("Foobar", 45.0);
        item.setColor(color);

        DataSeries series = new DataSeries();

        series.add(item);

        String expected = "{\"data\":[{\"name\":\"Foobar\",\"y\":45.0,\"color\":{\"stops\":[[0.0,\"#FF8000\"],[1.0,\"#804000\"]],\"radialGradient\":{\"cx\":0.5,\"cy\":0.3,\"r\":0.7}}}]}";
        assertEquals(expected, series.toString());
    }

}
