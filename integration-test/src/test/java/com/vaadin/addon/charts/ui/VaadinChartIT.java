package com.vaadin.addon.charts.ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VaadinChartIT {

	private final ChromeDriver driver = new ChromeDriver();

	@Before
	public void setUp() {
		driver.get("http://localhost:8080");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void checkChartDisplayed() {
		waitUntilPresent(By.tagName("vaadin-chart"));
		final WebElement chart = driver.findElementByTagName("vaadin-chart");
		assertNotNull(chart);
		final WebElement title = getElementFromShadowRoot(chart, By.className("highcharts-title"));
		assertTrue(title.getText().contains("First Chart for Flow!"));
	}

	private void waitUntilPresent(By by) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	private WebElement getElementFromShadowRoot(WebElement shadowRootOwner, By by) {
		WebElement shadowRoot = (WebElement) driver.executeScript("return arguments[0].shadowRoot", shadowRootOwner);
		assertNotNull("Could not locate shadowRoot in the element", shadowRoot);
		return shadowRoot.findElements(by).stream().findFirst()
				.orElseThrow(() -> new AssertionError("Could not find required element in the shadowRoot"));
	}
}
