/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica1;

import java.util.*;

public class BusquedaAleatoria {

    private int n;
    private int[][] F; // Matriz de flujos
    private int[][] D; // Matriz de distancias
    private int[] mejorSolucion; // Mejor solución encontrada
    private int mejorCosto; // Coste de la mejor solución
    private Random random;
    private int evaluaciones;

    public BusquedaAleatoria(int[][] D, int[][] F, int n, long semilla) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(semilla);
        this.mejorSolucion = new int[n];
        this.mejorCosto = Integer.MAX_VALUE;
        this.evaluaciones=0;
    }

    public int[] resolverBusquedaAleatoria() {
        int iteraciones = 1000 * n;
        int[] solucionActual = generarSolucionAleatoria();
        System.arraycopy(solucionActual, 0, mejorSolucion, 0, n);
        mejorCosto = calcularCosto(mejorSolucion);
        for (int i = 0; i < iteraciones; i++) {
            solucionActual = generarSolucionAleatoria();
            int costoActual = calcularCosto(solucionActual);

            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                System.arraycopy(solucionActual, 0, mejorSolucion, 0, n);
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

    private int calcularCosto(int[] solucion) {
        int coste = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coste += D[i][j] * F[solucion[i]][solucion[j]];
            }
        }
        evaluaciones++;
        return coste;
    }

    public int devolverCosto() {
        return mejorCosto;
    }
    public int devolverEvaluaciones() {
        return evaluaciones;
    }

}
