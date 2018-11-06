import java.util.HashMap;
import java.util.Scanner;

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * Main file to handle inputs and deliver outputs.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] v = scan.nextLine().split(" ");
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(Integer.parseInt(v[0]));
        HashMap<String, Integer> ids = new HashMap<String, Integer>();
        String[] inp = scan.nextLine().split(" ");
        for (int i = 0; i < inp.length; i++) {
            ids.put(inp[i], i);
        }
        for (int i = 0; i < Integer.parseInt(v[1]); i++) {
            inp = scan.nextLine().split(" ");
            Edge e = new Edge(ids.get(inp[0]), ids.get(inp[1]),
                Integer.parseInt(inp[2]));
            ewg.addEdge(e);
        }
        int iter = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < iter; i++) {
            inp = scan.nextLine().split(" ");
            DijkstraUndirectedSP djsp = new DijkstraUndirectedSP(ewg,
                ids.get(inp[0]));
            System.out.println((int)djsp.distTo(ids.get(inp[1])));
        }
    }
}
