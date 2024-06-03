package com.elaparato.dto;

import java.util.Date;
import java.util.List;

public class VentaDTO {
    private int id; // Agregar el campo id
    private Date fecha;
    private List<ProductoDTO> productos;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
