/**
 * Class for graph matrix.
 */
class GraphMatrix implements Graph {
    /**
     * number of vertices.
     */
    private int vertices;
    /**
     * number of edges.
     */
    private int edges;
    /**
     * matrix.
     */
    private int[][] matrix;
    /**
     * Constructs the object.
     *
     * @param      v1    The v1.
     */
    GraphMatrix(final int v1) {
        this.vertices = v1;
        this.edges = 0;
        this.matrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    /**
     * Iterable.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return null;
    }
    /**
     * number of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     * number of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
        return this.edges;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (!hasEdge(v, w) && v != w) {
            edges++;
        }
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return (matrix[v][w] == 1);
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        s += vertices + " vertices, " + edges + " edges" + '\n';
        if (edges == 0) {
            s += "No edges ";
        } else {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    s += matrix[i][j] + " ";
                }
                s = s.substring(0, s.length());
                s += ('\n');
            }
        }
        return s.substring(0, s.length() -  1);
    }
}
