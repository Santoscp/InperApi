package com.example.api.services;


import com.example.api.Repositories.EmpresaRepository;
import com.example.api.Repositories.UsuarioRepository;
import com.example.api.model.Empresa;
import com.example.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    public List<Usuario> GetAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Usuario> GetUserById(Integer id) {
        return userRepository.findById(id);
    }

    public List<Usuario> GetUserByName(String name) {
        return userRepository.getByName(name);
    }
    public Usuario saveUser(Usuario user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
