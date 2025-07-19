package semester2.week1;

import java.io.*;

public class Q1Sudoku {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    private static char[][] input;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }

    private static void init() throws IOException {
        input = new char[9][9];

        for (int i = 0; i < 9; i++) {
            String str = br.readLine();

            for (int j = 0; j < 9; j++) {
                input[i][j] = str.charAt(j);
            }
        }
    }

    private static void sol() throws IOException {
        if (dfs(0, 0)) {
            for (int a = 0; a < 9; a++) {
                for (int b = 0; b < 9; b++) {
                    sb.append(input[a][b]);
                }
                sb.append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        }
    }

    private static boolean dfs(int i, int j) {
        if (i >= 9) {
            return true;
        }

        int nx = j == 8 ? i + 1 : i;
        int ny = j == 8 ? 0 : j + 1;

        if (input[i][j] != '0') return dfs(nx, ny);

        for (int k = 1; k <= 9; k++) {
            char val = (char) (k + '0');

            input[i][j] = val;

            if (isValid(i, j)) {
                if (dfs(nx, ny)) {
                    return true;
                }
            }
            input[i][j] = '0';
        }
        return false;
    }

    private static boolean isValid(int x, int y) {
        // vertical
        for (int i = 0; i < 9; i++) {
            if (i == x) continue;
            if (input[i][y] == input[x][y]) return false;
        }

        // horizontal
        for (int j = 0; j < 9; j++) {
            if (j == y) continue;
            if (input[x][j] == input[x][y]) return false;
        }

        // square
        int a = x / 3 * 3;
        int b = y / 3 * 3;
        for (int i = a; i < a + 3; i++) {
            for (int j = b; j < b + 3; j++) {
                if (x == i && y == j) continue;
                if (input[i][j] == input[x][y]) return false;
            }
        }
        return true;
    }

}
