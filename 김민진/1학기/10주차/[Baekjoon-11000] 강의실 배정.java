package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5AssigningClassroom {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int cnt;

    private static Classroom[] classes;

    private static Queue<Integer> q;

    private static class Classroom implements Comparable<Classroom> {

        int start;
        int end;

        public Classroom(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Classroom o) {
            int comp = Integer.compare(this.start, o.start);

            if (comp == 0) {
                return Integer.compare(this.end, o.end);
            }

            return comp;
        }

    }

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        cnt = 0;

        classes = new Classroom[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            classes[i] = new Classroom(nextInt(), nextInt());
        }
        Arrays.sort(classes);

        q = new PriorityQueue<>();
    }

    private static void sol() {
        q.offer(classes[0].end);

        for (int i = 1; i < N; i++) {

            if (q.peek() <= classes[i].start) {
                q.poll();
            }

            q.offer(classes[i].end);
        }
        System.out.println(q.size());
    }

}
