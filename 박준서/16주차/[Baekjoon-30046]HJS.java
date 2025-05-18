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

    private static int N; // 각 문자열의 길이
    private static boolean flag; // 조건을 만족하는 우선순위가 존재하는지 여부
    private static char[] P, Q, R; // 세 개의 문자열 배열

    private static Map<Character, Integer> prio;

    public static void main(String args[]) throws Exception {
        init();

        // 세 문자(H, J, S)에 대한 모든 가능한 우선순위 조합을 시도
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    // 서로 다른 우선순위를 가져야 함
                    if (k == i || k == j || i == j)
                        continue;

                    // 각 문자에 우선순위 할당
                    prio.put('H', i);
                    prio.put('J', j);
                    prio.put('S', k);

                    // P < Q 이고 Q < R 인지 확인
                    if (check(P, Q) && check(Q, R)) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        if (flag)
            bw.write("HJS! HJS! HJS!\n");
        else
            bw.write("Hmm...\n");
        bwEnd();
    }

    // 초기화
    public static void init() throws Exception {
        N = nextInt(); // 문자열 길이 입력
        P = nextToken().toCharArray(); // 첫 번째 문자열 입력
        Q = nextToken().toCharArray(); // 두 번째 문자열 입력
        R = nextToken().toCharArray(); // 세 번째 문자열 입력

        prio = new HashMap<>(); // 우선순위 맵 초기화
    }

    // 두 문자열을 비교하여 arr1 < arr2 인지 확인
    public static boolean check(char[] arr1, char[] arr2) {
        for (int i = 0; i < N; i++) {
            // 첫 번째 문자열의 우선순위가 더 높으면 조건 불만족
            if (prio.get(arr1[i]) > prio.get(arr2[i]))
                return false;
            // 첫 번째 문자열의 우선순위가 더 낮으면 조건 만족
            else if (prio.get(arr1[i]) < prio.get(arr2[i]))
                return true;
            // 우선순위가 같으면 다음 문자 비교
        }
        // 모든 문자의 우선순위가 같으면 조건 불만족 (사전순으로 같음)
        return false;
    }
}
