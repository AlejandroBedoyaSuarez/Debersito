package Procesos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Videojuegos> inventario = new ArrayList<>();
        Cliente cliente = null;
        // iniciamos
        while (true) {
            System.out.println("\n=== Tienda de Videojuegos CarlitosLOL ===");
            System.out.println("1. Registrar videojuego");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Realizar compra");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            // salto de linea
            scanner.nextLine();

            if (opcion == 1) {
                System.out.println("¿Qué tipo de videojuego deseas registrar?");
                System.out.println("1. Físico");
                System.out.println("2. Digital");
                int tipo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Género: ");
                String genero = scanner.nextLine();
                System.out.print("Precio: ");
                double precio = scanner.nextDouble();

                if (tipo == 1) {
                    System.out.print("Stock inicial: ");
                    int stock = scanner.nextInt();
                    inventario.add(new videojuegoFisico(titulo, genero, precio, stock));
                } else if (tipo == 2) {
                    System.out.print("Código de descarga: ");
                    scanner.nextLine(); // Limpiar buffer
                    String codigo = scanner.nextLine();
                    inventario.add(new videojuegoDigital(titulo, genero, precio, codigo));
                }
                System.out.println("Videojuego registrado con éxito.");

            } else if (opcion == 2) {
                System.out.print("Nombre del cliente: ");
                String nombre = scanner.nextLine();
                System.out.print("Correo del cliente: ");
                String correo = scanner.nextLine();
                cliente = new Cliente(nombre, correo);
                System.out.println("Cliente registrado con éxito.");

            } else if (opcion == 3) {
                if (cliente == null) {
                    System.out.println("Primero debes registrar un cliente.");
                    continue;
                }
                if (inventario.isEmpty()) {
                    System.out.println("El inventario está vacío. Registra videojuegos primero.");
                    continue;
                }

                Compra compra = new Compra(cliente);

                System.out.println("Videojuegos disponibles:");
                for (int i = 0; i < inventario.size(); i++) {
                    System.out.println((i + 1) + ". " + inventario.get(i).detalles());
                }

                while (true) {
                    System.out.print(
                            "Selecciona el número del videojuego que deseas agregar al carrito (0 para finalizar): ");
                    int seleccion = scanner.nextInt();
                    if (seleccion == 0)
                        break;
                    if (seleccion > 0 && seleccion <= inventario.size()) {
                        compra.agregarVideojuego(inventario.get(seleccion - 1));
                    } else {
                        System.out.println("Opción no válida.");
                    }
                }

                System.out.println("\n=== Recibo ===");
                compra.imprimirRecibo();

            } else if (opcion == 4) {
                System.out.println("¡Gracias por usar la tienda de videojuegos CarlitosLOL!");
                scanner.close();
                break;

            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}