import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        int m = Integer.parseInt(in[1]);

        // matrix: 입력받은 비용을 저장할 2차원 배열
        int[][] matrix = new int[n + 1][m + 1];
        // DP[i][j][k]: i행 j열에서 k방향으로 이동할 때의 최소 비용
        // k = 0: 오른쪽 위, k = 1: 위, k = 2: 왼쪽 위
        int[][][] DP = new int[n + 1][m + 1][3];
        int ans = 1000000;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(DP[i][j], 1000000);
            }
        }

        // 동적 프로그래밍으로 최소 비용 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 왼쪽 위로 이동하는 경우 (j > 1일 때)
                if (j > 1)
                    DP[i][j][2] = Math.min(DP[i][j][2],
                            matrix[i][j] + Math.min(DP[i - 1][j - 1][0], DP[i - 1][j - 1][1]));
                // 오른쪽 위로 이동하는 경우 (j < m일 때)
                if (j < m)
                    DP[i][j][0] = Math.min(DP[i][j][0],
                            matrix[i][j] + Math.min(DP[i - 1][j + 1][1], DP[i - 1][j + 1][2]));
                // 위로 이동하는 경우
                DP[i][j][1] = Math.min(DP[i][j][1], matrix[i][j] + Math.min(DP[i - 1][j][0], DP[i - 1][j][2]));
            }
        }

        // 마지막 행에서 최소값 찾기
        for (int i = 1; i <= m; i++)
            for (int j = 0; j < 3; j++)
                ans = Math.min(Math.min(ans, DP[n][i][0]), Math.min(DP[n][i][1], DP[n][i][2]));

        System.out.println(ans);
    }
}
