package backtracking.core;

import java.util.ArrayList;

/**
 *
 * @author tackedev
 * @since Jan 25, 2021 3:56:25 PM
 */
public class Proposal extends ArrayList<Integer> implements Comparable<Proposal> {

    private double totalCost;

    public Proposal() {
        super();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public int compareTo(Proposal obj) {
        return totalCost < obj.getTotalCost() ? -1 : 
                totalCost > obj.getTotalCost() ? 1 : 0;
    }
    
    public ArrayList getRealObjects(VarSet varSet) {
        ArrayList realProposal = new ArrayList();
        for (int i = 0; i < varSet.size(); i++) {
            Variable v = varSet.get(i);
            int realIndex = this.get(i);
            IndexDomain indexDomain = v.getIndexDomain();
            realProposal.add(indexDomain.getRealValue(realIndex));
        }
        return realProposal;
    }
}
