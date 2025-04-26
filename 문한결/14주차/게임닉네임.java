import java.io.*;
import java.util.*;

public class 게임닉네임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Trie trie = new Trie();
    static Map<String, Integer> nameCount = new HashMap<>();

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String name = br.readLine();
            int count = nameCount.getOrDefault(name, 1);

            if (count == 1) {
                String alias = trie.insert(name);
                System.out.println(alias);
            } else {
                System.out.println(name + count);
                trie.insert(name); // 중복이어도 트라이에는 계속 넣어야 함
            }

            nameCount.put(name, count + 1);
        }
    }
}

class Trie {

    Node root = new Node();

    public String insert(String word) {
        Node current = root;
        StringBuilder prefix = new StringBuilder();
        boolean uniqueFound = false;
        int ix = 0;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // prefix.append(ch);

            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new Node());
                uniqueFound = true;
            } else {
                if (!uniqueFound) {
                    prefix.append(word.charAt(i));
                    ix++;
                }

            }
            current = current.children.get(ch);
        }
        if (ix < word.length()) {
            prefix.append(word.charAt(ix));
        }

        current.isEnd = true;

        return uniqueFound ? prefix.toString() : word;
    }
}

class Node {

    Map<Character, Node> children = new HashMap<>();
    boolean isEnd = false;
}
