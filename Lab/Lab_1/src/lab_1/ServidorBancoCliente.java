/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author wart
 */

public class ServidorBancoCliente {

    private static boolean verificar(int idcliente, double monto) {
  
        System.out.println("Verificando para: "+idcliente+" "+"monto"+monto);
        boolean aux = false;
        switch (idcliente) {

            case 1:
                aux = (monto <= 300);
                break;
            case 2:
                aux = (monto <= 400);
                break;
            case 3:
                aux = (monto <= 1000);
                break;
        }
        System.out.println(aux);
        return aux;
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 5001; // puerto en el que escuchara el socket

        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while (true) {   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                //cadena --> idcliente,monto
                System.out.println("cadena recibida:"+" "+cadena);

                String[] partes = cadena.split(",");

                int idCliente = Integer.parseInt(partes[0]);
 
                double monto = Double.parseDouble(partes[1]);//Double.valueOf(cadena)
                String respuesta = "";
                if (verificar(idCliente, monto)) {
                    respuesta = "SI";
                } else {
                    respuesta = "NO";
                }

                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
               
                System.out.println(respuesta);//ojo
                //imprime cadena recibida desde el cliente
                //Thread.sleep(3000);
                //for (int i=0;i<=10000000;i++);
                toClient.flush(); // 
                toClient.println(respuesta);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

