import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n, d, k, c;
    public static int[] arr;
    public static boolean canEat = false;

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        d = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());

        arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void solution() throws IOException {
        init();
        int ans = 0;
        int l = 0, r = k - 1;
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = l; i <= r; i++) {
            if(m.containsKey(arr[i])) m.put(arr[i], m.get(arr[i]) + 1);
            else m.put(arr[i], 1);
        }
        int size = m.size();
        if(canEat && !m.containsKey(c)) {
            size++;
        }
        ans = Math.max(ans, size);
        while(l <= n + 1) {
            if(m.get(arr[l % n]) == 1) {
                m.remove(arr[l % n]);
            }
            else m.put(arr[l % n], m.get(arr[l % n]) - 1);
            l++;
            r++;
            if(m.containsKey(arr[r % n])) m.put(arr[r % n], m.get(arr[r % n]) + 1);
            else m.put(arr[r % n], 1);
            size = m.size();
            if(!m.containsKey(c)) {
                size++;
            }
            ans = Math.max(ans, size);
        }
        sb.append(ans);
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
