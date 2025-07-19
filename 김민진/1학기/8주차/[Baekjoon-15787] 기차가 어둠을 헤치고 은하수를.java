package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q3MilkyWayTraversal {

	private static BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int N;
	private static int M;
	private static int command;
	private static int targetTrain;
	private static int passenger;

	private static int[] train;

	private static Set<Integer> ans;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		train = new int[N];
		ans   = new HashSet<>();
	}

	private static void sol() throws IOException {
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			command     = Integer.parseInt(st.nextToken());
			targetTrain = Integer.parseInt(st.nextToken()) - 1;
			passenger   = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) - 1 : -1;

			switch (command) {
				case 1:
					getOnTrain();
					break;
				case 2:
					getOffTrain();
					break;
				case 3:
					shiftBack();
					break;
				case 4:
					shiftFront();
					break;
			}
		}
		putTrainsInSet();
		System.out.println(ans.size());
	}

	private static void getOnTrain() {
		train[targetTrain] |= (1 << passenger);
	}

	private static void getOffTrain() {
		train[targetTrain] &= ~(1 << passenger);
	}

	private static void shiftBack() {
		train[targetTrain] = ((train[targetTrain] << 1) & ((1 << 20) - 1));
	}

	private static void shiftFront() {
		train[targetTrain] = (train[targetTrain] >> 1);
	}

	private static void putTrainsInSet() {
		for (int i = 0; i < N; i++) {
			ans.add(train[i]);
		}
	}

}
