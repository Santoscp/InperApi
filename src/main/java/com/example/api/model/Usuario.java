package com.example.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "puntos", nullable = false)
    private Integer puntos;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Pedido> Pedido;

}