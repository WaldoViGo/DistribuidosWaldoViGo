package lab_1;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author wart_0137
 */
public interface InterfaceServidorReserva extends Remote {
   double Cotizar(String inicio, String fin, String fechacotizacion)throws RemoteException;
   boolean reserva(String inicio,String fin, int idcliente, String fechaCompra) throws RemoteException;
}
