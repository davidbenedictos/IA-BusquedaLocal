package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;
import java.util.Random;
import java.util.ArrayList;
import static java.lang.Math.*;
import java.util.HashMap;
import java.util.Map;

public class ProbIA5SuccesorFunction implements SuccessorFunction{
    private int generateRandom(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<ProbIA5Board>();
        ProbIA5Board padre = (ProbIA5Board) state;
        /*
        for (Map.Entry<Estacion, Integer> e1 : padre.getEstaciones().entrySet()) {
            for (Map.Entry<Estacion, Integer> e2 : padre.getEstaciones().entrySet()) {
                if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                    boolean e1Used = padre.rutaIniciaEnEstacion(e1.getKey());
                    if (!e1Used) {
                        for (int bicisR = 0; bicisR <= padre.getBicisNext(e1.getKey()) && bicisR <= 30; ++bicisR) {
                            for (int bicisD = 0; bicisD <= bicisR; ++bicisD) {
                                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                                sucesor.añadirFurgoneta(e1.getKey(), e2.getKey(), e2.getKey(), bicisR, bicisD, bicisR - bicisD);
                                retval.add(new Successor("C: " + sucesor.getCoste() +
                                        ". eIni " + e1.getKey().getCoordX() + " " + e1.getKey().getCoordY()
                                        + ". eFin1 " + e2.getKey().getCoordX() + " " + e2.getKey().getCoordY()
                                        + ". D1, N1: " + e1.getKey().getDemanda() + " " + sucesor.getBicisNext(e1.getKey())
                                        + ". bR, bD: " + bicisR + " " + bicisD
                                        + ". D2, N2: " + +e2.getKey().getDemanda() + " " + sucesor.getBicisNext(e2.getKey())
                                        + " . E2 BN Original: " + e2.getKey().getNumBicicletasNext(), sucesor));
                            }
                        }
                    }
                }
            }

        }*/





        //AÑADIR FURGO biennn
        for (Map.Entry<Estacion, Integer> e1 : padre.getEstaciones().entrySet()) {
            for (Map.Entry<Estacion, Integer> e2 : padre.getEstaciones().entrySet()) {
                for (Map.Entry<Estacion, Integer> e3 : padre.getEstaciones().entrySet()) {
                    if (!e1.equals(e2) && !e2.equals(e3) && !e3.equals(e1) && padre.getNRutas() < padre.getNFurgos()) {
                        boolean e1Used = padre.rutaIniciaEnEstacion(e1.getKey());
                        //POTSER HAURIEM DE MIRAR QUE LA E2 I E3 NO ESTIGUIN EN MÉS DE DOS RUTES
                        if (!e1Used) {
                            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                                    padre.getRutas(), padre.getCoste());
                            sucesor.añadirFurgoneta(e1.getKey(), e2.getKey(), e3.getKey(), 1, 1, 0);
                            retval.add(new Successor("C: " + sucesor.getCoste() +
                                    ". eIni " + e1.getKey().getCoordX() + " " + e1.getKey().getCoordY()
                                    + ". eFin1 " + e2.getKey().getCoordX() + " " + e2.getKey().getCoordY()
                                    + ". D1, N1: " + e1.getKey().getDemanda() + " " + sucesor.getBicisNext(e1.getKey())
                                    + ". bR, bD: " + 1 + " " + 1
                                    + ". D2, N2: " + + e2.getKey().getDemanda() + " " + sucesor.getBicisNext(e2.getKey())
                                    + " . E2 BN Original: " + e2.getKey().getNumBicicletasNext(), sucesor));
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
            Estacion ini = r.getEstacionInicial();
            if (r.getBicisRecogidas() < 30 && padre.getBicisNext(ini) > r.getBicisRecogidas()) {
                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), r);

                sucesor.añadirFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                        r.getBicisRecogidas() + 1, r.getBicisDejadas1() + 1, r.getBicisDejadas2());

                retval.add(new Successor("Una bici dejada más en estacion final 1", sucesor));
            }
        }

        //UNA BICI MES A E3
        for (ProbIA5Board.Ruta r : padre.getRutas()) {
            Estacion ini = r.getEstacionInicial();
            if (r.getBicisRecogidas() < 30 && padre.getBicisNext(ini) > r.getBicisRecogidas()) {
                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), r);

                sucesor.añadirFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                        r.getBicisRecogidas() + 1, r.getBicisDejadas1(), r.getBicisDejadas2() + 1);

                retval.add(new Successor("Una bici dejada más en estacion final 2", sucesor));
            }
        }


        return retval;
    }

}

