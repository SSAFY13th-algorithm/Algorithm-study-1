package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q4BuildingCity {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static long total;
    private static long ans;

    private static boolean visited[];

    private static Queue<Node> pq;
    private static List<Node>[] graph;

    private static class Node implements Comparable<Node> {

        int to;
        int dist;

        public Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        total = 0;
        ans = 0;

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, dist));
            graph[to].add(new Node(from, dist));

            total += dist;
        }

        visited = new boolean[N + 1];
        pq = new PriorityQueue<>();
    }

    private static void sol() {
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;
            ans += cur.dist;

            for (Node nxt : graph[cur.to]) {
                if (visited[nxt.to]) {
                    continue;
                }

                pq.offer(nxt);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        System.out.println(total - ans);
    }

}
