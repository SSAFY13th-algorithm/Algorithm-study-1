package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2TaskScheduling {

    private static final int INF = 987654321;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int bitCnt;

    private static int[][] works;
    private static int[][] dp;    // [i][j: 진행되는 일]: j 일이 진행 중이고 i가 일을 할 때의 최소값

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(sol(0, 0));
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        works = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                works[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bitCnt = (1 << N) - 1; // 모든 일 진행
        dp = new int[N][bitCnt + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    private static int sol(int person, int set) {
        // all asigned
        if (person == N) {
            return 0;
        }

        // alr calculated
        if (dp[person][set] != -1) {
            return dp[person][set];
        }

        int min = INF;

        for (int job = 0; job < N; job++) {
            // alr working
            if ((set & (1 << job)) != 0) {
                continue;
            }

            int tmp = sol(person + 1, set | (1 << job)) + works[person][job];
            min = Math.min(tmp, min);
        }

        return dp[person][set] = min;
    }

}
