import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N,X;
    public static List<Pipe> pipes = new ArrayList<>();
    public static int[] dp;
    public static class Pipe{
        int l,c;
        public Pipe(int l, int c) {
            this.l = l;
            this.c = c;
        }
    }

    public static void init() throws IOException {
        int in[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = in[0];
        X = in[1];
        dp = new int[X + 1];
        pipes.add(new Pipe(-1,-1));
        for(int i = 0; i < N; i++) {
            in = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pipes.add(new Pipe(in[0],in[1]));
        }
    }
    public static void solution() throws IOException{
        init();
        dp[0] = 1;
        for(int i = 1; i <= N; i++) {
            Pipe pipe = pipes.get(i);
            for(int j = X; j >= 0; j--) {
                for(int k = 1; k <= pipe.c; k++) {
                    if(j - (k * pipe.l) < 0) continue;
                    dp[j] += dp[j - (k * pipe.l)];
                }
            }
        }
        System.out.println(dp[X]);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
