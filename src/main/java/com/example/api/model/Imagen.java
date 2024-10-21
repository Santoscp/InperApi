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
@Table(name = "img")
public class Imagen {
	 	@Id
	    @Column(name = "id", nullable = false)
	 	
	    private Integer id;
	    @Column(name = "nombre")
	    private String nombre; // Si la columna se llama "Nombre", cámbiala a "nombre"
	    
	    @Column(name ="foto")
	    private byte[] imagen;

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

		public byte[] getImagen() {
			return imagen;
		}

		public void setImagen(byte[] imagen) {
			this.imagen = imagen;
		}

		@Override
		public String toString() {
			return "Imagen [id=" + id + ", nombre=" + nombre + ", imagen=" + Arrays.toString(imagen) + "]";
		}
	    

}
