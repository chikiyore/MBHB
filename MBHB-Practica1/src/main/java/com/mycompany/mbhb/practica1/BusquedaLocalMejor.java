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
public class BusquedaLocalMejor {
    private int n;
    private int[][] F; // Matriz de flujos
    private int[][] D; // Matriz de distancias
    private int[] mejorSolucion;
    private int mejorCosto;
    private Random random;

    public BusquedaLocalMejor(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.mejorSolucion = generarSolucionAleatoria();
        this.mejorCosto = calcularCosto(mejorSolucion);
    }

    public int[] resolverBusquedaLocalMejor() {
        boolean mejora = true;

        while (mejora) {
            mejora = false;
            int[] mejorVecino = mejorSolucion.clone();
            int mejorCostoVecino = mejorCosto;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int[] vecino = generarVecino(mejorSolucion, i, j);
                    int costoVecino = calcularCosto(vecino);

                    if (costoVecino < mejorCostoVecino) {
                        mejorVecino = vecino.clone();
                        mejorCostoVecino = costoVecino;
                        mejora = true;
                    }
                }
            }

            if (mejora) {
                mejorSolucion = mejorVecino.clone();
                mejorCosto = mejorCostoVecino;
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
        return coste;
    }

    public int devolverCosto() {
        return mejorCosto;
    }
}
