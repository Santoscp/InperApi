package com.example.api.Repositories;

import com.example.api.model.Empresa;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    @Query(value = "SELECT * FROM empresas AS empre WHERE empre.nombre LIKE %?1%", nativeQuery = true)
    List<Empresa> getByName(String name);

    @Query(value = "SELECT * FROM empresas AS empre WHERE empre.direccion LIKE %?1%", nativeQuery = true)
    List<Empresa> getByAddress(String address);

    @Modifying
    @Transactional
    @Query(value = "UPDATE empresas as e SET e.nombre = :nuevoNombre, e.horario = :nuevoHorario WHERE e.id = :empresaId",
            nativeQuery = true)
    void updateCompany(@Param("nuevoNombre") String nuevoNombre, @Param("nuevoHorario") String nuevoHorario, @Param("empresaId") Integer empresaId);

}