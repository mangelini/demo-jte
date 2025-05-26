package com.example.demojte;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    private final HomeService homeService;
    private static final String DOWNLOAD_PDF_FILE_NAME = "generated_document.pdf";



    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "/demo")
    public String demo() {
        return "q8_cali";
    }

    @GetMapping(value = "/")
    public ResponseEntity<Resource> downloadGeneratedPdfInMemory() {
        try {
            // 1. Prepare data for the template
            Map<String, Object> params = new HashMap<>();
            /*params.put("userName", "Jane Doe");
            params.put("userEmail", "jane.doe@example.com");
            params.put("hobbies", Arrays.asList("Reading", "Hiking", "Photography"));*/

            // 2. Call the service to render the HTML to a PDF byte array
            byte[] pdfBytes = homeService.renderTemplateToPdfBytes("q8_cali.jte", params);

            // 3. Prepare the byte array as a Resource
            Resource resource = new ByteArrayResource(pdfBytes);

            // 4. Set headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + DOWNLOAD_PDF_FILE_NAME + "\"");

            // 5. Return the ResponseEntity
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfBytes.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}