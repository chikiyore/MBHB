/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica1;

/**
 *
 * @author jarro
 */
import java.util.*;

public class BusquedaTabu {
    private int n;
    private int[][] F, D;
    private int[] mejorSolucion;
    private int mejorCosto;
    private Random random;
    private int tamanoListaTabu;
    private int iteracionesMaximas;
    private int iteracionesReinicio;
    private int[][] frec;
    private ElementoTabu[] listaTabu;
    private int indiceListaTabu;
    private int evaluaciones;

    public BusquedaTabu(int[][] D, int[][] F, int n, long seed) {
        this.F = F;
        this.D = D;
        this.n = n;
        this.random = new Random(seed);
        this.tamanoListaTabu = 2;
        this.iteracionesMaximas = 40 * n;
        this.iteracionesReinicio = 8 * n;
        this.mejorSolucion = generarSolucionAleatoria();
        this.mejorCosto = calcularCosto(mejorSolucion);
        this.frec = new int[n][n];
        this.listaTabu = new ElementoTabu[tamanoListaTabu];
        this.indiceListaTabu = 0;
        this.evaluaciones=0;
    }

    public int[] resolverBusquedaTabu() {
        int[] solucionActual = mejorSolucion.clone();
        int costoActual = mejorCosto;
        int iteracion = 0;
        int reinicios = 0;

        while (iteracion < iteracionesMaximas) {
            int[] mejorVecino = null;
            int mejorCostoVecino = Integer.MAX_VALUE;
            int mejorI = -1, mejorJ = -1;

            List<int[]> candidatos = generarListaVecinos();
            for (int[] par : candidatos) {
                int i = par[0], j = par[1];
                int[] vecino = generarVecino(solucionActual, i, j);
                int nuevoCosto = costoActual + calcularDiferenciaCosto(solucionActual, i, j);

                if (!esTabu(new ElementoTabu(solucionActual[i], solucionActual[j], j, i, 0)) || nuevoCosto < mejorCosto) {
                    if (nuevoCosto < mejorCostoVecino) {
                        mejorVecino = vecino.clone();
                        mejorCostoVecino = nuevoCosto;
                        mejorI = i;
                        mejorJ = j;
                    }
                }
            }

            if (mejorVecino != null) {
                solucionActual = mejorVecino.clone();
                costoActual = mejorCostoVecino;
                actualizarMemoriaFrecuencia(solucionActual);
                agregarElementoTabu(new ElementoTabu(solucionActual[mejorI], solucionActual[mejorJ], mejorJ, mejorI, iteracion + (n / 2)));
                eliminarMovimientosTabuExpirados(iteracion);
                if (costoActual < mejorCosto) {
                    mejorCosto = costoActual;
                    mejorSolucion = solucionActual.clone();
                }
            }

            if (iteracion % iteracionesReinicio == 0 && reinicios < 4) {
                solucionActual = seleccionarMetodoReinicio();
                costoActual = calcularCosto(solucionActual);
                ajustarTamanoListaTabu();
                limpiarListaTabu();  // Reinicio de la lista tabú
                reinicios++;
            }

            iteracion++;
        }
        return mejorSolucion;
    }
private void eliminarMovimientosTabuExpirados(int iteracionLimite) {
    int count = 0;
    
    // Recorremos el array de la lista tabú
    for (int i = 0; i < listaTabu.length; i++) {
        if (listaTabu[i] != null && listaTabu[i].iteracionexpiracion < iteracionLimite) {
            // Si el movimiento ha expirado, lo eliminamos desplazando los elementos hacia la izquierda
            for (int j = i; j < listaTabu.length - 1; j++) {
                listaTabu[j] = listaTabu[j + 1];
            }
            listaTabu[listaTabu.length - 1] = null; // Colocar null al final del array
            count++;
            i--; // Ajustar el índice después de la eliminación
        }
    }
}

