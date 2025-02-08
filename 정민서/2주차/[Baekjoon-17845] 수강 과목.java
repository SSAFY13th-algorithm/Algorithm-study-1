import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, k; // 최대 공부시간, 과목 수
    public static int[] dp; // DP 배열

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dp[n]); // 최종적으로 얻을 수 있는 최대 중요도 출력
    }

    public static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1]; // DP 배열 생성

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            //역순 업데이트
            for (int j = n; j >= t; j--) {
                dp[j] = Math.max(dp[j], dp[j - t] + l);
            }
        }
    }
}
