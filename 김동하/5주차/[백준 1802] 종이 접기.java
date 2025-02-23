import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int T;
    public static String s;

    public static void init() throws IOException {
        s = br.readLine();
    }


    public static boolean solve(int l, int r) {
        if(l == r) return true;
        int m = (l + r) / 2;
        for(int i = l; i < m; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(r - i);
            if(c1 == c2) return false;
        }
        return solve(l, m - 1) && solve(m + 1, r);
    }

    public static void solution() throws IOException{
        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            init();
            boolean flag = solve(0,s.length() - 1);
            if(flag) System.out.println("YES");
            else System.out.println("NO");
        }


    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
