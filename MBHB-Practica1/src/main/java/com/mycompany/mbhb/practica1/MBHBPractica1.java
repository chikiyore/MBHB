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
        long[] Semillas={123456789,234567891,345678912,456789123,567891234};
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
                        Greedy algoritmoGreedy = new Greedy(D, F, n);
                        algoritmoGreedy.resolverGreedy();
                        algoritmoGreedy.mostrarSolucion();
                        break;
                    case 2:
                        System.out.println("Ejecutando Búsqueda Aleatoria\n");
                        
                      int[][] SolucionesBa=new int[5][n];
                      int[] CostosBa=new int[5];
                      
                       for(int i=0;i<5;i++){
                        BusquedaAleatoria ba=new BusquedaAleatoria(D, F, n, Semillas[i]);
                       SolucionesBa[i] = ba.resolverBusquedaAleatoria();
                       CostosBa[i]=ba.devolverCosto();                       
                       }
                       
                       System.out.println("Todas las soluciones:");
        for (int i=0;i<5 ; i++) {
            System.out.println(Arrays.toString(SolucionesBa[i]));
            System.out.println("Costo "+ (i+1) +" = "+ CostosBa[i] );
        }
                        
                        break;
                    case 3:
                        System.out.println("Búsqueda Local el mejor\n");
                        
                      int[][] SolucionesBLM=new int[5][n];
                      int[] CostosBLM=new int[5];
                      
                       for(int i=0;i<5;i++){
                        BusquedaLocalMejor blm=new BusquedaLocalMejor(D, F, n, Semillas[i]);
                       SolucionesBLM[i] = blm.resolverBusquedaLocalMejor();
                       CostosBLM[i]=blm.devolverCosto();                       
                       }
                       
                       System.out.println("Todas las soluciones:");
        for (int i=0;i<5 ; i++) {
            System.out.println(Arrays.toString(SolucionesBLM[i]));
            System.out.println("Costo "+ (i+1) +" = "+ CostosBLM[i] );
        }
                        break;
                    case 4:
                        System.out.println("Búsqueda Local Primer Mejor\n");
                        
                        
                      int[][] SolucionesBLPM=new int[5][n];
                      int[] CostosBLPM=new int[5];
                      
                       for(int i=0;i<5;i++){
                       BusquedaLocalPrimerMejor blpm=new BusquedaLocalPrimerMejor(D, F, n,Semillas[i]);
                       SolucionesBLPM[i] = blpm.resolverBusquedaLocalPrimerMejor();
                       CostosBLPM[i]=blpm.devolverCosto();
                       }
                       
                       System.out.println("Todas las soluciones:");
        for (int i=0;i<5 ; i++) {
            System.out.println(Arrays.toString(SolucionesBLPM[i]));
            System.out.println("Costo "+ (i+1) +" = "+ CostosBLPM[i] );
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
