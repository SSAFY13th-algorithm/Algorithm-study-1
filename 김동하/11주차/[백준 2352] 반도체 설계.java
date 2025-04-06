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

    public static int n;
    public static int[] dp;
    public static int[] arr;
    public static int ans;

    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        ans = 0;
    }

    public static void solution() throws IOException{
        init();
        for(int i = 1; i <= n; i++) {
            dp[i] = 1;
            for(int j = i - 1; j > 0; j--) {
                if(arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
    }

    public static void main(String[] args) throws IOException{
        solution();
        sb.append(ans);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
