import java.util.Scanner;

public class 계단수 {
    static final int DIVISOR = 1000000000;
    static int ans = 0;
    static int num;
    static long [][][] dp;
    public static void main(String[] args) {
        init();
        process();
        print();


    }
    private static void print() {System.out.println(ans);}


    private static void process() {
        dp();
        for (int i = 0; i <= 9; i++) {
            ans += dp[num][i][1023];
            ans %= DIVISOR;
        }
    }


    private static void dp() {
        for (int i = 2; i<= num; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {

                    if( j!= 0) {
                        dp[i][j][k|(1<<j)] += dp[i-1][j-1][k];
                    }

                    if (j != 9) {
                        dp[i][j][k|(1<<j)] += dp[i-1][j+1][k];
                    }
                    dp[i][j][k|(1<<j)] %= DIVISOR;
                }
            }
        }

    }




    private static void init() {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        dp = new long[num+1][10][1024];
        for (int i = 1 ; i <= 9; i++) dp[1][i][1 <<i] = 1;
    }

}
