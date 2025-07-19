package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q4Harvest {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int N;
	private static int ans;
	
	private static int[] crops;
	
	private static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		sol(1, 0, N-1);
		System.out.println(dp[0][N-1]);
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		ans = 0;
		
		crops = new int[N];
		for (int i = 0; i < N; i++) {
			crops[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N][N];
	}
	
	private static int sol(int depth, int left, int right) {
		if (left > right) return 0;
		
		if (dp[left][right] > 0) return dp[left][right];
		
		int startWithLeft = crops[left] * depth + sol(depth+1, left+1, right);
		int startWithRight = crops[right] * depth + sol(depth+1, left, right-1);
		
		dp[left][right] = Math.max(startWithLeft, startWithRight);
		
		return dp[left][right];
	}
	
}
