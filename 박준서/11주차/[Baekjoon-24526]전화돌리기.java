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

    // 그래프를 표현하는 ArrayList (각 원소는 Set<Integer>)
    private static ArrayList<Set<Integer>> graph = new ArrayList<>();
    // 각 노드를 가리키는 간선의 수를 저장하는 배열
    private static int[] pointed;
    // BFS를 위한 큐
    private static Queue<Integer> q;
    // N: 노드의 수, M: 간선의 수, ans: 정답(위상 정렬된 노드의 수)
    private static int N, M, ans;

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();

        for (int i = 0; i < N; i++)
            graph.add(new TreeSet<>());
        pointed = new int[N];
        q = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            readLine();
            int a = nextInt() - 1, b = nextInt() - 1;
            // 이미 존재하는 간선이면 무시
            if (graph.get(b).contains(a))
                continue;
            graph.get(b).add(a);
            pointed[a]++;
        }

        // 진입 차수가 0인 노드를 큐에 추가
        for (int i = 0; i < N; i++)
            if (pointed[i] == 0)
                q.add(i);

        // 위상 정렬 수행
        while (!q.isEmpty()) {
            int now = q.poll();
            ans++;

            // 현재 노드와 연결된 노드들의 진입 차수를 감소시키고,
            // 진입 차수가 0이 되면 큐에 추가
            for (int it : graph.get(now))
                if (--pointed[it] == 0)
                    q.add(it);
        }

        bw.write(ans + "\n");

        printAnswer();
    }
}
