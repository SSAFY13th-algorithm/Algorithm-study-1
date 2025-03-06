import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static int arr[];
	public static int dp[][];
	public static int ans;
	
	public static void init()throws IOException{
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp = new int[n + 1][n + 1];
		dp[1][0] = arr[1];
		dp[0][1] = arr[n];
	}
	
	
	/*
	 * dp[2][0] => dp[1][0] + arr[2]
	 * dp[1][1] => Max(dp[0][1] + arr[1], dp[1][0] + arr[n]
	 * dp[0][2] => dp[0][1] + arr[n - 1]
	 * 
	 * dp[3][0] => dp[2][0] + arr[3]
	 * dp[2][1] => Max(dp[1][1] + arr[2], dp[2][0] + arr[
	 */
	public static void solution()throws IOException{
		init();
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) {
					dp[j][i - j] = dp[j][i - j - 1] + i * arr[n - i + j + 1];
				}
				else if(j == i) {
					dp[j][i - j] = dp[j - 1][i - j] + i * arr[j];
				}
				else {
					dp[j][i - j] = Math.max(dp[j - 1][i - j] + i * arr[j], dp[j][i - j - 1] + i * arr[n - i + j + 1]);
				}
			}
		}
		for(int i = 0; i <= n; i++) {
			ans = Math.max(ans, dp[i][n - i]);
			ans = Math.max(ans, dp[n - i][i]);
		}
		sb.append(ans);
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
