package simple_graphs;

import java.util.Iterator;
import java.util.LinkedList;
import simple_graphs.com.OrderedList;
import simple_graphs.vertex.AdjInfo;
import simple_graphs.vertex.TraversedVertex;
import simple_graphs.vertex.Vertex;

/**
 *
 * @author tackedev
 * @since  Mar 4, 2021 9:11:46 PM
 */
public class GraphTraverser {
    
    AbstractGraph graph;

    public GraphTraverser(AbstractGraph graph) {
        this.graph = graph;
    }
    
    public OrderedList<TraversedVertex> breadthFirstTraversal() {
        OrderedList<TraversedVertex> result = new OrderedList<>();
        
        // reset visiting order
        graph.resetNum();
        Iterator vSet = graph.iterator();
        LinkedList<Vertex> queue = new LinkedList<>();
        
        int i = 1;
        while (vSet.hasNext()) {
            Vertex v = (Vertex) vSet.next();
            if (v.num == 0) {
                v.num = i++;
                result.add(new TraversedVertex(v, v.num));
                queue.addLast(v);
                
                while (!queue.isEmpty()) {
                    v = queue.removeFirst();
                    for (int pos = 0; pos < v.adjList.size(); pos++) {
                        Vertex adjV = ((AdjInfo) v.adjList.get(pos)).dest;
                        if (adjV.num == 0) {
                            adjV.num = i++;
                            result.add(new TraversedVertex(adjV, adjV.num));
                            queue.addLast(adjV);
                        }
                    }
                }
            }
        }
        return result;
    }
    
    protected int DFS(Vertex u, int order, OrderedList<TraversedVertex> result) {
        u.num = order++;
        result.add(new TraversedVertex(u, u.num));
        OrderedList<AdjInfo> adjList = u.adjList;
        for (int pos = 0; pos < adjList.size(); pos++) {
            Vertex adjV = ((AdjInfo) adjList.get(pos)).dest;
            if (adjV.num == 0) {
                order = DFS(adjV, order, result);
            }
        }
        return order;
    }
    
    public OrderedList<TraversedVertex> DepthFirstTraversal() {
        graph.resetNum();
        OrderedList<TraversedVertex> result = new OrderedList<>();
        int order = 1;
        Iterator vSet = graph.iterator();
        while (vSet.hasNext()) {
            Vertex u = (Vertex) vSet.next();
            if (u.num == 0) {
                order = DFS(u, order, result);
            }
        }
        return result;
    }
}
