/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.*;
import java.util.*;


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
        }
    }

    // escribe en el archivo
    public void insertarTexto(String path, String textoGenerado){
        File archivo = new File(path);

        try {
            FileWriter imprimir = new FileWriter(archivo, false);
            imprimir.append(textoGenerado);
            imprimir.close();
            //System.out.println("Se agregó el texto");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String leerArchivo(String path){
        StringBuilder sb =  new StringBuilder();
        File archivo = new File(path);

        if(existe(path) == true){
            try (BufferedReader rd = new BufferedReader(new FileReader(archivo))){
                String ln;
                while((ln = rd.readLine()) != null){
                    sb.append(ln).append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return sb.toString();

        }else{
            return "El archivo no existe";
        }


    }

    // cuenta los fragmentos del archivo
    public int contarFragmentosEnArchivo(String filePath) {
        int contador = 0;

        if(existe(filePath)){
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

        }else
            return 0;

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
                    StringTokenizer tokenizer = new StringTokenizer(linea, "\n");

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

    public List<String> palabraClaveBuscar(String path, String palabraClave) {
        List<String> fragmentosEncontrados = new ArrayList<>();

        List<String> fragmentos = fragmentosEnArchivo(path);

        for (String fragmento : fragmentos) {
            if (fragmento.contains(palabraClave)) {
                fragmentosEncontrados.add(fragmento);
            }
        }

        return fragmentosEncontrados;
    }

    // cuenta palabras en el archivo
    public int contarPalabrasEnArchivo(String filePath) {
        int contador = 0;

        if (existe(filePath)) {
            String contenido = leerArchivo(filePath);
            StringTokenizer tokenizer = new StringTokenizer(contenido);

            while (tokenizer.hasMoreTokens()) {
                tokenizer.nextToken();
                contador++;
            }
        }

        return contador;
    }


    // ordena fragmentos por longitud
    public List<String> ordenarFragmentosPorLongitud(String filePath) {
        List<String> fragmentos = fragmentosEnArchivo(filePath);

        // Ordenar los fragmentos por longitud en orden descendente
        Collections.sort(fragmentos, Comparator.comparingInt(String::length).reversed());

        return fragmentos;
    }

    public int caracteresFile(String path) throws IOException {
        String caracteres = leerArchivo(path);
        return caracteres.length();
    }


    // borra el archivo
    public void deleteFile(String path){
        File file = new File(path);
        file.delete();
    }

    public boolean existe(String path){
        File file = new File(path);

        if(file.exists())
            return true;
        else
            return false;

    }

}
