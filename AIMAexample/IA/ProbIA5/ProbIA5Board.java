//Este archivo antes se llamaba ProbIA5Board.java
package IA.ProbIA5;

   
  
import IA.Bicing.Estacion;
import IA.Bicing.Estaciones;
/**
 * Created by bejar on 17/01/17.
 */

public class ProbIA5Board {
    /* Class independent from AIMA classes
       - It has to implement the state of the problem and its operators
     *

    /* State data structure
        vector with the parity of the coins (we can assume 0 = heads, 1 = tails
     */

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

        // Métodos getter y setter para los campos si es necesario
    }

    private static int nbicis;
    private static int nestaciones;
    private static Estaciones estaciones;
    private int tipusdemanda;
    private int nfurgos;
    private ArrayList<Ruta> Rutas;
   // private Ruta [] rutas;
    private float cost;

    /* Constructor 
    public ProbIA5Board(int []init, int[] goal) {

        board = new int[init.length];
        solution = new int[init.length];

        for (int i = 0; i< init.length; i++) {
            board[i] = init[i];
            solution[i] = goal[i];
        }

    }*/

    public ProbIA5Board(Estaciones e, int nb, int nf, int demanda) {
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        tipusdemanda = demanda;
    }

    

    public int getNBicis() {return(nbicis);}

    public int getNEstaciones() {return(nestaciones);}

    public int getNFurgos() {return(nfurgos);}

    public int getDemanda() {return(tipusdemanda);}

    public Estaciones getEstaciones() {return(estaciones);}

    public int[] getVBicis() {return(vbicis);}



    public boolean estadoInicial1() {
        return true;
    }



    
    /*!\brief Enseña por pantalla la solucion actual
     *
     */
    /* 
    public void showState() {
        for(int i = 0; i < nestaciones; ++i) {
            System.out.println("Estacion " + i);
        }
        System.out.println();
        for(int j = 0; j < nbicis; ++j) {
            System.out.println("Bici " + j + ": " + vbicis[j]);
        }
    }
    */

    /* Heuristic function */
    public double heuristic(){
        // compute the number of coins out of place respect to solution
        return 0;
    }

     /* Goal test */
     public boolean is_goal(){
         // compute if board = solution
         return false;
     }

     /* auxiliary functions */

     // Some functions will be needed for creating a copy of the state



    /* ^^^^^ TO COMPLETE ^^^^^ */

}
