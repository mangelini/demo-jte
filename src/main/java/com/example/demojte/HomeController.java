package com.example.demojte;

import com.example.demojte.models.Product;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class HomeController {

    private final HomeService homeService;
    private static final String DOWNLOAD_PDF_FILE_NAME = "generated_document.pdf";

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Resource> generatePdf() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("title", "Documento di Riconciliazione");
            params.put("currentDate", LocalDateTime.now());
            params.put("pointOfSale", "123");
            params.put("saleDate", LocalDate.of(2025, 12, 12));
            params.put("saleType", "TIPO");

            List<Product> productList = new ArrayList<>();

            for (int i = 0; i < 75; i++) {
                productList.add(new Product("CODE" + i, "Desc" + i));
            }

            params.put("products", productList);

            byte[] pdfBytes = homeService.renderTemplateToPdfBytes("pages/pos.jte", params);

            Resource resource = new ByteArrayResource(pdfBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + DOWNLOAD_PDF_FILE_NAME + "\"");

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