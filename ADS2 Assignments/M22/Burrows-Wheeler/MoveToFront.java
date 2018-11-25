import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    // alphabet size of extended ASCII
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] series = new char[R];
        for (int i = 0; i < R; i++) {
            series[i] = (char) i;
        }

        // read the input
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        for (int i = 0; i < input.length; i++) {
            char in = input[i];

            int out;
            for (out = 0; out < series.length; out++) {
                if (series[out] == in) {
                    break;
                }
            }
            assert out < series.length;

            BinaryStdOut.write((char) out);

            System.arraycopy(series, 0, series, 1, out);
            series[0] = in;
        }

        // close output stream
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] series = new char[R];
        for (int i = 0; i < R; i++) {
            series[i] = (char) i;
        }

        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        for (int i = 0; i < input.length; i++) {
            // Read in each 8-bit character.
            int in = input[i];
            char ch = series[in];

            // Write the ith character in the series.
            BinaryStdOut.write(ch);

            // Move that character to the front.
            System.arraycopy(series, 0, series, 1, in);
            series[0] = ch;
        }

        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if      (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
