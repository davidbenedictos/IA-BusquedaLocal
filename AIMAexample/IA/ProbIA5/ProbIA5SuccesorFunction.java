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


        /*
        OBSERVACIONS:
            - MOLT IMPORTANT: S'HA DE TENIR EN COMPTE QUE LA NOVA RUTA QUE AFEGIM NO LA ESTIGUI FENT UN FURGONETA JA
            basicament que dos rutes no poden tenir la mateixa estacio inicial (ja ho deia l'enunciat de la practica pero no
            ens vam fixar)

        */

        // Escogemos de forma aleatoria el operador a usar para crear el sucesor
        //Random random = new Random();
        //int Operador = 0;
        //while (true) {
        //    Operador = generateRandom(random, 1, 3);
        //        if (Operador == 1){
        //            System.out.println("operador 1");
                    for (Estacion e1 : padre.getEstaciones()) {
                        for (Estacion e2 : padre.getEstaciones()) {
                            if (!e1.equals(e2) && padre.getNRutas() < padre.getNFurgos()) {
                                boolean e1Used = padre.rutaIniciaEnEstacion(e1);
                                if (!e1Used) {
                                    for (int bicisR = 0; bicisR < e1.getNumBicicletasNext() && bicisR < 30; ++bicisR) {
                                        for (int bicisD = 0; bicisD < bicisR; ++bicisD) {
                                            ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                                            if (bicisR - bicisD > 0) {
                                                Estacion e3 = padre.getEstacionRandom(e1, e2);
                                                sucesor.añadirFurgoneta(e1, e2, e3, bicisR, bicisD);
                                            } else sucesor.añadirFurgoneta(e1, e2, null, bicisR, bicisD);

                                            retval.add(new Successor("BR " + bicisR + ". BD1 " + bicisD + ". BD2 " + (bicisR - bicisD)
                                                    + ". Coste " + sucesor.getCoste() + ". BicisNext, Demanda: " + e1.getNumBicicletasNext() + " " + e1.getDemanda() + " " + e2.getNumBicicletasNext() + " " + e2.getDemanda(), sucesor));
                                        }
                                    }
                                }
                            }
                        }
                    }
               //     }
              //  } else if (Operador == 2) {
                   // System.out.println("operador 2");
                    for (ProbIA5Board.Ruta r1: padre.getRutas()) {
                        for (Estacion e1 : padre.getEstaciones()) {
                            if (!e1.equals(r1.getEstacionInicial()) && !e1.equals(r1.getEstacionFinal1())) {
                                if (r1.getEstacionFinal2() != null && !e1.equals(r1.getEstacionFinal2())) {
                                    ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                                    sucesor.cambiarEstacionInicial(r1, e1);
                                    retval.add(new Successor("Estación inicial cambiada", sucesor));
                                }
                            }

                        }
                    }
                        //   }
                        // } else if (Operador == 3) {
                       // System.out.println("operador 3");
                        for (ProbIA5Board.Ruta r1 : padre.getRutas()) {
                            for (Estacion e1 : padre.getEstaciones()) {
                                if (!e1.equals(r1.getEstacionInicial()) && !e1.equals(r1.getEstacionFinal1())) {
                                    if (r1.getEstacionFinal2() != null && !e1.equals(r1.getEstacionFinal2())) {
                                        ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());

                                        sucesor.cambiarEstacionFinal1(r1, e1);
                                        retval.add(new Successor("Estación final 1 cambiada", sucesor));
                                    }
                                }
                            }
                        }
                        //}

                        //L'operador de canviar l'estacio final 2 fa que no acabi mai el programa -> Descobrir pq
        /*
        for (ProbIA5Board.Ruta r : padre.getRutas()) {
            for (Estacion e : padre.getEstaciones()) {
                ProbIA5Board sucesor = new ProbIA5Board(padre.getEstaciones(), padre.getNBicis(), padre.getNFurgos(), padre.getRutas(), padre.getCoste());
                if (!e.equals(r.getEstacionInicial()) && !e.equals(r.getEstacionFinal1())) {
                    sucesor.cambiarEstacionFinal2(r, e);
                    retval.add(new Successor("Estación final 2 cambiada", sucesor));
                }
            }
        }*/


                        //System.out.println(padre.getRutas().size() + "numero rutas padre");

                        return retval;
                    }
}

