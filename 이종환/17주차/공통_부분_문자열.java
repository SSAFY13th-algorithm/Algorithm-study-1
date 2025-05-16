import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통_부분_문자열 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input1 = br.readLine().split("");
		String[] input2 = br.readLine().split("");
		int[][] dp = new int[input1.length][input2.length];
		int ans = 0;
		int temp = 0;
		for (int i = 0; i < input1.length; i++) {
			for (int j = 0; j < input2.length; j++) {
				if(input1[i].equals(input2[j])) {
					dp[i][j] = 1;
					if(i == 0 || j == 0) continue;
					
					dp[i][j] += dp[i-1][j-1];
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		System.out.println(ans);
	}
}
