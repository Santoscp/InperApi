package com.example.api.controllers;


import com.example.api.Exception.RecordNotFoundException;


import com.example.api.model.Producto;

import com.example.api.services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/inper/producto")
public class ProductoController {

    @Autowired
    private ProductoService prodcutoService;

    @GetMapping("/getall")
    public List<Producto> getArticulos() {
        return prodcutoService.GetAllProduct(); // Verifica que este método esté correctamente configurado.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("IdArticulo") Integer id)
            throws RecordNotFoundException {
        Optional<Producto> entity = prodcutoService.GetProductById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Producto>> getProductoByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Producto> entity = prodcutoService.GetProdcutoByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    







}
