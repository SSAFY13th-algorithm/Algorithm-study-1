package week8;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Q4InstallMirror {

	private static final int[] re = { 1, 0, 3, 2 };
	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;

	private static Point start;
	private static Point end;

	private static char[][] house;

	private static Queue<Node> q;

	private static class Node {
		Point p;
		int   dir;
		int   dist;

		Node(Point p, int dir, int dist) {
			this.p    = p;
			this.dir  = dir;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		house = new char[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				house[i][j] = s.charAt(j);

				if (house[i][j] == '#') {
					if (start == null) {
						start = new Point(i, j);
					} else {
						end = new Point(i, j);
					}
				}
			}
		}

		q = new ArrayDeque<>();
		// 시작점 인접 4방향
		for (int i = 0; i < 4; i++) {
			q.add(new Node(start, i, 0));
		}
	}

	private static void sol() {
		while (!q.isEmpty()) {
			Node cur = q.poll();

			// end point
			if (cur.p.equals(end)) {
				System.out.println(cur.dist - 1);
				return;
			}

			int cx = cur.p.x;
			int cy = cur.p.y;
			int cd = cur.dir;

			for (int i = 0; i < 4; i++) {
				if (i == cd || isOpposite(i, cd)) continue;

				int nx = cx + dx[i];
				int ny = cy + dy[i];

				findNextPoint(nx, ny, i, cur.dist);
			}
		}
	}

	private static void findNextPoint(int x, int y, int dir, int dist) {
		Point next;

		while (!OOB(x, y)) {
			// wall
			if (house[x][y] == '*') {
				break;
			}

			next = new Point(x, y);
			if (house[x][y] == '!' || house[x][y] == '#') {
				q.add(new Node(next, dir, dist + 1));
				// instead of visited
				house[x][y] = '.';
			}

			x += dx[dir];
			y += dy[dir];
		}
	}

	private static boolean isOpposite(int i, int d) {
		return i == re[d];
	}

	private static boolean OOB(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

}
