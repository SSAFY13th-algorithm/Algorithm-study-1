package week7;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q2RotatingDiscs {

	private static final int EMPTY      = 0;
	private static final int CLOCKWISE  = -1;
	private static final int CCLOCKWISE = 1;

	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N; // discs
	private static int M; // nums
	private static int T; // cnt
	private static int x; // disc to rotate
	private static int k; // rot count
	private static int d; // dir(CLOCKWISE || CCLOCKWISE)

	private static int[]   idx; // north
	private static int[][] discs;

	private static Set<Point> toRemove;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		idx      = new int[N];
		discs    = new int[N][M];
		toRemove = new HashSet<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				discs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void sol() throws IOException {
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			toRemove.clear();
			process(t);
		}
		System.out.println(getAns());
	}

	private static void process(int t) {
		rotate();
		removeDuplicatedNumbers();

		if (toRemove.isEmpty()) {
			updateDiscs();
		}
	}

	private static void rotate() {
		for (int i = x - 1; i < N; i += x) {
			int toMove = k * (d == 0 ? CLOCKWISE : CCLOCKWISE);
			idx[i] = (idx[i] + M + toMove) % M;
		}
	}

	private static void removeDuplicatedNumbers() {
		for (int i = 0; i < N; i++) {
			checkAdjacent(i);
		}

		for (Point p : toRemove) {
			discs[p.x][p.y] = EMPTY;
		}
	}

	private static void checkAdjacent(int disc) {
		int adj;
		int cnt = -1;
		while (++cnt < M) {
			int targetJ = (cnt + idx[disc]) % M;
			int target  = discs[disc][targetJ];

			if (target == EMPTY) continue;

			for (int d = -1; d <= 1; d += 2) {
				// horizontal
				adj = (targetJ + M + d) % M;
				if (discs[disc][adj] == target) {
					toRemove.add(new Point(disc, adj));
					toRemove.add(new Point(disc, targetJ));
				}

				// vertical
				if (disc + d < 0 || disc + d >= N) continue;
				int j = (idx[disc + d] + cnt) % M;
				adj = disc + d;
				if (discs[adj][j] == target) {
					toRemove.add(new Point(adj, j));
					toRemove.add(new Point(disc, targetJ));
				}
			}
		}
	}

	private static void updateDiscs() {
		double avg = getAvg();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (discs[i][j] == EMPTY) continue;

				if (discs[i][j] > avg) {
					discs[i][j]--;
				} else if (discs[i][j] < avg) {
					discs[i][j]++;
				}
			}
		}
	}

	private static double getAvg() {
		int sum = 0;
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (discs[i][j] == EMPTY) continue;

				sum += discs[i][j];
				cnt++;
			}
		}
		return (double) sum / cnt;
	}

	private static int getAns() {
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				ans += discs[i][j];
			}
		}
		return ans;
	}

}
