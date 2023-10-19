package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class ProbIA5SuccesorFunction implements SuccessorFunction{
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;

        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                if (!e1.equals(e2) && padre.getNRutas() <= padre.getNFurgos()) {
                    /*
                    for (int bicisR = 0; bicisR < e1.getNumBicicletasNext() - e1.getDemanda(); ++bicisR) {
                        for (int bicisD = 0; bicisD <= bicisR && bicisD < e2.getDemanda() - e2.getNumBicicletasNext(); ++bicisD) {
                    */
                    for (int bicisR = 0; bicisR < e1.getNumBicicletasNext() && bicisR < 30; ++bicisR) {
                        for (int bicisD = 0; bicisD < bicisR; ++bicisD) {
                            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                            sucesor.añadirFurgoneta(e1, e2, bicisR, bicisD);
                            retval.add(new Successor("Furgoneta añadida", sucesor));
                        }
                    }

                    /*
                    if (e1.getNumBicicletasNext() - e1.getDemanda() > 0) {
                        ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                        int bicisRecogidas = e1.getNumBicicletasNext() - e1.getDemanda();
                        sucesor.añadirFurgoneta(e1, e2, bicisRecogidas, bicisRecogidas);
                        retval.add(new Successor("Furgoneta añadida", sucesor));

                    }*/
                }
            }
        }
        return retval;
    }
}
