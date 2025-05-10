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

    private static int N, M, ans; // N: 행 수, M: 열 수, ans: 최대 문자 개수
    private static char[][] map; // 입력 문자 맵
    private static int[][][][] DP; // 동적 프로그래밍 배열
                                   // DP[0][k][i][j]: (i,j)에서 오른쪽 아래 방향으로 이동할 때 문자 k의 최대 개수
                                   // DP[1][k][i][j]: (i,j)에서 왼쪽 위 방향으로 이동할 때 문자 k의 최대 개수

    public static void main(String args[]) throws Exception {
        init();
        makeDPMap(); // DP 배열 계산

        // 각 문자(Z, O, A, C)에 대해 최대 개수 계산
        for (int k = 0; k < 4; k++) {
            ans = 0;
            // 모든 좌표 (i,j)에 대해 해당 좌표를 기준으로 두 경로로 나누어 최대값 계산
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= M; j++)
                    // (i,j)까지 왼쪽 위에서 오는 경로 +
                    // max((i+1,1)에서 오른쪽 아래로 가는 경로, (1,j+1)에서 오른쪽 아래로 가는 경로)
                    ans = Math.max(ans, DP[1][k][i][j] + Math.max(DP[0][k][i + 1][1], DP[0][k][1][j + 1]));

            bw.write(ans + " "); // 각 문자별 최대값 출력
        }
        bw.write("\n");
        bwEnd();
    }

    // 입력을 받아 초기화하는 메소드
    public static void init() throws Exception {
        N = nextInt(); // 행 수 입력
        M = nextInt(); // 열 수 입력

        map = new char[N + 1][M + 1]; // 문자 맵 초기화 (1-indexed)
        DP = new int[2][4][N + 2][M + 2]; // DP 배열 초기화 (여유 공간 포함)

        // 문자 맵 입력 받기
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++)
                map[i][j] = nextToken().toCharArray()[0];
    }

    // DP 배열을 계산하는 메소드
    public static void makeDPMap() {
        char[] ch = { 'Z', 'O', 'A', 'C' }; // 찾아야 할 문자들

        // 각 문자에 대해 오른쪽 아래 방향으로의 DP 계산 (아래에서 위로, 오른쪽에서 왼쪽으로)
        for (int k = 0; k < 4; k++) {
            for (int i = N; i > 0; i--)
                for (int j = M; j > 0; j--) {
                    // 현재 위치에서 아래쪽 또는 오른쪽으로 이동했을 때의 최대값 선택
                    DP[0][k][i][j] = Math.max(DP[0][k][i + 1][j], DP[0][k][i][j + 1]);
                    // 현재 위치의 문자가 찾는 문자와 일치하면 +1
                    if (map[i][j] == ch[k])
                        DP[0][k][i][j]++;
                }
        }

        // 각 문자에 대해 왼쪽 위 방향으로의 DP 계산 (위에서 아래로, 왼쪽에서 오른쪽으로)
        for (int k = 0; k < 4; k++) {
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= M; j++) {
                    // 현재 위치에서 위쪽 또는 왼쪽으로 이동했을 때의 최대값 선택
                    DP[1][k][i][j] = Math.max(DP[1][k][i - 1][j], DP[1][k][i][j - 1]);
                    // 현재 위치의 문자가 찾는 문자와 일치하면 +1
                    if (map[i][j] == ch[k])
                        DP[1][k][i][j]++;
                }
        }
    }
}
