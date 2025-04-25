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

    private static int N; // 정점의 개수
    private static int[][] matrix; // 인접 행렬 (그래프)

    public static void main(String args[]) throws Exception {
        N = nextInt();

        matrix = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = nextInt() == 1 ? 1 : Integer.MAX_VALUE / 2;

        // Floyd-Warshall 알고리즘
        // 모든 정점 쌍 (i, j)에 대해, k를 거쳐가는 경로가 더 짧으면 갱신
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);

        // matrix[i][j]가 무한대가 아니면 (경로가 있으면) 1, 아니면 0 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                bw.write((matrix[i][j] == Integer.MAX_VALUE / 2 ? 0 : 1) + " ");
            bw.write("\n");
        }
        bwEnd();
    }
}
