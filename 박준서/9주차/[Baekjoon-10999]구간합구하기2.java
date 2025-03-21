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

    private static long nextLong() {
        return Long.parseLong(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    // N: 수의 개수
    // M: 변경이 일어나는 횟수
    // K: 구간의 합을 구하는 횟수
    private static int N, M, K;

    static class Segtree {
        long[] elements;
        long[] tree;
        long[] lazy;

        public Segtree(long[] elements) {
            this.elements = elements;
            this.tree = new long[N * 4];
            this.lazy = new long[N * 4];
            init(0, N - 1, 1);
        }

        private long init(int start, int end, int node) {
            // 리프 노드인 경우
            if (start == end)
                return tree[node] = elements[start];

            // 내부 노드인 경우, 두 자식 노드의 합
            int mid = (start + end) / 2;
            return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
        }

        // 지연 전파 처리 메소드
        public void lazy_update(int node, int start, int end) {
            if (lazy[node] == 0)
                return;

            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }

        // 구간 업데이트 메소드
        public void update(int node, int start, int end, int left, int right, long val) {
            lazy_update(node, start, end);

            // 업데이트할 구간과 현재 노드의 구간이 겹치지 않는 경우
            if (right < start || left > end)
                return;

            // 업데이트할 구간이 현재 노드의 구간을 완전히 포함하는 경우
            if (left <= start && end <= right) {
                tree[node] += (end - start + 1) * val;

                if (start != end) {
                    lazy[node * 2] += val;
                    lazy[node * 2 + 1] += val;
                }
                return;
            }

            // 부분적으로 겹치는 경우, 자식 노드로 분할하여 처리
            int mid = (start + end) / 2;
            update(node * 2, start, mid, left, right, val);
            update(node * 2 + 1, mid + 1, end, left, right, val);

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        // 구간 합 조회 메소드
        public long sum(int node, int start, int end, int left, int right) {
            lazy_update(node, start, end);

            if (right < start || left > end)
                return 0;

            if (left <= start && end <= right)
                return tree[node];

            int mid = (start + end) / 2;
            return sum(node * 2, start, mid, left, right) +
                    sum(node * 2 + 1, mid + 1, end, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();
        K = nextInt();

        long[] input = new long[N];
        for (int i = 0; i < N; i++) {
            readLine();
            input[i] = nextLong();
        }

        Segtree seg = new Segtree(input);

        for (int i = 0; i < M + K; i++) {
            readLine();
            int queryType = nextInt();
            int b = nextInt() - 1;
            int c = nextInt() - 1;

            if (queryType == 1) {
                long d = nextLong();
                seg.update(1, 0, N - 1, b, c, d);
            } else {
                bw.write(seg.sum(1, 0, N - 1, b, c) + "\n");
            }
        }

        printAnswer();
    }
}
