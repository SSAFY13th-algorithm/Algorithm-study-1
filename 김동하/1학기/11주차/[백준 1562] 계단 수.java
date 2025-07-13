import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n;
    public static long[][][] dp;
    public static long ans;

    public static final long MOD = 1000000000;

    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][10][1<<10];
        ans = 0;
    }

    public static void solution() throws IOException{
        init();
        for(int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }
        // i = 자릿수, j = 마지막 수, k = 포함 수
        /**
         * i = 3, j = 5, k = 1000100000
         * i = 4, j =
         */
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < 10; j++) {
                for(int k = 0; k < (1 << 10); k++) {
                    int used = (1 << j) | k;
                    if(j == 0) dp[i][j][used] = (dp[i][j][used] + dp[i - 1][j + 1][k]) % MOD;
                    else if(j == 9) dp[i][j][used] = (dp[i][j][used] + dp[i - 1][j - 1][k]) % MOD;
                    else {
                        dp[i][j][used] = (dp[i][j][used] + (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD) % MOD;
                    }
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            ans += dp[n][i][1023];
            ans %= MOD;
        }
    }

    public static void main(String[] args) throws IOException{
        solution();
        sb.append(ans);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
