package com.example.api.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.model.Carrito;
import com.example.api.model.Cliente;
import com.example.api.model.Imagen;
import com.example.api.model.Producto;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

	@Query("SELECT c FROM Carrito c JOIN c.cliente cli WHERE cli.googleid = :googleid")
	Carrito findCarritoByGoogleId(@Param("googleid") String googleid);
	




	@Query("SELECT c FROM Carrito c WHERE c.cliente = :cliente")
	Carrito findByCliente(@Param("cliente") Cliente cliente);

	@Modifying
	@Transactional
	@Query("UPDATE Carrito c SET c.productos = :productos WHERE c.cliente.idCliente = :clienteId")
	void actualizarCarrito(@Param("productos") List<Producto> productos, @Param("clienteId") Integer clienteId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE carrito_producto cp SET cp.cantidad = cp.cantidad + 1 WHERE cp.id_carrito = :idCarrito AND cp.id_producto = :idProducto", nativeQuery = true)
	void incrementarCantidadProductoEnCarrito(@Param("idCarrito") int idCarrito, @Param("idProducto") int idProducto);
	@Modifying
	@Transactional
	@Query(value = "UPDATE carrito_producto cp SET cp.cantidad = cp.cantidad - 1 WHERE cp.id_carrito = :idCarrito AND cp.id_producto = :idProducto", nativeQuery = true)
	void decrementarCantidadProductoEnCarrito(@Param("idCarrito") int idCarrito, @Param("idProducto") int idProducto);


	@Query(value = "SELECT EXISTS (SELECT 1 FROM carrito_producto WHERE id_carrito = :idCarrito AND id_producto = :idProducto)", nativeQuery = true)
	Integer existeProductoEnCarrito(@Param("idCarrito") int idCarrito, @Param("idProducto") int idProducto);

	@Query("SELECT c.id FROM Carrito c WHERE c.cliente.googleid = :googleid")
	Integer findIdCarritoByGoogleId(@Param("googleid") String googleid);
	
	@Query(value="SELECT cp.cantidad FROM carrito_producto cp WHERE cp.id_carrito = :idCarrito AND cp.id_producto = :idProducto", nativeQuery = true)
	Integer findCantidadByCarritoAndProducto(@Param("idCarrito") Integer idCarrito, @Param("idProducto") Integer idProducto);

	

//	    @Query("SELECT COUNT(p) > 0 FROM Producto p WHERE p.id = :idProducto AND p.carrito.id = :idCarrito")
//	    Integer existeProductoEnCarrito(@Param("idCarrito") int idCarrito, @Param("idProducto") int idProducto);

}
