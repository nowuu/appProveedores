package com.ceica.modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private int id;

    private String nombre;

    public Categoria() {
        this.id = id;
        this.nombre = nombre;
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


  public static List<Categoria> getCategorias(){
        List<Categoria> categoriaList = new ArrayList<>();
        Connection con = null;

        try {
            con = Conexion.conectar();
            try (Statement stm = con.createStatement();
                 ResultSet resultSet = stm.executeQuery("SELECT * FROM categorias")) {
                while (resultSet.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("idCategorias"));
                    categoria.setNombre(resultSet.getString("categorias"));
                    categoriaList.add(categoria);
                }
            }
        } catch (SQLException e) {
            // Aquí podrías loguear o imprimir información sobre el error
            e.printStackTrace();
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

        return categoriaList;
    }
    @Override
    public String toString() {
        return id  + " -> "+nombre + "\n" ;
    }
}

