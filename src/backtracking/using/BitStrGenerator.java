package backtracking.using;

import backtracking.core.DefaultEvaluator;
import backtracking.core.IndexDomain;
import backtracking.core.Proposal;
import backtracking.core.RealDomain;
import backtracking.core.Solver;
import backtracking.core.VarSet;
import backtracking.core.Variable;
import java.util.ArrayList;

/**
 *
 * @author tackedev
 * @since Jan 25, 2021 8:36:58 PM
 */
public class BitStrGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init
        int numOfVars = 4;
        System.out.println("BIT STRING GENERATOR");
        
        // init domain
        RealDomain booleanSet = new RealDomain();
        booleanSet.add(0);
        booleanSet.add(1);
        
        // init varSet
        VarSet varSet = new VarSet();
        for (int i = 0; i < numOfVars; i++) {
            IndexDomain D = new IndexDomain(booleanSet);
            varSet.add(new Variable(D));
        }
        
        // init proposal evaluator
        DefaultEvaluator evaluator = new DefaultEvaluator();
        
        // init solver
        Solver solver = new Solver(varSet, evaluator);
        ArrayList<Proposal> solutions;
        
        System.out.println("Get ONE solution:");
        Proposal sol = solver.getOneSolution();
        if (sol != null) {
            System.out.println("A solution: " + sol + "\n");
        } else {
            System.out.println("No solution!\n");
        }
        
        solutions = solver.getAllSolution();
        int n = solutions.size();
        if (n > 0) {
            System.out.println(n + " solutions were detected:");
            for (int i = 0; i < n; i++) {
                System.out.println("A solution: " + solutions.get(i));
            }
        } else {
            System.out.println("No solution can be detected!");
        }
    }
}