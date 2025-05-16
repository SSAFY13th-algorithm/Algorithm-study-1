import java.io.*;
import java.util.*;

public class TreeAndQuery {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static Map<Long, Long> maps;
    static long[] input;
    static Set<Long> sets;

    public static void main(String[] args) throws Exception {
        init();
        execute();
    }

    static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        maps = new HashMap<>();
    }

    static void execute() throws Exception {
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            if (input[0] == 1) {
                maps.put(input[2], input[1]);
            } else {
                long c = input[1];
                long d = input[2];
                System.out.println(findSum(c, d));
            }
        }
    }

    static long findSum(long c, long d) {
        long answer = 0;
        sets = new HashSet<>();
        while (c >= 1) {
            sets.add(c);
            c = maps.getOrDefault(c, c / 2);
        }

        long root = 1;
        while (d >= 1) {
            if (sets.contains(d)) {
                root = d;
                break;
            }
            sets.add(d);
            d = maps.getOrDefault(d, d / 2);
        }

        for (long i : sets) {
            if (i >= root) {
                answer += i;
            }
        }

        return answer;
    }
}
