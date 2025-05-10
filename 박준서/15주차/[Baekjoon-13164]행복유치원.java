import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static int N, M; // N: 센서 개수, M: 집중국 개수
    private static long ans; // 정답(최소 신호 합)
    private static PriorityQueue<Integer> pq; // 인접 센서 간 거리 저장용 최소 힙

    public static void main(String args[]) throws Exception {
        N = nextInt();
        M = nextInt();
        pq = new PriorityQueue<>(Comparator.naturalOrder()); // 오름차순 정렬(최소 힙)

        // 첫 번째 센서 부터 시작
        int post = nextInt(), now = 0;
        // 두 번째 센서부터 차례대로 읽으며 인접 거리 계산
        for (int i = 1; i < N; i++) {
            now = nextInt();
            pq.add(now - post); // 인접 센서 사이 거리 추가
            post = now; // 현재 위치를 이전 위치로 업데이트
        }

        // (N-M)개의 그룹이 될 때까지 최소 거리 제거
        // N-M개의 최소 간격 합이 정답
        for (int i = 0; i < N - M; i++)
            ans += pq.poll();

        bw.write(ans + "\n");
        bwEnd();
    }
}
