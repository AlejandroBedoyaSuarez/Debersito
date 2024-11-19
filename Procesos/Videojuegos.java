package Procesos;

public class Videojuegos {
    protected String titulo;
    protected String genero;
    protected double precio;

    public Videojuegos(String titulo, String genero, double precio) {
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecio() {
        return precio;
    }

    public String detalles() {
        return "Título: " + titulo + ", Género: " + genero + ", Precio: $" + precio;
    }
}