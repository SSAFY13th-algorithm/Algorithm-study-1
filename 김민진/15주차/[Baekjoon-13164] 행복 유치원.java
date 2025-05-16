package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3HappyKindergarten {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        sol();
    }

    private static void sol() throws IOException {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] kids = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kids[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            pq.offer(kids[i] - kids[i - 1]);
        }

        int cnt = 0;
        while (N-- - M > 0) {
            cnt += pq.poll();
        }
        System.out.println(cnt);
    }

}
