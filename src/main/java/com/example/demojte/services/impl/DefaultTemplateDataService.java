package com.example.demojte.services.impl;

import com.example.demojte.models.Product;
import com.example.demojte.services.interfaces.ImageService;
import com.example.demojte.services.interfaces.TemplateDataService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultTemplateDataService implements TemplateDataService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTemplateDataService.class);

    private final ImageService imageService;

    @Value("${app.template.logo-path:static/logo.png}")
    private String logoPath;

    @Value("${app.template.product-count:75}")
    private int productCount;

    private String logoBase64;

    @PostConstruct
    private void init() {
        try {
            this.logoBase64 = imageService.loadAndEncodeImage(logoPath);
        } catch (Exception e) {
            logger.error("Failed to load logo image: {}", logoPath, e);
            this.logoBase64 = null;
        }
    }

    @Override
    public Map<String, Object> prepareTemplateData() {
        Map<String, Object> params = new HashMap<>();

        params.put("title", "Documento di Riconciliazione");
        params.put("currentDate", LocalDateTime.now());
        params.put("pointOfSale", "123");
        params.put("saleDate", LocalDate.of(2025, 12, 12));
        params.put("saleType", "TIPO");

        params.put("products", generateProducts());

        if (logoBase64 != null) {
            params.put("logoBase64", logoBase64);
        }

        return params;
    }

    private List<Product> generateProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= productCount; i++) {
            products.add(new Product("CODE" + i, "Desc" + i));
        }
        return products;
    }
}