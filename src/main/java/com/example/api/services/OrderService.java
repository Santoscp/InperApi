package com.example.api.services;

import com.example.api.Repositories.PedidoRepository;
import com.example.api.Repositories.ProductoRepository;
import com.example.api.model.Pedido;
import com.example.api.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> GetAllOrders() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> GetOrderById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public Pedido saveOrder(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deleteOrder(Integer id) {
        pedidoRepository.deleteById(id);
    }
}