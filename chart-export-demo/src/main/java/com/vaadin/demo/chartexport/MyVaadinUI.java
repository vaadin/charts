package com.vaadin.demo.chartexport;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Vaadin Charts Export Demo");
        setContent(new ChartExportDemo());
    }

}
