package com.vaadin.addon.charts.testbenchtests;

import java.io.File;
import java.net.InetAddress;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.vaadin.addon.charts.demoandtestapp.TServer;
import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.commands.TestBenchCommands;

public abstract class AbstractTestBenchTest {

    protected static final int TESTPORT = 5678;
    protected static String BASEURL = "http://localhost:" + TESTPORT + "/";
    private static final File REF_IMAGE_ROOT = new File(
            "src/test/resources/screenshots/reference");
    protected WebDriver driver;
    protected TestBenchCommands testBench;
    private Server server;
    protected static final String ERROR_IMAGE_ROOT = "target/testbench/errors/";

    public AbstractTestBenchTest() {
        super();
    }

    @Before
    public void setUp() {
        new File(ERROR_IMAGE_ROOT).mkdirs();
        Parameters.setScreenshotErrorDirectory(ERROR_IMAGE_ROOT);
        Parameters.setScreenshotComparisonTolerance(0.01);
        Parameters.setCaptureScreenshotOnFailure(true);
        try {
            server = TServer.startServer(TESTPORT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Remote web driver fails with error:
                // Error communicating with the remote browser. It may have
                // died.
                // e.printStackTrace();
            }
        }
    }

    protected void startBrowser() {
        prepareDriver();
        // dimension includes browser chrome
        driver.manage().window().setSize(new Dimension(800, 600));
        testBench = (TestBenchCommands) driver;
    }

    protected void prepareDriver() {
        String hubhost = System.getProperty("tb.hub");
        if (hubhost != null && !hubhost.isEmpty()) {
            try {
                BASEURL = InetAddress.getLocalHost().getHostName() + ":"
                        + TESTPORT + "/";
                Capabilities cap = DesiredCapabilities.firefox();
                URL remoteAddress = new URL("http://" + hubhost
                        + ":4444/wd/hub");
                driver = TestBench.createDriver(new RemoteWebDriver(
                        remoteAddress, cap));
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                throw new RuntimeException(e1);
            }
        } else {
            driver = TestBench.createDriver(new FirefoxDriver());
        }
    }

    public File getReferenceImage(String name) {
        return new File(REF_IMAGE_ROOT, name);
    }

    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}