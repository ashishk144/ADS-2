import java.util.Arrays;
import java.util.HashMap;

public class WordNet {
    HashMap<Integer, Bag<String>> ib;
    HashMap<String, Integer> si;
    Digraph g;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In syn = new In(synsets);
        In hyp = new In(hypernyms);
        String[] syns = syn.readAllLines();
        String[] hyper = hyp.readAllLines();
        this.ib = new HashMap<Integer, Bag<String>>();
        this.si = new HashMap<String, Integer>();
        for (String s: syns) {
            String[] temp = s.split(",");
            Bag<String> b = new Bag();
            for(String k: temp[1].split(" ")) {
                b.add(k);
                si.put(k, Integer.parseInt(temp[0]));
            }
            ib.put(Integer.parseInt(temp[0]), b);
        }
        g = new Digraph(syns.length);
        for (String m: hyper) {
            String[] t = m.split(",");
            for(int i = 1; i < t.length; i++) {
                g.addEdge(Integer.parseInt(t[0]), Integer.parseInt(t[i]));
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.si.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return this.si.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {

    // }
    public Digraph getGraph() {
        return this.g;
    }
    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB)

    // // do unit testing of this class
    // public static void main(String[] args)
}
