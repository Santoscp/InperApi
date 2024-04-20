package com.example.api.controllers;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Repartidor;
import com.example.api.services.RepartidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {
    @Autowired
    private RepartidorService repartidorService;

    @GetMapping
    public List<Repartidor> GetAllDeliveries() {
        return repartidorService.GetAllDeliveries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repartidor> getDeliveryById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Repartidor> entity = repartidorService.GetDeliveryById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Repartidor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Repartidor>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Repartidor> getDeliveryByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Repartidor> entity = repartidorService.GetDeliveryByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<Repartidor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Repartidor>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public Repartidor saveDelivery(@RequestBody Repartidor repartidor) {
        return repartidorService.saveDelivery(repartidor);
    }

    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Integer id) {
        repartidorService.deleteDelivery(id);
    }
}
