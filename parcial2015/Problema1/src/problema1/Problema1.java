/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luisa
 */
public class Problema1 {
    ArrayList<Integer> numeros;
    public Problema1(){
        numeros = new ArrayList<>();
    }
    public static void main(String[] args) throws IOException {
        Problema1 p = new Problema1();
        p.leerArchivo();
        p.encontrarParejasCercana();
    }
    public void leerArchivo() throws IOException{
        String leer;
        BufferedReader scanf = null;
        File f=null;
         URL url = getClass().getClassLoader().getResource("recursos\\archivo.txt") ;
        try {
             f = new File(url.toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(Problema1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            scanf = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Problema1.class.getName()).log(Level.SEVERE, null, ex);
        }
        leer=scanf.readLine();
        String []part = leer.split(",");
        for(String p: part){
            int num = Integer.parseInt(p);
            numeros.add(num);
        }
    }
    public void encontrarParejasCercana(){
        Collections.sort(numeros);
        int menor,a,b;
        a = numeros.get(0);
        b = numeros.get(1);
        menor = numeros.get(1) - numeros.get(0);
        for(int i = 1; i < numeros.size()-1; i++){
           if((numeros.get(i+1) - numeros.get(i)) < menor){
               menor = (numeros.get(i+1) - numeros.get(i));
               a = numeros.get(i);
               b = numeros.get(i+1);
           }
        }
        System.out.println(""+a+" , "+b);
    }
    
}
