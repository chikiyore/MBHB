package com.mycompany.mbhb.practica3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jarro
 */

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Multimodal {

    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;

    private final int TAMANO_POBLACION = 100;
    private final int MAX_GEN = 500;
    private final double PROBABILIDAD_CRUCE = 0.9;
    private final double MUTACION = 0.05;
    private final double TORNEO_PORCENTAJE = 0.10;
    
    private int RADIO_NICHO;
    private int CAPACIDAD_NICHO;

    private List<Integer> historialGlobal = new ArrayList<>();
    private List<Integer> historialMejorGen = new ArrayList<>();
    private List<Integer> historialPeorGen = new ArrayList<>();

    public Multimodal(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.RADIO_NICHO = Math.max(1, n / 5);
        this.CAPACIDAD_NICHO = 2;
    }

    public int[] ejecutarMultimodal() {
        List<int[]> poblacion = inicializarPoblacion();
        mejorSolucion = null;
        mejorCosto = Integer.MAX_VALUE;

        for (int gen = 0; gen < MAX_GEN; gen++) {
            //Evaluamos costes
            List<Integer> costesGen = new ArrayList<>(TAMANO_POBLACION);
            for (int[] ind : poblacion) {
                costesGen.add(calcularCosto(ind));
            }

            int peorOriginal = Collections.max(costesGen);

            //Aplicamos Clearing a los costes
            aplicarClearing(poblacion, costesGen);

            //Ordenamos índices por coste tras clearing
            List<Integer> orden = IntStream.range(0, poblacion.size())
                .boxed()
                .sorted(Comparator.comparingInt(costesGen::get))
                .collect(Collectors.toList());

            int idxMejor = orden.get(0);
            int idxPeor  = orden.get(orden.size() - 1);
            int costeMejorGen = costesGen.get(idxMejor);
            int costePeorGen  = peorOriginal;  // ahora usamos el peor real
            int[] mejorActual = poblacion.get(idxMejor);

            
            if (costeMejorGen < mejorCosto) {
                mejorCosto = costeMejorGen;
                mejorSolucion = mejorActual.clone();
            }

           
            historialGlobal.add(mejorCosto);
            historialMejorGen.add(costeMejorGen);
            historialPeorGen.add(costePeorGen);

            //Creamos la nueva poblacion
            List<int[]> nuevaPoblacion = new ArrayList<>();
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
        }

        return mejorSolucion;
    }

    private List<int[]> inicializarPoblacion() {
        List<int[]> pobl = new ArrayList<>();
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            int[] ind = IntStream.range(0, n).toArray();
            for (int j = n - 1; j > 0; j--) {
                int k = random.nextInt(j + 1);
                int tmp = ind[j]; ind[j] = ind[k]; ind[k] = tmp;
            }
            pobl.add(ind);
        }
        return pobl;
    }

    private int[] torneo(List<int[]> pobl) {
        int k = Math.max(1, (int) (TAMANO_POBLACION * TORNEO_PORCENTAJE));
        int[] mejor = null;
        int mejorC = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            int[] cand = pobl.get(random.nextInt(pobl.size()));
            int c = calcularCosto(cand);
            if (c < mejorC) { mejorC = c; mejor = cand; }
        }
        return mejor.clone();
    }

    private int[][] cruceOX(int[] p1, int[] p2) {
        int[] h1 = new int[n], h2 = new int[n];
        Arrays.fill(h1, -1); Arrays.fill(h2, -1);
        int a = random.nextInt(n), b;
        do { b = random.nextInt(n); } while (b == a);
        int start = Math.min(a, b), end = Math.max(a, b);
        for (int i = start; i <= end; i++) {
            h1[i] = p1[i]; h2[i] = p2[i];
        }
        rellenarOX(h1, p2, end + 1);
        rellenarOX(h2, p1, end + 1);
        return new int[][]{h1, h2};
    }

    private void rellenarOX(int[] h, int[] p, int start) {
        int pos = start % n;
        for (int i = 0; i < n; i++) {
            int gene = p[(start + i) % n];
            if (!contiene(h, gene)) {
                h[pos] = gene;
                pos = (pos + 1) % n;
            }
        }
    }

    private boolean contiene(int[] arr, int v) {
        for (int x : arr) if (x == v) return true;
        return false;
    }

    private int[] mutar(int[] ind) {
        int[] m = ind.clone();
        int size = Math.max(1, (int) (n * MUTACION));
        int start = random.nextInt(n);
        List<Integer> seg = new ArrayList<>();
        for (int i = 0; i < size; i++) seg.add(m[(start + i) % n]);
        Collections.shuffle(seg);
        for (int i = 0; i < size; i++) m[(start + i) % n] = seg.get(i);
        return m;
    }

    private int calcularCosto(int[] sol) {
        int cost = 0;
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
            cost += D[i][j] * F[sol[i]][sol[j]];
        }
        evaluacionesTotales++;
        return cost;
    }

    private void aplicarClearing(List<int[]> pobl, List<Integer> costes) {
        int size = pobl.size();
        boolean[] marcado = new boolean[size];
        List<Integer> orden = IntStream.range(0, size)
            .boxed()
            .sorted(Comparator.comparingInt(costes::get))
            .collect(Collectors.toList());
        for (int idx : orden) {
            if (marcado[idx]) continue;
            int cont = 1;
            for (int j : orden) {
                if (j == idx) continue;
                if (!marcado[j] && distanciaHamming(pobl.get(idx), pobl.get(j)) < RADIO_NICHO) {
                    if (cont < CAPACIDAD_NICHO) cont++;
                    else marcado[j] = true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (marcado[i]) costes.set(i, Integer.MAX_VALUE);
        }
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

    
    public int getMejorCosto() { return mejorCosto; }
    public int getEvaluacionesTotales() { return evaluacionesTotales; }
    public List<Integer> getHistorialGlobal() { return historialGlobal; }
    public List<Integer> getHistorialMejorGen() { return historialMejorGen; }
    public List<Integer> getHistorialPeorGen() { return historialPeorGen; }
}
