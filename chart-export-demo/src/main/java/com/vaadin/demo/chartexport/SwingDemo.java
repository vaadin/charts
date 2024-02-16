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
package com.vaadin.demo.chartexport;

import java.io.IOException;
import java.io.StringReader;

import javax.swing.JFrame;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.dom.svg.SVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.util.SVGGenerator;

public class SwingDemo {

    public static void display(Configuration conf) {
        String svg = SVGGenerator.getInstance().generate(conf);

        String parser = XMLResourceDescriptor.getXMLParserClassName();

        SVGDocumentFactory documentFactory = new SAXSVGDocumentFactory(parser,
                true);
        SVGDocument svgdoc;
        try {
            svgdoc = documentFactory.createSVGDocument(null, new StringReader(
                    svg));
            JSVGCanvas jsvgCanvas = new JSVGCanvas();
            jsvgCanvas.setSVGDocument(svgdoc);

            JFrame f = new JFrame();
            f.setSize(600, 400);
            f.getContentPane().add(jsvgCanvas);
            f.pack();
            f.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
