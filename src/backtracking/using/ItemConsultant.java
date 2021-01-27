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
 * @since Jan 25, 2021 9:50:00 PM
 */
public class ItemConsultant {
    public double budget;
    public VarSet varSet;
    public Evaluator evaluator;

    public ItemConsultant(double budget, VarSet varSet) {
        this.budget = budget;
        this.varSet = varSet;
        evaluator = new ItemSolutionEvaluator();
    }
    
    
    
    public class ItemSolutionEvaluator implements Evaluator {
        
        private double computeCost(Proposal p) {
            double totalCost = 0;
            ArrayList list = p.getRealObjects(varSet);
            for (Object x: list) {
                totalCost += ((Item) x).getPrice();
            }
            p.setTotalCost(totalCost);
            return totalCost;
        }
        
        @Override
        public boolean isSatisfied(Proposal p) {
            return computeCost(p) <= budget;
        }
    }
    
    public void print(Proposal p) {
        ArrayList list = p.getRealObjects(varSet);
        System.out.println("A solution:");
        for (Object x: list) {
            System.out.println((Item) x);
        }
        System.out.println("Cost:" + p.getTotalCost());
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // init Real Domain TV
        RealDomain<Item> tvDomain = new RealDomain<>();
        tvDomain.add(new Item("TV01", "Sony 42",     4800000));
        tvDomain.add(new Item("TV02", "LG 42",       3800000));
        tvDomain.add(new Item("TV03", "Samsung 48",  7500000));
        tvDomain.add(new Item("TV04", "Sony 55",    14800000));
        tvDomain.add(new Item("TV05", "LG 65",      16800000));
        tvDomain.add(new Item("TV06", "Samsung 55", 11200000));
        
        // init Real Domain Refregerator
        RealDomain<Item> rfDomain = new RealDomain<>();
        rfDomain.add(new Item("RF01", "National 420", 11800000));
        rfDomain.add(new Item("RF02", "General 380",  10800000));
        rfDomain.add(new Item("RF03", "Panasonic 180", 3500000));
        rfDomain.add(new Item("RF04", "Hitachi 380",   9800000));
        rfDomain.add(new Item("RF05", "Beko 250",      6700000));
        
        // init VarSet
        VarSet varSet = new VarSet();
        IndexDomain D = new IndexDomain(tvDomain);
        varSet.add(new Variable(D)); // TV1
        D = new IndexDomain(tvDomain);
        varSet.add(new Variable(D)); // TV2
        D = new IndexDomain(tvDomain);
        varSet.add(new Variable(D)); // TV3
        D = new IndexDomain(rfDomain);
        varSet.add(new Variable(D)); // RF1
        D = new IndexDomain(rfDomain);
        varSet.add(new Variable(D)); // RF2
        
        // init problem
        double budget = 40000000;
        ItemConsultant problem = new ItemConsultant(budget, varSet);
        Evaluator evaluator = problem.evaluator;
        
        // create Solver
        Solver solver = new Solver(varSet, evaluator);
        ArrayList<Proposal> solutions;
        solutions = solver.getAllSolution();
        
        int n = solutions.size();
        if (n > 0) {
            System.out.println(n + " solutions were detected for the budget of: " + budget);
            System.out.println("Proposal:");
            for (int i = 0; i < n; i++) {
                problem.print(solutions.get(i));
            }
        } else {
            System.out.println("No solution can be detected!");
        }
    }
}
