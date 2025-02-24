import java.util.*;
import java.io.*;
public class 진우의달여행 {
	static int [] input;
	static int N,M;
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int[][] arr;
	static int [][][]dp;
	static int answer=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N=input[0]; M=input[1];
		arr=new int[N][M]; dp=new int[3][N][M];
		for(int i=0;i<N;i++) {
			input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0;j<M;j++) {
				arr[i][j]=input[j];
			}
		}
		for(int i=0;i<M;i++) {
			for(int j=0;j<3;j++)
				dp[j][0][i]=arr[0][i];
		}
		
		for(int i=1;i<N;i++) {
			for(int j=0;j<M;j++) {
				dp[0][i][j]=j!=M-1?arr[i][j]+Math.min(dp[1][i-1][j+1], dp[2][i-1][j+1]):Integer.MAX_VALUE;
				dp[1][i][j]=arr[i][j]+Math.min(dp[0][i-1][j], dp[2][i-1][j]);
				dp[2][i][j]=j!=0 ?arr[i][j]+Math.min(dp[0][i-1][j-1], dp[1][i-1][j-1]): Integer.MAX_VALUE;	
			}
		}
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<3;j++)
				answer=Math.min(answer, dp[j][N-1][i]);
		}
		System.out.println(answer);
		}
	}