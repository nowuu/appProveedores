package com.ceica;

import com.ceica.Controladores.AlmacenController;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacen = new AlmacenController();
        almacen.nuevoProveedor("a", "nom", "dir", "loc", "pro");
        almacen.nuevoProveedor("b", "nom", "dir", "loc", "pro");
        System.out.println(almacen.toString());
        System.out.println(almacen.actualizarProveedorNombre("a","jose"));
        System.out.println(almacen.toString());

    }
    }
