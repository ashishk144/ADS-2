import java.util.Scanner;
import java.util.TreeMap;

class PageRank {
    private Digraph dig;
    private double[] pageranks;
    TreeMap<Integer, Double> prstore;
    PageRank(Digraph g) {
        this.dig = g;
        this.pageranks = new double[g.vertices()];
        for (int i = 0; i < g.vertices(); i++) {
            pageranks[i] = GetPR(i);
        }
    }
    double GetPR(int v) {
        prstore = new TreeMap<Integer, Double>();
        double pr = 1.0 / this.dig.vertices();
        TreeMap<Integer, Double> tr = new TreeMap<Integer, Double>();
        for (int i = 0; i < this.dig.vertices(); i++) {
            prstore.put(i, pr);
            tr.put(i, pr);
        }
        Digraph f = this.dig.reverse();
        double fpr = 0.0;
        for (int j = 0; j < 104; j++) {
            for (int i = 0; i < this.dig.vertices(); i++) {
                fpr = 0.0;
                for (int k : f.adj(v)) {
                    fpr += tr.get(k) / this.dig.outdegree(k);
                }
                prstore.put(i, fpr);
            }
            for (int i = 0; i < this.dig.vertices(); i++) {
                tr.put(i, prstore.get(i));
            }
        }
        return prstore.get(v);
    }
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


public class Solution {
    public static void main(String[] args) {
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
