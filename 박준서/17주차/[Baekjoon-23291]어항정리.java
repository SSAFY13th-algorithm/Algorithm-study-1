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

    private static int nextint() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static final int[] dx = { 0, 1, 0, -1 };
    private static final int[] dy = { -1, 0, 1, 0 };

    private static int N, K, ans; // N: 어항 수, K: 목표 차이, ans: 연산 횟수
    private static ArrayList<Integer> fishTanks; // 어항의 물고기 수를 저장하는 리스트

    public static void main(String args[]) throws Exception {
        init();
        final int[][] rollMatrix = roll();
        final int[][] horizenalMatrix = flipHorizenal();

        do {
            // 물고기 수가 가장 적은 어항에 물고기 한 마리 추가
            addFish(fishTanks);
            // 말아 올린 상태에서 물고기 수 조절
            spread(rollMatrix);
            // 수평으로 접은 상태에서 물고기 수 조절
            spread(horizenalMatrix);
            ans++;
        } while (!check(fishTanks)); // 물고기 수 차이가 K 이하가 될 때까지 반복

        bw.write(ans + "\n");
        bwEnd();
    }

    // 입력을 받아 초기화하는 메소드
    public static void init() throws Exception {
        N = nextint(); // 어항의 수
        K = nextint(); // 목표 물고기 수 차이

        fishTanks = new ArrayList<>(); // 어항 리스트 초기화
        for (int i = 0; i < N; i++)
            fishTanks.add(nextint()); // 각 어항의 물고기 수 입력
    }

    // 어항을 말아 올리는 배치 행렬을 생성하는 메소드
    public static int[][] roll() {
        int cnt = 4; // 이미 처리된 어항 수
        int next = 2, c = 1; // next: 다음에 필요한 어항 수, c: 회전 카운터
        int[][] ret = { { 1, 0 }, { 2, 3 } }; // 초기 배치 (2x2 행렬)

        // 남은 어항이 충분할 때까지 말아 올리기 반복
        while (N - cnt >= next) {
            int H = ret.length;
            int W = ret[0].length + 1;
            int[][] tmp = new int[W][H]; // 새 배치 행렬

            // 기존 행렬을 90도 회전
            for (int i = 0; i < H; i++)
                for (int j = 0; j < W - 1; j++)
                    tmp[j][H - i - 1] = ret[i][j];

            // 새로운 어항 추가
            for (int i = 0; i < H; i++)
                tmp[W - 1][i] = cnt++;

            if (++c == 2) { // 두 번 회전할 때마다
                next++; // 필요한 어항 수 증가
                c = 0;
            }
            ret = tmp;

            if (N - cnt < next) {
                break;
            }

            ret = tmp;
        }

        // 남은 어항 처리
        int rest = N - cnt;
        int[][] tmp = new int[ret.length][];
        for (int i = 0; i < ret.length - 1; i++) {
            int[] clon = new int[ret[0].length];
            for (int j = 0; j < ret[0].length; j++)
                clon[j] = ret[i][j];
            tmp[i] = clon;
        }
        // 마지막 행에 남은 어항 추가
        tmp[ret.length - 1] = new int[ret[0].length + rest];
        for (int i = 0; i < ret[0].length; i++)
            tmp[ret.length - 1][i] = ret[ret.length - 1][i];
        for (int i = 0; i < rest; i++) {
            tmp[ret.length - 1][ret[0].length + i] = cnt++;
        }

        ret = tmp;
        return ret;
    }

    // 수평으로 접는 배치 행렬을 생성하는 메소드
    public static int[][] flipHorizenal() {
        int[][] ret = new int[4][N / 4]; // 4행으로 구성된 배치 행렬
        int cnt = 0;

        // 어항을 4단계로 접어서 배치
        for (int i = N / 4 - 1; i >= 0; i--)
            ret[2][i] = cnt++;
        for (int i = 0; i < N / 4; i++)
            ret[1][i] = cnt++;
        for (int i = N / 4 - 1; i >= 0; i--)
            ret[0][i] = cnt++;
        for (int i = 0; i < N / 4; i++)
            ret[3][i] = cnt++;

        return ret;
    }

    // 물고기 수가 가장 적은 어항에 물고기 한 마리 추가하는 메소드
    public static void addFish(ArrayList<Integer> arr) {
        int min = Integer.MAX_VALUE;
        for (int it : arr)
            min = Math.min(it, min); // 최소값 찾기

        // 최소값인 어항에만 물고기 추가
        for (int i = 0; i < arr.size(); i++)
            arr.set(i, arr.get(i) + (arr.get(i) == min ? 1 : 0));
    }

    // 물고기 수 조절 메소드
    public static void spread(int[][] in) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int it : fishTanks)
            ret.add(it); // 현재 어항 상태 복사

        // 인접한 어항 간 물고기 수 조절
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length; j++) {
                int nowIdx = in[i][j]; // 현재 어항 인덱스
                int now = fishTanks.get(nowIdx); // 현재 어항의 물고기 수

                // 상, 우 방향만 검사 (중복 방지)
                for (int k = 0; k < 2; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (OOB(in, ny, nx)) // 범위 밖이면 건너뛰기
                        continue;

                    int nextIdx = in[ny][nx]; // 인접 어항 인덱스
                    int next = fishTanks.get(nextIdx); // 인접 어항의 물고기 수
                    int num = Math.abs(next - now) / 5; // 이동할 물고기 수 계산

                    // 물고기 이동
                    if (num != 0) {
                        if (now > next) { // 현재 어항의 물고기가 더 많으면
                            ret.set(nextIdx, ret.get(nextIdx) + num); // 인접 어항에 추가
                            ret.set(nowIdx, ret.get(nowIdx) - num); // 현재 어항에서 감소
                        } else { // 인접 어항의 물고기가 더 많으면
                            ret.set(nextIdx, ret.get(nextIdx) - num); // 인접 어항에서 감소
                            ret.set(nowIdx, ret.get(nowIdx) + num); // 현재 어항에 추가
                        }
                    }
                }
            }
        }

        reshape(in, ret); // 어항 재배치
        fishTanks = ret; // 결과 저장
    }

    // 어항을 일렬로 재배치하는 메소드
    public static void reshape(int[][] in, ArrayList<Integer> arr) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++)
            tmp.add(arr.get(i)); // 현재 상태 복사
        arr.clear(); // 리스트 비우기

        // 배치 행렬에 따라 어항을 일렬로 재배치
        for (int j = 0; j < in[in.length - 1].length; j++)
            for (int i = in.length - 1; i >= 0; i--)
                if (!OOB(in, i, j)) // 유효한 위치면
                    arr.add(tmp.get(in[i][j])); // 해당 어항 추가
    }

    // 배열 범위를 벗어나는지 확인하는 메소드
    public static boolean OOB(int[][] in, int y, int x) {
        if (y < 0 || x < 0 || y >= in.length)
            return true; // 기본 범위 체크

        // 불규칙한 배열 형태 처리
        if ((y < in.length - 1 && x >= in[0].length) ||
                (y == in.length - 1 && x >= in[in.length - 1].length))
            return true;

        return false;
    }

    // 물고기 수 차이가 K 이하인지 확인하는 메소드
    public static boolean check(ArrayList<Integer> FT) {
        int min = 10000, max = 0;
        for (int it : FT) {
            min = Math.min(min, it); // 최소값 갱신
            max = Math.max(max, it); // 최대값 갱신
        }
        return max - min <= K; // 차이가 K 이하면 true 반환
    }
}
