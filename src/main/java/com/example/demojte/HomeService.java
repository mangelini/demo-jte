package com.example.demojte;

import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@Service
public class HomeService {
    private final TemplateEngine templateEngine;
    private String logoBase64;

    @Autowired
    public HomeService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        try {
            this.logoBase64 = loadAndEncodeImage("static/logo.png");
        } catch (IOException e) {
            e.printStackTrace();
            this.logoBase64 = null;
        }
    }

    /**
     * Loads an image from the classpath, converts it to a Base64 string.
     *
     * @param imagePath The path to the image in the classpath (e.g., "static/logo.png").
     * @return Base64 encoded string of the image.
     * @throws IOException If there's an issue reading the image file.
     */
    private String loadAndEncodeImage(String imagePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(imagePath);
        if (!resource.exists()) {
            throw new IOException("Image not found at path: " + imagePath);
        }
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
            return Base64.getEncoder().encodeToString(imageBytes);
        }
    }

    /**
     * Renders a JTE template to an HTML string.
     *
     * @param templateName The name of the JTE template.
     * @param params       Parameters for the template.
     * @return HTML content as a String.
     */
    private String renderTemplateToHtmlString(String templateName, Map<String, Object> params) {
        StringOutput output = new StringOutput();
        templateEngine.render(templateName, params, output);
        return output.toString();
    }

    /**
     * Renders a JTE template to HTML and then converts it to a PDF byte array using OpenHTMLToPDF.
     * The Base64 encoded logo will be added to the template parameters.
     *
     * @param templateName The name of the JTE template (e.g., "index.jte").
     * @param params       A map of parameters to pass to the template.
     * @return The generated PDF as a byte array.
     * @throws IOException If there's an issue with IO operations.
     */
    public byte[] renderTemplateToPdfBytes(String templateName, Map<String, Object> params) throws IOException {
        if (this.logoBase64 != null) {
            params.put("logoBase64", this.logoBase64);
        }

        String htmlContent = renderTemplateToHtmlString(templateName, params);

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();

        try (InputStream fontStream = new ClassPathResource("fonts/Helvetica.ttf").getInputStream()) {
            builder.useFont(() -> fontStream, "Helvetica");
        } catch (IOException e) {
            System.err.println("Failed to load font: Helvetica.ttf. Ensure it's in src/main/resources/fonts/");
        }

        String baseUrl = Paths.get("src/main/resources/").toUri().toURL().toString();
        builder.withHtmlContent(htmlContent, baseUrl);

        builder.toStream(pdfOutputStream);
        try {
            builder.run();
        } catch (Exception e) {
            throw new IOException("Error rendering PDF: " + e.getMessage(), e);
        }

        return pdfOutputStream.toByteArray();
    }
}