package backtracking.using;

import backtracking.core.Evaluator;
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
 * @since Jan 25, 2021 9:19:29 PM
 */
public class DogsAndChickensProblem {

    public int numOfLeg;
    public DogsAndChickensEvaluator evaluator;

    public DogsAndChickensProblem(int numOfLeg) {
        this.numOfLeg = numOfLeg;
        evaluator = new DogsAndChickensEvaluator();
    }
    
    public class DogsAndChickensEvaluator implements Evaluator {
        // VarSet: [chickens, dogs]
        
        private int countLegs(Proposal p) {
            return p.get(0)*2 + p.get(1)*4;
        }
        
        @Override
        public boolean isSatisfied(Proposal p) {
            return countLegs(p) == numOfLeg;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init
        int numOfLeg = 100;
        DogsAndChickensProblem problem = new DogsAndChickensProblem(numOfLeg);
        
        int minVal = 1;
        int maxVal;
        
        // init Real Domain for Chicken
        RealDomain chickenDomain = new RealDomain();
        maxVal = (numOfLeg-4) - 2;
        for (int val = minVal; val <= maxVal; val++) {
            chickenDomain.add(val);
        }
        
        // init Real Domain for Dog
        RealDomain dogDomain = new RealDomain();
        maxVal = (numOfLeg-2)/4;
        for (int val = minVal; val <= maxVal; val++) {
            dogDomain.add(val);
        }
        
        // init VarSet
        VarSet varSet = new VarSet();
        IndexDomain D = new IndexDomain(chickenDomain);
        varSet.add(new Variable(D));
        D = new IndexDomain(dogDomain);
        varSet.add(new Variable(D));
        
        // init Proposal Evaluator
        Evaluator evaluator = problem.evaluator;
        
        // create Solver
        Solver solver = new Solver(varSet, evaluator);
        ArrayList<Proposal> solutions;
        solutions = solver.getAllSolution();
        
        int n = solutions.size();
        if (n > 0) {
            System.out.println(n + " solutions were detected:");
            System.out.println("      [chickens, dogs]");
            for (int i = 0; i < n; i++) {
                System.out.println("A solution:" + solutions.get(i));
            }
        } else {
            System.out.println("No solution can be detected!");
        }
    }

}
