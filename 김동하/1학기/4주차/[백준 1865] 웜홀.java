import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int T;
    public static int n, m, w;
    public static List<List<Node>> edges;
    public static int[] dist;

    public static class Node {

        int idx, cnt;

        public Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static void init() throws IOException {
        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        edges = new ArrayList<>();
        n = in[0];
        m = in[1];
        w = in[2];
        dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.get(in[0]).add(new Node(in[1], in[2]));
            edges.get(in[1]).add(new Node(in[0], in[2]));
        }
        for (int i = 0; i < w; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.get(in[0]).add(new Node(in[1], -in[2]));
        }
    }

    public static boolean bellmanFord(int idx) {
        for (int i = 0; i <= n; i++) {
            dist[i] = 987654321;
        }
        dist[idx] = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 1; j <= n; j++) {
                for (Node e : edges.get(j)) {
                    if (dist[j] == 987654321) {
                        continue;
                    }
                    if(dist[e.idx] > dist[j] + e.cnt) {
                        dist[e.idx] = dist[j] + e.cnt;
                        flag = true;
                    }
                }
            }
            if(!flag) break;
        }
        for (int j = 1; j <= n; j++) {
            for (Node e : edges.get(j)) {
                if(dist[j] == 987654321) continue;
                if(dist[e.idx] > dist[j] + e.cnt) return true;
            }
        }
        return false;
    }

    public static void solution() throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            boolean flag = false;
            for (int i = 1; i <= n; i++) {
                if (bellmanFord(i)) {
                    flag = true;
                    System.out.println("YES");
                    break;
                }
            }
            if (!flag) {
                System.out.println("NO");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
