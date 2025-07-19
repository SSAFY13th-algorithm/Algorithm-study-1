package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2NumberCard {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    
    private static final int MAX_NUM = 10_000_000;
    private static final int OFFSET = MAX_NUM;

    private static int N;
    private static int M;

    private static boolean[] cards;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        cards = new boolean[2 * MAX_NUM + 1];
        for (int i = 0; i < N; i++) {
        	int idx = Integer.parseInt(st.nextToken());
        	
        	cards[idx + OFFSET] = true;
        }
    }

    private static void sol() throws IOException {
        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            sb.append(cards[target + OFFSET] ? "1 " : "0 ");
        }
        System.out.println(sb);
    }

}