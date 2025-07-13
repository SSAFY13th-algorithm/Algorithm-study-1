import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n;
    public static String str;


    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        str = br.readLine();
    }

    public static int twoPointer() {
        int max = 0;
        int st = 0;
        int ed = 0;
        int length = 0;
        Map<Character, Integer> m = new HashMap<>();
        while (ed < str.length()) {
            char c = str.charAt(ed);
            if ((!m.containsKey(c)) && (m.size() == n)) {
                while (m.size() == n) {
                    int cnt = m.get(str.charAt(st));
                    m.remove(str.charAt(st));

                    if (cnt > 1) {
                        m.put(str.charAt(st), cnt - 1);
                    }
                    st++;
                    length--;
                }
                m.put(c, 1);
                length++;
            } else if (m.containsKey(c)) {
                length++;
                int cnt = m.get(c);
                m.put(c, cnt + 1);
            } else {
                m.put(c, 1);
                length++;
            }
            ed++;

            max = Math.max(length, max);
        }

        return max;
    }

    public static void solution() throws IOException {
        init();
        sb.append(twoPointer());

        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

