package ejer02;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;

/**
 *
 * wart_0137
 */

public class Cliente{
    
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        InterfazOperaciones Operes;
        
        try {
            Operes = (InterfazOperaciones) Naming.lookup("rmi://localhost/Calculadora");
            int op = 0;

            do {
                System.out.println("1.- Sumatoria");
                System.out.println("2.- Factorial");
                System.out.println("3.- Cadena Invertida");
                System.out.println("INTRODUCIR OPCION:");
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        Scanner entrada = new Scanner(System.in);
                        int NumSumatoria; 
                        System.out.print("Por Favor, Ingrese un Número: "); 
                        NumSumatoria = entrada.nextInt();
                        System.out.println( "   "+NumSumatoria+" = "+Operes.Sumatoria(NumSumatoria));
                        break;
                    case 2:
                        Scanner entrada1 = new Scanner(System.in);
                        int NumFactorial; 
                        System.out.print("Por Favor, Ingrese un Número: "); 
                        NumFactorial = entrada1.nextInt();
                        System.out.println( "   "+NumFactorial+"! = "+Operes.Factorial(NumFactorial));
                        break;
                    case 3:
                        Scanner entrada2 = new Scanner(System.in);
                        String Caracter; 
                        System.out.print("Por Favor, Ingrese cualquier caracter alfabetico o palabra: "); 
                        Caracter = entrada2.nextLine();
                         System.out.println( "   "+Caracter+" = "+Operes.CadInvertida(Caracter));
                        break;
                    default:
                        throw new AssertionError();
                }
            } while (op != 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**
public class Cliente{
    public static void main(String args[]){
	InterfazOperaciones calculadora;
      int numero;
	try {
	    calculadora=(InterfazOperaciones)Naming.lookup("rmi://localhost/Calculadora");
            numero=5;
	    System.out.println();
	    System.out.println( "   "+numero+"! = "+calculadora.Factorial(numero));
	    System.out.println();
	}
	catch (Exception e){
	    e.printStackTrace();
	}
    }
}

Les adjunto un codigo completo sobre como ingresar datos por teclado.
Traten de ir mejorandolo y agregarle nueva funcionalidades


public static void main(String[] args) {
  
 //-----------------------------------------------------//
 //------------- DECLARAMOS LAS VARIABLES --------------//
 //-----------------------------------------------------//
  
 Scanner entrada = new Scanner(System.in);
 String nombre;
 String direccion;
 int edad;
 double sueldo;
  
 //-----------------------------------------------------//
 //--------------- CARGAMOS LOS VALORES ----------------//
 //-----------------------------------------------------//
  
 System.out.println(".:: BIENVENIDOS ::.");
  
 System.out.print("Ingrese su nombre y apellido: ");
 nombre = entrada.nextLine();
  
 System.out.print("Ingrese su direccion: ");
 direccion = entrada.nextLine();
  
 System.out.print("Ingrese la edad: ");
 edad = entrada.nextInt();
  
 System.out.print("Ingrese el sueldo: ");
 sueldo = entrada.nextDouble();
  
  
 //-----------------------------------------------------//
 //------------- IMPRIMIMOS LOS RESULTADOS -------------//
 //-----------------------------------------------------//
  
 System.out.print("\n\n LOS RESULTADOS INGRESADOS SON:\n\n");
  
 System.out.println("NOMBRE: \t" + nombre);
 System.out.println("DIRECCION: \t" + direccion);
 System.out.println("EDAD: \t\t" + edad);
 System.out.println("SUELDO: \t" + "$ " + sueldo);
}
**/