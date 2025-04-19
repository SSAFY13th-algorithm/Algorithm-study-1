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

    private static int N, ans; // N: 목표 이모티콘 개수, ans: 최소 시간
    private static int[][] visited; // 방문 배열: [클립보드 개수][화면 이모티콘 개수]

    public static void main(String args[]) throws Exception {
        N = nextInt();

        // 방문 배열 초기화 (최대 1000개의 클립보드, 최대 2N개의 화면 이모티콘)
        visited = new int[2001][2 * N];
        for (int i = 0; i < 1001; i++)
            Arrays.fill(visited[i], Integer.MAX_VALUE);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 1, 0, 0 }); // [화면 이모티콘 개수, 클립보드 개수, 시간]
        visited[0][1] = 0; // 초기 상태 방문 표시

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int screen = now[0]; // 현재 화면의 이모티콘 개수
            int clipboard = now[1]; // 현재 클립보드의 이모티콘 개수
            int time = now[2]; // 현재까지 소요된 시간

            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장 (복사)
            if (screen > clipboard && visited[screen][screen] > time + 1) {
                visited[screen][screen] = time + 1;
                q.add(new int[] { screen, screen, time + 1 });
            }

            // 2. 클립보드에 있는 이모티콘을 화면에 붙여넣기 (붙여넣기)
            if (screen + clipboard < 2 * N && visited[clipboard][screen + clipboard] > time + 1) {
                visited[clipboard][screen + clipboard] = time + 1;
                q.add(new int[] { screen + clipboard, clipboard, time + 1 });
            }

            // 3. 화면에 있는 이모티콘 중 하나를 삭제 (삭제)
            if (screen > 0 && visited[clipboard][screen - 1] > time + 1) {
                visited[clipboard][screen - 1] = time + 1;
                q.add(new int[] { screen - 1, clipboard, time + 1 });
            }
        }

        // 목표 이모티콘 개수(N)에 도달하는 최소 시간 찾기
        ans = Integer.MAX_VALUE;
        for (int i = 0; i < 1001; i++)
            ans = Math.min(ans, visited[i][N]);

        bw.write(ans + "\n");
        bwEnd();
    }
}
