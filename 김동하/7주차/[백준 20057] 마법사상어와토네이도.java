import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();
    public static int n;
    public static int arr[][];

    /* dir : 왼 아 오 위
     * 10 10 5 7 7 2 2 1 1
     */

    public static int dx[] = {0,1,0,-1};
    public static int dy[] = {-1,0,1,0};
    public static int ddx[][] = {
            {-1,1,0,-1,1,-2,2,-1,1},
            {1,1,2,0,0,0,0,-1,-1},
            {-1,1,0,-1,1,-2,2,-1,1},
            {-1,-1,-2,0,0,0,0,1,1}
    };
    public static int ddy[][] = {
            {-1,-1,-2,0,0,0,0,1,1},
            {-1,1,0,-1,1,-2,2,-1,1},
            {1,1,2,0,0,0,0,-1,-1},
            {-1,1,0,-1,1,-2,2,-1,1}
    };
    public static class Node{
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Node torn;

    public static int dir;
    public static int turnCnt;
    public static int moveCnt;
    public static int moveSize;

    public static int[] perc = {10,10,5,7,7,2,2,1,1};

    public static void init()throws IOException{
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        torn = new Node((n / 2) + 1, (n / 2) + 1);
        moveCnt = 0;
        turnCnt = 0;
        moveSize = 1;
        dir = 0;
    }

    public static int ans = 0;

    public static boolean OOB(int x, int y) {
        if(x > n || x < 1 || y > n || y < 1) return true;
        return false;
    }

    public static void magic() {
        int sum = 0;
        int sand = arr[torn.x][torn.y];
        if(sand == 0) return;
        for(int i = 0; i < 9; i++) {
            int nx = torn.x + ddx[dir][i];
            int ny = torn.y + ddy[dir][i];
            int perSand = (sand * perc[i]) / 100;
            sum += perSand;
            if(OOB(nx,ny)) {
                ans += perSand;
                continue;
            }
            arr[nx][ny] += perSand;
        }
        int mod = sand - sum;
        arr[torn.x][torn.y] = 0;
        int nx = torn.x + dx[dir];
        int ny = torn.y + dy[dir];
        if(OOB(nx,ny)) ans += mod;
        else arr[nx][ny] += mod;
    }

    public static void solution()throws IOException{
        init();
        while(moveSize < n) {
            for(int i = 0; i < moveSize; i++) {
                torn.x += dx[dir];
                torn.y += dy[dir];
                magic();
            }
            dir = (dir + 1) % 4;
            turnCnt++;
            if(turnCnt == 2) {
                turnCnt = 0;
                moveSize++;
            }
        }
        moveSize--;
        for(int i = 0; i < moveSize; i++) {
            torn.x += dx[dir];
            torn.y += dy[dir];
            magic();
        }
        sb.append(ans);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args)throws IOException{
        solution();
    }

}
