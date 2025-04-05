package solution;

import java.util.*;
import java.io.*;
public class 계단수 {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int [][][] arr;
    static int answer=0;



    public static void main(String[] args)throws Exception {
        init();
        execute();
        System.out.println(answer);
        
        
    }

    static void init() throws IOException{
        n=Integer.parseInt(br.readLine());
        arr=new int[n+1][10][1<<10];
      
      
    }

    static void execute(){
        // arr[1][0][0]=0;
        for(int i=1;i<10;i++){
            arr[1][i][1<<i]=1;
        }
        for(int i=2;i<=n;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<1024;k++){
                    if(j!=0){
                        int a= k | (1<<(j)); 
                        arr[i][j][a]+=arr[i-1][j-1][k];
                        arr[i][j][a]%=1000000000;
                    }
                    if(j!=9){
                        int a= k | (1<<(j)); 
                        arr[i][j][a]+=arr[i-1][j+1][k];
                        arr[i][j][a]%=1000000000;
                    }
                }
            
        }
        }
        for(int i=0;i<10;i++)
        answer=(answer+arr[n][i][1023])%1000000000;

      

    }
    
}



