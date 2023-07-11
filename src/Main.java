import domain.AssemblyOverlapGraph;
import domain.Shotgun;
import services.ArchivoDAO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "fragmentos.txt";
        Shotgun shotgun = new Shotgun();
        AssemblyOverlapGraph assemblyGraph = new AssemblyOverlapGraph();
        ArchivoDAO archivoDAO = new ArchivoDAO();

        shotgun.fragmentador(8,10);

        assemblyGraph.insertarFragmentos(archivoDAO.contarFragmentosEnArchivo(path));
        assemblyGraph.asignarShotgun(shotgun);

        // Imprimir el grafo
        assemblyGraph.printGraph();

       System.out.println(archivoDAO.contarFragmentosEnArchivo(path));

        System.out.println("-------------");
        assemblyGraph.listaFragmentos();


    }
}