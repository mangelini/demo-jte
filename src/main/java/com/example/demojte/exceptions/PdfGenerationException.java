package com.example.demojte.exceptions;

public class PdfGenerationException extends RuntimeException {

    public PdfGenerationException(String message) {
        super(message);
    }

    public PdfGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}