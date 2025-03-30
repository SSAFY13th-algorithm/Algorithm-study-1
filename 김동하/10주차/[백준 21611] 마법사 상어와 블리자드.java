import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int ans;

    public static class Node{
        int x,y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] arr;

    public static Node shark;

    /*
     * 상 : 0
     * 하 : 1
     * 좌 : 2
     * 우 : 3
     * */
    public static int dx[] = {-1,1,0,0};
    public static int dy[] = {0,0,-1,1};

    public static void init() throws IOException{
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        shark = new Node((n + 1) / 2, (n + 1) / 2);
        arr = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        ans = 0;
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(stk.nextToken()) - 1;
            int s = Integer.parseInt(stk.nextToken());
            doMagic(d, s);
        }
    }

    public static boolean OOB(int x, int y) {
        if(x > n || x < 1 || y > n || y < 1) return true;
        return false;
    }

    public static void blizard(int d, int s) {
        for(int i = 1; i <= s; i++) {
            int nx = shark.x + dx[d] * i;
            int ny = shark.y + dy[d] * i;
            if(OOB(nx,ny)) break;
            arr[nx][ny] = 0;
        }
    }

    public static int changeDir(int d) {
        if(d == 0) return 2;
        else if(d == 1) return 3;
        else if(d == 2) return 1;
        else return 0;
    }

    public static Node findNextNotZero(int x, int y, int moveCnt, int siz, int dir, int changeDirCnt) {
        if(arr[x][y] != 0) return new Node(x,y);
        while(siz < n) {
            x = x + dx[dir];
            y = y + dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(arr[x][y] != 0) return new Node(x,y);
        }
        for(int i = 0; i < n; i++) {
            x = x + dx[dir];
            y = y + dy[dir];
            if(arr[x][y] != 0) return new Node(x,y);
        }
        return new Node(-1,-1);
    }

    public static void moveBids() {
        int x = shark.x;
        int y = shark.y - 1;
        int moveCnt = 0;
        int siz = 1;
        int dir = 1;
        int changeDirCnt = 1;
        while(siz < n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(arr[x][y] != 0) {
                x = nx;
                y = ny;
                continue;
            }
            Node next = findNextNotZero(nx, ny, moveCnt, siz, dir, changeDirCnt);
            if(next.x == -1) {
                return;
            }
            arr[x][y] = arr[next.x][next.y];
            arr[next.x][next.y] = 0;
            x = nx;
            y = ny;
        }
        for(int i = 0; i < n; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            moveCnt++;
            if(arr[x][y] != 0) {
                x = nx;
                y = ny;
                continue;
            }
            int tx = nx;
            int ty = ny;
            boolean flag = false;
            for(int j = moveCnt; j < siz; j++) {
                if(arr[nx][ny] != 0) {
                    arr[x][y] = arr[nx][ny];
                    arr[nx][ny] = 0;
                    x = tx;
                    y = ty;
                    flag = true;
                    break;
                }
                nx += dx[dir];
                ny += dy[dir];
            }
            if(!flag) return;
        }
    }

    public static void explodeBids(List<Node> list) {
        for(Node node : list) {
            arr[node.x][node.y] = 0;
        }
    }

    public static int explode() {
        int x = shark.x;
        int y = shark.y - 1;
        int moveCnt = 0;
        int siz = 1;
        int dir = 1;
        int changeDirCnt = 1;
        List<Node> list = new ArrayList<>();
        list.add(new Node(x,y));
        int prev = arr[x][y];
        int ret = 0;
        while(siz < n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(arr[nx][ny] == 0) {
                if(list.size() > 3) {
                    ret += list.size() * prev;
                    explodeBids(list);
                    return ret;
                }
            }
            if(arr[nx][ny] != prev) {
                if(list.size() > 3) {
                    ret += list.size() * prev;
                    explodeBids(list);
                }
                list.clear();
                list.add(new Node(nx,ny));
                prev = arr[nx][ny];
            }else {
                list.add(new Node(nx,ny));
            }
            x = nx;
            y = ny;
        }
        for(int i = 0; i < n; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(arr[nx][ny] == 0) {
                if(list.size() > 3) {
                    ret += list.size() * prev;
                    explodeBids(list);
                    return ret;
                }
            }
            if(arr[nx][ny] != prev) {
                if(list.size() > 3) {
                    ret += list.size() * prev;
                    explodeBids(list);
                }
                list.clear();
                list.add(new Node(nx,ny));
                prev = arr[nx][ny];
            }else {
                list.add(new Node(nx,ny));
            }
            x = nx;
            y = ny;
        }
        return ret;
    }

    public static List<Node> group;

    public static void grouping() {
        group = new ArrayList<>();
        int x = shark.x;
        int y = shark.y - 1;
        int moveCnt = 0;
        int siz = 1;
        int dir = 1;
        int changeDirCnt = 1;
        int prev = arr[x][y];
        if(prev == 0) return;
        int cnt = 1;
        while(siz < n) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(arr[nx][ny] == 0) {
                group.add(new Node(cnt,prev));
                return;
            }
            if(arr[nx][ny] == prev) {
                cnt++;
            }
            else {
                group.add(new Node(cnt,prev));
                cnt = 1;
                prev = arr[nx][ny];
            }
            x = nx;
            y = ny;

        }
        for(int i = 0; i < n; i++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            moveCnt++;
            if(arr[nx][ny] == 0) {
                group.add(new Node(cnt,prev));
                return;
            }
            if(arr[nx][ny] == prev) {
                cnt++;

            }
            else {
                group.add(new Node(cnt,prev));
                cnt = 1;
                prev = arr[nx][ny];
            }
            x = nx;
            y = ny;
        }
    }
    public static void addGroupToArr() {
        int[][] tmp = new int[n + 1][n + 1];
        int x = shark.x;
        int y = shark.y - 1;
        int moveCnt = 0;
        int siz = 1;
        int dir = 1;
        int changeDirCnt = 1;
        for(Node cur : group) {
            int a = cur.x;
            int b = cur.y;
            tmp[x][y] = a;
            x += dx[dir];
            y += dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(OOB(x,y)) break;
            tmp[x][y] = b;

            x += dx[dir];
            y += dy[dir];
            moveCnt++;
            if(moveCnt == siz) {
                dir = changeDir(dir);
                changeDirCnt++;
                moveCnt = 0;
            }
            if(changeDirCnt == 2) {
                siz++;
                changeDirCnt = 0;
            }
            if(OOB(x,y)) break;
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                arr[i][j] = tmp[i][j];
            }
        }
    }


    public static void doMagic(int d, int s) {
        blizard(d, s);

        moveBids();
        int cnt = -1;
        while(cnt != 0) {
            cnt = explode();
            ans += cnt;
            moveBids();
        }
        grouping();
        addGroupToArr();
    }

    public static void solution() throws IOException{
        init();
        sb.append(ans);
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
