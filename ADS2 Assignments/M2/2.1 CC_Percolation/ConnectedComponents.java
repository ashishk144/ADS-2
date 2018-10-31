/**
 * Class for connected components.
 */
public class ConnectedComponents {
    /**
     * marked.
     */
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    /**
     * id.
     */
    private int[] id;
    /**
     * size.
     */
    private int[] size;
    /**
     * count.
     */
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code graph}.
     * Complexity V.
     * @param graph the undirected graph
     */
    public ConnectedComponents(final Graph graph) {
        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        size = new int[graph.vertices()];
        for (int v = 0; v < graph.vertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    /**
     * depth-first search for a Graph.
     * Complexity E.
     * @param      graph  The graph
     * @param      v      { parameter_description }
     */
    private void dfs(final Graph graph, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }


    /**
     * Returns the component id
     * of the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the component id of
     * the connected component containing vertex {@code v}
     * unless {@code 0 <= v < V}
     */
    public int id(final int v) {
        return id[v];
    }

    /**
     * Returns the number of vertices
     * in the connected component containing vertex {@code v}.
     *
     * @param  v the vertex
     * @return the number of vertices
     * in the connected component containing vertex {@code v}
     */
    public int size(final int v) {
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the same
     *         connected component; {@code false} otherwise
     */
    public boolean connected(final int v, final int w) {
        return id(v) == id(w);
    }
}
