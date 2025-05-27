package com.example.demojte.controllers;

import com.example.demojte.config.PdfProperties;
import com.example.demojte.services.HomeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
@AllArgsConstructor
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final HomeService homeService;
    private final PdfProperties pdfProperties;

    @GetMapping("/generate")
    public ResponseEntity<Resource> generatePdf() {
        logger.debug("PDF generation request received");

        byte[] pdfBytes = homeService.generatePdf(pdfProperties.getTemplateName());
        Resource resource = new ByteArrayResource(pdfBytes);

        HttpHeaders headers = createPdfHeaders(pdfProperties.getDownloadFileName());

        logger.debug("PDF generated successfully. Size: {} bytes", pdfBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    private HttpHeaders createPdfHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                String.format("attachment; filename=\"%s\"", fileName));
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");
        return headers;
    }
}