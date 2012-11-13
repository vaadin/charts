package com.vaadin.addon.charts.client.ui;

import com.vaadin.shared.ComponentState;

public class HighchartComponentState extends ComponentState {

    public String jsonState;
    /**
     * Hacky helper field that can be used to force state chance event. TODO
     * figure out a better method.
     */
    public int paintCount;

}
