import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9]; // 스도쿠판
    static boolean[][] row = new boolean[9][10]; // row[a][b] = a번째 줄에 b가 있는지
    static boolean[][] col = new boolean[9][10]; // col[a][b] = a번째 칸에 b가 있는지
    static boolean[][] box = new boolean[9][10]; // box[a][b] = a번째 박스에 b가 있는지
    static List<int[]> blanks = new ArrayList<>(); // 빈칸 위치
    static boolean solved = false; // 풀었는지 안풀었는지

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = str.charAt(j) - '0';
                board[i][j] = num;
                if (num == 0) {
                    blanks.add(new int[]{i, j});
                } else { // 입력하면서 있는지에 대한 처리
                    row[i][num] = true;
                    col[j][num] = true;
                    box[(i / 3) * 3 + (j / 3)][num] = true;
                }
            }
        }

        dfs(0);
    }

    static void dfs(int depth) {
        if (depth == blanks.size()) { // 풀었으면 끝
            StringBuilder sb = new StringBuilder();
            for (int[] line : board) {
                for (int num : line) {
                    sb.append(num);
                }
                sb.append('\n');
            }
            System.out.print(sb);
            solved = true;
            return;
        }

        int[] pos = blanks.get(depth); // 빈칸마다 탐색
        int x = pos[0];
        int y = pos[1];
        int boxnum = (x / 3) * 3 + (y / 3);

        for (int num = 1; num <= 9; num++) { // 중복정답인경우 작은 수 출력이므로 1부터 시작
            if (!row[x][num] && !col[y][num] && !box[boxnum][num]) { // 수 검증
                row[x][num] = col[y][num] = box[boxnum][num] = true; // 방문 처리
                board[x][y] = num; // 입력

                dfs(depth + 1);
                if (solved) return;

                board[x][y] = 0; // 백트래킹
                row[x][num] = col[y][num] = box[boxnum][num] = false;
            }
        }
    }
}
