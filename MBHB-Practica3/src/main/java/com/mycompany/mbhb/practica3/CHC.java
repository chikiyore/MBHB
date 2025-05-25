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

public class CHC {

    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;
    private final int TAMANO_POBLACION = 100;
    private final int MAX_GEN = 500;
    private List<Integer> historialMejoresCostesGeneracional = new ArrayList<>();
    private List<Integer> historialPeoresCostes = new ArrayList<>();
    private List<Integer> historialMejoresCostesGlobal = new ArrayList<>();
    private List<Double> historialDistanciasMedias = new ArrayList<>();

    public CHC(int[][] D, int[][] F, int n, long seed) {
        this.D = D;
        this.F = F;
        this.n = n;
        this.random = new Random(seed);
    }

    public int[] ejecutarCHC() {
        List<int[]> poblacion = inicializarPoblacion();
        mejorCosto = Integer.MAX_VALUE;
        mejorSolucion = null;
        int d = n / 4;
        int generacionesSinMejora = 0;
        int reinicios=0;

        while(reinicios < 10) {
            List<int[]> nuevosIndividuos = new ArrayList<>();

            // Comparo parejas
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                for (int j = i + 1; j < TAMANO_POBLACION; j++) {
                    int[] padre1 = poblacion.get(i);
                    int[] padre2 = poblacion.get(j);
                    int distancia = distanciaHamming(padre1, padre2);

                    if (distancia > d) {
                        int[][] hijos = cruceOX(padre1, padre2);
                        for (int[] hijo : hijos) {
                            nuevosIndividuos.add(hijo);
                        }
                    }
                }
            }

            // Evaluo nuevos y selecciono TAMANO_POBLACION mejores
            List<int[]> union = new ArrayList<>(poblacion);
            union.addAll(nuevosIndividuos);

            union.sort(Comparator.comparingInt(this::calcularCosto));
            if (!nuevosIndividuos.isEmpty()) {
                poblacion = union.subList(0, TAMANO_POBLACION);
                generacionesSinMejora = 0;
            } else {
                d--;
                generacionesSinMejora++;
            }

            poblacion.sort(Comparator.comparingInt(this::calcularCosto));

            int[] mejorActual = poblacion.get(0);
            int[] peorActual = poblacion.get(TAMANO_POBLACION - 1);
            int costoActual = calcularCosto(mejorActual);
            int peorCostoActual = calcularCosto(peorActual);
            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorSolucion = mejorActual.clone();
            }

            historialMejoresCostesGeneracional.add(costoActual);
            historialMejoresCostesGlobal.add(mejorCosto);
            historialPeoresCostes.add(peorCostoActual);

            double sumDist = 0;
            int pairs = 0;
            for (int i = 0; i < TAMANO_POBLACION; i++) {
                for (int j = i + 1; j < TAMANO_POBLACION; j++) {
                    sumDist += distanciaHamming(poblacion.get(i), poblacion.get(j));
                    pairs++;
                }
            }
            double mediaDist = (pairs > 0 ? sumDist / pairs : 0);
            historialDistanciasMedias.add(mediaDist);
            // Reinicio si d llega a 0
            if (d <= 0) {
                reiniciarPoblacion(mejorSolucion, poblacion);
                d = n / 4;
                generacionesSinMejora = 0;
                reinicios++;
            }

        }

        return mejorSolucion;
    }

    private List<int[]> inicializarPoblacion() {
        List<int[]> poblacion = new ArrayList<>();
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int[] perm = new int[n];
            for (int j = 0; j < n; j++) {
                perm[j] = j;
            }
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

    private void reiniciarPoblacion(int[] elite, List<int[]> poblacion) {
        poblacion.clear();
        poblacion.add(elite.clone());

        for (int i = 1; i < TAMANO_POBLACION; i++) {
            int[] perm = new int[n];
            for (int j = 0; j < n; j++) {
                perm[j] = j;
            }
            for (int j = n - 1; j > 0; j--) {
                int k = random.nextInt(j + 1);
                int temp = perm[j];
                perm[j] = perm[k];
                perm[k] = temp;
            }
            poblacion.add(perm);
        }

    }

    private int[][] cruceOX(int[] p1, int[] p2) {
        int[] h1 = new int[n];
        int[] h2 = new int[n];
        Arrays.fill(h1, -1);
        Arrays.fill(h2, -1);

        int i = random.nextInt(n);
        int j = random.nextInt(n);
        while (j == i) {
            j = random.nextInt(n);
        }

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

    private int distanciaHamming(int[] a, int[] b) {
        int distancia = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                distancia++;
            }
        }
        return distancia;
    }

    public int getMejorCosto() {
        return mejorCosto;
    }

    public int getEvaluacionesTotales() {
        return evaluacionesTotales;
    }

    public List<Integer> getHistorialMejoresCostesGlobal() {
        return historialMejoresCostesGlobal;
    }

    public List<Integer> getHistorialMejoresCostesGeneracional() {
        return historialMejoresCostesGeneracional;
    }

    public List<Integer> getHistorialPeoresCostes() {
        return historialPeoresCostes;
    }
    public List<Double> getHistorialDistanciasMedias(){
        return historialDistanciasMedias;
    }
}
