import java.util.*;
import java.io.*;

public class 나머지합 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static long[] arr;
    static int[] input;
    static Map<Long, Long> remainMap;
    static long answer;

    public static void main(String[] args) throws Exception {
        init();
        execute();
        System.out.println(answer);
    }

    static void init() throws Exception {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        remainMap = new HashMap<>();
    }

    static void execute() {
        for (int i = 1; i < n; i++) arr[i] += arr[i - 1];
        for (int i = 0; i < n; i++) {
            long remain = arr[i] % m;
            if (remain == 0) answer++;
            remainMap.merge(remain, 1L, Long::sum);
        }
        for (long key : remainMap.keySet()) {
            long count = remainMap.get(key);
            if (count == 1) continue;
            answer += findSub(count);
        }
    }

    static long findSub(long n) {
        return n * (n - 1) / 2;
    }
}
