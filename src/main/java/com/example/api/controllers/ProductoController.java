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
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            productoService.eliminarProductoPorId(id);
            response.put("message", "Producto eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    // Actualizar producto
    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> actualizarProducto(
            @PathVariable Integer id,
            @RequestParam("nombre") String nombre,
            @RequestParam("precio_medio") BigDecimal precioMedio,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen
    ) {
        // Verificar si el producto existe
        if (!productoService.GetProductById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }

        // Obtener el producto existente
        Producto producto = productoService.GetProductById(id).get();

        // Actualizar los datos
        producto.setNombre(nombre);

        producto.setPrecio_medio(precioMedio);


        // Procesar la imagen si se envía
        if (imagen != null && !imagen.isEmpty()) {
            try {
                byte[] imagenBytes = imagen.getBytes();
                producto.setImagen(imagenBytes); // Guardar los bytes en la base de datos
            } catch (Exception e) {
                return ResponseEntity.status(500).build(); // Error al procesar la imagen
            }
        }

        // Guardar el producto actualizado
        productoService.actualizarProducto(producto);

        return ResponseEntity.noContent().build(); // Retorna 204 (sin contenido)
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Map<String, String>> addProduct(
            @RequestParam("nombre") String nombre,
            @RequestParam("precio_medio") BigDecimal precio_medio,
            @RequestParam("imagen") MultipartFile imagenFile) {

        if (imagenFile == null || imagenFile.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "La imagen es requerida"));
        }

        try {
            // Convertir la imagen a byte[]
            byte[] imagenBytes = imagenFile.getBytes();

            // Realizar la inserción en la base de datos
            productoService.agregarProducto(nombre, precio_medio, imagenBytes);

            // Crear un objeto de respuesta con el mensaje de éxito
            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto insertado con éxito");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Si ocurre algún error, devolver un error JSON
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error al insertar el producto");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




} 
