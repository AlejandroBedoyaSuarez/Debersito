package GUI;
import javax.swing.*;
import Procesos.Cliente;
import Procesos.Compra;
import Procesos.Videojuegos;
import Procesos.videojuegoDigital;
import Procesos.videojuegoFisico;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class interfazGrafica extends JFrame {
    protected ArrayList<Videojuegos> inventario = new ArrayList<>();
    protected Cliente cliente;
    protected JTextArea displayArea;
    protected JTextField inputField;

    @SuppressWarnings("unused")
    public interfazGrafica() {
        // Configuración de la ventana principal
        setTitle("Tienda de Videojuegos CarlitosLOL");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior (opciones)
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        JButton registrarJuegoBtn = new JButton("Registrar Videojuego");
        JButton registrarClienteBtn = new JButton("Registrar Cliente");
        JButton realizarCompraBtn = new JButton("Realizar Compra");
        JButton salirBtn = new JButton("Salir");

        panelSuperior.add(registrarJuegoBtn);
        panelSuperior.add(registrarClienteBtn);
        panelSuperior.add(realizarCompraBtn);
        panelSuperior.add(salirBtn);

        // Área de visualización
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Campo de entrada (para interacciones)
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(0, 30));

        // Agregar componentes a la ventana
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        // Acciones de los botones
        registrarJuegoBtn.addActionListener(e -> registrarVideojuego());
        registrarClienteBtn.addActionListener(e -> registrarCliente());
        realizarCompraBtn.addActionListener(e -> realizarCompra());
        salirBtn.addActionListener(e -> System.exit(0));
    }

    protected void registrarVideojuego() {
        // Solicitar datos del videojuego
        String[] opciones = {"Físico", "Digital"};
        int tipo = JOptionPane.showOptionDialog(this, "Selecciona el tipo de videojuego:", 
                "Registrar Videojuego", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (tipo == -1) return; // Si el usuario cancela

        String titulo = JOptionPane.showInputDialog(this, "Título:");
        String genero = JOptionPane.showInputDialog(this, "Género:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog(this, "Precio:"));

        if (tipo == 0) { // Físico
            int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Stock inicial:"));
            inventario.add(new videojuegoFisico(titulo, genero, precio, stock));
        } else { // Digital
            String codigo = JOptionPane.showInputDialog(this, "Código de descarga:");
            inventario.add(new videojuegoDigital(titulo, genero, precio, codigo));
        }

        displayArea.append("Videojuego registrado: " + titulo + "\n");
    }

    protected void registrarCliente() {
        // Solicitar datos del cliente
        String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente:");
        String correo = JOptionPane.showInputDialog(this, "Correo del cliente:");

        cliente = new Cliente(nombre, correo);

        displayArea.append("Cliente registrado: " + cliente.detalles() + "\n");
    }

    protected void realizarCompra() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Primero debes registrar un cliente.");
            return;
        }

        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El inventario está vacío. Registra videojuegos primero.");
            return;
        }

        Compra compra = new Compra(cliente);

        String[] opciones = new String[inventario.size()];
        for (int i = 0; i < inventario.size(); i++) {
            opciones[i] = inventario.get(i).detalles();
        }

        while (true) {
            String seleccion = (String) JOptionPane.showInputDialog(this, 
                    "Selecciona un videojuego para agregar al carrito:", 
                    "Realizar Compra", JOptionPane.PLAIN_MESSAGE, null, 
                    opciones, null);

            if (seleccion == null) break; // Cancelar o finalizar

            for (Videojuegos juego : inventario) {
                if (seleccion.contains(juego.getTitulo())) {
                    compra.agregarVideojuego(juego);
                    displayArea.append("Agregado al carrito: " + juego.getTitulo() + "\n");
                    break;
                }
            }
        }

        // Mostrar recibo
        StringBuilder recibo = new StringBuilder();
        recibo.append(cliente.detalles()).append("\nJuegos comprados:\n");
        double total = 0;
        for (Videojuegos juego : compra.carrito) {
            recibo.append(juego.detalles()).append("\n");
            total += juego.getPrecio();
        }
        recibo.append("Total a pagar: $").append(total).append("\n");

        JOptionPane.showMessageDialog(this, recibo.toString(), "Recibo", JOptionPane.INFORMATION_MESSAGE);
    }
}