package factorialsockettcp;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args){
        int port = 5001; // puerto de comunicacion
        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.print("Introduzca un numero : ");
            int num = Integer.parseInt(br.readLine());
            
            Socket client = new Socket("localhost", port); //conectarse al socket
            
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            toServer.println(num);  //mandar alservidor 
            String result = fromServer.readLine();  // devolver del servidor
            System.out.println("El numero devuelto es: "+result);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
