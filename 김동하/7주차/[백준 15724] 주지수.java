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
	public static int n,m,k;
	public static int arr[][];
	public static int dp[][];
	
	public static void init()throws IOException{
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		arr = new int[n + 1][m + 1];
		dp = new int[n + 1][m + 1];
		for(int i = 1; i <= n; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
				if(j == 1) dp[i][j] = arr[i][j];
				else dp[i][j] = dp[i][j - 1] + arr[i][j];
			}
		}
		k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			int sum = 0;
			for(int j = a; j <= c; j++) {
				sum += (dp[j][d] - dp[j][b]);
				sum += arr[j][b];
			}
			sb.append(sum).append("\n");
		}
	}
	
	
	
	public static void solution()throws IOException{
		init();
		
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
