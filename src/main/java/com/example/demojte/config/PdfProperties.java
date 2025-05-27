package com.example.demojte.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app.pdf")
public class PdfProperties {
    private String downloadFileName = "generated_document.pdf";
    private String templateName = "pages/pos.jte";
    private String fontPath = "fonts/Helvetica.ttf";
    private String fontFamilyName = "Helvetica";
}