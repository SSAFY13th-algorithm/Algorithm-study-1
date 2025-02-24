import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int n,m,k;

    public static int dx[] = {-1,1,0,0};
    public static int dy[] = {0,0,-1,1};

    public static Shark sharks[];

    public static Smell[][] smell;
    public static class Smell{
        int time;
        int shark;
        public Smell(int shark, int time) {
            this.time = time;
            this.shark = shark;
        }
    }
    public static List<Integer>[][] arr;
    public static class Shark{
        int curDir;
        int dir[][];
        boolean isAlive;
        Node node;
        Shark() {
            this.isAlive = true;
        }
    }

    public static void init() throws IOException {
        int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = in[0];
        m = in[1];
        k = in[2];
        arr = new ArrayList[n + 1][n + 1];
        smell = new Smell[n + 1][n + 1];
        sharks = new Shark[m + 1];
        for(int i = 1; i <= m; i++) {
            sharks[i] = new Shark();
        }
        for(int i = 1; i <= n; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 1; j <= n; j++) {
                smell[i][j] = new Smell(-1,-1);
                arr[i][j] = new ArrayList<>();
                if(in[j - 1] != 0) {
                    arr[i][j].add(in[j - 1]);
                    sharks[in[j - 1]].node = new Node(i,j);
                }
            }
        }
        in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 1; i <= m; i++) {
            sharks[i].curDir =  in[i - 1] - 1;
        }
        for(int i = 1; i <= m; i++) {
            int dir[][] = new int[4][4];
            for(int j = 0; j < 4; j++) {
                dir[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int t = 0; t < 4; t++) {
                    dir[j][t]--;
                }
            }
            sharks[i].dir = dir;
        }
    }
    public static void smelling() {
        for(int i = 1; i <= m; i++) {
            if(!sharks[i].isAlive) continue;
            smell[sharks[i].node.x][sharks[i].node.y] = new Smell(i,k);
        }
    }
    public static class Node{
        int x,y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void decreaseSmell() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(smell[i][j].time > 0) smell[i][j].time--;
                if(smell[i][j].time == 0) smell[i][j] = new Smell(-1,-1);
            }
        }
    }

    public static void moveShark(int idx) {
        Shark s = sharks[idx];
        Node node = null;
        int d = 0;
        int curDir = s.curDir;
        for(int i = 0; i < 4; i++) {
            if(node != null) break;
            int nDir = s.dir[curDir][i];
            int nx = s.node.x + dx[nDir];
            int ny = s.node.y + dy[nDir];
            if(nx > n || nx < 1 || ny > n || ny < 1) continue;
            if(smell[nx][ny].shark != -1) continue;
            d = nDir;
            node = new Node(nx,ny);
        }
        if(node == null) {
            for(int i = 0; i < 4; i++) {
                if(node != null) break;
                int nDir = s.dir[curDir][i];
                int nx = s.node.x + dx[nDir];
                int ny = s.node.y + dy[nDir];
                if(nx > n || nx < 1 || ny > n || ny < 1) continue;
                if(smell[nx][ny].shark != idx) continue;
                node = new Node(nx,ny);
                d = nDir;
            }
        }
        arr[node.x][node.y].add(idx);
        for(int i = 0; i < arr[s.node.x][s.node.y].size(); i++) {
            if(arr[s.node.x][s.node.y].get(i) == idx) {
                arr[s.node.x][s.node.y].remove(i);
            }
        }
        sharks[idx].curDir = d;
        sharks[idx].node = node;
    }

    public static void moveAll() {
        for(int i = 1; i <= m; i++) {
            if(!sharks[i].isAlive) continue;
            moveShark(i);
        }
    }

    public static void killOthers() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(arr[i][j].size() > 1) {
                    List<Integer> l = new ArrayList<>();
                    Collections.sort(arr[i][j]);
                    l.add(arr[i][j].get(0));
                    for(int t = 1; t < arr[i][j].size(); t++) {
                        int idx = arr[i][j].get(t);
                        sharks[idx].isAlive = false;
                    }
                    arr[i][j] = l;
                }
            }
        }
    }
    public static boolean isEnd() {
        for(int i = 2; i <= m; i++) {
            if(sharks[i].isAlive) return false;
        }
        return true;
    }

    public static void solution() throws IOException{
        init();
        int cnt = 0;
        while(!isEnd()) {
            cnt++;
            if(cnt > 1000) {
                cnt = -1;
                break;
            }
            smelling();
            moveAll();
            killOthers();
            decreaseSmell();
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
