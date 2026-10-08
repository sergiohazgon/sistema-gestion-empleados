package model;

public class Trabajador extends Usuario {


    public Trabajador(String nombre, String apellido, String dni, String correo, String contrasena) {
        super(nombre, apellido, dni, correo, contrasena);
    }


    @Override
    public String toString() {
        return super.toString() + "\nRol: Trabajador";
    }

}
