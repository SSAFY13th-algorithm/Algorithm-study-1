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

    private static int N; // 수열의 길이
    private static int[] arr; // 입력 수열
    private static int[] ans; // LIS를 저장하는 배열

    public static void main(String args[]) throws Exception {
        init(); // 초기화
        // 전체 길이 N에서 LIS 길이를 뺀 값(제거해야 할 최소 원소 개수)을 출력
        bw.write(N - LIS() + "\n");
        bwEnd(); // 출력 스트림 닫기
    }

    public static void init() throws Exception {
        N = nextint(); // 수열의 길이
        arr = new int[N]; // 입력 수열 배열
        ans = new int[N]; // LIS 배열

        // 수열 입력 받기
        for (int i = 0; i < N; i++)
            arr[i] = nextint();
    }

    // 최장 증가 부분 수열(LIS)의 길이를 구하는 메소드
    public static int LIS() {
        int ret = 1; // LIS의 길이 (초기값 1)
        ans[0] = arr[0]; // 첫 번째 원소로 초기화

        // 두 번째 원소부터 탐색
        for (int i = 1; i < N; i++) {
            // 현재 원소가 들어갈 위치를 이분 탐색으로 찾음
            int idx = BS(arr[i], ret);

            if (idx == ret) // 현재 LIS의 끝에 추가하는 경우
                ans[ret++] = arr[i]; // LIS 길이 증가
            else // 기존 LIS의 중간에 삽입하는 경우
                ans[idx] = arr[i]; // 해당 위치의 값 갱신
        }

        return ret; // LIS의 길이 반환
    }

    // 이분 탐색으로 target이 들어갈 위치를 찾는 메소드
    public static int BS(int target, int max) {
        int low = 0, high = max;

        // 이분 탐색 수행
        while (low < high) {
            int mid = (low + high) / 2;

            if (ans[mid] < target) // 중간값이 타겟보다 작으면
                low = mid + 1; // 오른쪽 절반 탐색
            else // 중간값이 타겟보다 크거나 같으면
                high = mid; // 왼쪽 절반 탐색
        }

        return low; // target이 들어갈 위치 반환
    }
}
