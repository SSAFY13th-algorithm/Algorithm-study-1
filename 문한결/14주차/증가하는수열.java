import java.io.*;
import java.util.*;

public class 증가하는수열 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[] prev   = new int[n];
        int[] lenIdx = new int[n];
        Arrays.fill(prev, -1);

        List<Integer> tailsVal = new ArrayList<>(); // tail 값만 저장
        tailsVal.add(arr[0]);
        lenIdx[0] = 0;
        int len = 1;

        for (int i = 1; i < n; i++) {
            int x = arr[i];


            int pos = Collections.binarySearch(tailsVal, x);
            if (pos < 0) pos = -(pos + 1);   // 삽입 위치

            if (pos == len) {
                tailsVal.add(x);
                len++;
            } else {
                tailsVal.set(pos, x);        // tail 값 교체
            }

            if (pos > 0) prev[i] = lenIdx[pos - 1];
            lenIdx[pos] = i;
        }

        // 역추적
        int[] lis = new int[len];
        int cur = lenIdx[len - 1];
        for (int k = len - 1; k >= 0; k--) {
            lis[k] = arr[cur];
            cur = prev[cur];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append('\n');
        for (int v : lis) sb.append(v).append(' ');
        System.out.println(sb);
    }
}
