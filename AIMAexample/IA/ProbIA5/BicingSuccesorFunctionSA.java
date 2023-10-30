package IA.ProbIA5;

import IA.Bicing.Estacion;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.Successor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BicingSuccesorFunctionSA implements SuccessorFunction{

    // Función que devuelve un entero aleatorio entre min y max.
    private int generateRandom(Random random, int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
    public ArrayList getSuccessors(Object state) {
        ArrayList retval = new ArrayList<BicingBoard>();
        BicingBoard padre = (BicingBoard) state;
        Random myRandom = new Random();
        int i, j;
        /*
        //generem numeros random per a pillar estacions random
        i = myRandom.nextInt(padre.getNEstaciones());
        do {
            j = myRandom.nextInt(padre.getNEstaciones());
        } while (i == j);
        */

        Random random = new Random();
        int Operador = 0;
        while(true){

            Operador = generateRandom(random, 1, 2);
            //Añade furgoneta Random
            if (Operador == 1 && padre.getNRutas() < padre.getNRutas()) {
                Estacion e1 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
                Estacion e2 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
                Estacion e3 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
                int br = generateRandom(random, 0, padre.bicisSobrantes(e1));
                int bd = generateRandom(random, 0, br);
                int bd2 = br - bd;
                if (!e1.equals(e2) && !e1.equals(e3) && e3.equals(e2)) {
                    BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(),
                            padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia());
                    sucesor.añadirFurgoneta(e1, e2, e3, br, bd, bd2);
                    retval.add(new Successor("Furgo añadida", sucesor));
                    return retval;
                }
            }
            //Eliminar furgo
            else if(Operador == 2 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));
                retval.add(new Successor("Furgo eliminada", sucesor));
                return retval;
            }
        }



/*
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

 */
    }

}