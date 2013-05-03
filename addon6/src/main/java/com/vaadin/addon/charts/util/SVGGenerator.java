package com.vaadin.addon.charts.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.Configuration;

/**
 * This class can be used to render the same Chart displayed on clients browser
 * into SVG format. SVG is nowadays well supported format and it can be
 * transferred to virtually any graphic format.
 * <p>
 * The implementation uses PhantomJS to render the chart on server side. Install
 * it separately from <a href="http://phantomjs.org/">phantomjs.org</a>. After
 * installation, if not installed so that it is available on your path, set
 * "phantom.exec" system property to point to your phantomjs binary.
 * <p>
 * The solution is derived form Highchart's exporting-server code: <a href=
 * "https://github.com/highslide-software/highcharts.com/blob/master/exporting-server/java/highcharts-export/src/main/java/com/highcharts/export/util/SVGCreator.java"
 * >SVGCreator.java</a>.
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

        try {
            JS_STUFF = File.createTempFile("jsstuff", ".js");
            JS_STUFF.deleteOnExit();
            FileOutputStream out = new FileOutputStream(JS_STUFF);
            String[] scripts = new String[] { "jquery.min.js", "highcharts.js",
                    "highcharts-more.js", "exporting.js", "vaadintheme.js" };
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

            JS_CONVERTER = File.createTempFile("converter", ".js");
            JS_CONVERTER.deleteOnExit();
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

    private static SVGGenerator INSTANCE;

    private Process process;

    private int port;

    /**
     * Creates in new {@link SVGGenerator} instance. The preferred way to get an
     * instance is to use {@link #getInstance()} method.
     * 
     * @param port
     *            the port used by the PhantomJS service
     */
    public SVGGenerator(int port) {
        // TODO create free port search
        this.port = port;
        try {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add(PHANTOM_EXEC);
            // comment out for debugging
            // commands.add("--remote-debugger-port=9001");

            commands.add(JS_CONVERTER.getAbsolutePath());

            commands.add("-jsstuff");
            commands.add(JS_STUFF.getAbsolutePath());

            commands.add("-port");
            commands.add("" + port);

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
        // instances to
        // ensure PhantomJS don't start to eat memory. With quick test, the
        // memory isn't a problem
        synchronized (SVGGenerator.class) {
            if (INSTANCE == null) {
                INSTANCE = new SVGGenerator(7878);
            }
        }
        return INSTANCE;
    }

    /**
     * Generates SVG file using given Vaadin Chart {@link Configuration}.
     * 
     * @param conf
     *            the configuration that will be plotted as an SVG graphics
     * @return String containing SVG graphics
     * @see SVGGenerator
     */
    public synchronized String generate(Configuration conf) {
        return generate(conf.toString());
    }

    /**
     * @param options
     * @return SVG generated from options json
     */
    public synchronized String generate(String options) {
        // long exportStartTime = System.currentTimeMillis();
        URL url;
        try {
            url = new URL("http://127.0.0.1:" + port + "/");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStream out = connection.getOutputStream();
            out.write(options.getBytes());
            out.close();
            InputStream in = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(in, baos);
            in.close();
            String line = new String(baos.toByteArray());
            if (line == null || !line.startsWith("<svg")) {
                process.destroy();
                process = null;
                INSTANCE = null;
                throw new RuntimeException("SVG generation failed!!");
            }
            return line;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        if (process != null) {
            process.destroy();
        }
    }

}
