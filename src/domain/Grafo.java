package domain;

import services.ArchivoDAO;

import java.util.*;

public class Grafo {
    private Shotgun sgn;
    String nuevoPath = "fragmentos.txt";
    private int numVertices;
    private List<List<Fragmento>> listaAdyacencia;

    // representa un nodo (fragmento) con su peso
    class Fragmento {
        String destino;
        int peso;

        public Fragmento(String destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        listaAdyacencia = new ArrayList<>(numVertices);

        // Inicializar la lista de adyacencia
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new LinkedList<>());
        }
    }

    // apuntar en memoria a shotgun
    public void asignarShotgun(Shotgun shotgun){
        this.sgn = shotgun;
    }

    // agrega nodos al grafo
    public void agregarArista(String origen, String destino, int peso) {
        listaAdyacencia.get(obtenerIndiceNodo(origen)).add(new Fragmento(destino, peso));
        listaAdyacencia.get(obtenerIndiceNodo(destino)).add(new Fragmento(origen, peso));
    }

    private int obtenerIndiceNodo(String nodo) {
        List<String> fragmentosLista = sgn.getListaFragmentos(); // obtener la lista de fragmentos

        for (int i = 0; i < fragmentosLista.size() ; i++) {
            if (fragmentosLista.get(i).equals(nodo)) {
                return i;
            }
        }
        return -1; // El nodo no existe en el grafo
    }

    public void imprimirGrafo() {
        for (int i = 0; i < numVertices; i++) {
            List<Fragmento> lista = listaAdyacencia.get(i);
            System.out.println("Lista de adyacencia del fragmento " + i + ": " );
            for (Fragmento fragmento : lista) {
                System.out.println("-> Nodo: " + fragmento.destino + ", Peso: " + fragmento.peso);
            }
            System.out.println();
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
                if(traslape!=0)
                    agregarArista(fragmento1, fragmento2, traslape);
            }
        }

    }

    // compara el traslape de f1 y f2
    public int compararTraslape(String fragmento1, String fragmento2) {
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




}

