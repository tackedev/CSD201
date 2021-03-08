package simple_graphs.vertex;

/**
 *
 * @author tackedev
 * @since  Mar 1, 2021 5:40:27 PM
 */
public class AdjInfo implements Comparable<AdjInfo> {
    public Vertex dest;
    public double weight;

    public AdjInfo(Vertex dest) {
        this.dest = dest;
        this.weight = 1;
    }

    public AdjInfo(Vertex dest, double weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(AdjInfo obj) {
        return dest.compareTo(obj.dest);
    }

    @Override
    public String toString() {
        return "(" + dest.key + "," + weight + ")";
    }
}
