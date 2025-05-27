package com.example.demojte.services.interfaces;

import java.util.Map;

public interface TemplateRenderingService {
    String renderTemplate(String templateName, Map<String, Object> params);
}
