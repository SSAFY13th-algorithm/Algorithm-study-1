import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st = new StringTokenizer("");

    private static void readLine() throws Exception {
        st = new StringTokenizer(br.readLine());
    }

    static String nextToken() throws Exception {
        while (!st.hasMoreTokens())
            readLine();
        return st.nextToken();
    }

    private static int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    private static void bwEnd() throws Exception {
        bw.flush();
        bw.close();
    }

    private static final int[] dx = { 0, 0, 1, -1 };
    private static final int[] dy = { 1, -1, 0, 0 };

    private static int N, ans; // N: 그리드 크기, ans: 총 점수
    private static int[][] matrix; // 블록 정보 (-1: 검은색, 0: 무지개, 1~: 일반)
    private static boolean[][] visited; // BFS 방문 체크
    private static ArrayList<Group> groups; // 찾은 블록 그룹 리스트

    private static class Group {
        ArrayList<int[]> blocks; // 그룹에 속한 블록 좌표들
        int rainbowCnt; // 무지개 블록 개수

        public Group(ArrayList<int[]> blocks) {
            this.blocks = blocks;
        }
    }

    public static void main(String args[]) throws Exception {
        N = nextInt();
        nextInt(); // 사용되지 않는 입력
        matrix = new int[N][N];
        groups = new ArrayList<>();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                matrix[i][j] = nextInt();

        // 메인 루프: 더 이상 그룹이 없을 때까지 반복
        while (true) {
            groups.clear();

            // 1. 모든 일반 블록에 대해 그룹 찾기
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (matrix[i][j] > 0) // 일반 블록에서만 시작
                        find(i, j);

            if (groups.isEmpty())
                break; // 그룹 없으면 종료

            // 2. 찾은 그룹 정렬 (우선순위 기준)
            sort();

            // 3. 최우선 그룹 제거 및 점수 획득
            remove(groups.get(0));

            // 4. 중력 적용
            drop();
            // 5. 회전
            turn();
            // 6. 중력 적용
            drop();
        }

        bw.write(ans + "\n");
        bwEnd();
    }

    private static void find(int y, int x) {
        ArrayList<int[]> tmp = new ArrayList<>();
        Queue<int[]> q = new ArrayDeque<>();
        tmp.add(new int[] { y, x });
        q.add(new int[] { y, x });
        int nowColor = matrix[y][x]; // 현재 블록 색상
        visited = new boolean[N][N]; // 매 탐색마다 새 방문 배열
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int k = 0; k < 4; k++) {
                int ny = now[0] + dy[k];
                int nx = now[1] + dx[k];
                // 범위 밖이거나, 같은 색/무지개가 아니거나, 방문했으면 스킵
                if (OOB(ny, nx) || !(matrix[ny][nx] == 0 || matrix[ny][nx] == nowColor) || visited[ny][nx])
                    continue;
                visited[ny][nx] = true;
                q.add(new int[] { ny, nx });
                tmp.add(new int[] { ny, nx });
            }
        }
        // 그룹 크기가 2 이상이면 추가
        if (tmp.size() >= 2)
            groups.add(new Group(tmp));
    }

    private static void sort() {
        // 각 그룹 내부 정렬 (기준 블록 찾기 위해)
        for (Group row : groups) {
            // 1. 일반 블록 우선, 2. 행 작은 순, 3. 열 작은 순
            row.blocks.sort((a, b) -> {
                if (matrix[a[0]][a[1]] == matrix[b[0]][b[1]]) // 둘 다 일반/무지개
                    if (a[0] == b[0])
                        return a[1] - b[1]; // 열 오름차순
                    else
                        return a[0] - b[0]; // 행 오름차순
                return matrix[b[0]][b[1]] - matrix[a[0]][a[1]]; // 일반 블록 우선
            });
            // 무지개 블록 개수 계산
            int cnt = 0;
            for (int[] it : row.blocks)
                if (matrix[it[0]][it[1]] == 0)
                    cnt++;
            row.rainbowCnt = cnt;
        }

        // 그룹 간 정렬 (우선순위 적용)
        groups.sort((a, b) -> {
            if (a.blocks.size() == b.blocks.size()) // 1. 크기 동일
                if (a.rainbowCnt == b.rainbowCnt) { // 2. 무지개 수 동일
                    // 3. 기준 블록 행 비교 (내림차순)
                    if (a.blocks.get(0)[0] == b.blocks.get(0)[0])
                        return b.blocks.get(0)[1] - a.blocks.get(0)[1]; // 4. 열 내림차순
                    else
                        return b.blocks.get(0)[0] - a.blocks.get(0)[0];
                } else
                    return b.rainbowCnt - a.rainbowCnt; // 무지개 수 내림차순
            return b.blocks.size() - a.blocks.size(); // 크기 내림차순
        });
    }

    // 선택된 그룹 제거 및 점수 계산
    private static void remove(Group select) {
        ans += select.blocks.size() * select.blocks.size(); // 점수 추가
        for (int[] it : select.blocks)
            matrix[it[0]][it[1]] = -2; // 빈 공간(-2)으로 표시
    }

    // 중력 적용
    private static void drop() {
        for (int i = 0; i < N; i++) { // 각 열에 대해
            int h = N - 1; // 현재 열의 가장 아래 위치
            for (int j = N - 1; j >= 0; j--) { // 아래에서 위로 탐색
                if (matrix[j][i] == -1) { // 검은 블록 만나면
                    h = j - 1; // 그 위부터 채움
                } else if (matrix[j][i] == -2) { // 빈 공간
                    continue;
                } else { // 이동 가능 블록
                    matrix[h][i] = matrix[j][i]; // 아래로 이동
                    if (h-- != j) // 원래 위치가 아니면
                        matrix[j][i] = -2; // 기존 위치 비움
                }
            }
        }
    }

    // 반시계 방향 90도 회전
    private static void turn() {
        int[][] next = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                next[i][j] = matrix[j][N - i - 1]; // 회전 공식 적용
        matrix = next;
    }

    private static boolean OOB(int y, int x) {
        return (y < 0 || x < 0 || y >= N || x >= N);
    }
}
