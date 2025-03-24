/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jarro
 */
public class EnfriamientoSimulado {
    //deberia dar en tai25 cercano al optimo
    //rozando el optmo por debajo de 350
    private int n;
    private int[][] F; // Matriz de flujos
    private int[][] D; // Matriz de distancias
    private int[] mejorSolucion;
    private int mejorCosto;
    private Random random;
    private double TIni;
    private int evaluaciones;
    private int vi,vj;

    public EnfriamientoSimulado(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.mejorSolucion = generarSolucionAleatoria();
        this.mejorCosto = calcularCosto(mejorSolucion);
        this.TIni=(0.3/(-Math.log(0.3))*mejorCosto);
        this.evaluaciones=0;
    }

    public int[] resolverEnfriamientoSimulado() {
       int iteraciones = 50 * n;
        
       double T = TIni;
        int[] solucionActual = mejorSolucion.clone();
        int costoActual = mejorCosto;
        
        for (int k = 1; k <= iteraciones; k++) { // 50 * n iteraciones de enfriamiento
            int aceptados = 0;
            for (int i = 0; i < 40 && aceptados < 5; i++) { // Máximo 40 intentos por temperatura o 5 aceptados
                int[] vecino = generarVecino(solucionActual);
                int costoVecino =costoActual + calcularDiferenciaCosto(solucionActual, vi, vj);
                int delta = costoVecino - costoActual;
                
                if (delta < 0 || random.nextDouble() < Math.exp(-delta / T)) {
                    solucionActual = vecino;
                    costoActual = costoVecino;
                    aceptados++;
                    if (costoActual < mejorCosto) {
                        mejorCosto = costoActual;
                        mejorSolucion = solucionActual.clone();
                    }
                }
                
            }
            T = TIni / (1 + k); // Miguel dijo k=1 inicialmente
        }
        return mejorSolucion;
    }
private int calcularDiferenciaCosto(int[] solucion, int i, int j) {
        int delta = 0;
        for (int k = 0; k < n; k++) {
            if (k != i && k != j) {
                delta += (D[i][k] * (F[solucion[j]][solucion[k]] - F[solucion[i]][solucion[k]]))
                        + (D[j][k] * (F[solucion[i]][solucion[k]] - F[solucion[j]][solucion[k]]))
                        + (D[k][i] * (F[solucion[k]][solucion[j]] - F[solucion[k]][solucion[i]]))
                        + (D[k][j] * (F[solucion[k]][solucion[i]] - F[solucion[k]][solucion[j]]));
            }
        }
        evaluaciones++;
        return delta;
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

    private int[] generarVecino(int[] solucion) {
        int[] vecino = solucion.clone();
        int i = random.nextInt(n);
        int j = random.nextInt(n);
        while (i == j) j = random.nextInt(n);
        vi=i;
        vj=j;
        int temp = vecino[i];
        vecino[i] = vecino[j];
        vecino[j] = temp;
        return vecino;
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
    public int devolverEvaluaiones() {
        return evaluaciones;
    }
}
