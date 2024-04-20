package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "usuario", nullable = false, length = 100)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    @OneToMany(mappedBy = "idAdmin", fetch = FetchType.LAZY)
    private List<Empresa> empresas;


}