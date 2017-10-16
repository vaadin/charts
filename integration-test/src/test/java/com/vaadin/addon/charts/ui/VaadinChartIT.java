package com.vaadin.addon.charts.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.parallel.BrowserUtil;
import com.vaadin.testbench.parallel.ParallelTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunOnHub
public class VaadinChartIT extends ParallelTest {

	@Override
	protected String getHubURL() {
		final String username = System.getProperty("SAUCE_USERNAME");
		final String accessKey = System.getProperty("SAUCE_ACCESS_KEY");
		return "http://" + username + ":" + accessKey + "@localhost:4445/wd/hub";
	}

	@Before
	public void setUp() throws InterruptedException {
		getDriver().get("http://localhost:8080");
	}

	@Test
	public void checkChartDisplayed() {
		waitUntilPresent(By.tagName("vaadin-chart"));
		final WebElement chart = findElement(By.tagName("vaadin-chart"));
		assertNotNull(chart);
		final WebElement title = getElementFromShadowRoot(chart, By.className("highcharts-title"));
		assertTrue(title.getText().contains("First Chart for Flow!"));
	}

	private void waitUntilPresent(By by) {
		new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	private WebElement getElementFromShadowRoot(WebElement shadowRootOwner, By by) {
		WebElement shadowRoot = (WebElement) executeScript("return arguments[0].shadowRoot", shadowRootOwner);
		assertNotNull("Could not locate shadowRoot in the element", shadowRoot);
		return shadowRoot.findElements(by).stream().findFirst()
				.orElseThrow(() -> new AssertionError("Could not find required element in the shadowRoot"));
	}

	/**
	 * @return all supported browsers which are actively tested
	 */
	public List<DesiredCapabilities> getAllBrowsers() {
		List<DesiredCapabilities> allBrowsers = new ArrayList<>();
			allBrowsers.add(BrowserUtil.chrome());
		return Collections.unmodifiableList(allBrowsers);
	}

	@BrowserConfiguration
	public List<DesiredCapabilities> getBrowserConfiguration() {
		return getAllBrowsers();
	}
}
