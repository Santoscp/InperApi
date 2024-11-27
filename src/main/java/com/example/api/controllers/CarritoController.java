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
            // Si no se encuentra el carrito, creamos uno nuevo
            Carrito nuevoCarrito = carritoservice.crearCarrito(googleid);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito); // Devolvemos el carrito creado con un 201 (CREATED)
        }
    }

    
    
    @GetMapping("/producto-existe")
    public ResponseEntity<Boolean> productoExisteEnCarrito(@RequestParam int idCarrito, @RequestParam int idProducto) {
        boolean existe = carritoservice.existeProductoEnCarrito(idCarrito, idProducto);
        return ResponseEntity.ok(existe);
    }
    

    @PostMapping("/agregar/{googleId}/{idProducto}")
    public ResponseEntity<Carrito> agregarProductoAlCarrito(@PathVariable String googleId, @PathVariable int idProducto) {
    	System.out.println("llega");
        Carrito carrito = carritoservice.agregarProductoAlCarrito(googleId, idProducto);
        return ResponseEntity.ok(carrito);
    }
    
    
    
    
    
    @PutMapping("/incrementar-cantidad")
    public ResponseEntity<String> incrementarCantidadProducto(
            @RequestParam int idCarrito,
            @RequestParam int idProducto) {
        
        try {
            carritoservice.incrementarCantidad(idCarrito, idProducto);
            return ResponseEntity.ok("Cantidad incrementada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al incrementar la cantidad: " + e.getMessage());
        }
    }
    @GetMapping("/cantidad")
    public ResponseEntity<Integer> obtenerCantidadProductoEnCarrito(
        @RequestParam String googleid, 
        @RequestParam Integer idProducto) {
        
        // Llamamos al servicio para obtener la cantidad del producto en el carrito
        Integer cantidad = carritoservice.obtenerCantidadProductoEnCarrito(googleid, idProducto);
        
        if (cantidad != null) {
            // Si la cantidad fue encontrada, la retornamos
            return ResponseEntity.ok(cantidad);
        } else {
            // Si no se encontró la cantidad, devolvemos un 404 o un mensaje adecuado
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);  // O puedes devolver un mensaje personalizado
        }





    

}
    
    
}
