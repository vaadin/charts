package com.vaadin.addon.charts.testbenchtests;

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

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.addon.charts.examples.other.NoDataToDisplayUI;
import com.vaadin.testbench.annotations.RunLocally;
import com.vaadin.testbench.parallel.Browser;

@RunLocally(Browser.CHROME)
public class NoDataDisplayTBTest extends AbstractParallelTest {

    @Test
    public void isChartActive_chartHasDoDataToDisplay_chartHasZeroActiveClients() throws IOException, AssertionError {
        String script=""+""
            + "var clients = vaadin.clients;"
            + "var anyActiveFound = false;"
            + "for (var client in clients) {"
            + "  if (clients[client].isActive()) {"
            + "    anyActiveFound = true;"
            + "    break;"
            +  "  }"
            + "}"
            + "return anyActiveFound;";
        driver.get(getTestUrl());

        Boolean result =(Boolean) getCommandExecutor().executeScript(script);
        Assert.assertFalse("There are no active clients",result);
    }

    @Override
    protected String getTestViewName() {
        return NoDataToDisplayUI.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "other";
    }
}
