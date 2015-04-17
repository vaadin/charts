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
        String expected = "{\"crosshairs\":true}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansTrue() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(true, true);

        String json = tooltip.toString();
        String expected = "{\"crosshairs\":[true,true]}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansMixed() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(false, true);

        String json = tooltip.toString();
        String expected = "{\"crosshairs\":[false,true]}";

        assertEquals(expected, json);
    }

    @Test
    public void testTwoStyles() {
        Tooltip tooltip = new Tooltip();
        tooltip.setCrosshairs(new CrosshairStyle(10, SolidColor.BLACK,
                DashStyle.SOLID, 0), new CrosshairStyle(5, "#880000",
                DashStyle.DOT, 1));

        String json = tooltip.toString();
        String expected = "{\"crosshairs\":[{\"width\":10,\"color\":\"#000000\",\"dashStyle\":\"Solid\",\"zIndex\":0},{\"width\":5,\"color\":\"#880000\",\"dashStyle\":\"Dot\",\"zIndex\":1}"
                + "]" + "}";

        assertEquals(expected, json);
    }

}
