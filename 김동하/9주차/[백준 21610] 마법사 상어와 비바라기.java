import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[][] arr;

    public static class Magic{
        int dir, speed;
        public Magic(int dir, int speed){
            this.dir = dir;
            this.speed = speed;
        }
    }

    public static class Node{
        int x,y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static List<Node> cloud;

    public static int[] dx = {0,-1,-1,-1,0,1,1,1};
    public static int[] dy = {-1,-1,0,1,1,1,0,-1};

    public static List<Magic> magic = new ArrayList<>();

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            magic.add(new Magic(a - 1, b));
        }
        cloud = new ArrayList<>();
        cloud.add(new Node(n,1));
        cloud.add(new Node(n,2));
        cloud.add(new Node(n - 1,1));
        cloud.add(new Node(n - 1,2));
    }

    public static boolean[][] cantCloud;



    public static void makeCloud() {
        cloud = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(arr[i][j] >= 2 && !cantCloud[i][j]) {
                    arr[i][j] -= 2;
                    cloud.add(new Node(i,j));
                }
            }
        }
    }

    public static void raining() {
        for(Node cur : cloud) {
            int x = cur.x;
            int y = cur.y;
            arr[x][y]++;
        }
    }

    public static void removeCloud() {
        cantCloud = new boolean[n + 1][n + 1];
        for(Node cur : cloud) {
            int x = cur.x;
            int y = cur.y;
            cantCloud[x][y] = true;
        }
    }

    public static void moveCloud(int idx) {
        int dir = magic.get(idx).dir;
        int speed = magic.get(idx).speed;
        int tx = dx[dir] * speed;
        int ty = dy[dir] * speed;
        List<Node> tmpCloud = new ArrayList<>();
        for(Node cur : cloud) {
            int x = cur.x;
            int y = cur.y;
            int nx = x + tx;
            int ny = y + ty;
            if(nx <= 0) while(nx <= 0) nx += n;
            if(nx > n) while(nx > n) nx -= n;
            if(ny <= 0) while(ny <= 0) ny += n;
            if(ny > n) while(ny > n) ny -= n;
            tmpCloud.add(new Node(nx, ny));
        }
        cloud = tmpCloud;
    }

    public static boolean OOB(int x, int y) {
        if(x > n || x < 1 || y > n || y < 1 || arr[x][y] == 0) return true;
        return false;
    }

    public static int ddx[] = {-1,-1,1,1};
    public static int ddy[] = {-1,1,-1,1};
    public static int count(int x, int y) {
        int ret = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + ddx[i];
            int ny = y + ddy[i];
            if(OOB(nx, ny)) continue;
            ret++;
        }
        return ret;
    }


    public static void copyWaterMagic() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(!cantCloud[i][j]) continue;
                int cnt = count(i, j);
                arr[i][j] += cnt;
            }
        }
    }

    public static void solution() throws IOException {
        init();
        for(int i = 0; i < m; i++) {
            moveCloud(i);
            raining();
            removeCloud();
            copyWaterMagic();
            makeCloud();
        }
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                ans += arr[i][j];
            }
        }
        sb.append(ans);
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
