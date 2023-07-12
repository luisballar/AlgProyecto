import domain.Grafo;
import domain.Shotgun;
import services.ArchivoDAO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "fragmentos.txt";
        Shotgun shotgun = new Shotgun();
        ArchivoDAO archivoDAO = new ArchivoDAO();

        // crear archivo
        archivoDAO.generaArchivo("texto.txt");

        int var = 100;

        shotgun.fragmentador(var,5);
        Grafo grafo = new Grafo(var);

        grafo.asignarShotgun(shotgun);
        grafo.compararFragmentos();
        grafo.imprimirGrafo();

    }
}

