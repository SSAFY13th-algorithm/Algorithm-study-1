import java.util.*;
import java.io.*;

public class GLCCDM {
    static int K;
    static List<Long> divisors = new ArrayList<>();
    static boolean found = false;
    static long A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (B % A != 0) {
            System.out.println(-1);
            return;
        }

        C = B / A;
        getDivisors(C);
        Collections.sort(divisors);

        dfs(0, 0, 0, 1, new long[K]);

        if (!found) System.out.println(-1);
    }

    static void getDivisors(long n) {
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) divisors.add(n / i);
            }
        }
    }

    static void dfs(int idx, int count, long g, long l, long[] arr) {
        if (found) return;
        if (count == K) {
            if (g == 1 && l == C) {
                for (int i = 0; i < K; i++) {
                    System.out.print(arr[i] * A + " ");
                }
                System.out.println();
                found = true;
            }
            return;
        }

        if (divisors.size() - idx < K - count) return;

        for (int i = idx; i < divisors.size(); i++) {
            long d = divisors.get(i);
            long newGCD = (count == 0) ? d : gcd(g, d);
            long newLCM = (count == 0) ? d : lcm(l, d);
            if (newLCM > C || C % newLCM != 0) continue;

            arr[count] = d;
            dfs(i + 1, count + 1, newGCD, newLCM, arr);
            if (found) return;
        }
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
