package model;

public class Administrador extends Usuario {


    public Administrador(String nombre, String apellido, String dni, String correo, String contrasena) {
        super(nombre, apellido, dni, correo, contrasena);
    }


    @Override
    public String toString() {
        return super.toString() + "\nRol: Administrador";
    }

}
