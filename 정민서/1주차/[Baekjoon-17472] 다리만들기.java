import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> edges = new ArrayList<>();
    static int islandCount = 0;
    static int[] parent;

    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        // 지도 입력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 1. 섬에 고유 번호 부여
        labelIslands();

        // 2. 가능한 다리 계산
        findBridges();

        // 3. 최소 신장 트리 계산
        int result = kruskal();
        System.out.println(result);
    }

    static void labelIslands() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandCount++;
                    dfs(i, j, islandCount + 1);
                }
            }
        }
    }

    static void dfs(int x, int y, int id) {
        visited[x][y] = true;
        map[x][y] = id;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny, id);
            }
        }
    }

    static void findBridges() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 1) {
                    int islandId = map[i][j];
                    for (int d = 0; d < 4; d++) {
                        int x = i, y = j, length = 0;
                        while (true) {
                            x += dx[d];
                            y += dy[d];
                            if (x < 0 || x >= N || y < 0 || y >= M) break; // 경계 초과
                            if (map[x][y] == islandId) break; // 같은 섬
                            if (map[x][y] > 1) { // 다른 섬 도달
                                if (length >= 2) {
                                    edges.add(new int[]{islandId, map[x][y], length});
                                }
                                break;
                            }
                            if (map[x][y] == 0) {
                                length++;
                            }
                        }
                    }
                }
            }
        }
    }

    static int kruskal() {
        edges.sort(Comparator.comparingInt(o -> o[2])); // 다리 길이 기준 정렬
        parent = new int[islandCount + 2];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        int totalCost = 0, connectedEdges = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            if (find(u) != find(v)) {
                union(u, v);
                totalCost += cost;
                connectedEdges++;
            }
        }

        // 모든 섬이 연결되지 못한 경우
        return connectedEdges == islandCount - 1 ? totalCost : -1;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) parent[py] = px;
    }
}
