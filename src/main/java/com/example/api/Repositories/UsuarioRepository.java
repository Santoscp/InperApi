package com.example.api.Repositories;

import com.example.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(
            value="Select * FROM usuario as usu WHERE usu.nombre LIKE %?1%",
            nativeQuery = true)
    public List<Usuario> getByName(String name);
    @Query(
            value="Select * FROM usuario as usu WHERE usu.correo LIKE ?1",
            nativeQuery = true)
    public Usuario getByEmail(String name);
}