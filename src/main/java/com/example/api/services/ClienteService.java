package com.example.api.services;


import com.example.api.Repositories.CarritoRepository;
import com.example.api.Repositories.ClienteRepository;
import com.example.api.Repositories.ImagenRepository;
import com.example.api.Repositories.ProductoRepository;
import com.example.api.model.Carrito;
import com.example.api.model.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienterepository;

    public List<Cliente> GetAllCliente() {
    	
   
        return clienterepository.findAll();
    }

    public Optional<Cliente> GetClienteById(Integer id) {
        return clienterepository.findById(id);
    }
    
    public void agregarCliente(String googleId, String googleFoto) {
        clienterepository.insertarCliente(googleId, googleFoto);
    }
    public boolean existeClientePorGoogleId(String googleid) {
        return clienterepository.existsClienteByGoogleid(googleid);
    }
    
    public int idClienteGoogle(String googleid) {
    	return clienterepository.findIdClienteByGoogleId(googleid);
    }
 

}
