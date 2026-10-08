package controller;

import model.Trabajador;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorUsuarios {

    private static ArrayList<Trabajador> trabajadores = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static final String FICHERO = "trabajadores.dat";

    // Guardar la lista de trabajadores en un fichero
    public static void guardarTrabajadores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
            oos.writeObject(trabajadores);
        } catch (IOException e) {
            System.out.println("Error al guardar los trabajadores: " + e.getMessage());
        }
    }

    // Cargar la lista de trabajadores
    public static void cargarTrabajadores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO))) {
            trabajadores = (ArrayList<Trabajador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No hay trabajadores guardados todavía.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los trabajadores: " + e.getMessage());
        }
    }
    // Dar de alta un nuevo trabajador y añadir a la lista
    public static void altaTrabajador() {
        System.out.println("\n--- DAR DE ALTA TRABAJADOR ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("DNI: ");
        String dni = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        for (Trabajador t : trabajadores) {
            if (t.getDni().equals(dni)) {
                System.out.println("Ya existe un trabajador con ese DNI.");
                return;
            }
        }

        trabajadores.add(new Trabajador(nombre, apellido, dni, correo, contrasena));
        guardarTrabajadores();
        System.out.println("Trabajador dado de alta correctamente.");
    }

    // Eliminar trabajador de la lista con su DNI
    public static void bajaTrabajador() {
        System.out.println("\n--- DAR DE BAJA TRABAJADOR ---");
        System.out.print("Introduce el DNI del trabajador: ");
        String dni = sc.nextLine();

        Trabajador encontrado = null;
        for (Trabajador t : trabajadores) {
            if (t.getDni().equals(dni)) {
                encontrado = t;
                break;
            }
        }

        if (encontrado != null) {
            trabajadores.remove(encontrado);
            guardarTrabajadores();
            System.out.println("Trabajador dado de baja correctamente.");
        } else {
            System.out.println("No se encontró ningún trabajador con ese DNI.");
        }
    }

    // Mostrar datos de un trabajador
    public static void verDatosTrabajador() {
        System.out.println("\n--- VER DATOS DE TRABAJADOR ---");
        System.out.print("Introduce el DNI del trabajador: ");
        String dni = sc.nextLine();

        for (Trabajador t : trabajadores) {
            if (t.getDni().equals(dni)) {
                System.out.println(t.toString());
                return;
            }
        }
        System.out.println("No se encontró ningún trabajador con ese DNI.");
    }

    // Ver total de trabajadores
    public static void verTrabajadores() {
        System.out.println("\n--- LISTA DE TRABAJADORES ---");
        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }
        for (Trabajador t : trabajadores) {
            System.out.println("-------------------------");
            System.out.println(t.toString());
        }
        System.out.println("-------------------------");
    }

    // Cambiar contraseña de un trabajador
    public static void cambiarContrasena() {
        System.out.println("\n--- CAMBIAR CONTRASEÑA ---");
        System.out.print("Introduce el DNI del trabajador: ");
        String dni = sc.nextLine();

        for (Trabajador t : trabajadores) {
            if (t.getDni().equals(dni)) {
                System.out.print("Nueva contraseña: ");
                String nuevaContrasena = sc.nextLine();
                t.setContrasena(nuevaContrasena);
                guardarTrabajadores();
                System.out.println("Contraseña cambiada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún trabajador con ese DNI.");
    }


    // Login con correo y contraseña
    public static Trabajador buscarTrabajador(String correo, String contrasena) {
        for (Trabajador t : trabajadores) {
            if (t.getCorreo().equals(correo) && t.getContrasena().equals(contrasena)) {
                return t;
            }
        }
        return null;
    }
}

