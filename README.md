# JTE Templates with Spring Boot

This project serves as a proof of concept for integrating the Java Template Engine (JTE) with Spring Boot.

It demonstrates the following JTE features:

*   Templates
*   Partial templates (e.g., for a header section)
*   CSS styling
*   Base64 encoding for images

The application generates an HTML document from a JTE template and then converts it into a PDF file using the OpenHtmlToPdf library.

To generate the PDF, start the application and navigate to `localhost:8080/api/pdf/generate` in your web browser.