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

    private static int N, ans; // N: 강의 수, ans: 필요한 최소 강의실 수
    private static ArrayList<Lecture> lectures = new ArrayList<>(); // 강의 목록
    private static PriorityQueue<Integer> times = new PriorityQueue<>((a, b) -> b - a); // 강의 종료 시간을 저장하는 우선순위 큐 (내림차순)

    static class Lecture {
        int s, e; // s: 시작 시간, e: 종료 시간

        public Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();

        for (int i = 0; i < N; i++) {
            readLine();
            lectures.add(new Lecture(nextInt(), nextInt()));
        }

        // 강의를 종료 시간 기준으로 내림차순 정렬
        // 종료 시간이 같으면 시작 시간 기준 내림차순 정렬
        lectures.sort((a, b) -> {
            if (a.e == b.e)
                return b.s - a.s;
            return b.e - a.e;
        });

        for (Lecture it : lectures) {
            if (times.isEmpty()) {
                times.add(it.s);
                continue;
            }

            // 현재 강의의 종료 시간이 우선순위 큐의 최상위 값(이전 강의의 시작 시간) 이하면
            // 같은 강의실을 사용할 수 있으므로 이전 강의 제거
            if (it.e <= times.peek()) {
                times.poll();
            }

            times.add(it.s);

            ans = Integer.max(ans, times.size());
        }

        bw.write(ans + "\n");

        printAnswer();
    }
}