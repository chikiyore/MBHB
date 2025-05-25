/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica3;

import java.util.*;

public class ILS {

    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;
    private List<Integer> historialMejoresCostes = new ArrayList<>();

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

        mejorSolucion = solucionOptimizada.clone();
        mejorCosto = costoOptimizado;

        historialMejoresCostes.add(mejorCosto);

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
            historialMejoresCostes.add(mejorCosto);

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

        int[] nuevaSolucion = solucion.clone();

        List<Integer> sublista = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            sublista.add(nuevaSolucion[(pos1 + i) % n]);
        }

        Collections.shuffle(sublista, random);

        for (int i = 0; i < s; i++) {
            nuevaSolucion[(pos1 + i) % n] = sublista.get(i);
        }

        return nuevaSolucion;
    }

    public int getMejorCosto() {
        return mejorCosto;
    }

    public int getEvaluacionesTotales() {
        return evaluacionesTotales;
    }

    public List<Integer> getHistorialMejoresCostes() {
        return historialMejoresCostes;
    }

}
