import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    public static int solve() {
        //n이 1일 경우
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 3;

        for (int i = 2; i <= n; i++) {
            dp[i] = ((dp[i - 1] * 2) + dp[i - 2]) % 9901;
        }
        return dp[n];


    }
}