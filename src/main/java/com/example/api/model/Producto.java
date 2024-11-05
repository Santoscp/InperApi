package com.example.api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "articulos")
public class Producto {
	   @Id
	   @Column(name = "IdArticulo", nullable = false)
	   @GeneratedValue(strategy = GenerationType.IDENTITY) // Cambia la estrategia según tu necesidad
	    private int id;

	  

	    
	    @Column(name="Descripción",nullable = true)
	    private String nombre;
	    @Column(name="precio_medio",nullable = true)
	    private BigDecimal  precio_medio;
	    

	    
	    
	    
	    @Column(name ="Imagen",nullable = true)
	    private byte[] imagen;

	    
	    
	    
	
    
  


	public byte[] getImagen() {
			return imagen;
		}

		public void setImagen(byte[] imagen) {
			this.imagen = imagen;
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}





	
	












	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio_medio=" + precio_medio + ", imagen="
				+ Arrays.toString(imagen) + "]";
	}

	public BigDecimal  getPrecio_medio() {
		return precio_medio;
	}

	public void setPrecio_medio(BigDecimal  precio_medio) {
		this.precio_medio = precio_medio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

















	public enum TipoProducto {
        Gaming("Gaming"),
        TodoEnUno("Todo en Uno"),
        Sobremesa("Sobremesa"),
        Smartphone("Smartphone"),
        TelefonosBasicos("Teléfonos Básicos"),
        CartuchoTinta("Cartucho de Tinta"),
        TONER("Toner"),
        TAMBORES("Tambores"),
        PAPEL("Papel"),
        TECLADO("Teclado"),
        RATON("Ratón"),
        PACKS("Packs"),
        MONITORES("Monitores"),
        Otros("Otros");

        private final String nombre;

       TipoProducto(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }
    }




}