    private List<int[]> generarListaVecinos() {
        List<int[]> candidatos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                candidatos.add(new int[]{i, j});
            }
        }
        Collections.shuffle(candidatos, random);
        return candidatos.subList(0, 40);
    }

    private void agregarElementoTabu(ElementoTabu elemento) {
        listaTabu[indiceListaTabu] = elemento;
        indiceListaTabu = (indiceListaTabu + 1) % tamanoListaTabu;
    }

    private boolean esTabu(ElementoTabu elemento) {
        for (ElementoTabu e : listaTabu) {
            if (e != null && e.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    private void actualizarMemoriaFrecuencia(int[] solucion) {
        for (int l = 0; l < n; l++) {
            frec[l][solucion[l]]++;
        }
    }

    private int[] seleccionarMetodoReinicio() {
        double prob = random.nextDouble();
        if (prob < 0.25) return generarSolucionAleatoria();
        else if (prob < 0.75) return generarSolucionDiversificada();
        else return mejorSolucion.clone();
    }

    private void ajustarTamanoListaTabu() {
        tamanoListaTabu = Math.max(1, tamanoListaTabu + (random.nextBoolean() ? tamanoListaTabu / 2 : -tamanoListaTabu / 2));
    }

    private void limpiarListaTabu() {
        listaTabu = new ElementoTabu[tamanoListaTabu];
        indiceListaTabu = 0;
    }

    private int[] generarSolucionAleatoria() {
        List<Integer> permutacion = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            permutacion.add(i);
        }
        Collections.shuffle(permutacion, random);
        return permutacion.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] generarSolucionDiversificada() {
        int[] solucion = new int[n];
        boolean[] asignado = new boolean[n];

        for (int l = 0; l < n; l++) {
            int mejorUnidad = -1, minFrecuencia = Integer.MAX_VALUE;
            for (int u = 0; u < n; u++) {
                if (!asignado[u] && frec[l][u] < minFrecuencia) {
                    minFrecuencia = frec[l][u];
                    mejorUnidad = u;
                }
            }
            solucion[l] = mejorUnidad;
            asignado[mejorUnidad] = true;
        }
        return solucion;
    }

    private int[] generarVecino(int[] solucion, int i, int j) {
        int[] vecino = solucion.clone();
        int temp = vecino[i];
        vecino[i] = vecino[j];
        vecino[j] = temp;
        return vecino;
    }

    private int calcularCosto(int[] solucion) {
        int coste = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coste += D[i][j] * F[solucion[i]][solucion[j]];
            }
        }
        evaluaciones++;
        return coste;
    }

    private int calcularDiferenciaCosto(int[] solucion, int i, int j) {
        int delta = 0;
        for (int k = 0; k < n; k++) {
            if (k != i && k != j) {
                delta += (D[i][k] * (F[solucion[j]][solucion[k]] - F[solucion[i]][solucion[k]]))
                        + (D[j][k] * (F[solucion[i]][solucion[k]] - F[solucion[j]][solucion[k]]))
                        + (D[k][i] * (F[solucion[k]][solucion[j]] - F[solucion[k]][solucion[i]]))
                        + (D[k][j] * (F[solucion[k]][solucion[i]] - F[solucion[k]][solucion[j]]));
            }
        }
        evaluaciones++;
        return delta;
    }

    public int devolverCosto() {
        return mejorCosto;
    }
    public int devolverEvaluaciones() {
        return evaluaciones;
    }


    public class ElementoTabu {

        public int i, j, valori, valorj, iteracionexpiracion;

        // constructor
        public ElementoTabu(int i, int j, int valori, int valorj, int iteracionexpiracion) {
            this.i = i;
            this.j = j;
            this.valori = valori;
            this.valorj = valorj;
            this.iteracionexpiracion = iteracionexpiracion;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true; // Si es el mismo objeto, son iguales
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false; // Si no es de la misma clase, no son iguales
            }
            ElementoTabu that = (ElementoTabu) obj; // Cast a ElementoTabu

            // Comparar los atributos, teniendo en cuenta que (i,j) es igual a (j,i)
            return (this.i == that.i && this.j == that.j && this.valori == that.valori && this.valorj == that.valorj)
                    || (this.i == that.j && this.j == that.i && this.valori == that.valorj && this.valorj == that.valori);
        }

        @Override
        public int hashCode() {
            // Usar un código hash que no dependa del orden de i y j
            return Objects.hash(Math.min(i, j), Math.max(i, j), Math.min(valori, valorj), Math.max(valori, valorj));
        }

    }
}
