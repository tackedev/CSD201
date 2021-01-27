package backtracking.core;

/**
 *
 * @author tackedev
 * @since Jan 25, 2021 3:50:57 PM
 */
public class Variable {
    
    private IndexDomain indexDomain;
    private int d_Index = -1; //index in domain is assigned to this var
    private double cost = 0; // cost when this var is assigned a value

    public Variable(IndexDomain indexDomain) {
        this.indexDomain = indexDomain;
    }

    public Variable(IndexDomain indexDomain, double cost) {
        this.indexDomain = indexDomain;
        this.cost = cost;
    }

    public IndexDomain getIndexDomain() {
        return indexDomain;
    }

    public void setIndexDomain(IndexDomain indexDomain) {
        this.indexDomain = indexDomain;
    }

    public int getD_Index() {
        return d_Index;
    }

    public void setD_Index(int d_Index) {
        this.d_Index = d_Index;
    }
}
