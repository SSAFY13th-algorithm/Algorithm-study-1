import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n, m;
    public static List<Integer>[] edge;
    public static int[] dist;

    public static class Node implements Comparable<Node> {
        int idx, cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }

        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        edge = new ArrayList[n + 1];
        dist = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            edge[a].add(b);
            edge[b].add(a);
        }
    }

    public static int dijkstra(int st) {
        boolean visited[] = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(st, 0));
        dist[st] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.idx]) continue;
            visited[cur.idx] = true;
            if (cur.cnt > dist[cur.idx]) continue;
            for (int next : edge[cur.idx]) {
                if (dist[next] > dist[cur.idx] + 1) {
                    dist[next] = dist[cur.idx] + 1;
                    pq.add(new Node(next, dist[next]));
                }
            }
        }
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            ret += dist[i];
        }
        return ret;
    }

    public static void solution() throws IOException {
        init();
        int min = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int x = dijkstra(i);
            if(x < min) {
                ans = i;
                min = x;
            }
        }
        sb.append(ans);
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
