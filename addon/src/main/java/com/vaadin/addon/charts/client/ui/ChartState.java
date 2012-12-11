package com.vaadin.addon.charts.client.ui;

import com.vaadin.shared.AbstractComponentState;

public class ChartState extends AbstractComponentState {

    public String jsonState;
    /**
     * Hacky helper field that can be used to force state chance event. TODO
     * figure out a better method.
     */
    public int paintCount;

}
