import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] parent;

    private static int N, M; // N: 원소의 개수, M: 연산의 개수

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++)
            parent[i] = i;

        for (int i = 0; i < M; i++) {
            readLine();
            int a = nextInt(); // 연산 종류 (0: 합집합, 1: 같은 집합 확인)
            int b = nextInt(); // 첫 번째 원소
            int c = nextInt(); // 두 번째 원소

            if (a == 0) {
                // 합집합 연산 (Union)
                union(b, c);
            } else {
                // 같은 집합 확인 연산 (Find)
                if (find(b) == find(c))
                    sb.append("YES\n"); // 같은 집합에 속함
                else
                    sb.append("NO\n"); // 다른 집합에 속함
            }
        }

        printAnswer();
    }

    // 두 원소가 속한 집합을 합치는 함수 (Union)
    private static void union(int a, int b) {
        // 각 원소의 대표(루트) 노드 찾기
        int aParent = find(a);
        int bParent = find(b);

        // 이미 같은 집합에 속해 있으면 종료
        if (aParent == bParent)
            return;

        // 값이 작은 쪽이 부모가 되도록 설정
        if (aParent < bParent)
            parent[aParent] = bParent;
        else
            parent[bParent] = aParent;
    }

    // 원소가 속한 집합의 대표(루트) 노드를 찾는 함수 (Find)
    private static int find(int now) {
        // 자기 자신이 대표 노드면 반환
        if (now == parent[now])
            return now;
        return parent[now] = find(parent[now]);
    }
}
