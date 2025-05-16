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

    // 방향 벡터 (상,좌,하,우)
    private static final int[] dx = { 0, 1, 0, -1 };
    private static final int[] dy = { -1, 0, 1, 0 };
    // 입력 방향(1~4)을 내부 방향(상,좌,하,우)으로 변환
    private static final int[] conv = { 1, 3, 0, 2 };

    private static int R, C, K, W, choco;
    private static ArrayList<Heater> heaters; // 온풍기 위치 및 방향
    private static ArrayList<Integer> targets; // 온도 체크 대상 위치
    private static boolean[][][] walls; // 벽 정보 [행][열][방향]
    private static int[][] matrix; // 각 칸의 온도

    // 온풍기 정보 클래스
    private static class Heater {
        int idx, d; // idx: 위치(1차원 인덱스), d: 방향

        public Heater(int idx, int d) {
            this.idx = idx;
            this.d = d;
        }
    }

    public static void main(String args[]) throws Exception {
        init(); // 입력 및 초기화

        // 초콜릿을 100개 이하로 먹을 때까지, play() 반복
        while (choco <= 100 && play()) {
        }

        bw.write(choco + "\n"); // 결과 출력
        bwEnd();
    }

    // 입력 및 초기화 함수
    public static void init() throws Exception {
        R = nextInt();
        C = nextInt();
        K = nextInt();

        heaters = new ArrayList<>();
        targets = new ArrayList<>();
        walls = new boolean[R][C][4]; // 시계방향
        matrix = new int[R][C];

        // 온풍기, 목표 칸 입력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int tmp = nextInt();
                if (tmp == 5)
                    targets.add(i * C + j); // 목표 칸
                else if (tmp != 0) {
                    heaters.add(new Heater(i * C + j, conv[tmp - 1])); // 온풍기
                }
            }
        }

        // 벽 정보 입력
        W = nextInt();
        while (W-- != 0) {
            int y = nextInt() - 1;
            int x = nextInt() - 1;
            int oper = nextInt();
            if (oper == 1)
                walls[y][x][1] = walls[y][x + 1][3] = true; // 오른쪽 벽
            else
                walls[y][x][0] = walls[y - 1][x][2] = true; // 위쪽 벽
        }
    }

    // 1회 시뮬레이션 실행 (온풍기 바람, 대류, 외벽 감소, 목표 검사)
    public static boolean play() {
        operation(); // 온풍기 바람 퍼뜨리기
        convection(); // 인접 칸 온도 조절
        decrease(); // 외벽 온도 감소
        choco++; // 초콜릿 1개 먹기
        return !check(); // 목표 온도 달성 시 false 반환
    }

    // 온풍기 바람 퍼뜨리기
    public static void operation() {
        Queue<int[]> q = new ArrayDeque<>();
        for (Heater it : heaters) {
            int[][] tmpMat = new int[R][C]; // 임시 온도 변화 저장
            int y = it.idx / C + dy[it.d];
            int x = it.idx % C + dx[it.d];

            // 온풍기 앞 칸이 범위 내이고, 벽이 없으면 시작
            if (!OOB(y, x) && !walls[y][x][(it.d + 2) % 4])
                q.add(new int[] { y, x, it.d, 5 }); // y, x, 방향, 온도(초기 5)
            while (!q.isEmpty()) {
                int[] now = q.poll();
                tmpMat[now[0]][now[1]] = now[3];

                if (now[3] == 0)
                    continue;
                int nd = now[2];
                int ny = now[0] + dy[nd], nx = now[1] + dx[nd], nc = now[3];
                // 직진
                if (!OOB(ny, nx) && !walls[ny][nx][(nd + 2) % 4]) {
                    q.add(new int[] { ny, nx, nd, nc - 1 });
                }
                // 왼쪽 대각선
                nd = (now[2] + 3) % 4;
                ny = now[0] + dy[nd];
                nx = now[1] + dx[nd];
                if (!OOB(ny, nx) && !walls[ny][nx][(nd + 2) % 4]) {
                    ny += dy[now[2]];
                    nx += dx[now[2]];
                    if (!OOB(ny, nx) && !walls[ny][nx][(now[2] + 2) % 4]) {
                        q.add(new int[] { ny, nx, now[2], nc - 1 });
                    }
                }
                // 오른쪽 대각선
                nd = (now[2] + 1) % 4;
                ny = now[0] + dy[nd];
                nx = now[1] + dx[nd];
                if (!OOB(ny, nx) && !walls[ny][nx][(nd + 2) % 4]) {
                    ny += dy[now[2]];
                    nx += dx[now[2]];
                    if (!OOB(ny, nx) && !walls[ny][nx][(now[2] + 2) % 4]) {
                        q.add(new int[] { ny, nx, now[2], nc - 1 });
                    }
                }
            }

            // 임시 온도 변화 반영
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    matrix[i][j] += tmpMat[i][j];
        }
    }

    // 인접 칸 온도 조절(대류)
    public static void convection() {
        int[][] tmpMat = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    // 범위 밖이거나 벽이 있거나, 인접 칸이 더 뜨거우면 패스
                    if (OOB(ny, nx) || walls[i][j][k] || matrix[ny][nx] >= matrix[i][j])
                        continue;
                    int tmp = (matrix[i][j] - matrix[ny][nx]) / 4;
                    tmpMat[ny][nx] += tmp;
                    tmpMat[i][j] -= tmp;
                }
            }
        }

        // 임시 온도 변화 반영
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                matrix[i][j] += tmpMat[i][j];
    }

    // 외벽 온도 감소
    public static void decrease() {
        for (int i = 0; i < C; i++) {
            matrix[0][i] = Math.max(0, --matrix[0][i]); // 윗줄
            matrix[R - 1][i] = Math.max(0, --matrix[R - 1][i]); // 아랫줄
        }
        for (int i = 1; i < R - 1; i++) {
            matrix[i][0] = Math.max(0, --matrix[i][0]); // 왼쪽줄
            matrix[i][C - 1] = Math.max(0, --matrix[i][C - 1]); // 오른쪽줄
        }
    }

    // 모든 목표 칸이 K 이상인지 검사
    public static boolean check() {
        for (int it : targets)
            if (matrix[it / C][it % C] < K)
                return false;
        return true;
    }

    // 범위 밖 체크 함수
    public static boolean OOB(int y, int x) {
        return (y < 0 || x < 0 || y >= R || x >= C);
    }
}
