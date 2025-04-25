import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static int N, cnt; // N: 입력 개수, cnt: 누적 합계 계산
    private static int[] matrix; // 누적 합 배열 (prefix sum)

    public static void main(String args[]) throws Exception {
        N = nextInt();

        // 누적 합 배열 초기화 (인덱스 1부터 사용)
        matrix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            cnt += nextInt(); // 숫자 입력받으며 누적 합 계산
            matrix[i] = cnt; // i번째까지의 누적 합 저장
        }

        N = nextInt();
        while (N-- != 0) {
            // prefix sum[b] - prefix sum[a-1]로 구간 합 계산
            bw.write((-matrix[nextInt() - 1] + matrix[nextInt()]) + "\n");
        }

        bwEnd();
    }
}
