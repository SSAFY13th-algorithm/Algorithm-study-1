import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;

    public static List<Integer>[] arr;
    public static List<Integer>[] rev;

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        arr = new ArrayList[n + 1];
        rev = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            int a,b;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            rev[b].add(a);
        }
    }

    public static int bfs(int x, List<Integer>[] list){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        boolean[] visited = new boolean[n + 1];
        visited[x] = true;
        int cnt = 0;
        while(!q.isEmpty()){
            int cur = q.poll();
            visited[cur] = true;
            for(int next : list[cur]) {
                if(!visited[next]){
                    q.add(next);
                    visited[next] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void solution() throws IOException {
        init();
        for(int i = 1; i <= n; i++){
            sb.append(n - 1 - bfs(i,arr) - bfs(i, rev)).append("\n");
        }

    }

    public static void main(String[] args)throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}