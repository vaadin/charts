package com.vaadin.addon.charts.testbenchtests;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.examples.other.NoDataToDisplay;

public class NoDataToDisplayTBTest extends AbstractParallelTest {

    @Test
    public void isChartActive_chartHasDoDataToDisplay_chartHasZeroActiveClients() {
        String script = "var clients = vaadin.clients;"
                + "var anyActiveFound = false;"
                + "for (var client in clients) {"
                + "  if (clients[client].isActive()) {"
                + "    anyActiveFound = true;" + "    break;" 
                + "  }"
                + "}"
                + "return anyActiveFound;";
        driver.get(getTestUrl());
        Boolean result = (Boolean) getTestBenchCommandExecutor()
                .executeScript(script);
        Assert.assertFalse("There are no active clients", result);
    }

    @Override
    protected String getTestViewName() {
        return NoDataToDisplay.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}