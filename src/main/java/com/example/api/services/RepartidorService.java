package com.example.api.services;

import com.example.api.Repositories.EmpresaRepository;
import com.example.api.Repositories.RepartidorRepository;
import com.example.api.model.Empresa;
import com.example.api.model.Repartidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepartidorService {
    @Autowired
    private RepartidorRepository repartidorRepository;

    public List<Repartidor> GetAllDeliveries() {
        return repartidorRepository.findAll();
    }

    public Optional<Repartidor> GetDeliveryById(Integer id) {
        return repartidorRepository.findById(id);
    }

    public List<Repartidor> GetDeliveryByName(String name) {
        return repartidorRepository.getByName(name);
    }

    public Repartidor saveDelivery(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    public void deleteDelivery(Integer id) {
        repartidorRepository.deleteById(id);
    }
}
