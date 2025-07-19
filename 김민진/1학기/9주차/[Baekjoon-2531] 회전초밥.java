package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5ConveyorBeltSushi {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int D;
    private static int K;
    private static int C;
    private static int max;

    private static int[] cnt;
    private static int[] sushi;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 0;

        cnt = new int[D + 1];
        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void sol() {
        int kinds = 0;

        // first K sushi
        for (int i = 0; i < K; i++) {
            if (cnt[sushi[i]] == 0) {
                kinds++;
            }
            cnt[sushi[i]]++;
        }
        // if coupon is not used, eat one more
        max = (cnt[C] == 0 ? kinds + 1 : kinds);

        // sliding window
        for (int i = 0; i < N; i++) {
            // rm left
            cnt[sushi[i]]--;
            if (cnt[sushi[i]] == 0) {
                kinds--;
            }

            // add right
            int right = (i + K) % N;
            if (cnt[sushi[right]] == 0) {
                kinds++;
            }
            cnt[sushi[right]]++;

            max = Math.max(max, (cnt[C] == 0 ? kinds + 1 : kinds));
        }
        System.out.println(max);
    }
}
