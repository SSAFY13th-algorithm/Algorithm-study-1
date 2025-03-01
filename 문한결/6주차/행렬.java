import java.util.*;
import java.io.*;
public class 행렬 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	static int[]input;
	static int N,M;
	static int [][]arr;
	static int [][]target;

	public static void main(String[] args) throws IOException {
    input=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N=input[0]; M=input[1];
    arr=new int[N][M];
    target=new int[N][M];
    for(int i=0;i<N;i++) {
        input=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        for(int j=0;j<M;j++)
        	arr[i][j]=input[j];
    }
    for(int i=0;i<N;i++) {
        input=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        for(int j=0;j<M;j++)
        	target[i][j]=input[j];
    }
    if(N < 3 || M < 3) {
        if(isFinish()) System.out.println(0);
        else System.out.println(-1);
        System.exit(0);
    }

    findMinFlip();
    
	System.out.println(-1);
	
	}
	public static boolean isFinish() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++)
			{
				if(target[i][j]!=arr[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void findMinFlip() {
		int answer=0;
		for(int i=0;i<=N-3;i++) {
			for(int j=0;j<=M-3;j++) {
				if(isDif(j,i)) {
				flip(j,i);
				answer+=1;
				}
				if(isFinish()) {
					System.out.println(answer);
					System.exit(0);
				}
				}
			
		}
		return ;
		
	}
	public static void flip(int x,int y) {
		for(int i=y;i<y+3;i++) {
			for(int j=x;j<x+3;j++) {
				arr[i][j]=(arr[i][j]==1?0:1);
			}
		}
	}
	public static boolean isDif(int x,int y) {
		if(target[y][x]!=arr[y][x])return true;
			
		return false;
	}
	
	
}