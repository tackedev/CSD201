package simple_graphs.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import simple_graphs.AbstractGraph;
import simple_graphs.GraphTraverser;
import simple_graphs.com.OrderedList;
import simple_graphs.vertex.TraversedVertex;

/**
 *
 * @author tackedev
 * @since  Mar 4, 2021 9:41:20 PM
 */
public class UnDirectedGraph extends AbstractGraph {
    
    String filename;

    public UnDirectedGraph(String filename) {
        super(AbstractGraph.UNDIRECTED);
        this.filename = filename;
    }
    
    @Override
    public boolean loadGraph() {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("The file " + filename + " doesn't existed!");
            System.exit(0);
        }
        StringTokenizer st;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            // read Vertices List
            String line = br.readLine();
            st = new StringTokenizer(line, " ");
            while (st.hasMoreTokens()) {
                String vKey = st.nextToken();
                this.addVertex(vKey);
            }
            
            // read adjacents
            while ((line = br.readLine()) != null && line.length() > 0) {
                st = new StringTokenizer(line, " ");
                String srcKey = st.nextToken();
                while (st.hasMoreTokens()) {
                    String destKey = st.nextToken();
                    this.addAdj(srcKey, destKey);
                }
            }
            
            br.close(); fr.close();
        } catch (IOException e) {
            System.exit(0);
        } 
        
        return true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "unDirectedGraph.txt";
        UnDirectedGraph graph = new UnDirectedGraph(filename);
        graph.loadGraph();
        System.out.println("Graph properties:");
        System.out.println(graph);
        
        GraphTraverser processer = new GraphTraverser(graph);
        System.out.println("Breadth-First traversal:");
        OrderedList<TraversedVertex> vList = processer.breadthFirstTraversal();
        System.out.println(vList);
        System.out.println("Depth-First traversal:");
        vList = processer.DepthFirstTraversal();
        System.out.println(vList);
    }
}
