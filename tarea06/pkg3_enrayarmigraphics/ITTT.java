/**
 * @author wart0137
 */
package pkg3_enrayarmigraphics;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITTT  extends Remote{
    String currentBoard() throws RemoteException;
    boolean play(int row, int column, int player) throws RemoteException;
    int checkWinner() throws RemoteException;
}    
