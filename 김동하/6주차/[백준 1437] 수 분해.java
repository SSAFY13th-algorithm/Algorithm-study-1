import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringBuilder sb = new StringBuilder();

    public static int n;


    public static void init() throws IOException{
        n = Integer.parseInt(br.readLine());
    }

    public static int devide(int num) {
        int ans = 1;
        for(int i = 0; i < num / 3; i++) {
            ans = (ans * 3) % 10007;
        }
        return ans;
    }
    public static int devideThree(int num) {
        if(num < 5) return num;
        if(num % 3 == 0) return devide(num);
        if(num % 3 == 1) return (devide(num - 4) * 4) % 10007;
        else return (devide(num - 2) * 2) % 10007;
    }
    public static void solution() throws IOException{
        init();
        sb.append(devideThree(n));
        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

