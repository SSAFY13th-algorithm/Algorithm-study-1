import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 할일_정하기1 {
	static int[][] dp; //dp[i][j] -> i번째 일까지 j 비트마스킹을 통해 표현된 사람들이 했을 때 최솟값  
	static int[][] work; // work[i][j] i번 사람이 j번 일을 할 때 비용
	static int num;
	static int size;
	static final int INF = Integer.MAX_VALUE/2;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		process();
		print();
	}

	private static void init() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		size = 1 << num;
		work = new int[num][num];
		dp = new int[num][size];
		
		for (int i = 0; i < num; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], INF);
			for (int j = 0; j < num; j++) work[i][j] = Integer.parseInt(st.nextToken());
		}
		
	}
	
	private static void process() {
		for (int i = 0; i < num; i++) {dp[0][1<<i] = work[i][0];}
		
		for (int i = 0; i < num-1; i++) {
			for (int j = 0; j < size; j++) {
				if (dp[i][j] == INF) continue;
				
				for (int k = 0; k < num; k++) {
					int tNum = j | (1<<k);
					if (tNum == j) continue;
					dp[i+1][tNum] = Math.min(dp[i+1][tNum],dp[i][j] + work[k][i+1]);
				}
			}
		}
	}
	
	private static void print() {System.out.println(dp[num-1][size-1]);}
}

