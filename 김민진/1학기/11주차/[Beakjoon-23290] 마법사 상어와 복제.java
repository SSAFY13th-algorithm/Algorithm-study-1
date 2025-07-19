package week11;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q1WizardSharkAndReplication {

    // ←, ↖, ↑, ↗, ↘, ↓, ↙
    private static final int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
    private static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    private static final int[] ds = { 2, 0, 6, 4 };

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int M; // fish
    private static int S; // spells
    private static int max; // dfs..

    private static int[][] smell;
    private static boolean[][] visited;

    private static Point shark;
    private static Point sharkToMove;
    private static List<Point> posOfEatenFish;
    private static List<Point> tmp;
    private static Point[][] pos;

    private static List<Integer>[][] fishPrev;
    private static List<Integer>[][] fishCur;

    private static StringTokenizer tokenize() throws IOException {
        return new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = tokenize();

        M = nextInt();
        S = nextInt();

        // 여기서 Point 객체 꺼내 쓸 것
        pos = new Point[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pos[i][j] = new Point(i, j);
            }
        }

        fishPrev = new List[4][4];
        fishCur = new List[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishPrev[i][j] = new ArrayList<>();
                fishCur[i][j] = new ArrayList<>();
            }
        }

        while (M-- > 0) {
            st = tokenize();

            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int d = nextInt() - 1;

            fishCur[x][y].add(d);
        }
        st = tokenize();
        shark = new Point(nextInt() - 1, nextInt() - 1);
        sharkToMove = new Point(-1, -1);

        smell = new int[4][4];
        visited = new boolean[4][4];
        // 3칸만 이동하므로
        posOfEatenFish = new ArrayList<>();
        tmp = new ArrayList<>();
    }

    private static void sol() {
        while (S-- > 0) {
            copyFishInfo();
            moveFish();
            moveShark();
            updateSmellInfo();
            replicateFish();
        }
        printAns();
    }

    /**
     * 현재의 물고기 정보를 prev에 저장
     * 추후 복제를 진행하기 위한 과정
     */
    private static void copyFishInfo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishPrev[i][j].clear();
                fishPrev[i][j].addAll(fishCur[i][j]);
                fishCur[i][j].clear();
            }
        }
    }

    private static void moveFish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int fish : fishPrev[i][j]) {
                    boolean moved = false;

                    for (int k = 8; k > 0; k--) {
                        int d = (fish + k) % 8;
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        // 격자를 벗어나거나 물고기 냄새가 남아있거나 상어의 위치인 경우 넘어가기기
                        if (OOB(nx, ny) || smell[nx][ny] != 0 || (shark.x == nx && shark.y == ny))
                            continue;

                        // 이동 후 반복 종료
                        fishCur[nx][ny].add(d);
                        moved = true;
                        break;
                    }
                    // 움직이지 못 한 경우 자리 유지
                    if (!moved) {
                        fishCur[i][j].add(fish);
                    }
                }
            }
        }
    }

    private static void clear() {
        max = 0;
        // 못 먹을 경우를 대비
        sharkToMove.x = -1;

        for (int i = 0; i < 4; i++) {
            Arrays.fill(visited[i], false);
        }

        tmp.clear();
    }

    private static void moveShark() {
        clear();

        dfs(0, shark.x, shark.y, 0, tmp);

        shark.x = sharkToMove.x;
        shark.y = sharkToMove.y;
        // 물고기 없애기
        for (int i = 0; i < posOfEatenFish.size(); i++) {
            int x = posOfEatenFish.get(i).x;
            int y = posOfEatenFish.get(i).y;

            fishCur[x][y].clear();
        }
    }

    private static void dfs(int depth, int x, int y, int eatenFish, List<Point> tmp) {
        if (depth >= 3) {
            // 더 많은 물고기를 먹을 수 있으면 업데이트
            if (eatenFish > max) {
                max = eatenFish;
                posOfEatenFish.clear();
                posOfEatenFish.addAll(tmp);
                sharkToMove.x = x;
                sharkToMove.y = y;

            } else if (eatenFish == 0 && sharkToMove.x == -1) {
                // 물고기 먹지 못 한 경우도 저장
                // -1 조건을 거는 이유는 가장 처음 도달한 위치가 사전 순으로 가장 빠르기 때문
                sharkToMove.x = x;
                sharkToMove.y = y;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[ds[d]];
            int ny = y + dy[ds[d]];

            if (OOB(nx, ny))
                continue;

            int fish = 0;
            boolean newVisited = false;
            if (!visited[nx][ny]) {
                fish = fishCur[nx][ny].size();

                if (fish > 0)
                    tmp.add(pos[nx][ny]);

                visited[nx][ny] = true;
                newVisited = true;
            }

            dfs(depth + 1, nx, ny, eatenFish + fish, tmp);
            if (newVisited) {
                visited[nx][ny] = false;
                if (fish > 0)
                    tmp.remove(tmp.size() - 1);
            }
        }
    }

    private static void updateSmellInfo() {
        // decrease
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0) {
                    smell[i][j]--;
                }
            }
        }
        // increase
        for (Point p : posOfEatenFish) {
            smell[p.x][p.y] = 2;
        }
    }

    private static void replicateFish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishCur[i][j].addAll(fishPrev[i][j]);
            }
        }
    }

    private static void printAns() {
        int a = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a += fishCur[i][j].size();
            }
        }
        System.out.println(a);
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || 4 <= x || y < 0 || 4 <= y;
    }

}