import java.util.Arrays;
import java.util.HashMap;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * { item_description }
     */
    private HashMap<Integer, Bag<String>> synset;
    /**
     * { item_description }
     */
    private HashMap<String, Bag<Integer>> synset1;
    /**
     * { var_description }
     */
    private Digraph g;
    /**
     * { var_description }
     */
    private SAP sap;
    // constructor takes the name of the two input files

    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms)
    throws Exception {
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
        int cnt = 0;
        for (int i = 0; i < g.vertices(); i++) {
            if (g.outdegree(i) == 0) {
                cnt++;
            }
        }
        if (cnt != 1 ) {
            throw new Exception("Multiple roots");
        }
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.hasCycle()) {
            throw new Exception("Cycle detected");
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return this.synset1.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(final String word) {
        return this.synset1.keySet().contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(final String nounA, final String nounB) {
        // if(nounA.equals(null) || nounA.equals(null)) {
        //     return 0;
        // } else {
        sap = new SAP(this.g);
        int dist = sap.length(synset1.get(nounA), synset1.get(nounB));
        // }
        return dist;
    }
    public Digraph getGraph() {
        return this.g;
    }
    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    public String sap(final String nounA, final String nounB) {
        sap = new SAP(this.g);
        int id = sap.ancestor(synset1.get(nounA), synset1.get(nounB));
        String ances = "";
        for (String s : synset.get(id)) {
            ances = s + " " + ances;
        }
        return ances.trim();
    }
}
