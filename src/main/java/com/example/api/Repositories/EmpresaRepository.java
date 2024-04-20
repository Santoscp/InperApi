package com.example.api.Repositories;

import com.example.api.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query(value = "SELECT * FROM empresas AS empre WHERE empre.nombre LIKE %?1%", nativeQuery = true)
    List<Empresa> getByName(String name);

    @Query(value = "SELECT * FROM empresas AS empre WHERE empre.direccion LIKE %?1%", nativeQuery = true)
    List<Empresa> getByAddress(String address);
}