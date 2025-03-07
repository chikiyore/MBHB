/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mbhb.practica1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author jarro
 */
public class MBHBPractica1 {

    public static void main(String[] args) {
        String filePath = "prueba.dat"; // Prueba.dat

        try {
            Scanner scanner = new Scanner(new File(filePath));

            // Leer dimension y crear matrices
            int n = scanner.nextInt();
            int[][] D = new int[n][n];
            int[][] F = new int[n][n];

            // Leer matriz D
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    D[i][j] = scanner.nextInt();
                }
            }

            // Leer matriz F
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    F[i][j] = scanner.nextInt();
                }
            }

            scanner.close();

            // Mostrar los datos leídos (opcional)
            System.out.println("Tamaño del problema: " + n);
            System.out.println("Matriz D:");
            pintarmatriz(D);
            System.out.println("Matriz F:");
            pintarmatriz(F);
            
            Greedy algoritmogreedy= new Greedy(D,F,n);
            algoritmogreedy.resolverGreedy();
            algoritmogreedy.mostrarSolucion();

        } catch (FileNotFoundException e) {
            System.out.println("No he encontrado el fichero.");
            e.printStackTrace();
        }
    }

    private static void pintarmatriz(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

    }
}
