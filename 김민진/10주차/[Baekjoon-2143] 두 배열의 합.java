package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Q3SumOfTwoArrays {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;
    private static int N;
    private static int M;
    private static long cnt;

    private static long[] A;
    private static long[] B;

    private static Map<Long, Integer> map;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        T = Integer.parseInt(br.readLine());
        cnt = 0;

        N = Integer.parseInt(br.readLine());

        A = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = A[i - 1] + Long.parseLong(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        B = new long[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            B[i] = B[i - 1] + Long.parseLong(st.nextToken());
        }

        map = new HashMap<>();
    }

    private static void sol() {
        // put the sums of subarrays to map
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                long tmpA = getPartialSum(i, j, A);

                map.put(tmpA, map.getOrDefault(tmpA, 0) + 1);
            }
        }

        // compare with the sum of subarrays of b
        for (int i = 1; i <= M; i++) {
            for (int j = i; j <= M; j++) {
                long tmpB = getPartialSum(i, j, B);

                cnt += map.getOrDefault(T - tmpB, 0);
            }
        }

        System.out.println(cnt);
    }

    private static long getPartialSum(int i, int j, long[] mat) {
        return mat[j] - mat[i - 1];
    }

}
