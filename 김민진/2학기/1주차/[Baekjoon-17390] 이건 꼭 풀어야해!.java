package semester2.week1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q3YouMustSolveThis {

	private static final BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static final StringBuilder   sb = new StringBuilder();
	private static       StringTokenizer st;

	private static int   A;
	private static int   Q;
	private static int[] nums;
	private static int[] prefix;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		nums = new int[A + 1];
		st   = new StringTokenizer(br.readLine());
		for (int i = 1; i <= A; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		prefix = new int[A + 1];
	}

	private static void sol() throws IOException {
		makePrefix();

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());

			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			sb.append(prefix[R] - prefix[L - 1]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void makePrefix() {
		for (int i = 1; i <= A; i++) {
			prefix[i] = prefix[i - 1] + nums[i];
		}
	}

}
