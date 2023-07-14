import domain.Grafo;
import domain.Menu;
import domain.Shotgun;
import services.ArchivoDAO;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        String path = "fragmentos.txt";
        Shotgun shotgun = new Shotgun();
        ArchivoDAO archivoDAO = new ArchivoDAO();

        // crear archivo
        archivoDAO.generaArchivo("texto.txt");

        int var = 10;

        shotgun.fragmentador(var,13);
        Grafo grafo = new Grafo(var);

        grafo.asignarShotgun(shotgun);
        grafo.compararFragmentos();

        // imprimir grafo conexo
        //grafo.imprimirGrafo();

        // imprimir grafo kruskal
        grafo.kruskalMST();

        String textoCompleto = grafo.construirTextoOriginal();
        System.out.println("\n");
        System.out.println("Texto completo: " + textoCompleto);


         */



       Menu menu = new Menu();
       menu.menuPrincipal();

    }
}

