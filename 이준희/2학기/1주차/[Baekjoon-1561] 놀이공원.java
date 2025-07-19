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

		if (n <= m) { // 기다리는 아이가 놀이기구 개수보다 작으면 바로 출력
			System.out.println(n);
			return;
		}

		long left = 0;
		long right = (long) n * max_time;
		long t = 0;

		while (left <= right) { // 모든 아이가 놀이기구를 다 탈 수 있는 시간 중 최소값 구하기
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
		for (int i = 0; i < m; i++) { // 모든 아이가 놀이기구를 다 타기 직전의 상태 찾기
			cnt += (t - 1) / ride[i];
		}

		for (int i = 0; i < m; i++) { // 놀이기구를 다 탄 시점
			if (t % ride[i] == 0) { // 이 때 0이므로 탈 수 있음
				cnt++;
				if (cnt == n) { // 줄 마지막 아이가 탔으면 출력
					System.out.println(i + 1);
					break;
				}
			}
		}
	}
}
