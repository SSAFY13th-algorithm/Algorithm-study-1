package week8;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Q1WizardSharkAndFireStorm {

	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int Q;
	private static int ans; // total sum of ice
	private static int cnt; // num of ice
	private static int len;

	private static int[] cmd;

	private static int[][]     matrix;
	private static int[][]     copy;
	private static boolean[][] visited;

	private static List<Point> toDecrease;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		ans = 0;
		cnt = 0;
		len = 1 << N;

		copy   = new int[len][len];
		matrix = new int[len][len];
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				ans += matrix[i][j];
			}
		}

		cmd = new int[Q];
		st  = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}

		visited    = new boolean[len][len];
		toDecrease = new ArrayList<>();
	}

	private static void sol() {
		for (int c : cmd) {
			int pow = 1 << c;

			rotate(pow);
			meltIce();
			copyToOrigin();
		}
		getAns();
	}

	private static void rotate(int pow) {
		for (int a = 0; a < len; a += pow) {
			for (int b = 0; b < len; b += pow) {
				// make copy
				for (int i = 0; i < pow; i++) {
					for (int j = 0; j < pow; j++) {
						// rotate
						copy[a + j][b + pow - i - 1] = matrix[a + i][b + j];
					}
				}
			}
		}
	}

	private static void meltIce() {
		toDecrease.clear();

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (!hasMoreThanThree(i, j)) {
					toDecrease.add(new Point(i, j));
				}
			}
		}

		for (Point p : toDecrease) {
			if (copy[p.x][p.y] <= 0) {
				continue;
			}

			copy[p.x][p.y]--;
			ans--;
		}
	}

	private static boolean hasMoreThanThree(int i, int j) {
		int cnt = 0;

		for (int d = 0; d < 4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];

			if (OOB(nx, ny)) {
				continue;
			}

			if (copy[nx][ny] > 0) {
				cnt++;
			}
		}
		return cnt >= 3;
	}

	private static void copyToOrigin() {
		for (int i = 0; i < len; i++) {
			System.arraycopy(copy[i], 0, matrix[i], 0, len);
		}
	}

	private static void getAns() {
		// count ice
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (visited[i][j] || matrix[i][j] == 0) {
					continue;
				}

				cnt = Math.max(cnt, bfs(i, j));
			}
		}

		System.out.println(ans);
		System.out.println(cnt);
	}

	private static int bfs(int i, int j) {
		Queue<Point> q = new ArrayDeque<>();

		q.add(new Point(i, j));
		visited[i][j] = true;

		int tmp = 1;
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (OOB(nx, ny) || visited[nx][ny] || matrix[nx][ny] == 0) {
					continue;
				}

				q.offer(new Point(nx, ny));
				visited[nx][ny] = true;
				tmp++;
			}
		}
		return tmp;
	}

	private static boolean OOB(int x, int y) {
		return x < 0 || len <= x || y < 0 || len <= y;
	}

}