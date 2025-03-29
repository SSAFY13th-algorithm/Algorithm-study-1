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

    // N: 행렬 크기, M: 블리자드 마법 횟수, ans: 최종 점수
    private static int N, M, ans;
    private static int[] dx = { 0, 0, -1, 1 };
    private static int[] dy = { -1, 1, 0, 0 };

    private static int[][] sequence; // 구슬의 순서를 저장하는 2차원 배열
    private static int[][] matrix; // 입력받은 구슬 정보를 저장하는 2차원 배열
    private static ArrayList<Integer> marbles; // 구슬의 현재 상태를 저장하는 리스트

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();

        matrix = new int[N][N];
        sequence = new int[N][N];
        marbles = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            readLine();
            for (int j = 0; j < N; j++) {
                matrix[i][j] = nextInt();
            }
        }

        // 구슬의 순서(나선형)와 초기 상태 설정
        int x = N / 2; // 시작 x좌표
        int y = N / 2; // 시작 y좌표
        int d = 2; // 시작 방향 (좌)
        int step = 1, cnt = 0, turn = 0; // step: 이동 횟수, cnt: 현재 이동 수, turn: 방향 전환 횟수
        for (int i = 0; i < N * N - 1; i++) {
            x += dx[d];
            y += dy[d];
            sequence[y][x] = i; // 각 위치의 순서 저장
            marbles.add(matrix[y][x]); // 초기 구슬 상태 저장
            if (++cnt == step) { // 현재 방향으로 step만큼 이동했으면
                cnt = 0;
                d = directionForSeq(d); // 방향 전환
                if (++turn == 2) { // 두 번 방향을 바꾸면
                    turn = 0;
                    step++; // 이동 횟수 증가
                }
            }
        }

        // M번의 블리자드 마법 수행
        while (M-- != 0) {
            readLine();
            blizzard(nextInt() - 1, nextInt()); // 블리자드 마법 수행

            repeatExplode(); // 연속 폭발 처리
            explodeMarbles(); // 구슬 폭발 처리
            makeMarbles(); // 구슬 변화 처리
        }

        bw.write(ans + "\n");

        printAnswer();
    }

    // 블리자드 마법으로 구슬 제거
    public static void blizzard(int d, int s) {
        int y = N / 2; // 시작 y좌표 (중앙)
        int x = N / 2; // 시작 x좌표 (중앙)
        for (int i = 0; i < s; i++) {
            y += dy[d];
            x += dx[d];
            marbles.set(sequence[y][x], 0); // 해당 위치의 구슬을 0으로 설정(제거)
        }
    }

    // 연속으로 폭발이 일어나지 않을 때까지 반복
    public static void repeatExplode() {
        boolean flag = true;

        while (flag)
            flag = explodeMarbles(); // 폭발이 있으면 계속 반복
    }

    // 구슬 폭발 처리 (연속된 4개 이상의 같은 구슬 폭발)
    public static boolean explodeMarbles() {
        boolean flag = false; // 폭발 여부
        ArrayList<Integer> newAL = new ArrayList<>(); // 폭발 후 새로운 구슬 상태
        int num = marbles.get(0), cnt = 1; // 현재 구슬 번호와 연속 개수

        for (int i = 1; i < marbles.size(); i++) {
            if (marbles.get(i) == 0) // 빈 공간은 건너뜀
                continue;
            if (marbles.get(i) == num) { // 같은 구슬이면 카운트 증가
                cnt++;
            } else {
                if (cnt < 4) { // 4개 미만이면 폭발하지 않음
                    if (num != 0) // 0이 아닌 구슬만 추가
                        for (int j = 0; j < cnt; j++)
                            newAL.add(num);
                } else { // 4개 이상이면 폭발
                    ans += num * cnt; // 점수 추가
                    flag = true; // 폭발 발생
                }
                num = marbles.get(i); // 새로운 구슬 번호
                cnt = 1; // 카운트 초기화
            }
            if (newAL.size() == N * N - 2) // 배열 크기 제한
                break;
        }

        // 마지막 구슬 처리
        if (cnt < 4) {
            if (num != 0)
                for (int j = 0; j < cnt; j++)
                    newAL.add(num);
        } else {
            ans += num * cnt;
            flag = true;
        }

        // 배열 크기 조정
        while (newAL.size() > N * N - 1)
            newAL.remove(newAL.size() - 1);
        for (int i = newAL.size(); i <= N * N; i++)
            newAL.add(0);

        marbles = newAL; // 새로운 구슬 상태로 업데이트
        return flag; // 폭발 여부 반환
    }

    // 구슬 변화 처리 (연속된 구슬을 개수, 번호 쌍으로 변경)
    public static void makeMarbles() {
        ArrayList<Integer> newAL = new ArrayList<>();
        int num = marbles.get(0), cnt = 1;

        for (int i = 1; i < marbles.size(); i++) {
            if (marbles.get(i) == 0) // 빈 공간은 건너뜀
                continue;
            if (marbles.get(i) == num) { // 같은 구슬이면 카운트 증가
                cnt++;
            } else {
                newAL.add(cnt); // 구슬 개수 추가
                newAL.add(num); // 구슬 번호 추가
                num = marbles.get(i);
                cnt = 1;
            }
            if (newAL.size() == N * N - 2) // 배열 크기 제한
                break;
        }

        // 마지막 구슬 처리
        if (num != 0) {
            newAL.add(cnt);
            newAL.add(num);
        }

        // 배열 크기 조정
        while (newAL.size() > N * N - 1)
            newAL.remove(newAL.size() - 1);
        for (int i = newAL.size(); i < N * N + 1; i++)
            newAL.add(0);

        marbles = newAL; // 새로운 구슬 상태로 업데이트
    }

    // 나선형 이동을 위한 방향 전환 메소드
    public static int directionForSeq(int d) {
        if (d == 0) // 좌 -> 상
            return 2;
        else if (d == 1) // 우 -> 하
            return 3;
        else if (d == 2) // 상 -> 우
            return 1;
        else // 하 -> 좌
            return 0;
    }

    // 디버깅을 위한 현재 구슬 상태 출력 메소드
    public static void printMatrix(int[][] M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%d ", marbles.get(sequence[i][j]));
            System.out.println();
        }
        System.out.println();
    }
}
