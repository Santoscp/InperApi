package com.example.api.services;


import com.example.api.Repositories.ImagenRepository;
import com.example.api.Repositories.ProductoRepository;
import com.example.api.model.Imagen;
import com.example.api.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public List<Imagen> GetAllImagen() {
    	
   
        return imagenRepository.findAll();
    }

    public Optional<Imagen> GetImagenById(Integer id) {
        return imagenRepository.findById(id);
    }

    public List<Imagen> GetImagenByName(String name) {
        return imagenRepository.getByName(name);
    }

}
