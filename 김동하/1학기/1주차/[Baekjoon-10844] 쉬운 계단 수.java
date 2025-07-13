package baekjoon;

import java.util.Scanner;


public class Main {
	public static int dp[][] = new int[101][10];
	public static Scanner sc = new Scanner(System.in);
	public static int n;
	public static void init() {
		n = sc.nextInt();
		for(int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}
	}
	
	public static void solution() {
		init();

		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) dp[i][j] = dp[i - 1][j + 1];
				else if(j == 9) dp[i][j] = dp[i - 1][j - 1];
				else dp[i][j] = (dp[i - 1][j -1] + dp[i - 1][j + 1]) % 1000000000;
			}
		}
		int ans = 0;
		for(int i = 0; i < 10; i++) {
			ans = (ans + dp[n][i]) % 1000000000;
		}
		System.out.println(ans);
	}
	public static void main(String[] args) {
		solution();
	}
}
