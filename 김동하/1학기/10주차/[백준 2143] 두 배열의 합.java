import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int T,n,m;
    public static int[] arrA,arrB;

    public static List<Integer> sumA,sumB;

    public static void init() throws IOException{
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        arrA = new int[n + 1];
        for(int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(stk.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        arrB = new int[m + 1];
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++ ) {
            arrB[i] = Integer.parseInt(stk.nextToken());
        }
        sumA = new ArrayList<>();
        sumB = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            sumA.add(arrA[i]);
            int sum = arrA[i];
            for(int j = i + 1; j < n; j++) {
                sum += arrA[j];
                sumA.add(sum);
            }
        }
        for(int i = 0; i < m; i++) {
            sumB.add(arrB[i]);
            int sum = arrB[i];
            for(int j = i + 1; j < m; j++) {
                sum += arrB[j];
                sumB.add(sum);
            }
        }
        Collections.sort(sumA);
        Collections.sort(sumB);
    }

    public static int upperBound(int a) {
        int st = 0;
        int ed = sumB.size() - 1;
        int toFind = T - a;
        while(st <= ed) {
            int mid = (st + ed) / 2;
            int cur = sumB.get(mid);
            if(cur > toFind) ed = mid - 1;
            else st = mid + 1;
        }
        return st;
    }
    public static int lowerBound(int a) {
        int st = 0;
        int ed = sumB.size() - 1;
        int toFind = T - a;
        while(st <= ed) {
            int mid = (st + ed) / 2;
            int cur = sumB.get(mid);
            if(cur >= toFind) ed = mid - 1;
            else st = mid + 1;
        }
        return st;
    }



    public static void solution() throws IOException{
        init();
        long ans = 0;
        for(int i : sumA) {
            int upper = upperBound(i);
            int lower = lowerBound(i);

            ans += (upper - lower);
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
