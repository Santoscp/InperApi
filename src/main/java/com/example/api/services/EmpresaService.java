package com.example.api.services;

import com.example.api.Repositories.EmpresaRepository;
import com.example.api.model.DTO.Company_Request_Update;
import com.example.api.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> GetAllCompanies() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> GetCompanyById(Integer id) {
        return empresaRepository.findById(id);
    }

    public List<Empresa> GetCompanyByName(String name) {
        return empresaRepository.getByName(name);
    }

    public List<Empresa> GetCompanyByAddress(String address) {
        return empresaRepository.getByAddress(address);
    }

    public Empresa saveCompany(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void deleteCompany(Integer id) {
        empresaRepository.deleteById(id);
    }

    public void updateCompany(Company_Request_Update company) {
        System.out.println(company);
        empresaRepository.updateCompany(company.getNombre(), company.getHorario(), company.getCompanyId());
    }
}
