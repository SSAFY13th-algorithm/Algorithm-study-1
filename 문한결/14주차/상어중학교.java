import java.io.*;
import java.util.*;

// findGroup->모든 좌표 돌면서 bfs-> blockN, startX,startY
// removeBlock-> startX,startY-> bfs돌리기
// moveDown 아래의 행부터 아래로 쭉쭉 내려가기 (>=0)
// rotate
public class 상어중학교 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static int[][] maps;
    static boolean[][] visited;
    static int[] input;
    static int startX, startY, blockN;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int answer = 0;
    static int rainbow = 0;

    public static void main(String[] args) throws Exception {
        init();
        execute();
        System.out.println(answer);

    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        maps = new int[n][n];
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                maps[i][j] = input[j];
            }

        }

    }

    static void execute() throws Exception {
        while (true) {
            rainbow = 0;

            blockN = 1;
            findGroup();
            if (blockN == 1) {
                break;
            }
            removeBlock();
            moveDown();
            rotate();
            moveDown();
        }
    }

    static void findGroup() {
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && maps[i][j] > 0) {
                    bfs(j, i);
                }

            }
        }

    }

    static void bfs(int x, int y) {
        int num = 1;
        int rainN = 0;
        Queue<int[]> que = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        que.add(new int[]{x, y, 1});
        visited[y][x] = true;
        int value = maps[y][x];
        while (!que.isEmpty()) {
            int xy[] = que.remove();
            int z = xy[2];
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i], ny = xy[1] + dy[i];

                if (OOB(nx, ny) || visited[ny][nx] || maps[ny][nx] == -10 || (maps[ny][nx] != 0 && maps[ny][nx] != value)) {

                    continue;
                }
                if (maps[ny][nx] == 0) {
                    rainN++;
                    list.add(new int[]{nx, ny});
                }

                que.add(new int[]{nx, ny, z + 1});
                num++;
                visited[ny][nx] = true;
            }

        }
        for (int[] xy : list) {
            visited[xy[1]][xy[0]] = false;
        }

        if (blockN < num) {
            startX = x;
            startY = y;
            blockN = num;
            rainbow = rainN;
        } else if (blockN == num) {
            if (rainN > rainbow) {
                startX = x;
                startY = y;
                blockN = num;
                rainbow = rainN;
            } else if (rainN == rainbow && y > startY) {
                startY = y;
                startX = x;
            } else if (rainN == rainbow && y == startY && x > startX) {
                startY = y;
                startX = x;
            }
        }

    }

    static void removeBlock() {
        visited = new boolean[n][n];
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{startX, startY});
        visited[startY][startX] = true;

        int value = maps[startY][startX];
        maps[startY][startX] = -10;
        while (!que.isEmpty()) {
            int xy[] = que.remove();
            for (int i = 0; i < 4; i++) {
                int nx = xy[0] + dx[i], ny = xy[1] + dy[i];
                if (OOB(nx, ny) || visited[ny][nx] || maps[ny][nx] == -10 || (maps[ny][nx] != 0 && maps[ny][nx] != value)) {
                    continue;
                }
                que.add(new int[]{nx, ny});
                maps[ny][nx] = -10;
                visited[ny][nx] = true;
            }

        }
        answer += blockN * blockN;
    }

    static void moveDown() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {
                if (maps[i][j] == -10 || maps[i][j] == -1) {
                    continue;
                }

                int cur = i;
                while (cur + 1 < n && maps[cur + 1][j] == -10) {
                    maps[cur + 1][j] = maps[cur][j];
                    maps[cur][j] = -10;
                    cur++;
                }
            }
        }
    }

    static void rotate() {
        int temp[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[n - 1 - j][i] = maps[i][j];
            }
        }
        maps = temp;

    }

    static boolean OOB(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

}
