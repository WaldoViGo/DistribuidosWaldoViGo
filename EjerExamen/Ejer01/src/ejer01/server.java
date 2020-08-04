package ejer01;
import java.io.*;
import java.net.*;

/**
 *
 * @author wart
 */

public class server {
    public static void main(String[] args) throws InterruptedException{
        int port =5001; // puerto en el que escuchara el socket
        
        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;      
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while(true){   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                int numero=Integer.parseInt(cadena);
                toClient = new PrintStream(client.getOutputStream());
                System.out.println(cadena+client.getInetAddress());
                if(numero%2==0){
                    toClient.flush(); // 
                    toClient.println("El numero "+cadena+" es Par");
                }
                else{
                    toClient.flush();
                    toClient.println("El número "+cadena+" es Impar");
                }
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
