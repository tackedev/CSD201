package simple_graphs.vertex;

import simple_graphs.com.OrderedList;

/**
 *
 * @author tackedev
 * @since  Mar 1, 2021 5:28:52 PM
 */
public class Vertex implements Comparable {
    public String key;
    public String name;
    public int num;
    public double pathLen;
    public Vertex predecessor;
    public OrderedList adjList;

    public Vertex(String key) {
        this.key = key;
        this.name = "";
        this.num = 0;
        this.pathLen = 0;
        this.predecessor = null;
        this.adjList = new OrderedList();
    }

    public Vertex(String key, String name) {
        this.key = key;
        this.name = name;
        this.num = 0;
        this.pathLen = 0;
        this.predecessor = null;
        this.adjList = new OrderedList();
    }

    @Override
    public int compareTo(Object obj) {
        return this.key.compareTo(((Vertex) obj).key);
    }

    @Override
    public String toString() {
        return "(" + key + "," + name + ", adjs:" + adjList + ")";
    }
}
