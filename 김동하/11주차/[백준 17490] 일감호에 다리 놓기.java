import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n, m;
    public static long k;
    public static PriorityQueue<Node> edges;
    public static long ans = 0L;

    public static class Node implements Comparable<Node> {
        int x, y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static int[] parents;

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Long.parseLong(stk.nextToken());
        edges = new PriorityQueue<>();
        stk = new StringTokenizer(br.readLine());
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            edges.add(new Node(0, i, Integer.parseInt(stk.nextToken())));
            parents[i] = i;
        }
        boolean connectedRight[] = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            connectedRight[i] = true;
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int a = Math.min(x, y);
            int b = Math.max(x, y);
            if(b == n && a == 1) connectedRight[b] = false;
            else connectedRight[a] = false;
        }
        for (int i = 1; i <= n; i++) {
            if (!connectedRight[i]) continue;
            if (i == n) union(1, n);
            else union(i, i + 1);
        }
    }

    public static int find(int x) {
        if (parents[x] == x) return parents[x];
        else return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) parents[y] = x;
        else parents[x] = y;
        return true;
    }


    public static boolean isAllUnion() {
        int x = find(1);
        for (int i = 2; i <= n; i++) {
            if (x != find(i)) return false;
        }
        return true;
    }

    public static void solution() throws IOException {
        init();
        if (m <= 1) sb.append("YES");
        else {
            while (!edges.isEmpty()) {
                Node cur = edges.poll();
                if (ans > k) break;
                boolean flag = union(cur.x, cur.y);
                if (flag) {
                    ans += cur.dist;
                }
            }
            if (isAllUnion() && ans <= k) sb.append("YES");
            else sb.append("NO");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
