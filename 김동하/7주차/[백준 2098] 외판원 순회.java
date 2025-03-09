import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static int[][] arr;
	public static int[][] dp;
	public static int INF = 987654321;
	
	public static void init()throws IOException{
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1][n + 1];
		StringTokenizer stk;
		for(int i = 0; i < n; i++) {
			 stk = new StringTokenizer(br.readLine());
			 for(int j = 0; j < n; j++) {
				 arr[i][j] = Integer.parseInt(stk.nextToken());
			 }
		}
		dp = new int[n + 1][(1 << n)];
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j < (1 << n); j++) {
				dp[i][j] = -1;
			}
		}
	}
	
	public static int dfs(int cur, int visited) {
		if(visited == (1 << n) - 1) {
			if(arr[cur][0] == 0) return INF;
			return arr[cur][0];
		}
		if(dp[cur][visited] != -1) return dp[cur][visited];
		dp[cur][visited] = INF;
		for(int i = 0; i < n; i++) {
			if((visited & (1 << i)) == 0 && arr[cur][i] != 0) {
				dp[cur][visited] = Math.min(dfs(i, visited | 1 << i) + arr[cur][i], dp[cur][visited]);
			}
		}
		return dp[cur][visited];
	}
	
	public static void solution()throws IOException{
		init();
		sb.append(dfs(0, 1));
		
		bw.append(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static void main(String[] args)throws IOException{
		//--------------솔루션 코드를 작성하세요.--------------------------------
		solution();
	}
}
