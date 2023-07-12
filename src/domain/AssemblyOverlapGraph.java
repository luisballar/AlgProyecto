/*
package domain;

import services.ArchivoDAO;

import java.util.*;

class Node{
    String sequence;
    List<Integer> edges;

    public Node(String sequence) {
        this.sequence = sequence;
        this.edges = new ArrayList<>();
    }

}

public class AssemblyOverlapGraph {
    private Shotgun sgn;
    String nuevoPath = "fragmentos.txt";
    List<Node> graph;

    public AssemblyOverlapGraph() {
        graph = new ArrayList<>();
    }

    // apunta a memoria a shotgun
    public void asignarShotgun(Shotgun shotgun){
        this.sgn = shotgun;
    }

    public void addNode(String sequence) {
        graph.add(new Node(sequence));
    }

    public void addEdge(int fromNodeIndex, int toNodeIndex) {
        graph.get(fromNodeIndex).edges.add(toNodeIndex);
    }

    // imprime el grafo
    public void printGraph() {
        for (int i = 0; i < graph.size(); i++) {
            Node node = graph.get(i);
            System.out.println("Node " + i + ": " + node.sequence);
            System.out.print("Edges: ");
            for (Integer edge : node.edges) {
                System.out.print(edge + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    // devuelve los fragmentos del archivo
    public String devuelveFragmentos(){
        StringBuilder fragmentos = new StringBuilder(new ArchivoDAO().leerArchivo(nuevoPath));

        return fragmentos.toString();
    }

    public void listaFragmentos(){
        List<String> fragmentosLista = sgn.getListaFragmentos(); // Obtener la lista de fragmentos

        for (int i = 0; i < fragmentosLista.size(); i++) {
            String fragmento1 = fragmentosLista.get(i);

            for (int j = i + 1; j < fragmentosLista.size(); j++) {
                String fragmento2 = fragmentosLista.get(j);

                // Comparar fragmento1 con fragmento2
                int traslape = compararTraslape(fragmento1, fragmento2);
                System.out.println("Traslape entre fragmento " + i + " y fragmento " + j + ": " + traslape);
            }
        }
    }

    // compara fragmentos
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


    // inserta los fragmentos en el grafo
    public void insertarFragmentos(int cantFragmentos) {
        String fragmentosTotales = devuelveFragmentos();
        StringTokenizer tokenizer = new StringTokenizer(fragmentosTotales, "\n");

        // insertar
        for (int i = 0; i < cantFragmentos; i++) {
            String fragmento = tokenizer.nextToken();
            addNode(fragmento);
            addEdge(i, i+1);
        }
    }

}

 */
