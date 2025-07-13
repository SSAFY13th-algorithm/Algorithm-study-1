import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n;
    public static List<String> list;
    public static TrieNode trie;

    public static class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean isEnd;

        public void insert(String word) {
            TrieNode trieNode = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                trieNode.child.putIfAbsent(c, new TrieNode());
                trieNode = trieNode.child.get(c);
            }
            trieNode.isEnd = true;
        }

        public boolean contains(String word) {
            TrieNode trieNode = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode child = trieNode.child.get(c);
                if(child == null) {
                    return false;
                }
                trieNode = child;
            }
            if(trieNode.isEnd && trieNode.child.isEmpty()) {
                return false;
            }
            return true;
        }
    }



    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        trie = new TrieNode();
        list = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            trie.insert(str);
            list.add(str);
        }
    }

    public static void solution() throws IOException {
        init();
        boolean isContain = false;
        for(int i = 0; i < list.size(); i++) {
            if(trie.contains(list.get(i))) {
                isContain = true;
                break;
            }
        }
        if(isContain) sb.append("NO\n");
        else sb.append("YES\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            solution();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
