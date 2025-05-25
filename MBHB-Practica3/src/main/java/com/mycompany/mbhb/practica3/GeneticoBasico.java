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

public class GeneticoBasico {

    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;
    private final int TAMANO_POBLACION = 100;
    private final int MAX_GEN = 1000;
    private final double PROBABILIDAD_CRUCE = 0.9;
    private final double MUTACION = 0.05;
    private final double ELITISMO = 0.10;
    private final double TORNEO_PORCENTAJE = 0.10;
    private List<Integer> historialMejoresCostes = new ArrayList<>();

    public GeneticoBasico(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
    }

    public int[] ejecutarAG() {
        int elitismo = (int) (TAMANO_POBLACION * ELITISMO);
        List<int[]> poblacion = inicializarPoblacion();
        mejorSolucion = null;
        mejorCosto = Integer.MAX_VALUE;

        for (int gen = 0; gen < MAX_GEN; gen++) {
            List<int[]> nuevaPoblacion = new ArrayList<>();

            // ELITISMO: guardo los mejores
            poblacion.sort(Comparator.comparingInt(this::calcularCosto));
            for (int i = 0; i < elitismo; i++) {
                nuevaPoblacion.add(poblacion.get(i).clone());
            }

            while (nuevaPoblacion.size() < TAMANO_POBLACION) {
                int[] padre1 = torneo(poblacion);
                int[] padre2 = torneo(poblacion);

                int[] hijo1, hijo2;

                if (random.nextDouble() < PROBABILIDAD_CRUCE) {
                    int[][] hijos = cruceOX(padre1, padre2);
                    hijo1 = hijos[0];
                    hijo2 = hijos[1];
                } else {
                    hijo1 = mutar(padre1);
                    hijo2 = mutar(padre2);
                }

                nuevaPoblacion.add(hijo1);
                if (nuevaPoblacion.size() < TAMANO_POBLACION) {
                    nuevaPoblacion.add(hijo2);
                }
            }

            poblacion = nuevaPoblacion;

            for (int[] individuo : poblacion) {
                int costo = calcularCosto(individuo);
                if (costo < mejorCosto) {
                    mejorCosto = costo;
                    mejorSolucion = individuo.clone();
                    }
            }
            historialMejoresCostes.add(mejorCosto);
        }

        return mejorSolucion;
    }

    private List<int[]> inicializarPoblacion() {
        List<int[]> poblacion = new ArrayList<>();
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int[] individuo = new int[n];
            for (int j = 0; j < n; j++) {
                individuo[j] = j;
            }
            for (int j = n - 1; j > 0; j--) {
                int k = random.nextInt(j + 1);
                int temp = individuo[j];
                individuo[j] = individuo[k];
                individuo[k] = temp;
            }
            poblacion.add(individuo);
        }
        return poblacion;
    }

    private int[] torneo(List<int[]> poblacion) {
        int k = Math.max(1, (int) (TAMANO_POBLACION * TORNEO_PORCENTAJE));
        int[] mejor = null;
        int mejorCostoLocal = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            int[] candidato = poblacion.get(random.nextInt(TAMANO_POBLACION));
            int costo = calcularCosto(candidato);
            if (costo < mejorCostoLocal) {
                mejorCostoLocal = costo;
                mejor = candidato;
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
        for (int j : array) {
            if (j == val) {
                return true;
            }
        }
        return false;
    }

    private int[] mutar(int[] individuo) {
        int[] mutado = individuo.clone();
        int size = Math.max(1, (int) (n * MUTACION));
        int start = random.nextInt(n);

        List<Integer> segmento = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            segmento.add(mutado[(start + i) % n]);
        }

        Collections.shuffle(segmento);

        for (int i = 0; i < size; i++) {
            mutado[(start + i) % n] = segmento.get(i);
        }

        return mutado;
    }

    private int calcularCosto(int[] solucion) {
        int coste = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coste += D[i][j] * F[solucion[i]][solucion[j]];
            }
        }
        evaluacionesTotales++;
        return coste;
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
