import java.io.*;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static void readLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    private static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    private static void printAnswer() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    private static int N, M, ans; // N: 행 수, M: 열 수, ans: 최대 합
    private static int[][] matrix; // 입력받은 숫자 배열
    private static boolean[][] visited; // 방문 여부를 체크하는 배열

    public static void main(String[] args) throws IOException {
        readLine();
        N = nextInt();
        M = nextInt();
        matrix = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++)
                matrix[i][j] = line.charAt(j) - '0';
        }

        back(0, 0);

        bw.write(ans + "\n");

        printAnswer();
    }

    // 백트래킹 함수
    public static void back(int now, int cnt) {
        // 모든 칸을 다 확인했을 때
        if (now == N * M) {
            ans = Math.max(ans, cnt); // 최대값 갱신
            return;
        }

        int y = now / M; // 현재 행
        int x = now % M; // 현재 열

        // 이미 방문한 칸이면 다음 칸으로 이동
        if (visited[y][x]) {
            back(now + 1, cnt);
            return;
        }

        int num = matrix[y][x];
        visited[y][x] = true;

        // 현재 칸만 선택하는 경우
        back(now + 1, cnt + num);

        // 세로로 숫자를 이어붙이는 경우
        int i, temp = num;
        for (i = y + 1; i < N; i++) {
            if (visited[i][x])
                break;
            visited[i][x] = true;
            temp = temp * 10 + matrix[i][x];
            back(now + 1, cnt + temp);
        }
        // 방문 표시 원복
        for (int j = y + 1; j < i; j++)
            visited[j][x] = false;

        // 가로로 숫자를 이어붙이는 경우
        temp = num;
        for (i = x + 1; i < M; i++) {
            if (visited[y][i])
                break;
            visited[y][i] = true;
            temp = temp * 10 + matrix[y][i];
            back(now + i - x + 1, cnt + temp);
        }
        // 방문 표시 원복
        for (int j = x + 1; j < i; j++)
            visited[y][j] = false;

        visited[y][x] = false; // 현재 칸 방문 표시 원복
    }
}
