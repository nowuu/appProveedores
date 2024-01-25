package com.ceica.Controladores;

import com.ceica.modelos.*;

import java.lang.invoke.StringConcatFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenController {


    private List<Proveedor> proveedorList;

    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;

    private List<Categoria> categorias;

    public AlmacenController() {

        proveedorList = new ArrayList<>();
        pedidoList = new ArrayList<>();
        piezaList = new ArrayList<>();
        categorias = new ArrayList<>();
        proveedorList=Proveedor.getProveedores();
        categorias=Categoria.getCategorias();


    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor proveedor = new Proveedor(nombre, cif);

        proveedor.setDireccion(direccion);
        proveedor.setLocalidad(localidad);
        proveedor.setProvincia(provincia);

        if (proveedor.insertar("cif,nombre,direccion,localidad,provincia)" +
                " values(?,?,?,?,?)",cif,nombre,direccion,localidad,provincia)) {
            return proveedorList.add(proveedor);
        }else{
            return false;
        }

    }

    public boolean borrarProveedor(String cif) {

        //return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));
        if (Proveedor.eliminarProveedor(cif)){

            proveedorList=Proveedor.getProveedores();
            return true;
        }else{
            return false;
        }
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


//    public boolean editarNombreProveedor(String cif, String nombre) {
//        proveedorList.stream()
//                .filter(p -> cif.equals(p.getCif()))
//                .findFirst()
//                .map(p -> {
//                    p.setNombre(nombre);
//                    return true;
//                })
//                .orElse(false);
//        return false;
//    }

    public boolean actulizarCIFproveedor(String cif ){

    for (Proveedor proveedor : proveedorList) {
        if (proveedor.getCif().equals(cif)) {
            proveedor.setCif(cif);
            return true;
        }
    }
        return false;


}


    public boolean actualizarProveedorNombre(String cif, String nuevoNombre) {
        if (Proveedor.editarNombreProovedor(cif, nuevoNombre))
            proveedorList = Proveedor.getProveedores();
            return true;

//            for (Proveedor proveedor : proveedorList) {
//                if (proveedor.getCif().equals(cif)) {
//                    proveedor.setNombre(nuevoNombre);
//                    return true;
//                }
//            }
//            return false;
//        }else {
//            return false;
//        }


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

    public boolean nuevoPieza(String nombre, Color color, Double precio, int idcategoria) {
        Pieza pieza = new Pieza(nombre, color.toString(), precio);
        pieza.setCategoria(getCategoriaByID(idcategoria));
        piezaList.add(pieza);
        return true;
    }

    private Categoria getCategoriaByID(int id) {
        return categorias.stream()
                .filter(c -> c.getId() == id).findFirst().get();
    }

    public boolean NuevoPrecioPieza(int id, Double precio) {
        return piezaList.stream()
                .filter(pieza -> pieza.getId() == id)
                .findFirst()
                .map(pieza -> {
                    pieza.setPrecio(precio);
                    return true;
                })
                .orElse(false);
    }

    public String nuevoPedido(String cif, int idPieza, int cantidad) {
        Proveedor proveedor = getProveedorByCIF(cif);
        if (proveedor != null) {
            Pieza pieza = getPiezaByID(idPieza);
            if (pieza != null) {
                Pedido pedido1 = new Pedido(proveedor, pieza);
                pedido1.setCantidad(cantidad);
                pedido1.setFecha(LocalDate.now());
                pedidoList.add(pedido1);
                return "Pedido creado";
            } else {
                return "Error al crear el pedido, Pieza no existe";
            }
        } else {
            return "Error al crear el pedido, Proveedor no existe";
        }
    }

    private Pieza getPiezaByID(int id) {
        for (int i = 0; i < piezaList.size(); i++) {
            if (piezaList.get(i).getId() == id) {
                return piezaList.get(i);
            }
        }
        return null;
    }

    private Proveedor getProveedorByCIF(String cif) {
        for (Proveedor p : proveedorList) {
            if (cif.equals(p.getCif())) {
                return p;
            }
        }
        return null;
    }

    public String getPedidosByPieza(int idPieza) {
        List<Pedido> pedidoByPieza = new ArrayList<>();
        for (Pedido pedido : pedidoList) {
            if (pedido.getPieza().getId() == idPieza) {
                pedidoByPieza.add(pedido);
            }
        }
        if (pedidoByPieza.size() > 0) {
            return pedidoByPieza.toString();
        } else {
            return "No hay pedidos de esta pieza";
        }

    }

    public String getPedidosByProveedor(String cif) {
        return pedidoList.stream()
                .filter(pedido -> cif.equals(pedido.getProveedor().getCif()))
                .toList()
                .stream()
                .findFirst()
                .map(Object::toString)
                .orElse("No hay pedidos de ese proveedor");
    }

    @Override
    public String toString() {
        return "AlmacenController{" +
                "proveedorList=" + proveedorList +
                ", piezaList=" + piezaList +
                ", pedidoList=" + pedidoList +
                ", categorias=" + categorias +
                '}';
    }


    public List<Proveedor> obtenerListaProveedores() {
        return proveedorList;
    }


    public String categoriasDisponibles() {
        return categorias.toString();
    }

    public boolean categoriaValida(int categoria) {
        for (Categoria cat : categorias){
            if(cat.getId()==categoria){
                return true;
            }
        }
        return false;
    }

    public String verPiezas() {
        return piezaList.toString();
    }
    public List<Pieza> mostrarListaDePiezas() {
        return piezaList;
    }


}
