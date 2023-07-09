/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.*;


public class ArchivoDAO {

    // crea el archivo en una ruta
    public void generaArchivo(String path){
        File archivo = new File(path);
        try {
            FileWriter imprimir = new FileWriter(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // escribe en el archivo
    public void insertarTexto(String path, String textoGenerado){
        File archivo = new File(path);

        try {
            FileWriter imprimir = new FileWriter(archivo, false);
            imprimir.append(textoGenerado);
            imprimir.close();
            System.out.println("Se agregó el texto");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    //Método general para leer un archivo
    public BufferedReader getReadFile(String nameFile) throws FileNotFoundException {
        File file = new File(nameFile);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
          return br;
    }

     */

    public String leerArchivo(String path){
        StringBuilder sb =  new StringBuilder();
        File archivo = new File(path);

        try (BufferedReader rd = new BufferedReader(new FileReader(archivo))){
            String ln;
            while((ln = rd.readLine()) != null){
                sb.append(ln).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
