package ejer02;

/**
 *
 * @author wart
 */

import java.rmi.*;

public interface InterfazOperaciones extends Remote {
    int Sumatoria(int arg) throws RemoteException;
    long Factorial(long arg) throws RemoteException;
    String CadInvertida(String arg) throws RemoteException;
    
}
