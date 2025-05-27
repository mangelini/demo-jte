package com.example.demojte.services.impl;

import com.example.demojte.config.PdfProperties;
import com.example.demojte.exceptions.PdfGenerationException;
import com.example.demojte.services.interfaces.PdfRenderingService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;

@Service
public class OpenHtmlToPdfRenderingService implements PdfRenderingService {

    private static final Logger logger = LoggerFactory.getLogger(OpenHtmlToPdfRenderingService.class);

    private final PdfProperties pdfProperties;

    @Autowired
    public OpenHtmlToPdfRenderingService(PdfProperties pdfProperties) {
        this.pdfProperties = pdfProperties;
    }

    @Override
    public byte[] convertHtmlToPdf(String htmlContent) throws PdfGenerationException {
        try (ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();

            loadCustomFont(builder);
            configureBuilder(builder, htmlContent);

            builder.toStream(pdfOutputStream);
            builder.run();

            return pdfOutputStream.toByteArray();

        } catch (Exception e) {
            throw new PdfGenerationException("Error rendering PDF", e);
        }
    }

    private void configureBuilder(PdfRendererBuilder builder, String htmlContent) throws MalformedURLException {
        String baseUrl = Paths.get("src/main/resources/").toUri().toURL().toString();
        builder.withHtmlContent(htmlContent, baseUrl);
    }

    private void loadCustomFont(PdfRendererBuilder builder) {
        try (InputStream fontStream = new ClassPathResource(pdfProperties.getFontPath()).getInputStream()) {
            builder.useFont(() -> fontStream, pdfProperties.getFontFamilyName());
        } catch (IOException e) {
            logger.warn("Failed to load custom font: {}. PDF will be rendered without this font.",
                    pdfProperties.getFontPath(), e);
        }
    }
}