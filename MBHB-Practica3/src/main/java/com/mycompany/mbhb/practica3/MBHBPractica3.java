/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mbhb.practica3;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jarro
 */
public class MBHBPractica3 {

    public static void main(String[] args) {
        long[] Semillas = {123456789, 234567891, 345678912, 456789123, 567891234};
        Scanner lectorOpcion = new Scanner(System.in);

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

            int opcion;
            do {
                // Menú interactivo
                System.out.println("\nSeleccione el algoritmo a aplicar:");
                System.out.println("1. Algoritmo Greedy");
                System.out.println("2. Búsqueda Aleatoria");
                System.out.println("3. Búsqueda Local el Mejor");
                System.out.println("4. Búsqueda Local Primer Mejor");
                System.out.println("5. Enfriamiento Simulado");
                System.out.println("6. Búsqueda Tabú");
                System.out.println("7. GRASP");
                System.out.println("8. ILS");
                System.out.println("9. VNS");
                System.out.println("10. Genetico Basico");
                System.out.println("11. Genetico CHC");
                System.out.println("12. Genetico Multimodal");
                System.out.println("13. Salir");

                while (!lectorOpcion.hasNextInt()) {
                    System.out.println("Por favor, ingrese un número válido.");
                    lectorOpcion.next(); // Limpiar entrada incorrecta
                }

                opcion = lectorOpcion.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ejecutando Algoritmo Greedy\n");
                        int[] SolucionesG1 = new int[n1];
                        int CostosG1;
                        int EvaluacionesG1;
                        int[] SolucionesG2 = new int[n2];
                        int CostosG2;
                        int EvaluacionesG2;
                        int[] SolucionesG3 = new int[n3];
                        int CostosG3;
                        int EvaluacionesG3;
                        Greedy algoritmoGreedy1 = new Greedy(D1, F1, n1);
                        SolucionesG1 = algoritmoGreedy1.resolverGreedy();
                        CostosG1 = algoritmoGreedy1.devolverCosto();
                        EvaluacionesG1 = algoritmoGreedy1.devolverEvaluaciones();
                        System.out.println("tai25b");
                        System.out.println("Costo " + CostosG1);
                        System.out.println("Evaluaciones " + EvaluacionesG1 + "\n");

                        Greedy algoritmoGreedy2 = new Greedy(D2, F2, n2);
                        SolucionesG2 = algoritmoGreedy2.resolverGreedy();
                        CostosG2 = algoritmoGreedy2.devolverCosto();
                        EvaluacionesG2 = algoritmoGreedy2.devolverEvaluaciones();
                        System.out.println("sko90");
                        System.out.println("Costo " + CostosG2);
                        System.out.println("Evaluaciones " + EvaluacionesG2 + "\n");

                        Greedy algoritmoGreedy3 = new Greedy(D3, F3, n3);
                        SolucionesG3 = algoritmoGreedy3.resolverGreedy();
                        CostosG3 = algoritmoGreedy3.devolverCosto();
                        EvaluacionesG3 = algoritmoGreedy3.devolverEvaluaciones();
                        System.out.println("tai150b");
                        System.out.println("Costo " + CostosG3);
                        System.out.println("Evaluaciones " + EvaluacionesG3 + "\n");

                        break;
                    case 2:
                        System.out.println("Ejecutando Búsqueda Aleatoria\n");

                        int[][] SolucionesBa1 = new int[5][n1];
                        int[] CostosBa1 = new int[5];
                        int[] EvaluacionesBa1 = new int[5];
                        int[][] SolucionesBa2 = new int[5][n2];
                        int[] CostosBa2 = new int[5];
                        int[] EvaluacionesBa2 = new int[5];
                        int[][] SolucionesBa3 = new int[5][n3];
                        int[] CostosBa3 = new int[5];
                        int[] EvaluacionesBa3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaAleatoria ba1 = new BusquedaAleatoria(D1, F1, n1, Semillas[i]);
                            SolucionesBa1[i] = ba1.resolverBusquedaAleatoria();
                            CostosBa1[i] = ba1.devolverCosto();
                            EvaluacionesBa1[i] = ba1.devolverEvaluaciones();

                            BusquedaAleatoria ba2 = new BusquedaAleatoria(D2, F2, n2, Semillas[i]);
                            SolucionesBa2[i] = ba2.resolverBusquedaAleatoria();
                            CostosBa2[i] = ba2.devolverCosto();
                            EvaluacionesBa2[i] = ba2.devolverEvaluaciones();

                            BusquedaAleatoria ba3 = new BusquedaAleatoria(D3, F3, n3, Semillas[i]);
                            SolucionesBa3[i] = ba3.resolverBusquedaAleatoria();
                            CostosBa3[i] = ba3.devolverCosto();
                            EvaluacionesBa3[i] = ba3.devolverEvaluaciones();

                        }

                        System.out.println("Todas las soluciones:");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBa1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBa1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBa1, calcularMedia(CostosBa1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBa1, calcularMedia(EvaluacionesBa1)) + "\n");

                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBa2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBa2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBa2, calcularMedia(CostosBa2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBa2, calcularMedia(EvaluacionesBa2)) + "\n");

                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBa3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBa3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBa3, calcularMedia(CostosBa3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBa3, calcularMedia(EvaluacionesBa3)) + "\n");

                        break;
                    case 3:
                        System.out.println("Búsqueda Local el mejor\n");

                        int[][] SolucionesBLM1 = new int[5][n1];
                        int[] CostosBLM1 = new int[5];
                        int[] EvaluacionesBLM1 = new int[5];
                        int[][] SolucionesBLM2 = new int[5][n2];
                        int[] CostosBLM2 = new int[5];
                        int[] EvaluacionesBLM2 = new int[5];
                        int[][] SolucionesBLM3 = new int[5][n3];
                        int[] CostosBLM3 = new int[5];
                        int[] EvaluacionesBLM3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaLocalMejor blm1 = new BusquedaLocalMejor(D1, F1, n1, Semillas[i]);
                            SolucionesBLM1[i] = blm1.resolverBusquedaLocalMejor();
                            CostosBLM1[i] = blm1.devolverCosto();
                            EvaluacionesBLM1[i] = blm1.devolverEvaluaciones();
                            BusquedaLocalMejor blm2 = new BusquedaLocalMejor(D2, F2, n2, Semillas[i]);
                            SolucionesBLM2[i] = blm2.resolverBusquedaLocalMejor();
                            CostosBLM2[i] = blm2.devolverCosto();
                            EvaluacionesBLM2[i] = blm2.devolverEvaluaciones();
                            BusquedaLocalMejor blm3 = new BusquedaLocalMejor(D3, F3, n3, Semillas[i]);
                            SolucionesBLM3[i] = blm3.resolverBusquedaLocalMejor();
                            CostosBLM3[i] = blm3.devolverCosto();
                            EvaluacionesBLM3[i] = blm3.devolverEvaluaciones();
                        }

                        System.out.println("Todas las soluciones:");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLM1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLM1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLM1, calcularMedia(CostosBLM1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLM1, calcularMedia(EvaluacionesBLM1)) + "\n");

                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLM2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLM2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLM2, calcularMedia(CostosBLM2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLM2, calcularMedia(EvaluacionesBLM2)) + "\n");

                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLM3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLM3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLM3, calcularMedia(CostosBLM3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLM3, calcularMedia(EvaluacionesBLM3)) + "\n");
                        break;
                    case 4:
                        System.out.println("Búsqueda Local Primer Mejor\n");

                        int[][] SolucionesBLPM1 = new int[5][n1];
                        int[] CostosBLPM1 = new int[5];
                        int[] EvaluacionesBLPM1 = new int[5];
                        int[][] SolucionesBLPM2 = new int[5][n2];
                        int[] CostosBLPM2 = new int[5];
                        int[] EvaluacionesBLPM2 = new int[5];
                        int[][] SolucionesBLPM3 = new int[5][n3];
                        int[] CostosBLPM3 = new int[5];
                        int[] EvaluacionesBLPM3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaLocalPrimerMejor blpm1 = new BusquedaLocalPrimerMejor(D1, F1, n1, Semillas[i]);
                            SolucionesBLPM1[i] = blpm1.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM1[i] = blpm1.devolverCosto();
                            EvaluacionesBLPM1[i] = blpm1.devolverEvaluaciones();
                            BusquedaLocalPrimerMejor blpm2 = new BusquedaLocalPrimerMejor(D2, F2, n2, Semillas[i]);
                            SolucionesBLPM2[i] = blpm2.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM2[i] = blpm2.devolverCosto();
                            EvaluacionesBLPM2[i] = blpm2.devolverEvaluaciones();
                            BusquedaLocalPrimerMejor blpm3 = new BusquedaLocalPrimerMejor(D3, F3, n3, Semillas[i]);
                            SolucionesBLPM3[i] = blpm3.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM3[i] = blpm3.devolverCosto();
                            EvaluacionesBLPM3[i] = blpm3.devolverEvaluaciones();
                        }

                        System.out.println("Todas las soluciones:");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLPM1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLPM1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLPM1, calcularMedia(CostosBLPM1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLPM1, calcularMedia(EvaluacionesBLPM1)) + "\n");

                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLPM2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLPM2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLPM2, calcularMedia(CostosBLPM2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLPM2, calcularMedia(EvaluacionesBLPM2)) + "\n");

                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBLPM3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBLPM3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBLPM3, calcularMedia(CostosBLPM3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBLPM3, calcularMedia(EvaluacionesBLPM3)) + "\n");
                        break;
                    case 5:
                        System.out.println("Enfriamiento Simulado\n");
                        int[][] SolucionesES1 = new int[5][n1];
                        int[] CostosES1 = new int[5];
                        int[] EvaluacionesES1 = new int[5];
                        int[][] SolucionesES2 = new int[5][n2];
                        int[] CostosES2 = new int[5];
                        int[] EvaluacionesES2 = new int[5];
                        int[][] SolucionesES3 = new int[5][n3];
                        int[] CostosES3 = new int[5];
                        int[] EvaluacionesES3 = new int[5];
                        for (int i = 0; i < 5; i++) {
                            EnfriamientoSimulado es1 = new EnfriamientoSimulado(D1, F1, n1, Semillas[i]);
                            SolucionesES1[i] = es1.resolverEnfriamientoSimulado();
                            CostosES1[i] = es1.devolverCosto();
                            EvaluacionesES1[i] = es1.devolverEvaluaiones();

                            EnfriamientoSimulado es2 = new EnfriamientoSimulado(D2, F2, n2, Semillas[i]);
                            SolucionesES2[i] = es2.resolverEnfriamientoSimulado();
                            CostosES2[i] = es2.devolverCosto();
                            EvaluacionesES2[i] = es2.devolverEvaluaiones();

                            EnfriamientoSimulado es3 = new EnfriamientoSimulado(D3, F3, n3, Semillas[i]);
                            SolucionesES3[i] = es3.resolverEnfriamientoSimulado();
                            CostosES3[i] = es3.devolverCosto();
                            EvaluacionesES3[i] = es3.devolverEvaluaiones();

                        }
                        System.out.println("Todas las soluciones:");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosES1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosES1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosES1, calcularMedia(CostosES1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesES1, calcularMedia(EvaluacionesES1)) + "\n");

                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosES2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosES2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosES2, calcularMedia(CostosES2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesES2, calcularMedia(EvaluacionesES2)) + "\n");

                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosES3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosES3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosES3, calcularMedia(CostosES3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesES3, calcularMedia(EvaluacionesES3)) + "\n");

                        break;
                    case 6:
                        System.out.println("Búsqueda Tabú\n");
                        int[][] SolucionesBT1 = new int[5][n1];
                        int[] CostosBT1 = new int[5];
                        int[] EvaluacionesBT1 = new int[5];
                        int[][] SolucionesBT2 = new int[5][n2];
                        int[] CostosBT2 = new int[5];
                        int[] EvaluacionesBT2 = new int[5];
                        int[][] SolucionesBT3 = new int[5][n3];
                        int[] CostosBT3 = new int[5];
                        int[] EvaluacionesBT3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            BusquedaTabu bt1 = new BusquedaTabu(D1, F1, n1, Semillas[i]);
                            SolucionesBT1[i] = bt1.resolverBusquedaTabu();
                            CostosBT1[i] = bt1.devolverCosto();
                            EvaluacionesBT1[i] = bt1.devolverEvaluaciones();

                            BusquedaTabu bt2 = new BusquedaTabu(D2, F2, n2, Semillas[i]);
                            SolucionesBT2[i] = bt2.resolverBusquedaTabu();
                            CostosBT2[i] = bt2.devolverCosto();
                            EvaluacionesBT2[i] = bt2.devolverEvaluaciones();

                            BusquedaTabu bt3 = new BusquedaTabu(D3, F3, n3, Semillas[i]);
                            SolucionesBT3[i] = bt3.resolverBusquedaTabu();
                            CostosBT3[i] = bt3.devolverCosto();
                            EvaluacionesBT3[i] = bt3.devolverEvaluaciones();

                        }
                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBT1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBT1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBT1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBT1, calcularMedia(CostosBT1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBT1, calcularMedia(EvaluacionesBT1)) + "\n");

                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBT2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBT2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBT2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBT2, calcularMedia(CostosBT2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBT2, calcularMedia(EvaluacionesBT2)) + "\n");

                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosBT3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosBT3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBT3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosBT3, calcularMedia(CostosBT3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesBT3, calcularMedia(EvaluacionesBT3)) + "\n");

                        break;
                    case 7:
                        System.out.println("Algoritmo GRASP");
                        int[] SolucionesGRASP1 = new int[n1];
                        int CostosGRASP1;
                        int EvaluacionesGRASP1;
                        int[] SolucionesGRASP2 = new int[n2];
                        int CostosGRASP2;
                        int EvaluacionesGRASP2;
                        int[] SolucionesGRASP3 = new int[n3];
                        int CostosGRASP3;
                        int EvaluacionesGRASP3;

                        GRASP grasp1 = new GRASP(D1, F1, n1, Semillas[0]);
                        SolucionesGRASP1 = grasp1.ejecutarGRASP();
                        CostosGRASP1 = grasp1.getMejorCosto();
                        EvaluacionesGRASP1 = grasp1.getEvaluacionesTotales();

                        GRASP grasp2 = new GRASP(D2, F2, n2, Semillas[0]);
                        SolucionesGRASP2 = grasp2.ejecutarGRASP();
                        CostosGRASP2 = grasp2.getMejorCosto();
                        EvaluacionesGRASP2 = grasp2.getEvaluacionesTotales();

                        GRASP grasp3 = new GRASP(D3, F3, n3, Semillas[0]);
                        SolucionesGRASP3 = grasp3.ejecutarGRASP();
                        CostosGRASP3 = grasp3.getMejorCosto();
                        EvaluacionesGRASP3 = grasp3.getEvaluacionesTotales();

                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");

                        System.out.println(Arrays.toString(SolucionesGRASP1));
                        System.out.println("Costo " + 1 + " = " + CostosGRASP1);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesGRASP1);

                        System.out.println("sko90");
                        System.out.println(Arrays.toString(SolucionesGRASP2));
                        System.out.println("Costo " + 1 + " = " + CostosGRASP2);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesGRASP2);

                        System.out.println("tai150b");
                        System.out.println(Arrays.toString(SolucionesGRASP3));
                        System.out.println("Costo " + 1 + " = " + CostosGRASP3);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesGRASP3);

                        Graficador.graficar(grasp1.getHistorialMejoresCostes(), "GRASP tai25b");
                        Graficador.graficar(grasp2.getHistorialMejoresCostes(), "GRASP sko90");
                        Graficador.graficar(grasp3.getHistorialMejoresCostes(), "GRASP tai150b");
                        Graficador.graficar(grasp1.getHistorialCostesIniciales(), "GRASP sin intensificacion tai25b");
                        Graficador.graficar(grasp2.getHistorialCostesIniciales(), "GRASP sin intensificacion sko90");
                        Graficador.graficar(grasp3.getHistorialCostesIniciales(), "GRASP sin intensificacion tai150b");
                        Graficador.graficar(grasp1.getHistorialCostesBL(), "GRASP con intensificacion tai25b");
                        Graficador.graficar(grasp2.getHistorialCostesBL(), "GRASP con intensificacion sko90");
                        Graficador.graficar(grasp3.getHistorialCostesBL(), "GRASP con intensificacion tai150b");
                        System.out.println("Distancias de Hamming 1: " + grasp1.calcularDistanciasHamming());
                        System.out.println("Número de soluciones únicas 1: " + grasp1.contarSolucionesUnicas());
                        System.out.println("Distancias de Hamming 2: " + grasp2.calcularDistanciasHamming());
                        System.out.println("Número de soluciones únicas 2: " + grasp2.contarSolucionesUnicas());
                        System.out.println("Distancias de Hamming 3: " + grasp3.calcularDistanciasHamming());
                        System.out.println("Número de soluciones únicas 3: " + grasp3.contarSolucionesUnicas());

                        break;
                    case 8:
                        System.out.println("Algoritmo ILS");
                        int[] SolucionesILS1 = new int[n1];
                        int CostosILS1;
                        int EvaluacionesILS1;
                        int[] SolucionesILS2 = new int[n2];
                        int CostosILS2;
                        int EvaluacionesILS2;
                        int[] SolucionesILS3 = new int[n3];
                        int CostosILS3;
                        int EvaluacionesILS3;

                        ILS ils1 = new ILS(D1, F1, n1, Semillas[0]);
                        SolucionesILS1 = ils1.ejecutarILS();
                        CostosILS1 = ils1.getMejorCosto();
                        EvaluacionesILS1 = ils1.getEvaluacionesTotales();

                        ILS ils2 = new ILS(D2, F2, n2, Semillas[0]);
                        SolucionesILS2 = ils2.ejecutarILS();
                        CostosILS2 = ils2.getMejorCosto();
                        EvaluacionesILS2 = ils2.getEvaluacionesTotales();

                        ILS ils3 = new ILS(D3, F3, n3, Semillas[0]);
                        SolucionesILS3 = ils3.ejecutarILS();
                        CostosILS3 = ils3.getMejorCosto();
                        EvaluacionesILS3 = ils3.getEvaluacionesTotales();

                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");

                        System.out.println(Arrays.toString(SolucionesILS1));
                        System.out.println("Costo " + 1 + " = " + CostosILS1);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesILS1);

                        System.out.println("sko90");
                        System.out.println(Arrays.toString(SolucionesILS2));
                        System.out.println("Costo " + 1 + " = " + CostosILS2);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesILS2);

                        System.out.println("tai150b");
                        System.out.println(Arrays.toString(SolucionesILS3));
                        System.out.println("Costo " + 1 + " = " + CostosILS3);
                        System.out.println("Evaluaciones " + 1 + " = " + EvaluacionesILS3);

                        Graficador.graficar(ils1.getHistorialMejoresCostes(), "ILS tai25b");
                        Graficador.graficar(ils2.getHistorialMejoresCostes(), "ILS sko90");
                        Graficador.graficar(ils3.getHistorialMejoresCostes(), "ILS tai150b");
                        break;
                    case 9:
                        System.out.println("Algoritmo VNS");
                        int[][] SolucionesVNS1 = new int[5][n1];
                        int[] CostosVNS1 = new int[5];
                        int[] EvaluacionesVNS1 = new int[5];
                        int[][] SolucionesVNS2 = new int[5][n2];
                        int[] CostosVNS2 = new int[5];
                        int[] EvaluacionesVNS2 = new int[5];
                        int[][] SolucionesVNS3 = new int[5][n3];
                        int[] CostosVNS3 = new int[5];
                        int[] EvaluacionesVNS3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            VNS vns1 = new VNS(D1, F1, n1, Semillas[i]);
                            SolucionesVNS1[i] = vns1.ejecutarVNS();
                            CostosVNS1[i] = vns1.getMejorCosto();
                            EvaluacionesVNS1[i] = vns1.getEvaluacionesTotales();

                            VNS vns2 = new VNS(D2, F2, n2, Semillas[i]);
                            SolucionesVNS2[i] = vns2.ejecutarVNS();
                            CostosVNS2[i] = vns2.getMejorCosto();
                            EvaluacionesVNS2[i] = vns2.getEvaluacionesTotales();

                            VNS vns3 = new VNS(D3, F3, n3, Semillas[i]);
                            SolucionesVNS3[i] = vns3.ejecutarVNS();
                            CostosVNS3[i] = vns3.getMejorCosto();
                            EvaluacionesVNS3[i] = vns3.getEvaluacionesTotales();

                            Graficador.graficar(vns1.getHistorialMejoresCostes(), "VNS tai25b semilla " + (i + 1));
                            Graficador.graficar(vns2.getHistorialMejoresCostes(), "VNS sko90 semilla" + (i + 1));
                            Graficador.graficar(vns3.getHistorialMejoresCostes(), "VNS tai150b semilla" + (i + 1));
                        }

                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesVNS1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosVNS1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesVNS1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosVNS1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosVNS1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosVNS1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosVNS1, calcularMedia(CostosVNS1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesVNS1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesVNS1, calcularMedia(EvaluacionesVNS1)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("sko90");
                            System.out.println(Arrays.toString(SolucionesVNS2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosVNS2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesVNS2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosVNS2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosVNS2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosVNS2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosVNS2, calcularMedia(CostosVNS2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesVNS2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesVNS2, calcularMedia(EvaluacionesVNS2)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("tai150b");
                            System.out.println(Arrays.toString(SolucionesVNS3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosVNS3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesVNS3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosVNS3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosVNS3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosVNS3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosVNS3, calcularMedia(CostosVNS3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesVNS3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesVNS3, calcularMedia(EvaluacionesVNS3)) + "\n");

                        System.out.println("Error relativo:\n");
                        System.out.println("tai25b");
                        double mediavns1 = calcularMedia(CostosVNS1);
                        int mejorvalorvns1 = Arrays.stream(CostosVNS1).min().getAsInt();
                        double errorvns1 = ((mediavns1 - mejorvalorvns1) / mejorvalorvns1) * 100;
                        System.out.println("El error relativo de VNS tai25b es " + errorvns1);
                        System.out.println("sko90");
                        double mediavns2 = calcularMedia(CostosVNS2);
                        int mejorvalorvns2 = Arrays.stream(CostosVNS2).min().getAsInt();
                        double errorvns2 = ((mediavns2 - mejorvalorvns2) / mejorvalorvns2) * 100;
                        System.out.println("El error relativo de VNS sko90 es " + errorvns2);
                        System.out.println("tai150b");
                        double mediavns3 = calcularMedia(CostosVNS3);
                        int mejorvalorvns3 = Arrays.stream(CostosVNS3).min().getAsInt();
                        double errorvns3 = ((mediavns3 - mejorvalorvns3) / mejorvalorvns3) * 100;
                        System.out.println("El error relativo de VNS tai150b es " + errorvns3 + "\n");
                        ArrayList<Double> listaErrores = new ArrayList<>();
                        listaErrores.add(errorvns1);
                        listaErrores.add(errorvns2);
                        listaErrores.add(errorvns3);

                        Graficador.graficar(listaErrores, "Error relativo por tamano");

                        System.out.println("Coeficiente de variacion:\n");
                        System.out.println("tai25b");

                        double desviacionvns1 = calcularDesviacion(CostosVNS1, mediavns1);
                        double cvvns1 = (desviacionvns1 / mediavns1) * 100;
                        System.out.println("El coeficiente de variacion de VNS tai25b es " + cvvns1);
                        System.out.println("sko90");
                        double desviacionvns2 = calcularDesviacion(CostosVNS2, mediavns2);
                        double cvvns2 = (desviacionvns2 / mediavns2) * 100;
                        System.out.println("El coeficiente de variacion de VNS sko90 es " + cvvns2);
                        System.out.println("tai150b");
                        double desviacionvns3 = calcularDesviacion(CostosVNS3, mediavns3);
                        double cvvns3 = (desviacionvns3 / mediavns3) * 100;
                        System.out.println("El coeficiente de variacion de VNS tai150b es " + cvvns3);
                        ArrayList<Double> listaCV = new ArrayList<>();
                        listaCV.add(cvvns1);
                        listaCV.add(cvvns2);
                        listaCV.add(cvvns3);

                        Graficador.graficar(listaCV, "Coeficiente de variacion por tamano");

                        break;

                    case 10:
                        System.out.println("Algoritmo Genetico Basico");
                        int[][] SolucionesGB1 = new int[5][n1];
                        int[] CostosGB1 = new int[5];
                        int[] EvaluacionesGB1 = new int[5];
                        int[][] SolucionesGB2 = new int[5][n2];
                        int[] CostosGB2 = new int[5];
                        int[] EvaluacionesGB2 = new int[5];
                        int[][] SolucionesGB3 = new int[5][n3];
                        int[] CostosGB3 = new int[5];
                        int[] EvaluacionesGB3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            GeneticoBasico gb1 = new GeneticoBasico(D1, F1, n1, Semillas[i]);
                            SolucionesGB1[i] = gb1.ejecutarAG();
                            CostosGB1[i] = gb1.getMejorCosto();
                            EvaluacionesGB1[i] = gb1.getEvaluacionesTotales();

                            GeneticoBasico gb2 = new GeneticoBasico(D2, F2, n2, Semillas[i]);
                            SolucionesGB2[i] = gb2.ejecutarAG();
                            CostosGB2[i] = gb2.getMejorCosto();
                            EvaluacionesGB2[i] = gb2.getEvaluacionesTotales();
                            
                            GeneticoBasico gb3 = new GeneticoBasico(D3, F3, n3, Semillas[i]);
                            SolucionesGB3[i] = gb3.ejecutarAG();
                            CostosGB3[i] = gb3.getMejorCosto();
                            EvaluacionesGB3[i] = gb3.getEvaluacionesTotales();

                            Graficador.graficar(gb1.getHistorialMejoresCostes(), "Genetico Basico tai25b semilla " + (i + 1));
                            Graficador.graficar(gb2.getHistorialMejoresCostes(), "Genetico Basico sko90 semilla" + (i + 1));
                            Graficador.graficar(gb3.getHistorialMejoresCostes(), "Genetico Basico tai150b semilla" + (i + 1));
                        }

                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesGB1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosGB1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesGB1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosGB1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosGB1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosGB1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosGB1, calcularMedia(CostosGB1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesGB1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesGB1, calcularMedia(EvaluacionesGB1)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("sko90");
                            System.out.println(Arrays.toString(SolucionesGB2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosGB2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesGB2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosGB2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosGB2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosGB2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosGB2, calcularMedia(CostosGB2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesGB2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesGB2, calcularMedia(EvaluacionesGB2)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("tai150b");
                            System.out.println(Arrays.toString(SolucionesGB3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosGB3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesGB3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosGB3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosGB3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosGB3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosGB3, calcularMedia(CostosGB3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesGB3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesGB3, calcularMedia(EvaluacionesGB3)) + "\n");

                        System.out.println("Error relativo:\n");
                        System.out.println("tai25b");
                        double mediagb1 = calcularMedia(CostosGB1);
                        int mejorvalorgb1 = Arrays.stream(CostosGB1).min().getAsInt();
                        double errorgb1 = ((mediagb1 - mejorvalorgb1) / mejorvalorgb1) * 100;
                        System.out.println("El error relativo de Genetico Basico tai25b es " + errorgb1);
                        System.out.println("sko90");
                        double mediagb2 = calcularMedia(CostosGB2);
                        int mejorvalorgb2 = Arrays.stream(CostosGB2).min().getAsInt();
                        double errorgb2 = ((mediagb2 - mejorvalorgb2) / mejorvalorgb2) * 100;
                        System.out.println("El error relativo de Genetico Basico sko90 es " + errorgb2);
                        System.out.println("tai150b");
                        double mediagb3 = calcularMedia(CostosGB3);
                        int mejorvalorgb3 = Arrays.stream(CostosGB3).min().getAsInt();
                        double errorgb3 = ((mediagb3 - mejorvalorgb3) / mejorvalorgb3) * 100;
                        System.out.println("El error relativo de Genetico Basico tai150b es " + errorgb3 + "\n");
                        ArrayList<Double> listaErroresGB = new ArrayList<>();
                        listaErroresGB.add(errorgb1);
                        listaErroresGB.add(errorgb2);
                        listaErroresGB.add(errorgb3);

                        //Graficador.graficar(listaErroresGB, "Error relativo por tamano");

                        System.out.println("Coeficiente de variacion:\n");
                        System.out.println("tai25b");

                        double desviaciongb1 = calcularDesviacion(CostosGB1, mediagb1);
                        double cvgb1 = (desviaciongb1 / mediagb1) * 100;
                        System.out.println("El coeficiente de variacion de Genetico Basico tai25b es " + cvgb1);
                        System.out.println("sko90");
                        double desviaciongb2 = calcularDesviacion(CostosGB2, mediagb2);
                        double cvgb2 = (desviaciongb2 / mediagb2) * 100;
                        System.out.println("El coeficiente de variacion de Genetico Basico sko90 es " + cvgb2);
                        System.out.println("tai150b");
                        double desviaciongb3 = calcularDesviacion(CostosGB3, mediagb3);
                        double cvgb3 = (desviaciongb3 / mediagb3) * 100;
                        System.out.println("El coeficiente de variacion de Genetico Basico tai150b es " + cvgb3);
                        ArrayList<Double> listaCVGB = new ArrayList<>();
                        listaCVGB.add(cvgb1);
                        listaCVGB.add(cvgb2);
                        listaCVGB.add(cvgb3);

                       // Graficador.graficar(listaCVGB, "Coeficiente de variacion por tamano");

                        break;

                    case 11:
                         System.out.println("Algoritmo CHC");
                        int[][] SolucionesCHC1 = new int[5][n1];
                        int[] CostosCHC1 = new int[5];
                        int[] EvaluacionesCHC1 = new int[5];
                        int[][] SolucionesCHC2 = new int[5][n2];
                        int[] CostosCHC2 = new int[5];
                        int[] EvaluacionesCHC2 = new int[5];
                        int[][] SolucionesCHC3 = new int[5][n3];
                        int[] CostosCHC3 = new int[5];
                        int[] EvaluacionesCHC3 = new int[5];

                        for (int i = 0; i < 5; i++) {
                            CHC chc1 = new CHC(D1, F1, n1, Semillas[i]);
                            SolucionesCHC1[i] = chc1.ejecutarCHC();
                            CostosCHC1[i] = chc1.getMejorCosto();
                            EvaluacionesCHC1[i] = chc1.getEvaluacionesTotales();

                            CHC chc2 = new CHC(D2, F2, n2, Semillas[i]);
                            SolucionesCHC2[i] = chc2.ejecutarCHC();
                            CostosCHC2[i] = chc2.getMejorCosto();
                            EvaluacionesCHC2[i] = chc2.getEvaluacionesTotales();
                            
                            CHC chc3 = new CHC(D3, F3, n3, Semillas[i]);
                            SolucionesCHC3[i] = chc3.ejecutarCHC();
                            CostosCHC3[i] = chc3.getMejorCosto();
                            EvaluacionesCHC3[i] = chc3.getEvaluacionesTotales();

                            Graficador.graficar(chc1.getHistorialPeoresCostes(), "CHC tai25b semilla " + (i + 1));
                            Graficador.graficar(chc2.getHistorialPeoresCostes(), "CHC sko90 semilla" + (i + 1));
                            Graficador.graficar(chc3.getHistorialPeoresCostes(), "CHC tai150b semilla" + (i + 1));
                        }

                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesCHC1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosCHC1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesCHC1[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosCHC1).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosCHC1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosCHC1));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosCHC1, calcularMedia(CostosCHC1)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesCHC1));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesCHC1, calcularMedia(EvaluacionesCHC1)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("sko90");
                            System.out.println(Arrays.toString(SolucionesCHC2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosCHC2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesCHC2[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosCHC2).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosCHC2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosCHC2));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosCHC2, calcularMedia(CostosCHC2)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesCHC2));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesCHC2, calcularMedia(EvaluacionesCHC2)) + "\n");

                        for (int i = 0; i < 5; i++) {
                            System.out.println("tai150b");
                            System.out.println(Arrays.toString(SolucionesCHC3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosCHC3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesCHC3[i]);
                        }
                        System.out.println("El mejor coste es: " + Arrays.stream(CostosCHC3).min().getAsInt());
                        System.out.println("El peor coste es: " + Arrays.stream(CostosCHC3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosCHC3));
                        System.out.println("Desviación tipica de coste: " + calcularDesviacion(CostosCHC3, calcularMedia(CostosCHC3)));
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesCHC3));
                        System.out.println("Desviación tipica de evaluaciones: " + calcularDesviacion(EvaluacionesCHC3, calcularMedia(EvaluacionesCHC3)) + "\n");

                        System.out.println("Error relativo:\n");
                        System.out.println("tai25b");
                        double mediachc1 = calcularMedia(CostosCHC1);
                        int mejorvalorchc1 = Arrays.stream(CostosCHC1).min().getAsInt();
                        double errorchc1 = ((mediachc1 - mejorvalorchc1) / mejorvalorchc1) * 100;
                        System.out.println("El error relativo de CHC tai25b es " + errorchc1);
                        System.out.println("sko90");
                        double mediachc2 = calcularMedia(CostosCHC2);
                        int mejorvalorchc2 = Arrays.stream(CostosCHC2).min().getAsInt();
                        double errorchc2 = ((mediachc2 - mejorvalorchc2) / mejorvalorchc2) * 100;
                        System.out.println("El error relativo de CHC sko90 es " + errorchc2);
                        System.out.println("tai150b");
                        double mediachc3 = calcularMedia(CostosCHC3);
                        int mejorvalorchc3 = Arrays.stream(CostosCHC3).min().getAsInt();
                        double errorchc3 = ((mediachc3 - mejorvalorchc3) / mejorvalorchc3) * 100;
                        System.out.println("El error relativo de CHC tai150b es " + errorchc3 + "\n");
                        ArrayList<Double> listaErroresCHC = new ArrayList<>();
                        listaErroresCHC.add(errorchc1);
                        listaErroresCHC.add(errorchc2);
                        listaErroresCHC.add(errorchc3);

                        //Graficador.graficar(listaErroresGB, "Error relativo por tamano");

                        System.out.println("Coeficiente de variacion:\n");
                        System.out.println("tai25b");

                        double desviacionchc1 = calcularDesviacion(CostosCHC1, mediachc1);
                        double cvchc1 = (desviacionchc1 / mediachc1) * 100;
                        System.out.println("El coeficiente de variacion de CHC tai25b es " + cvchc1);
                        System.out.println("sko90");
                        double desviacionchc2 = calcularDesviacion(CostosCHC2, mediachc2);
                        double cvchc2 = (desviacionchc2 / mediachc2) * 100;
                        System.out.println("El coeficiente de variacion de CHC sko90 es " + cvchc2);
                        System.out.println("tai150b");
                        double desviacionchc3 = calcularDesviacion(CostosCHC3, mediachc3);
                        double cvchc3 = (desviacionchc3 / mediachc3) * 100;
                        System.out.println("El coeficiente de variacion de CHC tai150b es " + cvchc3);
                        ArrayList<Double> listaCVCHC = new ArrayList<>();
                        listaCVCHC.add(cvchc1);
                        listaCVCHC.add(cvchc2);
                        listaCVCHC.add(cvchc3);

                       // Graficador.graficar(listaCVGB, "Coeficiente de variacion por tamano");
                        break;

























                    case 12:
                        System.out.println("Saliendo del programa...");
                        break;
                    case 13:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            } while (opcion != 13);

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero.");
            e.printStackTrace();
        }

        lectorOpcion.close();
    }

    public static double calcularMedia(int[] datos) {
        double suma = 0.0;
        for (int num : datos) {
            suma += num;
        }
        return suma / datos.length;
    }

    public static double calcularDesviacion(int[] datos, double media) {
        double sumaCuadrados = 0.0;
        for (int num : datos) {
            sumaCuadrados += Math.pow(num - media, 2);
        }
        return Math.sqrt(sumaCuadrados / datos.length);
    }

}
