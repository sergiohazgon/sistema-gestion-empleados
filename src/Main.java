import controller.GestorFichaje;
import controller.GestorUsuarios;
import model.Administrador;
import model.Trabajador;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Administrador admin = new Administrador("Admin", "Sistema", "00000000A", "admin@empresa.com", "admin123");

    public static void main(String[] args) {
        GestorUsuarios.cargarTrabajadores();
        System.out.println("=== SISTEMA DE FICHAJE ===");

        while (true) {
            System.out.print("\nUsuario (correo): ");
            String correo = sc.nextLine();
            System.out.print("Contraseña: ");
            String contrasena = sc.nextLine();

            if (correo.equals(admin.getCorreo()) && contrasena.equals(admin.getContrasena())) {
                menuAdmin();
            } else {
                Trabajador t = GestorUsuarios.buscarTrabajador(correo, contrasena);
                if (t != null) {
                    menuTrabajador(t);
                } else {
                    System.out.println("Usuario o contraseña incorrectos. Inténtalo de nuevo.");
                }
            }
        }
    }

    static void menuAdmin() {
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Dar de alta trabajador");
            System.out.println("2. Dar de baja trabajador");
            System.out.println("3. Ver datos de trabajador");
            System.out.println("4. Ver todos los trabajadores");
            System.out.println("5. Cambiar contraseña de trabajador");
            System.out.println("6. Ver registros de jornada");
            System.out.println("7. Cerrar sesión");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: GestorUsuarios.altaTrabajador(); break;
                case 2: GestorUsuarios.bajaTrabajador(); break;
                case 3: GestorUsuarios.verDatosTrabajador(); break;
                case 4: GestorUsuarios.verTrabajadores(); break;
                case 5: GestorUsuarios.cambiarContrasena(); break;
                case 6: GestorFichaje.verFichajes(); break;
                case 7: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    static void menuTrabajador(Trabajador t) {
        int opcion = 0;
        while (opcion != 2) {
            System.out.println("\n=== MENÚ TRABAJADOR ===");
            System.out.println("Bienvenido, " + t.getNombre());
            System.out.println("1. Realizar fichaje");
            System.out.println("2. Cerrar sesión");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: GestorFichaje.registrarFichaje(t.getCorreo()); break;
                case 2: System.out.println("Sesión cerrada."); break;
                default: System.out.println("Opción no válida.");
            }
        }
    }
}