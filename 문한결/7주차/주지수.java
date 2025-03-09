import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 
//
//
//   dp[i][j]: 1,1부터 i,j까지 사각형으로 만들었을 때, 합
//   x1,y1,x2,y2   dp[y2][x2]-dp[y1-1][x2]-dp[y2][x2-1]+dp[y2-1][x2-1]
//
//

public class 주지수 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	static int[]input;

    static int answer=0;
    static int[][]arr,stackArr;
    static int k,x1,y1,x2,y2,N,M;

	
	public static void main(String[] args) throws IOException {
    input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N=input[0];M=input[1];
    arr=new int [N+1][M+1];
	stackArr=new int [N+1][M+1];
    for(int i=1;i<N+1;i++) {
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int j=1;j<M+1;j++)
        	arr[i][j]=input[j-1];
    }
    setArr();

    k=Integer.parseInt(br.readLine());
    for(int i=0;i<k;i++) {
    	
        input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        y1=input[0]; x1=input[1]; y2=input[2]; x2=input[3];
        setAnswer();
                
    	System.out.println(answer);

    }
    
	}
	static void setArr() {
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<M+1;j++) {
				stackArr[i][j]=stackArr[i][j-1]+arr[i][j];
				arr[i][j]=arr[i-1][j]+stackArr[i][j];
			}
		}
	}
	static void setAnswer() {
		answer=arr[y2][x2]-arr[y2][x1-1]-arr[y1-1][x2]+arr[y1-1][x1-1];
	}
	

	
}
