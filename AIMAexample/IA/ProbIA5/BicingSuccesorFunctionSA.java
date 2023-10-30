package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BicingSuccesorFunctionSA implements SuccessorFunction{
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<BicingBoard>();
        BicingBoard padre = (BicingBoard) state;
        Random myRandom = new Random();
        int i, j;
        //generem numeros random per a pillar estacions random
        i = myRandom.nextInt(padre.getNEstaciones());
        do {
            j = myRandom.nextInt(padre.getNEstaciones());
        } while (i == j);

        //AÑADIR FURGONETA
        for (Map.Entry<Estacion, Integer> e1 : padre.getEstaciones().entrySet()) {
            for (Map.Entry<Estacion, Integer> e2 : padre.getEstaciones().entrySet()) {
                if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                    boolean e1Used = padre.rutaIniciaEnEstacion(e1.getKey());
                    if (!e1Used) {
                        for (int bicisR = 0; bicisR <= padre.getBicisNext(e1.getKey()) && bicisR <= 30; ++bicisR) {
                            for (int bicisD = 0; bicisD <= bicisR; ++bicisD) {
                                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia());
                                sucesor.añadirFurgoneta(e1.getKey(), e2.getKey(), e2.getKey(), bicisR, bicisD, bicisR - bicisD);
                                retval.add(new Successor("Furgoneta añadida. Coste: " + sucesor.getCoste() +
                                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
                            }
                        }
                    }
                }
            }

        }
        int k = myRandom.nextInt(padre.getNFurgos()); // furgo random

        //ELIMINAR FURGONETA
        for (BicingBoard.Ruta r : padre.getRutas()) {
            BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(),
                    padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);
            retval.add(new Successor("Furgoneta eliminada. Coste: " + sucesor.getCoste() +
                    ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
        }

        int l = myRandom.nextInt(padre.getNRutas()); // ruta random

        //UNA BICI MAS A E2
        for (BicingBoard.Ruta r : padre.getRutas()) {
            if (r.getBicisRecogidas() < 30 && padre.getBicisNext(r.getEstacionInicial()) > r.getBicisRecogidas()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);

                sucesor.modificarFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                        r.getBicisRecogidas() + 1, r.getBicisDejadas1() + 1, r.getBicisDejadas2(), r.getCosteRuta(), r.getDistanciaRuta());


                retval.add(new Successor("Bici añadida estación final 1. Coste: " + sucesor.getCoste() +
                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
            }
        }

        int m = myRandom.nextInt(padre.getNRutas()); // ruta random

        //UNA BICI MES A E3
        for (BicingBoard.Ruta r : padre.getRutas()) {
            if (r.getBicisRecogidas() < 30 && padre.getBicisNext(r.getEstacionInicial()) > r.getBicisRecogidas()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);



                sucesor.modificarFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                        r.getBicisRecogidas() + 1, r.getBicisDejadas1(), r.getBicisDejadas2() + 1, r.getCosteRuta(), r.getDistanciaRuta());

                Estacion e1 = r.getEstacionInicial();
                Estacion e2 = r.getEstacionFinal1();
                Estacion e3 = r.getEstacionFinal2();

                retval.add(new Successor("Bicicleta añadida estacion final 2. Coste: " + sucesor.getCoste() +
                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
            }
        }

        int n, o;
        n = myRandom.nextInt(padre.getNRutas()); // ruta random
        o = myRandom.nextInt(padre.getNEstaciones()); // estacion random

        //CAMBIAR ESTACION INICIAL INICIAL
        for (BicingBoard.Ruta r : padre.getRutas()) {
            for (Map.Entry<Estacion, Integer> e1 : padre.getEstaciones().entrySet()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                        padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);
                sucesor.modificarFurgoneta(e1.getKey(), r.getEstacionFinal1(), r.getEstacionFinal2(),
                        r.getBicisRecogidas(), r.getBicisDejadas1(), r.getBicisDejadas2(), r.getCosteRuta(), r.getDistanciaRuta());

                retval.add(new Successor("Estacion inicial cambiada. Coste: " + sucesor.getCoste() +
                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
            }
        }

        int p, q;
        p = myRandom.nextInt(padre.getNRutas()); // ruta random
        q = myRandom.nextInt(padre.getNEstaciones()); // estacion random

        //CAMBIAR ESTACION FINAL 1
        for (BicingBoard.Ruta r : padre.getRutas()) {
            for (Map.Entry<Estacion, Integer> e2 : padre.getEstaciones().entrySet()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                        padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);
                sucesor.modificarFurgoneta(r.getEstacionInicial(), e2.getKey(), r.getEstacionFinal2(),
                        r.getBicisRecogidas(), r.getBicisDejadas1(), r.getBicisDejadas2(), r.getCosteRuta(), r.getDistanciaRuta());

                retval.add(new Successor("Estacion final 1 cambiada. Coste: " + sucesor.getCoste() +
                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
            }
        }

        int t, s;
        t = myRandom.nextInt(padre.getNRutas()); // ruta random
        s = myRandom.nextInt(padre.getNEstaciones()); // estacion random

        //CAMBIAR ESTACION FINAL 2
        for (BicingBoard.Ruta r : padre.getRutas()) {
            for (Map.Entry<Estacion, Integer> e3 : padre.getEstaciones().entrySet()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                        padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);
                sucesor.modificarFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), e3.getKey(),
                        r.getBicisRecogidas(), r.getBicisDejadas1(), r.getBicisDejadas2(), r.getCosteRuta(), r.getDistanciaRuta());


                Estacion e1 = r.getEstacionInicial();
                Estacion e2 = r.getEstacionFinal1();

                retval.add(new Successor("Estacion final 2 cambiada. Coste: " + sucesor.getCoste() +
                        ". Heuristica: " + sucesor.getCoste()*sucesor.getDistancia()*0.001, sucesor));
            }
        }




        return retval;
    }

}