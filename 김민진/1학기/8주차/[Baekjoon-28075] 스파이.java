package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5Spy {

	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N; // day
	private static int M; // contribution
	private static int cnt;

	private static int[][] progress;

	public static void main(String[] args) throws IOException {
		init();
		sol(0, 0, -1);

		System.out.println(cnt);
	}

	private static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N   = Integer.parseInt(st.nextToken());
		M   = Integer.parseInt(st.nextToken());
		cnt = 0;

		progress = new int[2][3];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				progress[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void sol(int depth, int sum, int pre) {
		if (depth == N) {
			if (sum >= M) cnt++;
			return;
		}

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				sol(depth + 1,
						sum + (pre == j ? progress[i][j] / 2 : progress[i][j]), j);
			}
		}
	}

}
