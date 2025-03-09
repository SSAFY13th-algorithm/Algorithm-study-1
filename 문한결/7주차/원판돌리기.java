import java.io.*;
import java.util.*;

public class 원판돌리기 {
    static int N, M, T;
    static int[][] roundMap;
    static List<int[]> games = new ArrayList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        roundMap = new int[N + 1][M]; // 1-based indexing

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                roundMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            games.add(new int[]{x, d, k});
        }

        executeTRotate();

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                answer += roundMap[i][j];
            }
        }

        System.out.println(answer);
    }

    static void turnMap(int dir, int pos) {
        if (dir == 0) { // 시계 방향 회전
            for (int i = 1; i <= N; i++) {
                if (i % pos == 0) {
                    int temp = roundMap[i][M - 1];
                    for (int j = M - 1; j > 0; j--) {
                        roundMap[i][j] = roundMap[i][j - 1];
                    }
                    roundMap[i][0] = temp;
                }
            }
        } else { // 반시계 방향 회전
            for (int i = 1; i <= N; i++) {
                if (i % pos == 0) {
                    int temp = roundMap[i][0];
                    for (int j = 0; j < M - 1; j++) {
                        roundMap[i][j] = roundMap[i][j + 1];
                    }
                    roundMap[i][M - 1] = temp;
                }
            }
        }
    }

    static int bfs(int startX, int startY, int[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        int target = roundMap[startY][startX];
        boolean found = false;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx == M) nx = 0;
                if (nx == -1) nx = M - 1;

                if (nx >= 0 && nx < M && ny > 0 && ny <= N && visited[y][x] == visited[ny][nx] && roundMap[ny][nx] == target) {
                    found = true;
                    roundMap[ny][nx] = 0;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        if (found) {
            roundMap[startY][startX] = 0;
            return 1;
        }
        return 0;
    }

    static int deleteNear() {
        int sum = 0;
        int[][] visited = new int[N + 1][M];

        for (int i = 1; i <= N; i++) {
            System.arraycopy(roundMap[i], 0, visited[i], 0, M);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (roundMap[i][j] != 0) {
                    sum += bfs(j, i, visited);
                }
            }
        }

        return sum;
    }

    static void gameFun() {
        if (deleteNear() == 0) {
            int sum = 0, nonZero = 0;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (roundMap[i][j] != 0) {
                        sum += roundMap[i][j];
                        nonZero++;
                    }
                }
            }

            if (nonZero == 0) return;

            double avg = (double) sum / nonZero;

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < M; j++) {
                    if (roundMap[i][j] != 0) {
                        if (roundMap[i][j] > avg) {
                            roundMap[i][j]--;
                        } else if (roundMap[i][j] < avg) {
                            roundMap[i][j]++;
                        }
                    }
                }
            }
        }
    }

    static void executeTRotate() {
        for (int[] game : games) {
            int x = game[0], d = game[1], k = game[2];
            for (int i = 0; i < k; i++) {
                turnMap(d, x);
            }
            gameFun();
        }
    }
}
