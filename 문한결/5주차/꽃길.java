import java.util.*;
import java.io.*;

public class 꽃길 {

	static int []dx=new int[] {0,-1,-1,-1,0,1,1,1},dy=new int[] {-1,-1,0,1,1,1,0,-1};

	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int answer=Integer.MAX_VALUE;
	static int[] input;
	static int [][] arr;
	static int [][] arr2;

	static int N;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		N=Integer.parseInt(br.readLine());
		arr=new int[N][N];
		arr2=new int[N][N];
		for(int i=0;i<N;i++) {
			input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0;j<N;j++)
				arr[i][j]=input[j];
				
		}
		
		makeFlowers(0,0,arr,0,0);
		System.out.println(answer);
	
      
		
	}
	static void makeFlowers(int x,int y,int [][]curMap,int flowerN,int sum) {
		int [][]copyMap=new int[N][N];
		for(int i=0;i<N;i++) {
		   for(int j=0;j<N;j++)
			   copyMap[i][j]=curMap[i][j];
		}
		if(flowerN==3) {
			answer=Math.min(sum, answer);
			return;
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i<y && j<x)
					continue;
				if(isExistFlower(j,i,copyMap)) {
					arr2[i][j]=1;
					int dx[]=new int[] {1,-1,0,0};
					int dy[]=new int[] {0,0,1,-1};
					int sumTemp=copyMap[i][j];
					copyMap[i][j]=-1;
					for(int k=0;k<4;k++) {
						int nx=dx[k]+j; int ny=dy[k]+i;
						sumTemp+=copyMap[ny][nx];
						copyMap[ny][nx]=-1;
					}
					makeFlowers(i,j,copyMap,flowerN+1,sum+sumTemp);
					copyMap[i][j]=curMap[i][j];
					for(int k=0;k<4;k++) {
						int nx=dx[k]+j; int ny=dy[k]+i;
						copyMap[ny][nx]=curMap[ny][nx];
					}
					
				}
			}
		}		
	}
	static boolean isExistFlower(int x,int y,int [][]map) {
		int dx[]=new int[] {1,-1,0,0};
		int dy[]=new int[] {0,0,1,-1};
		if(map[y][x]<0)
			return false;
		for(int i=0;i<4;i++) {
			int nx=dx[i]+x; int ny=dy[i]+y;
			if(nx<0 || nx>=N || ny<0 || ny>=N || map[ny][nx]<0)
				return false;
		}
		return true;

		
	}
	static int findSum(int x,int y,int [][]map) {
		int dx[]=new int[] {1,-1,0,0};
		int dy[]=new int[] {0,0,1,-1};
		int sum=0;
		for(int i=0;i<4;i++) {
			int nx=dx[i]+x; int ny=dy[i]+y;
			sum+=map[ny][nx];
			map[ny][nx]=-1;
		}
		return sum;
	}
}

