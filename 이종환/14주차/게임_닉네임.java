import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 게임_닉네임 {
    static TrieNode root;
    static Map<String,Integer> names = new HashMap<>();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        root = new TrieNode();

        int cnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < cnt; i++) {
            String input = br.readLine();
            sb.append(root.insert(input)).append("\n");
        }
        System.out.print(sb.toString());
    }


    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();

        public String insert(String word) {
            if (names.containsKey(word)) {
                names.replace(word, names.get(word)+1);
            } else names.put(word, 1);
            StringBuilder sb = new StringBuilder();
            boolean isFinish = false;
            char[] chars = word.toCharArray();

            TrieNode node =  root;
            for (int i = 0; i < chars.length; i++) {
                if (!node.children.containsKey(chars[i])) {
                    node.children.put(chars[i], new TrieNode());

                    if (!isFinish) {
                        sb.append(chars[i]);
                        isFinish = true;
                    }
                }

                if(!isFinish) sb.append(chars[i]);


                node = node.children.get(chars[i]);
            }

            if (!isFinish && sb.toString().equals(word) && names.get(word)>1) {
                sb.append(names.get(word));
            }

            return sb.toString();

        }
    }

}
