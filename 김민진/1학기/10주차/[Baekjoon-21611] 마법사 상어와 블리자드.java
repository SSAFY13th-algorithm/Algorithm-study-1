package week10;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1WizardSharkAndBlizzard {

    private static final int[] dx = {0, -1, 1, 0, 0};
    private static final int[] dy = {0, 0, 0, -1, 1};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int D; // dir of magic
    private static int S; // dist of magic

    private static int[] cnt;       // 터진 구슬 개수 저장
    private static Point[] linearIdx; // 상어를 시작(0)으로 N*N-1까지 달팽이 형태로 맵 인덱스 저장
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cnt = new int[4];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        getIdx(N / 2, N / 2);
    }

    // 🐌
    private static void getIdx(int i, int j) {
        int[] dxx = {0, 1, 0, -1};
        int[] dyy = {-1, 0, 1, 0};
        // shark
        int pos = 0;
        int d = 0;
        int steps = 1;
        int turned = 0;

        linearIdx = new Point[N * N];

        linearIdx[pos++] = new Point(N / 2, N / 2);
        while (pos < N * N) {
            for (int s = 0; s < steps; s++) {
                i += dxx[d];
                j += dyy[d];

                linearIdx[pos++] = new Point(i, j);

                if (pos == N * N) {
                    break;
                }
            }
            d = (d + 1) % 4;
            if (++turned == 2) {
                turned = 0;
                steps++;
            }
        }
    }

    private static void sol() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            enchant();             // Destroy beads
            pullBeads();           // Fill empty spaces
            popConsecutiveBeads(); // Explode consecutive beads
            grouping();
        }
        printAns();
    }

    private static void enchant() {
        int x = N / 2;
        int y = N / 2;
        for (int i = 0; i < S; i++) {
            x += dx[D];
            y += dy[D];

            if (OOB(x, y)) {
                break;
            }
            map[x][y] = 0;
        }
    }

    private static void pullBeads() {
        int[][] tmp = new int[N][N];

        int idx = 1; // skip shark

        for (int i = 1; i < N * N; i++) {
            Point p = linearIdx[i];
            if (map[p.x][p.y] > 0) {
                Point to = linearIdx[idx++];
                tmp[to.x][to.y] = map[p.x][p.y];
            }
        }
        copyArr(tmp, map);
    }

    private static void makeZero(int start, int end) {
        for (int i = start; i < end; i++) {
            Point cur = linearIdx[i];
            map[cur.x][cur.y] = 0;
        }
    }

    private static void popConsecutiveBeads() {
        boolean popped = true;

        while (popped) {
            popped = false;

            for (int i = 1; i < N * N; i++) {
                int number = map[linearIdx[i].x][linearIdx[i].y];
                int start = i;
                int count = 1;

                while (i + 1 < N * N
                        && map[linearIdx[i + 1].x][linearIdx[i + 1].y] == number
                        && map[linearIdx[i + 1].x][linearIdx[i + 1].y] != 0) {
                    i++;
                    count++;
                }

                if (count >= 4) {
                    popped = true;

                    cnt[number] += count;
                    makeZero(start, i + 1);
                }
            }
            if (popped) {
                pullBeads();
            }
        }
    }

    private static void grouping() {
        int[][] tmp = new int[N][N];

        int idx = 1; // 상어 다음부터 시작
        int newIdx = 1; // 새 배열 시작 위치

        while (idx < N * N) {
            int number = map[linearIdx[idx].x][linearIdx[idx].y];

            // no bead
            if (number == 0) {
                break;
            }

            int count = 0;
            while (idx < N * N
                    && map[linearIdx[idx].x][linearIdx[idx].y] == number
                    && map[linearIdx[idx].x][linearIdx[idx].y] > 0) {
                idx++;
                count++;
            }

            if (newIdx < N * N) {
                Point countPos = linearIdx[newIdx++];
                tmp[countPos.x][countPos.y] = count;
            } else {
                break;
            }

            if (newIdx < N * N) {
                Point typePos = linearIdx[newIdx++];
                tmp[typePos.x][typePos.y] = number;
            } else {
                break;
            }
        }
        copyArr(tmp, map);
    }

    private static void printAns() {
        int ans = 0;

        for (int i = 1; i <= 3; i++) {
            ans += cnt[i] * i;
        }
        System.out.println(ans);
    }

    private static void copyArr(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }
}
