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
    Grafo grafo;
    int fragmentos;

    public void menuPrincipal(){

        System.out.println(builderOptions());

        try {
            entradaOpcion();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entradaOpcion() throws IOException {

        int opcion = entrada.nextInt();

        while (opcion >= 1 || opcion < 8) {


            switch (opcion) {
                case 1: // inserta un texto en el archivo
                    archivoDAO.deleteFile(path);

                    System.out.println("INGRESE EL TEXTO EN EL ARCHIVO");
                    entrada.nextLine();
                    String texto = entrada.nextLine().trim();

                    archivoDAO.insertarTexto(path, texto);
                    System.out.println("INSERTADO CON ÉXITO");

                    break;

                case 2: // fragmentar

                    if (archivoDAO.existe(path)) {

                        System.out.println("INGRESE EL NÚMERO DE FRAGMENTOS");
                        fragmentos = entrada.nextInt();
                        System.out.println("INGRESE LA LONGITUD DE CADA FRAGMENTO");
                        int longitud = entrada.nextInt();

                        if(longitud <= archivoDAO.caracteresFile(path) ){
                            archivoDAO.generaArchivo(path);
                            fragmentadorClass = new Shotgun();

                            try {
                                fragmentadorClass.fragmentador(fragmentos, longitud);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else if(longitud > archivoDAO.caracteresFile(path))
                            System.out.println("LA LONGITUD DEBE SER MENOR O IGUAL A " + archivoDAO.caracteresFile(path));
                    } else
                        System.out.println("NO HAY TEXTO PARA FRAGMENTAR");

                    break;



                case 3:
                    List<String> fragmentosOrdenados = archivoDAO.ordenarFragmentosPorLongitud(pathFragmentos);

                    if(!archivoDAO.existe(pathFragmentos)){
                        System.out.println("AUN NO HAY FRAGMENTOS");
                    }else if (fragmentosOrdenados == null || fragmentosOrdenados.size() == 0) {
                        System.out.println("AUN NO HAY FRAGMENTOS");

                    }else {
                        for (String fragmento : fragmentosOrdenados) {
                            System.out.println(fragmento);
                        }
                    }
                    break;

                case 4: // ordena la lista alfabéticamente
                    List<String> listFragmentos = archivoDAO.fragmentosEnArchivo(pathFragmentos);

                    if(!archivoDAO.existe(pathFragmentos)){
                        System.out.println("AUN NO HAY FRAGMENTOS");
                    }else if (listFragmentos == null || listFragmentos.size() == 0) {
                        System.out.println("---LISTA VACÍA---");
                    } else {

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
                    break;


                case 5: // lista de mayor a menor

                    if(!archivoDAO.existe(pathFragmentos)){
                        System.out.println("AUN NO HAY FRAGMENTOS");
                    }else if (fragmentadorClass == null) {
                        System.out.println("LOS FRAGMENTOS AUN NO TIENEN PESO, USE EL FRAGMENTADOR");
                    } else {

                        grafo = new Grafo(fragmentos);
                        grafo.asignarShotgun(fragmentadorClass);
                        grafo.compararFragmentos();
                        grafo.imprimirMenorMayor();
                    }
                    break;

                case 6: // lista de mayor a menor
                    if(!archivoDAO.existe(pathFragmentos)){
                        System.out.println("AUN NO HAY FRAGMENTOS");
                    }else if (fragmentadorClass == null) {
                        System.out.println("LOS FRAGMENTOS AUN NO TIENEN PESO, USE EL FRAGMENTADOR");
                    } else {

                        grafo = new Grafo(fragmentos);
                        grafo.asignarShotgun(fragmentadorClass);
                        grafo.compararFragmentos();
                        grafo.imprimirMayorMenor();
                    }
                    break;


                case 7:
                    System.out.println("INGRESE UN PALABRA CLAVE");
                    entrada.nextLine();
                    String clave = entrada.nextLine();

                    List<String> fragmentosClave = archivoDAO.palabraClaveBuscar(pathFragmentos, clave);

                    if (fragmentosClave.size() != 0) {
                        for (String fr : fragmentosClave) {
                            System.out.println(fr);
                        }
                    } else
                        System.out.println("NO SE ENCONTRÓ ESE FRAGMENTO");
                    break;

                case 8: // reconstruye el texto

                    if(fragmentadorClass == null){
                        System.out.println("EL FRAGMENTADOR NO SE HA INCIADO, USELO PRIMERO");
                    }else{
                        grafo = new Grafo(fragmentos);
                        grafo.asignarShotgun(fragmentadorClass);
                        grafo.obtenerArbolExpansionMinima();

                        System.out.println("--------------------------------------------------------------------------------" + "\n");
                        System.out.println("TEXTO CONSTRUIDO");
                        System.out.println(grafo.construirTextoOriginal());
                        System.out.println("--------------------------------------------------------------------------------" + "\n");
                        System.out.println("TEXTO ORIGINAL");
                        System.out.println(archivoDAO.leerArchivo(path));
                        System.out.println("--------------------------------------------------------------------------------" + "\n");
                    }
                    break;
            }
            // si la entrada es 11 sale completamente
            if (opcion == 9) {
                System.out.println("HA SALIDO DEL MENÚ");
                break;
            }
            System.out.println("\n| | | INGRESE OTRA OPCIÓN | | |");
            System.out.println(builderOptions());
            opcion = entrada.nextInt();
        }
    }

    private String builderOptions(){
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

        return sB.toString();
    }
}
