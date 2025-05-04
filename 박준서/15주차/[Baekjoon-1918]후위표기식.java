import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static char[] in; // 입력 수식 문자 배열
    private static Stack<Character> s; // 연산자 저장용 스택

    public static void main(String args[]) throws Exception {
        in = nextToken().toCharArray(); // 수식 입력받아 문자 배열로 변환
        s = new Stack<>(); // 연산자 스택 초기화

        for (char it : in) {
            // 1. 피연산자(알파벳)는 바로 출력
            if ('A' <= it && it <= 'Z')
                bw.write(it);

            // 2. 연산자 및 괄호 처리
            switch (it) {
                // +, - 연산자는 (를 만날 때까지 스택에서 pop하여 출력
                case '+':
                case '-':
                    while (!s.empty() && s.peek() != '(')
                        bw.write(s.pop());
                    s.push(it);
                    break;
                // *, / 연산자는 *, /가 스택에 있을 때만 pop하여 출력
                case '*':
                case '/':
                    while (!s.empty() && (s.peek() == '*' || s.peek() == '/'))
                        bw.write(s.pop());
                    s.push(it);
                    break;
                // ( 괄호는 그냥 스택에 push
                case '(':
                    s.push('(');
                    break;
                // ) 괄호는 (를 만날 때까지 pop하여 출력, (는 버림
                case ')':
                    while (s.peek() != '(')
                        bw.write(s.pop());
                    s.pop();
                    break;
            }
        }
        // 남아있는 연산자 모두 출력
        while (!s.empty())
            bw.write(s.pop());

        bwEnd();
    }
}
