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

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    // N: 부품의 개수
    // M: 부품 간의 관계 개수
    // ans: 정답 (사용되지 않는 변수)
    private static int N, M, ans;

    private static int[][] graph; // 부품 간의 관계를 저장하는 인접 행렬 (graph[X][Y] = K: X를 만드는데 Y가 K개 필요)
    private static int[] pointed; // 각 부품을 가리키는 화살표(선행 관계) 개수
    private static int[] material; // 각 부품의 필요 개수를 저장하는 배열
    private static ArrayDeque<Integer> q; // 위상 정렬에 사용할 큐

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        readLine();
        M = nextInt();

        graph = new int[N + 1][N + 1];
        pointed = new int[N + 1];
        material = new int[N + 1];
        q = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            readLine();
            int X = nextInt();
            int Y = nextInt();
            int K = nextInt();
            graph[X][Y] = K;
            pointed[Y]++;
        }

        // 시작점: 완제품인 N번 부품부터 시작
        q.add(N);
        material[N] = 1;

        topology();

        printMaterials();
        printAnswer();
    }

    // 위상 정렬을 수행하는 메소드
    public static void topology() {
        while (!q.isEmpty()) {
            int now = q.poll();
            int nowWeight = material[now];
            boolean flag = false; // 현재 부품이 중간 부품인지 확인하는 플래그

            // 현재 부품을 만드는데 필요한 다른 부품들 확인
            for (int i = 1; i <= N; i++) {
                if (graph[now][i] != 0) { // now 부품을 만드는데 i 부품이 필요한 경우
                    if (--pointed[i] == 0) // i 부품을 가리키는 화살표 개수 감소, 0이 되면 큐에 추가
                        q.add(i);
                    flag = true; // 현재 부품은 중간 부품임을 표시

                    material[i] += graph[now][i] * nowWeight;
                }
            }

            // 현재 부품이 중간 부품이면 기본 부품이 아니므로 개수를 0으로 설정
            if (flag)
                material[now] = 0;
        }
    }

    // 기본 부품(더 이상 분해할 수 없는 부품)의 번호와 필요 개수 출력
    public static void printMaterials() {
        for (int i = 1; i <= N; i++)
            if (material[i] != 0)
                System.out.printf("%d %d\n", i, material[i]);
    }
}
