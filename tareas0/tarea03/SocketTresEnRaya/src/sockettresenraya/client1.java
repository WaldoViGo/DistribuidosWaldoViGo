/**
 *
 * @author ®wart0137†
 */
package sockettresenraya;

//import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
//package sockettresenraya;
//import java.io.*;

public class client1{
    public static void main(String[] args){
        int port = 5001; // puerto de comunicacion

        try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            while(true){
            System.out.println("Introduzca una opcion:(client)\n\n"+"1.Marcar Posicion \n 2.Reiniciar Juego \n");
            String cadena = "";
            String opcion = br.readLine();
                if(opcion.equals("1")){
                System.out.println("Ingrese la posicion del tablero: (client)");
                System.out.println("[0,0],[0,1],[0,2] \n [1,0],[1,1],[1,2] \n [2,0],[2,1],[2,2] \n");
                System.out.println("Ponga la posicion en coordenadas sobre el tabler mostrado");
                String posicion=br.readLine();
                cadena=posicion;
                }
                else{
                    if(opcion.equals("2")){
                        cadena="reini";
                    }else{
                        System.out.println("NO marco corerectamente vuelva a intentarlo");
                    }
                }
            
            cadena = cadena+"o  ";
            
            Socket client = new Socket("localhost", port); //conectarse al socket
            
                PrintStream toServer = new PrintStream(client.getOutputStream());
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                toServer.println(cadena);//mandar al servidor
                String result = fromServer.readLine();//devolver del servidor
                //toServer.println(cadena);  //mandar alservidor 
                if(result.equals("ganador:x")){
                    break;
                }
                //String result = fromServer.readLine();  // devolver del servidor
                System.out.println(result);
            }
            System.out.println("Felicidades, has ganado!!!");
            //System.out.println("cadena devuelta es  : "+result);    
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
