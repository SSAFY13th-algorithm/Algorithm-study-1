package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4RemaningSum {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static long[] mod;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mod = new long[M];

        long psum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            psum = (psum + Integer.parseInt(st.nextToken())) % M;
            mod[(int) psum] += 1;
        }
    }

    private static void sol() {
        // 나머지가 0인 구간
        long cnt = mod[0];

        for (int i = 0; i < M; i++) {
            cnt += (mod[i] * (mod[i] - 1)) / 2;
        }
        System.out.println(cnt);
    }

}
