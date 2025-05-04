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

    private static int N; // 수열의 길이
    private static int[] arr; // 입력 수열
    private static int[] lis; // LIS 배열 (길이별 최소 끝값)
    private static int[] idx; // 각 원소가 LIS의 몇 번째에 해당하는지 저장
    private static Stack<Integer> ans; // LIS 원소를 역추적하여 저장할 스택

    public static void main(String args[]) throws Exception {
        N = nextInt();
        arr = new int[N];
        lis = new int[N];
        idx = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
            lis[i] = idx[i] = Integer.MIN_VALUE;
        }

        int i = 0, j = 0;
        lis[0] = arr[0]; // 첫 원소로 LIS 시작
        idx[0] = 0; // arr[0]은 LIS의 0번째 위치에 있음

        // arr의 각 원소에 대해 LIS 배열을 갱신
        while (++i < N) {
            if (lis[j] < arr[i]) {
                // 현재 원소가 LIS의 마지막 원소보다 크면 LIS를 확장
                lis[++j] = arr[i];
                idx[i] = j; // arr[i]는 LIS의 j번째 위치
            } else {
                // 그렇지 않으면 이분 탐색으로 LIS 배열에서 갱신할 위치 찾기
                int nIdx = BS(arr[i], j);
                lis[nIdx] = arr[i];
                idx[i] = nIdx; // arr[i]는 LIS의 nIdx번째 위치
            }
        }
        // LIS의 길이 출력
        bw.write((j + 1) + "\n");

        // LIS 원소 역추적
        ans = new Stack<>();
        for (int k = N - 1; k >= 0; k--)
            if (idx[k] == j) { // idx가 j인 원소가 LIS의 일부
                ans.add(arr[k]);
                j--;
            }

        // LIS 출력 (역순으로 쌓았으니 pop)
        while (!ans.isEmpty()) {
            bw.write(ans.pop() + " ");
        }
        bw.write("\n");
        bwEnd();
    }

    // LIS 배열에서 target이 들어갈 위치를 이분 탐색으로 찾음
    public static int BS(int target, int end) {
        int low = 0, high = end;
        while (low < high) {
            int mid = (low + high) / 2;
            if (lis[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
