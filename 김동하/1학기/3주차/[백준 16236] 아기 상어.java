import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int n;
    public static int arr[][] = new int[21][21];
    public static int sharkX, sharkY, sharkSize = 2, eatCnt;
    public static List<Fish> eatList = new ArrayList<>();
    public static int ans = 0;
    public static int dx[] = {1, 0, -1, 0};
    public static int dy[] = {0, 1, 0, -1};

    public static class Fish {

        int x, y;
        int dist;

        public Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static boolean OOB(int x, int y) {
        if (x > n || x < 1 || y > n || y < 1 || arr[x][y] == 9
                || arr[x][y] > sharkSize) {
            return true;
        }
        return false;
    }

    public static class FishComparator implements Comparator<Fish> {

        @Override
        public int compare(Fish f1, Fish f2) {
            if (f1.dist != f2.dist) {
                return Integer.compare(f1.dist, f2.dist);
            }
            if (f1.x != f2.x) {
                return Integer.compare(f1.x, f2.x);
            }
            return Integer.compare(f1.y, f2.y);
        }
    }

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < n; j++) {
                arr[i][j + 1] = in[j];
                if (in[j] == 9) {
                    sharkX = i;
                    sharkY = j + 1;
                }
            }
        }
    }

    public static void findFish() {
        Queue<Fish> q = new ArrayDeque<>();
        q.add(new Fish(sharkX, sharkY, 0));
        boolean visited[][] = new boolean[n + 1][n + 1];
        visited[sharkX][sharkY] = true;
        while (!q.isEmpty()) {
            Fish cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (OOB(nx, ny) || visited[nx][ny]) {
                    continue;
                }
                if (arr[nx][ny] != 0 && arr[nx][ny] < sharkSize) {
                    eatList.add(new Fish(nx, ny, dist + 1));
                }
                visited[nx][ny] = true;
                q.add(new Fish(nx, ny, dist + 1));
            }
        }
    }

    public static void levelUp() {
        eatCnt++;
        if(eatCnt == sharkSize) {
            sharkSize++;
            eatCnt = 0;
        }
    }
    public static boolean eatFish() {
        if (eatList.isEmpty()) {
            return false;
        }
        Collections.sort(eatList, new FishComparator());
        Fish fish = eatList.get(0);
        int x = fish.x;
        int y = fish.y;
        int dist = fish.dist;
        arr[x][y] = 9;
        arr[sharkX][sharkY] = 0;
        sharkX = x;
        sharkY = y;
        levelUp();
        ans += dist;
        eatList.clear();
        return true;
    }

    public static void solution() throws IOException{
        init();
        StringBuilder sb = new StringBuilder();
        while(true) {
            findFish();
            boolean flag = eatFish();
            if(!flag) {
                bw.write(sb.append(ans).toString());
                bw.flush();
                bw.close();
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
