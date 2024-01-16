package com.ceica.Controladores;

import com.ceica.modelos.Pedido;
import com.ceica.modelos.Pieza;
import com.ceica.modelos.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class AlmacenController {


    private List<Proveedor> proveedorList;

    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;


    public AlmacenController() {

        proveedorList = new ArrayList<>();
        piezaList = new ArrayList<>();
        pedidoList = new ArrayList<>();

    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor proveedor = new Proveedor(nombre, cif);

        proveedor.setDireccion(direccion);
        proveedor.setLocalidad(localidad);
        proveedor.setProvincia(provincia);
        return proveedorList.add(proveedor);


    }

    public boolean borrarProveedor(String cif) {

        return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));
    }

    @Override
    public String toString() {
        return "AlmacenController{" +
                "proveedorList=" + proveedorList + "\n" +
                ", piezaList=" + piezaList + "\n" +
                ", pedidoList=" + pedidoList + "\n" +
                '}';
    }
//
//    public boolean borrarProveedor(String cif) {
//        for (int i = 0; i < proveedorList.size(); i++) {
//            if (cif.equals(proveedorList.get(i).getCif())) {
//                    proveedorList.remove(i);
//                    return true;
//                }
//            }
//            return false;

    public boolean actualizarProveedorNombre(String cif, String nuevoNombre) {
        for (Proveedor proveedor : proveedorList) {
            if (proveedor.getCif().equals(cif)) {
                proveedor.setNombre(nuevoNombre);
                return true;
            }
        }
        return false;


    }

    public boolean actualizarProveedorDirecion(String cif, String nuevaDirección) {
        for (Proveedor proveedor : proveedorList) {
            if (proveedor.getCif().equals(cif)) {
                proveedor.setNombre(nuevaDirección);
                return true;
            }
        }
        return false;


    }

    public boolean actualizarProveedorLocalidad(String cif, String nuevaLocalidad) {
        for (Proveedor proveedor : proveedorList) {
            if (proveedor.getCif().equals(cif)) {
                proveedor.setNombre(nuevaLocalidad);
                return true;
            }
        }
        return false;


    }
    public boolean actualizarProveedorProvincia(String cif, String nuevaProvincia) {
        for (Proveedor proveedor : proveedorList) {
            if (proveedor.getCif().equals(cif)) {
                proveedor.setNombre(nuevaProvincia);
                return true;
            }
        }
        return false;


    }
}
