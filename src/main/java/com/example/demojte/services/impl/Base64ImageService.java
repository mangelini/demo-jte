package com.example.demojte.services.impl;

import com.example.demojte.exceptions.ResourceNotFoundException;
import com.example.demojte.services.interfaces.ImageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class Base64ImageService implements ImageService {

    @Override
    public String loadAndEncodeImage(String imagePath) throws ResourceNotFoundException {
        ClassPathResource resource = new ClassPathResource(imagePath);

        if (!resource.exists()) {
            throw new ResourceNotFoundException("Image not found at classpath path: " + imagePath);
        }

        try (InputStream inputStream = resource.getInputStream()) {
            byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            throw new ResourceNotFoundException("Failed to load image: " + imagePath, e);
        }
    }
}