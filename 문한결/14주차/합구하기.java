import java.io.*;
import java.util.*;

public class 합구하기 {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n,t;
    static int []arr,input,dp;
    public static void main(String[] args) throws  Exception{
        init();
        execute();

    }
    static void init() throws IOException{
        n=Integer.parseInt(br.readLine());
        arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        t=Integer.parseInt(br.readLine());
        dp=new int[n+1];
        dp[1]=arr[0];
        for(int i=2;i<=n;i++)dp[i]=dp[i-1]+arr[i-1];
    }
    static void execute() throws  Exception{
        for(int i=0;i<t;i++){
            input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(dp[input[1]]-dp[input[0]-1]);
        }

    }

}