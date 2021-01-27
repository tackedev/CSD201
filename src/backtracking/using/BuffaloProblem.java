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
 * @since Jan 25, 2021 8:49:34 PM
 */
public class BuffaloProblem {
    
    public int numOfBuffaloes;
    public int numOfWhisks;
    BuffaloEvaluator evaluator;

    public BuffaloProblem(int numOfBuffaloes) {
        this.numOfBuffaloes = numOfBuffaloes;
        this.numOfWhisks = numOfBuffaloes;
        evaluator = new BuffaloEvaluator();
    }

    public BuffaloProblem(int numOfBuffaloes, int numOfWhisks) {
        this.numOfBuffaloes = numOfBuffaloes;
        this.numOfWhisks = numOfWhisks;
        evaluator = new BuffaloEvaluator();
    }
    
    // implemented class for Proposal Evaluator of Buffalo Problem 
    public class BuffaloEvaluator implements Evaluator {
        // VarSet: [child, big, old] buffalo
        
        private int countGrass(Proposal p) {
            return p.get(0)*1 + p.get(1)*2 + p.get(2)/2;
        }
        
        private int countBuffaloes(Proposal p) {
            return p.get(0) + p.get(1) + p.get(2);
        }
        
        @Override
        public boolean isSatisfied(Proposal p) {
            return countGrass(p) == numOfWhisks && countBuffaloes(p) == numOfBuffaloes;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init
        int numOfGrassPkg = 100;
        BuffaloProblem problem = new BuffaloProblem(numOfGrassPkg);
        
        int minVal = 0;
        int maxVal;
        
        // init Real Domain for Small Buffaloes
        RealDomain smallDomain = new RealDomain();
        maxVal = numOfGrassPkg;
        for (int val = minVal; val <= maxVal; val++) {
            smallDomain.add(val);
        }
        
        // init Real Domain for Big Buffaloes
        RealDomain bigDomain = new RealDomain();
        maxVal = numOfGrassPkg/2;
        for (int val = minVal; val <= maxVal; val++) {
            bigDomain.add(val);
        }
        
        // init Real Domain for Old Buffaloes
        RealDomain olDomain = new RealDomain();
        maxVal = numOfGrassPkg*2;
        for (int val = minVal; val <= maxVal; val += 2) {
            olDomain.add(val);
        }
        
        // init VarSet
        VarSet varSet = new VarSet();
        IndexDomain D = new IndexDomain(smallDomain);
        varSet.add(new Variable(D));
        D = new IndexDomain(bigDomain);
        varSet.add(new Variable(D));
        D = new IndexDomain(olDomain);
        varSet.add(new Variable(D));

        // init evaluator
        Evaluator evaluator = problem.evaluator;

        // create solver
        Solver solver = new Solver(varSet, evaluator);
        ArrayList<Proposal> solutions;
        solutions = solver.getAllSolution();
        
        int n = solutions.size();
        if (n > 0) {
            System.out.println(n + " solutions were detected:");
            System.out.println("      [small, big, old]");
            for (int i = 0; i < n; i++) {
                System.out.println("A solution:" + solutions.get(i));
            }
        } else {
            System.out.println("No solution can be detected!");
        }
    }
}
