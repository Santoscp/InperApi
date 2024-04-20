package com.example.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`pedido?producto`")
public class PedidoProducto {

    @Id
    @Column(name = "id", nullable = false)
    private PedidoProductoId id;

    @MapsId("idProd")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prod", nullable = false)
    private Producto idProd;

    @MapsId("idPed")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ped", nullable = false)
    private Pedido idPed;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}