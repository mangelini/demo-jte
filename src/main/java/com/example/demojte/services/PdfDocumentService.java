package com.example.demojte.services;

import com.example.demojte.exceptions.PdfGenerationException;
import com.example.demojte.services.interfaces.PdfRenderingService;
import com.example.demojte.services.interfaces.TemplateDataService;
import com.example.demojte.services.interfaces.TemplateRenderingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class PdfDocumentService {
    private static final Logger logger = LoggerFactory.getLogger(PdfDocumentService.class);

    private final TemplateRenderingService templateRenderingService;
    private final PdfRenderingService pdfRenderingService;
    private final TemplateDataService templateDataService;

    /**
     * Generates a PDF from a JTE template.
     *
     * @param templateName The name of the JTE template
     * @return The generated PDF as a byte array
     * @throws PdfGenerationException If there's an issue during PDF generation
     */
    public byte[] generatePdf(String templateName) throws PdfGenerationException {
        try {
            logger.debug("Starting PDF generation for template: {}", templateName);

            Map<String, Object> templateData = templateDataService.prepareTemplateData();
            String htmlContent = templateRenderingService.renderTemplate(templateName, templateData);
            byte[] pdfBytes = pdfRenderingService.convertHtmlToPdf(htmlContent);

            logger.debug("PDF generation completed successfully. Size: {} bytes", pdfBytes.length);
            return pdfBytes;

        } catch (Exception e) {
            logger.error("Failed to generate PDF for template: {}", templateName, e);
            throw new PdfGenerationException("Failed to generate PDF for template: " + templateName, e);
        }
    }
}
