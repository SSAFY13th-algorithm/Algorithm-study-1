
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 계단 수의 길이
        int MOD = 1_000_000_000;

        // DP 테이블 생성
        long[][] dp = new long[N + 1][10];

        // 초기값 설정
        for (int j = 1; j <= 9; j++) {
            dp[1][j] = 1; // 한 자리 계단 수는 1부터 9까지
        }

        // 점화식으로 DP 테이블 채우기
        for (int i = 2; i <= N; i++) { // 길이 2부터 N까지
            for (int j = 0; j <= 9; j++) { // 마지막 숫자 0부터 9까지
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1]; // 이전 숫자가 j-1인 경우
                }
                if (j < 9) {
                    dp[i][j] += dp[i - 1][j + 1]; // 이전 숫자가 j+1인 경우
                }
                dp[i][j] %= MOD;
            }
        }
        long result = 0;
        for (int j = 0; j <= 9; j++) {
            result += dp[N][j];
            result %= MOD;
        }

        System.out.println(result);
    }
}

