import java.util.Scanner;

interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}

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
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        String[] input = scan.nextLine().split(",");
        if (type.equals("List")) {
            GraphList g = new GraphList(vertices);
            for (int i = 0; i < edges; i++) {
                String[] add = scan.nextLine().split(" ");
                int a = Integer.parseInt(add[0]);
                int b = Integer.parseInt(add[1]);
                if (a != b && !g.hasEdge(a, b)) {
                    g.addEdge(a, b);
                }
            }
            // if (edges > 0) {
            System.out.println(g.display(input));
            // } else {
            //     System.out.println(vertices + " vertices, " + edges + " edges");
            //     System.out.println("No edges");
            // }
        }
    }
}
