package com.vaadin.addon.charts.model.junittests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.vaadin.addon.charts.model.CrosshairStyle;
import com.vaadin.addon.charts.model.DashStyle;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.style.SolidColor;

public class TooltipCrosshairsJsonTest {

    @Test
    public void testSimpleBooleans() {

        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(true);

        String json = tooltip.toString();
        String expected = "{\n  \"crosshairs\": true\n}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansTrue() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(true, true);

        String json = tooltip.toString();
        String expected = "{\n  \"crosshairs\": [\n    true,\n    true\n  ]\n}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansMixed() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(false, true);

        String json = tooltip.toString();
        String expected = "{\n  \"crosshairs\": [\n    false,\n    true\n  ]\n}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoStyles() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(new CrosshairStyle(10, SolidColor.BLACK,
                DashStyle.SOLID, 0), new CrosshairStyle(5, "#880000",
                DashStyle.DOT, 1));

        String json = tooltip.toString();
        String expected = "{\n" + "  \"crosshairs\": [\n" + "    {\n"
                + "      \"width\": 10,\n" + "      \"color\": \"#000000\",\n"
                + "      \"dashStyle\": \"Solid\",\n" + "      \"zIndex\": 0\n"
                + "    },\n" + "    {\n" + "      \"width\": 5,\n"
                + "      \"color\": \"#880000\",\n"
                + "      \"dashStyle\": \"Dot\",\n" + "      \"zIndex\": 1\n"
                + "    }\n" + "  ]\n" + "}";

        assertEquals(expected, json);
    }

}
