import java.io.*;
import java.util.*;

public class ZOAC {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static String[][] arr;
    static int[] input;
    static int[][][] dp;
    static int answer[];

    public static void main(String[] args) throws Exception {
        init();
        execute();
        for (int a : answer) {
            System.out.print(a + " ");
        }
    }

    static void init() throws Exception {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        dp = new int[2][n + 1][m + 1];
        arr = new String[n + 1][m + 1];
        answer = new int[4];
        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i + 1][j + 1] = temp[j];
            }
        }

    }

    static void execute() {
        answer[0] = findMax("Z");
        dp = new int[2][n + 1][m + 1];
        answer[1] = findMax("O");
        dp = new int[2][n + 1][m + 1];
        answer[2] = findMax("A");
        dp = new int[2][n + 1][m + 1];
        answer[3] = findMax("C");
        dp = new int[2][n + 1][m + 1];

    }

    static int findMax(String str) {

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j].equals(str)) {
                    dp[0][i][j] = Math.max(dp[0][i - 1][j], dp[0][i][j - 1]) + 1;
                    dp[1][i][j] = Math.max(Math.max(dp[1][i - 1][j], dp[1][i][j - 1]), dp[0][i - 1][m]) + 1;

                } else {
                    dp[0][i][j] = Math.max(dp[0][i - 1][j], dp[0][i][j - 1]);
                    dp[1][i][j] = Math.max(Math.max(dp[1][i - 1][j], dp[1][i][j - 1]), dp[0][i - 1][m]);

                }

            }
        }
        for (int j = 1; j <= m; j++) {
            for (int i = 1; i <= n; i++) {

                dp[0][i][j] = Math.max(Math.max(dp[0][i - 1][j], dp[0][i][j - 1]), dp[0][i][j]);
                if (arr[i][j].equals(str)) {
                    dp[1][i][j] = Math.max(
                            Math.max(Math.max(dp[1][i - 1][j], dp[1][i][j - 1]), dp[0][i - 1][m]) + 1, dp[1][i][j]);
                } else {
                    dp[1][i][j] = Math.max(
                            Math.max(Math.max(dp[1][i - 1][j], dp[1][i][j - 1]), dp[0][i - 1][m]), dp[1][i][j]);
                }

                if (j != m) {
                    if (arr[1][j + 1].equals(str)) {
                        dp[1][1][j + 1] = Math.max(dp[1][1][j + 1], dp[0][i][j] + 1);
                    } else {
                        dp[1][1][j + 1] = Math.max(dp[1][1][j + 1], dp[0][i][j]);
                    }
                }

            }
        }
        return Math.max(dp[0][n][m], dp[1][n][m]);

    }
}
