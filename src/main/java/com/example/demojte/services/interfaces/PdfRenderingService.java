package com.example.demojte.services.interfaces;

import com.example.demojte.exceptions.PdfGenerationException;

public interface PdfRenderingService {
    byte[] convertHtmlToPdf(String htmlContent) throws PdfGenerationException;
}