package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3PhoneNumberList {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static StringBuilder sb = new StringBuilder();

    private static int T;
    private static int N;
    private static Trie trie;

    private static class TrieNode {

        boolean isEnd;
        TrieNode[] child;

        public TrieNode() {
            child = new TrieNode[10];
            isEnd = false;
        }
    }

    private static class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public boolean insert(String phone) {
            // starts from the root
            TrieNode cur = root;

            for (int i = 0; i < phone.length(); i++) {
                // char to int
                int digit = phone.charAt(i) - '0';

                // if cur doesn't have child, add one
                if (cur.child[digit] == null) {
                    cur.child[digit] = new TrieNode();
                }
                // move to the child
                cur = cur.child[digit];

                if (cur.isEnd) {
                    return false;
                }
            }
            cur.isEnd = true;

            // if the phone number is a prefix
            for (int i = 0; i < 10; i++) {
                if (cur.child[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            init();
            sol();
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        trie = new Trie();

        N = Integer.parseInt(br.readLine());
    }

    private static void sol() throws IOException {
        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            if (!trie.insert(input)) {
                sb.append("NO\n");

                for (int j = i + 1; j < N; j++) {
                    br.readLine();
                }

                return;
            }
        }
        sb.append("YES\n");
    }

}
