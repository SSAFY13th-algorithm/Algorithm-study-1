package main;

import java.io.*;
import java.util.*;

public class Main {

	static int	n;
	
	static int[][]	house;

	// i: horizonal || vertical || diagonal
	static int[][][]	dp;

	public static void main(String[] args) throws IOException {
		sol();
	}

	static void	sol() throws IOException {
		init();
		dp();

		System.out.println(getAns());
	}

	static void	init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer	st;

		house = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[3][n][n];
	}

	static void	dp() {
		// init dp
		for (int j = 1; j < n; j++) {
			if (house[0][j] == 1) break ;

			dp[0][0][j] = 1;
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				// wall
				if (house[i][j] == 1) continue ;
		
				// horizontal
				dp[0][i][j] = dp[0][i][j-1] + dp[2][i][j-1];

				// vertical
				dp[1][i][j] = dp[1][i-1][j] + dp[2][i-1][j];

				// diagonal
				if (house[i-1][j] == 0 && house[i][j-1] == 0) {
					dp[2][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
				}
			}
		}
	}

	static int	getAns() {
		return (dp[0][n-1][n-1] + dp[1][n-1][n-1] + dp[2][n-1][n-1]);
	}
}