import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;

    public static Set<Integer> set = new HashSet<>();
    public static int[] trains;
    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        trains = new int[n + 1];

        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            if(x == 1) {
                int z = Integer.parseInt(stk.nextToken());
                if((trains[y] & (1 << (z - 1))) != 0) continue;
                trains[y] += (1 << (z - 1));
            }
            else if(x == 2) {
                int z = Integer.parseInt(stk.nextToken());
                if((trains[y] & (1 << (z - 1))) != 0) {
                    trains[y] -= (1 << (z - 1));
                }
            }
            else if(x == 3) {
                trains[y] <<= 1;
                trains[y] &= ((1 << 20) - 1);
            }
            else if(x == 4) trains[y] >>= 1;
        }
    }


    public static void solution() throws IOException {
        init();
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(set.contains(trains[i])) continue;
            set.add(trains[i]);
            cnt++;
        }
        sb.append(cnt);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
