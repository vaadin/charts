/*
 * Vaadin Charts Addon
 *
 * Copyright (C) 2012-2025 Vaadin Ltd
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
package com.vaadin.addon.charts.testbenchtests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Assume;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.RetryRule;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.BrowserFactory;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.commands.TestBenchCommands;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.BrowserUtil;
import com.vaadin.testbench.parallel.ParallelTest;
import com.vaadin.testbench.parallel.setup.SetupDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunOnHub("tb3-hub.intra.itmill.com")
@BrowserFactory(ChartsBrowserFactory.class)
public abstract class AbstractParallelTest extends ParallelTest {

    @Rule
    public RetryRule maxAttempts = new RetryRule(runLocally() ? 1 : 2);

    protected int TESTPORT;
    protected String BASEURL = getTestBaseUrl();

    private static final String REF_IMAGE_ROOT = "src/test/resources/screenshots/reference";
    protected TestBenchCommands testBench;
    protected static final String ERROR_IMAGE_ROOT = "target/testbench/errors/";
    protected static final String HUB_NAME_PROPERTY = "com.vaadin.testbench.Parameters.hubHostname";
    private String hubHostName;
    private Boolean runLocally;
    private Browser localBrowser;

    public AbstractParallelTest() {
        super();
    }

    @Override
    public void setup() throws Exception {
        hubHostName = System.getProperty(HUB_NAME_PROPERTY);
        // override local driver behaviour, so we can easily specify local
        // PhantomJS with a system property
        if (runLocally()) {
            WebDriver driver = new SetupDriver().setupLocalDriver(localBrowser);
            setDriver(driver);
        } else {
            super.setup();
        }
        new File(ERROR_IMAGE_ROOT).mkdirs();
        Parameters.setScreenshotErrorDirectory(ERROR_IMAGE_ROOT);
        Parameters.setScreenshotComparisonTolerance(0.05);
        Parameters.setScreenshotReferenceDirectory(REF_IMAGE_ROOT);

        try {
            TESTPORT = 9998;
            BASEURL = getTestBaseUrl();
        } catch (Exception e) {
            e.printStackTrace();
        }
        testBench = (TestBenchCommands) getDriver();

        configBrowser();
    }

    @Override
    protected String getHubHostname() {
        if (hubHostName != null) {
            return hubHostName;
        } else {
            return super.getHubHostname();
        }
    }

    private boolean runLocally() {
        if (runLocally == null) {
            if (getBooleanProperty("localPhantom")) {
                runLocally = true;
                localBrowser = Browser.PHANTOMJS;
            } else if (getBooleanProperty("localChrome")) {
                runLocally = true;
                localBrowser = Browser.CHROME;
                WebDriverManager.chromedriver().setup();
            } else if (getBooleanProperty("localFirefox")) {
                runLocally = true;
                localBrowser = Browser.FIREFOX;
                WebDriverManager.firefoxdriver().setup();
            } else if (getBooleanProperty("localEdge")) {
                runLocally = true;
                localBrowser = Browser.EDGE;
                WebDriverManager.edgedriver().setup();
            } else {
                runLocally = false;
            }
        }
        return runLocally;
    }

    /**
     * Waits until the chart element has been rendered on screen. This is
     * necessary in the cases where the test grid is overloaded.
     */
    protected void waitUntilChartRendered() {
        waitForElementPresent(By.className("highcharts-container"), 90);
        getCommandExecutor().waitForVaadin();
    }

    protected void waitForElementPresent(By locator, int timeout) {
        waitUntil(ExpectedConditions.presenceOfElementLocated(locator),
                timeout);
    }

    private boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(System.getProperty(key));
    }

    private String getTestBaseUrl() {
        return "http://" + findAutoHostname() + ":" + TESTPORT + "/";
    }

    /**
     * Tries to automatically determine the IP address of the machine the test
     * is running on.
     *
     * @return An IP address of one of the network interfaces in the machine.
     * @throws RuntimeException
     *             if there was an error or no IP was found
     */
    private String findAutoHostname() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface current = interfaces.nextElement();
                if (!current.isUp() || current.isLoopback()
                        || current.isVirtual()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = current.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress current_addr = addresses.nextElement();
                    if (current_addr.isLoopbackAddress()) {
                        continue;
                    }
                    if (current_addr.isSiteLocalAddress()) {
                        return current_addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException("Could not enumerate ");
        }
        throw new RuntimeException(
                "No compatible (192.168.*) ip address found.");
    }

    @BrowserConfiguration
    public List<DesiredCapabilities> getBrowsersToTest() {
        List<DesiredCapabilities> allBrowsers = new ArrayList<>();
        if (runLocally()) {
            allBrowsers.add(localBrowser.getDesiredCapabilities());
        } else {
            allBrowsers.add(Browser.IE11.getDesiredCapabilities());
            allBrowsers.add(Browser.FIREFOX.getDesiredCapabilities());
            allBrowsers.add(Browser.CHROME.getDesiredCapabilities());
            allBrowsers.add(Browser.PHANTOMJS.getDesiredCapabilities());
        }
        return allBrowsers;
    }

    protected void configBrowser() {
        testBench.resizeViewPortTo(800, 513);
    }

    protected String getPackageName() {
        return "";
    }

    /**
     * Compares current screen using the TestViewName as base for screenshot
     * reference file
     *
     * @throws IOException
     */
    protected void captureAndCompare() throws IOException {
        compareScreen(getTestViewName());

    }

    /**
     * Compares current screen using the TestViewName and the suffix as base for
     * screenshot reference file
     *
     * @param suffix
     *            used in reference file name
     * @throws IOException
     */
    protected void captureAndCompare(String suffix) throws IOException {
        compareScreen(getTestViewName() + "-" + suffix);

    }

    private void compareScreen(String referenceName) throws IOException {
        assertTrue(testBench(driver).compareScreen(referenceName));
    }

    protected abstract String getTestViewName();

    protected String getTestUrl() {
        return getTestUrl(false);
    }

    protected String getTestUrl(boolean restartApplication) {
        String result = BASEURL;
        if (!getPackageName().isEmpty()) {
            result += getPackageName() + "/";
        }
        result += getTestViewName();
        if (restartApplication) {
            result += "?restartApplication";
        }
        return result;
    }

    protected void waitForVaadin() {
        getCommandExecutor().waitForVaadin();
    }

    /**
     * This method is just a workaround until the waitForVaadin() would work
     * also when a chart is dynamically changed. Currently, it only works
     * for initial rendering of the chart.
     */
    protected void waitForDynamicChanges() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Call this method if you want to skip the test on some specific browser.
     * For example, some versions of PhantomJS does not fire onContextMenu event on right click so
     * that browser could be skipped for a test which relays on it.
     *
     *
     * @param reason why the browser is skipped. This will be shown in test results.
     * @param browser which is wanted to be skipped
     */
    protected void skipBrowser(String reason, Browser... browser) {
        for (int i = 0; i < browser.length; i++) {
            skipBrowser(reason, browser[i]);
        }
    }

    private void skipBrowser(String reason, Browser browser) {
        DesiredCapabilities capabilities = getDesiredCapabilities();
        switch (browser) {
        case FIREFOX:
            Assume.assumeFalse("Skipped <" + reason + ">", BrowserUtil.isFirefox(capabilities));
            break;
        case CHROME:
            Assume.assumeFalse("Skipped <" + reason + ">", BrowserUtil.isChrome(capabilities));
            break;
        case SAFARI:
            Assume.assumeFalse("Skipped <" + reason + ">", BrowserUtil.isSafari(capabilities));
            break;
        case IE11:
            Assume.assumeFalse("Skipped <" + reason + ">", BrowserUtil.isIE(capabilities, 11));
            break;
        case PHANTOMJS:
            Assume.assumeFalse("Skipped <" + reason + ">", BrowserUtil.isPhantomJS(capabilities));
            break;
        default: throw new RuntimeException("Unknown browser: "+browser);
        }
    }
}
