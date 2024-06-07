package com.example.api.controllers;

import com.example.api.Exception.RecordNotFoundException;
import com.example.api.model.DTO.Company_Request_Update;
import com.example.api.model.Empresa;
import com.example.api.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/getall")
    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaService.GetAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getCompanyById(@PathVariable("id") Integer id)
            throws RecordNotFoundException {
        Optional<Empresa> entity = empresaService.GetCompanyById(id);

        if (!entity.isPresent()) {
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empresa>(entity.get(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Empresa> getCompanyByName(@PathVariable("name") String name)
            throws RecordNotFoundException {
        List<Empresa> entity = empresaService.GetCompanyByName(name);

        if (entity.isEmpty()) {
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empresa>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<Empresa> getCompanyByAddress(@PathVariable("address") String address)
            throws RecordNotFoundException {
        List<Empresa> entity = empresaService.GetCompanyByAddress(address);

        if (entity.isEmpty()) {
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empresa>(entity.get(0), new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping
    public Empresa saveCompany(@RequestBody Empresa empresa) {
        return empresaService.saveCompany(empresa);
    }

    @PutMapping("/edit")
    public void actualizarProducto(@RequestBody Company_Request_Update company) {
        System.out.println(company);
        empresaService.updateCompany(company);
    }
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        empresaService.deleteCompany(id);
    }
}
