package rmitictactoe;

import java.rmi.*;
import java.util.Scanner;
import java.rmi.server.*;

public class Cliente{
    public static void main(String args[]){
	InterfazStripe stripe;
        String cadena;
        Scanner sc= new Scanner(System.in);//<-
        System.out.println("Introducir jugador 0/x");//<-
        String jugador =sc.next();        
	try {
	    stripe =(InterfazStripe)Naming.lookup("rmi://localhost/tictactoe");
            while(stripe.empate()){
                mostrar(stripe.getTabla());
                System.out.println("Introducir poscion en  el siguiente formato 0,0");
                cadena=sc.next();
                cadena = cadena+","+jugador;
                String []cad= cadena.split(",");
                int x = Integer.parseInt(cad[0]);
                int y = Integer.parseInt(cad[1]);
                cadena = cad [2];
                char simbolo = cadena.charAt(0);
                System.out.println();
                System.out.println(stripe.marcar(x, y, simbolo));
                if(stripe.ganador(simbolo)){
                    break;
                }
            }
	    /*System.out.println();
	    System.out.println( "   "+numero+"! = "+calculadora.Factorial(numero));
	    System.out.println();*/
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
    public static void mostrar(char[][]arreglo)
    {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(arreglo[i][j]!='-'){
                    System.out.print(arreglo[i][j]); 
                }else
                    System.out.print(" ");
                if (j!=2)
                {
                    System.out.print("|");
                }
            }
            System.out.println("");
            if (i!=2){
                System.out.println("-+-+--");
            }
        } 
    }
}


