import java.util.*;

public class Main {
    static int N;
    static int[][] house;
    static int count = 0;

    public static void main(String[] args) {
        // 입력 처리
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        house = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                house[i][j] = sc.nextInt();
            }
        }

        // 초기 상태에서 시작 (1, 2)에서 가로 방향
        dfs(1, 2, 0);

        // 결과 출력
        System.out.println(count);
    }

    static void dfs(int r, int c, int dir) {
        // 종료 조건: (N, N)에 도달
        if (r == N && c == N) {
            count++;
            return;
        }

        // 가로 방향
        if (dir == 0 || dir == 2) {
            if (c + 1 <= N && house[r][c + 1] == 0) {
                dfs(r, c + 1, 0);
            }
        }

        // 세로 방향
        if (dir == 1 || dir == 2) {
            if (r + 1 <= N && house[r + 1][c] == 0) {
                dfs(r + 1, c, 1);
            }
        }

        // 대각선 방향
        if (r + 1 <= N && c + 1 <= N &&
            house[r][c + 1] == 0 && house[r + 1][c] == 0 && house[r + 1][c + 1] == 0) {
            dfs(r + 1, c + 1, 2);
        }
    }
}
