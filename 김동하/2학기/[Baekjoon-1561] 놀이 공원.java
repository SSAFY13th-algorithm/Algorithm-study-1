import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[] arr;


    public static void init() throws IOException {
        StringTokenizer stk =  new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[m + 1];
        stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }

    public static long binarySearch() {
        long st = 0;
        long ed = 60000000000L;
        while (st <= ed) {
            long mid = (st + ed) / 2;
            long sum = m;
            for(int i = 1; i <= m; i++) {
                sum += mid / arr[i];
            }
            if(sum < n) {
                st = mid + 1;
            }
            else {
                ed = mid - 1;
            }
        }
        return st;
    }

    public static void solution() throws IOException {
        init();
        if(n <= m) sb.append(n).append("\n");
        else {
            long time = binarySearch();
            long sum = m;
            for(int i = 1; i <= m; i++) {
                sum += (time - 1) / arr[i];
            }

            for(int i = 1; i <= m; i++) {
                if(time % arr[i] == 0) sum++;
                if(sum == n) {
                    sb.append(i).append("\n");
                    return;
                }
            }
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