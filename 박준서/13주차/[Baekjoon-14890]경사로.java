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

    private static int N, X, ans; // N: 지도 크기, X: 경사로 길이, ans: 가능한 길의 수
    private static int[][] matrix; // 지도 정보를 저장할 2차원 배열

    public static void main(String args[]) throws Exception {
        N = nextInt();
        X = nextInt();
        ans = 0;

        matrix = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = nextInt();

        for (int i = 0; i < N; i++)
            if (check(matrix[i])) { // i번째 행이 지나갈 수 있는 길인지 확인
                ans++;
            }

        for (int i = 0; i < N; i++) {
            int[] tmp = new int[N]; // 열 정보를 저장할 임시 배열
            for (int j = 0; j < N; j++) {
                tmp[j] = matrix[j][i]; // i번째 열의 정보를 tmp 배열에 복사
            }
            if (check(tmp)) { // i번째 열이 지나갈 수 있는 길인지 확인
                ans++;
            }
        }

        bw.write(ans + "\n");
        bwEnd();
    }

    // 한 줄(행 또는 열)이 지나갈 수 있는 길인지 확인하는 메소드
    public static boolean check(int[] row) {
        int num = row[0]; // 현재 높이
        int cnt = 1; // 현재 높이가 연속된 칸의 수

        for (int i = 1; i < N; i++) {
            if (row[i] == num) { // 같은 높이가 계속되는 경우
                cnt++; // 연속 카운트 증가
            } else if (row[i] == num + 1) { // 높이가 1 증가하는 경우 (오르막)
                if (cnt < X) // 경사로를 놓을 수 있는 충분한 길이가 없으면
                    return false; // 불가능
                num = row[i]; // 현재 높이 갱신
                cnt = 1; // 연속 카운트 초기화
            } else if (row[i] == num - 1) { // 높이가 1 감소하는 경우 (내리막)
                if (i + X > N) // 경사로를 놓을 수 있는 충분한 공간이 없으면
                    return false; // 불가능

                // 앞으로 X개의 칸이 모두 같은 높이인지 확인
                for (int j = 1; j < X; j++) {
                    if (row[i + j] != row[i])
                        return false; // 높이가 다르면 불가능
                }

                num = row[i]; // 현재 높이 갱신
                cnt = 1 - X; // 연속 카운트 조정 (이미 사용한 칸을 고려)
                i += X - 1; // 경사로가 놓인 위치 다음으로 이동
            } else { // 높이 차이가 1보다 크면
                return false; // 불가능
            }
        }

        return cnt >= 0;
    }
}
