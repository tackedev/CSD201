package backtracking.core;

import java.util.ArrayList;

/**
 *
 * @author tackedev
 * @since Jan 25, 2021 4:24:03 PM
 */
public class Solver {
    private VarSet varSet;
    Evaluator evaluator;

    public Solver(VarSet varSet, Evaluator evaluator) {
        this.varSet = varSet;
        this.evaluator = evaluator;
    }
    
    private void getNSolution(int varIndex, int n, ArrayList<Proposal> solutions) {
        if (solutions.size() == n) return;
        Variable v = varSet.get(varIndex);
        IndexDomain indexDomain = v.getIndexDomain();
        indexDomain.reset();
        
        while (indexDomain.hasNext()) {
            v.setD_Index(indexDomain.nextIndex());
            if (varIndex == varSet.size()-1) {
                // Last variable
                Proposal prop = new Proposal();
                for (Variable var: varSet) {
                    prop.add(var.getD_Index());
                }
                
                if (solutions.size() < n) {
                    if (evaluator.isSatisfied(prop)) {
                        solutions.add(prop);
                    }
                } else {
                    return;
                }
            } else {
                getNSolution(varIndex + 1, n, solutions);
            }
        }
    }
    
    public ArrayList<Proposal> getNSolution(int n) {
        ArrayList<Proposal> solutions = new ArrayList<>();
        int varIndex = 0;
        getNSolution(varIndex, n, solutions);
        return solutions;
    }
    
    public Proposal getOneSolution() {
        ArrayList<Proposal> solutions;
        solutions = getNSolution(1);
        return solutions.get(0);
    }
    
    private void getAllSolution(int varIndex, ArrayList<Proposal> solutions) {
        Variable v = varSet.get(varIndex);
        IndexDomain indexDomain = v.getIndexDomain();
        indexDomain.reset();
        
        while (indexDomain.hasNext()) {
            v.setD_Index(indexDomain.nextIndex());
            if (varIndex == varSet.size()-1) {
                // Last variable
                Proposal prop = new Proposal();
                for (Variable var: varSet) {
                    prop.add(var.getD_Index());
                }
                
                if (evaluator.isSatisfied(prop)) {
                    solutions.add(prop);
                }
            } else {
                getAllSolution(varIndex + 1, solutions);
            }
        }
    }
    
    public ArrayList<Proposal> getAllSolution() {
        ArrayList<Proposal> solutions = new ArrayList<>();
        int varIndex = 0;
        getAllSolution(varIndex, solutions);
        return solutions;
    }
}