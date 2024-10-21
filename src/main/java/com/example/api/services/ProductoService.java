package com.example.api.services;



import com.example.api.Repositories.ProductoRepository;

import com.example.api.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> GetAllProduct() {
    	
   
        return productoRepository.findAll();
    }

    public Optional<Producto> GetProductById(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> GetProdcutoByName(String name) {
        return productoRepository.getByName(name);
    }






}
