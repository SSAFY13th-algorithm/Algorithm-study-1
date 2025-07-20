import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static String str;
    public static String word;

    public static List<Character> stack = new ArrayList<>();


    public static void init() throws IOException {
        str = br.readLine();
        word = br.readLine();
    }

    public static void solution() throws IOException {
        init();
        int len = str.length();
        char ed = word.charAt(word.length() - 1);
        for (int i = 0; i < len; i++) {
            stack.add(str.charAt(i));
            if (str.charAt(i) == ed) {
                boolean flag = true;
                int idx = word.length() - 1;
                if(stack.size() < word.length()) continue;
                for(int j = stack.size() - 1; j >= stack.size() - word.length(); j--) {
                    if(stack.get(j) != word.charAt(idx)) {
                        flag = false;
                        break;
                    }
                    idx--;
                }
                if(flag) {
                    int x = stack.size() - 1;
                    for(int j = x; j >= x - word.length() + 1; j--) {
                        stack.remove(j);
                    }
                }
            }
        }
        if(stack.isEmpty()) {
            sb.append("FRULA");
            return;
        }
        for(int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }
    }

    public static void main(String[] args)throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}