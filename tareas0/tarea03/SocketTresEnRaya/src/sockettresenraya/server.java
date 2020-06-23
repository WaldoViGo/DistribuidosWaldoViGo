package sockettresenraya;
import java.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class server {
    
    public static void main(String[] args) throws InterruptedException{
        int port =5001; // puerto en el que escuchara el socket
        try{
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;      
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            Stripe game = new Stripe(); //<-* 
            while(true){   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // el servidorse queda esperando establecer conexion 
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena;
                cadena = fromClient.readLine(); //cadena obtenida desde el lector
                System.out.print(cadena);
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                //System.out.println(cadena+client.getInetAddress());
               
                //imprime cadena recibida desde el cliente
                //Thread.sleep(3000);
                 //for (int i=0;i<=10000000;i++);
                toClient.flush(); // 
                //toClient.println("Hola"+cadena);
                System.out.print(client);
                char marca = cadena.charAt(cadena.length()-1);
                if(Character.toString(marca).equals("x")){
                    System.out.print("Player Movement 'x' (client)");
                }else{
                    System.out.print("Player Movement 'o' (client1)");
                }
                // works with caracter 'x'
                cadena = cadena.substring(0,cadena.length()-1);//recovered received valor
                if(cadena.equals("reini")){
                    cadena="juego reiniciado, marca tu primera jugad en el tablero";
                    game.Reiniciar(); //tabla 3 raya
                }else{
                    //pull apart strings from coordinates
                    //mark over board
                    Pattern pat = Pattern.compile("[0,1,2],[0,1,2]");
                    Matcher mat = pat.matcher(cadena);
                    if(mat.matches()){
                        String coordenada=cadena;
                        char cx=coordenada.charAt(0);
                        int x = Integer.parseInt(Character.toString(cx));
                        char cy= coordenada.charAt(2);
                        int y = Integer.parseInt(Character.toString(cy));
                        cadena=game.marcar(x,y,marca);
                    }else{
                        cadena="jugada mal introducida,vuelva a intentar";
                    }
                }
            game.marcaciones();
            if(Character.toString(marca).equals("x")){
                System.out.println("Es el turno de 'o' (client1)");
            }else{
                System.out.println("Es el turno de 'x' (client)");
            }
            toClient.println(cadena);
        }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
