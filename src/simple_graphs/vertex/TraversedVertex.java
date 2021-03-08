package simple_graphs.vertex;

/**
 *
 * @author tackedev
 * @since  Mar 4, 2021 9:06:03 PM
 */
public class TraversedVertex implements Comparable<TraversedVertex> {
    
    public Vertex v;
    public int num;

    public TraversedVertex(Vertex v, int num) {
        this.v = v;
        this.num = num;
    }

    @Override
    public int compareTo(TraversedVertex t) {
        return num < t.num ? -1 : num > t.num ? 1 : 0;
    }

    @Override
    public String toString() {
        return "(" + v.key + "," + v.num + ")";
    }
}
