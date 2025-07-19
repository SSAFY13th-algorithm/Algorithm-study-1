package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Q1AdolesentShark {

	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	private static final int[]  dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	private static final int[]  dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	private static int          maxSum;
	private static Shark        shark;

	private static int[][]                  fishArr;
	private static HashMap<Integer, Fish>   fishMap;

	static class Fish {
		int x;
		int y;
		int dir;
		boolean isAlive;

		Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir - 1;
			this.isAlive = true;
		}

		void setIsAlive(boolean isAlive) {
			this.isAlive = isAlive;
		}

		void updatePoint(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void updateDir(int dir) {
			this.dir = dir;
		}
	}

	static class Shark {
		int x;
		int y;
		int dir;

		Shark() {
			this.x = 0;
			this.y = 0;
			this.dir = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		shark = new Shark();
		fishArr = new int[4][4];
		fishMap = new HashMap<>();

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) {
				int key = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				fishArr[i][j] = key;
				fishMap.put(key, new Fish(i, j, dir));
			}
		}
	}

	private static void sol() {
		// start to eat
		int key = fishArr[0][0];
		Fish eaten = fishMap.get(key);

		// initial eating
		fishArr[0][0] = -1;
		shark.dir = eaten.dir;
		fishMap.remove(key);

		moveShark(key);
		
		System.out.println(maxSum);
	}

	private static void moveShark(int total) {
		maxSum = Math.max(maxSum, total);

		moveFish();

		// move 1~3
		for (int i = 1; i <= 3; i++) {
			int nx = shark.x + dx[shark.dir] * i;
			int ny = shark.y + dy[shark.dir] * i;

			if (!isValidPoint(nx, ny) || fishArr[nx][ny] <= 0) continue;

			// current state
			int[][] 				tempArr = copyArray(fishArr);
			HashMap<Integer, Fish>	tempMap = copyMap(fishMap);
			
			int prevX = shark.x;
			int prevY = shark.y;
			int prevDir = shark.dir;

			// eat
			int	 fishNum = fishArr[nx][ny];
			Fish targetFish = fishMap.get(fishNum);
			
			fishArr[prevX][prevY] = 0;
			fishArr[nx][ny] = -1;
			fishMap.remove(fishNum);

			shark.x = nx;
			shark.y = ny;
			shark.dir = targetFish.dir;

			moveShark(total + fishNum);

			// restore
			fishArr = copyArray(tempArr);
			fishMap = copyMap(tempMap);
			shark.x = prevX;
			shark.y = prevY;
			shark.dir = prevDir;
		}
	}

	private static void moveFish() {
		for (int i = 1; i <= 16; i++) {
			if (!fishMap.containsKey(i)) continue;

			Fish fish = fishMap.get(i);
			int  originalDir = fish.dir;

			for (int d = 0; d < 8; d++) {
				int nextDir = (originalDir + d) % 8;
				int nx = fish.x + dx[nextDir];
				int ny = fish.y + dy[nextDir];

				if (!isValidPoint(nx, ny) || fishArr[nx][ny] == -1) continue;

				fish.dir = nextDir;

				if (fishArr[nx][ny] == 0) {
					// empty block
					fishArr[fish.x][fish.y] = 0;
					fishArr[nx][ny] = i;
					fish.updatePoint(nx, ny);
				} else {
					// swap
					int  targetFishNum = fishArr[nx][ny];
					Fish targetFish = fishMap.get(targetFishNum);

					int tx = fish.x, ty = fish.y;
					fishArr[tx][ty] = targetFishNum;
					fishArr[nx][ny] = i;

					fish.updatePoint(nx, ny);
					targetFish.updatePoint(tx, ty);
				}
				break;
			}
		}
	}

	private static int[][] copyArray(int[][] arr) {
		int[][] copy = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			copy[i] = Arrays.copyOf(arr[i], 4);
		}
		return copy;
	}

	private static HashMap<Integer, Fish> copyMap(HashMap<Integer, Fish> map) {
		HashMap<Integer, Fish> copy = new HashMap<>();
		
		for (int key : map.keySet()) {
			Fish original = map.get(key);
			Fish newFish = new Fish(original.x, original.y, original.dir + 1);

			newFish.dir = original.dir;
			copy.put(key, newFish);
		}
		return copy;
	}

	private static boolean isValidPoint(int x, int y) {
		return 0 <= x && x < 4 && 0 <= y && y < 4;
	}
}