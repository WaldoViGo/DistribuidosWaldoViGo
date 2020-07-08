/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitictactoe;

/**
 *@wart
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface InterfazStripe extends Remote{
    void Reiniciar()throws RemoteException;
    String marcar(int x,int y ,char marca) throws RemoteException;
    Boolean ganador (char marca) throws RemoteException;
    Boolean empate() throws RemoteException;
    char [][] getTabla()throws RemoteException;
}
