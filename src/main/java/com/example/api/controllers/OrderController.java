package com.example.api.controllers;


import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.Pedido;
import com.example.api.services.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService service;

    @GetMapping
    public List<Pedido> GetAllOrders() {
        return service.GetAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getOrdersById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Pedido> entity = service.GetOrderById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Pedido>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public Pedido saveOrder(@RequestBody Pedido pedido) {
        return service.saveOrder(pedido);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        service.deleteOrder(id);
    }
}