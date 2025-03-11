import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[][] mission;

    public static int ans = 0;

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        mission = new int[2][3];

        for(int i = 0; i < 2; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                mission[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
    }

    public static void rec(int prev, int day, int val) {
        if(day > n) {
            if(val >= m) ans++;
            return;
        }
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                if(prev == j) rec(j, day + 1, val + mission[i][j] / 2);
                else rec(j, day + 1, val + mission[i][j]);
            }
        }
    }

    public static void solution() throws IOException {
        init();
        rec(-1,1,0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }



    public static void main(String[] args) throws IOException {
        solution();
    }
}
