import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t;
    static int n;
    static String[] strs;
    static Trie trie;


    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            init();
            System.out.println(isConsistence() ? "YES" : "NO");

        }


    }

    public static void init() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        strs = new String[n];
        for (int i = 0; i < n; i++) {
            String tempStr = br.readLine().trim();
            strs[i] = tempStr;
        }
        Arrays.sort(strs, (str1, str2) -> str2.length() - str1.length());
        trie = new Trie();

    }

    public static boolean isConsistence() {
        for (int i = 0; i < n; i++) {
            if (trie.isIncludeWord(strs[i]))
                return false;
            trie.insert(strs[i]);

        }
        return true;
    }



}


class Node {
    HashMap<Character, Node> child;
    boolean isEndWord;

    public Node() {
        child = new HashMap();
        isEndWord = true;
    }

}


class Trie {
    Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String c) {
        Node node = this.root;
        for (int i = 0; i < c.length(); i++) {
            char ch = c.charAt(i);
            node.child.putIfAbsent(ch, new Node());
            node = node.child.get(ch);
        }
        node.isEndWord = true;
    }

    public boolean isIncludeWord(String str) {

        Node node = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (node.child.get(ch) == null)
                return false;
            node = node.child.get(ch);
        }
        return true;
    }

}
