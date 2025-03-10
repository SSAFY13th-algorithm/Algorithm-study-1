import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class 수확 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine());
		int[] arr =new int[size];
		Deque<Integer> dQ = new ArrayDeque<Integer>();
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long sum = 0;
		
		int[][] dp = new int[size][size]; // start, end; 
		
		dp[0][size-1] = 0;
		
		for (int i = 1; i < size; i++) {
			for (int j = 0; j <= i ; j++) {
				int start = j;
				int end = j+size-i-1;;
				
				if ( start == 0) {
					dp[start][end] = dp[start][end+1] + arr[end+1]*i;
				} else if (end == size-1) {
					dp[start][end] = dp[start-1][end] + arr[start-1]*i;
				}else {
					// 결국 특정 상황은 왼쪽걸 지우고 왔거나, 오른쪽 것을 지우고 왔을것임
					dp[start][end] = Math.max(dp[start][end+1] + arr[end+1]*i, dp[start-1][end] + arr[start-1]*i);
				}

			}
		}
		
		int ans = 0;
		
		for (int i = 0; i< size; i++) {
			ans = Math.max(ans, dp[i][i] + arr[i]*size);
		}
		
		System.out.println(ans);
		
		
	}
}
