import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ZOAC_7 {
    static String[] targets = {"Z","O","A","C"};
    static int height, width;
    static String[][] arr;
    static int [][][] dp;
    static int [][][] reverseDp;
    static int [] ans = new int[4];

    public static void main(String[] args) throws IOException {
        init();
        process();
    }

    private static void process() {
        processDp();
        processReverseDp();
        findHighest();
        print();
    }

    private static void print() {
        for (int i = 0; i < 4; i++) {
            System.out.print(ans[i]+" ");
        }
    }

    private static void findHighest() {
        for (int k = 0; k < 4; k++) {
            ans[k] =  dp[height-1][width-1][k];
            for (int i = 0; i < width-1; i++) {
                ans[k] = Math.max(ans[k], dp[height-1][i][k] + reverseDp[0][i+1][k]);
            }

            for (int i = 0; i < height-1; i++) {
                ans[k] = Math.max(ans[k], dp[i][width-1][k] + reverseDp[i+1][0][k]);
            }
        }
    }

    private static void processDp() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < 4; k++) {
                    if ( i > 0 )dp[i][j][k] = dp[i-1][j][k];
                    if ( j > 0 )dp[i][j][k]= Math.max(dp[i][j][k], dp[i][j-1][k]);

                    if (arr[i][j].equals(targets[k])) dp[i][j][k]++;
                }
            }
        }
    }

    private static void processReverseDp() {
        for (int i = height-1; i >= 0; i--) {
            for (int j = width-1; j >= 0; j--) {
                for (int k = 0; k < 4; k++) {
                    if ( i < height-1 )reverseDp[i][j][k] = reverseDp[i+1][j][k];
                    if ( j < width-1 )reverseDp[i][j][k]= Math.max(reverseDp[i][j][k], reverseDp[i][j+1][k]);

                    if (arr[i][j].equals(targets[k])) reverseDp[i][j][k]++;
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        arr = new String[height][width];
        dp = new int[height][width][4]; // 4는 사람당 하나
        reverseDp = new int[height][width][4]; // 반대로 도착지점부터 출발지점까지 기록

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) arr[i][j] = st.nextToken();
        }
    }
}
