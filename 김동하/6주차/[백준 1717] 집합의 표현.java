import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static String str;
    public static int[] parent;

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        parent = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if(a == 0) {
                union(b,c);
            }
            else{
                boolean flag = isSameParent(b,c);
                if(flag) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
    }
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if(x==y) return true;
        else return false;
    }
    public static int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;
        parent[y] = x;
    }
    public static void solution() throws IOException {
        init();

        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

