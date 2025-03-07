package com.mycompany.mbhb.practica1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author jarro
 */
import java.io.*;
import java.util.*;

public class Greedy {

    private int n; // Tamaño del problema
    private int[][] F; // Matriz de flujos
    private int[][] D; // Matriz de distancias
    private int[] solucion; // Solución encontrada

    public Greedy(int[][] D, int[][] F, int n) {
        //readFile(filePath);
        this.F = F;
        this.D = D;
        this.n = n;
        solucion = new int[n];
    }

    public void resolverGreedy() {
        // Calcular el potencial de flujo y de distancia
        int[] potencialFlujo = new int[n];
        int[] potencialDistancia = new int[n];

        for (int i = 0; i < n; i++) {
            potencialFlujo[i] = 0;
            potencialDistancia[i] = 0;
            for (int j = 0; j < n; j++) {
                potencialFlujo[i] += F[i][j];
                potencialDistancia[i] += D[i][j];
            }
        }
       System.out.println("Flujo Potencial: " + Arrays.toString(potencialFlujo));
       System.out.println("Distancia Potencial: " + Arrays.toString(potencialDistancia));
        // Ordenar unidades por flujo descendente
        List<Integer> unidades = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            unidades.add(i);
        }
        unidades.sort((u1, u2) -> Integer.compare(potencialFlujo[u2], potencialFlujo[u1]));
        System.out.println("UNIDADES: " + unidades.toString());

        // Ordenar localizaciones por distancia ascendente
        List<Integer> localizaciones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            localizaciones.add(i);
        }
        localizaciones.sort(Comparator.comparingInt(l -> potencialDistancia[l]));

        System.out.println("LOCALIZACIONES: " + localizaciones.toString());
        // Asignar cada unidad con mayor flujo a la mejor localización libre
        for (int i = 0; i < n; i++) {
            int unidad = unidades.remove(0); // Tomamos la unidad con mayor flujo
            int localizacion = localizaciones.remove(0); // Tomamos la localización con menor distancia
            solucion[unidad] = localizacion; // Asignamos
        }
    }

    public int funcionObjetivo() {
        int coste = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coste += F[i][j] * D[solucion[i]][solucion[j]];
            }
        }
        return coste;
    }

    public void mostrarSolucion() {
        System.out.println("Solución Greedy:");
        System.out.println(Arrays.toString(solucion));
        System.out.println("Coste de la solución: " + funcionObjetivo());
    }

}
