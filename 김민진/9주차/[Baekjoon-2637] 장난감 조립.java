package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2AssemblingToys {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static StringBuilder   sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static int[] cnt;
    private static int[] degree;

    private static List<Node>[] graph;
    private static Queue<Node> q;

    private static class Node {

        int id;
        int cnt;

        Node(int id, int cnt) {
            this.id = id;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        sol();

        System.out.println(sb);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // the number of parts needed
        cnt = new int[N + 1];
        cnt[N] = 1;

        degree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()); // 완제품, 중간 부품
            int v = Integer.parseInt(st.nextToken()); // 중간 부품, 기본 부품
            int w = Integer.parseInt(st.nextToken());

            degree[v]++;
            graph[u].add(new Node(v, w));
        }
        q = new ArrayDeque<>();
    }

    private static void sol() {
        q.offer(new Node(N, 1));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            for (int i = 0; i < graph[cur.id].size(); i++) {
                Node nxt = graph[cur.id].get(i);
                
                degree[nxt.id]--;
                cnt[nxt.id] += nxt.cnt * cnt[cur.id];

                if (degree[nxt.id] == 0) {
                	q.offer(new Node(nxt.id, cnt[nxt.id]));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
        	if (!graph[i].isEmpty()) continue;

        	sb.append(i).append(' ').append(cnt[i]).append('\n');
        }
    }

}
