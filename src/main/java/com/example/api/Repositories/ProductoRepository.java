package com.example.api.Repositories;

import com.example.api.model.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(
            value="Select * FROM productos as prod WHERE prod.nombre LIKE %?1%",
            nativeQuery = true)
    public List<Producto> getByName(String name);

    @Query(
            value="Select * FROM productos as prod WHERE prod.tipo LIKE %?1%",
            nativeQuery = true)
    public List<Producto> getByType(String name);

    //query para obtener todos los productos de la empresa con id = 1
    @Query(
            value="Select * FROM productos as prod WHERE prod.id_empresa = ?1",
            nativeQuery = true)
    public List<Producto> getByCompany(int id);

    //query para actualizar un producto por su nombre o su precioZ

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos as p SET p.nombre = :nuevoNombre, p.precio = :nuevoPrecio WHERE p.id = :productoId",
            nativeQuery = true)
    void actualizarProducto( @Param("nuevoNombre") String nuevoNombre, @Param("nuevoPrecio") Integer nuevoPrecio, @Param("productoId") Integer productoId);

    //query para eliminar un producto por la id

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Producto p WHERE p.id = :id")
    void deleteProductById(@Param("id") Integer id);
}