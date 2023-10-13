package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.ArrayList;
import java.util.List;

public class ProbIA5SuccesorFunction implements SuccessorFunction{

    public List getSuccessors(Object state){
        ArrayList retval = new ArrayList();

        ProbIA5Board board = (ProbIA5Board) state;

        ArrayList<ProbIA5Board> sucesores = new ArrayList<>();

        Estaciones estaciones = board.getEstaciones();
        int nbicis = board.getNBicis();
        int nfurgos = board.getNFurgos();
        int tipusdemanda = board.getDemanda();
/*

        for (Estacion e1 : estaciones) {
            for (Estacion e2 : estaciones) {
                // Evitar la misma estación como estación inicial y final
                if (e1.equals(e2)) {
                    continue;
                }

                // Intentar añadir una nueva ruta con diferentes combinaciones de bicicletas recogidas y dejadas
                for (int bicisRecogidas = 0; bicisRecogidas <= nbicis; bicisRecogidas++) {
                    for (int bicisDejadas = 0; bicisDejadas <= nbicis; bicisDejadas++) {
                        // Verificar que las bicicletas recogidas no excedan el límite en la estación e1
                        if (bicisRecogidas <= e1.getNumBicicletasNext()) {
                            // Verificar que las bicicletas dejadas no excedan la demanda en la estación e2
                            if (bicisDejadas <= e2.getDemanda()) {
                                // Crear una copia del estado actual y añadir la nueva ruta
                                ProbIA5Board sucesor = new ProbIA5Board(estaciones, nbicis, nfurgos, tipusdemanda);
                                sucesor.setRutas(board.getRutas()); // Copiar las rutas existentes

                                // Añadir la nueva ruta al sucesor
                                sucesor.añadirFurgoneta(e1, e2, bicisRecogidas, bicisDejadas);

                                // Agregar el sucesor a la lista de sucesores
                                retval.add(sucesor);
                            }
                        }
                    }
                }
            }
        }
*/
        return retval;

    }

}
