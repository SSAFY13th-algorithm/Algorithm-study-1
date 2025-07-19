package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5SumOfSquareNumber {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        sol();
    }

    private static void sol() throws IOException {
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[N]);
    }

}
