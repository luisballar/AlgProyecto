package domain;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Shotgun fragmentadorClass;
    Scanner entrada = new Scanner(System.in);


    public void menuPrincipal(){
        StringBuilder sB = new StringBuilder("Menu Principal")
                .append("\n Elija una opción:")
                .append("\n Opción 1: FRAGMENTAR TEXTO")
                .append("\n Opción 2: MOSTRAR FRAGMENTOS POR LARGO DE HILERA")
                .append("\n Opción 3: MOSTAR FRAGMENTOS EN ORDEN ALFABÉTICO")
                .append("\n Opción 4: MOSTRAR FRAGMENTOS DE MENOR A MAYOR")
                .append("\n Opción 5: MOSTRAR FRAGMENTOS DE MAYOR A MENOR")
                .append("\n Opción 6: MOSTRAR FRAGMENTOS POR PALABRA CLAVE")
                .append("\n Opción 6: RECONSTRUIR TEXTO")
                .append("\n Opción 7: Salir\n");

        System.out.println(sB.toString());
        entradaOpcion();
    }

    public void entradaOpcion(){

        int opcion= entrada.nextInt();

        while(opcion>=1 || opcion<7) {


            switch (opcion) {
                case 1: // fragmentar
                    System.out.println("Ingrese el número de fragmentos");
                    int fragmentos = entrada.nextInt();
                    System.out.println("Ingrese la longitud de cada fragmento");
                    int longitud = entrada.nextInt();

                    fragmentadorClass = new Shotgun();

                    try {
                        fragmentadorClass.fragmentador(fragmentos, longitud);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                case 2: // ordena la lista alfabéticamente

                    if (fragmentadorClass == null) {
                        System.out.println("---LISTA VACÍA---");
                    } else {
                        List<String> listFragmentos = fragmentadorClass.getListaFragmentos();

                        // elimina espacios en blanco al principio y al final de cada elemento
                        for (int i = 0; i < listFragmentos.size(); i++) {
                            String fragmento = listFragmentos.get(i);
                            listFragmentos.set(i, fragmento.trim());
                        }

                        Collections.sort(listFragmentos);

                        for (String sequence : listFragmentos) {
                            System.out.println(sequence);
                        }
                    }
            }
            // si la entrada 11 sale completamente
            if (opcion == 7) {
                System.out.println("HA SALIDO DEL MENÚ");
                break;
            }

            System.out.println("\n| | | INGRESE OTRA OPCIÓN | | |");
            opcion = entrada.nextInt();
        }



    }





}
