/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_1;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

/**
 *
 * @author wart
 */


public class ServidorCentral
        extends UnicastRemoteObject
        implements InterfaceBancoCentral {

    ServidorCentral() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public double cotizaciondolar(String fecha) throws RemoteException {
        System.out.println("Cotizando para la fecha" + fecha);
        double aux = 0;
        switch (fecha) {
            case "26-06-19":
                aux = 6.90;
                break;
            case "27-06-19":
                aux = 6.91;
                break;
            case "28-06-19":
                aux = 6.93;
                break;
            case "29-06-19":
                aux = 6.92;
                break;
            case "30-06-19":
                aux = 6.96;
                break;
            default:
                aux = 0;
        }
        return aux;
    }

    public static void main(String args[]) {
        try {
            ServidorCentral banco;
            LocateRegistry.createRegistry(1099);
            banco = new ServidorCentral();
            Naming.bind("BancoCentral", banco);
            System.out.println("El servidor esta listo banco Central\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
