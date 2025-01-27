package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Main {

    public static int n, m, k, x;
    public static boolean[] visited = new boolean[300001];
    public static int dist[] = new int[300001];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static List<Integer> edge[] = new ArrayList[300001];

    public static void init() throws IOException {
        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = in[0];
        m = in[1];
        k = in[2];
        x = in[3];
        for (int i = 0; i < m; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = in[0];
            int b = in[1];
            if(edge[a] == null) edge[a] = new ArrayList<>();
            edge[a].add(b);
        }
    }

    public static void bfs() {
        visited[x] = true;
        dist[x] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        while (!q.isEmpty()) {
            int tx = q.poll();
            List<Integer> list = edge[tx];
            if(list == null) continue;
            for(int i = 0; i < list.size(); i++) {
                int nx = list.get(i);
                if(visited[nx]) continue;
                visited[nx] = true;
                dist[nx] = dist[tx] + 1;
                q.add(nx);
            }
        }
    }

    public static void solution() {
        bfs();
        boolean succ = false;
        for(int i = 1; i <= n; i++) {
            if (dist[i] == k) {
                succ = true;
                System.out.println(i);
            }
        }
        if (!succ) {
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }
}
