package com.example.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PedidoProductoId implements Serializable {
    private static final long serialVersionUID = -929607034315597825L;
    @Column(name = "id_prod", nullable = false)
    private Integer idProd;

    @Column(name = "id_ped", nullable = false)
    private Integer idPed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PedidoProductoId entity = (PedidoProductoId) o;
        return Objects.equals(this.idPed, entity.idPed) &&
                Objects.equals(this.idProd, entity.idProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPed, idProd);
    }

}