/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica2;

import java.util.*;

public class GRASP {
    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;

    public GRASP(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.evaluacionesTotales = 0;
    }

    public int[] ejecutarGRASP() {
        int l = Math.max(1, (int) (0.1 * n)); 

        mejorCosto = Integer.MAX_VALUE;

        for (int iter = 0; iter < 5; iter++) {
            int[] solucionInicial = construirSolucionGreedyProbabilistica(l);
            BusquedaLocalPrimerMejor bl = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(),solucionInicial);

            int[] solucionOptimizada = bl.resolverBusquedaLocalPrimerMejor();
            int costo = bl.devolverCosto();
            evaluacionesTotales += bl.devolverEvaluaciones();

            if (costo < mejorCosto) {
                mejorCosto = costo;
                mejorSolucion = solucionOptimizada.clone();
            }
        }

        return mejorSolucion;
    }

    private int[] construirSolucionGreedyProbabilistica(int l) {
    
    int[] flujoTotal = new int[n];
    int[] distanciaTotal = new int[n];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            flujoTotal[i] += F[i][j];
            distanciaTotal[i] += D[i][j];
        }
    }

    
    List<Integer> unidades = new ArrayList<>();
    List<Integer> localizaciones = new ArrayList<>();
    for (int i = 0; i < n; i++) {
        unidades.add(i);
        localizaciones.add(i);
    }

    
    unidades.sort((a, b) -> Integer.compare(flujoTotal[b], flujoTotal[a]));

    
    localizaciones.sort(Comparator.comparingInt(i -> distanciaTotal[i]));

    
    int[] solucion = new int[n];
    Arrays.fill(solucion, -1); 

    for (int i = 0; i < n; i++) {
        // Seleccionar aleatoriamente una unidad del top-l
        int limiteU = Math.min(l, unidades.size());
        int unidad = unidades.get(random.nextInt(limiteU));

        // Seleccionar aleatoriamente una localización del top-l
        int limiteL = Math.min(l, localizaciones.size());
        int localizacion = localizaciones.get(random.nextInt(limiteL));

        // Asignar la unidad a la localización
        solucion[localizacion] = unidad;

        // Eliminar unidad y localización ya asignadas
        unidades.remove((Integer) unidad);
        localizaciones.remove((Integer) localizacion);
    }

    return solucion;
}

    

    public int getMejorCosto() {
        return mejorCosto;
    }

    public int getEvaluacionesTotales() {
        return evaluacionesTotales;
    }
}
