import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int bucket[][];
    static List<int[]> cloudList;
    static int[] input;
    static int[][] command;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        init();
        excute();
        System.out.println(findAnswer());

    }

    private static void init() throws IOException {
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        cloudList = new ArrayList<>();
        bucket = new int[N][N];
        command = new int[M][2];
        for (int i = 0; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++)
                bucket[i][j] = input[j];
        }


        for (int i = 0; i < M; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 2; j++)
                command[i][j] = input[j];
        }
        cloudList.add(new int[] {0, N - 1});
        cloudList.add(new int[] {1, N - 1});
        cloudList.add(new int[] {0, N - 2});
        cloudList.add(new int[] {1, N - 2});

    }

    static void excute() {
        for (int i = 0; i < M; i++) {

            moveCloud(command[i][0] - 1, command[i][1]);

            rain();

            copyWater();

            makeCloud();

        }


    }

    // dir-1
    static void moveCloud(int dir, int day) {
        List<int[]> tempList = new ArrayList<>();
        for (int xy[] : cloudList) {
            int nx = xy[0] + dx[dir] * day;
            int ny = xy[1] + dy[dir] * day;
            nx = (nx + 50 * N) % N;
            ny = (ny + 50 * N) % N;
            tempList.add(new int[] {nx, ny});
        }
        cloudList = tempList;
    }

    static void rain() {
        for (int xy[] : cloudList) {
            int x = xy[0];
            int y = xy[1];
            bucket[y][x] += 1;
        }
    }

    static void copyWater() {
        for (int xy[] : cloudList) {
            int x = xy[0];
            int y = xy[1];
            int cnt = 0;
            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0)
                    continue;
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;

                if (bucket[ny][nx] != 0)
                    cnt++;

            }
            bucket[y][x] += cnt;
        }
    }

    static void makeCloud() {
        List<int[]> tempList = new ArrayList<>();
        int[][] cloudArr = new int[N][N];
        for (int xy[] : cloudList) {
            cloudArr[xy[1]][xy[0]] = 1;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (bucket[i][j] >= 2 && cloudArr[i][j] == 0) {
                    tempList.add(new int[] {j, i});
                    bucket[i][j] -= 2;
                }
            }
        }
        cloudList = tempList;
    }

    static int findAnswer() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += bucket[i][j];
            }
        }
        return sum;
    }


}
