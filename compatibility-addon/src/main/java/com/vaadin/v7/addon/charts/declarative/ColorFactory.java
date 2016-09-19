package com.vaadin.v7.addon.charts.declarative;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2015 Vaadin Ltd
 * %%
 * This program is available under Commercial Vaadin Add-On License 3.0
 * (CVALv3).
 *
 * See the file licensing.txt distributed with this software for more
 * information about licensing.
 *
 * You should have received a copy of the CVALv3 along with this program.
 * If not, see <https://vaadin.com/license/cval-3>.
 * #L%
 */

import java.io.Serializable;

import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.GradientColor;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.declarative.DesignException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class ColorFactory implements Serializable {

    static GradientColor createGradient(Element element) {
        GradientColor gradientColor = createGradientColor(element);
        addStops(gradientColor, element);
        return gradientColor;
    }

    private static GradientColor createGradientColor(Element element) {
        Elements linearGradients = element.getElementsByTag("linear-gradient");
        Elements radialGradients = element.getElementsByTag("radial-gradient");
        if(!linearGradients.isEmpty() && radialGradients.isEmpty()) {
            Element linearGradient = linearGradients.first();
            double x1 = parseDoubleAttribute(linearGradient, "x1");
            double y1 = parseDoubleAttribute(linearGradient, "y1");
            double x2 = parseDoubleAttribute(linearGradient, "x2");
            double y2 = parseDoubleAttribute(linearGradient, "y2");
            return GradientColor.createLinear(x1,y1,x2,y2);
        }
        if(!radialGradients.isEmpty() && linearGradients.isEmpty()) {
            Element radialGradient = radialGradients.first();
            double cx = parseDoubleAttribute(radialGradient, "cx");
            double cy = parseDoubleAttribute(radialGradient, "cy");
            double r = parseDoubleAttribute(radialGradient, "r");
            return GradientColor.createRadial(cx,cy,r);
        }
        throw new DesignException("Cannot create color for element: "+element.nodeName());
    }

    private static void addStops(GradientColor gradientColor, Element element) {
        Elements stops = element.getElementsByTag("stops");
        for (Element stop : stops) {
            double position = parseDoubleAttribute(stop, "position");
            String color = stop.attr("color");
            if(color == null || "".equals(color)) {
                throw new DesignException("No color defined in stops: "+stop);
            }
            gradientColor.addColorStop(position, new SolidColor(color));
        }
    }

    private static double parseDoubleAttribute(Element element, String attributeKey) {
        try {
            String value = element.attr(attributeKey);
            return Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            throw new DesignException("Cannot parse double attibute "+attributeKey+" from "+element);
        }
    }

}
