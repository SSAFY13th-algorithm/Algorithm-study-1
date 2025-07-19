package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3StairNumbers {

    private static final int MOD = 1_000_000_000;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int ans;

    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = 0;

        dp = new int[N][10][1 << 10];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
    }

    private static void sol() {
        for (int i = 1; i < 10; i++) {
            ans += dfs(1, i, 1 << i);
            ans = mod(ans);
        }
        System.out.println(ans);
    }

    private static int dfs(int depth, int idx, int mask) {
        if (OOB(idx)) {
            return 0;
        }
        
        if (depth == N)
        	return (mask == (1 << 10) - 1) ? 1 : 0;
        
        if (dp[depth][idx][mask] != -1) return dp[depth][idx][mask];

        int tmp = 0;

        // ↙
        if (idx - 1 >= 0)
        	tmp += dfs(depth + 1, idx - 1, mask | (1 << (idx - 1)));
        
        // ↘
        if (idx + 1 <= 9)
        	tmp += dfs(depth + 1, idx + 1, mask | (1 << (idx + 1)));
        
        return dp[depth][idx][mask] = mod(tmp);
    }

    private static int mod(int n) {
        return n % MOD;
    }

    private static boolean OOB(int i) {
        return i < 0 || 9 < i;
    }

}
