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

    // N: 사람의 수, M: 관계의 수, ans: 정답(회장 후보), cnt: 최소 케빈 베이컨 수
    private static int N, M, ans, cnt = Integer.MAX_VALUE;
    private static int[][] E;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();

        E = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j)
                    continue;
                E[i][j] = Integer.MAX_VALUE / 2;
            }
        }

        for (int i = 0; i < M; i++) {
            readLine();
            int a = nextInt();
            int b = nextInt();
            E[a][b] = E[b][a] = 1;
        }

        BF();

        for (int i = 1; i <= N; i++) {
            int tmp = count(i);

            if (cnt > tmp) {
                cnt = tmp;
                ans = i;
            }
        }
        // 회장 후보 번호 출력
        bw.write(ans + "\n");
        printAnswer();
    }

    public static void BF() {
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    // (i→j 직접 가는 것과 i→k→j로 가는 것 중 최소값)
                    E[i][j] = Math.min(E[i][j], E[i][k] + E[k][j]);
    }

    // n번 사람의 케빈 베이컨 수 계산 (다른 모든 사람과의 최단 거리 합)
    public static int count(int n) {
        int ret = 0;
        for (int i = 1; i <= N; i++)
            ret += E[n][i];

        return ret;
    }
}
