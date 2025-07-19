package semester2.week1;

import java.io.*;
import java.util.StringTokenizer;

public class Q5Scale {

	private static final BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder   sb = new StringBuilder();
	private static       StringTokenizer st;

	private static int N;
	private static int M;

	private static int[][] mat;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		mat = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			mat[a][b] = 1;  // big
			mat[b][a] = -1; // small
		}
	}

	private static void sol() throws IOException {
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					// big
					if (mat[i][k] == 1 && mat[k][j] == 1) {
						mat[i][j] = 1;

						// small
					} else if (mat[i][k] == -1 && mat[k][j] == -1) {
						mat[i][j] = -1;
					}
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			int cnt = 0;
			for (int j = 1; j < N + 1; j++) {
				if (i != j && mat[i][j] == 0) {
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
