package com.vaadin.addon.charts.testbenchtests;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.annotations.BrowserConfiguration;
import com.vaadin.testbench.annotations.BrowserFactory;
import com.vaadin.testbench.annotations.RunOnHub;
import com.vaadin.testbench.commands.TestBenchCommands;
import com.vaadin.testbench.parallel.Browser;
import com.vaadin.testbench.parallel.ParallelTest;

@RunOnHub("tb3-hub.intra.itmill.com")
@BrowserFactory(ChartsBrowserFactory.class)
public abstract class AbstractParallelTest extends ParallelTest {

    protected int TESTPORT;
    protected String BASEURL = getTestBaseUrl();

    private static final String REF_IMAGE_ROOT = "src/test/resources/screenshots/reference";
    protected TestBenchCommands testBench;
    protected static final String ERROR_IMAGE_ROOT = "target/testbench/errors/";

    public AbstractParallelTest() {
        super();
    }

    @Override
    public void setup() throws Exception {
        super.setup();
        new File(ERROR_IMAGE_ROOT).mkdirs();
        Parameters.setScreenshotErrorDirectory(ERROR_IMAGE_ROOT);
        Parameters.setScreenshotComparisonTolerance(0.01);
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
        List<DesiredCapabilities> allBrowsers = new ArrayList<DesiredCapabilities>();
        allBrowsers.add(Browser.IE8.getDesiredCapabilities());
        allBrowsers.add(Browser.IE9.getDesiredCapabilities());
        allBrowsers.add(Browser.IE10.getDesiredCapabilities());
        allBrowsers.add(Browser.IE11.getDesiredCapabilities());
        allBrowsers.add(Browser.FIREFOX.getDesiredCapabilities());
        allBrowsers.add(Browser.CHROME.getDesiredCapabilities());
        allBrowsers.add(Browser.PHANTOMJS.getDesiredCapabilities());
        return allBrowsers;
    }

    protected void configBrowser() {
        testBench.resizeViewPortTo(800, 513);
    }

    protected String getPackageName() {
        return "";
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

    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
