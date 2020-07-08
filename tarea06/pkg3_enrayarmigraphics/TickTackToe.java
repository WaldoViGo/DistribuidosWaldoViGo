/**
 * @author wart0137
 */

package pkg3_enrayarmigraphics;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class TickTackToe extends UnicastRemoteObject implements ITTT{
    /** El Tablero del Juego */
    private char board[][] = {
        { '1', '2', '3' }, /* Los valores iniciales son números de referencia */
        { '4', '5', '6' }, /* usado para seleccionar un cuadrado vacante para*/
        { '7', '8', '9' } /* un turno. */
    };
    
    private int nextPlayer = 0;// Siguiente Jugador 
    private int numPlays = 0;// Numero de Jugadas 
    
    public TickTackToe() throws RemoteException{
        super();
    }
    
    /* Devuelve una representación textual del tablero de juego actual. */
    @Override
    public String currentBoard() throws RemoteException{
	StringBuilder sb = new StringBuilder();
	sb.append("\n\n ");

	/* bloquear el objeto actual(espacios)*/
	synchronized (this){
            sb.append(board[0][0]).append(" | ");
            sb.append(board[0][1]).append(" | ");
            sb.append(board[0][2]).append(" ");
            sb.append("\n---+---+---\n ");
            sb.append(board[1][0]).append(" | ");
            sb.append(board[1][1]).append(" | ");
            sb.append(board[1][2]).append(" ");
            sb.append("\n---+---+---\n ");
            sb.append(board[2][0]).append(" | ");
            sb.append(board[2][1]).append(" | ");
            sb.append(board[2][2]).append(" \n");
	}
	/* desbloquear*/
        return sb.toString();
    }

    /**hacer la movida/jugada segun el jugador */
    @Override
    public boolean play(int row, int column, int player) throws RemoteException{
        // fuera del tablero ?
        if (!(row >= 0 && row < 3 && column >= 0 && column < 3)){
	return false;}
        // bloquear
	synchronized (this){
            if (board[row][column] > '9'){ // casilla invalida ?
                return false;}
            if (player != nextPlayer){ // turno del jugador ?
                return false;}
            if (numPlays == 9){ // quedan mas jugadas?
                return false;}
            
            /* inserte el simbolo del jugador */
            board[row][column] = (player == 1) ? 'X' : 'O';
            nextPlayer = (nextPlayer + 1) % 2;
            numPlays++;
            return true;
	}
	// desbloquear para el sgt.
    }
    /**
     *Comprueba si hay un ganador del juego. Palabra clave sincronizada significa que el bloqueo
     *del objeto se adquiere cuando se llama al método y se libera de regreso.
     */
    @Override
    public synchronized int checkWinner() throws RemoteException{
	int i;
	/* Busque una línea ganadora: primero las diagonales */
	if ((board[0][0] == board[1][1] && board[0][0] == board[2][2]) || (board[0][2] == board[1][1] && board[0][2] == board[2][0])){
            if (board[1][1] == 'X'){
		return 1;}
            else{
		return 0;}
        }else
	/* Verifica filas y columnas para ver si hay un ganador */
            for (i = 0; i <= 2; i++){
                if ((board[i][0] == board[i][1] && board[i][0] == board[i][2])){
                    if (board[i][0] == 'X'){
                        return 1;}
                    else{
                        return 0;}
                }
                if ((board[0][i] == board[1][i] && board[0][i] == board[2][i])){
                    if (board[0][i] == 'X'){
                        return 1;}
                    else{
                        return 0;}
                }
            }
            if(numPlays == 9){
                return 2; /* Empate! */
            }else{
                return -1; /* El juego aun no termina */
            }    
    }
    /*pruevba 1
    public class TickTackToe implements Runnable{
        private JFrame frame;
	private final int WIDTH = 506;
	private final int HEIGHT = 527;
	private Thread thread;
        
        private Painter painter;
	private DataOutputStream dos;
	private DataInputStream dis;
        
        private BufferedImage board;
	private BufferedImage redX;
	private BufferedImage blueX;
	private BufferedImage redCircle;
	private BufferedImage blueCircle;


    }*/
} 
