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

    private static int N; // 용액의 개수
    private static long max; // 0에 가장 가까운 특성값의 절대값
    private static ArrayList<Long> arr; // 용액의 특성값을 저장할 리스트
    private static long[] ans; // 정답(세 용액의 특성값)을 저장할 배열

    public static void main(String args[]) throws Exception {
        N = nextInt(); // 용액의 개수 입력
        arr = new ArrayList<>(); // 용액 특성값 리스트 초기화
        max = Long.MAX_VALUE; // 최소값 비교를 위해 최대값으로 초기화

        for (int i = 0; i < N; i++)
            arr.add((long) nextInt());

        arr.sort(Comparator.naturalOrder());

        // 세 용액을 선택하여 특성값의 합이 0에 가장 가까운 조합 찾기
        for (int i = 0; i < N - 2; i++) {
            int p1 = i + 1; // 두 번째 포인터 (i 다음 위치)
            int p2 = N - 1; // 세 번째 포인터 (마지막 위치)

            while (p1 != p2) {
                // 세 용액의 특성값 합 계산
                long sum = arr.get(i) + arr.get(p1) + arr.get(p2);

                // 현재 합의 절대값이 지금까지 찾은 최소 절대값보다 작으면 갱신
                if (Math.abs(sum) < max) {
                    max = Math.abs(sum); // 최소 절대값 갱신
                    ans = new long[] { arr.get(i), arr.get(p1), arr.get(p2) }; // 정답 배열 갱신
                }

                // 합이 음수면 p1을 증가시켜 합을 키움
                if (sum < 0)
                    p1++;
                // 합이 양수면 p2를 감소시켜 합을 줄임
                else
                    p2--;
            }
        }

        for (int i = 0; i < 3; i++)
            bw.write(ans[i] + " ");
        bw.write("\n");

        bwEnd();
    }
}
