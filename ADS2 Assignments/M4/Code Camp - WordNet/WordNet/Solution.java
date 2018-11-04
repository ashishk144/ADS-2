import java.util.Arrays;
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
        String synset = "Files/" + in.readLine();
        String hypernym = "Files/" + in.readLine();
        try {
            WordNet word = new WordNet(synset, hypernym);
            String token = in.readLine();
            switch (token) {
                case "Graph":
                    System.out.println(word.getGraph());
                    break;
                case "Queries":
                    while(in.hasNextLine()) {
                        String[] line = in.readLine().split(" ");
                        System.out.println("distance = " + word.distance(
                            line[0], line[1])
                            + ", ancestor = " + word.sap(line[0], line[1]));
                    }
                    break;
            }
        } catch (NullPointerException f) {
            System.out.println("IllegalArgumentException");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
