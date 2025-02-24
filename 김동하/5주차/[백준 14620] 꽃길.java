import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int n;
    public static int ans = Integer.MAX_VALUE;
    public static int[][] arr = new int[11][11];
    public static boolean[][] isSelected = new boolean[11][11];

    public static int dx[] = {1,0,-1,0};
    public static int dy[] = {0,1,0,-1};

    public static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        int[] in;
        for(int i = 1; i <= n; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 1; j <= n; j++) {
                arr[i][j] = in[j - 1];
            }
        }
    }

    public static boolean OOB(int x, int y) {
        if(x > n || x < 1 || y > n || y < 1 || isSelected[x][y]) return true;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx > n || nx < 1 || ny > n || ny < 1 || isSelected[nx][ny]) return true;
        }
        return false;
    }

    public static void select(int x, int y, boolean flag) {
        isSelected[x][y] = flag;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            isSelected[nx][ny] = flag;
        }
    }

    public static void countCost() {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(isSelected[i][j]) sum += arr[i][j];
            }
        }
        ans = Math.min(ans, sum);
    }

    public static void track(int x, int y, int cnt) {
        if(cnt == 3) {
            countCost();
            return;
        }
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(OOB(i,j)) continue;
                select(i,j,true);
                track(i,j,cnt + 1);
                select(i,j,false);
            }
        }
    }

    public static void solution() throws IOException{
        init();
        track(1,1,0);
        System.out.println(ans);

    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
