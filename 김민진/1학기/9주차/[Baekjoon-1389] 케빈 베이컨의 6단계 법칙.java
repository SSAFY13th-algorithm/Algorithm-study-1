package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q4SixDegreesOfSeparation {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], 1000);
            graph[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[a][b] = 1;
            graph[b][a] = 1;
        }
    }

    private static void sol() {
        floydWarshall();
        findMin();
    }

    private static void floydWarshall() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }

    private static void findMin() {
        int min = Integer.MAX_VALUE;
        int idx = -1;
        int sum;

        outer:
        for (int i = 0; i < N; i++) {
            sum = 0;
            for (int j = 0; j < N; j++) {
                sum += graph[i][j];

                if (sum >= min) {
                    continue outer;
                }
            }
            min = Math.min(min, sum);
            idx = i;
        }

        System.out.println(idx + 1);
    }

}
