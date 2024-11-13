package com.example.api.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "carrito")

public class Carrito {
	  @Id
	   @Column(name = "id_carrito", nullable = false)
	   @GeneratedValue(strategy = GenerationType.IDENTITY) // Cambia la estrategia según tu necesidad
	    private int id_carrito;
	  
	  @ManyToOne
	    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	  @JsonBackReference  // Evita que se serialice la propiedad "cliente" hacia atrás.
	    private Cliente cliente;

	    // Relación muchos a muchos: un carrito puede tener muchos productos
	  @ManyToMany
	    @JoinTable(
	        name = "carrito_producto",
	        joinColumns = @JoinColumn(name = "id_carrito"),
	        inverseJoinColumns = @JoinColumn(name = "id_producto")
	    )
	    private List<Producto> productos = new ArrayList<>(); // Inicializar aquí
	  
	  
	  
	  

		public int getId_carrito() {
			return id_carrito;
		}

		public void setId_carrito(int id_carrito) {
			this.id_carrito = id_carrito;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public List<Producto> getProductos() {
			return productos;
		}

		public void setProductos(List<Producto> productos) {
			this.productos = productos;
		}

		@Override
		public String toString() {
			return "Carrito [id_carrito=" + id_carrito + ", cliente=" + cliente + ", productos=" + productos + "]";
		}
	

}
