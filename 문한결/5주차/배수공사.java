import java.util.*;
import java.io.*;
public class 배수공사 {


    static int[] input;
    static int N, x;
    static int[][] dp;
    static int[] L, C;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        x = input[1];

        L = new int[N];
        C = new int[N];
        dp = new int[N + 1][x + 1];

        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            L[i] = input[0];
            C[i] = input[1];
        }

        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            int pipe = L[i - 1];
            int count = C[i - 1];

            for (int j = 0; j <= x; j++) {
                dp[i][j] = dp[i - 1][j];

                for (int k = 1; k <= count && j >= k * pipe; k++) {
                    dp[i][j] += dp[i - 1][j - k * pipe];
                }
            }
        }

        System.out.println(dp[N][x]);
    }
}

