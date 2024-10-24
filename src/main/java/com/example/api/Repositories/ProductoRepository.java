package com.example.api.Repositories;
import com.example.api.model.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(value = "SELECT * FROM producto AS pro WHERE pro.nombre LIKE %?1%", nativeQuery = true)
    List<Producto> getByName(String name);
    
 
    
    
    

}