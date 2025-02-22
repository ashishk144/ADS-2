import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;


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
            if (dig.outdegree(i) == 0) {
                for (int j = 0; j < this.dig.vertices(); j++) {
                    if (i != j) {
                        this.dig.addEdge(i, j);
                    }
                }
            }
            prstore.put(i, pr);
            tr.put(i, pr);
        }
        Digraph f = this.dig.reverse();
        double fpr = 0.0;
        for (int j = 0; j < 1000; j++) {
            for (int i = 0; i < this.dig.vertices(); i++) {
                fpr = 0.0;
                for (int k : f.adj(i)) {
                    fpr += (tr.get(k) * 1.0) / (this.dig.outdegree(k) * 1.0);
                }
                prstore.put(i, fpr);
            }
            for (int i = 0; i < this.dig.vertices(); i++) {
                tr.put(i, prstore.get(i));
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

class WebSearch {
    private PageRank pgS;
    /**
    * hash map for the words in the content.
    */
    private HashMap<String, ArrayList<Integer>> webCont;
    /**
    * Constructs the object.
    *
    * @param      pg1       The page 1
    * @param      filename  The filename
    */
    WebSearch(final PageRank pg1, final String filename) {
        this.pgS = pg1;
        try {
          File file = new File(filename);
          Scanner sc = new Scanner(file);
          webCont = new HashMap<>();
          while (sc.hasNextLine()) {
            String[] temp = sc.nextLine().split(":");
            for (String word : temp[1].split(" ")) {
              webCont.putIfAbsent(word, new ArrayList<Integer>());
              webCont.get(word).add(Integer.parseInt(temp[0]));
            }
          }
        } catch (IOException ioe) {
          throw new IllegalArgumentException("Could not open");
        }
    }
    /**
     * { function_description }
     *
     * @param      query  The query
     *
     * @return     { description_of_the_return_value }
     */
    public int iAmFeelingLucky(final String query) {
        if (webCont.containsKey(query)) {
          double max = 0.0;
          int mpage = 0;
          for (Integer page : webCont.get(query)) {
            double temp = pgS.getPR(page);
            if (temp >= max) {
                max = temp;
                mpage = page;
            }
          }
          return mpage;
        } else {
          return -1;
        }
    }
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
        System.out.println(g);
        // Create page rank object and pass the graph object to the constructor
        PageRank pr = new PageRank(g);
        // print the page rank object
        System.out.println(pr);
        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        WebSearch web = new WebSearch(pr, file);
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
        while (scan.hasNext()) {
          String qr = scan.nextLine();
          String query = qr.substring(2, qr.length());
          System.out.println(web.iAmFeelingLucky(query));
        }

    }
}
