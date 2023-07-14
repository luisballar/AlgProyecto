/*
package domain;


import java.util.*;

class Arista implements Comparable<Arista> {
    int origen, destino, peso;

    public int compareTo(Arista arista) {
        return this.peso - arista.peso;
    }
}

class MainKruskal {
    int vertices;
    ArrayList<Arista> todasLasAristas = new ArrayList<>();

    MainKruskal(int vertices) {
        this.vertices = vertices;
    }

    void agregarArista(int origen, int destino, int peso) {
        Arista arista = new Arista();
        arista.origen = origen;
        arista.destino = destino;
        arista.peso = peso;
        todasLasAristas.add(arista);
    }

    void kruskalMST(MainKruskal mainKruskal) {
        // Crear una estructura de conjunto para almacenar los subconjuntos de vértices
        DisjointSet disjointSet = new DisjointSet(vertices);

        // Ordenar las aristas por peso
        Collections.sort(mainKruskal.todasLasAristas);

        ArrayList<Arista> mst = new ArrayList<>();

        for (Arista arista : mainKruskal.todasLasAristas) {
            int conjuntoOrigen = disjointSet.find(arista.origen);
            int conjuntoDestino = disjointSet.find(arista.destino);

            if (conjuntoOrigen != conjuntoDestino) {
                // Si los vértices de la arista no están en el mismo conjunto, no formarán un ciclo
                mst.add(arista);
                disjointSet.union(conjuntoOrigen, conjuntoDestino);
            }
        }

        System.out.println("Árbol de Expansión Mínima:");
        for (Arista arista : mst) {
            System.out.println(arista.origen + " - " + arista.destino + " : " + arista.peso);
        }
    }
}

class DisjointSet {
    int[] padre;

    DisjointSet(int n) {
        padre = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
        }
    }

    int find(int x) {
        if (padre[x] != x) {
            padre[x] = find(padre[x]);
        }
        return padre[x];
    }

    void union(int x, int y) {
        int conjuntoX = find(x);
        int conjuntoY = find(y);
        padre[conjuntoY] = conjuntoX;
    }
}

 */

