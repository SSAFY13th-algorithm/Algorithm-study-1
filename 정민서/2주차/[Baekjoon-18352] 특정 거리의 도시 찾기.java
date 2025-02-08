import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    //도시의 개수
    public static int n;

    //도로의 개수
    public static int m;

    //거리정보
    public static int k;

    //출발 도시의 번호
    public static int x;

    public static List<List<Integer>> graph = new ArrayList<>();
    public static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();
        bfs();
        printResult();

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        //도로 정보 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
        }
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(new int[]{x, 0});
        visited[x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int city = cur[0];
            int dist = cur[1];

            //목표 거리에 도달 한 경우 종료
            if (dist == k) {
                result.add(city);
                continue;
            }

            for (int next : graph.get(city)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, dist + 1});
                }
            }
        }
    }

    static void printResult() {
        if (result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result);
            for (int city : result) {
                System.out.println(city);
            }
        }
    }
}