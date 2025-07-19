package week12;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1GoodbyeFineDust {

    private static final int PURIFIER = -1;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R;
    private static int C;
    private static int T;

    private static Point purifier;

    private static int[][] fineDusts;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        fineDusts = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                fineDusts[i][j] = Integer.parseInt(st.nextToken());

                // 위쪽 공기청정기 위치 저장
                if (purifier == null && fineDusts[i][j] == PURIFIER) {
                    purifier = new Point(i, j);
                }
            }
        }
    }

    private static void sol() {
        while (T-- > 0) {
            spreadDust();
            purifyAir();
        }
        System.out.println(getAns());
    }

    private static void spreadDust() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (fineDusts[i][j] <= 0) {
                    continue;
                }

                int cnt = 0;
                int spreadDust = fineDusts[i][j] / 5;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (OOB(nx, ny) || fineDusts[nx][ny] == PURIFIER) {
                        continue;
                    }
                    cnt++;
                    tmp[nx][ny] += spreadDust;
                }
                int left = fineDusts[i][j] - (cnt * spreadDust);
                tmp[i][j] += left > 0 ? left : 0;
            }
        }
        copyArr(tmp, fineDusts);
    }

    private static void purifyAir() {
        // upper
        // ↓
        for (int i = purifier.x - 1; i >= 0; i--) {
            fineDusts[i + 1][0] = fineDusts[i][0];
        }
        // ←
        for (int j = 1; j < C; j++) {
            fineDusts[0][j - 1] = fineDusts[0][j];
        }
        // ↑
        for (int i = 1; i <= purifier.x; i++) {
            fineDusts[i - 1][C - 1] = fineDusts[i][C - 1];
        }
        // →
        for (int j = C - 2; j > 0; j--) {
            fineDusts[purifier.x][j + 1] = fineDusts[purifier.x][j];
        }

        // lower
        // ↑
        for (int i = purifier.x + 1; i < R; i++) {
            fineDusts[i - 1][0] = fineDusts[i][0];
        }
        // ←
        for (int j = 1; j < C; j++) {
            fineDusts[R - 1][j - 1] = fineDusts[R - 1][j];
        }
        // ↓
        for (int i = R - 2; i >= purifier.x + 1; i--) {
            fineDusts[i + 1][C - 1] = fineDusts[i][C - 1];
        }
        // →
        for (int j = C - 2; j > 0; j--) {
            fineDusts[purifier.x + 1][j + 1] = fineDusts[purifier.x + 1][j];
        }

        fineDusts[purifier.x][purifier.y] = -1;
        fineDusts[purifier.x + 1][purifier.y] = -1;
        // 공청기에서 나온 바람
        fineDusts[purifier.x][1] = 0;
        fineDusts[purifier.x + 1][1] = 0;
    }

    private static void copyArr(int[][] from, int[][] to) {
        for (int i = 0; i < R; i++) {
            System.arraycopy(from[i], 0, to[i], 0, C);
        }
        fineDusts[purifier.x][purifier.y] = -1;
        fineDusts[purifier.x + 1][purifier.y] = -1;
    }

    private static int getAns() {
        int ans = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (fineDusts[i][j] >= 0) {
                    ans += fineDusts[i][j];
                }
            }
        }
        return ans;
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || R <= x || y < 0 || C <= y;
    }

}
