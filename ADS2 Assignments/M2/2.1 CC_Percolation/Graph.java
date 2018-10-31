/**
 * List of graphs.
 */
class Graph {
    /**
     * vertices.
     */
    private int vertices;
    /**
     * edges.
     */
    private int edge;
    /**
     * bag.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v1    The v 1
     */
    Graph(final int v1) {
        this.vertices = v1;
        this.edge = 0;
        this.adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * vertices.
     * Complexity 1
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     * number of edges.
     * Complexity 1
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return this.edge;
    }
    /**
     * Adds an edge.
     * Complexity 1
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (!hasEdge(v, w) && v != w) {
            edge++;
            adj[v].add(w);
            adj[w].add(v);
        }
    }
    /**
     * iterable.
     * Complexity 1
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     * Complexity (E)
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        int count = 0;
        for (int i : adj[v]) {
            if (i == w) {
                count += 1;
                break;
            }
        }
        for (int i : adj[w]) {
            if (i == v) {
                count += 1;
                break;
            }
        }
        if (count == 2) {
            return true;
        }
        return false;
    }
    /**
     * display.
     *
     * @param      data  The data
     *
     * @return     { description_of_the_return_value }
     */
    public String display(final String[] data) {
        String s = "";
        s += vertices + " vertices, " + edge + " edges" + '\n';
        if (edge == 0) {
            s += "No edges ";
        } else {
            for (int v = 0; v < vertices; v++) {
                s += data[v] + ": ";
                for (int w : adj[v]) {
                    s += data[w] + " ";
                }
                s += '\n';
            }
        }
        return s.substring(0, s.length() - 1);
    }
}
