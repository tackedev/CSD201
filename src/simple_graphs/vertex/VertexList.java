package simple_graphs.vertex;

import simple_graphs.com.OrderedList;

/**
 *
 * @author tackedev
 * @since  Mar 1, 2021 5:38:39 PM
 */
public class VertexList extends OrderedList<Vertex> {
    public VertexList () {
        super();
    }
    
    public Vertex search(String key) {
        Vertex v = new Vertex(key);
        return search(v);
    }
    
    public Vertex addVertex(String key) {
        Vertex v = new Vertex(key);
        Vertex t = search(v);
        if (t != null) {
            return t;
        }
        this.add(v);
        return v;
    } 
    
    public Vertex addVertex(String key, String name) {
        Vertex v = new Vertex(key, name);
        Vertex t = search(v);
        if (t != null) {
            return t;
        }
        this.add(v);
        return v;
    }
    
    public void resetNum() {
        this.forEach(v -> {
            ((Vertex) v).num = 0;
        });
    }
    
    @Override
    public String toString() {
        String result = "";
        for (Comparable v : this) {
            result += ((Vertex) v).toString() + "\n";
        }
        return result;
    }
}
