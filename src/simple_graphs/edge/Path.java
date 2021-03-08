package simple_graphs.edge;

import java.util.LinkedList;

/**
 *
 * @author tackedev
 * @since  Mar 6, 2021 2:03:14 PM
 */
public class Path extends LinkedList<Edge> {
    public double pathLen;

    public Path() {
        super();
        pathLen = 0;
    }
}
