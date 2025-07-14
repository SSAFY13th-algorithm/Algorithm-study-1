import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<int[]> list = new ArrayDeque<>();
    static int n, m;
    static int[][] w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        w = new int[n][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken()) - 1;
            int small = Integer.parseInt(st.nextToken()) - 1;
            w[big][small] = 1;
            w[small][big] = 2;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (w[i][k] == 1 && w[k][j] == 1) w[i][j] = 1;
                    if (w[i][k] == 2 && w[k][j] == 2) w[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && w[i][j] == 0) count++;
            }
            System.out.println(count);
        }
    }


}
