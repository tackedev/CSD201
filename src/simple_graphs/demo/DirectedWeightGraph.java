package simple_graphs.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import simple_graphs.AbstractGraph;
import simple_graphs.DJ_SP_Result;
import simple_graphs.DJ_Shortest_Processor;

/**
 *
 * @author tackedev
 * @since Mar 7, 2021 9:14:58 PM
 */
public class DirectedWeightGraph extends AbstractGraph {
    
    String filename;

    public DirectedWeightGraph(String filename) {
        super(AbstractGraph.DIRECTED_WEIGHTED);
        this.filename = filename;
    }

    @Override
    public boolean loadGraph() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("The file " + filename + " doesn't existed!");
            System.exit(0);
        }
        
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            StringTokenizer st1 = new StringTokenizer(line, " ");
            while (st1.hasMoreTokens()) {
                String vKey = st1.nextToken();
                this.addVertex(vKey);
            }
            while ((line = br.readLine()) != null && line.length() > 0) {
                st1 = new StringTokenizer(line, " ");
                String srckey = st1.nextToken();
                while (st1.hasMoreTokens()) {
                    String part = st1.nextToken();
                    StringTokenizer st2 = new StringTokenizer(part, "(), ");
                    String destKey = st2.nextToken();
                    double weight = Double.parseDouble(st2.nextToken());
                    this.addAdj(srckey, destKey, weight);
                }
            }
            
            br.close(); fr.close();
        } catch (IOException | NumberFormatException e) {
            System.exit(0);
        }
        
        return true;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "Dijskstra_demo.txt";
        DirectedWeightGraph graph = new DirectedWeightGraph(filename);
        graph.loadGraph();
        System.out.println("Graph properties:");
        System.out.println(graph);
        
        DJ_Shortest_Processor processor = new DJ_Shortest_Processor(graph);
        System.out.println("Shortest paths, Dijktra algorithm:");
        DJ_SP_Result result = processor.createDJShortestResult("d");
        if (result == null) {
            System.out.println("FAIL!");
        } else {
            System.out.println(result);
        }
    }
}
