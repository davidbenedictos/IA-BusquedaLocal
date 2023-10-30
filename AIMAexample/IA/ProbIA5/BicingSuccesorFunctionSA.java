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
        System.out.println("Entramos sucesor");
        Random random = new Random();
        int Operador = 0;
        while(true){

            Operador = generateRandom(random, 0, 1);
            //Añade furgoneta Random
            if (Operador == 0 && padre.getNRutas() < padre.getNFurgos()) {
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

            else if(Operador == 1 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));
                retval.add(new Successor("Furgo eliminada", sucesor));
                return retval;
            }
            //Afegir una bici a E2 random
            else if(Operador == 2 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                if((padre.getRutas().get(r).getNBicis() < 30 )&& (padre.getBicisNext(padre.getRutas().get(r).getEstacionInicial()) > padre.getRutas().get(r).getBicisRecogidas())) {
                    BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(), padre.getNBicis(),
                            padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));

                    sucesor.modificarFurgoneta(padre.getRutas().get(r).getEstacionInicial(), padre.getRutas().get(r).getEstacionFinal1(), padre.getRutas().get(r).getEstacionFinal2(),
                            padre.getRutas().get(r).getBicisRecogidas() + 1, padre.getRutas().get(r).getBicisDejadas1() + 1, padre.getRutas().get(r).getBicisDejadas2(), padre.getRutas().get(r).getCosteRuta(), padre.getRutas().get(r).getDistanciaRuta());
                    retval.add(new Successor("Añadimos bici a E2 random", sucesor));
                    return retval;
                }
            }

            //Afegir una bici a E3random
            else if(Operador == 3 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                if((padre.getRutas().get(r).getNBicis() < 30 )&& ((padre.getBicisNext(padre.getRutas().get(r).getEstacionInicial()) > padre.getRutas().get(r).getBicisRecogidas()))) {
                    BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(), padre.getNBicis(),
                            padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));

                    sucesor.modificarFurgoneta(padre.getRutas().get(r).getEstacionInicial(), padre.getRutas().get(r).getEstacionFinal1(), padre.getRutas().get(r).getEstacionFinal2(), padre.getRutas().get(r).getBicisRecogidas() + 1, padre.getRutas().get(r).getBicisDejadas1(), padre.getRutas().get(r).getBicisDejadas2() + 1, padre.getRutas().get(r).getCosteRuta(), padre.getRutas().get(r).getDistanciaRuta());
                    retval.add(new Successor("Añadimos bici a E3 random", sucesor));
                    return retval;
                }
            }

            //CAMBIAR ESTACION INICIAL INICIAL
            else if(Operador == 4 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                Estacion e1 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getVectorEstaciones(), padre.getNBicis(),
                        padre.getNFurgos(), padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));
                sucesor.modificarFurgoneta(e1, padre.getRutas().get(r).getEstacionFinal1(), padre.getRutas().get(r).getEstacionFinal2(),
                        padre.getRutas().get(r).getBicisRecogidas(), padre.getRutas().get(r).getBicisDejadas1(), padre.getRutas().get(r).getBicisDejadas2(), padre.getRutas().get(r).getCosteRuta(), padre.getRutas().get(r).getDistanciaRuta());
                retval.add(new Successor("Cambiamos estacion inicial", sucesor));
                return retval;
            }

            //CAMBIAR ESTACION FINAL 1

            else if(Operador == 5 && padre.getNRutas() > 0){
                int r = generateRandom(random, 0, padre.getNRutas()-1);
                Estacion e2 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(),  padre.getVectorEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                        padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));
                sucesor.modificarFurgoneta(padre.getRutas().get(r).getEstacionInicial(), e2, padre.getRutas().get(r).getEstacionFinal2(),
                        padre.getRutas().get(r).getBicisRecogidas(), padre.getRutas().get(r).getBicisDejadas1(), padre.getRutas().get(r).getBicisDejadas2(), padre.getRutas().get(r).getCosteRuta(), padre.getRutas().get(r).getDistanciaRuta());
                retval.add(new Successor("Cambiamos estacion final", sucesor));
                return retval;
            }

            //CAMBIAR ESTACION FINAL 2
            int r = generateRandom(random, 0, padre.getNRutas()-1);
            Estacion e2 = padre.getVectorEstaciones().get(generateRandom(random, 0, padre.getNEstaciones() - 1));
            BicingBoard sucesor = new BicingBoard(padre.getEstaciones(),  padre.getVectorEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                    padre.getRutas(), padre.getCoste(), padre.getDistancia(), padre.getRutas().get(r));
            sucesor.modificarFurgoneta(padre.getRutas().get(r).getEstacionInicial(), e2, padre.getRutas().get(r).getEstacionFinal2(),
                    padre.getRutas().get(r).getBicisRecogidas(), padre.getRutas().get(r).getBicisDejadas1(), padre.getRutas().get(r).getBicisDejadas2(), padre.getRutas().get(r).getCosteRuta(), padre.getRutas().get(r).getDistanciaRuta());
            retval.add(new Successor("Cambiamos estacion final", sucesor));
            return retval;

            }
        }




/*








        for (BicingBoard.Ruta r : padre.getRutas()) {
            for (Map.Entry<Estacion, Integer> e3 : padre.getEstaciones().entrySet()) {
                BicingBoard sucesor = new BicingBoard(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(),
                        padre.getRutas(), padre.getCoste(), padre.getDistancia(), r);
                sucesor.modificarFurgoneta(r.getEstacionInicial(), r.getEstacionFinal1(), e3.getKey(),
                        r.getBicisRecogidas(), r.getBicisDejadas1(), r.getBicisDejadas2(), r.getCosteRuta(), r.getDistanciaRuta());




            }
        }




        return retval;

 */
    }
