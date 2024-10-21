package com.example.api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
	   @Id
	    @Column(name = "id", nullable = false)
	    private Integer id;

	  
	    @Column(name = "nombre", nullable = false, length = 100)
	    private String nombre; // Si la columna se llama "Nombre", cámbiala a "nombre"
	    
	    @Column(name="descripcion")
	    private String descripcion;
	    
	    @Enumerated(EnumType.STRING)    
	    @Column(name = "tipo", nullable = false)
	    private TipoProducto tipo; // Añadimos la categoría con el enum
	    
	    
	    
	    @Column(name ="imagen")
	    private byte[] imagen;
	    
	    
	    
	
    
  


	public byte[] getImagen() {
			return imagen;
		}

		public void setImagen(byte[] imagen) {
			this.imagen = imagen;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", imagen=" + Arrays.toString(imagen) + "]";
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