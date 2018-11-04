import java.util.Scanner;
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
        Scanner scan = new Scanner(System.in);
        switch (token) {
            case "Graph":
                System.out.println(word.getGraph());
                break;
            case "Queries":
                while(scan.hasNext()) {
                    String[] line = scan.nextLine().split(" ");
                    System.out.println("distance = " + word.distance(line[0], line[1]) + "ancestor =" + word.sap(line[0], line[1]));
                }
                break;
        }
    }
}
