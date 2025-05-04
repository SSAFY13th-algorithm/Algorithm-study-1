import java.io.*;
import java.util.*;

public class 후위표기식 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] str;
    static String answer = "";
    static Map<String, Integer> priorMap;
    static ArrayDeque<String> stacks;

    public static void main(String[] args) throws Exception {
        init();
        execute();
        System.out.println(answer);

    }

    static void init() throws Exception {
        str = br.readLine().split("");
        priorMap = new HashMap<>();
        priorMap.put("+", 1);
        priorMap.put("-", 1);
        priorMap.put("*", 2);
        priorMap.put("/", 2);
        priorMap.put("(", 3);
        stacks = new ArrayDeque<>();

    }

    static void execute() {
        for (String s : str) {

            if (isOperand(s)) {

                if (s.equals(")")) {

                    while (!stacks.isEmpty() && !stacks.peekLast().equals("(")) {
                        answer += stacks.removeLast();
                    }
                    stacks.removeLast();
                } else {
                    while (!stacks.isEmpty() && priorMap.get(stacks.peekLast()) >= priorMap.get(s) && !stacks.peekLast().equals("(")) {
                        answer += stacks.removeLast();
                    }

                    stacks.add(s);
                }

            } else {
                answer += s;
            }
        }
        while (!stacks.isEmpty()) {
            answer += stacks.removeLast();
        }

    }

    static boolean isOperand(String str) {
        return str.equals("+") || str.equals("-")
                || str.equals("*") || str.equals("/")
                || str.equals("(") || str.equals(")");
    }

}
