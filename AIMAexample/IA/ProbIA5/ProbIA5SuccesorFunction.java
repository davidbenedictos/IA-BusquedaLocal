package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.Random;
import java.util.ArrayList;

import static java.lang.Math.*;




public class ProbIA5SuccesorFunction implements SuccessorFunction{
    private int generateRandom(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public int distanciaEstaciones(Estacion e1, Estacion e2) {
        return abs(e1.getCoordX() - e2.getCoordX()) + abs(e1.getCoordY() - e2.getCoordY());

    }
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;
        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                    boolean e1Used = padre.rutaIniciaEnEstacion(e1);
                    boolean e2Used = padre.rutaFinalEnEstacion(e2);
                    if (!e1Used && !e2Used) { //HEM DE TENIR EN COMPTE QUE HEM D'ACTUALITZAR EL NUMBICISNEXT
                        for (int bicisR = 0; bicisR <= e1.getNumBicicletasNext() && bicisR <= 30; ++bicisR) {
                            for (int bicisD = 0; bicisD <= bicisR; ++bicisD) {
                                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                                sucesor.añadirFurgoneta(e1, e2, null, bicisR, bicisD);
                                /*retval.add(new Successor("Coste: " + sucesor.getCoste() + " . Estacion inicial " +
                                        e1.getCoordX() + " " + e1.getCoordY() + " Estacion final 1: " +
                                        e2.getCoordX() + " " + e2.getCoordY(), sucesor));*/
                                retval.add(new Successor("Coste: " + sucesor.getCoste() + " . Distancia recorrida: "
                                        + distanciaEstaciones(e1, e2), sucesor));
                            }
                        }
                    }
                }
            }

        }
        /*
        for (IA.ProbIA5.ProbIA5Board.Ruta ruta : padre.getRutas()) {
            for (Estacion newInicial : padre.getEstaciones()) {
                if (!padre.rutaIniciaEnEstacion(newInicial) && newInicial.getNumBicicletasNext() >= ruta.getBicisRecogidas()) {
                    ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste(), ruta);
                    sucesor.cambiarEstacionInicial(ruta, newInicial);
                    retval.add(new Successor("Cambiada estación inicial de: " + ruta.getEstacionInicial().getCoordX() + " "
                            + ruta.getEstacionInicial().getCoordY() + " a: " + newInicial.getCoordX() + " " + newInicial.getCoordY(), sucesor));
                    System.out.println("BUCLE");
                }
            }
        }

         */




        return retval;
        }

}

