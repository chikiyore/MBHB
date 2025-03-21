/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mbhb.practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AlgorithmConstraints;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jarro
 */
public class MBHBPractica1 {

    public static void main(String[] args) {
        long[] Semillas = {123456789, 234567891, 345678912, 456789123, 567891234};
        Scanner lectorOpcion = new Scanner(System.in);

        // Preguntar el nombre del fichero
        //System.out.print("Ingrese el nombre del fichero (ejemplo: prueba.dat): ");
        //String filePath = lectorOpcion.nextLine();
        try {
            Scanner scanner1 = new Scanner(new File("tai25b.dat"));
            Scanner scanner2 = new Scanner(new File("sko90.dat"));
            Scanner scanner3 = new Scanner(new File("tai150b.dat"));

            // Leer la dimensión y crear matrices
            int n1 = scanner1.nextInt();
            int[][] D1 = new int[n1][n1];
            int[][] F1 = new int[n1][n1];

            // Leer matriz D
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n1; j++) {
                    D1[i][j] = scanner1.nextInt();
                }
            }

            // Leer matriz F
            for (int i = 0; i < n1; i++) {
                for (int j = 0; j < n1; j++) {
                    F1[i][j] = scanner1.nextInt();
                }
            }

            // Leer la dimensión y crear matrices
            int n2 = scanner2.nextInt();
            int[][] D2 = new int[n2][n2];
            int[][] F2 = new int[n2][n2];

            // Leer matriz D
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n2; j++) {
                    D2[i][j] = scanner2.nextInt();
                }
            }

            // Leer matriz F
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n2; j++) {
                    F2[i][j] = scanner2.nextInt();
                }
            }

            // Leer la dimensión y crear matrices
            int n3 = scanner3.nextInt();
            int[][] D3 = new int[n3][n3];
            int[][] F3 = new int[n3][n3];

            // Leer matriz D
            for (int i = 0; i < n3; i++) {
                for (int j = 0; j < n3; j++) {
                    D3[i][j] = scanner3.nextInt();
                }
            }

            // Leer matriz F
            for (int i = 0; i < n3; i++) {
                for (int j = 0; j < n3; j++) {
                    F3[i][j] = scanner3.nextInt();
                }
            }

            scanner1.close();
            scanner2.close();
            scanner3.close();

            // Mostrar los datos leídos (opcional)
            // System.out.println("Tamaño del problema: " + n);
            // System.out.println("Matriz D:");
            // pintarMatriz(D);
            //  System.out.println("Matriz F:");
            //  pintarMatriz(F);
            int opcion;
            do {
                // Menú interactivo
                System.out.println("\nSeleccione el algoritmo a aplicar:");
                System.out.println("1. Algoritmo Greedy");
                System.out.println("2. Búsqueda Aleatoria");
                System.out.println("3. Búsqueda Local el Mejor");
                System.out.println("4. Búsqueda Local Primer Mejor");
                System.out.println("5. Enfriamiento Simulado (por implementar)");
                System.out.println("6. Búsqueda Tabú(por implementar)");
                System.out.println("7. Salir");

                while (!lectorOpcion.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    lectorOpcion.next(); // Limpiar entrada incorrecta
                }

                opcion = lectorOpcion.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ejecutando Algoritmo Greedy\n");
                        Greedy algoritmoGreedy1 = new Greedy(D1, F1, n1);
                        algoritmoGreedy1.resolverGreedy();
                        algoritmoGreedy1.mostrarSolucion();

                        Greedy algoritmoGreedy2 = new Greedy(D2, F2, n2);
                        algoritmoGreedy2.resolverGreedy();
                        algoritmoGreedy2.mostrarSolucion();

                        Greedy algoritmoGreedy3 = new Greedy(D3, F3, n3);
                        algoritmoGreedy3.resolverGreedy();
                        algoritmoGreedy3.mostrarSolucion();
                        break;
                    case 2:
                        System.out.println("Ejecutando Búsqueda Aleatoria\n");

                        int[][] SolucionesBa1 = new int[5][n1];
                        int[] CostosBa1 = new int[5];
                        int[][] SolucionesBa2 = new int[5][n2];
                        int[] CostosBa2 = new int[5];
                        int[][] SolucionesBa3 = new int[5][n3];
                        int[] CostosBa3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaAleatoria ba1 = new BusquedaAleatoria(D1, F1, n1, Semillas[i]);
                            SolucionesBa1[i] = ba1.resolverBusquedaAleatoria();
                            CostosBa1[i] = ba1.devolverCosto();

                            BusquedaAleatoria ba2 = new BusquedaAleatoria(D2, F2, n2, Semillas[i]);
                            SolucionesBa2[i] = ba2.resolverBusquedaAleatoria();
                            CostosBa2[i] = ba2.devolverCosto();

                            BusquedaAleatoria ba3 = new BusquedaAleatoria(D3, F3, n3, Semillas[i]);
                            SolucionesBa3[i] = ba3.resolverBusquedaAleatoria();
                            CostosBa3[i] = ba3.devolverCosto();

                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa3[i]);
                        }

                        break;
                    case 3:
                        System.out.println("Búsqueda Local el mejor\n");

                        int[][] SolucionesBLM1 = new int[5][n1];
                        int[] CostosBLM1 = new int[5];
                        int[][] SolucionesBLM2 = new int[5][n2];
                        int[] CostosBLM2 = new int[5];
                        int[][] SolucionesBLM3 = new int[5][n3];
                        int[] CostosBLM3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaLocalMejor blm1 = new BusquedaLocalMejor(D1, F1, n1, Semillas[i]);
                            SolucionesBLM1[i] = blm1.resolverBusquedaLocalMejor();
                            CostosBLM1[i] = blm1.devolverCosto();
                             BusquedaLocalMejor blm2 = new BusquedaLocalMejor(D2, F2, n2, Semillas[i]);
                            SolucionesBLM2[i] = blm2.resolverBusquedaLocalMejor();
                            CostosBLM2[i] = blm2.devolverCosto();
                             BusquedaLocalMejor blm3 = new BusquedaLocalMejor(D3, F3, n3, Semillas[i]);
                            SolucionesBLM3[i] = blm3.resolverBusquedaLocalMejor();
                            CostosBLM3[i] = blm3.devolverCosto();
                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM3[i]);
                        }
                        break;
                    case 4:
                        System.out.println("Búsqueda Local Primer Mejor\n");

                        int[][] SolucionesBLPM = new int[5][n1];
                        int[] CostosBLPM = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaLocalPrimerMejor blpm = new BusquedaLocalPrimerMejor(D1, F1, n1, Semillas[i]);
                            SolucionesBLPM[i] = blpm.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM[i] = blpm.devolverCosto();
                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM[i]);
                        }
                        break;
                    case 5:
                        System.out.println("Enfriamiento Simulado aún no implementado.");
                        break;
                    case 6:
                        System.out.println("Búsqueda Tabú aún no implementado.");
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            } while (opcion != 7);

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
