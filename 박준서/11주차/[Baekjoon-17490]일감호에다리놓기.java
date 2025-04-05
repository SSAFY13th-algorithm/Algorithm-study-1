import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static long nextLong() {
        return Long.parseLong(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    // N: 징검다리의 수, M: 부서진 경로의 수, K: 최대 비용
    private static int N, M;
    private static long K;

    // jinggums: 각 징검다리의 비용을 저장하는 배열
    private static int[] jinggums;
    // brokenRoutes: 부서진 경로를 표시하는 배열 (1이면 부서짐)
    private static int[] brokenRoutes;
    // routes: 징검다리 번호를 저장하는 큐
    private static Queue<Integer> routes;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();
        K = nextLong();

        jinggums = new int[N + 1];
        brokenRoutes = new int[N];
        routes = new ArrayDeque<>();

        // 각 징검다리의 비용 입력 받기
        readLine();
        for (int i = 1; i <= N; i++) {
            jinggums[i] = nextInt();
            routes.add(i);
        }

        long totalCost = 0;

        for (int i = 0; i < M; i++) {
            readLine();
            int a = nextInt(), b = nextInt();
            // a와 b를 오름차순으로 정렬
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            if (a + 1 == b)
                brokenRoutes[a] = 1;// 인접한 징검다리 사이의 경로가 부서진 경우
            else
                brokenRoutes[0] = 1; // 인접하지 않은 경로가 부서진 경우
        }

        // 부서진 경로가 2개 미만이면 항상 가능
        if (M < 2)
            bw.write("YES\n");
        else {
            // 부서진 경로가 나올 때까지 큐를 회전
            while (brokenRoutes[routes.peek()] == 0)
                routes.add(routes.poll());
            routes.add(routes.poll()); // 첫 번째 부서진 경로를 큐의 맨 뒤로 이동

            // 각 구간별 최소 비용을 계산
            int minRoute = Integer.MAX_VALUE;
            while (!routes.isEmpty()) {
                int now = routes.poll();
                minRoute = Math.min(minRoute, jinggums[now]); // 현재 구간의 최소 비용 갱신

                // 현재 위치 다음이 부서진 경로라면 구간이 끝남
                if (brokenRoutes[now % N] == 1) {
                    totalCost += minRoute; // 구간의 최소 비용을 총 비용에 추가
                    minRoute = Integer.MAX_VALUE; // 다음 구간을 위해 최소값 초기화
                }
            }

            // 총 비용이 K 이하면 가능, 아니면 불가능
            if (totalCost <= K) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        printAnswer();
    }
}
