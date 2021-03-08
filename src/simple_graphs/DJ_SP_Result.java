package simple_graphs;

import java.util.ArrayList;
import simple_graphs.edge.Edge;
import simple_graphs.edge.Path;
import simple_graphs.vertex.AdjInfo;
import simple_graphs.vertex.Vertex;

/**
 *
 * @author tackedev
 * @since  Mar 6, 2021 2:04:58 PM
 */
public class DJ_SP_Result {
    Vertex start;
    AbstractGraph graph;
    ArrayList<Path> paths;

    public DJ_SP_Result(AbstractGraph graph, Vertex start) {
        this.start = start;
        this.graph = graph;
        paths = new ArrayList<>();
        graph.forEach(_item -> {
            paths.add(new Path());
        });
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < graph.size(); i++) {
            result += "From " + start.key + " to " + ((Vertex) graph.get(i)).key + ", len: "
                    + paths.get(i).pathLen + ": " + paths.get(i) + "\n";
        }
        return result;
    }
    
    public void setupPath(int vertexIndex) {
        Vertex dest = (Vertex) graph.get(vertexIndex);
        Path path = paths.get(vertexIndex);
        path.pathLen = dest.pathLen;
        AdjInfo adj;
        Edge edge;
        
        // add edges to the path
        Vertex src = dest.predecessor;
        while (src != null) {
            adj = graph.getAdjInfo(src, dest);
            edge = new  Edge(src, dest, adj.weight);
            path.addFirst(edge);
            dest = src;
            src = dest.predecessor;
        }
    }
    
    public void setupPath() {
        for (int i = 0; i < graph.size(); i++) {
            setupPath(i);
        }
    }
}
