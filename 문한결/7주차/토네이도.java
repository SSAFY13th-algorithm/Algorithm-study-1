import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 토네이도 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer = 0, N, curDistance = 0, curDir = 0, stackConvert = 0, convertDis = 1;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int curX, curY, sum;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        curX = curY = N / 2;
        arr = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
        execute();
        System.out.println(answer);
    }

    static void execute() {
        while (!(curX == 0 && curY == 0)) {
            moveAllSand();
            moveTornado();
        }
    }

    static void moveTornado() {
        curX += dx[curDir];
        curY += dy[curDir];
        curDistance++;

        if (curDistance == convertDis) {
            stackConvert++;
            curDir = (curDir + 1) % 4;
            curDistance = 0;
        }
        if (stackConvert == 2) {
            convertDis++;
            stackConvert = 0;
        }
    }

    static void moveAllSand() {
        int upDir = (curDir + 1) % 4, downDir = (curDir + 3) % 4;
        int[] udDirs = {upDir, downDir};
        sum = 0;

        for (int i = 0; i < 3; i++) {
            for (int dir : udDirs) {
                int nx = curX + i * dx[curDir] + dx[dir];
                int ny = curY + i * dy[curDir] + dy[dir];
                moveSand(i, 0, nx, ny);
                if (i == 1) moveSand(i, 1, nx + dx[dir], ny + dy[dir]);
            }
        }
        moveSand(3, 0, curX + 3 * dx[curDir], curY + 3 * dy[curDir]);
        int nextX = curX + 2 * dx[curDir], nextY = curY + 2 * dy[curDir];
        int targetX = curX + dx[curDir], targetY = curY + dy[curDir];

        if (inBounds(nextY, nextX)) {
            arr[nextY][nextX] += (arr[targetY][targetX] - sum);
        } else {
            answer += (arr[targetY][targetX] - sum);
        }
        arr[targetY][targetX] = 0;
    }

    static void moveSand(int disX, int disY, int x, int y) {
        int priorX = curX + dx[curDir];
        int priorY = curY + dy[curDir];
        double ratio = getRatio(disX, disY);
        int sandMoved = (int) (arr[priorY][priorX] * ratio);

        if (inBounds(y, x)) {
            arr[y][x] += sandMoved;
        } else {
            answer += sandMoved;
        }
        sum += sandMoved;
    }

    static double getRatio(int disX, int disY) {
        switch (disX) {
            case 0:
                return 0.01;
            case 1:
                return disY == 0 ? 0.07 : 0.02;
            case 2:
                return 0.1;
            case 3:
                return 0.05;
            default:
                return 0;
        }
    }

    static boolean inBounds(int y, int x) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
