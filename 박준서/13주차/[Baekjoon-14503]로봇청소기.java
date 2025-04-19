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

    // 상(0), 우(1), 하(2), 좌(3) 방향으로의 이동을 위한 배열
    private static final int[] dx = { 0, 1, 0, -1 };
    private static final int[] dy = { -1, 0, 1, 0 };

    private static int N, M, ans; // N: 행 크기, M: 열 크기, ans: 청소한 칸 수
    private static int x, y, d; // 로봇 청소기의 현재 위치(x,y)와 방향(d)
    private static int[][] matrix; // 방의 상태를 저장할 2차원 배열

    public static void main(String args[]) throws Exception {
        N = nextInt();
        M = nextInt();
        y = nextInt();
        x = nextInt();
        d = nextInt();

        matrix = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                matrix[i][j] = nextInt();

        while (true) {
            // 1. 현재 위치가 아직 청소되지 않았다면 청소
            if (matrix[y][x] == 0) {
                ans++; // 청소한 칸 수 증가
                matrix[y][x] = 2; // 청소된 상태를 2로 표시
            }

            // 2. 주변 4방향 탐색
            boolean moved = false; // 이동 여부 플래그
            for (int i = 0; i < 4; i++) {
                // 현재 방향에서 왼쪽 방향부터 차례대로 탐색 ((d+3-i)%4로 왼쪽 방향 계산)
                int nx = x + dx[(d + 3 - i) % 4];
                int ny = y + dy[(d + 3 - i) % 4];

                // 벽이거나 이미 청소된 칸이면 다음 방향 탐색
                if (matrix[ny][nx] > 0)
                    continue;

                // 청소할 공간이 있으면 그 방향으로 회전하고 한 칸 전진
                d = (d + 3 - i) % 4; //
                x = nx;
                y = ny;
                moved = true;
                break;
            }

            // 3. 네 방향 모두 청소가 되어있거나 벽인 경우
            if (moved)
                continue; // 이동했다면 다시 1번부터 진행

            // 후진 위치 계산 (현재 방향에서 180도 회전)
            int nx = x + dx[(d + 2) % 4];
            int ny = y + dy[(d + 2) % 4];

            // 뒤쪽이 벽이라 후진할 수 없으면 작동 중지
            if (matrix[ny][nx] == 1)
                break;

            x = nx;
            y = ny;
        }

        bw.write(ans + "\n");
        bwEnd();
    }
}
