package Procesos;

public class videojuegoFisico extends Videojuegos {
    protected int stock;

    public videojuegoFisico(String titulo, String genero, double precio, int stock) {
        super(titulo, genero, precio);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock() {
        //if (stock > 0) verifica si el valor de la variable stock es mayor que 0
        //Si stock es mayor que 0, entonces ejecuta stock--, que es igual a stock = stock - 1. Es decir, reduce en 1 la cantidad de stock.
        //Si stock ya es 0 o un número negativo, no hace nada
        if (stock > 0) stock--;   
    }

    @Override
    public String detalles() {
        return super.detalles() + ", Stock: " + stock;
    }
}

