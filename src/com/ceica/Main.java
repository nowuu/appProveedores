package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Controladores.LoginController;
import com.ceica.modelos.Color;

import java.util.ArrayList;
import java.util.Arrays;
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
            if(LoginController.login(usr,pass)){
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
        String op;
        String cif;
        String menuPiezas= """
                1. Nueva pieza
                2. Cambiar precio
                3. Borrar pieza
                4. Ver piezas
                5. Volver al menú anterior
                """;
        do{
            System.out.println(menuPiezas);
            op=leer.nextLine();
            switch (op){
                case "1":
                    nuevaPieza(leer,almacen);
                    break;
                case "2":
                    System.out.println("Dime el id de la pieza");
                    int id = leer.nextInt();
                    leer.nextLine();
                    System.out.println("Ingrese el nuevo precio de la pieza:");
                    double nuevoPrecio = leer.nextDouble();
                    leer.nextLine();
                    boolean actualizacionExitosa = almacen.NuevoPrecioPieza(id,nuevoPrecio);
                    System.out.println("¿Actualización exitosa? " + actualizacionExitosa);
                    System.out.println("Lista de piezas después de la actualización:");
                    System.out.println(almacen.verPiezas());
                    break;
                case "3":
                    System.out.println("que provvedor quieres borrar");
                    cif= leer.nextLine();
                    almacen.borrarProveedor(cif);
                    System.out.println("el prooveedor a sido borrado");
                    break;
                case "4":
                    System.out.println(almacen.toString());
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        }while(! "5".equals(op));
    }



    private static void nuevaPieza(Scanner leer, AlmacenController almacen) {
        String nombre, colorPieza;
        double precio = 0;
        Color color = null;
        boolean colorValido = false, categoriaValida = false, precioValido = false;
        int categoria;
        System.out.print("Nombre de la pieza: ");
        nombre = leer.nextLine();
        do {
            System.out.print("Precio: ");
            try {
                precio = leer.nextDouble();
                leer.nextLine();
                precioValido = true;
            } catch (Exception e) {
                leer.nextLine();
                System.out.println("Formato de precio no válido");
                precioValido = false;
            }
        } while (!precioValido);
        do {
            System.out.println("Color de la pieza (Colores disponibles)");
            System.out.println(Arrays.stream(Color.values()).toList());
            colorPieza = leer.nextLine();
            try {
                color = Color.valueOf(colorPieza.toUpperCase());
                colorValido = true;
            } catch (Exception e) {
                colorValido = false;
            }
        } while (!colorValido);
        do {
            System.out.println(almacen.categoriasDisponibles());
            categoria = leer.nextInt();
            leer.nextLine();
            if (almacen.categoriaValida(categoria)) {
                categoriaValida = true;
            } else {
                System.out.println("Categoría no válida");
            }
        } while (!categoriaValida);

        almacen.nuevoPieza(nombre, color, precio, categoria);
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