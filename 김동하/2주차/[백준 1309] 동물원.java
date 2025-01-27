package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] dp = new int[100001];

    public static int n;

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        dp[0] = 1;
        dp[1] = 3;
    }

    // dp[n] = dp[n - 2] * 3 + (dp[n - 1] - dp[n - 2]) * 2 = dp[n - 2] + dp[n - 1] * 2
    public static void solution() {
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + (dp[i - 1] * 2) % 9901) % 9901;
        }
        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
}
