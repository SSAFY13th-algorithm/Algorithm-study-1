package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5BridgeConstruction {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N; // lecture buildings
    private static int M; // construction zones
    private static long K; // total stones
    private static long cnt; // number of stones used

    private static long[] stones;
    private static int[] parents;

    private static boolean[] constructionZones;

    private static StringTokenizer tokenize() throws IOException {
        return new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static long nextLong() {
        return Long.parseLong(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        if (init())
            sol();
    }

    private static boolean init() throws IOException {
        st = tokenize();

        N = nextInt();
        M = nextInt();
        K = nextLong();
        cnt = 0;

        st = tokenize();
        stones = new long[N + 1];
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stones[i] = nextLong();
            parents[i] = i;
        }

        constructionZones = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            st = tokenize();
            int a = nextInt();
            int b = nextInt();

            int large = Math.max(a, b);
            int small = Math.min(a, b);

            constructionZones[large == N && small == 1 ? 1 : large] = true;
        }
        // no need to build bridges
        if (M <= 1) {
            System.out.println("YES");
            return false;
        }
        // not enough stones to build bridges
        if (K < M) {
            System.out.println("NO");
            return false;
        }

        grouping();

        return true;
    }

    // 현재의 건물과 다음 건물물 확인하여 공사 구역이 아닌 경우 union
    private static void grouping() {
        for (int cur = 1; cur <= N; cur++) {
            // 다음 건물
            int nxt = (cur + 1) % (N + 1);

            if (nxt == 0)
                nxt = 1;

            // 공사 구역이거나 이미 union된 경우
            if (constructionZones[nxt] || find(nxt) == find(cur))
                continue;

            union(cur, nxt);
        }
    }

    private static void sol() {
        for (int i = 1; i <= N; i++) {
            // 비용이 더 적은 쪽으로 union 했기 때문에
            // 대표자를 찾은 경우 그 건물의 돌을 더해준다
            if (find(i) == i) {
                cnt += stones[i];
            }
        }
        System.out.println(cnt <= K ? "YES" : "NO");
    }

    private static int find(int a) {
        if (parents[a] == a)
            return a;

        return parents[a] = find(parents[a]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // 사용되는 돌의 수가 적은 쪽으로 union → 그리디
        if (stones[rootA] < stones[rootB])
            parents[rootB] = rootA;
        else
            parents[rootA] = rootB;
    }

}