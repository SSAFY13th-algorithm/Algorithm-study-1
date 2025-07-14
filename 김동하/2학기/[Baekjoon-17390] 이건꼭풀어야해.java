import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,q;
    public static int[] arr;
    public static int[] sum;

    public static void init() throws IOException {
        StringTokenizer stk =  new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        q = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        sum = new int[n];
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(arr);
        sum[0] = arr[0];
        for(int i = 1; i < n; i++){
            sum[i] = sum[i-1] + arr[i];
        }
        for(int i = 0; i < q; i++) {
            int a,b;
            stk = new StringTokenizer(br.readLine());
            a = Integer.parseInt(stk.nextToken()) - 1;
            b = Integer.parseInt(stk.nextToken()) - 1;
            sb.append(sum[b] - sum[a] + arr[a]).append('\n');
        }
    }

    public static void solution() throws IOException {
        init();
    }

    public static void main(String[] args)throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}