/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class ArchivoDAO {

    // crea el archivo en una ruta
    public void generaArchivo(String path){
        File archivo = new File(path);

        if(!archivo.exists()) {
            try {
                FileWriter imprimir = new FileWriter(archivo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else
            System.out.println("ARCHIVO EXISTE");
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

    // cuenta los fragmentos del archivo
    public int contarFragmentosEnArchivo(String filePath) {
        int contador = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                // Verificar si la línea no está vacía
                if (!linea.trim().isEmpty()) {
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contador;
    }

    // devuelve los fragmentos del archivo
    public List<String> fragmentosEnArchivo(String filePath) {
        File file = new File(filePath);
        List<String> tokens = new ArrayList<>();

        if(!file.exists())
            return tokens; // retorna la lista vacía
        else{

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(linea);

                    if (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken();
                        tokens.add(token);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return tokens;
    }


    // borra el archivo
    public void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }


}
