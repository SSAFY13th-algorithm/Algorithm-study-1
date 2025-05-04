import java.util.*;
import java.io.*;


//        1 1 -> 1

public class 경로찾기 {
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[][]arr;
    static int[]input;
    public static void main(String[] args)throws Exception {
        init();
        execute();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) System.out.print(arr[i][j]+" ");
            System.out.println();
        }


    }
    static void init() throws IOException {
        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        for(int i=0;i<n;i++){
            input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0;j<n;j++)arr[i][j]=input[j];
        }
    }


    static void execute(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(arr[j][i]==1 && arr[i][k]==1)arr[j][k]=1;
                }
            }
        }
    }

}