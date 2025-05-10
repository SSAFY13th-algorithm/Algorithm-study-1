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

    private static int N, K, ans;
    private static ArrayList<Integer> arr, step;

    public static void main(String args[]) throws Exception {
        init(); // 입력 및 변수 초기화

        arr.sort(Comparator.naturalOrder());

        int now = 0;
        // 각 강의실과 이전 강의실(혹은 0) 사이의 거리 계산
        for (int i = 0; i < N; i++) {
            step.add(arr.get(i) - now);
            now = arr.get(i);
        }

        step.sort(Comparator.naturalOrder()); // 거리 차이 오름차순 정렬

        // 가장 큰 (K-1)개의 거리를 제외한 나머지 거리의 합을 구함
        for (int i = 0; i < N - K; i++)
            ans += step.get(i);

        bw.write(ans + "\n");
        bwEnd();
    }

    // 입력값을 읽어 변수 및 리스트 초기화
    public static void init() throws Exception {
        N = nextInt(); // 강의실 개수
        K = nextInt(); // 그룹 개수

        arr = new ArrayList<>(); // 강의실 위치 저장 리스트
        step = new ArrayList<>(); // 인접 강의실 간 거리 저장 리스트
        for (int i = 0; i < N; i++)
            arr.add(nextInt()); // 강의실 위치 입력
    }
}
