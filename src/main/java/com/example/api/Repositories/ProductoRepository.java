package com.example.api.Repositories;
import com.example.api.model.Producto;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(value = "SELECT * FROM articulos AS art WHERE art.Descripción LIKE %?1%", nativeQuery = true)
    List<Producto> getByName(String name);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Producto p WHERE p.id = :id")
    void deleteById(@Param("id") Integer id);
    
//    @Modifying
//    @Query("UPDATE Producto p SET p.nombre = :nombre, p.descripcion = :descripcion, p.tipoProducto = :tipoProducto, p.imagen = :imagen WHERE p.id = :id")
//    int actualizarProductoPorId(@Param("id") Integer id, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("tipoProducto") TipoProducto tipoProducto, @Param("imagen") byte[] imagen);

    
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE producto as p SET p.nombre = :nuevoNombre, p.descripcion = :descripcion, p.tipo = :tipo, p.imagen= NULL WHERE p.id = :id",
//            nativeQuery = true)
//    void actualizarProducto( @Param("nuevoNombre") String nuevoNombre, @Param("descripcion") String descripcion, @Param("tipo") TipoProducto tipo);
    
    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.nombre = :nombre, p.imagen = :imagen WHERE p.id = :id")
    void actualizarProducto(@Param("id") Integer id, @Param("nombre") String nombre, 
                            @Param("imagen") byte[] imagen);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO articulos (Descripción, precio_medio, imagen) VALUES (:nombre, :precio_medio, :imagen)", nativeQuery = true)
    void insertarArticulo(@Param("nombre") String nombre, @Param("precio_medio") BigDecimal precio_medio, @Param("imagen") byte[] imagen);


    @Modifying
    @Transactional
    @Query(value = "UPDATE articulos SET id_articulo = LAST_INSERT_ID() WHERE IdArticulo = LAST_INSERT_ID()", nativeQuery = true)
    void actualizarIdArticulo();

    
}


 
    
    
    
