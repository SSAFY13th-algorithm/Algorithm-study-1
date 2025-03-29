package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q4PieceOfPaper {

    private static final int HOR = 0;
    private static final int VER = 1;

    private static final int[] dx = {0, 1};
    private static final int[] dy = {1, 0};

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int max;

    private static int[][] nums;
    private static boolean[][] pieces;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        sol(0, 0);
        
        System.out.println(max);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = 0;

        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                nums[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
        pieces = new boolean[N][M];
        visited = new boolean[N][M];
    }

    private static void sol(int i, int j) {
        if (i == N) {
            max = Math.max(max, totalSum());
            visited = new boolean[N][M];
            return;
        }

        if (j == M) {
            sol(i + 1, 0);
            return;
        }

        // horizontal
        pieces[i][j] = true;
        sol(i, j + 1);

        // vertical
        pieces[i][j] = false;
        sol(i, j + 1);
    }

    private static int totalSum() {
        int res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    continue;
                }
                res += partialSum(i, j, pieces[i][j] ? HOR : VER);
            }
        }
        return res;
    }

    private static int partialSum(int i, int j, int dir) {
        int res = 0;

        int ni = i;
        int nj = j;

        while (ni < N && nj < M && !visited[ni][nj] && 
              (pieces[ni][nj] ? HOR : VER) == dir) {
        	
            res = res * 10 + nums[ni][nj];
            visited[ni][nj] = true;
            
            ni += dx[dir];
            nj += dy[dir];
        }
        return res;
    }

}