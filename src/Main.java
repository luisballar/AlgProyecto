import domain.Grafo;
import domain.Shotgun;
import services.ArchivoDAO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "fragmentos.txt";
        Shotgun shotgun = new Shotgun();
        ArchivoDAO archivoDAO = new ArchivoDAO();

        shotgun.fragmentador(10,10);
        Grafo grafo = new Grafo(10);

        grafo.asignarShotgun(shotgun);
        grafo.compararFragmentos();
        grafo.imprimirGrafo();

    }
}

