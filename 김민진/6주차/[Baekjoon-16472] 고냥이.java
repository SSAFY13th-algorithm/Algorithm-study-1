package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5Kitty {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int    N;
	private static int    len;
	private static int    ans;
	private static String str;

	private static int[] cnt;

	public static void main(String[] args) throws IOException {
		sol();
	}

	private static void sol() throws IOException {
		init();
		kitty();

		System.out.println(ans);
	}

	private static void init() throws IOException {
		N   = Integer.parseInt(br.readLine());
		str = br.readLine().trim();
		len = str.length();
		ans = 0;

		cnt = new int[26];

		br.close();
	}

	private static void kitty() {
		int left  = 0;
		int right = -1;
		int alpha = 0;

		while (++right < len) {
			int idx = str.charAt(right) - 'a';
			cnt[idx]++;

			if (cnt[idx] == 1) {
				alpha++;
			}

			while (alpha > N) {
				idx = str.charAt(left++) - 'a';
				cnt[idx]--;

				if (cnt[idx] == 0) {
					alpha--;
				}
			}
			ans = Math.max(ans, right - left + 1);
		}
	}

}
