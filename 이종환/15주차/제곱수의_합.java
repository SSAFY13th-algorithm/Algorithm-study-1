import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Á¦°ö¼öÀÇ_ÇÕ {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int goal = Integer.parseInt(br.readLine());
		int[] dp = new int[goal+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		for (int i = 1; i < 400; i++) {
			if (i*i > goal) break;
			else dp[i*i] = 1;
		}
		
		for (int i = 2; i <= goal; i++) {
			int num = 1;
			while(true) {
				int target = num*num;
				if(target >= i) break;
				dp[i] = Math.min(dp[i-target]+1,dp[i] );
				num++;
			}
		}
		System.out.println(dp[goal]);
	}
}
