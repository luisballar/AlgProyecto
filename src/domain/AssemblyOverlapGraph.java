package domain;

import services.ArchivoDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Node {
    String sequence;
    List<Integer> edges;

    public Node(String sequence) {
        this.sequence = sequence;
        this.edges = new ArrayList<>();
    }

}

public class AssemblyOverlapGraph {
    String nuevoPath = "fragmentos.txt";
    List<Node> graph;

    public AssemblyOverlapGraph() {
        graph = new ArrayList<>();
    }

    public void addNode(String sequence) {
        graph.add(new Node(sequence));
    }

    public void addEdge(int fromNodeIndex, int toNodeIndex) {
        graph.get(fromNodeIndex).edges.add(toNodeIndex);
    }

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

    public static void main(String[] args) {
        AssemblyOverlapGraph assemblyGraph = new AssemblyOverlapGraph();
        ArchivoDAO archivoDAO = new ArchivoDAO();

        assemblyGraph.insertarFragmentos(archivoDAO.contarFragmentosEnArchivo("fragmentos.txt"));

        // Imprimir el grafo
        assemblyGraph.printGraph();

        System.out.println(archivoDAO.contarFragmentosEnArchivo("fragmentos.txt"));

    }
}
