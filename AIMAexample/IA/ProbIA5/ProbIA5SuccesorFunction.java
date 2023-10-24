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
        /*
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
        */

        //AÑADIR FURGO
        for (Estacion e1 : padre.getEstaciones()) {
            for (Estacion e2 : padre.getEstaciones()) {
                for (Estacion e3 : padre.getEstaciones()) {
                    if (!e1.equals(e2) && !e2.equals(e3) && !e3.equals(e1) && padre.getNRutas() < padre.getNFurgos()) {
                        boolean e1Used = padre.rutaIniciaEnEstacion(e1);
                        //POTSER HAURIEM DE MIRAR QUE LA E2 I E3 NO ESTIGUIN EN MÉS DE DOS RUTES
                        if (!e1Used) {
                            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getBicisNext(), padre.getCoste());
                            sucesor.añadirFurgoneta(e1, e2, e3, 1, 1, 0);
                            retval.add(new Successor("C: " + sucesor.getCoste() +
                                    ". eIni " + e1.getCoordX() + " " + e1.getCoordY()
                                    + ". eFin1 " + e2.getCoordX() + " " + e2.getCoordY()
                                    + ". D1, N1: " + e1.getDemanda() + " " + sucesor.getBicisNext(e1.getCoordX(), e1.getCoordY())
                                    + ". bR, bD: " + 1 + " " + 1
                                    + ". D2, N2: " + + e2.getDemanda() + " " + sucesor.getBicisNext(e2.getCoordX(), e2.getCoordY())
                                    + " . E2 BN Original: " + e2.getNumBicicletasNext(), sucesor));
                        }
                    }
                }
            }
        }




        //llevar una mas a e1
        //possibles fallos:
        //agafar mes de les que hi ha
        //agafar mes de 30

        //UNA BICI MAS A E2
        for (ProbIA5Board.Ruta r : padre.getRutas()) {
            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getBicisNext(), r);
            sucesor.añadirFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                    r.getBicisRecogidas() + 1, r.getBicisDejadas1() + 1, r.getBicisDejadas2());
            retval.add(new Successor("Una bici dejada más en estacion final 1", sucesor));

        }



        //llevar una mas a e2



        return retval;
        }

}

