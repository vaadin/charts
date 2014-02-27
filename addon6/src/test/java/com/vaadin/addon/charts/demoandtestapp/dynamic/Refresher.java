package com.vaadin.addon.charts.demoandtestapp.dynamic;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.ProgressIndicator;

public class Refresher extends CssLayout {
    public Refresher() {
        addComponent(new ProgressIndicator());
        setWidth("0px");
        setHeight("0px");
    }

    @Override
    protected String getCss(Component c) {
        return "overflow:hidden;";
    }
}
