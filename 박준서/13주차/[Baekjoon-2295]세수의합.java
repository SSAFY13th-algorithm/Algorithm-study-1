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

    private static int N; // 입력 배열의 크기
    private static int ans; // 정답을 저장할 변수 (사용되지 않음)
    private static ArrayList<Integer> arr; // 입력 숫자들을 저장할 리스트
    private static Set<Integer> s; // 두 수의 합을 저장할 집합

    public static void main(String args[]) throws Exception {
        N = nextInt(); // 배열 크기 입력
        arr = new ArrayList<>(); // 입력 숫자 저장 리스트 초기화
        s = new TreeSet<>(); // 두 수의 합 저장 집합 초기화 (TreeSet은 정렬됨)

        // N개의 숫자 입력 받기
        for (int i = 0; i < N; i++)
            arr.add(nextInt());

        // 모든 가능한 두 수의 합을 집합에 저장
        // (같은 인덱스의 숫자도 더할 수 있음)
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                s.add(arr.get(i) + arr.get(j));

        arr.sort(Comparator.naturalOrder());

        // 가장 큰 값부터 확인하여 조건을 만족하는 숫자 찾기
        loop: for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                // arr[i] = arr[j] + x 형태로 표현 가능한지 확인
                // 즉, x = arr[i] - arr[j]가 두 수의 합으로 표현 가능한지 확인
                int tmp = arr.get(i) - arr.get(j);
                if (s.contains(tmp)) {
                    bw.write(arr.get(i) + "\n");
                    break loop; // 가장 큰 값을 찾았으므로 반복문 종료
                }
            }
        }

        bwEnd();
    }
}
