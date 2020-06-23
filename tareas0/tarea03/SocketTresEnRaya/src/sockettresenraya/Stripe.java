/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockettresenraya;
/**
 *
 * @author 
 */
public class Stripe {
    private char[][] tabla;
    private int turno;

    Stripe (){
        turno = 1;
        tabla = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = '-';
            }
        }
    }

    public String marcaciones()
    {
        String aux="";
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                aux=aux+tabla[i][j];
                if (j!=2)
                {
                    aux=aux+",";
                }
            }
            if (i!=2)
                {
                    aux=aux+":";
                }
        }
        return "marcas:"+aux;    
    }
    public void Reiniciar() {
        turno = 1;
        tabla = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = '-';
            }
        }
    }

    public String marcar(int x, int y, char marca) {
        int turnomarcacion = 0;
        if (marca == 'x') {
            turnomarcacion = 1;
        } else {
            turnomarcacion = 2;
        }
        // verificar si el turno corresponde
        if (turnomarcacion == turno) {
            // si esta vacio marcar
            if (tabla[x][y] == '-') {
                tabla[x][y] = marca;
                // verificar si hy ganador
                if (ganador(marca)) {
                    return "ganador:" + marca;
                } else {
                    // verificar si hay empate
                    if (empate()) {
                        return "resultado:empate";
                    } else {
                        // pasar el turno
                        turno = (turno % 2) + 1;
                        return "continuar:juego";
                    }
                }
            } else {
                return "error:posicion ocupada";
            }
        } else {
            return "error:turno incorrecto";
        }
    }

    private Boolean ganador(char marca) {
        Boolean bganador = false;
        // verificar si hay ganador por filas
        if (tabla[0][0] == marca && tabla[0][1] == marca && tabla[0][2] == marca) {
            bganador = true;
        }
        if (tabla[1][0] == marca && tabla[1][1] == marca && tabla[1][2] == marca) {
            bganador = true;
        }
        if (tabla[2][0] == marca && tabla[2][1] == marca && tabla[2][2] == marca) {
            bganador = true;
        }
        // verificar sihay ganadorpor columnas
        if (tabla[0][0] == marca && tabla[1][0] == marca && tabla[2][0] == marca) {
            bganador = true;
        }
        if (tabla[0][1] == marca && tabla[1][1] == marca && tabla[2][1] == marca) {
            bganador = true;
        }
        if (tabla[0][2] == marca && tabla[1][2] == marca && tabla[2][2] == marca) {
            bganador = true;
        }
        // verificar diagonales
        if (tabla[0][0] == marca && tabla[1][1] == marca && tabla[2][2] == marca) {
            bganador = true;
        }
        if (tabla[2][0] == marca && tabla[1][1] == marca && tabla[0][2] == marca) {
            bganador = true;
        }
        return bganador;
    }

    private Boolean empate() {
        Boolean bempate = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j] == '-') {
                    bempate = false;
                }
            }
        }
        return bempate;
    }
}
    
