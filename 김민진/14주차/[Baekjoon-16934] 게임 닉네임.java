package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2GameNickname {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static int N;
    private static Trie trie;

    private static List<String> aliases;

    private static class TrieNode {

        boolean isEnd;
        TrieNode[] children;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }

    }

    private static class Trie {

        private final TrieNode root;
        private static Map<String, Integer> nickCount;

        public Trie() {
            root = new TrieNode();
            nickCount = new HashMap<>();
        }

        public void insert(String nick) {
            TrieNode cur = root;

            String alias = "";
            boolean stop = false;
            for (int i = 0; i < nick.length(); i++) {
                int c = nick.charAt(i) - 'a';

                if (!stop) {
                    alias += nick.charAt(i);
                }

                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                    stop = true;
                }
                cur = cur.children[c];
            }

            nickCount.putIfAbsent(alias, 1);

            // 중복 닉네임
            if (cur.isEnd) {
                int cnt = nickCount.get(alias) + 1;

                alias += cnt;
                nickCount.put(nick, cnt);
            } else {
                cur.isEnd = true;
            }

            aliases.add(alias);
        }

    }

    public static void main(String[] args) throws IOException {
        sol();
    }

    private static void sol() throws IOException {
        trie = new Trie();

        N = Integer.parseInt(br.readLine());

        aliases = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String nick = br.readLine().trim();

            trie.insert(nick);
        }

        for (String alias : aliases) {
            sb.append(alias).append("\n");
        }
        System.out.println(sb);
    }

}
