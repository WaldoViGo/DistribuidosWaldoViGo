package rmitictactoe;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.server.*;



public class Servidor 
    extends UnicastRemoteObject
    implements InterfazStripe
{
    public char [][] tabla ={{'-','-','-'},{'-','-','-'},{'-','-','-'}};
    private int turno = 1;
    Servidor () throws java.rmi.RemoteException
    {
        super();
    }
    
    public static void main(String args[]) { 
	try {
	    Servidor tictactoe;
	    LocateRegistry.createRegistry(1099); // registrar el servidor e rmi register
	    tictactoe = new Servidor(); 
	    Naming.bind("tictactoe", tictactoe); 
            System.out.println("El servidor esta listo\n");
        }
	catch (Exception e){
	    e.printStackTrace();
	}
    } 
    
    public void Reiniciar() throws RemoteException{
        turno = 1;
        tabla = new char [3][3];
        for (int i=0;i<3;i++){
         for(int j=0;j<3;i++){
         tabla[i][j]= '-';
         }
        }
    }
    
    public String  marcar (int x, int y ,char marca)throws RemoteException{
        int turnomarcacion =0;
        if(marca=='x'){
            turnomarcacion = 1;
        }else{
            turnomarcacion=2;
        }
        //verificador de turno
        if(turnomarcacion == turno){
            //if was empty marks
            if(this.tabla[x][y]== '-'){
                this.tabla[x][y]= marca;
            //verify a winner
                if (ganador(marca)){
                    return "ganador:"+marca;
                }else{
                    //verify a  tie
                    if(empate()){
                    return "reusltado:Empate";
                    }else{
                    //pass througth turn
                        turno = (turno%2)+1;
                        return "continuar:Juego";
                    }
                }
            }else{
            return "error posicion ocuapda";
            }
        }else{
        return"error turno incorrecto";
        }
    }
    
    public Boolean ganador(char marca) throws RemoteException{
        Boolean bganador=false;
        //verify if exists a winner by rows
        if(this.tabla[0][0]== marca && this.tabla[0][1]== marca && this.tabla[0][2]== marca){
            bganador=true;
        }
        if(this.tabla[1][0]== marca && this.tabla[1][1]== marca && this.tabla[1][2]== marca){
            bganador=true;
        }
        if(this.tabla[2][0]== marca && this.tabla[2][1]== marca && this.tabla[2][2]== marca){
            bganador=true;
        }
        //verify if exists a winner by columns
        if(this.tabla[0][0]== marca && this.tabla[1][0]== marca && this.tabla[2][0]== marca){
            bganador=true;
        }
         if(this.tabla[0][1]== marca && this.tabla[1][1]== marca && this.tabla[2][1]== marca){
            bganador=true;
        }
          if(this.tabla[0][2]== marca && this.tabla[1][2]== marca && this.tabla[2][2]== marca){
            bganador=true;
        }
        //verify a winner by diagonals
         if(this.tabla[0][0]== marca && this.tabla[1][1]== marca && this.tabla[2][2]== marca){
            bganador=true;
        }
          if(this.tabla[2][0]== marca && this.tabla[1][1]== marca && this.tabla[0][2]== marca){
            bganador=true;
        }
         return bganador;
    }
    
    public Boolean empate() throws RemoteException{
        Boolean bempate = true;
        for (int i=1;i<3;i++){
            for(int j=1;j<3;j++){
                if(this.tabla[i][j]=='-'){
                    bempate= false;
                }
            }
        }
        return bempate;
    }

    public char [][]getTabla(){
        return this.tabla;
    }
}



