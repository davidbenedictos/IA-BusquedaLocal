package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ProbIA5SuccesorFunction implements SuccessorFunction{

    public List getSuccessors(Object state) {
        List retval = new ArrayList();

        ProbIA5Board board = (ProbIA5Board) state;

        //ArrayList<ProbIA5Board> sucesores = new ArrayList<>();
      //  System.out.println(board.getRutas().size());
        int i = 0;
        int j = 0;
        for (Estacion e1 : board.getEstaciones()) {
            j = 0;
            for (Estacion e2 : board.getEstaciones()) {
                // Evitar la misma estación como estación inicial y final

                if (!e1.equals(e2)) {
                    // Intentar añadir una nueva ruta con diferentes combinaciones de bicicletas recogidas y dejadas
                    for (int bicisRecogidas = 0; bicisRecogidas <= e1.getNumBicicletasNext() && bicisRecogidas <= 30; bicisRecogidas++) {
                        for (int bicisDejadas = 0; bicisDejadas <= bicisRecogidas; bicisDejadas++) {
                            // Crear una copia del estado actual y añadir la nueva ruta
                            ProbIA5Board sucesor = new ProbIA5Board(board.getEstaciones(), board.getNBicis(), board.getNFurgos(), board.getRutas(), board.getCoste()*-1);
                            //ArrayList<>(ProbIA5Board.Ruta) aux = new ArrayList<>(board.getRutas());
                            //  ArrayList<ProbIA5Board.Ruta> aux = new ArrayList<>(board.getRutas());
                           // sucesor.setRutas(aux); // Copiar las rutas existentes

                            // Añadir la nueva ruta al sucesor
                            sucesor.añadirFurgoneta(e1, e2, bicisRecogidas, bicisDejadas);
                            // Agregar el sucesor a la lista de sucesores


                            String S = "Furgo añadido desde estacion i : " + i + " . Hasta estacion j: " + j + " con coste: "+ sucesor.getCoste() + " " + sucesor.getRutas().size();
                            retval.add(new Successor(S, sucesor));

                        }
                    }
                }
                ++j;
            }
            ++i;
        }
       // System.out.println(board.getRutas().size());
        return retval;
    }
}
