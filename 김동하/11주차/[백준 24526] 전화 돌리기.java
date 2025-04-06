import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;

    public static boolean[] isCycle;
    public static List<Integer>[] list;
    public static boolean visited[];
    public static int[] degree;

    public static void init() throws IOException{
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        list = new ArrayList[n + 1];
        degree = new int[n + 1];
        isCycle = new boolean[n + 1];
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            degree[a]++;
            list[b].add(a);
        }
    }

    public static void topological() {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(degree[i] == 0) q.add(i);
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            visited[cur] = true;
            for(int next : list[cur]) {
                degree[next]--;
                if(degree[next] == 0) {
                    q.add(next);
                }
            }
        }
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) isCycle[i] = true;
        }
    }


    public static void solution() throws IOException{
        init();
        topological();

        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(!isCycle[i]) cnt++;
        }

        sb.append(cnt);

    }

    public static void main(String[] args) throws IOException{
        solution();

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
