package week14;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.Queue;

public class Q1SharkMiddleSchool {

    private static final int RAINBOW = 0;
    private static final int BLACK = -1;
    private static final int EMPTY = -2;

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int ans;

    private static int[][] map;
    private static boolean[][] visited; // for every cycle of 1 round
    private static Point[][] pos;     // 객체 재활용
    private static Queue<Point> q;
    private static Queue<Group> pq; // reference Blocks

    private static class Group implements Comparable<Group> {

        int x;
        int y;
        int rainbow;
        List<Point> group;

        public Group(Point p, int rainbow, List<Point> group) {
            this.x = p.x;
            this.y = p.y;
            this.rainbow = rainbow;
            this.group = group;
        }

        @Override
        public int compareTo(Group o) {
            if (this.group.size() == o.group.size()) {
                if (this.rainbow == o.rainbow) {
                    if (this.x == o.x) {
                        return Integer.compare(o.y, this.y);
                    }
                    return Integer.compare(o.x, this.x);
                }
                return Integer.compare(o.rainbow, this.rainbow);
            }
            return Integer.compare(o.group.size(), this.group.size());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][N];
        pos = new Point[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                pos[i][j] = new Point(i, j);
            }
        }
        q = new ArrayDeque<>();
        pq = new PriorityQueue<>();
    }

    private static void sol() {
        while (!findBiggestBlock()) {
            removeBlockGroup();
            gravitate();
            rotateMap();
            gravitate();
        }
        System.out.println(ans);
    }

    private static boolean findBiggestBlock() {
        clear();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // not visited && normal block
                if (!visited[i][j] && map[i][j] > 0) {
                    bfs(i, j);
                }
            }
        }
        return pq.isEmpty();
    }

    private static void bfs(int x, int y) {
        List<Point> group = new ArrayList<>();
        boolean[][] visitedRainbow = new boolean[N][N]; // 무지개 블록 방문 체크용

        q.clear();
        q.offer(pos[x][y]);
        group.add(pos[x][y]);
        visited[x][y] = true;

        int normalB = map[x][y];
        int rainbow = 0;
        Point refTmp = pos[x][y];

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 범위 밖 || 검은색 블록 || 다른 색상의 일반 블록
                if (OOB(nx, ny) || map[nx][ny] == BLACK
                        || (map[nx][ny] != RAINBOW && map[nx][ny] != normalB)) {
                    continue;
                }

                // 무지개 블록
                if (map[nx][ny] == RAINBOW) {
                    if (visitedRainbow[nx][ny]) {
                        continue; // 현재 BFS에서 이미 방문한 무지개 블록
                    }
                    visitedRainbow[nx][ny] = true;
                    rainbow++;
                } // 일반 블록
                else {
                    if (visited[nx][ny]) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    // 기준 블록 갱신
                    if (refTmp.x > nx) {
                        refTmp = pos[nx][ny];
                    } else if (refTmp.x == nx && refTmp.y > ny) {
                        refTmp = pos[nx][ny];
                    }
                }
                q.offer(pos[nx][ny]);
                group.add(pos[nx][ny]);
            }
        }
        if (group.size() >= 2) { // 블록 그룹 크기: 2 이상인 것만 해당
            pq.offer(new Group(refTmp, rainbow, group));
        }
    }

    private static void removeBlockGroup() {
        Group group = pq.poll();

        int size = group.group.size();
        ans += size * size;

        for (Point p : group.group) {
            map[p.x][p.y] = EMPTY;
        }
    }

    private static void gravitate() {
        for (int j = 0; j < N; j++) {
            int bottom = N - 1;
            for (int i = N - 1; i >= 0; i--) { // 각 열의 가장 아래 칸부터 시작
                if (map[i][j] == EMPTY) {
                    continue;
                }

                if (map[i][j] == BLACK) { // 바닥 업데이트
                    bottom = i - 1;
                } else {
                    map[bottom][j] = map[i][j];

                    if (bottom != i) {
                        map[i][j] = EMPTY;
                    }
                    bottom--;
                }
            }
        }
    }

    private static void rotateMap() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[N - j - 1][i] = map[i][j];
            }
        }
        map = tmp;
    }

    private static void clear() {
        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }
        pq.clear();
    }

    private static boolean OOB(int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }

}
