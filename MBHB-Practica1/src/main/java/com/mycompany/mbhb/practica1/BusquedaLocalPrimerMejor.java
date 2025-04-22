/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jarro
 */
public class BusquedaLocalPrimerMejor {

    private int n;
    private int[][] F; // Matriz de flujos
    private int[][] D; // Matriz de distancias
    private int[] mejorSolucion;
    private int mejorCosto;
    private Random random;
    private int evaluaciones;

    public BusquedaLocalPrimerMejor(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.mejorSolucion = generarSolucionInicial();
        this.mejorCosto = calcularCosto(mejorSolucion);
        this.evaluaciones = 0;

    }

    public int[] resolverBusquedaLocalPrimerMejor() {
        boolean mejora = true;

        while (mejora) {
            int it = 0;
            mejora = false;
            int[] mejorVecino = mejorSolucion.clone();
            int mejorCostoVecino = mejorCosto;
            List<int[]> candidatos = generarListaVecinos();

            
            while (it < candidatos.size() && !mejora) {
                int i = candidatos.get(it)[0], j = candidatos.get(it)[1];
                int[] vecino = generarVecino(mejorSolucion, i, j);
                int costoVecino = mejorCosto + calcularDiferenciaCosto(mejorSolucion, i, j);

                if (costoVecino < mejorCostoVecino) {
                    mejorVecino = vecino.clone();
                    mejorCostoVecino = costoVecino;
                    mejora = true; // Si mejora salimos del buvle
                    break;
                }
                it++;
            }

            // Si encontramos una mejora, actualizamos la solucion
            if (mejora) {
                mejorSolucion = mejorVecino.clone();
                mejorCosto = mejorCostoVecino;
            }
        }
        return mejorSolucion;
    }

    private List<int[]> generarListaVecinos() {
        List<int[]> candidatos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                candidatos.add(new int[]{i, j});
            }
        }
        Collections.shuffle(candidatos, random);
        return candidatos;
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

    private int[] generarSolucionInicial() {
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

    private int[] generarVecino(int[] solucion, int i, int j) {
        int[] vecino = solucion.clone();
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

    public int devolverEvaluaciones() {
        return evaluaciones;
    }
}
