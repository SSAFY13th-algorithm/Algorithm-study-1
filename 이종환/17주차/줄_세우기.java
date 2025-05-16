import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 줄_세우기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//최장 증가 수열 (LIS, Longest Increasing Subsequence)
		int size = Integer.parseInt(br.readLine());
		int[] arr = new int[size];
		int[] dp = new int[size];
		for (int i = 0; i < size; i++) arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.fill(dp, 1);
		for (int i = 1; i < size; i++) {
			for (int j = 0; j <i; j++) {
				if (arr[i] < arr[j]) continue;
				dp[i] = Math.max(dp[i], dp[j]+1);
				
			}
		}
		int max = 1;
		for (int i = 0; i < size; i++) max = Math.max(max, dp[i]);
		System.out.println(size-max);

	}
	

}
