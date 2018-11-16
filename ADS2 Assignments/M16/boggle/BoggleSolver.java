
public class BoggleSolver {
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    String[] dict;
    TrieSET dic;
    public BoggleSolver(String[] dictionary) {
        this.dict = dictionary;
        dic = new TrieSET();
        for (int i = 0; i < dictionary.length; i++) {
            dic.add(dictionary[i]);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        TrieSET validWords = new TrieSET();
        int rows = board.rows();
        int cols = board.cols();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] marked = new boolean[rows][cols];
                generateValidWords(board, i, j, "", marked, validWords);
            }
        }
        return validWords;
    }
    private void generateValidWords(BoggleBoard b, int row, int col, String prefix, boolean[][] check, TrieSET set) {
        if (check[row][col]) {
            return;
        }
        char letter = b.getLetter(row, col);
        String word = prefix + letter;
        if (word.length() > 2 && dic.contains(word)) {
            System.out.println(word);
            set.add(word);
        }
        if (dic.keysWithPrefix(word) == null) {
            return;
        }
        check[row][col] = true;
        for (int i = -1; i < row; i++) {
            for (int j = -1; j < row; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (row + i > 0 && col + j > 0 && row + i < b.rows() && col + j < b.cols())
                    generateValidWords(b, row+i, col+j, word, check, set);
            }
        }
        check[row][col] = false;
    }
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (dic.contains(word)) {
            int len = word.length();
            if (len >= 3 && len <= 4) {
                return 1;
            } else if (len == 5) {
                return 2;
            } else if (len == 6) {
                return 3;
            } else if (len == 7) {
                return 5;
            } else if (len >= 8) {
                return 11;
            }
        }
        return 0;
    }
}
