import java.util.*;
import java.io.*;

public class Main {
    static int TC, N, M, W;
    static List<Edge> graph;
    static final int INF = 1000000000;

    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            
            // 양방향
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.add(new Edge(u, v, cost));
                graph.add(new Edge(v, u, cost));
            }
            // 단방향, 음수 가중치
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                
                graph.add(new Edge(u, v, -cost));
            }
            
            if (bellmanFord()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean bellmanFord() {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 1; i <= N; i++) {
            for (Edge edge : graph) {
                if (dist[edge.end] > dist[edge.start] + edge.cost) {
                    dist[edge.end] = dist[edge.start] + edge.cost;
                    // N번째도 값이 변경되면 음수 사이클 존재
                    if (i == N) return true; 
                }
            }
        }
        return false;
    }
}
