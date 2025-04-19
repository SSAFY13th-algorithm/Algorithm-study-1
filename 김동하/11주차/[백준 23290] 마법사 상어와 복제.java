import java.io.*;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int M,S;

    //좌 좌상 상 우상 우 우하 하 좌하
    public static int[] dx = {0,-1,-1,-1,0,1,1,1};
    public static int[] dy = {-1,-1,0,1,1,1,0,-1};

    //상 좌 하 우
    public static int[] sDx = {-1,0,1,0};
    public static int[] sDy = {0,-1,0,1};

    public static int[][] arr;

    public static class Fish{
        int x,y,dir;
        public Fish(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        public Fish(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Fish shark;

    public static List<Fish> fishList;
    public static List<Fish> copiedFishList;


    public static void init() throws IOException{
        arr = new int[5][5];
        fishList = new ArrayList<>();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        M = Integer.parseInt(stk.nextToken());
        S = Integer.parseInt(stk.nextToken());
        copiedFishList = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken()) - 1;
            fishList.add(new Fish(a,b,c));
        }
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                arr[i][j] = 5;
            }
        }
        stk = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(stk.nextToken());
        int b = Integer.parseInt(stk.nextToken());
        shark = new Fish(a,b);
    }

    public static void copyList(List<Fish> copier, List<Fish> copied) {
        copied.clear();
        for(Fish f : copier) {
            copied.add(f);
        }
    }

    public static int[][] fishCnt;

    public static void moveFish() {
        copyList(fishList, copiedFishList);
        List<Fish> moved = new ArrayList<>();
        fishCnt = new int[5][5];
        for(Fish f : fishList) {
            int x = f.x;
            int y = f.y;
            int dir = f.dir;
            boolean flag = false;
            for(int i = 0; i < 8; i++) {
                int ndir = (dir - i + 8) % 8;
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];
                if(OOB(nx,ny) || arr[nx][ny] <= 2 || (shark.x == nx && shark.y == ny)) continue;
                flag = true;
                fishCnt[nx][ny]++;
                moved.add(new Fish(nx,ny,ndir));
                break;
            }
            if(!flag) {
                moved.add(new Fish(x,y,dir));
                fishCnt[x][y]++;
            }
        }
        copyList(moved,fishList);
    }

    public static boolean OOB(int x, int y) {
        if(x > 4 || x < 1 || y > 4 || y < 1) return true;
        return false;
    }

    public static int maxCnt = 0;
    public static List<SharkMove> sharkMoves;

    public static void findMove(Fish n,int cnt, int idx, List<Integer> dirs) {
        if(idx == 3) {
            List<Integer> list = new ArrayList<>();
            for(int a : dirs) {
                list.add(a);
            }
            if(cnt > maxCnt) {
                maxCnt = cnt;
                sharkMoves.clear();
                sharkMoves.add(new SharkMove(list, cnt));
            }
            else if(cnt == maxCnt) {
                sharkMoves.add(new SharkMove(list, cnt));
            }
            return;
        }
        for(int i = 0; i < 4; i++) {
            int nx = n.x + sDx[i];
            int ny = n.y + sDy[i];
            if(OOB(nx,ny)) continue;
            dirs.add(i);
            int tmp = fishCnt[nx][ny];
            fishCnt[nx][ny] = 0;
            findMove(new Fish(nx,ny), cnt + tmp, idx + 1, dirs);
            fishCnt[nx][ny] = tmp;
            dirs.remove(dirs.size() - 1);
        }
    }

    public static void rmFish(int x, int y) {
        List<Fish> tmp = new ArrayList<>();
        for(Fish f : fishList) {
            if(f.x == x && f.y == y) {
                arr[x][y] = 2;
            }
            else tmp.add(f);
        }
        copyList(tmp, fishList);
    }

    public static void moveShark() {
        sharkMoves = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        findMove(new Fish(shark.x,shark.y),0,0, tmp);
        Collections.sort(sharkMoves);
        tmp = sharkMoves.get(0).moveDir;
        int nx = shark.x;
        int ny = shark.y;
        for(int i = 0; i < 3; i++) {
            nx = nx + sDx[tmp.get(i)];
            ny = ny + sDy[tmp.get(i)];
            if(fishCnt[nx][ny] != 0) {
                rmFish(nx,ny);
            }
        }
        shark.x = nx;
        shark.y = ny;
    }

    public static void rmSmell() {
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(arr[i][j] <= 2) arr[i][j]--;
            }
        }
        for(int i = 1; i <= 4; i++) {
            for(int j = 1; j <= 4; j++) {
                if(arr[i][j] == -1) arr[i][j] = 5;
            }
        }
    }

    public static void copyFish() {
        for(Fish f : copiedFishList) {
            fishList.add(f);
        }
    }

    public static class SharkMove implements Comparable<SharkMove>{
        List<Integer> moveDir;
        int cnt;
        public SharkMove(List<Integer> moveDir, int cnt) {
            this.moveDir = moveDir;
            this.cnt = cnt;
        }
        public int compareTo(SharkMove o) {
            if(this.cnt == o.cnt) {
                int a = 0;
                int b = 0;
                int tmp = 100;
                for(int x : this.moveDir) {
                    a += (x + 1) * tmp;
                    tmp /= 10;
                }
                tmp = 100;
                for(int y : o.moveDir) {
                    b += (y + 1) * tmp;
                    tmp /= 10;
                }
                return a - b;
            }
            return o.cnt - this.cnt;
        }
    }


    public static void solution() throws IOException{
        init();
        for(int i = 0; i < S; i++) {
            moveFish();
            maxCnt = -1;
            moveShark();
            rmSmell();
            copyFish();
        }
        sb.append(fishList.size());
    }

    public static void main(String[] args) throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
