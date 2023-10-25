package IA.ProbIA5;

/**
 * Created by bejar on 17/01/17.
 */

import aima.search.framework.HeuristicFunction;

public class ProbIA5HeuristicFunction implements HeuristicFunction {
    /*
    public double getHeuristicValue(Object state) {
        ProbIA5Board board = (ProbIA5Board) state;

        int bicicletasExtra = calcularBicicletasExtra(board);
        int costosTransporte = calcularCostosTransporte(board);

        // Ponderación para balancear los dos criterios
        double pesoGanancias = 0.8;
        double pesoCostos = 0.2;

        // Calcular el valor de la heurística como combinación lineal de ganancias y costos
        double heuristica = -(pesoGanancias * bicicletasExtra - pesoCostos * costosTransporte);

        return heuristica;
    }

    private int calcularBicicletasExtra(ProbIA5Board board) {
        int bicicletasExtra = 0;
        for (IA.ProbIA5.ProbIA5Board.Ruta ruta : board.getRutas()) {
            int bicicletasPrevistas = ruta.getEstacionFinal1().getDemanda();
            int bicicletasDejadas = ruta.getBicisDejadas1();
            bicicletasExtra += Math.max(0, bicicletasDejadas - bicicletasPrevistas);
        }
        return bicicletasExtra;
    }

    private int calcularCostosTransporte(ProbIA5Board board) {
        int costosTransporte = 0;
        for (IA.ProbIA5.ProbIA5Board.Ruta ruta : board.getRutas()) {
            int bicicletasTransportadas = ruta.getBicisRecogidas() + ruta.getBicisDejadas1() + ruta.getBicisDejadas2();
            costosTransporte += ((bicicletasTransportadas + 9) / 10); // Costo por kilómetro
        }
        return costosTransporte;
    }


}

     */
    public boolean equals(Object obj) {
        boolean retValue;

        retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object n) {
        ProbIA5Board estado = (ProbIA5Board) n;
        //System.out.println(estado.getCoste());
        return estado.getCoste();
        //return estado.getBicisMal();
    }


}
