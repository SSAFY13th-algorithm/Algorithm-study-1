package solution;
import java.util.*;
import java.io.*;

public class 일감호
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long n, m, k;
    static long[] parent;
    static long[] input;
    static long minCost = 0;
    static long[] consturtSite;
    static List<long[]> list;

    public static void main(String args[]) throws Exception {
        
    	init();
        
        execute();
        
        System.out.println(minCost <= k ? "YES" : "NO");
    }

    static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        n = input[0]; m = input[1]; k = input[2];
        list = new ArrayList<>();
        consturtSite = new long[(int) n];
        parent = new long[(int) (n + 2)];
        for (int i = 0; i < n + 2; i++) parent[i] = i;
        input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        for (int i = 0; i < input.length; i++) {
            list.add(new long[]{i + 1, n + 1, input[i]});
        }
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            if(input[1]==1)consturtSite[(int) input[0] - 1] = 1;
            else {
            	consturtSite[(int) Math.min(input[0],input[1]) - 1] = 1;
            }
        }
    }

    static void execute() {
        for (int i = 1; i <= n; i++) {
            if (consturtSite[i - 1] == 1) continue;
            union(i, i % n + 1);
        }
        
        list.sort((arr1, arr2) -> Long.compare(arr1[2], arr2[2]));
        long one = find(1);
        boolean isFinish = true;
        for (int i = 2; i <= n; i++) {
            if (find(i) != one) isFinish = false;
        }
        if (isFinish || m==0 || m==1) return;
        for (int i = 0; i < n; i++) {
            long[] vs = list.get(i);
            long v1 = vs[0];
            long v2 = vs[1];
            v1 = find(v1);
            v2 = find(v2);
            if (v1 != v2) {
                union(v1, v2);
                minCost += vs[2];
            }
        }
    }

    static void union(long a, long b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[(int) a] = b;
        else parent[(int) b] = a;
    }

    static long find(long v) {
        if (v == parent[(int) v]) return v;
        return parent[(int) v] = find(parent[(int) v]);
    }
}
