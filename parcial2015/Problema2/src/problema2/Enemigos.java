/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2;

/**
 *
 * @author Sanchez Diaz
 */
public class Enemigos {
    private char tipo;
    private int tiempoConsume;

    public Enemigos() {
    }
    public Enemigos(char tipo, String tiempoConsume) {
        this.tipo = tipo;
        this.tiempoConsume = Integer.parseInt(tiempoConsume);
    }
    public Enemigos(char tipo, int tiempoConsume) {
        this.tipo = tipo;
        this.tiempoConsume = tiempoConsume;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int getTiempoConsume() {
        return tiempoConsume;
    }

    public void setTiempoConsume(int tiempoConsume) {
        this.tiempoConsume = tiempoConsume;
    }
 
    @Override
    public String toString() {
        return ""+tipo+" "+tiempoConsume;
    }
}
