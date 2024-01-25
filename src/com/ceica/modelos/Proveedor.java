package com.ceica.modelos;

import com.ceica.bbdd.Conexion;
import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Proveedor extends ModeloBase{

    private int id;
    private String nombre;
    private String cif;
    private String direccion;
    private String localidad;
    private String provincia;

    public Proveedor() {
    }

    public Proveedor(String nombre, String cif) {
        this.nombre = nombre;
        this.cif = cif;
    }



    public static boolean editarNombreProovedor(String cif, String nuevoNombre) {
        Connection con=Conexion.conectar();
        String sql="update proveedores set nombre=? where cif=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nuevoNombre);
            pst.setString(2, cif);
            if (pst.executeUpdate() > 0){
            return true;
        }else{
            return false;
        }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public static boolean eliminarProveedor(String cif) {
        Connection con = Conexion.conectar();
        String sql = "delete from proveedores where cif=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cif);
            if(pst.executeUpdate()>0){
                con.close();
                return true;
            }else{
                con.close();
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }

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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public static List<Proveedor> getProveedores() {
        List<Proveedor> proveedorList = new ArrayList<>();
        Connection con = null;

        try {
            con = Conexion.conectar();
            try (Statement stm = con.createStatement();
                 ResultSet resultSet = stm.executeQuery("SELECT * FROM proveedores")) {

                while (resultSet.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(resultSet.getInt("idProveedores"));
                    proveedor.setCif(resultSet.getString("cif"));
                    proveedor.setNombre(resultSet.getString("nombre"));
                    proveedor.setDireccion(resultSet.getString("dirección"));
                    proveedor.setLocalidad(resultSet.getString("localidad"));
                    proveedor.setProvincia(resultSet.getString("provincia"));
                    proveedorList.add(proveedor);
                }

            } catch (SQLException e) {
                // Aquí podrías loguear o imprimir información sobre el error
                return proveedorList;
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // Manejo de errores al cerrar la conexión
                    e.printStackTrace();
                }
            }
        }

        return proveedorList;
    }




        @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cif='" + cif + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "proveedores";
    }
}




