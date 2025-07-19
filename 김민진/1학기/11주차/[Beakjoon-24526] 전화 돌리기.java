package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q4TransferringCall {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static boolean[] visited;
    private static boolean[] cycle;
    private static boolean[] finished;

    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init graph
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        visited = new boolean[N + 1];
        cycle = new boolean[N + 1];
        finished = new boolean[N + 1];
    }

    private static void sol() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (!cycle[i]) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean dfs(int v) {
        if (visited[v] && !finished[v]) {
            return cycle[v] = true;
        }

        // alr checked
        if (finished[v] || cycle[v]) {
            return cycle[v];
        }

        visited[v] = true;

        for (int u : graph[v]) {
            cycle[v] |= dfs(u);
        }

        finished[v] = true;

        return cycle[v];
    }

}
