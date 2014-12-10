package com.vaadin.addon.charts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartOptions;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.themes.VaadinTheme;

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
 * <p>
 * The solution is derived from SVGGenerator of 'exporting-server' of Highcharts
 * (not available anymore).
 */
public class SVGGenerator {

    private static final String PHANTOM_EXEC;

    private static File JS_STUFF;
    private static File JS_CONVERTER;

    static {
        String phantomExec = System.getProperty("phantom.exec");
        if (phantomExec != null) {
            PHANTOM_EXEC = phantomExec;
        } else {
            PHANTOM_EXEC = "phantomjs"; // expect it is found from the path
        }

        createTemporaryFiles();
    }

    private static SVGGenerator INSTANCE;

    private Process process;

    /**
     * Creates in new {@link SVGGenerator} instance. The preferred way to get an
     * instance is to use {@link #getInstance()} method.
     */
    public SVGGenerator() {
        try {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add(PHANTOM_EXEC);
            // comment out for debugging
            // commands.add("--remote-debugger-port=9001");

            ensureTemporaryFiles();

            commands.add(JS_CONVERTER.getAbsolutePath());

            commands.add("-jsstuff");
            commands.add(JS_STUFF.getAbsolutePath());

            process = new ProcessBuilder(commands).start();
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
            if (INSTANCE == null) {
                INSTANCE = new SVGGenerator();
            }
        }
        return INSTANCE;
    }

    /**
     * Generates an SVG file using given Vaadin Chart {@link Configuration}.
     *
     * @param conf
     *            the configuration that will be plotted as an SVG graphics
     * @return String containing SVG graphics
     * @see SVGGenerator
     */
    public String generate(Configuration conf) {
        return generate(conf.toString());
    }

    /**
     * Generates an SVG file using given Vaadin Chart {@link Configuration}.
     *
     * @param conf
     *            the configuration that will be plotted as an SVG graphics
     * @param targetWidth
     *            the target width in pixels for the the chart
     * @param targetHeight
     *            the target height in pixels for the chart
     * @return String containing SVG graphics
     * @see SVGGenerator
     */
    public String generate(Configuration conf, int targetWidth, int targetHeight) {
        return generate(conf.toString(), targetWidth, targetHeight);
    }

    /**
     * Generates an SVG file using given JSON configuration object.
     *
     * @param options
     *            the json options string that will be plotted as an SVG
     *            graphics
     * @param targetWidth
     *            the target width in pixels for the the chart
     * @param targetHeight
     *            the target height in pixels for the chart
     * @return String containing SVG graphics
     * @see SVGGenerator
     * @see #generate(Configuration, int, int)
     */
    public synchronized String generate(String options, int targetWidth,
            int targetHeight) {

        return generate(options, getTheme(), targetWidth, targetHeight);
    }

    /**
     * Generates an SVG file using given JSON configuration object.
     *
     * @param options
     *            the json options string that will be plotted as an SVG
     *            graphics
     * @param targetWidth
     *            the target width in pixels for the the chart
     * @param targetHeight
     *            the target height in pixels for the chart
     * @return String containing SVG graphics
     * @see SVGGenerator
     * @see #generate(Configuration, int, int)
     */
    public synchronized String generate(String options, String theme,
            int targetWidth, int targetHeight) {

        try {
            ensureTemporaryFiles();

            OutputStream out = process.getOutputStream();
            out.write((targetWidth + "\n").getBytes());
            out.write((targetHeight + "\n").getBytes());
            out.write(theme.getBytes());
            out.write("\n___Config:start\n".getBytes());
            out.write(options.getBytes());
            out.write("\n___VaadinSVGGenerator:run\n".getBytes());
            out.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

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

    /**
     * Generates an SVG file from given JSON string containing chart options.
     *
     * @param options
     *            the json options string that will be plotted as an SVG
     *            graphics
     * @return SVG generated from options JSON
     */
    public String generate(String options) {
        return generate(options, -1, -1);
    }

    private String getTheme() {
        ChartOptions chartOptions = null;
        try {
            chartOptions = ChartOptions.get();
        } catch (IllegalStateException e) {
            // Thrown when no UI thread is found. This is most likely because we
            // are running an automated process. We will proceed to use a null
            // chartOptions object, and use the default VaadinTheme in lieu of
            // the default Highcharts theme.
        }
        if (chartOptions == null || chartOptions.getTheme() == null) {
            // generate the default Vaadin theme
            VaadinTheme theme = new VaadinTheme();
            return theme.toString();
        }
        return chartOptions.getTheme().toString();
    }

    private void destroyPhantomInstance(String line) {
        process.destroy();
        process = null;
        INSTANCE = null;
        throw new RuntimeException("SVG generation failed: " + line);
    }

    public void destroy() {
        if (process != null) {
            process.destroy();
            INSTANCE = null;
        }
    }

    /**
     * Ensure that the temporary files still exist and
     * re-generate if they have been cleaned away from /tmp
     */
    private static void ensureTemporaryFiles() {
        if (JS_STUFF == null || JS_CONVERTER == null) {
            createTemporaryFiles();
        } else if (!temporaryFilesExist()
                    || JS_STUFF.length() == 0
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
            String[] scripts = new String[]{"jquery.min.js", "highcharts.js",
                    "highcharts-more.js", "funnel.js", "exporting.js",
                    "vaadintheme.js"};
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
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean temporaryFilesExist() {
        return JS_STUFF != null && JS_STUFF.exists()
                && JS_CONVERTER != null && JS_CONVERTER.exists();
    }
}
