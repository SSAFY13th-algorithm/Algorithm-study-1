import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회 {
    static int[][] cost;
    static int size;
    static int minCost = Integer.MAX_VALUE;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        dp = new int[1 << size][size];
        cost = new int[size][size];

        for (int i = 0; i < (1 << size); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 비용 행렬 입력
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 0, 1);

        System.out.println(minCost);
    }

    private static void find(int curNum, int curSum, int visited) {
        if (dp[visited][curNum] <= curSum) {
            return;
        }
        dp[visited][curNum] = curSum;

        if (visited == (1 << size) - 1) {
            if (cost[curNum][0] != 0) {  
                minCost = Math.min(minCost, curSum + cost[curNum][0]);
            }
            return;
        }

        for (int i = 0; i < size; i++) {
            if ((visited & (1 << i)) != 0 || cost[curNum][i] == 0) continue;
            find(i, curSum + cost[curNum][i], visited | (1 << i));
        }
    }
}
