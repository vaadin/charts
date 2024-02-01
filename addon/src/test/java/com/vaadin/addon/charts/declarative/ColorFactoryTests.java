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
package com.vaadin.addon.charts.declarative;

import static org.junit.Assert.*;

import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.ui.declarative.DesignException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

public class ColorFactoryTests {

    @Test(expected = DesignException.class)
    public void create_noColorTypeDefined_exceptionIsThrown() {
        Element element = createColorElement(
            "<background-color>" +
                "<stops position=\"0\" color=\"white\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
            "</background-color>");

        ColorFactory.createGradient(element);
    }

    @Test(expected = DesignException.class)
    public void create_multipleColorTypesDefined_exceptionIsThrown() {
        Element element = createColorElement(
            "<background-color>" +
                "<linear-gradient x1=\"0\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>"+
                "<radial-gradient cx=\"0.5\" cy=\"0.3\" r=\"0.7\"></radial-gradient>"+
                "<stops position=\"0\" color=\"white\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
            "</background-color>");

        ColorFactory.createGradient(element);
    }

    @Test
    public void create_linearColor_colorIsCreatedWithCorrectParametersAndStops() {
        Element element = createColorElement(
            "<background-color><linear-gradient x1=\"0\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>" +
                "<stops position=\"0\" color=\"white\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
            "</background-color>");

        GradientColor color = ColorFactory.createGradient(element);

        assertNotNull(color.getLinearGradient());
        assertNull(color.getRadialGradient());
        assertEquals(0d, color.getLinearGradient().getX1());
        assertEquals(0d, color.getLinearGradient().getY1());
        assertEquals(1d, color.getLinearGradient().getX2());
        assertEquals(1d, color.getLinearGradient().getY2());
    }

    @Test
    public void create_gradientColor_colorIsCreatedWithCorrectParametersAndStops() {
        Element element = createColorElement(
            "<background-color><radial-gradient cx=\"0.5\" cy=\"0.3\" r=\"0.7\"></radial-gradient>" +
                "<stops position=\"0\" color=\"white\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
            "</background-color>");

        GradientColor color = ColorFactory.createGradient(element);

        assertNotNull(color.getRadialGradient());
        assertNull(color.getLinearGradient());
        assertEquals(0.5d, color.getRadialGradient().getCx());
        assertEquals(0.3d, color.getRadialGradient().getCy());
        assertEquals(0.7d, color.getRadialGradient().getR());
    }

    @Test(expected = DesignException.class)
    public void create_doubleNotParsable_DesignExceptionIsThrown() {
        Element element = createColorElement(
            "<background-color><linear-gradient x1=\"abc\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>" +
                "<stops position=\"0\" color=\"white\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
                "</background-color>");

        GradientColor color = ColorFactory.createGradient(element);

    }

    @Test(expected = DesignException.class)
    public void create_noColorInStops_DesignExceptionIsThrown() {
        Element element = createColorElement(
            "<background-color><linear-gradient x1=\"0\" y1=\"0\" x2=\"1\" y2=\"1\"></linear-gradient>" +
                "<stops position=\"0\"></stops>" +
                "<stops position=\"1\" color=\"black\"></stops>" +
                "</background-color>");

        GradientColor color = ColorFactory.createGradient(element);

    }

    private Element createColorElement(String configHtml) {
        Document doc = Jsoup.parseBodyFragment(configHtml);
        return doc.body().children().first();
    }
}
