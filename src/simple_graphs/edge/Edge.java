package simple_graphs.edge;

import simple_graphs.vertex.Vertex;

/**
 *
 * @author tackedev
 * @since  Mar 6, 2021 1:59:21 PM
 */
public class Edge {
    public Vertex src;
    public Vertex dest;
    public double weight;

    public Edge(Vertex src, Vertex dest) {
        this.src = src;
        this.dest = dest;
        this.weight = 1;
    }

    public Edge(Vertex src, Vertex dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + src.key + "," + dest.key + "," + weight + ")";
    }
}
