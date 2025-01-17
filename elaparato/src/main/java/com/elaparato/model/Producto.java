package com.elaparato.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @SequenceGenerator(name = "producto_seq", sequenceName = "producto_seq", allocationSize = 1)
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int cantidad;

    @ManyToMany
    @JoinTable(
            name = "producto_venta",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "venta_id")
    )
    @JsonIgnore //importante agregar para evitar errores de formato en la response
    private List<Venta> listaVentas;

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, int precio, int cantidad, List<Venta> listaVentas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.listaVentas = listaVentas;
    }
}
