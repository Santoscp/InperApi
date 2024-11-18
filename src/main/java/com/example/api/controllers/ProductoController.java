package com.example.api.controllers;

import com.example.api.Repositories.ProductoRepository;
import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Producto;
import com.example.api.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/prueba/articulos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping("/getall")
    public List<Producto> getArticulos() {
        return productoService.GetAllProduct();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Producto> entity = productoService.GetProductById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    // Obtener producto por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Producto>> getProductoByName(@PathVariable("name") String name) 
            throws RecordNotFoundException {
        List<Producto> entity = productoService.GetProdcutoByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

//    // Eliminar producto
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) {
        try {
            productoService.eliminarProductoPorId(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar producto
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        // Verificar si el producto existe utilizando el servicio
        if (!productoService.GetProductById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }

        producto.setId(id);

        // Procesar imagen si está en formato Base64
        if (producto.getImagen() != null && producto.getImagen().length > 0) {
            String imagenBase64 = new String(producto.getImagen());
            if (imagenBase64.startsWith("data:image/jpeg;base64,")) {
                imagenBase64 = imagenBase64.substring("data:image/jpeg;base64,".length());
                byte[] imagenBytes = Base64.getDecoder().decode(imagenBase64);
                producto.setImagen(imagenBytes);
            }
        }

        // Actualizar el producto
        productoService.actualizarProducto(producto);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/addProduct")
    public ResponseEntity<String> agregarProducto(@RequestBody Producto producto) {
        productoService.agregarProducto(producto.getNombre(),producto.getPrecio_medio());
        return new ResponseEntity<>("Producto añadido exitosamente", HttpStatus.CREATED);
    }



}
