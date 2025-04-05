import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[] arr;

    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(arr);
        m = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int x = Integer.parseInt(stk.nextToken());
            if(binarySearch(x)) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
    }

    public static boolean binarySearch(int x) {
        int st = 0;
        int ed = n - 1;
        while(st <= ed) {
            int mid = (st + ed) / 2;
            if(arr[mid] == x) return true;
            else if(arr[mid] < x) st = mid + 1;
            else ed = mid - 1;
        }
        return false;
    }

    public static void solution() throws IOException{
        init();
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
