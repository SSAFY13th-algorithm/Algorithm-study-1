import java.io.*;

public class Baekjoon_15817 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        // DP[i][j]: i번째 파이프까지 사용했을 때, 길이 j를 만들 수 있는 경우의 수
        int[][] DP = new int[N + 1][M + 1];
        // 초기값 설정: 길이 0을 만드는 경우는 1가지
        DP[0][0] = 1;

        // 각 파이프 종류별로 반복
        for (int i = 1; i <= N; i++) {
            // 각 파이프의 길이(len)와 개수(cnt) 입력 받기
            in = br.readLine().split(" ");
            int len = Integer.parseInt(in[0]); // 파이프의 길이
            int cnt = Integer.parseInt(in[1]); // 파이프의 개수

            // 현재까지의 모든 가능한 길이에 대해 반복
            for (int j = 0; j <= M; j++) {
                // 현재 파이프를 0개부터 cnt개까지 사용하는 경우 검사
                for (int k = 0; k <= cnt; k++) {
                    // 만들어지는 길이가 목표 길이(M)를 초과하면 중단
                    if (j + len * k > M)
                        break;
                    // 현재 상태의 경우의 수를 이전 상태의 경우의 수에 더함
                    DP[i][j + len * k] += DP[i - 1][j];
                }
            }
        }
        System.out.println(DP[N][M]);
    }
}
