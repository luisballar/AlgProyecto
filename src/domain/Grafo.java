package domain;

import domain.Shotgun;
import services.ArchivoDAO;

import java.util.*;

public class Grafo {
    private Shotgun sgn;
    String nuevoPath = "fragmentos.txt";
    private int numVertices;
    private List<List<Fragmento>> listaAdyacencia;

    // representa un nodo (fragmento)
    public class Fragmento {
        String destino;
        String origen;
        int peso;

        public Fragmento(String origen, String destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }

        public int getPeso() {
            return peso;
        }

        public String getDestino() {
            return destino;
        }
    }

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new ArrayList<>(numVertices);

        // inicializa la lista de adyacencia
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new LinkedList<>());
        }
    }

    // apunta en memoria a shotgun
    public void asignarShotgun(Shotgun shotgun) {
        this.sgn = shotgun;
    }

    // agrega nodos al grafo
    public void agregarArista(String origen, String destino, int peso) {
        int indiceOrigen = obtenerIndiceNodo(origen);
        int indiceDestino = obtenerIndiceNodo(destino);

        // si los nodos existen
        if (indiceOrigen != -1 && indiceDestino != -1) {
            List<Fragmento> listaOrigen = listaAdyacencia.get(indiceOrigen);
            List<Fragmento> listaDestino = listaAdyacencia.get(indiceDestino);

            Fragmento nuevaAristaOrigen = new Fragmento(origen, destino, peso);
            Fragmento nuevaAristaDestino = new Fragmento(destino, origen, peso);

            if (!listaOrigen.contains(nuevaAristaOrigen)) {
                listaOrigen.add(nuevaAristaOrigen);
            }

            if (!listaDestino.contains(nuevaAristaDestino)) {
                listaDestino.add(nuevaAristaDestino);
            }
        }
    }

    private int obtenerIndiceNodo(String nodo) {
        List<String> fragmentosLista = sgn.getListaFragmentos(); // obtener la lista de fragmentos

        for (int i = 0; i < fragmentosLista.size(); i++) {
            if (fragmentosLista.get(i).equals(nodo)) {
                return i;
            }
        }
        return -1; // el nodo no existe en el grafo
    }

    // imprime el grafo conexo
    public void imprimirGrafoConexo() {
        for (int i = 0; i < numVertices; i++) {
            List<Fragmento> lista = listaAdyacencia.get(i);
            System.out.println("Lista de adyacencia del fragmento " + i + ": ");
            for (Fragmento fragmento : lista) {
                System.out.println("-> Nodo: " + fragmento.destino + ", Peso: " + fragmento.peso);
            }
            System.out.println();
        }
    }

    // imprime la lista de fragmentos de mayor a menor segun su peso
    public void imprimirMayorMenor() {
        List<Fragmento> fragmentosOrdenados = new ArrayList<>();

        for (List<Fragmento> lista : listaAdyacencia) {
            fragmentosOrdenados.addAll(lista);
        }

        fragmentosOrdenados.sort((f1, f2) -> Integer.compare(f2.getPeso(), f1.getPeso()));

        System.out.println("Lista de fragmentos ordenados por peso (de mayor a menor):");
        for (Fragmento fragmento : fragmentosOrdenados) {
            System.out.println("-> Nodo: " + fragmento.getDestino() + ", Peso: " + fragmento.getPeso());
        }
    }


    // imprime la lista de fragmentos de menor a mayor segun su peso
    public void imprimirMenorMayor() {
        List<Fragmento> fragmentosOrdenados = new ArrayList<>();

        for (List<Fragmento> lista : listaAdyacencia) {
            fragmentosOrdenados.addAll(lista);
        }

        fragmentosOrdenados.sort(Comparator.comparingInt(Fragmento::getPeso));

        System.out.println("Lista de fragmentos ordenados por peso (de menor a mayor):");
        for (Fragmento fragmento : fragmentosOrdenados) {
            System.out.println("-> Nodo: " + fragmento.getDestino() + ", Peso: " + fragmento.getPeso());
        }
    }

    // devuelve los fragmentos del archivo
    public String devuelveFragmentos() {
        StringBuilder fragmentos = new StringBuilder(new ArchivoDAO().leerArchivo(nuevoPath));

        return fragmentos.toString();
    }

    // saca un fragmento de la lista y lo compara con el siguiente
    public void compararFragmentos() {
        List<String> fragmentosLista = sgn.getListaFragmentos(); // Obtener la lista de fragmentos

        for (int i = 0; i < fragmentosLista.size(); i++) {
            String fragmento1 = fragmentosLista.get(i);

            for (int j = i + 1; j < fragmentosLista.size(); j++) {
                String fragmento2 = fragmentosLista.get(j);

                // Comparar fragmento1 con fragmento2
                int traslape = compararTraslape(fragmento1, fragmento2);
                //System.out.println("Traslape entre fragmento " + i + " y fragmento " + j + ": " + traslape);

                //si el traslape es 0 no los agrega al grafo
                if (traslape != 0)
                    agregarArista(fragmento1, fragmento2, traslape);
            }
        }

    }

    // compara el traslape de f1 y f2
    private int compararTraslape(String fragmento1, String fragmento2) {
        int traslape = 0;

        // Comparar cada subcadena de fragmento1 con fragmento2
        for (int i = 0; i < fragmento1.length(); i++) {
            String subcadena = fragmento1.substring(i);

            if (fragmento2.startsWith(subcadena)) {
                traslape = subcadena.length();
                break;
            }
        }

        return traslape;
    }

    // Algoritmo de Kruskal para encontrar el árbol de expansión mínima con los nodos de mayor peso
    public List<Fragmento> kruskalMST() {
        PriorityQueue<Fragmento> pq = new PriorityQueue<>(new Comparator<Fragmento>() {
            public int compare(Fragmento f1, Fragmento f2) {
                return f2.peso - f1.peso; // orden descendente por peso
            }
        });

        // Agregar todas las aristas a la cola de prioridad
        for (int i = 0; i < numVertices; i++) {
            List<Fragmento> lista = listaAdyacencia.get(i);
            for (Fragmento fragmento : lista) {
                pq.offer(fragmento);
            }
        }

        // estructura de conjunto para verificar ciclos
        DisjointSet disjointSet = new DisjointSet(numVertices);

        List<Fragmento> mst = new ArrayList<>();

        while (!pq.isEmpty()) {
            Fragmento fragmento = pq.poll();
            int conjuntoOrigen = disjointSet.find(obtenerIndiceNodo(fragmento.origen));
            int conjuntoDestino = disjointSet.find(obtenerIndiceNodo(fragmento.destino));

            if (conjuntoOrigen != conjuntoDestino) {
                // No hay ciclo, se agrega la arista al árbol de expansión mínima
                mst.add(fragmento);
                disjointSet.union(conjuntoOrigen, conjuntoDestino);
            }
        }

        // Imprimir el árbol de expansión mínima
        System.out.println("Árbol de Expansión Mínima (nodos de mayor peso):");
        for (Fragmento fragmento : mst) {
            System.out.println("-> Nodo: " + fragmento.destino + ", Peso: " + fragmento.peso);
        }

        return mst;
    }

    // clase auxiliar para representar la estructura de conjunto disjunto
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

    // devuelve los fragmentos del grafo ordenados de mayor a menor por peso
    public List<Fragmento> obtenerFragmentosOrdenados() {
        List<Fragmento> mst = kruskalMST();
        List<Fragmento> fragmentosOrdenados = new ArrayList<>();

        // Agregar los primeros fragmentos del árbol de expansión mínima a la lista ordenada
        fragmentosOrdenados.add(mst.get(0));

        for (int i = 1; i < mst.size(); i++) {
            Fragmento fragmentoActual = mst.get(i);
            int index = 0;

            // Buscar el índice donde debe insertarse el fragmento actual en la lista ordenada
            while (index < fragmentosOrdenados.size() && fragmentoActual.peso < fragmentosOrdenados.get(index).peso) {
                index++;
            }

            // Insertar el fragmento actual en la posición adecuada
            fragmentosOrdenados.add(index, fragmentoActual);
        }

        return fragmentosOrdenados;
    }

    public String construirTextoOriginal() {
        List<Fragmento> fragmentosOrdenados = obtenerFragmentosOrdenados();
        StringBuilder texto = new StringBuilder();

        // Agregar el primer fragmento al texto
        Fragmento primerFragmento = fragmentosOrdenados.get(0);
        texto.append(primerFragmento.origen).append(primerFragmento.destino.substring(primerFragmento.peso));

        // Construir el texto iterando sobre los fragmentos ordenados
        for (int i = 1; i < fragmentosOrdenados.size(); i++) {
            Fragmento fragmento = fragmentosOrdenados.get(i);

            // Verificar si el origen ya está presente en el texto
            if (texto.toString().contains(fragmento.origen)) {
                // Agregar el sufijo del destino al texto
                texto.append(fragmento.destino.substring(fragmento.peso));
            } else {
                // Agregar el sufijo del origen al texto
                texto.append(fragmento.origen.substring(fragmento.peso));
            }
        }

        return texto.toString();
    }

    // Obtener el árbol de expansión mínima como lista de fragmentos
    public List<Fragmento> obtenerArbolExpansionMinima() {
        compararFragmentos();
        return kruskalMST();
    }
}
