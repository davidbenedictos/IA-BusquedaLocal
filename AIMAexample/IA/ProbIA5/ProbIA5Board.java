package IA.ProbIA5;

import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.max;


public class ProbIA5Board {    
   public class Ruta {
        private Estacion estacionIni;
        private Estacion estacionFi1;
        private Estacion estacionFi2;
        private int nbicisRecogidas;
        private int nbicisDejadas1;
        private int nbicisDejadas2;

        private int costeRuta;

        public Ruta(Estacion e1, Estacion e2, Estacion e3, int n1, int n2, int n3) {
            this.estacionIni = e1;
            this.estacionFi1 = e2;
            this.estacionFi2 = e3;
            this.nbicisRecogidas = n1;
            this.nbicisDejadas1 = n2;
            this.nbicisDejadas2 = n3;
            costeRuta = 0;
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

       public int getCosteRuta() {
           return costeRuta;
       }

       public void setCosteRuta(int costeRuta) {
           this.costeRuta = costeRuta;
       }
   }


    /*********************/
    /***** ATRIBUTOS *****/
    /*********************/

    private static int nbicis;
    private static int nestaciones;
    private static Estaciones estaciones;
    private int nfurgos;
    private ArrayList<Ruta> Rutas;
    private float coste;

    //CREADORAS
    public ProbIA5Board(Estaciones e, int nb, int nf) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        Rutas = new ArrayList<>();
        coste = 0;
    }

    public ProbIA5Board(Estaciones e, int nb, int nf, ArrayList<Ruta> r, float c) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;

