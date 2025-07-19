package week7;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1WizardSharkAndTornado {

	private static final int[] dx  = { 0, 1, 0, -1 };
	private static final int[] dy  = { -1, 0, 1, 0 };
	private static final int[] per = { 5, 10, 10, 2, 2, 7, 7, 1, 1 };

	private static final int[][] sandx = {
			{ 0, -1, 1, -2, 2, -1, 1, -1, 1 },
			{ 2, 1, 1, 0, 0, 0, 0, -1, -1 },
			{ 0, -1, 1, -2, 2, -1, 1, -1, 1 },
			{ -2, -1, -1, 0, 0, 0, 0, 1, 1 },
	};
	private static final int[][] sandy = {
			{ -2, -1, -1, 0, 0, 0, 0, 1, 1 },
			{ 0, -1, 1, -2, 2, -1, 1, -1, 1 },
			{ 2, 1, 1, 0, 0, 0, 0, -1, -1 },
			{ 0, -1, 1, -2, 2, -1, 1, -1, 1 },
	};

	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int ans;
	private static int dir;
	private static int steps;
	private static int turned;

	private static Point cur;

	private static int[][] matrix;

	public static void main(String[] args) throws IOException {
		init();
		sol();
		
		System.out.println(ans);
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		ans    = 0;
		dir    = 0;	// left
		steps  = 1;
		turned = 0;

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cur = new Point(N / 2, N / 2);
	}

	private static void sol() {
		while (cur.x >= 0 && cur.y >= 0) {
			for (int i = 0; i < steps; i++) {
				move();
				enchant();
			}
			turn();
		}
	}

	private static void move() {
		cur.x += dx[dir];
		cur.y += dy[dir];
	}

	private static void enchant() {
		if (OOB(cur.x, cur.y)) return ;

		int nx;
		int ny;
		int cn = matrix[cur.x][cur.y];

		for (int i = 0; i < 9; i++) {
			nx = cur.x + sandx[dir][i];
			ny = cur.y + sandy[dir][i];
			int ns = matrix[cur.x][cur.y] * per[i] / 100;

			if (OOB(nx, ny)) {
				ans += ns;
			} else {
				matrix[nx][ny] += ns;
			}
			cn -= ns;
		}

		// leftover
		nx = cur.x + dx[dir];
		ny = cur.y + dy[dir];

		if (OOB(nx, ny)) {
			ans += cn;
		} else {
			matrix[nx][ny] += cn;
		}
	}

	private static void turn() {
		turned++;
		dir = (dir + 1) % 4;

		if (turned == 2) {
			turned = 0;
			steps++;
		}
	}

	private static boolean OOB(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

}
