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
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;
        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                    boolean e1Used = padre.rutaIniciaEnEstacion(e1);
                    if (!e1Used) {
                        for (int bicisR = 0; bicisR <= padre.getBicisNext(e1.getCoordX(), e1.getCoordY()) && bicisR <= 30; ++bicisR) {
                            for (int bicisD = 0; bicisD <= bicisR; ++bicisD) {
                                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getBicisNext(), padre.getCoste());
                                sucesor.añadirFurgoneta(e1, e2, null, bicisR, bicisD);
                                retval.add(new Successor("C: " + sucesor.getCoste() +
                                        ". eIni " + e1.getCoordX() + " " + e1.getCoordY()
                                        + ". eFin1 " + e2.getCoordX() + " " + e2.getCoordY()
                                        + ". D1, N1: " + e1.getDemanda() + " " + sucesor.getBicisNext(e1.getCoordX(), e1.getCoordY())
                                        + ". bR, bD: " + bicisR + " " + bicisD
                                        + ". D2, N2: " + + e2.getDemanda() + " " + sucesor.getBicisNext(e2.getCoordX(), e2.getCoordY())
                                        + " . E2 BN Original: " + e2.getNumBicicletasNext(), sucesor));
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
                    System.out.println("EStacio inicial anterior: " + ruta.getEstacionInicial().getCoordX() + " " + ruta.getEstacionInicial().getCoordY()
                    + " a estacion inicial " + newInicial.getCoordX() + " " + newInicial.getCoordY());
                    retval.add(new Successor("Cambiada estación inicial de: " + ruta.getEstacionInicial().getCoordX() + " "
                            + ruta.getEstacionInicial().getCoordY() + " a: " + newInicial.getCoordX() + " " + newInicial.getCoordY(), sucesor));
                }
            }
        }
        */
        /*
        for (ProbIA5Board.Ruta ruta : padre.getRutas()) {
            for (ProbIA5Board.Ruta ruta2: padre.getRutas()) {
                if (!ruta.equals(ruta2)) {
                    //fer intercambiar
                }
            }
        }*/

        return retval;
        }

}

