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
     * Main function to handle inputs and deliver outputs.
     *
     * @param      args  The arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int vert = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vert);
        for (int i = 0; i < edges; i++) {
            String[] inp = scan.nextLine().split(" ");
            Edge e = new Edge (Integer.parseInt(inp[0]), Integer.parseInt(
                inp[1]), Double.parseDouble(inp[2]));
            ewg.addEdge(e);
        }
        LazyPrimMST mst = new LazyPrimMST(ewg);
        System.out.printf("%.5f\n", mst.weight());
    }
}