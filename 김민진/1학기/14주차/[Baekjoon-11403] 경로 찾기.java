package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4FindRoute {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    // floyd-warshall
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        graph = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()) != 0;
            }
        }
    }

    private static void sol() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] |= graph[i][k] && graph[k][j];
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(graph[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
