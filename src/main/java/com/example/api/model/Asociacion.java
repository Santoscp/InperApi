package com.example.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "asociacion")
public class Asociacion {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_owner", nullable = false)
    private Owner idOwner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "asociacion",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_empresa"))
    private List<Empresa> empresas;

}