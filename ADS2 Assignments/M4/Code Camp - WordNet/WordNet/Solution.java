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
     * Main class to handle inputs and deliver outputs.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        In in = new In();
        String synset = "Files/" + in.readString();
        String hypernym = "Files/" + in.readString();
        WordNet word = new WordNet(synset, hypernym);
        String token = in.readString();
        switch (token) {
            case "Graph":
                System.out.println(word.getGraph());
                break;
            case "Queries":
                break;
        }
    }
}
