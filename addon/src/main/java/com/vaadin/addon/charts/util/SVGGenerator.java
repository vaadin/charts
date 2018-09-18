package com.vaadin.addon.charts.util;

/*
 * #%L
 * Vaadin Charts
 * %%
 * Copyright (C) 2012 - 2014 Vaadin Ltd
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.themes.ValoLightTheme;

/**
 * This class can be used to render a Chart displayed on the browser of the
 * client as SVG. SVG is a well supported format and it can be transferred to
 * virtually any graphic format.
 * <p>
 * The implementation uses PhantomJS to render the chart on server side. Install
 * version 1.9 or newer separately from <a
 * href="http://phantomjs.org/">phantomjs.org</a>. After installation either
 * ensure that phantomjs binary is available on PATH or set "phantom.exec"
 * system property to point into it.
 */
public class SVGGenerator {

    private static final String PHANTOM_EXEC;

    private static File JS_STUFF;
    private static File JS_CONVERTER;

    /**
     * Charset for communication with PhantomJS should always be UTF-8 and not
     * default charset.
     * 
     * PhantomJS default console output encoding is UTF-8 http://goo.gl/aklNQa
     */
    private Charset charset = Charset.forName("UTF-8");

    static {
        String phantomExec = System.getProperty("phantom.exec");
        if (phantomExec != null) {
            PHANTOM_EXEC = phantomExec;
        } else {
            PHANTOM_EXEC = "phantomjs"; // expect it is found from the path
        }

        createTemporaryFiles();
    }

    // The process which is created in getInstance() method if needed
    private static Process PHANTOM_JS_PROCESS;
    // The phantomJS process given to generator when instance is created
    private Process phantomJSProcess;

    // Input data for SVG generation
    private String options;
    private String theme;
    private String lang;
    private int targetWidth = -1;
    private int targetHeight = -1;
    private boolean timeline;

    /**
     * Creates in new {@link SVGGenerator} instance. The preferred way to get an
     * instance is to use {@link #getInstance()} method.
     */
    public SVGGenerator(Process process) {
        phantomJSProcess = process;
    }

