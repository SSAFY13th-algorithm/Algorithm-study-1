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

    private static int N; // 입력받을 자연수 N
    private static ArrayList<Integer> powers; // 제곱수 리스트 저장
    private static int[] DP; // DP[i]: i를 제곱수의 합으로 나타내는 최소 개수

    public static void main(String args[]) throws Exception {
        N = nextInt();
        powers = new ArrayList<>();
        DP = new int[2 * N + 1];

        Arrays.fill(DP, Integer.MAX_VALUE);

        // 1부터 N 이하의 제곱수들을 powers에 저장하고, DP[제곱수] = 1로 설정
        for (int i = 1; i * i <= N; i++) {
            DP[i * i] = 1; // 제곱수는 자기 자신만으로 표현 가능하므로 1
            powers.add(i * i); // 제곱수 리스트에 추가
        }

        // DP를 이용해서 각 수를 제곱수의 합으로 표현할 때 최소 개수 계산
        for (int i = 1; i <= N; i++) {
            if (DP[i] == Integer.MAX_VALUE)
                continue; // 아직 도달하지 못한 수는 건너뜀
            for (int it : powers)
                DP[i + it] = Math.min(DP[i + it], DP[i] + 1); // i + 제곱수에 대해 최소값 갱신
        }

        bw.write(DP[N] + "\n");
        bwEnd();
    }
}
