package IA.ProbIA5;

/**
 * Created by bejar on 17/01/17.
 */

import aima.search.framework.HeuristicFunction;

public class ProbIA5HeuristicFunction implements HeuristicFunction {

    public boolean equals(Object obj) {
        boolean retValue;

        retValue = super.equals(obj);
        return retValue;
    }

    public double getHeuristicValue(Object n) {
        ProbIA5Board estado = (ProbIA5Board) n;
       //System.out.println(estado.getCoste());
        return estado.getCoste();
    }
}
