package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3TripToMoon {
	
	private static final int MAX_VAL = 100000000;
	
	private static int N;
	private static int M;
	
	private static int[][]   cost;
	private static int[][][] dp;   // 0: ↘, 1: ↓, 2: ↙
	
	public static void main(String[] args) throws IOException {
		sol();
	}
	
	private static void sol() throws IOException {
		init();
		doDP();
		printMin();
	}
	
	private static void init() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cost = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M][3];
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < 3; k++) {
				dp[0][j][k] = cost[0][j]; 
			}
		}
		
		dp[0][0][0]   = MAX_VAL;
		dp[0][M-1][2] = MAX_VAL;
	}
	
	private static void doDP() {
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Arrays.fill(dp[i][j], MAX_VAL);
				// ↘
				if (j > 0)
					dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + cost[i][j];

				// ↓
				dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + cost[i][j];
				
				// ↙
				if (j < M-1)
					dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + cost[i][j];				
			}
		}
	}
	
	private static void printMin() {
		int res = MAX_VAL;
		
		for (int j = 0; j < M; j++) {
			for (int k = 0; k < 3; k++) {
				res = Math.min(res, dp[N-1][j][k]);
			}
		}
		System.out.println(res);
	}

}
