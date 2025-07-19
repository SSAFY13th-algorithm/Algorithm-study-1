import java.io.*;

public class Main {

	private static final int	MOD = 9901;

	private static int		n;
	private static int[]	dp;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		dp = new int[n];

		// init dp
		dp[0] = 3;

		if (n > 1) {
			dp[1] = 7;

			for (int i = 2; i < n; i++) {
				dp[i] = (2 * dp[i - 1] + dp[i - 2]) % MOD;
			}
		}
		System.out.println(dp[n-1]);
	}
}