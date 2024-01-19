package com.ceica.Controladores;


public class LoginController {

    public static boolean LoginController( String USUARIO, String CONTRASEÑA) {
        if ("admin".equals(USUARIO) & "1234".equals(CONTRASEÑA)) {
            return true;

        } else {
            return false;
        }

    }
}