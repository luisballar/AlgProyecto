package domain;


import services.ArchivoDAO;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Shotgun {
    private int totalChars;
    private int fragmentos;
    private int longitud;
    private String caracteres;
    private LinkedList<String> listaFragmentos = new LinkedList<String>();
    ArchivoDAO archivoDAO = new ArchivoDAO();
    String path = "texto.txt";
    String nuevoPath = "fragmentos.txt";


    // cuenta los caracteres del archivo
    public int totalCaracteres() throws IOException {
        caracteres = archivoDAO.leerArchivo(path);

        return caracteres.length();
    }

    // retorna random entre 0 y la cantidad de caracteres del archivo
    private int caracteresRandom() throws IOException {
        Random random = new Random();

        int limite = totalCaracteres() - (longitud + 1);

        return random.nextInt(limite); // 0 a contadorCaracteres - 1;
    }

    // random que oscila entre longitud-1 y longitud+1
    public int randomLongitud(int longitud){
        Random random = new Random();

        int logitudPromedio = random.nextInt(3) + (longitud-1);

        return logitudPromedio;
    }

    // fragmentador
    public void fragmentador(int cantFragmentos, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();

        this.longitud = longitud;

        for (int i = 0; i < cantFragmentos; i++) {
            int inicioFragmento = caracteresRandom();
            String nuevoFragmento = caracteres.substring(inicioFragmento, inicioFragmento + randomLongitud(longitud));

            // agrega el fragmento a un StringBuilder
            sb.append(nuevoFragmento).append("\n");
            // agrega el fragmento a una lista
            listaFragmentos.add(nuevoFragmento);
        }

        // agregar al archivo
        archivoDAO.insertarTexto(nuevoPath, sb.toString());
    }

}
