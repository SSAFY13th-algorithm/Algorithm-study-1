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

    // N: 회전 초밥 벨트에 놓인 접시의 수
    // d: 초밥의 가짓수
    // k: 연속해서 먹는 접시의 수
    // c: 쿠폰 번호
    // ans: 최대로 먹을 수 있는 초밥 가짓수
    private static int N, d, k, c, ans;

    private static int[] sushi; // 회전 초밥 벨트에 놓인 초밥 종류를 저장하는 배열
    private static int[] sushiNum; // 각 초밥 종류별 개수를 카운트하는 배열
    private static ArrayDeque<Integer> q; // 현재 연속으로 먹는 k개의 초밥을 저장하는 큐

    public static void main(String[] args) throws Exception {
        readLine();
        N = nextInt();
        d = nextInt();
        k = nextInt();
        c = nextInt();

        sushi = new int[N + 1];
        sushiNum = new int[d + 1];
        q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            readLine();
            sushi[i] = nextInt();
        }

        // 초기 상태 설정: 마지막 k개의 초밥을 큐에 넣고 카운트
        for (int i = 0; i < k; i++) {
            q.add(sushi[N - k + i]);
            sushiNum[sushi[N - k + i]]++;
        }

        // 슬라이딩 윈도우 방식으로 모든 연속된 k개의 초밥 조합을 확인
        for (int i = 0; i < N; i++) {
            // 큐의 맨 앞 초밥을 제거하고 카운트 감소
            sushiNum[q.poll()]--;

            // 새로운 초밥을 큐에 추가하고 카운트 증가
            sushiNum[sushi[i]]++;
            q.add(sushi[i]);

            // 쿠폰으로 받는 초밥을 추가로 먹는 경우를 시뮬레이션
            sushiNum[c]++;
            // 현재 먹을 수 있는 초밥 가짓수 계산하여 최댓값 갱신
            ans = Math.max(ans, count());
            // 쿠폰 초밥 카운트 원상복구 (다음 반복에서 다시 추가)
            sushiNum[c]--;
        }

        bw.write(ans + "\n");
        printAnswer();
    }

    // 현재 먹을 수 있는 초밥의 가짓수를 계산하는 메소드
    // 1부터 d까지의 초밥 종류 중 하나 이상 있는 초밥의 종류 수를 카운트
    public static int count() {
        int ret = 0;
        for (int i = 0; i <= d; i++)
            if (sushiNum[i] != 0) {
                ret++;
            }
        return ret;
    }
}
