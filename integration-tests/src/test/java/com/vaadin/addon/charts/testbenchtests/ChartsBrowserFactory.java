package com.vaadin.addon.charts.testbenchtests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.DefaultBrowserFactory;

public class ChartsBrowserFactory extends DefaultBrowserFactory {

    @Override
    public DesiredCapabilities create(Browser browser) {
        switch (browser) {
        case IE8:
            return createIE(browser, "8");
        case IE9:
            return createIE(browser, "9");
        case IE10:
            return createIE(browser, "10");
        case IE11:
            return createIE(browser, "11");
        case PHANTOMJS:
            DesiredCapabilities phantom2 = create(browser, "2", Platform.LINUX);
            // Hack for the test cluster
            phantom2.setCapability("phantomjs.binary.path", "/usr/bin/phantomjs2");
            return phantom2;
        case CHROME:
            return create(browser, "40", Platform.VISTA);
        case FIREFOX:
        default:
            return createFirefox(browser);
        }
    }

    private DesiredCapabilities createFirefox(Browser browser) {
        DesiredCapabilities desiredCapabilities =
            create(browser, "24", Platform.XP);
        // Configuring an empty Firefox profile fixes tests
        // where a data point is clicked.
        //    - pointClick, select and unselect in ServerSideEvents tests
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        return desiredCapabilities;
    }

    private DesiredCapabilities createIE(Browser browser, String version) {
        DesiredCapabilities capabilities = create(browser, version,
                Platform.WINDOWS);
        capabilities.setCapability(
                InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        return capabilities;
    }

    @Override
    public DesiredCapabilities create(Browser browser, String version) {
        return create(browser);
    }
}