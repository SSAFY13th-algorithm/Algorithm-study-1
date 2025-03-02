import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n,m;
    public static int[][] arr;
    public static int[][] check;

    public static void init() throws IOException {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[n + 1][m + 1];
        check = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            for(int j = 1; j <= m; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }
        for(int i = 1; i <= n; i++) {
            String str = br.readLine();
            for(int j = 1; j <= m; j++) {
                check[i][j] = str.charAt(j - 1) - '0';
            }
        }
    }

    public static void reverse(int x, int y) {
        for(int i = x; i < x + 3; i++) {
            for(int j = y; j < y + 3; j++) {
                arr[i][j] = 1 - arr[i][j];
            }
        }
    }
    public static boolean isSame() {
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (arr[i][j] != check[i][j])
                    return false;
            }
        }
        return true;
    }

    public static int greedy() {
        int cnt = 0;
        if(isSame())return 0;
        for(int i = 1; i <= n - 2; i++) {
            for(int j = 1; j <= m - 2; j++) {
                if(arr[i][j] != check[i][j]) {
                    reverse(i,j);
                    cnt++;
                    if(isSame()) return cnt;
                }
            }
        }
        return -1;
    }
    public static void solution() throws IOException {
        init();
        sb.append(greedy());
        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

