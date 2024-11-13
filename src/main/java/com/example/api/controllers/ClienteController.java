package com.example.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Carrito;
import com.example.api.model.Cliente;
import com.example.api.model.Imagen;
import com.example.api.model.Producto;
import com.example.api.services.CarritoService;
import com.example.api.services.ClienteService;
import com.example.api.services.ImagenService;
import com.example.api.services.ProductoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/prueba/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteservice;

    @GetMapping("/getall")
    public List<Cliente> getClientes() {
        return clienteservice.GetAllCliente(); // Verifica que este método esté correctamente configurado.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("IdCliente") Integer id)
            throws RecordNotFoundException {
        Optional<Cliente> entity = clienteservice.GetClienteById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/existe/{googleid}")
    public boolean existeClientePorGoogleId(@PathVariable String googleid) {
        return clienteservice.existeClientePorGoogleId(googleid);
    }
    
    
    @PostMapping("/addClient")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        try {
            clienteservice.agregarCliente( cliente.getGoogleid(), cliente.getGoogle_foto());
            return new ResponseEntity<>("Cliente creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    

}