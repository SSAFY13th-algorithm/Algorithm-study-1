import java.io.*;
import java.util.*;

public class 행복유치원 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m;
    static long[] arr;
    static long[] difArr;
    static int[] input;
    static long answer = 0;

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
        difArr = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            difArr[i] = arr[i + 1] - arr[i];
        }
    }

    static void execute() {
        Arrays.sort(difArr);
        for (long d : difArr) {
            answer += d;
        }
        for (int i = 0; i < m - 1; i++) {
            answer -= difArr[n - 2 - i];
        }
    }
}

