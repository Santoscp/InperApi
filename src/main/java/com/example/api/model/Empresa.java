package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "empresas")
public class Empresa {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnoreProperties("empresas")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin idAdmin;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "horario", nullable = false)
    private String horario;

    @Column(name = "puntuaje", nullable = false)
    private Integer puntuaje;


    @JsonIgnoreProperties("empresas")
    @ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
    private List<Owner> owners;

    @JsonIgnoreProperties({"empresas", "idOwner"})
    @ManyToMany(mappedBy = "empresas", fetch = FetchType.LAZY)
    private List<Asociacion> asociaciones;

    @JsonIgnoreProperties("idEmpresa")
    @OneToMany(mappedBy = "idEmpresa", fetch = FetchType.EAGER)
    private List<Producto> productos;

}