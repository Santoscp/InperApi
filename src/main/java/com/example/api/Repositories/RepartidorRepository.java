package com.example.api.Repositories;

import com.example.api.model.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    @Query(
            value="Select * FROM repartidor as repa WHERE repa.nombre LIKE %?1%",
            nativeQuery = true)
    public List<Repartidor> getByName(String name);
}