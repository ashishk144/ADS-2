import java.util.Scanner;
/**
 * Class for solution.
 */
public class Solution {
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
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int k = Integer.parseInt(scan.nextLine());
        String[] inp;
        Edge e;
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(n);
        for (int i = 0; i < k; i++) {
            inp = scan.nextLine().split(" ");
            if (!inp[0].equals(inp[1])) {
                e = new Edge(Integer.parseInt(inp[0]),
                             Integer.parseInt(inp[1]),
                             Integer.parseInt(inp[2]));
                ewg.addEdge(e);
            }
        }
        String caseToGo = scan.nextLine();
        try {
            switch (caseToGo) {
            case "Graph":
                //Print the Graph Object.
                System.out.println(ewg);
                break;

            case "DirectedPaths":
                // Handle the case of DirectedPaths, where two integers are
                // given.
                // First is the source and second is the destination.
                // If the path exists print the distance between them.
                // Other wise print "No Path Found."
                DijkstraUndirectedSP dj;
                while (scan.hasNextLine()) {
                    inp = scan.nextLine().split(" ");
                    dj = new DijkstraUndirectedSP(ewg, Integer.parseInt(
                                                      inp[0]));
                    if (dj.hasPathTo(Integer.parseInt(inp[1]))) {
                        System.out.println(dj.distTo(Integer.parseInt(
                                                         inp[1])));
                    } else {
                        throw new Exception("No Path Found.");
                    }
                }
                break;

            case "ViaPaths":
                // Handle the case of ViaPaths, where three integers are given.
                // First is the source and second is the via is the one where
                // path should pass throuh.
                // third is the destination.
                // If the path exists print the distance between them.
                // Other wise print "No Path Found."
                while (scan.hasNextLine()) {
                    inp = scan.nextLine().split(" ");
                    int a = Integer.parseInt(inp[0]);
                    int b = Integer.parseInt(inp[1]);
                    int c = Integer.parseInt(inp[2]);
                    dj = new DijkstraUndirectedSP(ewg, a);
                    double sum = 0;
                    if (dj.hasPathTo(b)) {
                        sum += dj.distTo(b);
                        String s = a + " ";
                        int vert = a;
                        for(Edge edg: dj.pathTo(b)) {
                            s += edg.other(vert) + " ";
                            vert = edg.other(vert);
                        }
                        dj = new DijkstraUndirectedSP(ewg, b);
                        if (dj.hasPathTo(c)) {
                            sum += dj.distTo(c);
                            for(Edge edg: dj.pathTo(c)) {
                                s += edg.other(vert) + " ";
                                vert = edg.other(vert);
                            }
                            System.out.println(sum);
                            System.out.println(s.trim());
                        } else {
                            throw new Exception("No Path Found.");
                        }
                    } else {
                        throw new Exception("No Path Found.");
                    }
                }
                break;

            default:
                break;
            }
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

    }
}