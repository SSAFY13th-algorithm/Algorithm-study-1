package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n, k;
    public static int[] dp = new int[10001];
    static class Subject {
        private int important, time;
        public Subject(int important, int time) {
            this.important = important;
            this.time = time;
        }
    }

    public static Subject[] subject = new Subject[1001];

    public static void init() throws IOException {
        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = in[0];
        k = in[1];
        for (int i = 1; i <= k; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            subject[i] = new Subject(in[0], in[1]);
        }
    }

    public static void solution() {
        for(int i = 1; i <= k; i++) {
            int t = subject[i].time;
            for(int j = n; j > 0; j--) {
                if(j - t >= 0) dp[j] = Math.max(dp[j], dp[j - t] + subject[i].important);
                else dp[j] = Math.max(dp[j],dp[j - 1]);
            }
        }
        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
}
