package week9;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q1WizardSharkAndWishingForRain {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int D;
    private static int S;

    private static int[][] water;
    private static int[][] tmpWater;
    private static boolean[][] cloud;
    private static boolean[][] preCloud;

    private static List<Point> bug;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        water = new int[N][N];
        tmpWater = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cloud = new boolean[N][N];
        preCloud = new boolean[N][N];
        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                cloud[i][j] = true;
                preCloud[i][j] = true;
            }
        }

        bug = new ArrayList<>();
    }

    private static void sol() throws IOException {
        for (int t = 0; t < M; t++) {
            st = new StringTokenizer(br.readLine());

            D = Integer.parseInt(st.nextToken()) - 1;
            S = Integer.parseInt(st.nextToken());

            moveCloud();
            copyWater();
            makeCloud();
        }
        System.out.println(getAns());
    }

    private static void moveCloud() {
        clear();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!preCloud[i][j]) {
                    continue;
                }

                // N max 50, S max 50
                int nx = (i + (dx[D] * S) + (N * S)) % N;
                int ny = (j + (dy[D] * S) + (N * S)) % N;

                cloud[nx][ny] = true;
                water[nx][ny]++;

                bug.add(new Point(nx, ny));
            }
        }
        copyArr(water, tmpWater);
    }

    private static void copyWater() {

        for (Point p : bug) {
            for (int d = 1; d <= 8; d += 2) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if (OOB(nx, ny)) {
                    continue;
                }

                if (water[nx][ny] > 0) {
                    tmpWater[p.x][p.y]++;
                }
            }
        }
        copyArr(tmpWater, water);
    }

    private static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (water[i][j] >= 2 && !cloud[i][j]) {
                    preCloud[i][j] = true;
                    water[i][j] -= 2;
                } else {
                    preCloud[i][j] = false;
                }
            }
        }
    }

    private static int getAns() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += water[i][j];
            }
        }
        return cnt;
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

    private static void copyArr(int[][] from, int[][] to) {
        for (int i = 0; i < N; i++) {
            to[i] = Arrays.copyOf(from[i], N);
        }
    }

    private static void clear() {
        bug.clear();

        for (int i = 0; i < N; i++) {
            Arrays.fill(cloud[i], false);
        }
    }

}
