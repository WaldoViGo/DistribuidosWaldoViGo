package factorialsockettcp;
import java.io.*;
import java.net.*;

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
                String res;
                int num;
                num =Integer.parseInt(fromClient.readLine());
                int fact = 1;
                for(int i=1;i<=num;i++)
                    {
                    fact=fact*i;    
                    }
                res=Long.toString(fact);
                toClient=new PrintStream(client.getOutputStream());
                System.out.println("res"+client.getInetAddress());
                toClient.flush(); // 
                toClient.println("El resultado es:"+res);
                
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
