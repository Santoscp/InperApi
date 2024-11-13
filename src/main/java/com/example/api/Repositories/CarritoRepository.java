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

	

	

    
    

}