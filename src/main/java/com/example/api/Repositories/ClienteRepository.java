package com.example.api.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.api.model.Cliente;
import com.example.api.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	

	
	
	 @Modifying
	    @Transactional
	    @Query(value = "INSERT INTO cliente ( googleid, google_foto) VALUES ( :googleid, :google_foto)", nativeQuery = true)
	    void insertarCliente(@Param("googleid") String googleId, @Param("google_foto") String googleFoto);
	 
	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.googleid = :googleid")
	    boolean existsClienteByGoogleid(@Param("googleid") String googleid);
	 
	   @Query("SELECT c.idCliente FROM Cliente c WHERE c.googleid = :googleid")
	    int findIdClienteByGoogleId(@Param("googleid") String googleid);
	   
	   
	// Cambiar esto:
	   @Query("SELECT c FROM Cliente c WHERE c.googleid = :googleid")
	   Optional<Cliente> findByGoogleid(@Param("googleid") String googleid);

	   
	 
    
 
    
   
}

