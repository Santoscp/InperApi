package com.example.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "dni", nullable = false, length = 50)
    private String dni;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "asociacion",
            joinColumns = @JoinColumn(name = "id_owner"),
            inverseJoinColumns = @JoinColumn(name = "id_empresa"))
    private List<Empresa> empresas;



}