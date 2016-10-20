package com.vaadin.addon.charts.testbenchtests;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.CheckboxClickEvent;
import com.vaadin.addon.charts.LegendItemClickEvent;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointSelectEvent;
import com.vaadin.addon.charts.PointUnselectEvent;
import com.vaadin.addon.charts.SeriesHideEvent;
import com.vaadin.addon.charts.SeriesShowEvent;
import com.vaadin.addon.charts.XAxesExtremesChangeEvent;
import com.vaadin.addon.charts.YAxesExtremesChangeEvent;
import com.vaadin.addon.charts.examples.dynamic.ServerSideEvents;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.Series;
import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.ui.Component;

public class ServerSideEventsTBTest extends AbstractParallelTest {


    @Override
    protected void configBrowser() {
        testBench.resizeViewPortTo(800, 700);
    }

    @Override
    protected String getTestViewName() {
        return ServerSideEvents.class.getSimpleName();
    }

    @Override
    protected String getPackageName() {
        return "dynamic";
    }

    private void openTestUI() {
        driver.get(getTestUrl());
        waitUntilChartRendered();
    }

    @Test
    public void chartClick_occured_eventIsFired() {
        openTestUI();
        disableAxesEvents();
        WebElement chart = driver.findElement(By.className("vaadin-chart"));

        new Actions(driver).moveToElement(chart, 200, 200).click().build()
                .perform();

        assertLastEventIsType(ChartClickEvent.class);
    }

    @Test
    public void pointClick_occured_eventIsFired() {
        skipBrowser(
                "Datapoint click through Testbench does not work correctly",
                Browser.CHROME, Browser.PHANTOMJS);
        openTestUI();

        WebElement firstMarker = findLastDataPointOfTheFirstSeries();

        click(firstMarker);

        assertFirstHistoryEventIsType(PointClickEvent.class);
    }

    @Test
    public void legendItemClick_occuredWhileVisibilityTogglingDisabled_eventIsFired() {
        openTestUI();
        WebElement disableVisibilityToggling = findDisableVisibityToggle();
        click(disableVisibilityToggling);
        WebElement legendItem = findLegendItem();

        click(legendItem);

        assertLastEventIsType(LegendItemClickEvent.class);
    }

    @Test
    public void legendItemClick_occuredWhileVisibilityTogglingEnabled_eventAndSeriesHideEventAreFired() {
        openTestUI();
        WebElement legendItem = findLegendItem();

        click(legendItem);

        assertLastEventIsType(SeriesHideEvent.class);
        assertFirstHistoryEventIsType(LegendItemClickEvent.class);
    }

    @Test
    public void checkBoxClick_occured_eventIsFired() {
        openTestUI();
        disableAxesEvents();
        WebElement checkBox = findCheckBox();

        click(checkBox);

        assertLastEventIsType(CheckboxClickEvent.class);
    }

    @Test
    public void checkBoxClick_secondCheckboxClicked_secondSeriesIsReturned() {
        openTestUI();
        disableAxesEvents();
        WebElement secondCheckBox = findSecondCheckbox();

        click(secondCheckBox);

        CheckboxClickEvent checkboxClickEvent = readCheckboxEventDetails();
        Assert.assertNotNull(checkboxClickEvent.getSeries());
        Assert.assertEquals("1", checkboxClickEvent.getSeries().getId());
        Assert.assertEquals(1, checkboxClickEvent.getSeriesItemIndex());
    }

    @Test
    public void checkBoxClick_seriesWasNotSelected_checkBoxIsChecked() {
        openTestUI();
        disableAxesEvents();
        WebElement secondCheckBox = findSecondCheckbox();

        click(secondCheckBox);

        CheckboxClickEvent checkboxClickEvent = readCheckboxEventDetails();
        Assert.assertTrue(checkboxClickEvent.isChecked());
    }

    @Test
    public void hideSeries_occuredFromLegendClick_eventIsFired() {
        openTestUI();
        WebElement legendItem = findLegendItem();

        click(legendItem);

        assertLastEventIsType(SeriesHideEvent.class);
    }

    @Test
    public void hideSeries_occuredFromServer_eventIsFired() {
        openTestUI();
        WebElement hideSeries = findHideFirstSeriesButton();

        click(hideSeries);

        assertLastEventIsType(SeriesHideEvent.class);
    }

    @Test
    public void showSeries_occuredFromLegendClick_eventIsFired() {
        openTestUI();
        WebElement legendItem = findLegendItem();
        click(legendItem);

        click(legendItem);

        assertLastEventIsType(SeriesShowEvent.class);
    }

    @Test
    public void showSeries_occuredFromServer_eventIsFired() {
        openTestUI();
        WebElement hideSeriesToggle = findHideFirstSeriesButton();
        click(hideSeriesToggle);

        click(hideSeriesToggle);

        assertLastEventIsType(SeriesShowEvent.class);
    }

