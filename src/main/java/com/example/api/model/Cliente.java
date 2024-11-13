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
@Table(name = "cliente")

public class Cliente {
	  @Id
	  @Column(name = "id_cliente", nullable = false)
	    private int idCliente;
	  
	   @Column(name = "googleid", nullable = false)
	    private String googleid;
	   
	   @Column(name = "google_foto", nullable = false)
	    private String google_foto;
	   
	   
	    @OneToMany(mappedBy = "cliente")
	    @JsonManagedReference  // Controla que se serialice solo la parte del cliente hacia adelante.

	    private List<Carrito> carritos;


	public List<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(List<Carrito> carritos) {
		this.carritos = carritos;
	}


	public String getGoogleid() {
		return googleid;
	}

	public void setGoogleid(String googleid) {
		this.googleid = googleid;
	}

	public String getGoogle_foto() {
		return google_foto;
	}

	public void setGoogle_foto(String google_foto) {
		this.google_foto = google_foto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", googleid=" + googleid + ", google_foto=" + google_foto
				+ ", carritos=" + carritos + "]";
	}


	   
	   

}
