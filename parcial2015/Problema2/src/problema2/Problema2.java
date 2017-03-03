/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Sanchez Diaz
 */
public class Problema2 {
       private ArrayList<String> cadenas;
    private ArrayList<Enemigos> enemigos;
    private int numCasos,menor;
    private boolean primeraVez;
    public Problema2(){
        cadenas = new ArrayList<>();
        enemigos = new ArrayList<>();
    }
    public static void main(String[] args) throws IOException {
        Problema2 p = new Problema2();
        p.leerArchivo();
        p.revisarCasos();
    }
    public void leerArchivo() throws IOException{
        String cadena;
        BufferedReader scanf = null;
        File f = null;
        URL url = getClass().getClassLoader().getResource("recursos\\archivo.txt");
        try {
            f = new File(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Problema2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            scanf = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Problema2.class.getName()).log(Level.SEVERE, null, ex);
        }
        while((cadena = scanf.readLine()) != null ){
            cadenas.add(cadena);
        }
        numCasos = Integer.parseInt(cadenas.get(0));
    }
    public void revisarCasos(){
        int j = 1;
        for(int i = 0; i < numCasos; i++){
            String []parts = cadenas.get(j).split(" ");
            int numEne = Integer.parseInt(parts[0]);
            int ancho = Integer.parseInt(parts[1]);
            int alto = Integer.parseInt(parts[2]);
            generarTablero(numEne, ancho, alto, j+1);
            j +=( 1 + alto + numEne);
        }
    }
    public void generarTablero(int numEne, int ancho, int alto,int inicio){
        enemigos.clear();
        primeraVez = true;
        int px = 0,py = 0;
        System.out.println("");
        System.out.println("ENTRADA");
        System.out.println("");
        for(int i = inicio; i < (inicio+numEne); i++){
            String []parts = cadenas.get(i).split(" ");
            Enemigos ene = new Enemigos(parts[0].charAt(0), parts[1]);
            enemigos.add(ene);
            System.out.println(""+ene);
        }
        char matriz[][];
        matriz = new char[alto][ancho];
        int j = inicio+numEne;
        for(int i = j, l = 0; i < j+alto; i++, l++){
            for(int k = 0; k < ancho; k++){
                if(cadenas.get(i).charAt(k) == 'E'){
                    py = l;
                    px = k;
                }
                matriz[l][k] = cadenas.get(i).charAt(k);
            }
        }
        for(int i = 0; i < alto; i++){
            for(int k = 0; k < ancho; k++){
                System.out.print(""+matriz[i][k]);
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("SALIDA");
        System.out.println("");
        buscarCamino(matriz, px, py, 0, ancho, alto);
        System.out.println(""+menor);
    }
    public int tiempoGastadoEnemi(char [][]matriz,int posX,int posY){
        for(Enemigos e: enemigos){
            if(e.getTipo() == matriz[posY][posX]){
                return e.getTiempoConsume();
            }
        }
        return 0;
    }
    public void copiarMatriz(char [][]matriz, char [][]copia, int alto, int ancho){
        for(int i = 0; i < alto; i++){
            for(int k = 0; k < ancho; k++){
                copia[i][k] = matriz[i][k];
            }
            
        }
    }
    public void buscarCamino(char [][]matriz, int posX, int posY, int tiempoGast, int ancho, int alto){
        int tiempo = tiempoGastadoEnemi(matriz, posX, posY);
        char [][]copia;
        copia = new char[alto][ancho];
        copiarMatriz(matriz, copia, alto, ancho);
        copia[posY][posX] = '-';
        if(posX == 0 || posX == (ancho-1) || posY == 0 || posY == (alto-1)){
            if(primeraVez){
                primeraVez = false;
                menor = tiempoGast + tiempo;
                return;
            }
            if((tiempoGast + tiempo) < menor){
                menor = tiempoGast + tiempo;
                return;
            }
            return;
        }
        if(matriz[posY-1][posX] != '-'){
            buscarCamino(copia, posX, posY-1, tiempoGast + tiempo, ancho, alto);
        }
        if(matriz[posY+1][posX] != '-'){
            buscarCamino(copia, posX, posY+1, tiempoGast + tiempo, ancho, alto);
        }
        if(matriz[posY][posX-1] != '-'){
            buscarCamino(copia, posX-1, posY, tiempoGast + tiempo, ancho, alto);
        }
        if(matriz[posY][posX+1] != '-'){
            buscarCamino(copia, posX+1, posY, tiempoGast + tiempo, ancho, alto);
        }
        return;
    }
}
