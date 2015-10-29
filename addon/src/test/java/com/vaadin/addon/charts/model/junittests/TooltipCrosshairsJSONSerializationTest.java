package com.vaadin.addon.charts.model.junittests;

import org.junit.Test;

public class TooltipCrosshairsJSONSerializationTest {

    @Test
    public void testSimpleBooleans() {

        // TODO Tooltip and PlotOptions.Tooltip are quite different
        // Tooltip tooltip = new Tooltip();
        // tooltip.setCrosshairs(true);
        //
        // String json = tooltip.toString();
        // String expected = "{\"crosshairs\":true}";
        //
        // assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansTrue() {
        // TODO Tooltip and PlotOptions.Tooltip are quite different

        // Tooltip tooltip = new Tooltip();
        // tooltip.setCrosshairs(true, true);
        //
        // String json = tooltip.toString();
        // String expected = "{\"crosshairs\":[true,true]}";
        //
        // assertEquals(expected, json);
    }

    @Test
    public void testTwoBooleansMixed() {
        // TODO Tooltip and PlotOptions.Tooltip are quite different

        // Tooltip tooltip = new Tooltip();
        // tooltip.setCrosshairs(false, true);
        //
        // String json = tooltip.toString();
        // String expected = "{\"crosshairs\":[false,true]}";
        //
        // assertEquals(expected, json);
    }

    @Test
    public void testTwoStyles() {
        // TODO Tooltip and PlotOptions.Tooltip are quite different

        // Tooltip tooltip = new Tooltip();
        // tooltip.setCrosshairs(new CrosshairStyle(10, SolidColor.BLACK,
        // DashStyle.SOLID, 0), new CrosshairStyle(5, "#880000",
        // DashStyle.DOT, 1));
        //
        // String json = tooltip.toString();
        // String expected =
        // "{\"crosshairs\":[{\"width\":10,\"color\":\"#000000\",\"dashStyle\":\"Solid\",\"zIndex\":0},{\"width\":5,\"color\":\"#880000\",\"dashStyle\":\"Dot\",\"zIndex\":1}"
        // + "]" + "}";
        //
        // assertEquals(expected, json);
    }

}
