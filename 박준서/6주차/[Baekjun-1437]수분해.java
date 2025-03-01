import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int N, ans = 1; // N: 입력값, ans: 결과값(초기값 1)
    private static final long mod = 10007; // 결과값을 나눌 모듈러 값

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();

        // N이 0보다 클 때까지 반복
        do {
            if (N > 4) {
                // N이 4보다 크면 3씩 분할하는 것이 최적
                ans *= 3; // 결과에 3을 곱함
                N -= 3; // N에서 3을 뺌
            } else {
                // N이 4 이하면 그대로 사용하는 것이 최적
                ans *= N; // 결과에 N을 곱함
                N = 0; // N을 0으로 설정하여 반복 종료
            }
            ans %= mod; // 결과값을 10007로 나눈 나머지로 업데이트(오버플로우 방지)
        } while (N > 0);

        sb.append(ans).append('\n');
        printAnswer();
    }
}
