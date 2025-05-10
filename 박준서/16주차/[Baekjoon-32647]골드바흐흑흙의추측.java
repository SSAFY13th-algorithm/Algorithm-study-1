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

    private static long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static long N, M, K; // N, M: 소수 범위, K: 목표 합
    private static ArrayList<Long> arr; // N부터 M까지의 소수를 저장할 리스트
    private static TreeMap<Long, Long> map; // 동적 프로그래밍을 위한 맵 (key: 합, value: 경우의 수)

    public static void main(String args[]) throws Exception {
        init();

        // N부터 M까지의 소수를 찾아 리스트에 추가
        for (long i = Math.max(2, N); i <= M; i++)
            if (isPrime(i))
                arr.add(i);

        // 초기값 설정: 합이 0인 경우는 1가지 방법 (아무것도 선택하지 않는 경우)
        map.put(0L, 1L);

        // 각 소수에 대해 동적 프로그래밍 수행
        for (long prime : arr) {
            // 현재 맵에 있는 모든 키(합)를 가져옴
            List<Long> keys = new ArrayList<>(map.keySet());

            // 각 합에 현재 소수를 더한 새로운 합을 계산하고 경우의 수 업데이트
            for (long key : keys) {
                long sum = key + prime;
                // 새로운 합이 K 이하인 경우만 처리
                if (sum <= K) {
                    // 새로운 합의 경우의 수 = 기존 합의 경우의 수 + 현재 합의 경우의 수
                    map.put(sum, map.getOrDefault(sum, 0L) + map.get(key));
                }
            }
        }

        // K를 만드는 경우의 수 출력 (없으면 0)
        bw.write(map.getOrDefault(K, 0L) + "\n");
        bwEnd();
    }

    // 초기화
    public static void init() throws Exception {
        N = nextLong(); // 범위 시작 값
        M = nextLong(); // 범위 끝 값
        K = nextLong(); // 목표 합

        arr = new ArrayList<>(); // 소수 리스트 초기화
        map = new TreeMap<>(Collections.reverseOrder()); // 내림차순 정렬된 맵 초기화
    }

    // 소수 판별
    public static boolean isPrime(long a) {
        if (a < 2)
            return false;
        // 2부터 제곱근까지만 확인하면 소수 여부 판별 가능
        for (long i = 2; i * i <= a; i++)
            if (a % i == 0)
                return false;
        return true;
    }
}
