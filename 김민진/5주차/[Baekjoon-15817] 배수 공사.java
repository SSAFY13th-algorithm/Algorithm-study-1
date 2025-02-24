package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2DrainageWork {
	
	private static int N;
	private static int x;
	
	private static int[] dp;

	private static int[][] pipes;
	
	public static void main(String[] args) throws IOException {
		sol();
	}
	
	private static void sol() throws IOException {
		init();
		doDP();
	}

	private static void init() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		pipes = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int len = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			pipes[i][0] = len;
			pipes[i][1] = cnt;
		}
		
		dp    = new int[x+1];
		dp[0] = 1;
	}
	
	private static void doDP() {
		for (int[] pipe : pipes) {
			int len = pipe[0];
			int cnt = pipe[1];
			
			for (int i = x; i >= len; i--) {
				int tmp = 1;
				while (tmp <= cnt && len * tmp <= i) {
 					dp[i] += dp[i - len * tmp++];
				}
			}
		}
		
		System.out.println(dp[x]);
	}
}
