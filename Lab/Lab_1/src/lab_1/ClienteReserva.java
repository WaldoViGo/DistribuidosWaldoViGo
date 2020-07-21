package lab_1;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
/**
 *
 * wart_0137
 */
public class ClienteReserva{
    
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        InterfaceServidorReserva reserva;

        try {
            reserva = (InterfaceServidorReserva) Naming.lookup("rmi://localhost/ServidorReserva");
            int op = 0;

            do {
                System.out.println("1.- COTIZAR");
                System.out.println("2.- RESERVAR");
                System.out.println("3.- SALIR");
                System.out.println("INTRODUCIR OPCION:");
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        double cotizacion = reserva.Cotizar("26-06-19", "26-06-19", "26-06-19");
                        System.out.println(cotizacion);
                        break;
                    case 2:
                        System.out.println("INTRODUZCA EL ID DEL CLIENTE");
                        int idcliente = sc.nextInt();
                        if (reserva.reserva("26-06-19", "26-06-19", idcliente, "26-06-19")) {
                            System.out.println("Se logro hacer la reserva");
                        } else {
                            System.out.println("Error no se logro hacer la reserva");
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            } while (op != 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

