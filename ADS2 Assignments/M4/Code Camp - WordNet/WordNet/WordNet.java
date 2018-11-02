import java.util.Arrays;
import java.util.HashMap;

public class WordNet {
    HashMap<Integer, Bag<String>> synset;
    HashMap<String, Bag<Integer>> synset1;
    Digraph g;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In syn = new In(synsets);
        In hyp = new In(hypernyms);
        // String[] syns = syn.readAllLines();
        // String[] hyper = hyp.readAllLines();
        this.synset = new HashMap<Integer, Bag<String>>();
        this.synset1 = new HashMap<String, Bag<Integer>>();
        for (String s : syn.readAllLines()) {
            String[] temp = s.split(",");
            int id = Integer.parseInt(temp[0]);
            synset.putIfAbsent(id, new Bag<String>());
            for (String k : temp[1].split(" ")) {
                synset.get(id).add(k);
                synset1.putIfAbsent(k, new Bag<Integer>());
                synset1.get(k).add(id);
            }
        }
        g = new Digraph(synset.size());
        for (String m : hyp.readAllLines()) {
            String[] t = m.split(",");
            for (int i = 1; i < t.length; i++) {
                g.addEdge(Integer.parseInt(t[0]), Integer.parseInt(t[i]));
            }
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.synset1.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return this.synset1.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {
    //     if()
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
