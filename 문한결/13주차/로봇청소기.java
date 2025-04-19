package solution;

import java.util.*;
import java.io.*;
public class 로봇청소기 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int input[];
	static int n,m;
	static int robotX,robotY,d;
	static int[]dx= {0,1,0,-1},dy= {-1,0,1,0};
	static int[][]maps;
	static boolean clean[][];
	static int answer=0;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		init();
		execute();
		System.out.println(answer);

	}
	static void init() throws Exception{
		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n=input[0]; m=input[1];
		maps=new int[n][m];
		clean=new boolean[n][m];
		input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		robotX=input[1]; robotY=input[0]; d=input[2];
		for(int i=0;i<n;i++) {
			input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int j=0;j<m;j++)
				maps[i][j]=input[j];
		}
		
	}
	static void execute() {
		while(true) {
		
		
			if(cleanAtCurrentPos(robotX,robotY))continue;
			if(!isExistNotClean(robotX,robotY)) {
				if(!executeStep2(robotX,robotY))return;
			}else {
				executeStep3(robotX,robotY);
			}
			
		}
		
	}
	static boolean cleanAtCurrentPos(int x,int y) {
		if(!clean[y][x]) {
			clean[y][x]=true;
			answer++;
			return true;
		}
		return false;
	}
	static boolean isExistNotClean(int x,int y) {
		for(int i=0;i<4;i++) {
			int nx=dx[i]+x; int ny=dy[i]+y;
			if(OOB(nx,ny) && !clean[ny][nx] && maps[ny][nx]==0)return true;
		}
		return false;
	}

	static boolean executeStep2(int x,int y) {
		int backD=(d+2)%4;
		int nx=x+dx[backD],ny=y+dy[backD];
		if(!OOB(nx,ny)|| maps[ny][nx]==1)return false;
		robotX=nx;
		robotY=ny;
		return true;
		
		
	}
	static void executeStep3(int x,int y) {
		int rototeD=(d+3)%4;
	
		int nx=x+dx[rototeD],ny=y+dy[rototeD];
		if(OOB(nx,ny) && maps[ny][nx]==0 && !clean[ny][nx]) {
			robotX=nx; robotY=ny; 
		}
		d=rototeD;
		
		
	}
	
	
	
	static boolean OOB(int x,int y) {
		return !(x>=m || x<0 || y>=n || y<0);
	}

}
