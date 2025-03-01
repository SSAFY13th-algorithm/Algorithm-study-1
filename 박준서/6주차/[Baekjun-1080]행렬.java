import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static boolean[][] matrix; // 현재 행렬
    private static boolean[][] target; // 목표 행렬
    private static int N, M, ans = 0;

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        M = nextInt();
        matrix = new boolean[N][M];
        target = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++)
                matrix[i][j] = str.charAt(j) == '1';
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++)
                target[i][j] = str.charAt(j) == '1';
        }

        // 3x3보다 작은 행렬이면서 현재와 목표가 다르면 변환 불가능
        if ((N < 3 || M < 3) && !check()) {
            System.out.println(-1);
            return;
        }

        // 왼쪽 위부터 순차적으로 현재와 목표가 다르면 3x3 뒤집기 수행
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (matrix[i][j] != target[i][j]) {
                    flip(i, j);
                    ans++;
                }
            }
        }

        // 최종적으로 현재와 목표가 같으면 뒤집기 횟수 출력, 다르면 -1 출력
        System.out.println(check() ? ans : -1);
    }

    // (y,x)를 왼쪽 위 꼭지점으로 하는 3x3 부분 행렬을 뒤집는 메소드
    private static void flip(int y, int x) {
        for (int i = y; i < y + 3; i++)
            for (int j = x; j < x + 3; j++)
                matrix[i][j] = !matrix[i][j];
    }

    // 현재 행렬과 목표 행렬이 같은지 확인하는 메소드
    private static boolean check() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (matrix[i][j] != target[i][j])
                    return false;
        return true;
    }
}
