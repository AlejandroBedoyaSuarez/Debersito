package Procesos;

import java.util.ArrayList;

public class Compra {
    protected Cliente cliente; //objeto de la clase Cliente, representa a la persona que está realizando la compra
    public ArrayList<Videojuegos> carrito; //sirve para hacer la lista

    //Hacemos el constructor
    public Compra(Cliente cliente) {
        this.cliente = cliente;
        this.carrito = new ArrayList<>(); //Inicializa la lista de videojuegos (carrito) como vacía, preparada para llenarse con los juegos que el cliente elija 
    }

    public void agregarVideojuego(Videojuegos juego) {
        //El operador instanceof  se usa para verificar si un objeto pertenece a una clase específica o a una subclase de esa clase
        //Usamos instanceof para determinar si el objeto juego es de tipo VideojuegoFisico y no simplemente de la clase base Videojuegos o de otro tipo, como videojuegoDigital
        if (juego instanceof videojuegoFisico) {
            videojuegoFisico fisico = (videojuegoFisico) juego;
            //verifica si hay stock disponible 
            if (fisico.getStock() > 0) {
                //Si hay stock, se reduce en 1 (fisico.reducirStock()) y el juego se agrega al carrito
                fisico.reducirStock();
                carrito.add(fisico);
            } else {
                //Si no hay stock, se muestra un mensaje de error.
                System.out.println("Stock insuficiente para: " + fisico.getTitulo());
            }
        } else {
            //si es digital, se añade al carrito
            carrito.add(juego);
        }
    }

    public void imprimirRecibo() {
        System.out.println(cliente.detalles());
        System.out.println("Juegos comprados:");
        double total = 0;
        for (Videojuegos juego : carrito) {
            System.out.println(juego.detalles());
            total += juego.getPrecio();
        }
        System.out.println("Total a pagar: $" + total);
    }
}