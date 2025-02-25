import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static final int[] dy = { -1, 1, 0, 0 };
    private static final int[] dx = { 0, 0, -1, 1 };
    // N: 행렬 크기, M: 상어 수, K: 냄새 지속시간
    private static int N, M, K;
    private static int ans = 0, deadCnt = 0;

    private static Field[][] matrix; // 행렬의 상태를 저장하는 2차원 배열
    private static ArrayList<Shark> sharks; // 상어들의 정보를 저장하는 리스트

    static class Field { // 행렬의 각 칸의 정보를 저장하는 클래스
        int smell; // 냄새를 남긴 상어의 번호
        int time; // 냄새가 남아있는 시간

        public Field(int smell, int time) {
            this.smell = smell;
            this.time = time;
        }
    }

    static class Shark { // 상어의 정보를 저장하는 클래스
        int x, y; // 상어의 위치
        int dire; // 현재 방향
        boolean isDead; // 죽었는지 여부
        ArrayList<ArrayList<Integer>> priority; // 각 방향별 우선순위

        public Shark(int x, int y, int dire) {
            this.x = x;
            this.y = y;
            this.dire = dire;
            this.isDead = false;
            this.priority = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                priority.add(new ArrayList<>());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        K++;

        matrix = new Field[N][N];
        sharks = new ArrayList<>();
        sharks.add(null); // 1번부터 시작하기 위해 0번 인덱스는 null로 채움

        // 상어 객체 초기화
        for (int i = 1; i <= M; i++)
            sharks.add(new Shark(0, 0, 0));

        // 행렬 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int sharkNum = Integer.parseInt(st.nextToken());
                matrix[i][j] = new Field(sharkNum, sharkNum == 0 ? 0 : K);
                if (sharkNum != 0) {
                    sharks.get(sharkNum).y = i;
                    sharks.get(sharkNum).x = j;
                }
            }
        }

        // 상어의 초기 방향 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks.get(i).dire = Integer.parseInt(st.nextToken()) - 1;
        }

        // 각 상어의 방향 우선순위 입력
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    sharks.get(i).priority.get(j).add(Integer.parseInt(st.nextToken()) - 1);
                }
            }
        }

        // 1번 상어만 남을 때까지 시뮬레이션
        while (deadCnt < M - 1) {
            cleanField(); // 냄새 감소
            moveShark(); // 상어 이동
            if (++ans > 1000)
                break; // 1000초 초과시 종료
        }

        sb.append(ans > 1000 ? -1 : ans).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 상어 이동 처리 메소드
    public static void moveShark() {
        int[][] temp = new int[N][N]; // 이동 결과를 임시 저장할 배열

        for (int i = 1; i <= M; i++) {
            Shark shark = sharks.get(i);
            if (shark.isDead)
                continue;

            boolean moved = false;
            int nx = -1, ny = -1, nd = -1;

            // 1순위: 아무 냄새가 없는 칸으로 이동
            for (Integer priority : shark.priority.get(shark.dire)) {
                nx = shark.x + dx[priority];
                ny = shark.y + dy[priority];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && matrix[ny][nx].smell == 0) {
                    nd = priority;
                    moved = true;
                    break;
                }
            }

            // 2순위: 자신의 냄새가 있는 칸으로 이동
            if (!moved) {
                for (Integer priority : shark.priority.get(shark.dire)) {
                    nx = shark.x + dx[priority];
                    ny = shark.y + dy[priority];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && matrix[ny][nx].smell == i) {
                        nd = priority;
                        break;
                    }
                }
            }

            // 이동한 위치에 다른 상어가 있으면 현재 상어는 죽음
            if (temp[ny][nx] == 0) {
                temp[ny][nx] = i;
                shark.x = nx;
                shark.y = ny;
                shark.dire = nd;
            } else {
                shark.isDead = true;
                deadCnt++;
            }
        }

        // 이동 결과를 실제 격자에 반영
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] != 0) {
                    matrix[i][j].smell = temp[i][j];
                    matrix[i][j].time = K;
                }
            }
        }
    }

    // 냄새 감소 처리 메소드
    public static void cleanField() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j].time > 0) {
                    if (--matrix[i][j].time == 0) {
                        matrix[i][j].smell = 0;
                    }
                }
            }
        }
    }
}
