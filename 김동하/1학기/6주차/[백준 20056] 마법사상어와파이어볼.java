import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();
    public static int n, m, k;
    public static List<Fire>[][] arr;
    public static List<Fire> fires = new ArrayList<>();
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static class Fire {
        int r, c, mass, speed, dir;

        Fire(int r, int c, int mass, int speed, int dir) {
            this.r = r;
            this.c = c;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }
    public static void moveFire() {
        List<Fire>[][] newArr = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                newArr[i][j] = new ArrayList<>();
            }
        }

        for (Fire fire : fires) {
            int x = fire.r;
            int y = fire.c;
            int mass = fire.mass;
            int speed = fire.speed;
            int dir = fire.dir;

            int nx = (x + dx[dir] * (speed % n) + n - 1) % n + 1;
            int ny = (y + dy[dir] * (speed % n) + n - 1) % n + 1;

            newArr[nx][ny].add(new Fire(nx, ny, mass, speed, dir));
        }
        arr = newArr;
    }

    public static void afterMove() {
        fires.clear();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[i][j].isEmpty()) continue;

                if (arr[i][j].size() == 1) {
                    fires.add(arr[i][j].get(0));
                    continue;
                }

                int mSum = 0, sSum = 0, cnt = arr[i][j].size();
                boolean odd = true, even = true;

                for (Fire fire : arr[i][j]) {
                    mSum += fire.mass;
                    sSum += fire.speed;
                    if (fire.dir % 2 == 0) odd = false;
                    else even = false;
                }

                int nm = mSum / 5;
                if (nm == 0) continue;

                int ns = sSum / cnt;
                int[] newDirs = (even || odd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                for (int d : newDirs) {
                    fires.add(new Fire(i, j, nm, ns, d));
                }
            }
        }
    }
    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        arr = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                arr[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());;
            int y = Integer.parseInt(stk.nextToken());
            int mass = Integer.parseInt(stk.nextToken());
            int s = Integer.parseInt(stk.nextToken());
            int d = Integer.parseInt(stk.nextToken());

            arr[x][y].add(new Fire(x, y, mass, s, d));
            fires.add(new Fire(x, y, mass, s, d));
        }
    }


    public static void solution() throws IOException{
        init();
        for (int i = 0; i < k; i++) {
            moveFire();
            afterMove();
        }

        int sum = fires.stream().mapToInt(f -> f.mass).sum();
        System.out.println(sum);
    }

    public static void main(String[] args)throws IOException{
        solution();
    }
}
