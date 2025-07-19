import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<int[]> list = new ArrayDeque<>();
    static int n, q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] sum = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); // 비내림차순으로 정렬

        sum[0] = arr[0];
        for (int i = 1; i < n; i++) { // 구간합 구해놓기
            sum[i] = sum[i - 1] + arr[i];
        }

        for (int i = 0; i < q; i++) { // 입력에 따라 출력하기
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            sb.append(sum[to] - sum[from] + arr[from] + "\n");
        }

        System.out.println(sb.toString());
    }


}
