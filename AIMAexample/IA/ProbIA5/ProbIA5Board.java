package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import java.util.ArrayList;


public class ProbIA5Board {    
   public class Ruta {
        private Estacion estacionIni;
        private Estacion estacionFi1;
        private Estacion estacionFi2;
        private int nbicisRecogidas;
        private int nbicisDejadas1;
        private int nbicisDejadas2;

        public Ruta(Estacion e1, Estacion e2, Estacion e3, int n1, int n2, int n3) {
            this.estacionIni = e1;
            this.estacionFi1 = e2;
            this.estacionFi2 = e3;
            this.nbicisRecogidas = n1;
            this.nbicisDejadas1 = n2;
            this.nbicisDejadas2 = n3;
        }

        public Ruta(Estacion e1, Estacion e2, int n1, int n2) {
            this.estacionIni = e1;
            this.estacionFi1 = e2;
            this.estacionFi2 = null;
            this.nbicisRecogidas = n1;
            this.nbicisDejadas1 = n2;
            this.nbicisDejadas2 = 0;
        }

        public Estacion getEstacionInicial() {
            return estacionIni;
        }

        public Estacion getEstacionFinal1() {
            return estacionFi1;
        }

        public Estacion getEstacionFinal2() {
            return estacionFi2;
        }

        public int getNBicis() {
            return nbicisRecogidas;
        }

        public int getBicisRecogidas() {
            return nbicisRecogidas;
        }

        public int getBicisDejadas1() {
            return nbicisDejadas1;
        }
    }


    //Atributos
    private static int nbicis;
    private static int nestaciones;
    private static Estaciones estaciones;
    private int tipusdemanda;
    private int nfurgos;
    private ArrayList<Ruta> Rutas;
    private float coste;

    //CREADORAS
    public ProbIA5Board(Estaciones e, int nb, int nf, int demanda) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        tipusdemanda = demanda;
        estadoInicial1();
        coste = 0;
    }

    
    //GETTERS
    public int getNBicis() {
        return(nbicis);
    }

    public int getNEstaciones() {
        return(nestaciones);
    }

    public int getNFurgos() {
        return(nfurgos);
    }

    public int getDemanda() {
        return(tipusdemanda);
    }

    public Estaciones getEstaciones() {
        return(estaciones);
    }


    //La primera solución inicial es un vector de Rutas vacio
    public boolean estadoInicial1() {
        Rutas = new ArrayList<Ruta>();
        return true;
    }


    public double heuristic(){
        return 0;
    }

    
    public boolean is_goal(){
        return false;
    }


    //OPERADORES

    //Nova ruta. De momento solo puede tener una estación final
    //Bicis recogidas <= nbicis en e1 && Bicis recogidas <= 30
    public void añadirFurgoneta(Estacion e1, Estacion e2, int bicisRecogidas, int bicisDejadas) {
        Ruta ruta = new Ruta(e1, e2, bicisRecogidas, bicisDejadas);
        Rutas.add(ruta);
        modificarCoste(ruta);
    }

    //Calcular el coste de una ruta. Supongo que los beneficios restan y los costes suman. Queremos minimizar el coste
    public void modificarCoste(final Ruta ruta) {
        //Suma al coste los kilometros de la ruta ponderados por el numero de bicis transportado
        coste += distancia1(ruta)*((ruta.getNBicis() + 9)/10);

        //Nos beneficia dejar una bici en una estacion, mientras no se supere la demanda de bicis necesaria

        coste -= Math.min(ruta.getBicisDejadas1(), bicisNecesarias(ruta.getEstacionFinal1()));

        //Incrementa el coste por cada bici que recojamos por debajo de la demanda
        coste += Math.max(0, ruta.getBicisRecogidas() - bicisSobrantes(ruta.getEstacionInicial()));
    }
    
    //Bicis que faltaran en una estacion
    public int bicisNecesarias(Estacion e) {
        return Math.max(0, e.getDemanda() - e.getNumBicicletasNext());

    }

    //Bicis que sobran en una estacion
    public int bicisSobrantes(Estacion e) {
        return Math.max(0, e.getNumBicicletasNext() - e.getDemanda());

    }

    //Distancia entre la estacion inicial y la estacion final 1
    public int distancia1(final Ruta ruta) {
        return distanciaEstaciones(ruta.getEstacionInicial(), ruta.getEstacionFinal1());
    }

    ////Distancia entre la estacion inicial y la estacion final 1
    /*public int distancia2(final Ruta ruta) {
        return distanciaEstaciones(ruta.getEstacionFinal1(), ruta.getEstacionFinal2());
    }*/

    //Devuelve la distancia entre las dos estaciones
    public int distanciaEstaciones(Estacion e1, Estacion e2) {
        return Math.abs(e1.getCoordX() - e2.getCoordX()) + Math.abs(e1.getCoordY() - e2.getCoordY());

    }

}