package main;

import java.io.*;

public class Main {

	static int	n;
	
	final static long	mod = 1000000000;

	public static void main(String[] args) throws IOException {
	
		init();
		dp();
	}

	static void	init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	}

	static void	dp() {
		long		sum = 0;
		long[][]	dp = new long[n+1][10];

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 1 && j != 0) dp [i][j] = 1;
				else {
					if (j == 0) dp[i][j] = dp[i-1][j+1] % mod;
					else if (j == 9) dp[i][j] = dp[i-1][j-1] % mod;
					else dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] % mod;
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			sum += dp[n][i];
		}
		System.out.println(sum % mod);
	}
}