package Procesos;

public class Cliente {
    protected String nombre;
    protected String correo;

    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String detalles() {
        return "Cliente: " + nombre + ", Correo: " + correo;
    }
}

