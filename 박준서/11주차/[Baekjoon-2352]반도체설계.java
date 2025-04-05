import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    // N: 입력받을 숫자의 개수, M: 각 숫자
    private static int N, M;
    // 가장 긴 증가하는 부분 수열(LIS)을 저장할 ArrayList
    private static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();

        readLine();
        while (N-- != 0) {
            M = nextInt();
            // 이진 탐색으로 현재 숫자가 들어갈 위치 찾기
            int p = binaryS(M);
            // 찾은 위치가 배열의 크기와 같다면 (모든 원소보다 크다면)
            if (p == arr.size())
                arr.add(M); // 배열 끝에 추가
            else
                arr.set(p, M); // 아니면 해당 위치의 값을 현재 값으로 교체
        }

        // LIS의 길이 출력
        bw.write(arr.size() + "\n");

        printAnswer();
    }

    // 이진 탐색
    public static int binaryS(int target) {
        int low = 0, high = arr.size();
        int mid = 0;
        mid = (low + high) / 2;

        // 배열이 비어있으면 0 반환
        if (arr.size() == 0)
            return 0;

        // 이진 탐색 수행
        while (low < high) {
            if (arr.get(mid) == target)
                break; // 동일한 값을 찾으면 탐색 종료
            else if (arr.get(mid) < target)
                low = mid + 1; // target이 더 크면 오른쪽 부분 탐색
            else
                high = mid; // target이 더 작으면 왼쪽 부분 탐색
            mid = (low + high) / 2; // 중간 위치 갱신
        }

        // target이 들어갈 위치 반환
        return mid;
    }
}
