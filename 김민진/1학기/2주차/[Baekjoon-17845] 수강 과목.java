import java.io.*;
import java.util.*;

public class Main {

	private static int	n;
	private static int	k;

	private static BufferedReader	br;
	private static StringTokenizer	st;

	private static int[][]	dp;

	public static void main(String[] args) throws IOException {

		init();
		sol();

	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		dp = new int[k+1][n+1];
	}

	private static void sol() throws IOException {
		int	time;
		int	importance;
		
		for (int i = 1; i < k+1; i++) {
			st = new StringTokenizer(br.readLine());
			importance = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());

			for (int j = 1; j < n+1; j++) {
				dp[i][j] = dp[i-1][j];

				if (j - time >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-time] + importance);
				}
			}
		}

		System.out.println(dp[k][n]);
	}
}