    protected static Process startPhantomJS() {
        try {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add(PHANTOM_EXEC);
            // comment out for debugging
            // commands.add("--remote-debugger-port=9001");

            ensureTemporaryFiles();

            commands.add(JS_CONVERTER.getAbsolutePath());

            commands.add("-jsstuff");
            commands.add(JS_STUFF.getAbsolutePath());

            final Process process = new ProcessBuilder(commands).start();
            final BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String readLine = bufferedReader.readLine();
            if (!readLine.contains("ready")) {
                throw new RuntimeException("PHANTOM JS NOT READY");
            }
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    super.run();
                    if (process != null) {
                        System.out.println("Shutting down PhantomJS instance");
                        process.destroy();
                    }
                }
            });

            return process;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return an {@link SVGGenerator} instance
     */
    public static final SVGGenerator getInstance() {
        // TODO pool several instances for better performance, and possibly kill
        // instances to ensure PhantomJS doesn't start to eat memory. With quick
        // test, the memory isn't a problem
        synchronized (SVGGenerator.class) {
            if (PHANTOM_JS_PROCESS == null) {
                PHANTOM_JS_PROCESS = startPhantomJS();
            }
        }
        return new SVGGenerator(PHANTOM_JS_PROCESS);
    }

    /**
     *
     * @param theme
     *            The theme that will be used to create the SVG graphics. The
     *            default is the theme value in {@link ChartOptions#get()}
     *            object.
     * @return
     */
    public SVGGenerator withTheme(String theme) {
        this.theme = theme;
        return this;
    }

    /**
     *
     * @param lang
     *            The lang options that will be used to create the SVG graphics.
     *            The default is the lang value in {@link ChartOptions#get()}
     *            object.
     * @return
     */
    public SVGGenerator withLang(String lang) {
        this.lang = lang;
        return this;
    }

    /**
     *
     * @param width
     *            The target width in pixels for the the chart. The default
     *            value is -1.
     * @return
     */
    public SVGGenerator withWidth(int width) {
        targetWidth = width;
        return this;
    }

    /**
     *
     * @param height
     *            The target height in pixels for the chart. The default value
     *            is -1.
     * @return
     */
    public SVGGenerator withHeight(int height) {
        targetHeight = height;
        return this;
    }

    /**
     *
     * @param timeline
     *            The boolean if timeline should be visible in the chart. The
     *            default value is false;
     * @return
     */
    public SVGGenerator withTimeline(boolean timeline) {
        this.timeline = timeline;
        return this;
    }

    /**
     * Generates an SVG file using given JSON configuration object.
     *
     * @param conf
     *            the configuration that will be plotted as an SVG graphics
     *
     * @return String containing SVG graphics
     * @see #generate(String)
     */
    public synchronized String generate(Configuration conf) {
        return generate(ChartSerialization.toJSON(conf));
    }

    /**
     * Generates an SVG file using given JSON configuration object.
     *
     * @param options
     *            the json options string that will be plotted as an SVG
     *            graphics
     *
     * @return String containing SVG graphics
     * @see SVGGenerator
     * @see #withLang(String)
     * @see #withTheme(String)
     * @see #withTimeline(boolean)
     * @see #withHeight(int)
     * @see #withWidth(int)
     */
    public synchronized String generate(String options) {
        readDefaultInputValuesIfNeeded();

        try {
            ensureTemporaryFiles();

            OutputStream out = phantomJSProcess.getOutputStream();
            out.write(getBytes((timeline ? 1 : 0) + "\n"));
            out.write(getBytes(targetWidth + "\n"));
            out.write(getBytes(targetHeight + "\n"));
            out.write(getBytes(theme));
            out.write(getBytes("\n___Lang:start\n"));
            out.write(getBytes(lang));
            out.write(getBytes("\n___Config:start\n"));
            out.write(getBytes(options));
            out.write(getBytes("\n___VaadinSVGGenerator:run\n"));
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    phantomJSProcess.getInputStream(), charset));

            String line = reader.readLine();
            while (!line.startsWith("<svg")) {
                if (line.startsWith("Render failed")) {
                    destroyPhantomInstance(line);
                }
                if (line.startsWith("[object ")
                        || line.startsWith("Highcharts")) {
                    line = reader.readLine();
                } else {
                    destroyPhantomInstance(line);
                }
            }
            return line;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readDefaultInputValuesIfNeeded() {
        if (theme == null) {
            theme = getTheme();
        }
        if (lang == null) {
            lang = getLang();
        }
    }

    private byte[] getBytes(String string) {
        return string.getBytes(charset);
    }

    private String getTheme() {
        ChartOptions chartOptions = null;
        try {
            chartOptions = ChartOptions.get();
        } catch (IllegalStateException e) {
            // Thrown when no UI thread is found. This is most likely because we
            // are running an automated process. We will proceed to use a null
            // chartOptions object, and use the default VaadinTheme
        }
        if (chartOptions == null || chartOptions.getTheme() == null) {
            // generate the default Vaadin theme
            ValoLightTheme theme = new ValoLightTheme();
            return ChartSerialization.toJSON(theme);
        }
        return ChartSerialization.toJSON(chartOptions.getTheme());
    }

    private String getLang() {
        ChartOptions chartOptions = null;
        try {
            chartOptions = ChartOptions.get();
        } catch (IllegalStateException e) {
            // Thrown when no UI thread is found. This is most likely because we
            // are running an automated process.
        }
        if (chartOptions == null || chartOptions.getLang() == null) {
            return "{}";
        }
        return ChartSerialization.toJSON(chartOptions.getLang());
    }

    private void destroyPhantomInstance(String line) {
        phantomJSProcess.destroy();
        phantomJSProcess = null;
        PHANTOM_JS_PROCESS = null;
        throw new RuntimeException("SVG generation failed: " + line);
    }

    public void destroy() {
        if (phantomJSProcess != null) {
            phantomJSProcess.destroy();
            phantomJSProcess = null;
            PHANTOM_JS_PROCESS = null;
        }
    }

    /**
     * Ensure that the temporary files still exist and re-generate if they have
     * been cleaned away from /tmp
     */
    private static void ensureTemporaryFiles() {
        if (JS_STUFF == null || JS_CONVERTER == null) {
            createTemporaryFiles();
        } else if (!temporaryFilesExist() || JS_STUFF.length() == 0
                || JS_CONVERTER.length() == 0) {
            writeTemporaryFileContents();
        }
    }

    private static void createTemporaryFiles() {
        try {
            JS_STUFF = File.createTempFile("jsstuff", ".js");
            JS_STUFF.deleteOnExit();
            JS_CONVERTER = File.createTempFile("converter", ".js");
            JS_CONVERTER.deleteOnExit();

            writeTemporaryFileContents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTemporaryFileContents() {
        try {
            if (!JS_STUFF.exists()) {
                JS_STUFF.createNewFile();
                JS_STUFF.deleteOnExit();
            }
            FileOutputStream out = new FileOutputStream(JS_STUFF);
            String[] scripts = new String[] { "highstock.js",
                    "highcharts-more.js", "funnel.js", "exporting.js",
                    "heatmap.js", "solid-gauge.js", "highcharts-3d.js",
                    "vaadintheme.js", "treemap.js" };
            for (String string : scripts) {
                InputStream resourceAsStream = Chart.class
                        .getResourceAsStream("/com/vaadin/addon/charts/client/"
                                + string);
                IOUtils.copy(resourceAsStream, out);
                resourceAsStream.close();
            }
            InputStream resourceAsStream = SVGGenerator.class
                    .getResourceAsStream("vaadin-charts-formatter.js");
            IOUtils.copy(resourceAsStream, out);
            resourceAsStream.close();

            out.close();

            if (!JS_CONVERTER.exists()) {
                JS_CONVERTER.createNewFile();
                JS_CONVERTER.deleteOnExit();
            }
            out = new FileOutputStream(JS_CONVERTER);
            resourceAsStream = SVGGenerator.class
                    .getResourceAsStream("phantomconverter.js");
            IOUtils.copy(resourceAsStream, out);
            resourceAsStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean temporaryFilesExist() {
        return JS_STUFF != null && JS_STUFF.exists() && JS_CONVERTER != null
                && JS_CONVERTER.exists();
    }
}
