/**
 * @author wart0137
 */
package pkg3_enrayarmigraphics;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    ITTT game;
    Scanner keyboardSc;
    int winner = 0;
    int player = 1;

    public Client() throws MalformedURLException, RemoteException, NotBoundException{
        game = (ITTT) Naming.lookup("gameRegistry");
	keyboardSc = new Scanner(System.in);
    }

    public int readPlay() {
        int play;
	do {
            System.out.printf("Jugador %d, por favor inserta el  numero de tu casilla "
			+ "donde desee %c (o presiona 0 para reiniciar el tablero):n\n",
			player, (player == 1) ? 'X' : 'O');
            play = keyboardSc.nextInt();
	} while (play > 9 || play < 1);//corregir aca para el reinicio
            return play;
	}

    public void playGame() throws RemoteException{
	int play;
	boolean playAccepted;
	do {
            player = ++player % 2;
            do {
		System.out.println(game.currentBoard());
		play = readPlay();
		if (play != 0){//corregira aca para el reinicio
                    playAccepted = game.play(--play / 3, play % 3, player);
                    if (!playAccepted)
			System.out.println("Invalido! Intente de Nuevo.");
		}else
                    playAccepted = false;
            } while (!playAccepted);
		winner = game.checkWinner();
	} while (winner == -1);
            System.out.println(game.currentBoard());
    }

    public void congratulate() {
	if (winner == 2)
            System.out.printf("\nQue Mediocre,es un Empate\n");
	else
            System.out.printf("\nFelicidades, jugador %d, Ganaste!\n", winner);
	}
	
    /** El programa vuelve a ejecutarse en el método principal.*/
    public static void main(String[] args) {
        /* TO DO */
        try{
            Client g = new Client();
            g.playGame();
            g.congratulate();
	}catch (MalformedURLException e){ e.printStackTrace();}
        catch (RemoteException e){ e.printStackTrace();}
	catch (NotBoundException e){ e.printStackTrace();}
    }
}    




































/*I. Quando se usa SUN RPC é gerado código para converter os dados de e para
um formato de rede. O que acontece quando se usa RMI?

Em Java RMI esse processo é feito pelo Proxy e seus stubs que representam o 
objeto remoto no cliente. O RMI torna-se, assim, transparente para o cliente, podendo esconder os detalhes de tradução de referências a objetos e a conversão e serialização dos parâmetros.
O stub agem para o cliente como um gateway e quando se invoca um método do stub
ele irá initializar a conexão com a JVM (java virtual machine) remota, escreve e transmite os parâmetros (marshals) para a JVM remota e, quando recebe o retorno ou exception, lê-os (unmarshals) e envia-os para quem o invocou.


II. Das classes e interfaces Java que usou, quais as que pertencem apenas ao
cliente, apenas ao servidor e a ambas?

    Cliente: Game.java                     (UI que invoca métodos de TTTService)
    Servidor: TTT.java e Application.java  (Implementação da interface remota)
    Ambas: TTTService.java                 (Interface remota)
*/
