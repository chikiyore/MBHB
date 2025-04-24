/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica2;

import java.util.*;

public class VNS {
    private int n;
    private int[][] F;
    private int[][] D;
    private Random random;
    private int mejorCosto;
    private int[] mejorSolucion;
     private int evaluacionesTotales;

    public VNS(int[][] D, int[][] F, int n, long seed) {
        this.D = D;
        this.F = F;
        this.n = n;
        this.random = new Random(seed);
        this.evaluacionesTotales = 0;
    }

    public int[] ejecutarVNS() {
        int bl = 0;
        int kmax = 5;

        // Solución aleatoria inicial
        int[] solucionActual = generarSolucionAleatoria();
        BusquedaLocalPrimerMejor blpm = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(), solucionActual);
        solucionActual = blpm.resolverBusquedaLocalPrimerMejor();
        mejorCosto = blpm.devolverCosto();
        mejorSolucion = solucionActual.clone();
        evaluacionesTotales += blpm.devolverEvaluaciones();

        while (bl < 10) {
            int k = 1;

            while (k <= kmax) {
                int s = n / (9 - k); 
                int[] vecino = mutarSublista(solucionActual, s);
                BusquedaLocalPrimerMejor blVecino = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(), vecino);
                int[] solucionVecina = blVecino.resolverBusquedaLocalPrimerMejor();
                bl++;
                int costoVecino = blVecino.devolverCosto();
                evaluacionesTotales += blVecino.devolverEvaluaciones();

                if (costoVecino < mejorCosto) {
                    solucionActual = solucionVecina.clone();
                    mejorCosto = costoVecino;
                    mejorSolucion = solucionVecina.clone();
                    k = 1;
                } else {
                    k++;
                }

                
                if (bl >= 10) break; // salir si se alcanza blmax
            }
        }

        return mejorSolucion;
    }

    private int[] generarSolucionAleatoria() {
        List<Integer> permutacion = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            permutacion.add(i);
        }
        Collections.shuffle(permutacion, random);

        int[] solucion = new int[n];
        for (int i = 0; i < n; i++) {
            solucion[i] = permutacion.get(i);
        }
        return solucion;
    }

    private int[] mutarSublista(int[] solucion, int s) {
        int[] mutada = solucion.clone();
        int inicio = random.nextInt(n);
        List<Integer> sublista = new ArrayList<>();

        for (int i = 0; i < s; i++) {
            int pos = (inicio + i) % n;
            sublista.add(mutada[pos]);
        }

        Collections.shuffle(sublista, random);

        for (int i = 0; i < s; i++) {
            int pos = (inicio + i) % n;
            mutada[pos] = sublista.get(i);
        }

        return mutada;
    }

    public int getMejorCosto() {
        return mejorCosto;
    }
    public int getEvaluacionesTotales() {
        return evaluacionesTotales;
    }
}

