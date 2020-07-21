/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * wart_0137
 */
public interface InterfaceBancoCentral extends Remote{
    double cotizaciondolar(String fecha) throws RemoteException;
}