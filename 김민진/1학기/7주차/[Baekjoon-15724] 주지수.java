package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3ProvincialLandCount {
	
	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder   sb = new StringBuilder();
	private static StringTokenizer st;
	
	private static int N;
	private static int M;
	
	private static int[][] land;
	private static int[][] prefix;

	public static void main(String[] args) throws IOException {
		init();
		sol();

		System.out.println(sb);
	}
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		land = new int[N][M];
		prefix = new int[N+1][M+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				land[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getPrefix();
	}

	private static void getPrefix() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + land[i-1][j-1];
			}
		}
	}
	
	private static void sol() throws IOException {
		int cmd = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < cmd; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sb.append(getSum(x1-1, y1-1, x2, y2)).append('\n');
		}
	}
	
	private static int getSum(int x1, int y1, int x2, int y2) {
		return prefix[x2][y2] - prefix[x1][y2] - prefix[x2][y1] + prefix[x1][y1];
	}
	
}
