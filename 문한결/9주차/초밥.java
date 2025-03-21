import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, d, k, c;
    static int input[];
    static int answer;
    static int arr[];
    static Map<Integer, Integer> map;


    public static void main(String[] args) throws IOException {
        init();
        findAnswer();
        System.out.println(answer);

    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        d = input[1];
        k = input[2];
        c = input[3];
        map = new HashMap<>();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


    }

    static void findAnswer() {
        for (int i = 0; i < 2 * n; i++) {

            int cur = i % n;
            if (map.get(arr[cur]) == null) {
                map.put(arr[cur], 1);
            } else {
                map.put(arr[cur], map.get(arr[cur]) + 1);
            }
            if (i == k - 1)
                findMax();
            if (i < k)
                continue;
            if (map.get(arr[cur - k < 0 ? n + (cur - k) : cur - k]) == 1)
                map.remove(arr[cur - k < 0 ? n + (cur - k) : cur - k]);
            else
                map.put(arr[cur - k < 0 ? n + (cur - k) : cur - k],
                        map.get(arr[cur - k < 0 ? n + (cur - k) : cur - k]) - 1);
            findMax();

        }

    }

    static void findMax() {
        answer = Math.max(answer, map.get(c) == null ? map.size() + 1 : map.size());
    }



}
