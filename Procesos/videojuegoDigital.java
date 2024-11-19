package Procesos;

public class videojuegoDigital extends Videojuegos {
    protected String codigoDescarga;

    public videojuegoDigital(String titulo, String genero, double precio, String codigoDescarga) {
        super(titulo, genero, precio);
        this.codigoDescarga = codigoDescarga;
    }

    public String getCodigoDescarga() {
        return codigoDescarga;
    }

    @Override
    public String detalles() {
        return super.detalles() + ", Código de Descarga: " + codigoDescarga;
    }
}
