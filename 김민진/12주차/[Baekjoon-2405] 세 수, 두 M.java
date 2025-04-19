package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q3ThreeNumbersTwoM {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int ans;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        ans = 0;

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(nums);
    }

    /*
     * |b - (a + b + c)/3| × 3
     * = |(3b - a - b - c)/3| × 3
     * = |(2b - a - c)/3| × 3
     * = |2b - a - c|
     */
    private static void sol() {
        int gap;

        for (int i = 1; i < N - 1; i++) {
            gap = Math.abs(2 * nums[i] - nums[0] - nums[i + 1]);
            ans = Math.max(ans, gap);

            gap = Math.abs(2 * nums[i] - nums[i - 1] - nums[N - 1]);
            ans = Math.max(ans, gap);
        }
        System.out.println(ans);
    }

    /*
     * [-100, 0, 1, 2, 100]
     * 최소, 최대 둘 다 포함: |2 * 1 - (-100) - 100| = |2 + 100 - 100| = 2
     * 하나만 포함, 두 수는 붙임: |2 * 0 - (-100) - 1| = |0 + 100 - 1| = 99
     */
}
