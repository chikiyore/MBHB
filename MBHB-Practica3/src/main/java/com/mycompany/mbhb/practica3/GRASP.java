/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica3;

import java.util.*;

public class GRASP {
    private int n;
    private int[][] F;
    private int[][] D;
    private int evaluacionesTotales;
    private int mejorCosto;
    private int[] mejorSolucion;
    private Random random;
    private List<Integer> historialMejoresCostes = new ArrayList<>();
    private List<Integer> historialCostesIniciales = new ArrayList<>();
private List<Integer> historialCostesBL = new ArrayList<>();
private List<int[]> solucionesConstruidas = new ArrayList<>();


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
            solucionesConstruidas.add(solucionInicial.clone());
            historialCostesIniciales.add(calcularCosto(solucionInicial));
            BusquedaLocalPrimerMejor bl = new BusquedaLocalPrimerMejor(D, F, n, random.nextLong(),solucionInicial);

            int[] solucionOptimizada = bl.resolverBusquedaLocalPrimerMejor();
            int costo = bl.devolverCosto();
            historialCostesBL.add(costo);
            evaluacionesTotales += bl.devolverEvaluaciones();

            if (costo < mejorCosto) {
                mejorCosto = costo;
                mejorSolucion = solucionOptimizada.clone();
            }
            historialMejoresCostes.add(mejorCosto);
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
        // Elijo aleatoriamente una del top L
        int limiteU = Math.min(l, unidades.size());
        int unidad = unidades.get(random.nextInt(limiteU));

        // Lo mismo pero con localizacion
        int limiteL = Math.min(l, localizaciones.size());
        int localizacion = localizaciones.get(random.nextInt(limiteL));

        // y asigno la unidad a la localización
        solucion[localizacion] = unidad;

        // las elimino de la lista original
        unidades.remove((Integer) unidad);
        localizaciones.remove((Integer) localizacion);
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
    
     public List<Integer> getHistorialCostesIniciales() {
    return historialCostesIniciales;
}
     public List<Integer> getHistorialCostesBL() {
    return historialCostesBL;
}
     private int distanciaHamming(int[] a, int[] b) {
    int distancia = 0;
    for (int i = 0; i < a.length; i++) {
        if (a[i] != b[i]) {
            distancia++;
        }
    }
    return distancia;
}

     public List<Integer> calcularDistanciasHamming() {
    List<Integer> distancias = new ArrayList<>();
    for (int i = 0; i < solucionesConstruidas.size(); i++) {
        for (int j = i + 1; j < solucionesConstruidas.size(); j++) {
            distancias.add(distanciaHamming(solucionesConstruidas.get(i), solucionesConstruidas.get(j)));
        }
    }
    return distancias;
}

     public int contarSolucionesUnicas() {
    Set<String> solucionesUnicas = new HashSet<>();
    for (int[] solucion : solucionesConstruidas) {
        solucionesUnicas.add(Arrays.toString(solucion));
    }
    return solucionesUnicas.size();
}


}
