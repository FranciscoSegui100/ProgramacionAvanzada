/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelocteservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Definimos el servidor y el socket que tomara paquete, los datos de entrada y salida y el puerto donde se inicia el svdor
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out; 
        final int puerto = 8000;
        
        try {
            //iniciamos el svdr
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");
            
            
            while(true){
                // conectamos el cliente al svdr
                sc = servidor.accept();
                
                System.out.println("Clte conectado");
                
                //definimos la lectura del paquete
                in =  new DataInputStream(sc.getInputStream());
                
                //Se leen los datos del cliente, lo que manda el cliente: 
                String msjClte = in.readUTF();
                
                //logica de la conversion:
                //se llevaba a un array el msj
                String[] moneda= msjClte.split("\\s+");
                
                //se separa el array para saber el tipo de moneda
                String tipoMoneda = moneda[0].toLowerCase();
                
                //se separa y se convierte a double para saber la cantidad a convertir
                double cantidad = Double.parseDouble(moneda[1]);
                
                double precioFinal=0;
                
                System.out.println("Mensaje del Clte " + msjClte);
                
                //se convierte a la moneda que el clte eligio
                switch(tipoMoneda){
                    case "usd":
                         precioFinal = cantidad * 1400;
                        break;
                        
                    case "clp":
                        precioFinal = cantidad * 1.65;
                        break;
                    case "eur":
                        precioFinal = cantidad *1610;
                        break;
                }                
                
                //se crea la salida del paquete para la devolucion del clte
                out =  new DataOutputStream(sc.getOutputStream());
                
                //se da la devolucion
                out.writeUTF("La cotizacion es de: " + precioFinal);
                
                //se desconecta al clte
                sc.close();
                System.out.println("Clte desconectado");
                
                
            }
        
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
