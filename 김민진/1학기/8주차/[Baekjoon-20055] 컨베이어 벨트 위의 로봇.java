package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2RobotOnConveyorBelt {
	
	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int K;
	private static int cnt;  // count of zero durability
	private static int time;
	private static int idxC; // idx of conveyer

	private static int[]     conveyor;
	private static boolean[] robot;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N  = Integer.parseInt(st.nextToken());
		K  = Integer.parseInt(st.nextToken());
		
		cnt  = 0;
		time = 0;
		idxC = 0;

		conveyor = new int[2 * N];
		robot    = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			conveyor[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void sol() {
		while (true) {
			time++;
			rotate();
			
			if (moveRobot() || putRobot()) break;
		}
		System.out.println(time);
	}

	private static void rotate() {
		// rotate conveyor belt
		idxC = (idxC - 1 + 2 * N) % (2 * N);

		// rotate Robot
		for (int i = N-1; i > 0; i--) {
			robot[i] = robot[i-1];
		}
		robot[0] = false;
	}

	private static boolean moveRobot() {
		// drop the robot at the end
		if (robot[N-1]) {
			robot[N-1] = false;
		}

		for (int i = N-2; i >= 0; i--) {
			int nxt = (idxC + i + 1) % (2 * N);
			// can move
			if (robot[i] && !robot[i+1] && conveyor[nxt] > 0) {
				robot[i] = false;
				robot[i+1] = true;
				conveyor[nxt]--;

				if (conveyor[nxt] == 0) {
					cnt++;
					if (cnt >= K) return true;
				}
			}
		}
		return false;
	}

	private static boolean putRobot() {
		if (conveyor[idxC] <= 0 || robot[0]) return false;

		robot[0] = true;
		conveyor[idxC]--;

		if (conveyor[idxC] == 0) {
			cnt++;
			if (cnt == K) return true;
		}
		return false;
	}

}
