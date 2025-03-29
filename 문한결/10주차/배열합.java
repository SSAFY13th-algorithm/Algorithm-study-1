package solution;

import java.util.*;
import java.io.*;

public class 배열합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long t;
    static int n, m;
    static long[] a, b;
    static Map<Long, Long> aMaps;
    static Map<Long, Long> bMaps;
    static long answer = 0;
    static long[][] arr;
    static long[][] brr;

    public static void main(String[] args) throws Exception {
        init();
        execute();
        System.out.println(answer);
    }

    static void init() throws Exception {
        t = Long.parseLong(br.readLine());
        n = Integer.parseInt(br.readLine());
        aMaps = new HashMap<>();
        bMaps = new HashMap<>();
        arr = new long[n][n];
        a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        for (int i = 0; i < n; i++) {
            arr[0][i] = a[i];
            aMaps.put(a[i], aMaps.getOrDefault(a[i], 0L) + 1);
        }
        m = Integer.parseInt(br.readLine());
        b = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        brr = new long[m][m];
        for (int i = 0; i < m; i++) {
            brr[0][i] = b[i];
            bMaps.put(b[i], bMaps.getOrDefault(b[i], 0L) + 1);
        }
    }

    static void execute() {
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                arr[i][j] = a[j] + arr[i - 1][j - 1];
                aMaps.put(arr[i][j], aMaps.getOrDefault(arr[i][j], 0L) + 1);
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = i; j < m; j++) {
                brr[i][j] = b[j] + brr[i - 1][j - 1];
                bMaps.put(brr[i][j], bMaps.getOrDefault(brr[i][j], 0L) + 1);
            }
        }

        for (long i : aMaps.keySet()) {
            long num = aMaps.get(i);
            answer += bMaps.getOrDefault(t - i, 0L) * num;
        }
    }
}

