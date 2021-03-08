package simple_graphs;

import java.util.Iterator;
import simple_graphs.vertex.AdjInfo;
import simple_graphs.vertex.Vertex;

/**
 *
 * @author tackedev
 * @since  Mar 6, 2021 2:31:24 PM
 */
public class DJ_Shortest_Processor extends GraphTraverser {
    
    public static final double INFINITY = Double.MAX_VALUE;

    public DJ_Shortest_Processor(AbstractGraph graph) {
        super(graph);
    }
    
    private void DJreset() {
        for (Comparable obj: graph) {
            Vertex v = (Vertex) obj;
            v.num = 0;
            v.pathLen = INFINITY;
            v.predecessor = null;
        }
    }
    
    private Vertex getMinLenVertex() {
        Vertex result = null;
        Iterator it = graph.iterator();
        while (it.hasNext()) {
            Vertex v = (Vertex) it.next();
            if (v.num == 0) {
                if (result == null) {
                    result = v;
                } else if (result.pathLen > v.pathLen) {
                    result = v;
                }
            }
        }
        return result;
    }
    
    private void DJ_Algoritm(Vertex startVertex) {
        DJreset();
        startVertex.pathLen = 0;
        Vertex curVertex = startVertex;
        Vertex predecessor;
        double curLen, newLen;
        while (curVertex != null) {
            curVertex.num = 1;
            predecessor = curVertex;
            curLen = curVertex.pathLen;
            
            // get adjacents
            Iterator adjs = curVertex.adjList.iterator();
            while (adjs.hasNext()) {
                AdjInfo adj = (AdjInfo) adjs.next();
                Vertex v = adj.dest;
                newLen = curLen + adj.weight;
                if (v.num == 0 && newLen < v.pathLen) {
                    v.pathLen = newLen;
                    v.predecessor = predecessor;
                }
            }
            curVertex = getMinLenVertex();
        }
    }
    
    public DJ_SP_Result createDJShortestResult(Vertex startVertex) {
        DJ_Algoritm(startVertex);
        
        DJ_SP_Result result = new DJ_SP_Result(graph, startVertex);
        
        result.setupPath();
       
        return result;
    }
    
    public DJ_SP_Result createDJShortestResult(String startKey) {
        Vertex startVertex = graph.search(startKey);
        if (startKey == null) {
            return null;
        }
        return createDJShortestResult(startVertex);
    }
}
