package controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorFichaje {


    private static final String fichero = "horas.txt";


    public static void registrarFichaje(String correo) {
        // Fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        String fechaHora = ahora.format(formato);

        // Escribir en el fichero
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true));
            writer.write(correo + " - " + fechaHora);
            writer.newLine();
            writer.close();
            System.out.println("Fichaje registrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el fichaje: " + e.getMessage());
        }
    }

    // Ver todos los fichajes
    public static void verFichajes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fichero));
            String linea;
            System.out.println("\n--- REGISTROS DE JORNADA ---");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No hay registros de jornada todavía.");
        }
    }
}
