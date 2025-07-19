package week3;

import java.util.*;
import java.io.*;

public class Q1BabyShark {

	private static int      N;
	private static int      ans;
	private static Shark    shark;

	private static final int[]  dx = { -1, 0, 1, 0 };
	private static final int[]  dy = { 0, -1, 0, 1 };

	private static int[][]      matrix;
	private static boolean[][]  visited;

	private static Queue<Block> q;  // bfs

	static class Shark {
		int x;
		int y;
		int size;
		int eatenFish;

		Shark(int x, int y, int size, int eatenFish) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.eatenFish = eatenFish;
		}

		/**
		 * Update state of shark after eating fish
		 *
		 * @param x next row
		 * @param y next column
		 */
		public void updateInfo(int x, int y) {
			this.x = x;
			this.y = y;
			eatenFish++;

			if (eatenFish == size) {
				size++;
				eatenFish = 0;
			}
		}
	}

	static class Block {
		int x;
		int y;
		int time;

		Block(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		ans = 0;
		matrix = new int[N][N];
		visited = new boolean[N][N];

		q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());

				if (matrix[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					matrix[i][j] = 0;
				}
			}
		}
	}

	private static void sol() {
		do clearAll(); while (findFish());

		System.out.println(ans);
	}

	/**
	 * search matrix and find fish that shark can eat
	 *
	 * @return  whether edible fish exists or not
	 */
	private static boolean findFish() {
		Block fishToEat = null;
		int minDist = Integer.MAX_VALUE;

		q.offer(new Block(shark.x, shark.y, 0));
		visited[shark.x][shark.y] = true;

		while (!q.isEmpty()) {
			Block b = q.poll();

			if (fishToEat != null && b.time > minDist) break;

			for (int i = 0; i < 4; i++) {
				int nx = b.x + dx[i];
				int ny = b.y + dy[i];

				if (!checkOOB(nx, ny) || visited[nx][ny] || shark.size < matrix[nx][ny]) continue;

				visited[nx][ny] = true;

				if (matrix[nx][ny] != 0 && matrix[nx][ny] < shark.size) {
					// first discovered
					if (fishToEat == null) {
						fishToEat = new Block(nx, ny, b.time + 1);
						minDist = b.time + 1;
					// when distances are equal -> choose by priority
					// 1. upper pos
					// 2. leftmost (if rows are same)
					} else if (b.time + 1 == minDist) {
						if (nx < fishToEat.x || (nx == fishToEat.x && ny < fishToEat.y)) {
							fishToEat = new Block(nx, ny, b.time + 1);
						}
					}
				}
				q.offer(new Block(nx, ny, b.time + 1));
			}
		}
		return fishToEatExists(fishToEat, minDist);
	}

	private static boolean fishToEatExists(Block b, int dist) {
		if (b != null) {
			matrix[b.x][b.y] = 0;
			shark.updateInfo(b.x, b.y);
			ans += dist;
			return true;
		}

		return false;
	}

	private static boolean checkOOB(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	private static void clearAll() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		q.clear();
	}
}