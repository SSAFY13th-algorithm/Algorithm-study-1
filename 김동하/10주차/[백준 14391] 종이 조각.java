import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[][] arr;
    public static int ans;

    public static void init() throws IOException{
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        ans = 0;
        arr = new int[n + 1][m + 1];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
    }

    public static void solution() throws IOException{
        init();
        int siz = (1 << n * m);
        for(int bit= 0; bit < siz; bit++) {
            int sum = 0;
            for(int i = 0; i < n; i++) {
                int order = 1;
                for(int j = m - 1; j >= 0; j--) {
                    int tmp = j + i * m;
                    if((bit & (1 << tmp)) > 0) {
                        sum += arr[i][j] * order;
                        order *= 10;
                    }
                    else order = 1;
                }
            }
            for(int j = 0; j < m; j++) {
                int order = 1;
                for(int i = n - 1; i >= 0; i--) {
                    int tmp = j + i * m;
                    if((bit & (1 << tmp)) == 0) {
                        sum += arr[i][j] * order;
                        order *= 10;
                    }
                    else order = 1;
                }
            }
            ans = Math.max(ans, sum);
        }
        sb.append(ans);
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
