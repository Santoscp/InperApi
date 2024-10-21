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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Imagen;
import com.example.api.model.Producto;
import com.example.api.services.ImagenService;
import com.example.api.services.ProductoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/inper/img")
public class ImagenController {

    @Autowired
    private ImagenService imagenservice;

    @GetMapping("/getall")
    public List<Imagen> getImagen() {
        return imagenservice.GetAllImagen(); // Verifica que este método esté correctamente configurado.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getImagenById(@PathVariable("IdImagen") Integer id)
            throws RecordNotFoundException {
        Optional<Imagen> entity = imagenservice.GetImagenById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Imagen>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Imagen>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Imagen>> getImagenByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Imagen> entity = imagenservice.GetImagenByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    

}
