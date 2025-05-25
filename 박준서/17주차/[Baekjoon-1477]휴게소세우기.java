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

    private static int N; // 기존 휴게소 개수
    private static int M; // 추가할 휴게소 개수
    private static int L; // 고속도로 길이
    private static ArrayList<Integer> arr; // 휴게소 위치 저장 리스트
    private static ArrayList<Integer> terms; // 휴게소 간 거리 저장 리스트

    public static void main(String args[]) throws Exception {
        init();
        bw.write(find() + "\n"); // 결과 계산 및 출력
        bwEnd(); // 출력 스트림 닫기
    }

    public static void init() throws Exception {
        N = nextint(); // 기존 휴게소 개수
        M = nextint(); // 추가할 휴게소 개수
        L = nextint(); // 고속도로 길이
        terms = new ArrayList<>(); // 휴게소 간 거리를 저장할 리스트

        if (N == 0) {
            terms.add(L); // 시작점부터 끝점까지의 거리만 저장
            return;
        }

        arr = new ArrayList<>(); // 휴게소 위치를 저장할 리스트
        for (int i = 0; i < N; i++)
            arr.add(nextint()); // 각 휴게소 위치 입력
        arr.sort(Comparator.naturalOrder()); // 위치 오름차순 정렬

        // 휴게소 간 거리 계산
        terms.add(arr.get(0) - 0); // 시작점부터 첫 휴게소까지의 거리
        for (int i = 1; i < N; i++)
            terms.add(arr.get(i) - arr.get(i - 1)); // 인접한 휴게소 간 거리
        terms.add(L - arr.get(N - 1)); // 마지막 휴게소부터 끝점까지의 거리
    }

    // 최적의 휴게소 간 최대 거리를 찾는 메소드
    public static int find() {
        // 특수 케이스: 모든 위치에 휴게소가 있는 경우
        if (L - N - M == 1)
            return 1;

        // 가능한 모든 거리 값에 대해 탐색 (2부터 L까지)
        loop: for (int i = 2; i <= L; i++) {
            int cnt = 0; // 추가로 필요한 휴게소 개수

            // 각 구간마다 필요한 휴게소 개수 계산
            for (int it : terms) {
                // (구간 길이 - 1) / i: 해당 구간에 추가로 필요한 휴게소 수
                cnt += (it - 1) / i;

                // 필요한 휴게소 수가 M을 초과하면 현재 거리 i는 불가능
                if (cnt > M)
                    continue loop; // 다음 거리 값으로 넘어감
            }

            return i; // 조건을 만족하는 최소 거리 반환
        }
        return 0; // 해를 찾지 못한 경우 (실제로는 발생하지 않음)
    }
}
