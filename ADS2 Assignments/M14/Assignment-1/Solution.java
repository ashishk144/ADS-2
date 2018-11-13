import java.util.Scanner;
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
     * Main function to handle input and delivers output.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        Scanner scan = new Scanner(System.in);
        String prefix = scan.nextLine();
        TST table = new TST();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                table.put(words[i].substring(j), i);
            }
        }
        Iterable<String> kwp = table.keysWithPrefix(prefix);
        for (String s : kwp) {
            System.out.println(s);
        }
    }
    /**
     * Loads words.
     *
     * @return     words.
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
