package semester2.week1;

import java.io.*;
import java.util.StringTokenizer;

public class Q4AmusementPark {

	private static final BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
	private static final BufferedWriter  bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static       StringTokenizer st;

	private static int   N;
	private static int   M;
	private static int[] opTime;

	public static void main(String[] args) throws IOException {
		init();
		sol();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		opTime = new int[M];
		st     = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			opTime[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void sol() throws IOException {
		// 놀이기구 수가 많거나 같을 때
		if (N <= M) {
			bw.write(N + "");
		} else {
			long left       = 0;
			long right      = N / M * 30L; // 최대 소요 시간
			long targetTime = 0;

			// N번째 아이가 탈 수 있는 시간 찾기
			while (left <= right) {
				long mid           = (left + right) / 2;
				long totalChildren = M;

				// mid 시간에 탈 수 있는 아이의 수
				for (int i = 0; i < M; i++) {
					totalChildren += mid / opTime[i];
				}

				// N명 이상의 아이가 탈 수 있음 → 범위 좁히기
				if (totalChildren >= N) {
					targetTime = mid;
					right      = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			getAns(targetTime);
		}
		bw.flush();
		bw.close();
	}

	private static void getAns(long time) throws IOException {
		// N-1번째 아이가 타는 시간(time - 1)을 기반으로
		long childrenBefore = M;
		for (int i = 0; i < M; i++) {
			childrenBefore += (time - 1) / opTime[i];
		}

		// N번째 아이가 어느 놀이기구에 타는지 찾기
		// 이분탐색에서 N번째 아이가 탈 수 있는 최소 시간을 찾음
		// 따라서 time일 때 최소 하나 이상의 비어있는 기구가 있음이 보장됨
		for (int i = 0; i < M; i++) {
			if (time % opTime[i] == 0) {
				childrenBefore++;
				if (childrenBefore == N) {
					bw.write((i + 1) + "\n");
					return;
				}
			}
		}
	}
}