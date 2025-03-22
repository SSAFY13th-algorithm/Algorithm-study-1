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
    public static int cnt[];

    public static class Node{
        int x;
        int cnt;
        public Node(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
    public static List<Node>[] list;
    public static int[] degree;
    public static boolean[] isBase;
    public static void init() throws IOException {
        StringTokenizer stk;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        degree = new int[n + 1];
        cnt = new int[n + 1];
        isBase = new boolean[n + 1];
        for(int i = 0; i <= n; i++) {
            isBase[i] = true;
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            list[x].add(new Node(y,z));
            degree[y]++;
            isBase[x] = false;
        }

    }

    public static void topology() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(n,1));
        cnt[n] = 1;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            for(int i = 0; i < list[x].size(); i++) {
                Node next = list[x].get(i);
                cnt[next.x] += (cnt[x] * next.cnt);
                degree[next.x]--;
                if(degree[next.x] == 0) q.add(new Node(next.x, cnt[next.x]));
            }
        }
    }

    public static void solution() throws IOException {
        init();
        topology();
        for(int i = 1; i <= n; i++) {
            if(!isBase[i]) continue;
            sb.append(i).append(" ").append(cnt[i]).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
