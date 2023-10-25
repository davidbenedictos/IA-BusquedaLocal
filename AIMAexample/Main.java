import IA.Bicing.Estaciones;
import IA.ProbIA5.ProbIA5Board;
import IA.ProbIA5.ProbIA5GoalTest;
import IA.ProbIA5.ProbIA5HeuristicFunction;
import IA.ProbIA5.ProbIA5SuccesorFunction;
import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.AStarSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.IterativeDeepeningAStarSearch;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        /**
         *  For a problem to be solvable:
         *    count(0,prob) % 2 == count(0,sol) %2
         */
        long tiempoInicial = System.nanoTime();
        Estaciones e = new Estaciones(25, 1250, 0, 1234);
        ProbIA5Board board = new ProbIA5Board(e, 1250, 5);
        // board.showState();

        // Create the Problem object

         Problem p = new  Problem(board,
                                new ProbIA5SuccesorFunction(),
                                new ProbIA5GoalTest(),
                                new ProbIA5HeuristicFunction());
        System.out.println("Problem created");
        // Instantiate the search algorithm
	    // AStarSearch(new GraphSearch()) or IterativeDeepeningAStarSearch()
        Search search = new HillClimbingSearch();

        // Instantiate the SearchAgent object
        SearchAgent agent = new SearchAgent(p, search);

	// We print the results of the search
        System.out.println();
        printActions(agent.getActions());
        printInstrumentation(agent.getInstrumentation());

        // You can access also to the goal state using the
	// method getGoalState of class Search
        long tiempoFinal = System.nanoTime();
        long tiempoEjecucion = tiempoFinal - tiempoInicial;

        // Convertir nanosegundos a milisegundos
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
