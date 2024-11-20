package Procesos;

import GUI.interfazGrafica;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Usamos SwingUtilities para asegurar que la interfaz gráfica se ejecute en el hilo adecuado
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                interfazGrafica frame = new interfazGrafica();  // Crear una instancia de la GUI
                frame.setVisible(true);  // Mostrar la ventana
            }
        });
    }
}