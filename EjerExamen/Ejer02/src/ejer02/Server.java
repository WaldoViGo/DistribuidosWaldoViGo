/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejer02;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 *
 * @author wart
 */

public class Server 
    extends UnicastRemoteObject
    implements InterfazOperaciones 
{
    Server() throws java.rmi.RemoteException{
	super();
    }
    
    public int Sumatoria(int numero){
        int iSumatorio = 0;
        int iContador = numero;
		
        while (iContador != 0) {	   
            iSumatorio = iSumatorio + iContador;
            iContador--;
        }       
        return iSumatorio;
    }

    public long Factorial(long a) {
	if (a==0 || a==1) {
	    return 1;
	} else {
	    return a*Factorial(a-1);
	}
    }
    
    public String CadInvertida(String sCadena){
        String cadInvertida="";
        for (int x=sCadena.length()-1;x>=0;x--){
            cadInvertida = cadInvertida + sCadena.charAt(x);
        }   
        return cadInvertida;
    }
    
    public static void main(String args[]) { 
	try {
	    Server Operes;
	    LocateRegistry.createRegistry(1099); // registrar el servidor e rmi register
	    Operes=new Server(); 
	    Naming.bind("Calculadora", Operes); 
            System.out.println("El servidor esta listo\n");
        }
	catch (Exception e){
	    e.printStackTrace();
	}
    } 
}