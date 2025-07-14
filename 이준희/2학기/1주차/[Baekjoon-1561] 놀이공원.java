import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] ride = new int[m];
		int max_time = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			ride[i] = Integer.parseInt(st.nextToken());
			max_time = Math.max(max_time, ride[i]);
		}

		if (n <= m) {
			System.out.println(n);
			return;
		}

		long left = 0;
		long right = (long) n * max_time;
		long t = 0;

		while (left <= right) {
			long mid = (left + right) / 2;
			long total = m;
			for (int i = 0; i < m; i++) {
				total += mid / ride[i];
			}
			if (total >= n) {
				t = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		long cnt = m;
		for (int i = 0; i < m; i++) {
			cnt += (t - 1) / ride[i];
		}

		for (int i = 0; i < m; i++) {
			if (t % ride[i] == 0) {
				cnt++;
				if (cnt == n) {
					System.out.println(i + 1);
					break;
				}
			}
		}
	}
}
