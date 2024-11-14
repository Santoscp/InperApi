package com.example.api.services;


import com.example.api.Repositories.CarritoRepository;
import com.example.api.Repositories.ClienteRepository;
import com.example.api.Repositories.ImagenRepository;
import com.example.api.Repositories.ProductoRepository;
import com.example.api.model.Carrito;
import com.example.api.model.Cliente;
import com.example.api.model.Producto;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    
    
    
    

    @Autowired
    private ProductoRepository productoRepository;
    
    
    public Carrito obtenerCarritoPorCliente(Cliente cliente) {
        return carritoRepository.findByCliente(cliente);
    }
    
    public Carrito obtenerCarritoPorGoogleId(String googleid) {
        return carritoRepository.findCarritoByGoogleId(googleid);
    }

    public List<Carrito> GetAllCarrito() {
    	
   
        return carritoRepository.findAll();
    }

    public Optional<Carrito> GetCarritoById(Integer id) {
        return carritoRepository.findById(id);
    }
    
    
    public Carrito agregarProductoAlCarrito(String googleId, int idProducto) {
        // 1. Buscar el cliente por su googleId
        Cliente cliente = clienteRepository.findByGoogleid(googleId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2. Buscar el carrito del cliente
        Carrito carrito = carritoRepository.findByCliente(cliente);
        if (carrito == null) {
            // Si no tiene carrito, creamos uno nuevo
            carrito = new Carrito();
            carrito.setCliente(cliente); // Establecemos el cliente al carrito
        }

        // 3. Buscar el producto por id
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 4. Añadir el producto al carrito
        carrito.getProductos().add(producto);

        // 5. Guardar el carrito actualizado
        return carritoRepository.save(carrito);
    }
    
    public void incrementarCantidad(int idCarrito, int idProducto) {
        carritoRepository.incrementarCantidadProductoEnCarrito(idCarrito, idProducto);
    }
    
    
    public boolean existeProductoEnCarrito(int idCarrito, int idProducto) {
        // Realizas la consulta y devuelves un booleano
        Integer resultado = carritoRepository.existeProductoEnCarrito(idCarrito, idProducto);
        
        return resultado != null && resultado == 1; // 1 significa que existe, 0 significa que no existe
    }
    

    



 

}
