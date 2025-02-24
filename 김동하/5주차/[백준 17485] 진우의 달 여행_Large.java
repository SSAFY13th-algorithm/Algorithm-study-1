import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n,m;
    public static int[][] arr;
    public static int[][][] dp;

    public static void init() throws IOException {
        int in[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = in[0];
        m = in[1];
        arr = new int [n + 1][m + 1];
        dp = new int [n + 1][m + 1][3];
        for(int i = 1; i <= n; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 1; j <= m; j++) {
                arr[i][j] = in[j - 1];
            }
        }
    }
    // 0 1 2 -> 왼 가 오
    public static void solution() throws IOException{
        init();
        for(int i = 1; i <= m; i++) {
            for(int j = 0; j < 3; j++) {
                dp[0][i][j] = 0;
            }
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                for(int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(j == 1) {
                    dp[i][j][1] = dp[i - 1][j][2] + arr[i][j];
                    dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]);
                    dp[i][j][2] += arr[i][j];
                }
                else if(j == m) {
                    dp[i][j][1] = dp[i - 1][j][0] + arr[i][j];
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][1],dp[i - 1][j - 1][2]);
                    dp[i][j][0] += arr[i][j];
                }
                else {
                    dp[i][j][0] = Math.min(dp[i - 1][j - 1][1], dp[i - 1][j - 1][2]);
                    dp[i][j][0] += arr[i][j];
                    dp[i][j][1] = Math.min(dp[i - 1][j][0], dp[i - 1][j][2]);
                    dp[i][j][1] += arr[i][j];
                    dp[i][j][2] = Math.min(dp[i - 1][j + 1][0], dp[i - 1][j + 1][1]);
                    dp[i][j][2] += arr[i][j];
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= m; i++) {
            for(int j = 0; j < 3; j++) {
                ans = Math.min(ans, dp[n][i][j]);
            }
        }
        System.out.println(ans);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
