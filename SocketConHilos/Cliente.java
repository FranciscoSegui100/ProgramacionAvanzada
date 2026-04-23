/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    
   public static void main(String[] args) {
    try {
        Socket socket = new Socket("localhost", 9923);
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        Scanner sn = new Scanner(System.in);

        // 1. LEER la solicitud de nombre que manda el servidor
        System.out.println(entrada.readUTF()); 
        
        // 2. ENVIAR el nombre
        String nombre = sn.nextLine();
        salida.writeUTF(nombre);

        // 3. AHORA SÍ, creamos el objeto ClienteHilos
        ClienteHilos clienteHilos = new ClienteHilos(entrada, salida);

        // 4. INICIAMOS el hilo de escucha (el que tiene el while(true))
        clienteHilos.start();

        // 5. LANZAMOS el menú en el hilo principal
        clienteHilos.ejecutarMenu();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

