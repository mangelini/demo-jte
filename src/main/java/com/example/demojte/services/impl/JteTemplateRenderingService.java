package com.example.demojte.services.impl;

import com.example.demojte.services.interfaces.TemplateRenderingService;
import gg.jte.TemplateEngine;
import gg.jte.output.StringOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JteTemplateRenderingService implements TemplateRenderingService {

    private final TemplateEngine templateEngine;

    @Autowired
    public JteTemplateRenderingService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String renderTemplate(String templateName, Map<String, Object> params) {
        StringOutput output = new StringOutput();
        templateEngine.render(templateName, params, output);
        return output.toString();
    }
}