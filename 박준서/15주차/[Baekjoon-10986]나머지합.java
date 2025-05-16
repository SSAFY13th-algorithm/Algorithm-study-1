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

    private static int N, M, sum; // N: 수의 개수, M: 나눌 값, sum: 누적합 (mod M)
    private static long ans; // 정답(조건을 만족하는 구간의 개수)
    private static int dp[]; // 나머지 별 누적합 개수 카운트

    public static void main(String args[]) throws Exception {
        N = nextInt();
        M = nextInt();
        dp = new int[M];

        for (int i = 0; i < N; i++) {
            // 누적합을 구하고 M으로 나눈 나머지 계산
            sum = (sum + nextInt()) % M;
            // 해당 나머지의 개수 증가
            dp[sum]++;
            // 현재까지의 누적합이 같은 경우, 그 구간의 합이 M으로 나누어떨어짐
            ans += dp[sum] - 1;
            // 만약 누적합이 0이면, 0~i까지의 구간합이 M으로 나누어떨어짐
            if (sum == 0)
                ans++;
        }

        bw.write(ans + "\n");
        bwEnd();
    }
}
