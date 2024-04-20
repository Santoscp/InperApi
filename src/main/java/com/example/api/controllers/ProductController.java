package com.example.api.controllers;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Producto;
import com.example.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private ProductService service;
    @Autowired
    private ProductService productoService;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Producto> entity = service.obtenerProductoPorId(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Producto> getProductByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Producto> entity = service.obtenerProductoPorNombre(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Producto>> getProductByType(@PathVariable("type") String type)
            throws RecordNotFoundException {
        List<Producto> entity = service.obtenerProductoPorTipo(type);

        if (entity.isEmpty()) {
            return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Producto>>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}
