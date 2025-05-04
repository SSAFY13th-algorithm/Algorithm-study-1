package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3LIS {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int dpLen;

    private static int[] nums;
    private static int[] dp;         // LIS 값
    private static int[] idx;        // 각 수가 dp의 몇 번째 위치에 들어갔는지

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        idx = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void sol() {
        dp[0] = nums[0];
        idx[0] = 0;
        dpLen = 1;

        for (int i = 1; i < N; i++) {
            // 끝에 추가
            if (nums[i] > dp[dpLen - 1]) {
                dp[dpLen] = nums[i];
                idx[i] = dpLen;
                dpLen++;

                // 중간에 추가
            } else {
                int target = lowerBound(0, dpLen, nums[i]);
                dp[target] = nums[i];
                idx[i] = target;
            }
        }
        printAns();
    }

    private static int lowerBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private static void printAns() {
        int len = dpLen - 1;

        // 역순 탐색
        int[] ans = new int[dpLen];
        for (int i = N - 1; i >= 0; i--) {
            if (idx[i] == len) {
                ans[len--] = nums[i];
            }
        }

        sb.append(dpLen).append("\n");
        for (int x : ans) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }
}
