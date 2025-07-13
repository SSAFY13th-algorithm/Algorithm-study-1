import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int[][] arr = new int[9][9];
    public static boolean flag = false;

    public static void init() throws IOException {
        String str;
        for(int i = 0; i < 9; i++) {
            str = br.readLine();
            for(int j = 0; j < 9; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
    }

    public static void dfs(int x) {
        if(x == 81) {
            flag = true;
            return;
        }
        int r = x / 9;
        int c = x % 9;
        if(arr[r][c] != 0) dfs(x + 1);
        else {
            for(int i = 1; i < 10; i++) {
                if(!check(r,c,i)) continue;
                arr[r][c] = i;
                dfs(x + 1);

                if(flag) return;
                arr[r][c] = 0;
            }
        }
    }

    public static boolean check(int r, int c, int x) {
        for(int i = 0; i < 9; i++) {
            if(arr[i][c] == x || arr[r][i] == x) return false;
        }
        int nr = r / 3 * 3;
        int nc = c / 3 * 3;
        for(int i = nr; i < nr + 3; i++) {
            for(int j = nc; j < nc + 3; j++) {
                if(arr[i][j] == x) return false;
            }
        }
        return true;
    }

    public static void solution() throws IOException {
        init();

        dfs(0);
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(arr[i][j]);
            }sb.append('\n');
        }
    }

    public static void main(String[] args)throws IOException {
        solution();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}