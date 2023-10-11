package IA.ProbIA5;

import java.util.Random;

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

    private int [] board;
    private static int [] solution;

    private int nbicis;
    private int nestaciones;
    private Estaciones estaciones;
    private int [] vbicis;
    private int tipusdemanda;
    private int nfurgos;
    private Ruta [] rutas;

    /* Constructor */
    public ProbIA5Board(int []init, int[] goal) {

        board = new int[init.length];
        solution = new int[init.length];

        for (int i = 0; i< init.length; i++) {
            board[i] = init[i];
            solution[i] = goal[i];
        }

    }

    public ProbIA5Board(Estaciones e, int nb, int nf, int demanda) {
        //this.board = ;
        estaciones = e;
        nbicis = nb;
        nestaciones = e.size();
        nfurgos = nf;
        tipusdemanda = demanda;
        vbicis = createRandomvbicis(nb, e.size());
        //Ruta ruta = new Ruta(e[0], e[1], e[2], 5, 2, 3);
    }

    private int [] createRandomvbicis(int num, int nestaciones) {
        int[] vbicis = new int[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) { // Ejemplo de bucle que genera 10 números aleatorios
            int numeroAleatorio = random.nextInt(nestaciones + 1); // Genera un número aleatorio de 0 a nestaciones
            vbicis[i] = numeroAleatorio;
        }
        return vbicis;
    }

    public int getNBicis() {return(nbicis);}

    public int getNEstaciones() {return(nestaciones);}

    public int getNFurgos() {return(nfurgos);}

    public int getDemanda() {return(tipusdemanda);}

    public Estaciones getEstaciones() {return(estaciones);}

    public int[] getVBicis() {return(vbicis);}

    /*!\brief Enseña por pantalla la solucion actual
     *
     */
    public void showState() {
        for(int i = 0; i < nestaciones; ++i) {
            System.out.println("Estacion " + i);
        }
        System.out.println();
        for(int j = 0; j < nbicis; ++j) {
            System.out.println("Bici " + j + ": " + vbicis[j]);
        }
    }

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
