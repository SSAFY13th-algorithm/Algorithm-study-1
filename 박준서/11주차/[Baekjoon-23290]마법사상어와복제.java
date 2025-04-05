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
        if (!st.hasMoreTokens())
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

    // 8방향 이동을 위한 방향 배열 (상, 상좌, 좌, 좌하, 하, 하우, 우, 상우)
    private static final int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    private static final int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

    // 행렬 상태와 물고기 수를 저장하는 배열
    private static int[][] matrix; // 냄새 정보 저장 (값이 있으면 냄새가 남아있는 상태)
    private static int[][] fishCnt; // 각 칸의 물고기 수 저장
    private static int M, S; // M: 물고기 수, S: 시뮬레이션 횟수

    // 물고기와 복제된 물고기, 상어 정보를 저장하는 리스트
    private static ArrayList<Fish> fishes; // 현재 물고기들
    private static ArrayList<Fish> duplicate; // 복제할 물고기들
    private static Fish shark; // 상어 위치

    // 물고기 클래스 정의
    static class Fish {
        int y, x, d; // y, x: 위치, d: 방향

        // 깊은 복사를 위한 생성자
        public Fish(Fish deep) {
            this.y = deep.y;
            this.x = deep.x;
            this.d = deep.d;
        }

        public Fish(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Fish(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        M = nextInt();
        S = nextInt();

        matrix = new int[4][4];
        fishes = new ArrayList<>();

        // M개의 물고기 정보 입력 (위치와 방향)
        while (M-- != 0)
            fishes.add(new Fish(nextInt() - 1, nextInt() - 1, nextInt() - 1));

        // 상어 위치 입력
        shark = new Fish(nextInt() - 1, nextInt() - 1);

        // S번의 시뮬레이션 수행
        for (int i = 1; i <= S; i++) {
            duplicationCasting(); // 1. 복제 마법 시전
            moveFishes(); // 2. 물고기 이동
            moveShark(); // 3. 상어 이동
            removeSmells(); // 4. 냄새 제거
            duplication(); // 5. 복제 마법 완료
        }

        // 결과 출력 (남은 물고기 수)
        bw.write(fishes.size() + "\n");
        bwEnd();
    }

    // 1. 복제 마법 시전 (현재 물고기들을 복제 리스트에 저장)
    public static void duplicationCasting() {
        duplicate = new ArrayList<>();
        for (Fish it : fishes)
            duplicate.add(new Fish(it)); // 깊은 복사로 물고기 복제
    }

    // 2. 물고기 이동
    public static void moveFishes() {
        ArrayList<Fish> nextFishes = new ArrayList<>(); // 이동 후 물고기 리스트
        fishCnt = new int[4][4]; // 각 칸의 물고기 수 초기화

        for (Fish it : fishes) {
            boolean flag = false; // 이동 가능 여부
            for (int i = 0; i < 8; i++) {
                // 현재 방향에서 반시계 방향으로 45도씩 회전하며 이동 가능한 방향 탐색
                int nd = (it.d - i + 8) % 8;
                int nx = it.x + dx[nd];
                int ny = it.y + dy[nd];

                // 행렬 밖이거나 피해야 할 칸(냄새가 있거나 상어가 있는 칸)이면 스킵
                if (OOB(ny, nx) || avoid(ny, nx))
                    continue;

                // 이동 가능한 방향 찾음
                flag = true;
                nextFishes.add(new Fish(ny, nx, nd)); // 새 위치와 방향으로 물고기 추가
                fishCnt[ny][nx]++; // 해당 칸의 물고기 수 증가
                break;
            }
            // 이동할 수 없으면 제자리에 남음
            if (!flag) {
                nextFishes.add(it);
                fishCnt[it.y][it.x]++;
            }
        }
        fishes = nextFishes; // 이동 후 물고기 리스트로 갱신
    }

    // 3. 상어 이동
    public static void moveShark() {
        // 상어의 모든 가능한 3칸 이동 경로 생성 (1,2,3,4는 상,좌,하,우를 의미)
        Queue<String> q = new ArrayDeque<>();
        q.add("");
        while (q.peek().length() != 3) {
            String tmp = q.poll();
            q.add(tmp + "1"); // 상
            q.add(tmp + "2"); // 좌
            q.add(tmp + "3"); // 하
            q.add(tmp + "4"); // 우
        }

        // 가장 많은 물고기를 잡을 수 있는 경로 찾기
        String maxRoute = "";
        int max = -1;
        while (!q.isEmpty()) {
            String tmp = q.poll();
            int ret = calc(tmp, 0); // 해당 경로로 잡을 수 있는 물고기 수 계산

            if (ret <= max)
                continue;

            max = ret;
            maxRoute = tmp;
        }

        // 선택된 경로로 상어 이동 및 물고기 제거
        for (int i = 0; i < 3; i++) {
            int nd = calcDirection(maxRoute.charAt(i) - '1'); // 방향 변환
            shark.y += dy[nd];
            shark.x += dx[nd];
            if (fishCnt[shark.y][shark.x] > 0) {
                matrix[shark.y][shark.x] = 3; // 냄새 표시 (3턴 동안 유지)
                fishCnt[shark.y][shark.x] = 0; // 물고기 제거
            }
        }
        eat(); // 물고기 제거 처리
    }

    // 방향 변환 메소드 (1,2,3,4 -> 실제 방향 배열 인덱스)
    public static int calcDirection(int i) {
        if (i == 0)
            return 2; // 상
        if (i == 1)
            return 0; // 좌
        if (i == 2)
            return 6; // 하
        if (i == 3)
            return 4; // 우
        return -1;
    }

    // 상어가 지나간 칸의 물고기 제거
    public static void eat() {
        ArrayList<Fish> nextFishs = new ArrayList<>();

        for (Fish it : fishes)
            if (matrix[it.y][it.x] < 3) // 냄새가 없는 칸의 물고기만 남김
                nextFishs.add(it);

        fishes = nextFishs; // 남은 물고기 리스트로 갱신
    }

    // 특정 경로로 상어가 이동할 때 잡을 수 있는 물고기 수 계산
    public static int calc(String route, int cnt) {
        int ret = 0;
        int ny = shark.y;
        int nx = shark.x;
        int[][] tmp = new int[4][]; // 물고기 수 배열 복사
        for (int i = 0; i < 4; i++)
            tmp[i] = fishCnt[i].clone();

        for (int i = 0; i < 3; i++) {
            int nd = calcDirection(route.charAt(i) - '1');
            ny += dy[nd];
            nx += dx[nd];

            if (OOB(ny, nx)) { // 행렬 밖으로 나가면 불가능한 경로
                ret = -1;
                break;
            }

            ret += tmp[ny][nx]; // 해당 칸의 물고기 수 추가
            tmp[ny][nx] = 0; // 물고기 제거 (중복 계산 방지)
        }
        return ret;
    }

    // 4. 냄새 제거 (모든 칸의 냄새 1 감소)
    public static void removeSmells() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                matrix[i][j] = Math.max(0, matrix[i][j] - 1);
    }

    // 5. 복제 마법 완료 (복제된 물고기 추가)
    public static void duplication() {
        for (Fish it : duplicate) {
            fishes.add(new Fish(it)); // 복제된 물고기 추가
            fishCnt[it.y][it.x]++; // 해당 칸의 물고기 수 증가
        }
    }

    public static void pr(int[][] t) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                System.out.printf("%d ", t[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    public static boolean OOB(int y, int x) {
        return (y < 0 || x < 0 || y >= 4 || x >= 4);
    }

    public static boolean avoid(int y, int x) {
        return (matrix[y][x] != 0 || (y == shark.y && x == shark.x));
    }
}
