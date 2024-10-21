package com.example.api.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.model.Imagen;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    @Query(value = "SELECT * FROM img AS img WHERE img.nombre LIKE %?1%", nativeQuery = true)
    List<Imagen> getByName(String name);
    
 
    
    
    

}