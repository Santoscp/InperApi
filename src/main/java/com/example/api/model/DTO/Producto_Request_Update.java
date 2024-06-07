package com.example.api.model.DTO;

public class Producto_Request_Update {
    private int productoId;
    private String nombre;
    private int precio;

    public Producto_Request_Update(int productId, String nombre, int precio) {
        this.productoId = productId;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto_Request_Update() {
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productId) {
        this.productoId = productId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int price) {
        this.precio = price;
    }

    @Override
    public String toString() {
        return "Producto_Request_Update{" +
                "productId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
