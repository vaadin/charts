package com.vaadin.addon.charts.junittests;


import com.vaadin.addon.charts.model.PlotOptionsBubble;
import com.vaadin.server.Sizeable;
import org.junit.Test;

public class PlotOptionsTest {
    @Test(expected = IllegalArgumentException.class)
    public void plotOptions_setSizeWithCMUnit_raiseException() {
        PlotOptionsBubble plotOptions= new PlotOptionsBubble();
        plotOptions.setMaxSize(10, Sizeable.Unit.CM);

    }
}
