package semester2.week1;

import java.io.*;
import java.util.Stack;

public class Q2StringEruption {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static String haystack;
    private static String needle;
    private static int len;

    private static Stack<Character> s;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        haystack = br.readLine();
        needle = br.readLine();
        len = needle.length();

        s = new Stack<>();
    }

    private static void sol() throws IOException {
        for (int i = 0; i < haystack.length(); i++) {
            s.push(haystack.charAt(i));

            if (s.size() >= len && isNeedle()) {
                for (int j = 0; j < len; j++) {
                    s.pop();
                }
            }
        }
        printAns();
    }

    private static void printAns() throws IOException {
        if (s.empty()) {
            bw.write("FRULA");
        } else {
            for (char str : s) {
                bw.write(str);
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean isNeedle() {
        for (int i = 0; i < len; i++) {
            if (!s.get(s.size() - len + i).equals(needle.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
