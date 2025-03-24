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
                System.out.println("5. Enfriamiento Simulado");
                System.out.println("6. Búsqueda Tabú");
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
                            EvaluacionesBa1[i]=ba1.devolverEvaluaciones();

                            BusquedaAleatoria ba2 = new BusquedaAleatoria(D2, F2, n2, Semillas[i]);
                            SolucionesBa2[i] = ba2.resolverBusquedaAleatoria();
                            CostosBa2[i] = ba2.devolverCosto();
                             EvaluacionesBa2[i]=ba2.devolverEvaluaciones();

                            BusquedaAleatoria ba3 = new BusquedaAleatoria(D3, F3, n3, Semillas[i]);
                            SolucionesBa3[i] = ba3.resolverBusquedaAleatoria();
                            CostosBa3[i] = ba3.devolverCosto();
                             EvaluacionesBa3[i]=ba3.devolverEvaluaciones();

                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa3[i]);
                        }

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
                            EvaluacionesBLM1[i]=blm1.devolverEvaluaciones();
                            BusquedaLocalMejor blm2 = new BusquedaLocalMejor(D2, F2, n2, Semillas[i]);
                            SolucionesBLM2[i] = blm2.resolverBusquedaLocalMejor();
                            CostosBLM2[i] = blm2.devolverCosto();
                            EvaluacionesBLM2[i]=blm2.devolverEvaluaciones();
                            BusquedaLocalMejor blm3 = new BusquedaLocalMejor(D3, F3, n3, Semillas[i]);
                            SolucionesBLM3[i] = blm3.resolverBusquedaLocalMejor();
                            CostosBLM3[i] = blm3.devolverCosto();
                            EvaluacionesBLM3[i]=blm3.devolverEvaluaciones();
                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM2[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM3[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM3[i]);
                        }
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
                            EvaluacionesBLPM1[i]=blpm1.devolverEvaluaciones();
                            BusquedaLocalPrimerMejor blpm2 = new BusquedaLocalPrimerMejor(D2, F2, n2, Semillas[i]);
                            SolucionesBLPM2[i] = blpm2.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM2[i] = blpm2.devolverCosto();
                             EvaluacionesBLPM2[i]=blpm2.devolverEvaluaciones();
                            BusquedaLocalPrimerMejor blpm3 = new BusquedaLocalPrimerMejor(D3, F3, n3, Semillas[i]);
                            SolucionesBLPM3[i] = blpm3.resolverBusquedaLocalPrimerMejor();
                            CostosBLPM3[i] = blpm3.devolverCosto();
                             EvaluacionesBLPM3[i]=blpm3.devolverEvaluaciones();
                        }

                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM1[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM3[i]);
                        }
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
                            EvaluacionesES1[i]=es1.devolverEvaluaiones();

                            EnfriamientoSimulado es2 = new EnfriamientoSimulado(D2, F2, n2, Semillas[i]);
                            SolucionesES2[i] = es2.resolverEnfriamientoSimulado();
                            CostosES2[i] = es2.devolverCosto();
                            EvaluacionesES2[i]=es2.devolverEvaluaiones();

                            EnfriamientoSimulado es3 = new EnfriamientoSimulado(D3, F3, n3, Semillas[i]);
                            SolucionesES3[i] = es3.resolverEnfriamientoSimulado();
                            CostosES3[i] = es3.devolverCosto();
                            EvaluacionesES3[i]=es3.devolverEvaluaiones();

                        }
                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES3[i]);
                        }

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
                            EvaluacionesBT1[i]=bt1.devolverEvaluaciones();
                            
                            BusquedaTabu bt2 = new BusquedaTabu(D2, F2, n2, Semillas[i]);
                            SolucionesBT2[i] = bt2.resolverBusquedaTabu();
                            CostosBT2[i] = bt2.devolverCosto();
                             EvaluacionesBT2[i]=bt2.devolverEvaluaciones();
                            
                            BusquedaTabu bt3 = new BusquedaTabu(D3, F3, n3, Semillas[i]);
                            SolucionesBT3[i] = bt3.resolverBusquedaTabu();
                            CostosBT3[i] = bt3.devolverCosto();
                             EvaluacionesBT3[i]=bt3.devolverEvaluaciones();

                        }
                        System.out.println("Todas las soluciones:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT1[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT2[i]);
                        }
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT3[i]);
                        }
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
