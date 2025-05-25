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

    private static int nextint() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static int lengthY, lengthX, ans; // 두 문자열의 길이와 최장 공통 부분 문자열 길이
    private static char[] in1, in2; // 입력 문자열을 저장할 배열
    private static int[][] DP; // 동적 프로그래밍을 위한 2차원 배열

    public static void main(String args[]) throws Exception {
        init(); // 초기화
        LCS(); // 최장 공통 부분 문자열(LCS) 계산
        bw.write(ans + "\n"); // 결과 출력
        bwEnd(); // 출력 스트림 닫기
    }

    // 입력을 받아 초기화하는 메소드
    public static void init() throws Exception {
        in1 = nextToken().toCharArray(); // 첫 번째 문자열 입력
        in2 = nextToken().toCharArray(); // 두 번째 문자열 입력
        lengthY = in1.length; // 첫 번째 문자열 길이
        lengthX = in2.length; // 두 번째 문자열 길이
        DP = new int[lengthY + 1][lengthX + 1]; // DP 배열 초기화 (1-indexed)
    }

    // 최장 공통 부분 문자열(LCS)을 계산하는 메소드
    public static void LCS() {
        for (int i = 1; i <= in1.length; i++)
            for (int j = 1; j <= in2.length; j++)
                // 현재 위치의 문자가 같은 경우
                if (in1[i - 1] == in2[j - 1]) {
                    // 이전 위치의 LCS 길이에 1을 더함
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    // 최대 길이 갱신
                    ans = Math.max(ans, DP[i][j]);
                }
    }
}
