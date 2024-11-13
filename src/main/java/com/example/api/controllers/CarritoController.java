package com.example.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Carrito;
import com.example.api.model.Imagen;
import com.example.api.model.Producto;
import com.example.api.services.CarritoService;
import com.example.api.services.ClienteService;
import com.example.api.services.ImagenService;
import com.example.api.services.ProductoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/prueba/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoservice;
    @Autowired
    private ClienteService clienteservice;

    @GetMapping("/getall")
    public List<Carrito> getCarrito() {
        return carritoservice.GetAllCarrito(); // Verifica que este método esté correctamente configurado.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable("IdCarrito") Integer id)
            throws RecordNotFoundException {
        Optional<Carrito> entity = carritoservice.GetCarritoById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Carrito>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Carrito>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }
    
//    @PostMapping("/agregar/{clienteId}/{productoId}")
//    public void agregarProductoACarrito(@PathVariable String clienteId, @PathVariable int productoId) {
//        carritoservice.agregarProductoAlCarrito(clienteId, productoId);
//    }
//   
//    @GetMapping("/carrito")
//    public ResponseEntity<Carrito> obtenerCarritoDelUsuario() {
//    	
//        int userId = clienteservice.idClienteGoogle("oLvGQZdoUZRA0d3uX99ApK3Oye13");  // Extraemos el ID del usuario del JWT (userDetails)
//        
//        Carrito carrito = carritoservice.obtenerCarritoPorUsuario(userId);
//        return ResponseEntity.ok(carrito);
//    }
    
    @GetMapping("/cliente")
    public ResponseEntity<Carrito> obtenerCarritoPorGoogleId(@RequestParam String googleid) {
        Carrito carrito = carritoservice.obtenerCarritoPorGoogleId(googleid);
        if (carrito != null) {
            return ResponseEntity.ok(carrito); // Si se encuentra el carrito, lo devolvemos
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el carrito, devolvemos 404
        }
    }
    

    @PostMapping("/agregar/{googleId}/{idProducto}")
    public ResponseEntity<Carrito> agregarProductoAlCarrito(@PathVariable String googleId, @PathVariable int idProducto) {
        Carrito carrito = carritoservice.agregarProductoAlCarrito(googleId, idProducto);
        return ResponseEntity.ok(carrito);
    }






    

}