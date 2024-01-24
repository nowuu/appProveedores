package com.ceica.bbdd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

    public static Connection conectar(){
        // Cargar la configuración desde el archivo de propiedades
        Properties propiedades = new Properties();
        try (FileInputStream entrada = new FileInputStream("config.properties")) {
            propiedades.load(entrada);
            //Obtener la configuración de la base de datos
            String url=propiedades.getProperty("db.url");
            String usuario=propiedades.getProperty("db.usuario");
            String contraseña=propiedades.getProperty("db.contraseña");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            return conexion;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}