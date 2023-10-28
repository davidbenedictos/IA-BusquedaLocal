import IA.Bicing.Estaciones;
import IA.ProbIA5.*;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        int nbicis = 1250;
        int nestaciones = 25;
        int demanda = 0;
        int seed = 1234;
        int nfurgos = 5;
        int heuristic = 1;
        int estadoIni = 1;


        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-e":
                    try {
                        nestaciones = Integer.parseInt(args[++i]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(args[i] + " is not an integer.");
                        System.exit(0);
                    }

                    break;

                case "-b":
                    try {
                        nbicis = Integer.parseInt(args[++i]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(args[i] + " is not an integer.");
                        System.exit(0);
                    }

                    break;

                case "-f":
                    try {
                        nfurgos =  Integer.parseInt(args[++i]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(args[i] + " is not an integer.");
                        System.exit(0);
                    }
                    break;

                case "-s":
                    try {
                        seed = Integer.parseInt(args[++i]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(args[i] + " is not an integer.");
                        System.exit(0);
                    }
                    break;

                case "-i":
                    try {
                        estadoIni =  Integer.parseInt(args[++i]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(args[i] + " is not an integer.");
                        System.exit(0);
                    }
                    break;

                case "-h":
                    try {
                        heuristic = Integer.parseInt(args[++i]);
                    } catch (NumberFormatException e) {
                        System.out.println(args[i] + "is not an integer.");
                        System.exit(0);
                    }
                    break;

                default:
                    // arg
                    System.out.println("La opción " + args[i] + " no es válida.");
                    System.out.println("-e [Número de estaciones] ");
                    System.out.println("-b [Número de bicis]");
                    System.out.println("-f [Número de furgonetas]");
                    System.out.println("-s [Seed]");
                    System.out.println("-i [Estado Inicial algoritmo (0/1)]");
                    System.out.println("-h [Función Heuristica (0/1)]");
                    System.exit(0);
                    break;
            }
        }

        Estaciones e = new Estaciones(nestaciones, nbicis, demanda, seed);
        BicingBoard board = new BicingBoard(e, nbicis, nfurgos, estadoIni);

        if(heuristic == 0) {
            BicingHillClimbingSearch(board);
        } else if (heuristic == 1) {
            BicingHillClimbingSearchDistance(board);
        }


    }


    private static void BicingHillClimbingSearch(BicingBoard board) {
        System.out.println("\nBicing HillClimbing  -->");
        try {
            long time = System.currentTimeMillis();
            Problem problem =  new Problem(board,
                    new BicingSuccesorFunction(),
                    new BicingGoalTest(),
                    new BicingHeuristicFunction());
            System.out.println("Problem created");
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            System.out.println();
            printActions(agent.getActions());
            BicingBoard newBoard = (BicingBoard) search.getGoalState();
            time = System.currentTimeMillis() - time;
            System.out.println(newBoard.getCoste());
            printInstrumentation(agent.getInstrumentation());
            System.out.println(time + " ms");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void BicingHillClimbingSearchDistance(BicingBoard board) {
        System.out.println("\nBicing HillClimbingDistance  -->");
        try {
            long time = System.currentTimeMillis();
            Problem problem =  new Problem(board,
                    new BicingSuccesorFunction(),
                    new BicingGoalTest(),
                    new BicingHeuristicFunctionDistance());
            System.out.println("Problem created.");
            Search search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem,search);
            System.out.println();
            printActions(agent.getActions());
            BicingBoard newBoard = (BicingBoard) search.getGoalState();
            time = System.currentTimeMillis() - time;
            System.out.println(newBoard.getCoste());
            printInstrumentation(agent.getInstrumentation());
            System.out.println("Tiempo de ejecución: " + time + " milisegundos");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
