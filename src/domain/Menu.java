package domain;

import services.ArchivoDAO;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    String path = "texto.txt";
    String pathFragmentos = "fragmentos.txt";
    Shotgun fragmentadorClass;
    ArchivoDAO archivoDAO = new ArchivoDAO();
    Scanner entrada = new Scanner(System.in);

    public void menuPrincipal(){
        StringBuilder sB = new StringBuilder("Menu Principal")
                .append("\n Elija una opción:")
                .append("\n Opción 1: INSERTAR TEXTO")
                .append("\n Opción 2: FRAGMENTAR TEXTO")
                .append("\n Opción 3: MOSTRAR FRAGMENTOS POR LARGO DE HILERA")
                .append("\n Opción 4: MOSTAR FRAGMENTOS EN ORDEN ALFABÉTICO")
                .append("\n Opción 5: MOSTRAR FRAGMENTOS DE MENOR A MAYOR")
                .append("\n Opción 6: MOSTRAR FRAGMENTOS DE MAYOR A MENOR")
                .append("\n Opción 7: MOSTRAR FRAGMENTOS POR PALABRA CLAVE")
                .append("\n Opción 8: RECONSTRUIR TEXTO")
                .append("\n Opción 9: Salir\n");

        System.out.println(sB.toString());
        entradaOpcion();
    }

    public void entradaOpcion(){

        int opcion= entrada.nextInt();

        while(opcion>=1 || opcion<8) {


            switch (opcion) {
                case 1: // inserta un texto en el archivo
                    archivoDAO.deleteFile(path);

                    System.out.println("Ingrese el texto en el archivo");
                    entrada.nextLine();
                    String texto = entrada.nextLine();

                    archivoDAO.insertarTexto(path, texto.trim());
                    break;

                case 2: // fragmentar
                    System.out.println("Ingrese el número de fragmentos");
                    int fragmentos = entrada.nextInt();
                    System.out.println("Ingrese la longitud de cada fragmento");
                    int longitud = entrada.nextInt();

                    archivoDAO.generaArchivo(path);
                    fragmentadorClass = new Shotgun();

                    try {
                        fragmentadorClass.fragmentador(fragmentos, longitud);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                case 3:












                case 4: // ordena la lista alfabéticamente
                    List<String> listFragmentos = archivoDAO.fragmentosEnArchivo(pathFragmentos);

                    if (listFragmentos == null || listFragmentos.size() == 0) {
                        System.out.println("---LISTA VACÍA---");
                    }else{

                        // elimina espacios en blanco al principio y al final de cada elemento
                        for (int i = 0; i < listFragmentos.size(); i++) {
                            String fragmento = listFragmentos.get(i);
                            listFragmentos.set(i, fragmento.trim());
                        }

                        Collections.sort(listFragmentos);
                        System.out.println("LISTA ORDENADA ALFABÉTICAMENTE:" + "\n");

                        for (String sequence : listFragmentos) {
                            System.out.println(sequence);
                        }
                    }
            }
            // si la entrada 11 sale completamente
            if (opcion == 8) {
                System.out.println("HA SALIDO DEL MENÚ");
                break;
            }

            System.out.println("\n| | | INGRESE OTRA OPCIÓN | | |");
            opcion = entrada.nextInt();
        }



    }





}
