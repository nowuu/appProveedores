package com.ceica.modelos;

public class Pieza {

    private int id;

    private String nombre;

    private String color;

    private Double precio;

    private Categoria categoria;

    public Pieza(String nombre, String color, Double precio) {
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
    }
}
