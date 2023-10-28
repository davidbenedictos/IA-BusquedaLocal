import IA.Bicing.Estaciones;
import IA.ProbIA5.BicingBoard;
import IA.ProbIA5.BicingGoalTest;
import IA.ProbIA5.BicingHeuristicFunction;
import IA.ProbIA5.BicingSuccesorFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        /**
         *  For a problem to be solvable:
         *    count(0,prob) % 2 == count(0,sol) %2
         */
        Estaciones e = new Estaciones(25, 1250, 0, 1234);
        BicingBoard board = new BicingBoard(e, 1250, 5);


        long tiempoInicial = System.nanoTime();
        Problem p = new  Problem(board,
                new BicingSuccesorFunction(),
                new BicingGoalTest(),
                new BicingHeuristicFunction());
        System.out.println("Problem created");
        Search search = new HillClimbingSearch();
        SearchAgent agent = new SearchAgent(p, search);
        // We print the results of the search
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());
        long tiempoFinal = System.nanoTime();
        long tiempoEjecucion = tiempoFinal - tiempoInicial;
        long tiempoEnMilisegundos = tiempoEjecucion / 1000000;
        System.out.println("Tiempo de ejecución: " + tiempoEnMilisegundos + " milisegundos");



    }

        private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }
    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }
}
