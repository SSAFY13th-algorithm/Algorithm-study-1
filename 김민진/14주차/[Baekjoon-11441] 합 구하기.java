package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5GetRangeSum {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int T;
    private static int[] prefix;

    public static void main(String[] args) throws IOException {
        init();
        sol();

        System.out.println(sb);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i - 1] + cur;
        }

        T = Integer.parseInt(br.readLine());
    }

    private static void sol() throws IOException {
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(prefix[e] - prefix[s - 1]).append("\n");
        }
    }

}
