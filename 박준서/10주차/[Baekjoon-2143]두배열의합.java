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

    // T: 목표 합, N: 첫 번째 배열의 크기, M: 두 번째 배열의 크기
    private static int T, N, M;
    private static long ans; // 가능한 조합의 수를 저장할 변수
    private static int[] arr1, arr2; // 두 개의 입력 배열

    // 부분 배열의 합과 그 등장 횟수를 저장할 TreeMap
    private static TreeMap<Integer, Long> TM1 = new TreeMap<>(), TM2 = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        readLine();
        T = nextInt();

        readLine();
        N = nextInt();
        arr1 = new int[N];

        readLine();

        for (int i = 0; i < N; i++)
            arr1[i] = nextInt();

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = i; j < N; j++) {
                cnt += arr1[j]; // 요소를 하나씩 더해가며 부분 배열의 합 계산
                if (TM1.containsKey(cnt))
                    TM1.replace(cnt, TM1.get(cnt) + 1);
                else
                    TM1.put(cnt, 1L);
            }
        }

        readLine();
        M = nextInt();
        arr2 = new int[M];

        readLine();

        for (int i = 0; i < M; i++)
            arr2[i] = nextInt();

        for (int i = 0; i < M; i++) {
            int cnt = 0;
            for (int j = i; j < M; j++) {
                cnt += arr2[j]; // 요소를 하나씩 더해가며 부분 배열의 합 계산
                if (TM2.containsKey(cnt))
                    TM2.replace(cnt, TM2.get(cnt) + 1);
                else
                    TM2.put(cnt, 1L);
            }
        }

        // 두 부분 배열의 합이 T가 되는 모든 조합 찾기
        for (int it : TM1.keySet()) {
            if (TM2.containsKey(T - it))
                // 첫 번째 배열에서 합이 it인 부분 배열의 개수와
                // 두 번째 배열에서 합이 (T-it)인 부분 배열의 개수를 곱하여 조합의 수 계산
                ans += TM1.get(it) * TM2.get(T - it);
        }

        bw.write(ans + "\n");

        printAnswer();
    }
}
