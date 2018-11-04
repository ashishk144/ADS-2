import java.util.HashSet;
/**
 * Class for sap.
 */
public class SAP {
    /**
     * { var_description }.
     */
    private Digraph graph;
    /**
     * { var_description }.
     */
    private Integer dist;
    /**
     * { var_description }.
     */
    private Integer ancestor;
    // constructor takes a digraph (not necessarily a DAG)

    /**
     * Constructs the object.
     *
     * @param      G     { parameter_description }
     */
    public SAP(final Digraph G) {
        this.graph = G;
        dist = Integer.MAX_VALUE;
    }

    // length of shortest ancestral path between v
    // and w; -1 if no such path

    /**
     * { function_description }
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final int v, final int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(
            this.graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(
            this.graph, w);
        HashSet<Integer> s1 = new HashSet<Integer>();
        HashSet<Integer> s2 = new HashSet<Integer>();
        for (int i = 0; i < this.graph.vertices(); i++) {
            if (bfs1.hasPathTo(i)) {
                s1.add(i);
            }
            if (bfs2.hasPathTo(i)) {
                s2.add(i);
            }
        }
        s1.retainAll(s2);
        for (Integer i : s1) {
            if (dist >= bfs1.distTo(i) + bfs2.distTo(i)) {
                dist = bfs1.distTo(i) + bfs2.distTo(i);
                ancestor = i;
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return dist;
    }

    // a common ancestor of v and w that participates in a shortest
    //ancestral path; -1 if no such path

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(
            this.graph, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(
            this.graph, w);
        HashSet<Integer> s1 = new HashSet<Integer>();
        HashSet<Integer> s2 = new HashSet<Integer>();
        for (int i = 0; i < this.graph.vertices(); i++) {
            if (bfs1.hasPathTo(i)) {
                s1.add(i);
            }
            if (bfs2.hasPathTo(i)) {
                s2.add(i);
            }
        }
        s1.retainAll(s2);
        for (Integer i : s1) {
            if (dist >= bfs1.distTo(i) + bfs2.distTo(i)) {
                dist = bfs1.distTo(i) + bfs2.distTo(i);
                ancestor = i;
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and
    // any vertex in w; -1 if no such path

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v,
        final Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                int tdist = length(i, j);
                if (tdist != -1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return dist;
    }

    // a common ancestor that participates in shortest
    // ancestral path; -1 if no such path

    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for (int i : v) {
            for (int j : w) {
                int tdist = length(i, j);
                if (tdist != -1 && dist >= tdist) {
                    dist = tdist;
                    ancestor = ancestor(i, j);
                }
            }
        }
        if (dist == Integer.MAX_VALUE) {
            return -1;
        }
        return ancestor;
    }
}
