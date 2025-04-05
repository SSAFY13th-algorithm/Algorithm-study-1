package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q2SemiconductorDesign {

	private static final BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static       StringTokenizer st;

	private static int   N;
	private static int[] chips;

	private static List<Integer> res;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());

		chips = new int[N];
		st    = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			chips[i] = Integer.parseInt(st.nextToken());
		}
		res = new ArrayList<>();
	}

	private static void sol() {
		res.add(0);
		for (int chip : chips) {
			if (res.get(res.size() - 1) < chip) {
				res.add(chip);
			} else {
				int idx = binarySearch(0, res.size() - 1, chip);
				res.set(idx, chip);
			}
		}
		System.out.println(res.size() - 1);
	}

	private static int binarySearch(int s, int e, int target) {
		while (s < e) {
			int mid = (s + e) / 2;

			if (res.get(mid) < target) {
				s = mid + 1;
			} else {
				e = mid;
			}
		}
		return e;
	}

}
