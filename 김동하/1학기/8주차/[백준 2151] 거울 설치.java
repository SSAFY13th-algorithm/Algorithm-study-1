import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n;
    public static char[][] arr;

    public static int dx[] = {-1,0,1,0};
    public static int dy[] = {0,1,0,-1};

    public static class Node implements Comparable<Node> {
        int x, y,dist,dir;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int dist, int dir) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
    public static Node[] door = new Node[2];

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n + 1][n + 1];
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= n; j++) {
                arr[i][j] = str.charAt(j - 1);
                if(arr[i][j] == '#') {
                    door[cnt] = new Node(i, j);
                    cnt++;
                }
            }
        }
    }
    public static boolean OOB(int x, int y) {
        if(x > n || x < 1 || y > n || y < 1 || arr[x][y] == '*') return true;
        return false;
    }

    public static void dijkstra() {
        boolean visited[][][] = new boolean[4][n + 1][n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < 4; i++) {
            pq.add(new Node(door[0].x, door[0].y, 0, i));
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            if(x == door[1].x && y == door[1].y) {
                sb.append(cur.dist);
                return;
            }
            int dir = cur.dir;
            int d = cur.dist;
            visited[dir][x][y] = true;
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(OOB(nx, ny) || visited[dir][nx][ny]) continue;
            if(arr[nx][ny] == '!') {
                pq.add(new Node(nx, ny, d + 1, (dir + 1) % 4));
                pq.add(new Node(nx, ny, d + 1, (dir + 3) % 4));
            }
            pq.add(new Node(nx,ny,d,dir));
        }
    }

    public static void solution() throws IOException {
        init();
        dijkstra();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
