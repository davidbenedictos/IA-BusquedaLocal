package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.Random;
import java.util.ArrayList;



public class ProbIA5SuccesorFunction implements SuccessorFunction{
    private int generateRandom(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;
        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                    boolean e1Used = padre.rutaIniciaEnEstacion(e1);
                    boolean e2Used = padre.rutaFinalEnEstacion(e2);
                    if (!e1Used && !e2Used) {
                        for (int bicisR = 0; bicisR <= e1.getNumBicicletasNext() && bicisR <= 30; ++bicisR) {
                            for (int bicisD = 0; bicisD <= bicisR; ++bicisD) {
                                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                                sucesor.añadirFurgoneta(e1, e2, null, bicisR, bicisD);
                                //HEM DE TENIR EN COMPTE QUE HEM D'ACTUALITZAR EL NUMBICISNEXT

                                retval.add(new Successor("BR " + bicisR + ". BD1 " + bicisD + ". BD2 " + (bicisR - bicisD)
                                        + ". Coste " + sucesor.getCoste() + ". BicisNext, Demanda: "
                                        + e1.getNumBicicletasNext() + " " + e1.getDemanda() + " " + e2.getNumBicicletasNext()
                                        + " " + e2.getDemanda(), sucesor));
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

