package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pieza extends ModeloBase{

    private static int idPieza = 0;
    private int id;

    private String nombre;

    private String color;

    private Double precio;

    private Categoria categoria;

    public Pieza(String nombre, String color, Double precio) {
        this.id = idPieza++;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
    }

    public Pieza() {


    }

    @Override
    public String toString() {
        return "Pieza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}';
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }




    static List<Pieza> getPiezas() {
        List<Pieza> piezaList = new ArrayList<>();
        Connection con = null;

        try {
            con = Conexion.conectar();
            try (Statement stm = con.createStatement();
                 ResultSet resultSet = stm.executeQuery("SELECT P.idPiezas, P.nombre, P.precio, P.color, C.idCategorias, C.categorias as nombre_categoria FROM proveedores.piezas as P INNER JOIN categorias as C ON P.idCategorias = C.idCategorias")) {
                while (resultSet.next()) {
                    Pieza pieza = new Pieza();
                    pieza.setId(resultSet.getInt("idPiezas"));
                    pieza.setNombre(resultSet.getString("nombre"));
                    pieza.setPrecio(resultSet.getDouble("precio"));
                    pieza.setColor(resultSet.getString("color"));

                    Categoria categoria1 = new Categoria();
                    categoria1.setId(resultSet.getInt("idCategorias"));
                    categoria1.setNombre(resultSet.getString("nombre_categoria"));

                    pieza.setCategoria(categoria1);
                    piezaList.add(pieza);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return piezaList;
    }

    @Override
    protected String getNombreTabla() {
        return "piezas";
    }

//    public static boolean insertar(Pieza pieza,Categoria categoria1) {
//        Connection con=Conexion.conectar();
//        String sql="insert into proveedores (cif,nombre,dirección,localidad,provincia)"+
//                "values(?,?,?,?,?)";
//        try {
//            PreparedStatement pst=con.prepareStatement(sql);
//            pst.setString(1, proveedor.getCif());
//            pst.setString(2, proveedor.getNombre());
//            pst.setString(3, proveedor.getDireccion());
//            pst.setString(4, proveedor.getLocalidad());
//            pst.setString(5, proveedor.getProvincia());
//            if (pst.executeUpdate()<0){
//                return false;
//            }else{
//                return true;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//


}