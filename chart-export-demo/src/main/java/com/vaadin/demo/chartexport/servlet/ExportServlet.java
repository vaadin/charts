package com.vaadin.demo.chartexport.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.vaadin.demo.chartexport.util.MimeType;
import com.vaadin.demo.chartexport.util.SVGCreatorDemo;
import com.vaadin.demo.chartexport.util.SVGRasterizer;
import com.vaadin.demo.chartexport.util.SVGRasterizerException;

/**
 * Highcharts Exporting Server Servlet. </br></br> See the original class that
 * this is based on and which uses the Spring Framework: <a href=
 * "https://github.com/highslide-software/highcharts.com/blob/master/exporting-server/java/highcharts-export/src/main/java/com/highcharts/export/controller/ExportController.java"
 * >ExportController.java</a>.
 * 
 */
public class ExportServlet extends HttpServlet {

    private static final Float MAX_WIDTH = 2000.0F;
    private static final Float MAX_SCALE = 4.0F;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // Highcharts requires support for multipart/form-data (supported with
        // Servlet 3.0 via HttpServletRequest#getParts())

        // Here we don't have Servlet 3.0, but we use Apache Commons FileUpload
        // library instead.
        Map<String, String> parameters = new HashMap<String, String>();
        try {
            List<FileItem> items = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    parameters.put(fieldname, fieldvalue);
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        ServletContext context = request.getSession().getServletContext();

        String svg = parameters.get("svg");
        String type = parameters.get("type");
        String filename = parameters.get("filename");
        String width = parameters.get("width");
        String scale = parameters.get("scale");
        String options = parameters.get("options");
        String constr = parameters.get("constr");
        String callback = parameters.get("callback");

        MimeType mime = getMime(type);
        filename = getFilename(filename);
        Float parsedWidth = widthToFloat(width);
        Float parsedScale = scaleToFloat(scale);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (options != null && !options.isEmpty()) {
            // create a svg file out of the options
            String location = context.getRealPath("/") + "/WEB-INF";

            try {
                svg = SVGCreatorDemo.getInstance().createSVG(location, options);

                if (svg.equals("no-svg")) {
                    throw new ServletException(
                            "Could not create an SVG out of the options");
                }
            } catch (InterruptedException e) {
                throw new ServletException(
                        "Could not create an SVG out of the options", e);
            } catch (TimeoutException e) {
                throw new ServletException(
                        "Could not create an SVG out of the options", e);
            }
        }

        if (svg == null || svg.isEmpty() || svg.equalsIgnoreCase("undefined")) {
            throw new ServletException(
                    "The manadatory svg POST parameter is undefined.");
        }

        try {
            ExportServlet.convert(stream, svg, filename, mime, parsedWidth,
                    parsedScale);
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setHeader("charset", "utf-8");
        response.setContentType(mime.getType());
        response.setContentLength(stream.size());
        response.setHeader("Content-disposition", "attachment; filename=\""
                + filename + "." + mime.name().toLowerCase() + "\"");

        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().write(stream.toByteArray());
    }

    public static void convert(ByteArrayOutputStream stream, String svg,
            String filename, MimeType mime, Float width, Float scale)
            throws IOException, ServletException {

        if (!MimeType.SVG.equals(mime)) {
            try {
                stream = SVGRasterizer.getInstance().transcode(stream, svg,
                        mime, width, scale);
            } catch (SVGRasterizerException sre) {
                System.out
                        .println("Error while transcoding svg file to an image");
                stream.close();
                throw new ServletException(
                        "Error while transcoding svg file to an image", sre);
            } catch (TranscoderException te) {
                System.out
                        .println("Error while transcoding svg file to an image");
                stream.close();
                throw new ServletException(
                        "Error while transcoding svg file to an image", te);
            }
        } else {
            stream.write(svg.getBytes());
        }
    }

    private String getFilename(String name) {
        return (name != null) ? name : "chart";
    }

    private static MimeType getMime(String mime) {
        MimeType type = MimeType.get(mime);
        if (type != null) {
            return type;
        }
        return MimeType.PNG;
    }

    private static Float widthToFloat(String width) {
        if (width != null && !width.isEmpty()
                && !(width.compareToIgnoreCase("undefined") == 0)) {
            Float parsedWidth = Float.valueOf(width);
            if (parsedWidth.compareTo(MAX_WIDTH) > 0) {
                return MAX_WIDTH;
            }
            if (parsedWidth.compareTo(0.0F) > 0) {
                return parsedWidth;
            }
        }
        return null;
    }

    private static Float scaleToFloat(String scale) {
        if (scale != null && !scale.isEmpty()
                && !(scale.compareToIgnoreCase("undefined") == 0)) {
            Float parsedScale = Float.valueOf(scale);
            if (parsedScale.compareTo(MAX_SCALE) > 0) {
                return MAX_SCALE;
            } else if (parsedScale.compareTo(0.0F) > 0) {
                return parsedScale;
            }
        }
        return null;
    }
}
