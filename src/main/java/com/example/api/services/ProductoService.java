package com.example.api.services;



import com.example.api.Repositories.ProductoRepository;

import com.example.api.model.Producto;
import com.example.api.model.Producto.TipoProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    
    public void eliminarProductoPorId(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Producto con id " + id + " no encontrado");
        }
    }
//    public void actualizarProducto(Producto producto) {
//        System.out.println(producto);
//        productoRepository.actualizarProducto(producto.getNombre(), producto.getDescripcion(),producto.getTipo());
//    }
    public Producto actualizarProducto(Producto producto) {
        // Guarda el producto actualizado
        Producto productoActualizado = productoRepository.save(producto);
        
        // Opcional: Imprime el producto actualizado para verificar
        System.out.println("Producto actualizado: " + productoActualizado);

        return productoActualizado; // Retornar para comprobar si es necesario
    }
    
//    public Producto guardarProducto(Producto producto) {
//        System.out.println("ADDING");
//        System.out.println(producto);
//        return productoRepository.save(producto);
//    }
    public void agregarProducto(String nombre, BigDecimal precio_medio) {
        // Insertar el producto
        productoRepository.insertarArticulo(nombre,precio_medio);
        // Actualizar el id_articulo
        productoRepository.actualizarIdArticulo();
    }



}
