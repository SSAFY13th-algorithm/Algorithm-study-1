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

    private static int N, M, K; // N, M, K 입력값 저장 변수
    private static ArrayList<Integer> arr; // 조건을 만족하는 수를 저장할 리스트

    public static void main(String args[]) throws Exception {
        init();

        // N+1부터 M-1까지의 수 중에서 N의 배수이면서 M의 약수인 수를 찾아 리스트에 추가
        for (int i = N + 1; i < M; i++)
            if (i % N == 0 && M % i == 0)
                arr.add(i);

        // 조건을 만족하는 수의 개수가 K보다 작으면 -1 출력
        if (arr.size() < K)
            bw.write("-1\n");
        else {
            // K개의 수를 출력
            for (int i = 0; i < K; i++)
                bw.write(arr.get(i) + " ");
            bw.write("\n");
        }
        bwEnd();
    }

    // 초기화
    public static void init() throws Exception {
        N = nextInt(); // 첫 번째 정수 N 입력
        M = nextInt(); // 두 번째 정수 M 입력
        K = nextInt(); // 세 번째 정수 K 입력

        arr = new ArrayList<>(); // 리스트 초기화
        arr.add(N); // 리스트에 N 추가
        arr.add(M); // 리스트에 M 추가
    }
}
