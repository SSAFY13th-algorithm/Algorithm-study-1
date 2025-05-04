package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Q2Postfix {

    private static final Map<Character, Integer> OP = new HashMap<>() {
        {
            put('+', 0);
            put('-', 0);
            put('*', 1);
            put('/', 1);
            put('(', -1);
            put(')', -1);
        }
    };

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();

    private static String s;
    private static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        sol();
    }

    private static void sol() throws IOException {
        s = br.readLine().trim();
        stack = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            // nums
            if (!OP.containsKey(c)) {
                sb.append(c);
                continue;
            }

            // ops
            switch (c) {
                case '(' ->
                    stack.push(c);
                case ')' -> {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    // rm '('
                    stack.pop();
                }
                default -> {
                    int pri = OP.get(c);

                    while (!stack.isEmpty() && OP.get(stack.peek()) >= pri) {
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        // print remaining ops
        while (!stack.isEmpty()) {
            char pop = stack.pop();

            if (pop != '(' && pop != ')') {
                sb.append(pop);
            }
        }

        System.out.println(sb);
    }
}
