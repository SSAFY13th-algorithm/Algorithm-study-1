package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q5TSP {
	
	private static final int INF = 987654321;
	
	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int N;
	private static int bitCnt;
	
	private static int[][] graph;
	private static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		init();
		
		System.out.println(sol(0, 1));
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 도시 방문했을 때의 값
		bitCnt = (1 << N) - 1;
		
		dp = new int[N][bitCnt + 1];
		for (int i = 0; i < N; i++) {
			// 방문하지 않은 경우와 계산된 상태를 명확하게 구분하기 위함
			// 방문을 했는데 경로가 없으면 INF가 저장되기 때문
			// -> 이런 경우를 걸러주기 위해 -1로 명확히 함
			Arrays.fill(dp[i], -1);
		}
	}
	
	private static int sol(int cur, int subset) {
		// 이미 값이 업데이트 되어 있음 -> 해당 값 반환
		if (dp[cur][subset] != -1) return dp[cur][subset];
		
		// 모든 도시 방문
		if (subset == bitCnt) {
			return graph[cur][0] == 0 ? INF : graph[cur][0];
		}
		
		// 최소값 계산을 위함
		dp[cur][subset] = INF;
		
		// 방문
		for (int i = 0; i < N; i++) {
			// 길이 없거나 이미 방문
			if (graph[cur][i] == 0 || (subset & (1 << i)) != 0) continue ;
			
			// 도시 i 방문 처리
			int nxt = subset | (1 << i);
			
			dp[cur][subset] = Math.min(dp[cur][subset], sol(i, nxt) + graph[cur][i]);
		}
		
		return dp[cur][subset];
	}

}
