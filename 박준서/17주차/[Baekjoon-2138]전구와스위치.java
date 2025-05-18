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

    private static int N, ans = Integer.MAX_VALUE; // 전구 개수와 최소 스위치 조작 횟수
    private static char[] arr1, arr2; // 초기 상태와 목표 상태 배열

    public static void main(String args[]) throws Exception {
        init(); // 초기화
        blob(0); // 첫 번째 스위치를 누르지 않는 경우
        flip(arr1, 0); // 첫 번째 스위치를 누른 상태로 시작
        blob(1); // 첫 번째 스위치를 누르는 경우

        if (ans == Integer.MAX_VALUE)
            bw.write("-1\n"); // 불가능한 경우
        else
            bw.write(ans + "\n"); // 최소 스위치 조작 횟수
        bwEnd();
    }

    public static void init() throws Exception {
        N = nextint(); // 전구 개수
        arr1 = nextToken().toCharArray(); // 초기 상태
        arr2 = nextToken().toCharArray(); // 목표 상태
    }

    // 그리디 알고리즘으로 전구를 목표 상태로 변경하는 메소드
    public static void blob(int st) {
        int cnt = st; // 스위치 조작 횟수 (첫 번째 스위치 조작 여부로 초기화)
        char[] tmp = arr1.clone(); // 초기 상태 복사

        // 첫 번째 전구부터 순차적으로 처리
        for (int i = 1; i < N; i++) {
            // 이전 전구가 목표 상태와 다르면 현재 위치의 스위치를 누름
            if (tmp[i - 1] != arr2[i - 1]) {
                flip(tmp, i); // i번째 스위치 누름
                cnt++; // 조작 횟수 증가
            }
        }

        // 모든 전구가 목표 상태와 일치하는지 확인
        if (check(tmp))
            ans = Math.min(ans, cnt); // 최소 조작 횟수 갱신
    }

    // idx 위치의 스위치를 눌러 전구 상태를 변경하는 메소드
    public static void flip(char[] arr, int idx) {
        // idx와 그 인접한 전구(idx-1, idx+1)의 상태를 반전
        for (int i = Math.max(0, idx - 1); i <= Math.min(idx + 1, N - 1); i++)
            arr[i] = arr[i] == '0' ? '1' : '0'; // 0->1, 1->0으로 변경
    }

    // 모든 전구가 목표 상태와 일치하는지 확인하는 메소드
    public static boolean check(char[] arr) {
        for (int i = 0; i < N; i++)
            if (arr[i] != arr2[i])
                return false; // 하나라도 다르면 false
        return true; // 모두 일치하면 true
    }
}
