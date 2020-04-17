/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factorialsocketudp;

/**
 *
 * @author Carlos
 */
import java.net.*;
import java.io.*;

public class ClientUDP {

  // Los argumentos proporcionan el mensaje y el nombre del servidor
  public static void main(String args[]) {
    int puerto = 6789;

    try {
        //String dato="prueba";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br= new BufferedReader(isr);
        System.out.print("Introduzca un numero : ");
        int num = Integer.parseInt(br.readLine());
        String numCade=num+"";
        String ip="localhost";
        try (DatagramSocket socketUDP = new DatagramSocket()) {
            byte[] mensaje = numCade.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);
            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion =
                    new DatagramPacket(mensaje, numCade.length(), hostServidor,
                            puerto);
            // Enviamos el datagrama
            socketUDP.send(peticion);
            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta =
                    new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);
            // Enviamos la respuesta del servidor a la salida estandar
            System.out.println("Respuesta: " + new String(respuesta.getData()));
            // Cerramos el socket
        }
    } catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
  }
}

