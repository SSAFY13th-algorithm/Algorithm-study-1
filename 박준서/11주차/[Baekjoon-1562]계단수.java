import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    private static final int MOD = 1_000_000_000;

    private static long[][][] DP = new long[101][10][(1 << 10)]; // 단계|현재위치|지나온위치
    private static int N, ans; // N: 계단 수의 길이, ans: 정답을 저장할 변수

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();

        // 길이가 1인 계단 수 초기화 (0으로 시작하는 수는 계단 수가 아님)
        for (int i = 1; i < 10; i++)
            DP[1][i][(1 << i)]++; // 길이 1, 숫자 i로 끝나는, i를 사용한 계단 수는 1개

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) { // 현재 자릿수의 숫자 (0~9)
                for (int k = 0; k < (1 << 10); k++) { // 이전까지 사용한 숫자들의 비트마스크
                    if (j > 0) { // j가 0보다 크면 j-1에서 j로 이동 가능
                        DP[i][j][(k | (1 << j))] += DP[i - 1][j - 1][k];
                        DP[i][j][(k | (1 << j))] %= MOD;
                    }
                    if (j < 9) { // j가 9보다 작으면 j+1에서 j로 이동 가능
                        DP[i][j][(k | (1 << j))] += DP[i - 1][j + 1][k];
                        DP[i][j][(k | (1 << j))] %= MOD;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            // (1 << 10) - 1은 0부터 9까지 모든 숫자를 사용했음을 의미하는 비트마스크
            ans += DP[N][i][(1 << 10) - 1];
            ans %= MOD;
        }
        bw.write(ans + "\n");

        printAnswer();
    }
}