        Rutas = new ArrayList<>(r.size());
        for (int i = 0; i < r.size(); ++i) {
            Rutas.add(r.get(i));
        }
        coste = c;
    }




    /*********************/
    /****** GETTERS ******/
    /*********************/

    public int getNBicis() {
        return(nbicis);
    }

    public int getNEstaciones() {
        return(nestaciones);
    }

    public int getNFurgos() {
        return(nfurgos);
    }

    public Estaciones getEstaciones() {
        return(estaciones);
    }

    public ArrayList<Ruta> getRutas(){
        return Rutas;
    }
    public float getCoste(){ return coste; }

    public int getNRutas() { return Rutas.size(); };

    public void setRutas(ArrayList<Ruta> rutas){
        Rutas = new ArrayList<>(rutas);
    }

    /********************************/
    /****** ESTADOS INICIALES *******/
    /********************************/

    //La primera solución inicial es un vector de Rutas vacio
    public boolean estadoInicial1() {
        Rutas = new ArrayList<Ruta>();
        return true;
    }


    public boolean estadoInicial2() {
        ordenarEstacionesPorDiferencia(estaciones);
        //List<Ruta> rutas = new ArrayList<>();
        Rutas = new ArrayList<>();

        if (estaciones.size() < 2 || nfurgos > 30) {
            return true;
        }
        for (int i = 0; i < estaciones.size() - 1; i++) {
            Estacion estacionFinal = estaciones.get(i);
            Estacion estacionInicial = estaciones.get(estaciones.size() - 1);
            int diferencia = estacionFinal.getNumBicicletasNext() - estacionFinal.getDemanda();
            if (diferencia > 0) {
                Ruta ruta = new Ruta(estacionInicial, estacionFinal, diferencia, diferencia);
                Rutas.add(ruta);
                nfurgos += 1;
            }
        }
        return false;
    }

    /*Funció auxiliar Esther*/
    public static void ordenarEstacionesPorDiferencia(List<Estacion> estaciones) {
        Collections.sort(estaciones, new Comparator<Estacion>() {
            @Override
            public int compare(Estacion estacion1, Estacion estacion2) {
                int diferencia1 = estacion1.getNumBicicletasNoUsadas() - estacion1.getDemanda();
                int diferencia2 = estacion2.getNumBicicletasNoUsadas() - estacion2.getDemanda();
                return Integer.compare(diferencia1, diferencia2);
            }
        });
    }

    /*************************/
    /****** OPERADORES *******/
    /*************************/

    //Nova ruta. De momento solo puede tener una estación final
    //Bicis recogidas <= nbicis en e1 && Bicis recogidas <= 30
    public void añadirFurgoneta(Estacion e1, Estacion e2, int bicisRecogidas, int bicisDejadas) {
        Ruta rutaNueva = new Ruta(e1, e2, bicisRecogidas, bicisDejadas);
        //System.out.println("Coste antes de la nueva ruta: " + coste);
        modificarCoste(rutaNueva);

        Rutas.add(rutaNueva);
        //System.out.println("Nuevo coste: " + coste);
    }

    //Calcular el coste de una ruta. Supongo que los beneficios restan y los costes suman. Queremos minimizar el coste
    public void modificarCoste(Ruta ruta) {
        coste -= ruta.getCosteRuta();
        ruta.setCosteRuta(0);

        int c = 0;

        //Suma al coste los kilometros de la ruta ponderados por el numero de bicis transportado
        //c += distancia1(ruta)*((ruta.getNBicis() + 9)/10);


        //Nos beneficia dejar una bici en una estacion, mientras no se supere la demanda de bicis necesaria
        c -= min(ruta.getBicisDejadas1(), bicisNecesarias(ruta.getEstacionFinal1()));

        //Incrementa el coste por cada bici que recojamos por debajo de la demanda
        c += max(0, ruta.getBicisRecogidas() - bicisSobrantes(ruta.getEstacionInicial()));




        /*System.out.println("Modificamos el coste de verdad");
        System.out.println("Bicicletas sobrantes: " + bicisSobrantes(ruta.getEstacionInicial()));
        System.out.println("Bicicletas necesarias: " + bicisNecesarias(ruta.getEstacionFinal1()));
        System.out.println("Bicicletas recogidas: " + ruta.getBicisRecogidas());
        System.out.println("Bicicletas dejadas: " + ruta.getBicisDejadas1());
        System.out.println("El coste se modifica en: " + modificacion);
        System.out.println("Numero de furgos totales " + Rutas.size());*/
        coste += c;
        ruta.setCosteRuta(c);
        //System.out.println("");
    }

    public void cambiarEstacionInicial(Ruta r1, Estacion e1) {
        r1.estacionIni = e1;
        modificarCoste(r1);
        System.out.println("Estacion Inicial cambiada");
    }

    public void cambiarEstacionFinal(Ruta r1, Estacion e1) {
        r1.estacionFi1 = e1;
        modificarCoste(r1);
        System.out.println("EStacion Final Cambiada");
    }

    public void añadirEstacionFinal2(Ruta r1, Estacion e2) {
        r1.estacionFi2 = e2;
        modificarCoste(r1);
        System.out.println("Estacion Final 2 Añadida");
    }



    
    //Bicis que faltaran en una estacion
    public int bicisNecesarias(Estacion e) {
        return max(0, e.getDemanda() - e.getNumBicicletasNext());

    }

    //Bicis que sobran en una estacion
    public int bicisSobrantes(Estacion e) {
        return max(0, e.getNumBicicletasNext() - e.getDemanda());

    }

    //Distancia entre la estacion inicial y la estacion final 1
    public int distancia1(final Ruta ruta) {
        return distanciaEstaciones(ruta.getEstacionInicial(), ruta.getEstacionFinal1());
    }

    ////Distancia entre la estacion final 1 y la estacion final 2
    /*public int distancia2(final Ruta ruta) {
        return distanciaEstaciones(ruta.getEstacionFinal1(), ruta.getEstacionFinal2());
    }*/

    //Devuelve la distancia entre las dos estaciones
    public int distanciaEstaciones(Estacion e1, Estacion e2) {
        return abs(e1.getCoordX() - e2.getCoordX()) + abs(e1.getCoordY() - e2.getCoordY());

    }

    public int getBicisMal() {
        int valor = 0;
        for (Ruta r1: Rutas) {
            int aux = r1.getEstacionFinal1().getDemanda();
            int aux2 = r1.nbicisDejadas1;
            valor += aux2 - aux;
        }
        return min(0, valor);
    }
}