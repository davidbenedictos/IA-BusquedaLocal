package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;

import java.util.ArrayList;



public class ProbIA5SuccesorFunction implements SuccessorFunction{
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;
        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                if (!e1.equals(e2) && padre.getNRutas() <= padre.getNFurgos()) {
                    for (int bicisR = 0; bicisR < e1.getNumBicicletasNext() && bicisR < 30; ++bicisR) {
                        for (int bicisD = 0; bicisD < bicisR; ++bicisD) {
                            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                            sucesor.añadirFurgoneta(e1, e2, bicisR, bicisD);
                            retval.add(new Successor("Furgoneta añadida", sucesor));
                        }
                    }
                }
            }
        }

        for (ProbIA5Board.Ruta r1: padre.getRutas()) {
            for (Estacion e1 : padre.getEstaciones()) {
                if (!e1.equals(r1.getEstacionInicial()) && !e1.equals(r1.getEstacionFinal1())) {
                    if (r1.getEstacionFinal2() != null &&  !e1.equals(r1.getEstacionFinal2())) {
                        ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                        sucesor.cambiarEstacionInicial(r1, e1);
                        retval.add(new Successor("Estación inicial cambiada", sucesor));
                    }
                }
            }
        }

        for (ProbIA5Board.Ruta r1: padre.getRutas()) {
            for (Estacion e1 : padre.getEstaciones()) {
                if (!e1.equals(r1.getEstacionInicial()) && !e1.equals(r1.getEstacionFinal1())) {
                    if (r1.getEstacionFinal2() != null &&  !e1.equals(r1.getEstacionFinal2())) {
                        ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                        sucesor.cambiarEstacionFinal(r1, e1);
                        retval.add(new Successor("Estación final cambiada", sucesor));
                    }
                }
            }
        }

        for (ProbIA5Board.Ruta r1: padre.getRutas()) {
            for (Estacion e1 : padre.getEstaciones()) {
                if (!e1.equals(r1.getEstacionInicial()) && !e1.equals(r1.getEstacionFinal1())) {
                    if (r1.getEstacionFinal2() != null &&  !e1.equals(r1.getEstacionFinal2())) {
                        ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                        sucesor.añadirEstacionFinal2(r1, e1);
                        retval.add(new Successor("Estación final 2 añadida", sucesor));
                    }
                }
            }
        }
        System.out.println(padre.getRutas().size() + "numero rutas padre");
        return retval;
    }
}