    @Test
    public void setXAxesExtremes_occured_eventIsFired() {
        skipBrowser("Selecting area through Testbench does not work correctly",
                Browser.CHROME, Browser.PHANTOMJS);
        openTestUI();
        WebElement chart = driver.findElement(By.className("vaadin-chart"));

        new Actions(driver).moveToElement(chart, 200, 100).click()
                .clickAndHold().moveByOffset(50, 0).release().build().perform();

        assertLastEventIsType(XAxesExtremesChangeEvent.class);
    }

    @Test
    public void setYAxesExtremes_occured_eventIsFired() {
        skipBrowser("Selecting area through Testbench does not work correctly",
                Browser.CHROME, Browser.PHANTOMJS);
        openTestUI();
        WebElement chart = driver.findElement(By.className("vaadin-chart"));

        new Actions(driver).moveToElement(chart, 150, 120).click()
                .clickAndHold().moveByOffset(80, 40).release().build().perform();

        // There are two y axis. One event for each should be fired.
        assertLastEventIsType(YAxesExtremesChangeEvent.class);
        assertNthHistoryEventIsType(YAxesExtremesChangeEvent.class, 2);
    }

    @Test
    public void unselect_occured_eventIsFired() {
        skipBrowser(
                "Datapoint click through Testbench does not work correctly",
                Browser.CHROME, Browser.PHANTOMJS);
        openTestUI();
        disableAxesEvents();
        WebElement lastDataPointOfTheFirstSeries = findLastDataPointOfTheFirstSeries();

        click(lastDataPointOfTheFirstSeries);

        assertLastEventIsType(PointUnselectEvent.class);
    }

    @Test
    public void select_occured_eventIsFired() {
        skipBrowser(
                "Datapoint click through Testbench does not work correctly",
                Browser.CHROME, Browser.PHANTOMJS);
        openTestUI();
        disableAxesEvents();
        WebElement lastDataPointOfTheFirstSeries = findLastDataPointOfTheFirstSeries();

        click(lastDataPointOfTheFirstSeries);

        assertNthHistoryEventIsType(PointSelectEvent.class, 1);
    }

    private void assertLastEventIsType(
            Class<? extends Component.Event> expectedEvent) {
        LabelElement lastEvent = $(LabelElement.class).id("lastEvent");
        Assert.assertEquals(expectedEvent.getSimpleName(), lastEvent.getText());
    }

    private void assertFirstHistoryEventIsType(
            Class<? extends Component.Event> expectedEvent) {
        LabelElement lastEvent = $(LabelElement.class).id("event0");
        String eventHistory = lastEvent.getText();
        Assert.assertNotNull(eventHistory);
        String eventType = eventHistory.split(":")[0];
        Assert.assertEquals(expectedEvent.getSimpleName(), eventType);
    }

    private void assertNthHistoryEventIsType(
            Class<? extends Component.Event> expectedEvent, int historyIndex) {
        LabelElement lastEvent = $(LabelElement.class).id(
                "event" + historyIndex);
        String eventHistory = lastEvent.getText();
        Assert.assertNotNull(eventHistory);
        String eventType = eventHistory.split(":")[0];
        Assert.assertEquals(expectedEvent.getSimpleName(), eventType);
    }

    private CheckboxClickEvent readCheckboxEventDetails() {
        String detailsJson = $(LabelElement.class).id("eventDetails").getText();

        Gson gson = new GsonBuilder().registerTypeAdapter(Series.class,
                new DataSeriesDeserializer()).create();

        return gson.fromJson(detailsJson, CheckboxClickEvent.class);
    }

    private void click(WebElement secondCheckBox) {
        new Actions(driver).click(secondCheckBox).build().perform();
        getTestBenchCommandExecutor().waitForVaadin();
    }

    private WebElement findHideFirstSeriesButton() {
        return $(ButtonElement.class).first();
    }

    private WebElement findLastDataPointOfTheFirstSeries() {
        return driver.findElement(By.cssSelector(".highcharts-markers > path"));
    }

    private WebElement findLegendItem() {
        return driver.findElement(By.className("highcharts-legend-item"));
    }

    private WebElement findSecondCheckbox() {
        return driver.findElements(
                By.cssSelector(".vaadin-chart > input[type='checkbox']"))
                .get(1);
    }

    private WebElement findCheckBox() {
        return driver.findElement(By
                .cssSelector(".vaadin-chart > input[type='checkbox']"));
    }

    private WebElement findDisableVisibityToggle() {
        return driver.findElement(By
                .cssSelector(".v-checkbox > input[type='checkbox']"));
    }

    private void disableAxesEvents() {
        $(CheckBoxElement.class).id("XAxesExtremes").click();
        $(CheckBoxElement.class).id("YAxesExtremes").click();
    }

    private static class DataSeriesDeserializer implements
            JsonDeserializer<Series> {
        @Override
        public Series deserialize(JsonElement series, Type type,
                JsonDeserializationContext jdc) throws JsonParseException {
            return new Gson().fromJson(series, DataSeries.class);
        }
    }

}
