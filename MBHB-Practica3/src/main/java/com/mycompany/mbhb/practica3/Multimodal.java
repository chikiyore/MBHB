/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica3;

/**
 *
 * @author jarro
 */

import java.util.*;

public class Multimodal {
    private int n;
    private int[][] F, D;
    private Random random;
    private final int TAMANO_POBLACION = 100;
    private final int MAX_GEN = 500;
    private final double CROSSOVER_RATE = 0.9;
    private final double MUTATION_RATE = 0.05;
    private final double RADIO_CLEARING = 5.0;
    private final int ELITISM = 10;

    private int mejorCosto = Integer.MAX_VALUE;
    private int[] mejorSolucion = null;
    private int evaluaciones = 0;

    public Multimodal(int[][] D, int[][] F, int n, long seed) {
        this.D = D;
        this.F = F;
        this.n = n;
        this.random = new Random(seed);
    }

    public int[] ejecutarMultimodal() {
        List<int[]> poblacion = inicializarPoblacion();

        for (int gen = 0; gen < MAX_GEN; gen++) {
            // Evaluar y aplicar clearing
            poblacion = aplicarClearingDominantes(poblacion, RADIO_CLEARING);

            // Elitismo
            List<int[]> nuevaPoblacion = new ArrayList<>();
            poblacion.sort(Comparator.comparingInt(this::calcularCosto));
            for (int i = 0; i < Math.min(ELITISM, poblacion.size()); i++) {
                nuevaPoblacion.add(poblacion.get(i).clone());
            }

            // Rellenar con descendencia
            while (nuevaPoblacion.size() < TAMANO_POBLACION) {
                int[] p1 = torneo(poblacion);
                int[] p2 = torneo(poblacion);

                int[] h1, h2;
                if (random.nextDouble() < CROSSOVER_RATE) {
                    int[][] hijos = cruceOX(p1, p2);
                    h1 = hijos[0];
                    h2 = hijos[1];
                } else {
                    h1 = mutar(p1);
                    h2 = mutar(p2);
                }

                nuevaPoblacion.add(h1);
                if (nuevaPoblacion.size() < TAMANO_POBLACION) nuevaPoblacion.add(h2);
            }

            poblacion = nuevaPoblacion;

            // Actualizar mejor solución global
            for (int[] ind : poblacion) {
                int cost = calcularCosto(ind);
                if (cost < mejorCosto) {
                    mejorCosto = cost;
                    mejorSolucion = ind.clone();
                }
            }
        }

        return mejorSolucion;
    }

    private List<int[]> aplicarClearingDominantes(List<int[]> poblacion, double sigma) {
        List<int[]> dominantes = new ArrayList<>();
        poblacion.sort(Comparator.comparingInt(this::calcularCosto));

        for (int i = 0; i < poblacion.size(); i++) {
            int[] candidato = poblacion.get(i);
            boolean dominado = false;

            for (int[] dom : dominantes) {
                if (distanciaHamming(candidato, dom) <= sigma) {
                    dominado = true;
                    break;
                }
            }

            if (!dominado) {
                dominantes.add(candidato);
            }
        }

        return dominantes;
    }

    private List<int[]> inicializarPoblacion() {
        List<int[]> poblacion = new ArrayList<>();
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int[] perm = new int[n];
            for (int j = 0; j < n; j++) perm[j] = j;
            for (int j = n - 1; j > 0; j--) {
                int k = random.nextInt(j + 1);
                int temp = perm[j];
                perm[j] = perm[k];
                perm[k] = temp;
            }
            poblacion.add(perm);
        }
        return poblacion;
    }

    private int[] torneo(List<int[]> poblacion) {
        int k = Math.max(1, TAMANO_POBLACION / 10);
        int[] mejor = null;
        int mejorCosto = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            int[] candidato = poblacion.get(random.nextInt(poblacion.size()));
            int costo = calcularCosto(candidato);
            if (costo < mejorCosto) {
                mejor = candidato;
                mejorCosto = costo;
            }
        }

        return mejor.clone();
    }

    private int[][] cruceOX(int[] p1, int[] p2) {
        int[] h1 = new int[n];
        int[] h2 = new int[n];
        Arrays.fill(h1, -1);
        Arrays.fill(h2, -1);

        int i = random.nextInt(n);
        int j;
        do {
            j = random.nextInt(n);
        } while (j == i);

        int start = Math.min(i, j);
        int end = Math.max(i, j);

        for (int k = start; k <= end; k++) {
            h1[k] = p1[k];
            h2[k] = p2[k];
        }

        rellenarOX(h1, p2, end + 1);
        rellenarOX(h2, p1, end + 1);

        return new int[][]{h1, h2};
    }

    private void rellenarOX(int[] hijo, int[] padre, int start) {
        int pos = start % n;
        for (int i = 0; i < n; i++) {
            int gene = padre[(start + i) % n];
            if (!contiene(hijo, gene)) {
                hijo[pos] = gene;
                pos = (pos + 1) % n;
            }
        }
    }

    private boolean contiene(int[] array, int val) {
        for (int j : array) if (j == val) return true;
        return false;
    }

    private int[] mutar(int[] ind) {
        int[] mutado = ind.clone();
        int size = Math.max(1, (int) (n * MUTATION_RATE));
        int start = random.nextInt(n);

        List<Integer> segmento = new ArrayList<>();
        for (int i = 0; i < size; i++) segmento.add(mutado[(start + i) % n]);
        Collections.shuffle(segmento);
        for (int i = 0; i < size; i++) mutado[(start + i) % n] = segmento.get(i);

        return mutado;
    }

    private int calcularCosto(int[] sol) {
        int cost = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cost += D[i][j] * F[sol[i]][sol[j]];
        evaluaciones++;
        return cost;
    }

    private int distanciaHamming(int[] a, int[] b) {
        int dist = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) dist++;
        return dist;
    }

    public int getMejorCosto() {
        return mejorCosto;
    }

    public int getEvaluacionesTotales() {
        return evaluaciones;
    }
}

