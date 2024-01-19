package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Controladores.LoginController;
import com.ceica.modelos.Color;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String usr,pass;
        AlmacenController almacen=new AlmacenController();
        Scanner leer=new Scanner(System.in);
        System.out.println("Bienvenido a AppAlmacen");
        System.out.println(".... Enter para empezar");
        leer.nextLine();
        do{
            System.out.println("Login de AppAlmacen");
            System.out.print("Introduce Usuario: ");
            usr=leer.nextLine();
            System.out.println("Introduce password");
            pass=leer.nextLine();
            if(LoginController.LoginController(usr,pass)){
                System.out.println("Estoy en AppAlmacen");
                menuPrincipalAlmacen(leer,almacen);
                leer.nextLine();
            }else{
                System.out.println("Usuario o Contraseña incorrecta");
            }
        }while(true);
    }

    private static void menuPrincipalAlmacen(Scanner leer, AlmacenController almacen) {
        String op;

        String MENUPRINCIPAL= """
                1.Proovedores
                2.Piezas
                3.Pedidos
                4.Salir
                """;
        System.out.println(MENUPRINCIPAL);
        op = leer.nextLine();
        switch (op){

            case "1":
                subMenuProveedores(leer, almacen);


                break;
            case "2":
subMenuPiezas(leer,almacen);

                break;
            case "3":


                break;
            case "4":
                System.out.println("Opcion no valida");
                break;
            default:
                System.out.println("Opcion no válida");
        }


    }

    private static void subMenuPiezas(Scanner leer, AlmacenController almacen) {
    }

    private static void subMenuProveedores(Scanner leer, AlmacenController almacen) {
        String op,cif,nombre,direccion,localidad,provincia;
        String menuProveedores= """
                1. Nuevo proveedor
                2. Editar proveedor
                3. Ver proveedores
                4. Eliminar proveedor
                5. Volver al menú principal
                """;


        do {

            System.out.println(menuProveedores);
            op=leer.nextLine();
            switch (op){
                case "1":
                    System.out.print("CIF: ");
                    cif=leer.nextLine();
                    System.out.print("Nombre: ");
                    nombre=leer.nextLine();
                    System.out.println("Direccion: ");
                    direccion=leer.nextLine();
                    System.out.println("Localidad: ");
                    localidad=leer.nextLine();
                    System.out.println("Provincia: ");
                    provincia=leer.nextLine();
                    almacen.nuevoProveedor(cif,nombre,direccion,localidad,provincia);
                    break;
                case "2":
                    String nuevonombre;
                System.out.println("Dime el cif ");
                cif = leer.nextLine();
                System.out.println("dime el nuevo nombre");
                nuevonombre = leer.nextLine();
                boolean nombreAnterior = almacen.actualizarProveedorNombre(cif,nuevonombre);

                if (nombreAnterior) {
                    System.out.println("Nombre anterior: " + nombreAnterior);
                    System.out.println("Proveedor actualizado correctamente. Nuevo nombre: " + nuevonombre);
                } else {
                    System.out.println("Proveedor no encontrado.");
                }
                break;
                case "3":
                    System.out.println("Aqui tienes todos los proovedores");
                    System.out.println(almacen.obtenerListaProveedores().toString());
                    break;
                case "4":
                    System.out.println("que provvedor quieres borrar");
                    cif= leer.nextLine();
                    almacen.borrarProveedor(cif);
                    System.out.println("el prooveedor a sido borrado");
                    break;
                case "5":
                    System.out.println("Volviendo al menú principal...");
                default:
                    System.out.println("Opción no válida");
            }

        }while(! "5".equals(op));
    }
}