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

    private static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    private static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

    // N: 격자 크기, M: 이동 횟수
    private static int N, M;

    private static int[][] matrix; // 각 칸의 물의 양을 저장하는 2차원 배열
    private static ArrayList<Cloud> clouds; // 구름의 위치를 저장하는 리스트

    static class Cloud {
        int x, y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {

        readLine();
        N = nextInt();
        M = nextInt();

        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < N; j++)
                matrix[i][j] = nextInt();
        }

        clouds = new ArrayList<>();
        clouds.add(new Cloud(0, N - 1));
        clouds.add(new Cloud(1, N - 1));
        clouds.add(new Cloud(0, N - 2));
        clouds.add(new Cloud(1, N - 2));

        while (M-- != 0) {
            readLine();
            int d = nextInt();
            int s = nextInt();

            moveClouds(d, s);
            rain();
            copyWater();
            createClouds();
        }

        bw.write(countMatrix() + "\n");
        printAnswer();
    }

    // 1. 모든 구름이 d 방향으로 s칸 이동하는 메소드
    private static void moveClouds(int d, int s) {
        for (Cloud cloud : clouds) {
            cloud.x = (cloud.x + (dx[d - 1] * s) % N + N) % N;
            cloud.y = (cloud.y + (dy[d - 1] * s) % N + N) % N;
        }
    }

    // 2. 각 구름에서 비가 내려 물의 양 1 증가시키는 메소드
    private static void rain() {
        for (Cloud cloud : clouds)
            matrix[cloud.y][cloud.x]++;
    }

    // 3. 물복사버그 마법 시전 메소드
    private static void copyWater() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                temp[i][j] = matrix[i][j];

        // 각 구름에 대해 대각선 방향에 물이 있는 바구니 수만큼 물의 양 증가
        for (Cloud cloud : clouds) {
            for (int i = 1; i < 8; i += 2) { // 대각선 방향만 확인 (1, 3, 5, 7번 방향)
                int nx = cloud.x + dx[i];
                int ny = cloud.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && temp[ny][nx] > 0) {
                    matrix[cloud.y][cloud.x]++;
                }
            }
        }
    }

    // 4. 새로운 구름 생성하는 메소드
    private static void createClouds() {
        ArrayList<Cloud> newClouds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] >= 2 && !isCloud(j, i)) {
                    matrix[i][j] -= 2;
                    newClouds.add(new Cloud(j, i));
                }
            }
        }
        clouds.clear();
        clouds.addAll(newClouds);
    }

    // 해당 위치에 구름이 있는지 확인하는 메소드
    private static boolean isCloud(int x, int y) {
        for (Cloud cloud : clouds) {
            if (cloud.x == x && cloud.y == y)
                return true;
        }
        return false;
    }

    // 디버깅용: 격자의 물의 양 출력하는 메소드
    public static void printMatrix() {
        for (int[] row : matrix) {
            for (int it : row)
                System.out.printf("%d ", it);
            System.out.println();
        }
        System.out.println();
    }

    // 격자의 물의 양 합계를 계산하는 메소드
    public static int countMatrix() {
        int ret = 0;
        for (int[] row : matrix)
            for (int it : row)
                ret += it;
        return ret;
    }
}
