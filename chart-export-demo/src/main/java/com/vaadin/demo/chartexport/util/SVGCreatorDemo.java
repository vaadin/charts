package com.vaadin.demo.chartexport.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * SVGCreatorDemo is used to run PhantomJs executable to render Highcharts
 * charts and export SVG image of those back as a result. There are two
 * alternative ways to run PhantomJs:</br>
 * <ol>
 * <li>with highcharts-convert.js that caches input and output to files.</li>
 * <li>with highcharts-convert-nofiles.js that is pruned version of the
 * highcharts-convert.js without file caching.</li>
 * </ol>
 * </br> This SVGCreatorDemo is based on Highchart's exporting-server code: <a
 * href=
 * "https://github.com/highslide-software/highcharts.com/blob/master/exporting-server/java/highcharts-export/src/main/java/com/highcharts/export/util/SVGCreator.java"
 * >SVGCreator.java</a> </br> </br> Path to phantomjs executable is needed
 * either in the default path '/usr/local/bin/phantomjs' or in the path
 * configured in src/main/resources/chart-export.properties file.
 */
public class SVGCreatorDemo {

    private String PHANTOM_EXEC = "/usr/local/bin/phantomjs";
    private final String PHANTOM_SCRIPT_DEFAULT = "highcharts-convert.js";
    private final String PHANTOM_SCRIPT_NOFILES = "highcharts-convert-nofiles.js";

    private static final String SVG_HOOK_PREFIX = "SVG:[";
    private static final String SVG_HOOK_SUFFIX = "]";

    private static final SVGCreatorDemo INSTANCE = new SVGCreatorDemo();

    private long exportStartTime;

    private SVGCreatorDemo() {
        ResourceBundle bundle = ResourceBundle.getBundle("chart-export",
                Locale.getDefault());
        if (bundle == null) {
            System.out.println("Couldn't find chart-export.properties");
            return;
        }

        PHANTOM_EXEC = bundle.getString("phantom.exec");
    }

    public static final SVGCreatorDemo getInstance() {
        return INSTANCE;
    }

    /**
     * Creates SVG String with the given options.
     * 
     * @param contextRootLocation
     *            Path to the context root. Web applications's WebContent
     *            folder.
     * @param options
     *            JSON formatted Highchart options.
     * @return SVG String.
     * 
     * @throws IOException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public synchronized String createSVG(String contextRootLocation,
            String options) throws IOException, InterruptedException,
            TimeoutException {

        return createSVG(contextRootLocation, options, false);
    }

    /**
     * @see {@link #createSVG(String, String)}
     * 
     * @param contextRootLocation
     * @param options
     * @param cacheToFile
     *            Write the input and output to file.
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    public synchronized String createSVG(String contextRootLocation,
            String options, boolean cacheToFile) throws IOException,
            InterruptedException, TimeoutException {
        try {
            exportStartTime = System.currentTimeMillis();

            if (cacheToFile) {
                return executeWithChaching(contextRootLocation, options);
            }
            return executeWithoutCaching(contextRootLocation, options);
        } finally {
            System.out.println("Executed SVGCreatorDemo.createSVG in "
                    + (System.currentTimeMillis() - exportStartTime) + "ms");
        }
    }

    private String executeWithoutCaching(String contextRootLocation,
            String options) throws IOException, InterruptedException,
            TimeoutException {
        String commandLine = buildCommand(options,
                getResourceLocation(contextRootLocation, false), null, null);
        System.out.println(commandLine);

        return executeCommandLineReturnSVG(commandLine, 5000);
    }

    private String executeWithChaching(String contextRootLocation,
            String options) throws IOException, InterruptedException,
            TimeoutException {
        String commandLine;
        // create options file
        File infile = createUniqueFile(options, ".json");
        String inFilename = infile.getAbsolutePath();
        String outFilename = inFilename.replaceAll(".json", ".svg");

        commandLine = buildCommand(null,
                getResourceLocation(contextRootLocation, true), inFilename,
                outFilename);
        System.out.println(commandLine);

        int success = executeCommandLine(commandLine, 5000);
        System.out.println("succes " + success);

        if (success == 0) {
            String svg = readFile(outFilename);
            return svg;
        }
        return "no-svg";
    }

    private String getResourceLocation(String contextRootLocation,
            boolean writeToFile) {
        String path = contextRootLocation + "/phantomjs/";
        if (writeToFile) {
            return path + PHANTOM_SCRIPT_DEFAULT;
        }
        return path + PHANTOM_SCRIPT_NOFILES;
    }

    private String buildCommand(String options, String location,
            String inFilename, String outFilename) throws IOException {

        StringBuilder sb = new StringBuilder(PHANTOM_EXEC);
        sb.append(" ").append(location);
        if (options != null) {
            // options must be formatted properly for the command line, no
            // line breaks allowed.
            sb.append(" -jsonopt ").append("'")
                    .append(formatCommandlineJson(options)).append("'");
        }
        if (inFilename != null) {
            sb.append(" -infile ").append(inFilename);
        }
        if (outFilename != null) {
            sb.append(" -outfile ").append(outFilename);
        }

        return sb.toString();
    }

    private String formatCommandlineJson(String json) {
        // Remove all line breaks
        json = json.replaceAll("\n", "");
        json = json.replaceAll("\r", "");
        return json;
    }

    private File createUniqueFile(String content, String extension)
            throws IOException {
        File file = File.createTempFile("tempChart", extension);
        writeFile(file, content);
        return file;
    }

    private void writeFile(File file, String content) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(content);
        } finally {
            bw.close();
            fw.close();
        }
    }

    private String readFile(String path) throws IOException {
        FileInputStream stream = new FileInputStream(new File(path));
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size());
            /* Instead of using default, pass in a decoder. */
            return Charset.forName("utf-8").decode(bb).toString();
        } finally {
            stream.close();
        }
    }

    private static int executeCommandLine(final String commandLine,
            final long timeout) throws IOException, InterruptedException,
            TimeoutException {
        Process process = execute(commandLine);

        return runProcessWorker(process, timeout);
    }

    private static String executeCommandLineReturnSVG(final String commandLine,
            final long timeout) throws IOException, InterruptedException,
            TimeoutException {
        Process process = execute(commandLine);

        String svg = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String currentLine = null;
        while ((currentLine = in.readLine()) != null) {
            if (currentLine.startsWith(SVG_HOOK_PREFIX)
                    && currentLine.endsWith(SVG_HOOK_SUFFIX)) {
                svg = currentLine.substring(SVG_HOOK_PREFIX.length(),
                        currentLine.lastIndexOf(SVG_HOOK_SUFFIX));
                System.out.println(svg);
                break;
            }
            System.out.println("Debug: " + currentLine);
        }
        in.close();

        if (runProcessWorker(process, timeout) != null) {
            return svg;
        }
        return null;
    }

    private static Integer runProcessWorker(Process process, final long timeout)
            throws TimeoutException, InterruptedException {
        Worker worker = new Worker(process);
        worker.start();
        try {
            worker.join(timeout);
            if (worker.exit != null) {
                return worker.exit;
            } else {
                throw new TimeoutException();
            }
        } catch (InterruptedException ex) {
            worker.interrupt();
            Thread.currentThread().interrupt();
            throw ex;
        } finally {
            process.destroy();
        }
    }

    private static Process execute(String commandLine) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(commandLine);
    }

    private static class Worker extends Thread {
        private final Process process;
        private Integer exit;

        private Worker(Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            try {
                exit = process.waitFor();
                System.out.println("exit value thread = " + exit);
            } catch (InterruptedException ignore) {
                return;
            }
        }
    }

}
