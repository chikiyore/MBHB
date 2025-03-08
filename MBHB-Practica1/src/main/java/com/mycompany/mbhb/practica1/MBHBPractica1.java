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
    Scanner lectorOpcion = new Scanner(System.in);

        // Preguntar el nombre del fichero
        System.out.print("Ingrese el nombre del fichero (ejemplo: prueba.dat): ");
        String filePath = lectorOpcion.nextLine();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            // Leer la dimensión y crear matrices
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
            pintarMatriz(D);
            System.out.println("Matriz F:");
            pintarMatriz(F);

            int opcion;
            do {
                // Menú interactivo
                System.out.println("\nSeleccione el algoritmo a aplicar:");
                System.out.println("1. Algoritmo Greedy");
                System.out.println("2. Búsqueda Aleatoria (por implementar)");
                System.out.println("3. Búsqueda Local (por implementar)");
                System.out.println("4. Enfriamiento Simulado (por implementar)");
                System.out.println("5. Búsqueda Tabú(por implementar)");                
                System.out.println("6. Salir");
                
                while (!lectorOpcion.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    lectorOpcion.next(); // Limpiar entrada incorrecta
                }

                opcion = lectorOpcion.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ejecutando Algoritmo Greedy\n");
                        Greedy algoritmoGreedy = new Greedy(D, F, n);
                        algoritmoGreedy.resolverGreedy();
                        algoritmoGreedy.mostrarSolucion();
                        break;
                    case 2:
                        System.out.println("Búsqueda Aleatoria aún no implementado.");
                        break;
                    case 3:
                        System.out.println("Búsqueda Local aún no implementado.");
                        break;
                    case 4:
                        System.out.println("Enfriamiento Simulado aún no implementado.");
                        break;
                    case 5:
                        System.out.println("Búsqueda Tabú aún no implementado.");
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            } while (opcion != 6);

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero.");
            e.printStackTrace();
        }

        lectorOpcion.close();
    }

    private static void pintarMatriz(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
}
