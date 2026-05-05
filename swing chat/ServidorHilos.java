package hilos.mvc;

import javax.swing.*;

public class ClienteControlador {
    private ClienteModelo modelo;
    private ClienteVista vista;

    public ClienteControlador(ClienteModelo modelo, ClienteVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        // Botón Conectar
        vista.btnConectar.addActionListener(e -> {
            String ip = vista.txtIp.getText();
            int puerto = Integer.parseInt(vista.txtPuerto.getText());
            String nombre = vista.txtNombre.getText();

            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar un nombre");
                return;
            }

            try {
                // Pasamos una función (Callback) para actualizar la vista cuando lleguen mensajes
                modelo.conectar(ip, puerto, nombre, mensaje -> {
                    // Asegurar que las actualizaciones de la GUI se hagan en el hilo de Swing
                    SwingUtilities.invokeLater(() -> vista.agregarMensaje(mensaje));
                });
                vista.habilitarAcciones(true);
                vista.agregarMensaje("--- Conectado al servidor ---");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al conectar: " + ex.getMessage());
            }
        });

        // Botón Fecha
        vista.btnFecha.addActionListener(e -> {
            try { modelo.pedirFechaHora(); } catch (Exception ex) { error(ex); }
        });

        // Botón Usuarios
        vista.btnUsuarios.addActionListener(e -> {
            try { modelo.pedirUsuariosConectados(); } catch (Exception ex) { error(ex); }
        });

        // Botón Multiplicar
        vista.btnMultiplicar.addActionListener(e -> {
            try {
                int n1 = Integer.parseInt(vista.txtN1.getText());
                int n2 = Integer.parseInt(vista.txtN2.getText());
                int n3 = Integer.parseInt(vista.txtN3.getText());
                modelo.enviarMultiplicacion(n1, n2, n3);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese solo números válidos en N1, N2 y N3");
            } catch (Exception ex) { error(ex); }
        });

        // Botón Enviar Mensaje
        vista.btnEnviarMsg.addActionListener(e -> {
            String destino = vista.txtDestinatario.getText();
            String msg = vista.txtMensaje.getText();
           
            if(!msg.trim().isEmpty()) {
                try { 
                    modelo.enviarMensajeChat(destino, msg); 
                    vista.txtMensaje.setText(""); // Limpiar caja
                } catch (Exception ex) { error(ex); }
            }else{
                JOptionPane.showMessageDialog(vista, "Ingrese un mensaje");
            }
        });

        // Botón Desconectar (Salir)
        vista.btnSalir.addActionListener(e -> {
            modelo.desconectar();
            vista.habilitarAcciones(false);
            vista.agregarMensaje("--- Desconectado ---");
        });
    }

    private void error(Exception e) {
        JOptionPane.showMessageDialog(vista, "Error de comunicación: " + e.getMessage());
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}