import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

public class Solution {

    // Don't modify this method.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.
    public static String[] toReadFile(String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        for(String s: toReadFile(file)) {
            s = s.toLowerCase();
            if(!st.contains(s)) {
                st.put(s, 1);
            } else {
                st.put(s, st.get(s) + 1);
            }
        }
        return st;
    }

}

class T9 {
    BinarySearchST<String, Integer> tnine;
    TST<Integer> tst;
    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here
        this.tnine = st;
        tst = new TST();
        Iterable<String> keys = st.keys();
        for(String key: keys) {
            tst.put(key, st.get(key));
        }
    }

    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }

    public Iterable<String> potentialWords(String t9Signature) {
        // your code goes here
        HashMap<Character, String[]> map = new HashMap<Character, String[]>();
        map.put('2', "abc".split(""));
        map.put('3', "def".split(""));
        map.put('4', "ghi".split(""));
        map.put('5', "jkl".split(""));
        map.put('6', "mno".split(""));
        map.put('7', "pqrs".split(""));
        map.put('8', "tuv".split(""));
        map.put('9', "wxyz".split(""));
        // map.put('1', "abc".split(""));
        TST<Object> bg = new TST<Object>();
        // for(int i = 0; i < t9Signature.length(); i++) {
        //     for(String k: map.get(t9Signature.charAt(i))) {
        //         boolean[] mark = new boolean[map.get(t9Signature.charAt(i)).length];
        //         formWords()
        //     }
        // }
        formWords(0, map, "", bg, t9Signature, t9Signature.length());
        return bg.keys();
    }
    public void formWords(int i, HashMap<Character, String[]> m, String prefix, TST<Object> b, String sign, int len) {
        if(i >= len) {
            return;
        }
        for(String k: m.get(sign.charAt(i))) {
            // System.out.println(k);
            String word = prefix + k;
            if(tst.contains(word) && word.length() == len) {
                b.put(word, 'v');
            }
            formWords(i+1, m, word, b, sign, len);
        }

    }

    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here
        // System.out.println(k);
        BinarySearchST<Integer, String> bst = new BinarySearchST<Integer, String>();
        MaxPQ<Integer> topvals = new MaxPQ<Integer>();
        for(String word: words) {
            if(!bst.contains(tst.get(word))) {
                bst.put(tst.get(word), word);
            } else {
                bst.put(tst.get(word), bst.get(tst.get(word)) + " " + word);
            }
            topvals.insert(tst.get(word));
        }
        int count = 0;
        TST<Integer> sugstions = new TST();
        for(int i = 0; i < k; i++) {
            if(!topvals.isEmpty()) {
                String[] str = bst.get(topvals.delMax()).split(" ");
                int len = str.length;
                if(len > 1) {
                    String temp = "";
                    for(String strin: str) {
                        if(strin.length() > temp.length()) {
                            temp = strin;
                        }
                    }
                    sugstions.put(temp, 1);
                } else {
                    sugstions.put(str[0], 1);
                }
            }
        }
        return sugstions.keys();
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
