/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica2;

import java.util.*;

public class ILS {
    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;

    public ILS(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.evaluacionesTotales = 0;
    }

    public int[] ejecutarILS() {
        
        int[] solucionInicial = generarSolucionAleatoria();
        
        
        BusquedaLocalPrimerMejor bl = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(), solucionInicial);
        int[] solucionOptimizada = bl.resolverBusquedaLocalPrimerMejor();
        int costoOptimizado = bl.devolverCosto();
        evaluacionesTotales += bl.devolverEvaluaciones();

        
        if (costoOptimizado < calcularCosto(solucionInicial)) {
            mejorSolucion = solucionOptimizada.clone();
            mejorCosto = costoOptimizado;
        } else {
            mejorSolucion = solucionInicial.clone();
            mejorCosto = calcularCosto(solucionInicial);
        }

        
        for (int iter = 0; iter < 9; iter++) {
            
            int[] solucionMutada = mutarSolucion(mejorSolucion);
            
            
            bl = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(), solucionMutada);
            solucionOptimizada = bl.resolverBusquedaLocalPrimerMejor();
            costoOptimizado = bl.devolverCosto();
            evaluacionesTotales += bl.devolverEvaluaciones();

            
            if (costoOptimizado < mejorCosto) {
                mejorCosto = costoOptimizado;
                mejorSolucion = solucionOptimizada.clone();
            }
        }

        return mejorSolucion;
    }

    
    private int[] generarSolucionAleatoria() {
        int[] solucion = new int[n];
        List<Integer> unidades = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            unidades.add(i);
        }
        Collections.shuffle(unidades, random); 
        for (int i = 0; i < n; i++) {
            solucion[i] = unidades.get(i); 
        }
        return solucion;
    }

    
    private int[] mutarSolucion(int[] solucion) {
    int s = n / 4; 
    int pos1 = random.nextInt(n); 

    
    List<Integer> sublista = new ArrayList<>();
    for (int i = 0; i < s; i++) {
        sublista.add(solucion[(pos1 + i) % n]);
    }

    
    Collections.shuffle(sublista, random);

    // Asignamos la nueva mutacion 
    for (int i = 0; i < s; i++) {
        solucion[(pos1 + i) % n] = sublista.get(i);
    }

    return solucion;
}


    
    private int calcularCosto(int[] solucion) {
        int costo = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costo += D[i][j] * F[solucion[i]][solucion[j]];
            }
        }
        evaluacionesTotales++;
        return costo;
    }

    public int getMejorCosto() {
        return mejorCosto;
    }

    public int getEvaluacionesTotales() {
        return evaluacionesTotales;
    }
}

