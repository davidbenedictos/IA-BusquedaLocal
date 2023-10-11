//Este archivo antes se llamaba ProbIA5Board.java
package IA.ProbIA5;

   
  
import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
/**
 * Created by bejar on 17/01/17.
 */

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
    }


    //Atributos
    private static int nbicis;
    private static int nestaciones;
    private static Estaciones estaciones;
    private int tipusdemanda;
    private int nfurgos;
    private ArrayList<Ruta> Rutas;
    private float cost;

    //CREADORAS
    public Estado(Estaciones e, int nb, int nf, int demanda) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        tipusdemanda = demanda;
        estadoInicial1();
        cost = 0;
    }

    
    //GETTERS
    public int getNBicis() {return(nbicis);}

    public int getNEstaciones() {return(nestaciones);}

    public int getNFurgos() {return(nfurgos);}

    public int getDemanda() {return(tipusdemanda);}

    public Estaciones getEstaciones() {return(estaciones);}

    public int[] getVBicis() {return(vbicis);}


    //La primera solución inicial es un vector de Rutas vacio
    public boolean estadoInicial1() {
        Rutas = new ArrayList<Ruta>();
        return true;
    }

    /* Heuristic function */
    public double heuristic(){
        return 0;
    }

    /* Goal test */
    public boolean is_goal(){
        return false;
    }


    //OPERADORES

    public void añadirFurgoneta(){
        Ruta ruta = new Ruta(parametros ruta...);
        configurar la ruta...
         
    }

}
