import java.util.Scanner;
import java.util.TreeMap;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * { var_description }.
     */
    private Digraph dig;
    /**
     * { var_description }.
     */
    private double[] pageranks;
    /**
     * { var_description }.
     */
    private TreeMap<Integer, Double> prstore;
    /**
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     */
    PageRank(final Digraph g) {
        this.dig = g;
        this.pageranks = new double[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            if (g.outdegree(i) == 0) {
                for (int j = 0; j < g.vertices(); j++) {
                    if(i != j) {
                        g.addEdge(i, j);
                    }
                }
            }
            pageranks[i] = getPR(i);
        }
    }
    /**
     * Gets the pr.
     *
     * @param      v     { parameter_description }
     *
     * @return     The pr.
     */
    double getPR(final int v) {
        prstore = new TreeMap<Integer, Double>();
        double pr = 1.0 / this.dig.vertices();
        TreeMap<Integer, Double> tr = new TreeMap<Integer, Double>();
        for (int i = 0; i < this.dig.vertices(); i++) {
            prstore.put(i, pr);
            tr.put(i, pr);
        }
        Digraph f = this.dig.reverse();
        double fpr = 0.0;
        int count;
        for (int j = 0; j < 104; j++) {
            for (int i = 0; i < this.dig.vertices(); i++) {
                fpr = 0.0;
                for (int k : f.adj(i)) {
                    fpr += tr.get(k) / this.dig.outdegree(k);
                }
                prstore.put(i, fpr);
            }
            count = 0;
            for (int i = 0; i < this.dig.vertices(); i++) {
                if (tr.get(i) == prstore.get(i)) {
                    count++;
                }
                tr.put(i, prstore.get(i));
            }
            if(count == this.dig.vertices()) {
                break;
            }
        }
        return prstore.get(v);
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < pageranks.length; i++) {
            s += i + " - " + pageranks[i] + "\n";
        }
        return s;
    }
}

// class WebSearch {

// }

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
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // read the first line of the input to get the number of vertices
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph
        Digraph g = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String[] s = scan.nextLine().split(" ");
            for (int j = 1; j < s.length; j++) {
                g.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[j]));
            }
        }

        // Create page rank object and pass the graph object to the constructor
        PageRank pr = new PageRank(g);
        // print the page rank object
        System.out.println(g);
        System.out.println(pr);
        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}
