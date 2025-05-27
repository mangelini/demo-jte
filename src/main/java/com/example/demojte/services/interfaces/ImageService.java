package com.example.demojte.services.interfaces;

import com.example.demojte.exceptions.ResourceNotFoundException;

public interface ImageService {
    String loadAndEncodeImage(String imagePath) throws ResourceNotFoundException;
}