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
                System.out.println("7. Salir");

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
                        SolucionesG1=algoritmoGreedy1.resolverGreedy();
                        CostosG1=algoritmoGreedy1.devolverCosto();
                        EvaluacionesG1=algoritmoGreedy1.devolverEvaluaciones();
                         System.out.println("tai25b");
                         System.out.println("Costo " + CostosG1);
                         System.out.println("Evaluaciones " +  EvaluacionesG1+"\n");
                        

                        Greedy algoritmoGreedy2 = new Greedy(D2, F2, n2);
                        SolucionesG2=algoritmoGreedy2.resolverGreedy();
                        CostosG2=algoritmoGreedy2.devolverCosto();
                        EvaluacionesG2=algoritmoGreedy2.devolverEvaluaciones();
                        System.out.println("sko90");
                         System.out.println("Costo " + CostosG2);
                         System.out.println("Evaluaciones " +  EvaluacionesG2+"\n");
                        

                        Greedy algoritmoGreedy3 = new Greedy(D3, F3, n3);
                        SolucionesG3=algoritmoGreedy3.resolverGreedy();
                        CostosG3=algoritmoGreedy3.devolverCosto();
                        EvaluacionesG3=algoritmoGreedy3.devolverEvaluaciones();
                        System.out.println("tai150b");
                         System.out.println("Costo " + CostosG3);
                         System.out.println("Evaluaciones " +  EvaluacionesG3+"\n");
                        
                        
                        
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
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa1[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBa1).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBa1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa1));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBa1, calcularMedia(CostosBa1)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa1) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBa1, calcularMedia(EvaluacionesBa1))+"\n");
                        
                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa2[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBa2).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBa2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa2));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBa2, calcularMedia(CostosBa2)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa2) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBa2, calcularMedia(EvaluacionesBa2))+"\n");
                        
                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBa3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBa3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBa3[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBa3).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBa3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBa3));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBa3, calcularMedia(CostosBa3)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBa3) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBa3, calcularMedia(EvaluacionesBa3))+"\n");

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
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM1[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLM1).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLM1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM1));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLM1, calcularMedia(CostosBLM1)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM1) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLM1, calcularMedia(EvaluacionesBLM1))+"\n");
                        
                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM2[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM2[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLM2).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLM2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM2));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLM2, calcularMedia(CostosBLM2)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM2) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLM2, calcularMedia(EvaluacionesBLM2))+"\n");
                        
                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLM3[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLM3[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLM3).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLM3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLM3));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLM3, calcularMedia(CostosBLM3)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLM3) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLM3, calcularMedia(EvaluacionesBLM3))+"\n");
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
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM1[i]);
                             System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM1[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLPM1).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLPM1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM1));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLPM1, calcularMedia(CostosBLPM1)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM1) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLPM1, calcularMedia(EvaluacionesBLPM1))+"\n");
                        
                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM2[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLPM2).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLPM2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM2));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLPM2, calcularMedia(CostosBLPM2)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM2) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLPM2, calcularMedia(EvaluacionesBLPM2))+"\n");
                        
                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBLPM3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBLPM3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBLPM3[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBLPM3).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBLPM3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBLPM3));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBLPM3, calcularMedia(CostosBLPM3)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBLPM3) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBLPM3, calcularMedia(EvaluacionesBLPM3))+"\n");
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
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES1[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosES1).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosES1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES1));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosES1, calcularMedia(CostosES1)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES1) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesES1, calcularMedia(EvaluacionesES1))+"\n");
                        
                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES2[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosES2).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosES2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES2));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosES2, calcularMedia(CostosES2)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES2) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesES2, calcularMedia(EvaluacionesES2))+"\n");
                        
                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesES3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosES3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesES3[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosES3).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosES3).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosES3));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosES3, calcularMedia(CostosES3)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesES3) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesES3, calcularMedia(EvaluacionesES3))+"\n");

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
                        System.out.println("Todas las soluciones:\n");
                        System.out.println("tai25b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT1[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT1[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT1[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBT1).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBT1).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBT1));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBT1, calcularMedia(CostosBT1)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT1) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBT1, calcularMedia(EvaluacionesBT1))+"\n");
                        
                        System.out.println("sko90");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT2[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT2[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT2[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBT2).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBT2).max().getAsInt());
                        System.out.println("Media de coste: " + calcularMedia(CostosBT2));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBT2, calcularMedia(CostosBT2)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT2) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBT2, calcularMedia(EvaluacionesBT2))+"\n");
                        
                        System.out.println("tai150b");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(Arrays.toString(SolucionesBT3[i]));
                            System.out.println("Costo " + (i + 1) + " = " + CostosBT3[i]);
                            System.out.println("Evaluaciones " + (i + 1) + " = " + EvaluacionesBT3[i]);
                        }
                        System.out.println("El mejor coste es: "+ Arrays.stream(CostosBT3).min().getAsInt());
                        System.out.println("El peor coste es: "+ Arrays.stream(CostosBT3).max().getAsInt());                        
                        System.out.println("Media de coste: " + calcularMedia(CostosBT3));
                        System.out.println("Desviación tipica de coste: "+ calcularDesviacion(CostosBT3, calcularMedia(CostosBT3)) );
                        System.out.println("Media de evaluaciones: " + calcularMedia(EvaluacionesBT3) );
                        System.out.println("Desviación tipica de evaluaciones: " +calcularDesviacion(EvaluacionesBT3, calcularMedia(EvaluacionesBT3))+"\n");
                        
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
